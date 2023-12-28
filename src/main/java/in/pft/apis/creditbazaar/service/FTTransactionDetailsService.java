package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.FTTransactionDetailsRepository;
import in.pft.apis.creditbazaar.service.dto.FTTransactionDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.FTTransactionDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.FTTransactionDetails}.
 */
@Service
@Transactional
public class FTTransactionDetailsService {

    private final Logger log = LoggerFactory.getLogger(FTTransactionDetailsService.class);

    private final FTTransactionDetailsRepository fTTransactionDetailsRepository;

    private final FTTransactionDetailsMapper fTTransactionDetailsMapper;

    public FTTransactionDetailsService(
        FTTransactionDetailsRepository fTTransactionDetailsRepository,
        FTTransactionDetailsMapper fTTransactionDetailsMapper
    ) {
        this.fTTransactionDetailsRepository = fTTransactionDetailsRepository;
        this.fTTransactionDetailsMapper = fTTransactionDetailsMapper;
    }

    /**
     * Save a fTTransactionDetails.
     *
     * @param fTTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FTTransactionDetailsDTO> save(FTTransactionDetailsDTO fTTransactionDetailsDTO) {
        log.debug("Request to save FTTransactionDetails : {}", fTTransactionDetailsDTO);
        return fTTransactionDetailsRepository
            .save(fTTransactionDetailsMapper.toEntity(fTTransactionDetailsDTO))
            .map(fTTransactionDetailsMapper::toDto);
    }

    /**
     * Update a fTTransactionDetails.
     *
     * @param fTTransactionDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FTTransactionDetailsDTO> update(FTTransactionDetailsDTO fTTransactionDetailsDTO) {
        log.debug("Request to update FTTransactionDetails : {}", fTTransactionDetailsDTO);
        return fTTransactionDetailsRepository
            .save(fTTransactionDetailsMapper.toEntity(fTTransactionDetailsDTO))
            .map(fTTransactionDetailsMapper::toDto);
    }

    /**
     * Partially update a fTTransactionDetails.
     *
     * @param fTTransactionDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<FTTransactionDetailsDTO> partialUpdate(FTTransactionDetailsDTO fTTransactionDetailsDTO) {
        log.debug("Request to partially update FTTransactionDetails : {}", fTTransactionDetailsDTO);

        return fTTransactionDetailsRepository
            .findById(fTTransactionDetailsDTO.getId())
            .map(existingFTTransactionDetails -> {
                fTTransactionDetailsMapper.partialUpdate(existingFTTransactionDetails, fTTransactionDetailsDTO);

                return existingFTTransactionDetails;
            })
            .flatMap(fTTransactionDetailsRepository::save)
            .map(fTTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the fTTransactionDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<FTTransactionDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FTTransactionDetails");
        return fTTransactionDetailsRepository.findAllBy(pageable).map(fTTransactionDetailsMapper::toDto);
    }

    /**
     * Get all the fTTransactionDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<FTTransactionDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return fTTransactionDetailsRepository.findAllWithEagerRelationships(pageable).map(fTTransactionDetailsMapper::toDto);
    }

    /**
     * Returns the number of fTTransactionDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return fTTransactionDetailsRepository.count();
    }

    /**
     * Get one fTTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<FTTransactionDetailsDTO> findOne(Long id) {
        log.debug("Request to get FTTransactionDetails : {}", id);
        return fTTransactionDetailsRepository.findOneWithEagerRelationships(id).map(fTTransactionDetailsMapper::toDto);
    }

    /**
     * Delete the fTTransactionDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FTTransactionDetails : {}", id);
        return fTTransactionDetailsRepository.deleteById(id);
    }
}
