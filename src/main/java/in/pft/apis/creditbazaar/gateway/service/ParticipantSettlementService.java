package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.ParticipantSettlementRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.ParticipantSettlementMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement}.
 */
@Service
@Transactional
public class ParticipantSettlementService {

    private final Logger log = LoggerFactory.getLogger(ParticipantSettlementService.class);

    private final ParticipantSettlementRepository participantSettlementRepository;

    private final ParticipantSettlementMapper participantSettlementMapper;

    public ParticipantSettlementService(
        ParticipantSettlementRepository participantSettlementRepository,
        ParticipantSettlementMapper participantSettlementMapper
    ) {
        this.participantSettlementRepository = participantSettlementRepository;
        this.participantSettlementMapper = participantSettlementMapper;
    }

    /**
     * Save a participantSettlement.
     *
     * @param participantSettlementDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ParticipantSettlementDTO> save(ParticipantSettlementDTO participantSettlementDTO) {
        log.debug("Request to save ParticipantSettlement : {}", participantSettlementDTO);
        participantSettlementDTO.setParticipantSettlementId(IdAndUlidGeneration.generateUniqueLong(9));
        participantSettlementDTO.setParticipantSettlementUlidId(IdAndUlidGeneration.generateUlid());
        return participantSettlementRepository
            .save(participantSettlementMapper.toEntity(participantSettlementDTO))
            .map(participantSettlementMapper::toDto);
    }

    /**
     * Update a participantSettlement.
     *
     * @param participantSettlementDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ParticipantSettlementDTO> update(ParticipantSettlementDTO participantSettlementDTO) {
        log.debug("Request to update ParticipantSettlement : {}", participantSettlementDTO);
        return participantSettlementRepository
            .save(participantSettlementMapper.toEntity(participantSettlementDTO))
            .map(participantSettlementMapper::toDto);
    }

    /**
     * Partially update a participantSettlement.
     *
     * @param participantSettlementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ParticipantSettlementDTO> partialUpdate(ParticipantSettlementDTO participantSettlementDTO) {
        log.debug("Request to partially update ParticipantSettlement : {}", participantSettlementDTO);

        return participantSettlementRepository
            .findById(participantSettlementDTO.getId())
            .map(existingParticipantSettlement -> {
                participantSettlementMapper.partialUpdate(existingParticipantSettlement, participantSettlementDTO);

                return existingParticipantSettlement;
            })
            .flatMap(participantSettlementRepository::save)
            .map(participantSettlementMapper::toDto);
    }

    /**
     * Get all the participantSettlements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ParticipantSettlementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ParticipantSettlements");
        return participantSettlementRepository.findAllBy(pageable).map(participantSettlementMapper::toDto);
    }

    /**
     * Get all the participantSettlements with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<ParticipantSettlementDTO> findAllWithEagerRelationships(Pageable pageable) {
        return participantSettlementRepository.findAllWithEagerRelationships(pageable).map(participantSettlementMapper::toDto);
    }

    /**
     * Returns the number of participantSettlements available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return participantSettlementRepository.count();
    }

    /**
     * Get one participantSettlement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ParticipantSettlementDTO> findOne(Long id) {
        log.debug("Request to get ParticipantSettlement : {}", id);
        return participantSettlementRepository.findOneWithEagerRelationships(id).map(participantSettlementMapper::toDto);
    }

    /**
     * Delete the participantSettlement by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ParticipantSettlement : {}", id);
        return participantSettlementRepository.deleteById(id);
    }
}
