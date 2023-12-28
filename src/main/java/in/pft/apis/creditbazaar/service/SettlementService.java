package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.SettlementRepository;
import in.pft.apis.creditbazaar.service.dto.SettlementDTO;
import in.pft.apis.creditbazaar.service.mapper.SettlementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.Settlement}.
 */
@Service
@Transactional
public class SettlementService {

    private final Logger log = LoggerFactory.getLogger(SettlementService.class);

    private final SettlementRepository settlementRepository;

    private final SettlementMapper settlementMapper;

    public SettlementService(SettlementRepository settlementRepository, SettlementMapper settlementMapper) {
        this.settlementRepository = settlementRepository;
        this.settlementMapper = settlementMapper;
    }

    /**
     * Save a settlement.
     *
     * @param settlementDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<SettlementDTO> save(SettlementDTO settlementDTO) {
        log.debug("Request to save Settlement : {}", settlementDTO);
        return settlementRepository.save(settlementMapper.toEntity(settlementDTO)).map(settlementMapper::toDto);
    }

    /**
     * Update a settlement.
     *
     * @param settlementDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<SettlementDTO> update(SettlementDTO settlementDTO) {
        log.debug("Request to update Settlement : {}", settlementDTO);
        return settlementRepository.save(settlementMapper.toEntity(settlementDTO)).map(settlementMapper::toDto);
    }

    /**
     * Partially update a settlement.
     *
     * @param settlementDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<SettlementDTO> partialUpdate(SettlementDTO settlementDTO) {
        log.debug("Request to partially update Settlement : {}", settlementDTO);

        return settlementRepository
            .findById(settlementDTO.getId())
            .map(existingSettlement -> {
                settlementMapper.partialUpdate(existingSettlement, settlementDTO);

                return existingSettlement;
            })
            .flatMap(settlementRepository::save)
            .map(settlementMapper::toDto);
    }

    /**
     * Get all the settlements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<SettlementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Settlements");
        return settlementRepository.findAllBy(pageable).map(settlementMapper::toDto);
    }

    /**
     * Get all the settlements with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<SettlementDTO> findAllWithEagerRelationships(Pageable pageable) {
        return settlementRepository.findAllWithEagerRelationships(pageable).map(settlementMapper::toDto);
    }

    /**
     * Returns the number of settlements available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return settlementRepository.count();
    }

    /**
     * Get one settlement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<SettlementDTO> findOne(Long id) {
        log.debug("Request to get Settlement : {}", id);
        return settlementRepository.findOneWithEagerRelationships(id).map(settlementMapper::toDto);
    }

    /**
     * Delete the settlement by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Settlement : {}", id);
        return settlementRepository.deleteById(id);
    }
}
