package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.UpdateVARepository;
import in.pft.apis.creditbazaar.gateway.service.dto.UpdateVADTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.UpdateVAMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.UpdateVA}.
 */
@Service
@Transactional
public class UpdateVAService {

    private final Logger log = LoggerFactory.getLogger(UpdateVAService.class);

    private final UpdateVARepository updateVARepository;

    private final UpdateVAMapper updateVAMapper;

    public UpdateVAService(UpdateVARepository updateVARepository, UpdateVAMapper updateVAMapper) {
        this.updateVARepository = updateVARepository;
        this.updateVAMapper = updateVAMapper;
    }

    /**
     * Save a updateVA.
     *
     * @param updateVADTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UpdateVADTO> save(UpdateVADTO updateVADTO) {
        log.debug("Request to save UpdateVA : {}", updateVADTO);
        return updateVARepository.save(updateVAMapper.toEntity(updateVADTO)).map(updateVAMapper::toDto);
    }

    /**
     * Update a updateVA.
     *
     * @param updateVADTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UpdateVADTO> update(UpdateVADTO updateVADTO) {
        log.debug("Request to update UpdateVA : {}", updateVADTO);
        return updateVARepository.save(updateVAMapper.toEntity(updateVADTO)).map(updateVAMapper::toDto);
    }

    /**
     * Partially update a updateVA.
     *
     * @param updateVADTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<UpdateVADTO> partialUpdate(UpdateVADTO updateVADTO) {
        log.debug("Request to partially update UpdateVA : {}", updateVADTO);

        return updateVARepository
            .findById(updateVADTO.getId())
            .map(existingUpdateVA -> {
                updateVAMapper.partialUpdate(existingUpdateVA, updateVADTO);

                return existingUpdateVA;
            })
            .flatMap(updateVARepository::save)
            .map(updateVAMapper::toDto);
    }

    /**
     * Get all the updateVAS.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<UpdateVADTO> findAll(Pageable pageable) {
        log.debug("Request to get all UpdateVAS");
        return updateVARepository.findAllBy(pageable).map(updateVAMapper::toDto);
    }

    /**
     * Get all the updateVAS with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<UpdateVADTO> findAllWithEagerRelationships(Pageable pageable) {
        return updateVARepository.findAllWithEagerRelationships(pageable).map(updateVAMapper::toDto);
    }

    /**
     * Returns the number of updateVAS available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return updateVARepository.count();
    }

    /**
     * Get one updateVA by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<UpdateVADTO> findOne(Long id) {
        log.debug("Request to get UpdateVA : {}", id);
        return updateVARepository.findOneWithEagerRelationships(id).map(updateVAMapper::toDto);
    }

    /**
     * Delete the updateVA by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete UpdateVA : {}", id);
        return updateVARepository.deleteById(id);
    }
}
