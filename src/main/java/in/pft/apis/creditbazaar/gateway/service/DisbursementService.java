package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.DisbursementRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.DisbursementMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.Disbursement}.
 */
@Service
@Transactional
public class DisbursementService {

    private final Logger log = LoggerFactory.getLogger(DisbursementService.class);

    private final DisbursementRepository disbursementRepository;

    private final DisbursementMapper disbursementMapper;

    public DisbursementService(DisbursementRepository disbursementRepository, DisbursementMapper disbursementMapper) {
        this.disbursementRepository = disbursementRepository;
        this.disbursementMapper = disbursementMapper;
    }

    /**
     * Save a disbursement.
     *
     * @param disbursementDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<DisbursementDTO> save(DisbursementDTO disbursementDTO) {
        log.debug("Request to save Disbursement : {}", disbursementDTO);
        disbursementDTO.setDbmtId(IdAndUlidGeneration.generateUniqueLong(9));
        disbursementDTO.setDisbursementUlidId(IdAndUlidGeneration.generateUlid());
        return disbursementRepository.save(disbursementMapper.toEntity(disbursementDTO))
            .flatMap(savedEntity->{
                disbursementDTO.setDisbursementRefNo("DBCR-PTS-PBY-FRCR-PTS-"+savedEntity.getId());
                return disbursementRepository.findById(savedEntity.getId())
                    .flatMap(existingEntity->{
                        existingEntity.setDisbursementRefNo(disbursementDTO.getDisbursementRefNo());
                        return disbursementRepository.save(existingEntity)
                            .map(disbursementMapper::toDto);
                    });
            });
    }

    /**
     * Update a disbursement.
     *
     * @param disbursementDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<DisbursementDTO> update(DisbursementDTO disbursementDTO) {
        log.debug("Request to update Disbursement : {}", disbursementDTO);
        return disbursementRepository.save(disbursementMapper.toEntity(disbursementDTO)).map(disbursementMapper::toDto);
    }

    /**
     * Partially update a disbursement.
     *
     * @param disbursementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<DisbursementDTO> partialUpdate(DisbursementDTO disbursementDTO) {
        log.debug("Request to partially update Disbursement : {}", disbursementDTO);

        return disbursementRepository
            .findById(disbursementDTO.getId())
            .map(existingDisbursement -> {
                disbursementMapper.partialUpdate(existingDisbursement, disbursementDTO);

                return existingDisbursement;
            })
            .flatMap(disbursementRepository::save)
            .map(disbursementMapper::toDto);
    }

    /**
     * Get all the disbursements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<DisbursementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Disbursements");
        return disbursementRepository.findAllBy(pageable).map(disbursementMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<DisbursementDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return disbursementRepository.findAllByFilter(filter, pageable).map(disbursementMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return disbursementRepository.countByFilter(filter);
    }


    /**
     * Get all the disbursements with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<DisbursementDTO> findAllWithEagerRelationships(Pageable pageable) {
        return disbursementRepository.findAllWithEagerRelationships(pageable).map(disbursementMapper::toDto);
    }

    /**
     * Returns the number of disbursements available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return disbursementRepository.count();
    }

    /**
     * Get one disbursement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<DisbursementDTO> findOne(Long id) {
        log.debug("Request to get Disbursement : {}", id);
        return disbursementRepository.findOneWithEagerRelationships(id).map(disbursementMapper::toDto);
    }

    /**
     * Delete the disbursement by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Disbursement : {}", id);
        return disbursementRepository.deleteById(id);
    }
}
