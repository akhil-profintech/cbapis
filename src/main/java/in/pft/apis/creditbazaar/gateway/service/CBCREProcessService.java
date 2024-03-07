package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.CBCREProcessRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CBCREProcessMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.CBCREProcess}.
 */
@Service
@Transactional
public class CBCREProcessService {

    private final Logger log = LoggerFactory.getLogger(CBCREProcessService.class);

    private final CBCREProcessRepository cBCREProcessRepository;

    private final CBCREProcessMapper cBCREProcessMapper;

    public CBCREProcessService(CBCREProcessRepository cBCREProcessRepository, CBCREProcessMapper cBCREProcessMapper) {
        this.cBCREProcessRepository = cBCREProcessRepository;
        this.cBCREProcessMapper = cBCREProcessMapper;
    }

    /**
     * Save a cBCREProcess.
     *
     * @param cBCREProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CBCREProcessDTO> save(CBCREProcessDTO cBCREProcessDTO) {
        log.debug("Request to save CBCREProcess : {}", cBCREProcessDTO);
        cBCREProcessDTO.setCbProcessId(IdAndUlidGeneration.generateUniqueLong(9));
        cBCREProcessDTO.setCbProcessUlidId(IdAndUlidGeneration.generateUlid());
        return cBCREProcessRepository.save(cBCREProcessMapper.toEntity(cBCREProcessDTO))
            .flatMap(savedEntity->{
                cBCREProcessDTO.setCbProcessRefNo("CBCRE-PTS-"+savedEntity.getId());
                return cBCREProcessRepository.findById(savedEntity.getId())
                    .flatMap(existingEntity->{
                        existingEntity.setCbProcessRefNo(cBCREProcessDTO.getCbProcessRefNo());
                        return cBCREProcessRepository.save(existingEntity).map(cBCREProcessMapper::toDto);
                    });
                });
    }

    /**
     * Update a cBCREProcess.
     *
     * @param cBCREProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CBCREProcessDTO> update(CBCREProcessDTO cBCREProcessDTO) {
        log.debug("Request to update CBCREProcess : {}", cBCREProcessDTO);
        return cBCREProcessRepository.save(cBCREProcessMapper.toEntity(cBCREProcessDTO)).map(cBCREProcessMapper::toDto);
    }

    /**
     * Partially update a cBCREProcess.
     *
     * @param cBCREProcessDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CBCREProcessDTO> partialUpdate(CBCREProcessDTO cBCREProcessDTO) {
        log.debug("Request to partially update CBCREProcess : {}", cBCREProcessDTO);

        return cBCREProcessRepository
            .findById(cBCREProcessDTO.getId())
            .map(existingCBCREProcess -> {
                cBCREProcessMapper.partialUpdate(existingCBCREProcess, cBCREProcessDTO);

                return existingCBCREProcess;
            })
            .flatMap(cBCREProcessRepository::save)
            .map(cBCREProcessMapper::toDto);
    }

    /**
     * Get all the cBCREProcesses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CBCREProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CBCREProcesses");
        return cBCREProcessRepository.findAllBy(pageable).map(cBCREProcessMapper::toDto);
    }

    /**
     * Get all the cBCREProcesses with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<CBCREProcessDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cBCREProcessRepository.findAllWithEagerRelationships(pageable).map(cBCREProcessMapper::toDto);
    }

    /**
     * Returns the number of cBCREProcesses available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return cBCREProcessRepository.count();
    }

    /**
     * Get one cBCREProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CBCREProcessDTO> findOne(Long id) {
        log.debug("Request to get CBCREProcess : {}", id);
        return cBCREProcessRepository.findOneWithEagerRelationships(id).map(cBCREProcessMapper::toDto);
    }

    /**
     * Delete the cBCREProcess by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CBCREProcess : {}", id);
        return cBCREProcessRepository.deleteById(id);
    }
}
