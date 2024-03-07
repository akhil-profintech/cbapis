package in.pft.apis.creditbazaar.gateway.service;

import de.huxhorn.sulky.ulid.ULID;
import in.pft.apis.creditbazaar.gateway.repository.ActionRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.ActionDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.ActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration.generateUlid;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.Action}.
 */
@Service
@Transactional
public class ActionService {

    private final Logger log = LoggerFactory.getLogger(ActionService.class);

    private final ActionRepository actionRepository;

    private final ActionMapper actionMapper;

    public ActionService(ActionRepository actionRepository, ActionMapper actionMapper) {
        this.actionRepository = actionRepository;
        this.actionMapper = actionMapper;
    }

    /**
     * Save a action.
     *
     * @param actionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ActionDTO> save(ActionDTO actionDTO) {
        log.debug("Request to save Action : {}", actionDTO);
        actionDTO.setActionId(generateUlid());
        return actionRepository.save(actionMapper.toEntity(actionDTO)).map(actionMapper::toDto);
    }

    /**
     * Update a action.
     *
     * @param actionDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ActionDTO> update(ActionDTO actionDTO) {
        log.debug("Request to update Action : {}", actionDTO);
        return actionRepository.save(actionMapper.toEntity(actionDTO)).map(actionMapper::toDto);
    }

    /**
     * Partially update a action.
     *
     * @param actionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ActionDTO> partialUpdate(ActionDTO actionDTO) {
        log.debug("Request to partially update Action : {}", actionDTO);

        return actionRepository
            .findById(actionDTO.getId())
            .map(existingAction -> {
                actionMapper.partialUpdate(existingAction, actionDTO);

                return existingAction;
            })
            .flatMap(actionRepository::save)
            .map(actionMapper::toDto);
    }

    /**
     * Get all the actions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ActionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Actions");
        return actionRepository.findAllBy(pageable).map(actionMapper::toDto);
    }

    /**
     * Returns the number of actions available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return actionRepository.count();
    }

    /**
     * Get one action by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ActionDTO> findOne(Long id) {
        log.debug("Request to get Action : {}", id);
        return actionRepository.findById(id).map(actionMapper::toDto);
    }

    /**
     * Delete the action by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Action : {}", id);
        return actionRepository.deleteById(id);
    }
}
