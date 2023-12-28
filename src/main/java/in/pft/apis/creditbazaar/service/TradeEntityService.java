package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.TradeEntityRepository;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import in.pft.apis.creditbazaar.service.mapper.TradeEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.TradeEntity}.
 */
@Service
@Transactional
public class TradeEntityService {

    private final Logger log = LoggerFactory.getLogger(TradeEntityService.class);

    private final TradeEntityRepository tradeEntityRepository;

    private final TradeEntityMapper tradeEntityMapper;

    public TradeEntityService(TradeEntityRepository tradeEntityRepository, TradeEntityMapper tradeEntityMapper) {
        this.tradeEntityRepository = tradeEntityRepository;
        this.tradeEntityMapper = tradeEntityMapper;
    }

    /**
     * Save a tradeEntity.
     *
     * @param tradeEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradeEntityDTO> save(TradeEntityDTO tradeEntityDTO) {
        log.debug("Request to save TradeEntity : {}", tradeEntityDTO);
        return tradeEntityRepository.save(tradeEntityMapper.toEntity(tradeEntityDTO)).map(tradeEntityMapper::toDto);
    }

    /**
     * Update a tradeEntity.
     *
     * @param tradeEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradeEntityDTO> update(TradeEntityDTO tradeEntityDTO) {
        log.debug("Request to update TradeEntity : {}", tradeEntityDTO);
        return tradeEntityRepository.save(tradeEntityMapper.toEntity(tradeEntityDTO)).map(tradeEntityMapper::toDto);
    }

    /**
     * Partially update a tradeEntity.
     *
     * @param tradeEntityDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TradeEntityDTO> partialUpdate(TradeEntityDTO tradeEntityDTO) {
        log.debug("Request to partially update TradeEntity : {}", tradeEntityDTO);

        return tradeEntityRepository
            .findById(tradeEntityDTO.getId())
            .map(existingTradeEntity -> {
                tradeEntityMapper.partialUpdate(existingTradeEntity, tradeEntityDTO);

                return existingTradeEntity;
            })
            .flatMap(tradeEntityRepository::save)
            .map(tradeEntityMapper::toDto);
    }

    /**
     * Get all the tradeEntities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TradeEntityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TradeEntities");
        return tradeEntityRepository.findAllBy(pageable).map(tradeEntityMapper::toDto);
    }

    /**
     * Returns the number of tradeEntities available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tradeEntityRepository.count();
    }

    /**
     * Get one tradeEntity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TradeEntityDTO> findOne(Long id) {
        log.debug("Request to get TradeEntity : {}", id);
        return tradeEntityRepository.findById(id).map(tradeEntityMapper::toDto);
    }

    /**
     * Delete the tradeEntity by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TradeEntity : {}", id);
        return tradeEntityRepository.deleteById(id);
    }
}
