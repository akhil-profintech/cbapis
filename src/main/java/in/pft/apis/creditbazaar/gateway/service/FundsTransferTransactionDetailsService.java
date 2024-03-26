package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.FundsTransferTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FundsTransferTransactionDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetails}.
 */
@Service
@Transactional
public class FundsTransferTransactionDetailsService {

    private final Logger log = LoggerFactory.getLogger(FundsTransferTransactionDetailsService.class);

    private final FundsTransferTransactionDetailsRepository fundsTransferTransactionDetailsRepository;

    private final FundsTransferTransactionDetailsMapper fundsTransferTransactionDetailsMapper;

    public FundsTransferTransactionDetailsService(
        FundsTransferTransactionDetailsRepository fundsTransferTransactionDetailsRepository,
        FundsTransferTransactionDetailsMapper fundsTransferTransactionDetailsMapper
    ) {
        this.fundsTransferTransactionDetailsRepository = fundsTransferTransactionDetailsRepository;
        this.fundsTransferTransactionDetailsMapper = fundsTransferTransactionDetailsMapper;
    }

    /**
     * Save a fundsTransferTransactionDetails.
     *
     * @param fundsTransferTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FundsTransferTransactionDetailsDTO> save(FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO) {
        log.debug("Request to save FundsTransferTransactionDetails : {}", fundsTransferTransactionDetailsDTO);
        return fundsTransferTransactionDetailsRepository
            .save(fundsTransferTransactionDetailsMapper.toEntity(fundsTransferTransactionDetailsDTO))
            .map(fundsTransferTransactionDetailsMapper::toDto);
    }

    /**
     * Update a fundsTransferTransactionDetails.
     *
     * @param fundsTransferTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FundsTransferTransactionDetailsDTO> update(FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO) {
        log.debug("Request to update FundsTransferTransactionDetails : {}", fundsTransferTransactionDetailsDTO);
        return fundsTransferTransactionDetailsRepository
            .save(fundsTransferTransactionDetailsMapper.toEntity(fundsTransferTransactionDetailsDTO))
            .map(fundsTransferTransactionDetailsMapper::toDto);
    }

    /**
     * Partially update a fundsTransferTransactionDetails.
     *
     * @param fundsTransferTransactionDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<FundsTransferTransactionDetailsDTO> partialUpdate(FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO) {
        log.debug("Request to partially update FundsTransferTransactionDetails : {}", fundsTransferTransactionDetailsDTO);

        return fundsTransferTransactionDetailsRepository
            .findById(fundsTransferTransactionDetailsDTO.getId())
            .map(existingFundsTransferTransactionDetails -> {
                fundsTransferTransactionDetailsMapper.partialUpdate(
                    existingFundsTransferTransactionDetails,
                    fundsTransferTransactionDetailsDTO
                );

                return existingFundsTransferTransactionDetails;
            })
            .flatMap(fundsTransferTransactionDetailsRepository::save)
            .map(fundsTransferTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the fundsTransferTransactionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<FundsTransferTransactionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FundsTransferTransactionDetails");
        return fundsTransferTransactionDetailsRepository.findAllBy(pageable).map(fundsTransferTransactionDetailsMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<FundsTransferTransactionDetailsDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return fundsTransferTransactionDetailsRepository.findAllByFilter(filter, pageable).map(fundsTransferTransactionDetailsMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return fundsTransferTransactionDetailsRepository.countByFilter(filter);
    }


    /**
     * Get all the fundsTransferTransactionDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<FundsTransferTransactionDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return fundsTransferTransactionDetailsRepository
            .findAllWithEagerRelationships(pageable)
            .map(fundsTransferTransactionDetailsMapper::toDto);
    }

    /**
     * Returns the number of fundsTransferTransactionDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return fundsTransferTransactionDetailsRepository.count();
    }

    /**
     * Get one fundsTransferTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<FundsTransferTransactionDetailsDTO> findOne(Long id) {
        log.debug("Request to get FundsTransferTransactionDetails : {}", id);
        return fundsTransferTransactionDetailsRepository
            .findOneWithEagerRelationships(id)
            .map(fundsTransferTransactionDetailsMapper::toDto);
    }

    /**
     * Delete the fundsTransferTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FundsTransferTransactionDetails : {}", id);
        return fundsTransferTransactionDetailsRepository.deleteById(id);
    }
}
