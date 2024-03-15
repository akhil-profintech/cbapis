package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.CREHighlightsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.CREHighlightsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CREHighlightsMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.CREHighlights}.
 */
@Service
@Transactional
public class CREHighlightsService {

    private final Logger log = LoggerFactory.getLogger(CREHighlightsService.class);

    private final CREHighlightsRepository cREHighlightsRepository;

    private final CREHighlightsMapper cREHighlightsMapper;

    public CREHighlightsService(CREHighlightsRepository cREHighlightsRepository, CREHighlightsMapper cREHighlightsMapper) {
        this.cREHighlightsRepository = cREHighlightsRepository;
        this.cREHighlightsMapper = cREHighlightsMapper;
    }

    /**
     * Save a cREHighlights.
     *
     * @param cREHighlightsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CREHighlightsDTO> save(CREHighlightsDTO cREHighlightsDTO) {
        log.debug("Request to save CREHighlights : {}", cREHighlightsDTO);
        cREHighlightsDTO.setCreHighlightsId(IdAndUlidGeneration.generateUniqueLong(9));
        cREHighlightsDTO.setCreHighlightsUlidId(IdAndUlidGeneration.generateUlid());
        return cREHighlightsRepository.save(cREHighlightsMapper.toEntity(cREHighlightsDTO)).map(cREHighlightsMapper::toDto);
    }

    /**
     * Update a cREHighlights.
     *
     * @param cREHighlightsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CREHighlightsDTO> update(CREHighlightsDTO cREHighlightsDTO) {
        log.debug("Request to update CREHighlights : {}", cREHighlightsDTO);
        return cREHighlightsRepository.save(cREHighlightsMapper.toEntity(cREHighlightsDTO)).map(cREHighlightsMapper::toDto);
    }

    /**
     * Partially update a cREHighlights.
     *
     * @param cREHighlightsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CREHighlightsDTO> partialUpdate(CREHighlightsDTO cREHighlightsDTO) {
        log.debug("Request to partially update CREHighlights : {}", cREHighlightsDTO);

        return cREHighlightsRepository
            .findById(cREHighlightsDTO.getId())
            .map(existingCREHighlights -> {
                cREHighlightsMapper.partialUpdate(existingCREHighlights, cREHighlightsDTO);

                return existingCREHighlights;
            })
            .flatMap(cREHighlightsRepository::save)
            .map(cREHighlightsMapper::toDto);
    }

    /**
     * Get all the cREHighlights.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CREHighlightsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CREHighlights");
        return cREHighlightsRepository.findAllBy(pageable).map(cREHighlightsMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<CREHighlightsDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return cREHighlightsRepository.findAllByFilter(filter, pageable).map(cREHighlightsMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return cREHighlightsRepository.countByFilter(filter);
    }

    /**
     * Get all the cREHighlights with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<CREHighlightsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return cREHighlightsRepository.findAllWithEagerRelationships(pageable).map(cREHighlightsMapper::toDto);
    }

    /**
     * Returns the number of cREHighlights available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return cREHighlightsRepository.count();
    }

    /**
     * Get one cREHighlights by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CREHighlightsDTO> findOne(Long id) {
        log.debug("Request to get CREHighlights : {}", id);
        return cREHighlightsRepository.findOneWithEagerRelationships(id).map(cREHighlightsMapper::toDto);
    }

    /**
     * Delete the cREHighlights by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CREHighlights : {}", id);
        return cREHighlightsRepository.deleteById(id);
    }
}
