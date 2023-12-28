package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.ContextRepository;
import in.pft.apis.creditbazaar.service.dto.ContextDTO;
import in.pft.apis.creditbazaar.service.mapper.ContextMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.Context}.
 */
@Service
@Transactional
public class ContextService {

    private final Logger log = LoggerFactory.getLogger(ContextService.class);

    private final ContextRepository contextRepository;

    private final ContextMapper contextMapper;

    public ContextService(ContextRepository contextRepository, ContextMapper contextMapper) {
        this.contextRepository = contextRepository;
        this.contextMapper = contextMapper;
    }

    /**
     * Save a context.
     *
     * @param contextDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ContextDTO> save(ContextDTO contextDTO) {
        log.debug("Request to save Context : {}", contextDTO);
        return contextRepository.save(contextMapper.toEntity(contextDTO)).map(contextMapper::toDto);
    }

    /**
     * Update a context.
     *
     * @param contextDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ContextDTO> update(ContextDTO contextDTO) {
        log.debug("Request to update Context : {}", contextDTO);
        return contextRepository.save(contextMapper.toEntity(contextDTO)).map(contextMapper::toDto);
    }

    /**
     * Partially update a context.
     *
     * @param contextDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ContextDTO> partialUpdate(ContextDTO contextDTO) {
        log.debug("Request to partially update Context : {}", contextDTO);

        return contextRepository
            .findById(contextDTO.getId())
            .map(existingContext -> {
                contextMapper.partialUpdate(existingContext, contextDTO);

                return existingContext;
            })
            .flatMap(contextRepository::save)
            .map(contextMapper::toDto);
    }

    /**
     * Get all the contexts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ContextDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Contexts");
        return contextRepository.findAllBy(pageable).map(contextMapper::toDto);
    }

    /**
     * Get all the contexts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<ContextDTO> findAllWithEagerRelationships(Pageable pageable) {
        return contextRepository.findAllWithEagerRelationships(pageable).map(contextMapper::toDto);
    }

    /**
     * Returns the number of contexts available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return contextRepository.count();
    }

    /**
     * Get one context by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ContextDTO> findOne(Long id) {
        log.debug("Request to get Context : {}", id);
        return contextRepository.findOneWithEagerRelationships(id).map(contextMapper::toDto);
    }

    /**
     * Delete the context by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Context : {}", id);
        return contextRepository.deleteById(id);
    }
}
