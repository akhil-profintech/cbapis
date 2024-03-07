package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.EscrowTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.EscrowTransactionDetailsMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails}.
 */
@Service
@Transactional
public class EscrowTransactionDetailsService {

    private final Logger log = LoggerFactory.getLogger(EscrowTransactionDetailsService.class);

    private final EscrowTransactionDetailsRepository escrowTransactionDetailsRepository;

    private final EscrowTransactionDetailsMapper escrowTransactionDetailsMapper;

    public EscrowTransactionDetailsService(
        EscrowTransactionDetailsRepository escrowTransactionDetailsRepository,
        EscrowTransactionDetailsMapper escrowTransactionDetailsMapper
    ) {
        this.escrowTransactionDetailsRepository = escrowTransactionDetailsRepository;
        this.escrowTransactionDetailsMapper = escrowTransactionDetailsMapper;
    }

    /**
     * Save a escrowTransactionDetails.
     *
     * @param escrowTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EscrowTransactionDetailsDTO> save(EscrowTransactionDetailsDTO escrowTransactionDetailsDTO) {
        log.debug("Request to save EscrowTransactionDetails : {}", escrowTransactionDetailsDTO);
        escrowTransactionDetailsDTO.setEscrowTrnxDetailsId(IdAndUlidGeneration.generateUniqueLong(9));
        escrowTransactionDetailsDTO.setEscrowTrnxDetailsUlidId(IdAndUlidGeneration.generateUlid());
        return escrowTransactionDetailsRepository
            .save(escrowTransactionDetailsMapper.toEntity(escrowTransactionDetailsDTO))
            .map(escrowTransactionDetailsMapper::toDto);
    }

    /**
     * Update a escrowTransactionDetails.
     *
     * @param escrowTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EscrowTransactionDetailsDTO> update(EscrowTransactionDetailsDTO escrowTransactionDetailsDTO) {
        log.debug("Request to update EscrowTransactionDetails : {}", escrowTransactionDetailsDTO);
        return escrowTransactionDetailsRepository
            .save(escrowTransactionDetailsMapper.toEntity(escrowTransactionDetailsDTO))
            .map(escrowTransactionDetailsMapper::toDto);
    }

    /**
     * Partially update a escrowTransactionDetails.
     *
     * @param escrowTransactionDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EscrowTransactionDetailsDTO> partialUpdate(EscrowTransactionDetailsDTO escrowTransactionDetailsDTO) {
        log.debug("Request to partially update EscrowTransactionDetails : {}", escrowTransactionDetailsDTO);

        return escrowTransactionDetailsRepository
            .findById(escrowTransactionDetailsDTO.getId())
            .map(existingEscrowTransactionDetails -> {
                escrowTransactionDetailsMapper.partialUpdate(existingEscrowTransactionDetails, escrowTransactionDetailsDTO);

                return existingEscrowTransactionDetails;
            })
            .flatMap(escrowTransactionDetailsRepository::save)
            .map(escrowTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the escrowTransactionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EscrowTransactionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EscrowTransactionDetails");
        return escrowTransactionDetailsRepository.findAllBy(pageable).map(escrowTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the escrowTransactionDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<EscrowTransactionDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return escrowTransactionDetailsRepository.findAllWithEagerRelationships(pageable).map(escrowTransactionDetailsMapper::toDto);
    }

    /**
     * Returns the number of escrowTransactionDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return escrowTransactionDetailsRepository.count();
    }

    /**
     * Get one escrowTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EscrowTransactionDetailsDTO> findOne(Long id) {
        log.debug("Request to get EscrowTransactionDetails : {}", id);
        return escrowTransactionDetailsRepository.findOneWithEagerRelationships(id).map(escrowTransactionDetailsMapper::toDto);
    }

    /**
     * Delete the escrowTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EscrowTransactionDetails : {}", id);
        return escrowTransactionDetailsRepository.deleteById(id);
    }
}
