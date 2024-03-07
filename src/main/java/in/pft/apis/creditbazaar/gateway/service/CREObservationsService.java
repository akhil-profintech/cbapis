package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.CREObservationsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.CREObservationsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CREObservationsMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.CREObservations}.
 */
@Service
@Transactional
public class CREObservationsService {

    private final Logger log = LoggerFactory.getLogger(CREObservationsService.class);

    private final CREObservationsRepository cREObservationsRepository;

    private final CREObservationsMapper cREObservationsMapper;

    public CREObservationsService(CREObservationsRepository cREObservationsRepository, CREObservationsMapper cREObservationsMapper) {
        this.cREObservationsRepository = cREObservationsRepository;
        this.cREObservationsMapper = cREObservationsMapper;
    }

    /**
     * Save a cREObservations.
     *
     * @param cREObservationsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CREObservationsDTO> save(CREObservationsDTO cREObservationsDTO) {
        log.debug("Request to save CREObservations : {}", cREObservationsDTO);
        cREObservationsDTO.setCreObservationsId(IdAndUlidGeneration.generateUniqueLong(9));
        cREObservationsDTO.setCreObservationsUlidId(IdAndUlidGeneration.generateUlid());
        return cREObservationsRepository.save(cREObservationsMapper.toEntity(cREObservationsDTO)).map(cREObservationsMapper::toDto);
    }

    /**
     * Update a cREObservations.
     *
     * @param cREObservationsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CREObservationsDTO> update(CREObservationsDTO cREObservationsDTO) {
        log.debug("Request to update CREObservations : {}", cREObservationsDTO);
        return cREObservationsRepository.save(cREObservationsMapper.toEntity(cREObservationsDTO)).map(cREObservationsMapper::toDto);
    }

    /**
     * Partially update a cREObservations.
     *
     * @param cREObservationsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CREObservationsDTO> partialUpdate(CREObservationsDTO cREObservationsDTO) {
        log.debug("Request to partially update CREObservations : {}", cREObservationsDTO);

        return cREObservationsRepository
            .findById(cREObservationsDTO.getId())
            .map(existingCREObservations -> {
                cREObservationsMapper.partialUpdate(existingCREObservations, cREObservationsDTO);

                return existingCREObservations;
            })
            .flatMap(cREObservationsRepository::save)
            .map(cREObservationsMapper::toDto);
    }

    /**
     * Get all the cREObservations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CREObservationsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CREObservations");
        return cREObservationsRepository.findAllBy(pageable).map(cREObservationsMapper::toDto);
    }

    /**
     * Get all the cREObservations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<CREObservationsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cREObservationsRepository.findAllWithEagerRelationships(pageable).map(cREObservationsMapper::toDto);
    }

    /**
     * Returns the number of cREObservations available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return cREObservationsRepository.count();
    }

    /**
     * Get one cREObservations by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CREObservationsDTO> findOne(Long id) {
        log.debug("Request to get CREObservations : {}", id);
        return cREObservationsRepository.findOneWithEagerRelationships(id).map(cREObservationsMapper::toDto);
    }

    /**
     * Delete the cREObservations by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CREObservations : {}", id);
        return cREObservationsRepository.deleteById(id);
    }
}
