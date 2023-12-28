package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.BeneValidationRepository;
import in.pft.apis.creditbazaar.service.dto.BeneValidationDTO;
import in.pft.apis.creditbazaar.service.mapper.BeneValidationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.BeneValidation}.
 */
@Service
@Transactional
public class BeneValidationService {

    private final Logger log = LoggerFactory.getLogger(BeneValidationService.class);

    private final BeneValidationRepository beneValidationRepository;

    private final BeneValidationMapper beneValidationMapper;

    public BeneValidationService(BeneValidationRepository beneValidationRepository, BeneValidationMapper beneValidationMapper) {
        this.beneValidationRepository = beneValidationRepository;
        this.beneValidationMapper = beneValidationMapper;
    }

    /**
     * Save a beneValidation.
     *
     * @param beneValidationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<BeneValidationDTO> save(BeneValidationDTO beneValidationDTO) {
        log.debug("Request to save BeneValidation : {}", beneValidationDTO);
        return beneValidationRepository.save(beneValidationMapper.toEntity(beneValidationDTO)).map(beneValidationMapper::toDto);
    }

    /**
     * Update a beneValidation.
     *
     * @param beneValidationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<BeneValidationDTO> update(BeneValidationDTO beneValidationDTO) {
        log.debug("Request to update BeneValidation : {}", beneValidationDTO);
        return beneValidationRepository.save(beneValidationMapper.toEntity(beneValidationDTO)).map(beneValidationMapper::toDto);
    }

    /**
     * Partially update a beneValidation.
     *
     * @param beneValidationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<BeneValidationDTO> partialUpdate(BeneValidationDTO beneValidationDTO) {
        log.debug("Request to partially update BeneValidation : {}", beneValidationDTO);

        return beneValidationRepository
            .findById(beneValidationDTO.getId())
            .map(existingBeneValidation -> {
                beneValidationMapper.partialUpdate(existingBeneValidation, beneValidationDTO);

                return existingBeneValidation;
            })
            .flatMap(beneValidationRepository::save)
            .map(beneValidationMapper::toDto);
    }

    /**
     * Get all the beneValidations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<BeneValidationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BeneValidations");
        return beneValidationRepository.findAllBy(pageable).map(beneValidationMapper::toDto);
    }

    /**
     * Get all the beneValidations with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<BeneValidationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return beneValidationRepository.findAllWithEagerRelationships(pageable).map(beneValidationMapper::toDto);
    }

    /**
     * Returns the number of beneValidations available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return beneValidationRepository.count();
    }

    /**
     * Get one beneValidation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<BeneValidationDTO> findOne(Long id) {
        log.debug("Request to get BeneValidation : {}", id);
        return beneValidationRepository.findOneWithEagerRelationships(id).map(beneValidationMapper::toDto);
    }

    /**
     * Delete the beneValidation by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete BeneValidation : {}", id);
        return beneValidationRepository.deleteById(id);
    }
}
