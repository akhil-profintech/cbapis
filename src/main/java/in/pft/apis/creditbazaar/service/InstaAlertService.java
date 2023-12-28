package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.InstaAlertRepository;
import in.pft.apis.creditbazaar.service.dto.InstaAlertDTO;
import in.pft.apis.creditbazaar.service.mapper.InstaAlertMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.InstaAlert}.
 */
@Service
@Transactional
public class InstaAlertService {

    private final Logger log = LoggerFactory.getLogger(InstaAlertService.class);

    private final InstaAlertRepository instaAlertRepository;

    private final InstaAlertMapper instaAlertMapper;

    public InstaAlertService(InstaAlertRepository instaAlertRepository, InstaAlertMapper instaAlertMapper) {
        this.instaAlertRepository = instaAlertRepository;
        this.instaAlertMapper = instaAlertMapper;
    }

    /**
     * Save a instaAlert.
     *
     * @param instaAlertDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<InstaAlertDTO> save(InstaAlertDTO instaAlertDTO) {
        log.debug("Request to save InstaAlert : {}", instaAlertDTO);
        return instaAlertRepository.save(instaAlertMapper.toEntity(instaAlertDTO)).map(instaAlertMapper::toDto);
    }

    /**
     * Update a instaAlert.
     *
     * @param instaAlertDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<InstaAlertDTO> update(InstaAlertDTO instaAlertDTO) {
        log.debug("Request to update InstaAlert : {}", instaAlertDTO);
        return instaAlertRepository.save(instaAlertMapper.toEntity(instaAlertDTO)).map(instaAlertMapper::toDto);
    }

    /**
     * Partially update a instaAlert.
     *
     * @param instaAlertDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<InstaAlertDTO> partialUpdate(InstaAlertDTO instaAlertDTO) {
        log.debug("Request to partially update InstaAlert : {}", instaAlertDTO);

        return instaAlertRepository
            .findById(instaAlertDTO.getId())
            .map(existingInstaAlert -> {
                instaAlertMapper.partialUpdate(existingInstaAlert, instaAlertDTO);

                return existingInstaAlert;
            })
            .flatMap(instaAlertRepository::save)
            .map(instaAlertMapper::toDto);
    }

    /**
     * Get all the instaAlerts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<InstaAlertDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InstaAlerts");
        return instaAlertRepository.findAllBy(pageable).map(instaAlertMapper::toDto);
    }

    /**
     * Get all the instaAlerts with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<InstaAlertDTO> findAllWithEagerRelationships(Pageable pageable) {
        return instaAlertRepository.findAllWithEagerRelationships(pageable).map(instaAlertMapper::toDto);
    }

    /**
     * Returns the number of instaAlerts available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return instaAlertRepository.count();
    }

    /**
     * Get one instaAlert by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<InstaAlertDTO> findOne(Long id) {
        log.debug("Request to get InstaAlert : {}", id);
        return instaAlertRepository.findOneWithEagerRelationships(id).map(instaAlertMapper::toDto);
    }

    /**
     * Delete the instaAlert by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete InstaAlert : {}", id);
        return instaAlertRepository.deleteById(id);
    }
}
