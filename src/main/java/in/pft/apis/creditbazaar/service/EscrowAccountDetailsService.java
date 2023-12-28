package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.EscrowAccountDetailsRepository;
import in.pft.apis.creditbazaar.service.dto.EscrowAccountDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.EscrowAccountDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.EscrowAccountDetails}.
 */
@Service
@Transactional
public class EscrowAccountDetailsService {

    private final Logger log = LoggerFactory.getLogger(EscrowAccountDetailsService.class);

    private final EscrowAccountDetailsRepository escrowAccountDetailsRepository;

    private final EscrowAccountDetailsMapper escrowAccountDetailsMapper;

    public EscrowAccountDetailsService(
        EscrowAccountDetailsRepository escrowAccountDetailsRepository,
        EscrowAccountDetailsMapper escrowAccountDetailsMapper
    ) {
        this.escrowAccountDetailsRepository = escrowAccountDetailsRepository;
        this.escrowAccountDetailsMapper = escrowAccountDetailsMapper;
    }

    /**
     * Save a escrowAccountDetails.
     *
     * @param escrowAccountDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EscrowAccountDetailsDTO> save(EscrowAccountDetailsDTO escrowAccountDetailsDTO) {
        log.debug("Request to save EscrowAccountDetails : {}", escrowAccountDetailsDTO);
        return escrowAccountDetailsRepository
            .save(escrowAccountDetailsMapper.toEntity(escrowAccountDetailsDTO))
            .map(escrowAccountDetailsMapper::toDto);
    }

    /**
     * Update a escrowAccountDetails.
     *
     * @param escrowAccountDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<EscrowAccountDetailsDTO> update(EscrowAccountDetailsDTO escrowAccountDetailsDTO) {
        log.debug("Request to update EscrowAccountDetails : {}", escrowAccountDetailsDTO);
        return escrowAccountDetailsRepository
            .save(escrowAccountDetailsMapper.toEntity(escrowAccountDetailsDTO))
            .map(escrowAccountDetailsMapper::toDto);
    }

    /**
     * Partially update a escrowAccountDetails.
     *
     * @param escrowAccountDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<EscrowAccountDetailsDTO> partialUpdate(EscrowAccountDetailsDTO escrowAccountDetailsDTO) {
        log.debug("Request to partially update EscrowAccountDetails : {}", escrowAccountDetailsDTO);

        return escrowAccountDetailsRepository
            .findById(escrowAccountDetailsDTO.getId())
            .map(existingEscrowAccountDetails -> {
                escrowAccountDetailsMapper.partialUpdate(existingEscrowAccountDetails, escrowAccountDetailsDTO);

                return existingEscrowAccountDetails;
            })
            .flatMap(escrowAccountDetailsRepository::save)
            .map(escrowAccountDetailsMapper::toDto);
    }

    /**
     * Get all the escrowAccountDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<EscrowAccountDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EscrowAccountDetails");
        return escrowAccountDetailsRepository.findAllBy(pageable).map(escrowAccountDetailsMapper::toDto);
    }

    /**
     * Get all the escrowAccountDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<EscrowAccountDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return escrowAccountDetailsRepository.findAllWithEagerRelationships(pageable).map(escrowAccountDetailsMapper::toDto);
    }

    /**
     * Returns the number of escrowAccountDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return escrowAccountDetailsRepository.count();
    }

    /**
     * Get one escrowAccountDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<EscrowAccountDetailsDTO> findOne(Long id) {
        log.debug("Request to get EscrowAccountDetails : {}", id);
        return escrowAccountDetailsRepository.findOneWithEagerRelationships(id).map(escrowAccountDetailsMapper::toDto);
    }

    /**
     * Delete the escrowAccountDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete EscrowAccountDetails : {}", id);
        return escrowAccountDetailsRepository.deleteById(id);
    }
}
