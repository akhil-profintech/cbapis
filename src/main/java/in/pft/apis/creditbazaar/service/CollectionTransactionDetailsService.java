package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.CollectionTransactionDetailsRepository;
import in.pft.apis.creditbazaar.service.dto.CollectionTransactionDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.CollectionTransactionDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.CollectionTransactionDetails}.
 */
@Service
@Transactional
public class CollectionTransactionDetailsService {

    private final Logger log = LoggerFactory.getLogger(CollectionTransactionDetailsService.class);

    private final CollectionTransactionDetailsRepository collectionTransactionDetailsRepository;

    private final CollectionTransactionDetailsMapper collectionTransactionDetailsMapper;

    public CollectionTransactionDetailsService(
        CollectionTransactionDetailsRepository collectionTransactionDetailsRepository,
        CollectionTransactionDetailsMapper collectionTransactionDetailsMapper
    ) {
        this.collectionTransactionDetailsRepository = collectionTransactionDetailsRepository;
        this.collectionTransactionDetailsMapper = collectionTransactionDetailsMapper;
    }

    /**
     * Save a collectionTransactionDetails.
     *
     * @param collectionTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CollectionTransactionDetailsDTO> save(CollectionTransactionDetailsDTO collectionTransactionDetailsDTO) {
        log.debug("Request to save CollectionTransactionDetails : {}", collectionTransactionDetailsDTO);
        return collectionTransactionDetailsRepository
            .save(collectionTransactionDetailsMapper.toEntity(collectionTransactionDetailsDTO))
            .map(collectionTransactionDetailsMapper::toDto);
    }

    /**
     * Update a collectionTransactionDetails.
     *
     * @param collectionTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CollectionTransactionDetailsDTO> update(CollectionTransactionDetailsDTO collectionTransactionDetailsDTO) {
        log.debug("Request to update CollectionTransactionDetails : {}", collectionTransactionDetailsDTO);
        return collectionTransactionDetailsRepository
            .save(collectionTransactionDetailsMapper.toEntity(collectionTransactionDetailsDTO))
            .map(collectionTransactionDetailsMapper::toDto);
    }

    /**
     * Partially update a collectionTransactionDetails.
     *
     * @param collectionTransactionDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CollectionTransactionDetailsDTO> partialUpdate(CollectionTransactionDetailsDTO collectionTransactionDetailsDTO) {
        log.debug("Request to partially update CollectionTransactionDetails : {}", collectionTransactionDetailsDTO);

        return collectionTransactionDetailsRepository
            .findById(collectionTransactionDetailsDTO.getId())
            .map(existingCollectionTransactionDetails -> {
                collectionTransactionDetailsMapper.partialUpdate(existingCollectionTransactionDetails, collectionTransactionDetailsDTO);

                return existingCollectionTransactionDetails;
            })
            .flatMap(collectionTransactionDetailsRepository::save)
            .map(collectionTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the collectionTransactionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CollectionTransactionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CollectionTransactionDetails");
        return collectionTransactionDetailsRepository.findAllBy(pageable).map(collectionTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the collectionTransactionDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<CollectionTransactionDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return collectionTransactionDetailsRepository
            .findAllWithEagerRelationships(pageable)
            .map(collectionTransactionDetailsMapper::toDto);
    }

    /**
     * Returns the number of collectionTransactionDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return collectionTransactionDetailsRepository.count();
    }

    /**
     * Get one collectionTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CollectionTransactionDetailsDTO> findOne(Long id) {
        log.debug("Request to get CollectionTransactionDetails : {}", id);
        return collectionTransactionDetailsRepository.findOneWithEagerRelationships(id).map(collectionTransactionDetailsMapper::toDto);
    }

    /**
     * Delete the collectionTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CollectionTransactionDetails : {}", id);
        return collectionTransactionDetailsRepository.deleteById(id);
    }
}
