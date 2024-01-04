package in.pft.apis.creditbazaar.service;

import de.huxhorn.sulky.ulid.ULID;
import in.pft.apis.creditbazaar.repository.FinanceRequestRepository;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.mapper.FinanceRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.FinanceRequest}.
 */
@Service
@Transactional
public class FinanceRequestService {

    private final Logger log = LoggerFactory.getLogger(FinanceRequestService.class);

    private final FinanceRequestRepository financeRequestRepository;

    private final FinanceRequestMapper financeRequestMapper;

    private static final ULID ulid = new ULID();


    public FinanceRequestService(FinanceRequestRepository financeRequestRepository, FinanceRequestMapper financeRequestMapper) {
        this.financeRequestRepository = financeRequestRepository;
        this.financeRequestMapper = financeRequestMapper;
    }

    public  String generateUlid() {
        return ulid.nextULID();
    }


    /**
     * Save a financeRequest.
     *
     * @param financeRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FinanceRequestDTO> save(FinanceRequestDTO financeRequestDTO) {
        log.debug("Request to save FinanceRequest : {}", financeRequestDTO);

        return financeRequestRepository
            .save(financeRequestMapper.toEntity(financeRequestDTO))
            .flatMap(savedEntity->{

                financeRequestDTO.setRequestId(generateUlid());
                financeRequestDTO.setFinanceRequestRefNo("FRCR-IKF-" + savedEntity.getId());

                return financeRequestRepository.findById(savedEntity.getId())
                    .flatMap(existingEntity->{

                        existingEntity.setRequestId(financeRequestDTO.getRequestId());
                        existingEntity.setFinanceRequestRefNo(financeRequestDTO.getFinanceRequestRefNo());

                    return financeRequestRepository.save(existingEntity)
                        .map(financeRequestMapper::toDto);
                    });
            });
    }

    /**
     * Update a financeRequest.
     *
     * @param financeRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FinanceRequestDTO> update(FinanceRequestDTO financeRequestDTO) {
        log.debug("Request to update FinanceRequest : {}", financeRequestDTO);
        return financeRequestRepository.save(financeRequestMapper.toEntity(financeRequestDTO)).map(financeRequestMapper::toDto);
    }

    /**
     * Partially update a financeRequest.
     *
     * @param financeRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<FinanceRequestDTO> partialUpdate(FinanceRequestDTO financeRequestDTO) {
        log.debug("Request to partially update FinanceRequest : {}", financeRequestDTO);

        return financeRequestRepository
            .findById(financeRequestDTO.getId())
            .map(existingFinanceRequest -> {
                financeRequestMapper.partialUpdate(existingFinanceRequest, financeRequestDTO);

                return existingFinanceRequest;
            })
            .flatMap(financeRequestRepository::save)
            .map(financeRequestMapper::toDto);
    }

    /**
     * Get all the financeRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<FinanceRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinanceRequests");
        return financeRequestRepository.findAllBy(pageable).map(financeRequestMapper::toDto);
    }

    /**
     * Get all the financeRequests with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<FinanceRequestDTO> findAllWithEagerRelationships(Pageable pageable) {
        return financeRequestRepository.findAllWithEagerRelationships(pageable).map(financeRequestMapper::toDto);
    }

    /**
     * Returns the number of financeRequests available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return financeRequestRepository.count();
    }

    /**
     * Get one financeRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<FinanceRequestDTO> findOne(Long id) {
        log.debug("Request to get FinanceRequest : {}", id);
        return financeRequestRepository.findOneWithEagerRelationships(id).map(financeRequestMapper::toDto);
    }

    /**
     * Delete the financeRequest by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FinanceRequest : {}", id);
        return financeRequestRepository.deleteById(id);
    }
}
