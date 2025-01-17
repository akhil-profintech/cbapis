package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.RepaymentRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.RepaymentDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.RepaymentMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.Repayment}.
 */
@Service
@Transactional
public class RepaymentService {

    private final Logger log = LoggerFactory.getLogger(RepaymentService.class);

    private final RepaymentRepository repaymentRepository;

    private final RepaymentMapper repaymentMapper;

    public RepaymentService(RepaymentRepository repaymentRepository, RepaymentMapper repaymentMapper) {
        this.repaymentRepository = repaymentRepository;
        this.repaymentMapper = repaymentMapper;
    }

    /**
     * Save a repayment.
     *
     * @param repaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<RepaymentDTO> save(RepaymentDTO repaymentDTO) {
        log.debug("Request to save Repayment : {}", repaymentDTO);
        repaymentDTO.setRepaymentId(IdAndUlidGeneration.generateUniqueLong(9));
        repaymentDTO.setRepaymentUlidId(IdAndUlidGeneration.generateUlid());
        return repaymentRepository.save(repaymentMapper.toEntity(repaymentDTO))
            .flatMap(savedEntity->{
                repaymentDTO.setRepaymentRefNo("RPCR-RNH-PTS-PBY-FRCR-PTS-"+savedEntity.getId());
                    return repaymentRepository.findById(savedEntity.getId())
                        .flatMap(existingEntity->{
                            existingEntity.setRepaymentRefNo(repaymentDTO.getRepaymentRefNo());
                            return repaymentRepository.save(existingEntity).map(repaymentMapper::toDto);
                        });
            });
    }

    /**
     * Update a repayment.
     *
     * @param repaymentDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<RepaymentDTO> update(RepaymentDTO repaymentDTO) {
        log.debug("Request to update Repayment : {}", repaymentDTO);
        return repaymentRepository.save(repaymentMapper.toEntity(repaymentDTO)).map(repaymentMapper::toDto);
    }

    /**
     * Partially update a repayment.
     *
     * @param repaymentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<RepaymentDTO> partialUpdate(RepaymentDTO repaymentDTO) {
        log.debug("Request to partially update Repayment : {}", repaymentDTO);

        return repaymentRepository
            .findById(repaymentDTO.getId())
            .map(existingRepayment -> {
                repaymentMapper.partialUpdate(existingRepayment, repaymentDTO);

                return existingRepayment;
            })
            .flatMap(repaymentRepository::save)
            .map(repaymentMapper::toDto);
    }

    /**
     * Get all the repayments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<RepaymentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Repayments");
        return repaymentRepository.findAllBy(pageable).map(repaymentMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<RepaymentDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return repaymentRepository.findAllByFilter(filter, pageable).map(repaymentMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return repaymentRepository.countByFilter(filter);
    }


    /**
     * Get all the repayments with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<RepaymentDTO> findAllWithEagerRelationships(Pageable pageable) {
        return repaymentRepository.findAllWithEagerRelationships(pageable).map(repaymentMapper::toDto);
    }

    /**
     * Returns the number of repayments available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return repaymentRepository.count();
    }

    /**
     * Get one repayment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<RepaymentDTO> findOne(Long id) {
        log.debug("Request to get Repayment : {}", id);
        return repaymentRepository.findOneWithEagerRelationships(id).map(repaymentMapper::toDto);
    }

    /**
     * Delete the repayment by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Repayment : {}", id);
        return repaymentRepository.deleteById(id);
    }
}
