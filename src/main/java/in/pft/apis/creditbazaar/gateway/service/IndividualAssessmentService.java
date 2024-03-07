package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.IndividualAssessmentRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.IndividualAssessmentDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.IndividualAssessmentMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment}.
 */
@Service
@Transactional
public class IndividualAssessmentService {

    private final Logger log = LoggerFactory.getLogger(IndividualAssessmentService.class);

    private final IndividualAssessmentRepository individualAssessmentRepository;

    private final IndividualAssessmentMapper individualAssessmentMapper;

    public IndividualAssessmentService(
        IndividualAssessmentRepository individualAssessmentRepository,
        IndividualAssessmentMapper individualAssessmentMapper
    ) {
        this.individualAssessmentRepository = individualAssessmentRepository;
        this.individualAssessmentMapper = individualAssessmentMapper;
    }

    /**
     * Save a individualAssessment.
     *
     * @param individualAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<IndividualAssessmentDTO> save(IndividualAssessmentDTO individualAssessmentDTO) {
        log.debug("Request to save IndividualAssessment : {}", individualAssessmentDTO);
        individualAssessmentDTO.setAssessmentId(IdAndUlidGeneration.generateUniqueLong(9));
        individualAssessmentDTO.setAssessmentUlidId(IdAndUlidGeneration.generateUlid());
        return individualAssessmentRepository
            .save(individualAssessmentMapper.toEntity(individualAssessmentDTO))
            .map(individualAssessmentMapper::toDto);
    }

    /**
     * Update a individualAssessment.
     *
     * @param individualAssessmentDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<IndividualAssessmentDTO> update(IndividualAssessmentDTO individualAssessmentDTO) {
        log.debug("Request to update IndividualAssessment : {}", individualAssessmentDTO);
        return individualAssessmentRepository
            .save(individualAssessmentMapper.toEntity(individualAssessmentDTO))
            .map(individualAssessmentMapper::toDto);
    }

    /**
     * Partially update a individualAssessment.
     *
     * @param individualAssessmentDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<IndividualAssessmentDTO> partialUpdate(IndividualAssessmentDTO individualAssessmentDTO) {
        log.debug("Request to partially update IndividualAssessment : {}", individualAssessmentDTO);

        return individualAssessmentRepository
            .findById(individualAssessmentDTO.getId())
            .map(existingIndividualAssessment -> {
                individualAssessmentMapper.partialUpdate(existingIndividualAssessment, individualAssessmentDTO);

                return existingIndividualAssessment;
            })
            .flatMap(individualAssessmentRepository::save)
            .map(individualAssessmentMapper::toDto);
    }

    /**
     * Get all the individualAssessments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<IndividualAssessmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all IndividualAssessments");
        return individualAssessmentRepository.findAllBy(pageable).map(individualAssessmentMapper::toDto);
    }

    /**
     * Get all the individualAssessments with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<IndividualAssessmentDTO> findAllWithEagerRelationships(Pageable pageable) {
        return individualAssessmentRepository.findAllWithEagerRelationships(pageable).map(individualAssessmentMapper::toDto);
    }

    /**
     * Returns the number of individualAssessments available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return individualAssessmentRepository.count();
    }

    /**
     * Get one individualAssessment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<IndividualAssessmentDTO> findOne(Long id) {
        log.debug("Request to get IndividualAssessment : {}", id);
        return individualAssessmentRepository.findOneWithEagerRelationships(id).map(individualAssessmentMapper::toDto);
    }

    /**
     * Delete the individualAssessment by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete IndividualAssessment : {}", id);
        return individualAssessmentRepository.deleteById(id);
    }
}
