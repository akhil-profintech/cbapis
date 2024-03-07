package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.TradeRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.TradeMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.Trade}.
 */
@Service
@Transactional
public class TradeService {

    private final Logger log = LoggerFactory.getLogger(TradeService.class);

    private final TradeRepository tradeRepository;

    private final TradeMapper tradeMapper;

    public TradeService(TradeRepository tradeRepository, TradeMapper tradeMapper) {
        this.tradeRepository = tradeRepository;
        this.tradeMapper = tradeMapper;
    }

    /**
     * Save a trade.
     *
     * @param tradeDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradeDTO> save(TradeDTO tradeDTO) {
        log.debug("Request to save Trade : {}", tradeDTO);
        tradeDTO.setTradeUlidId(IdAndUlidGeneration.generateUlid());
        return tradeRepository.save(tradeMapper.toEntity(tradeDTO)).map(tradeMapper::toDto);
    }

    /**
     * Update a trade.
     *
     * @param tradeDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradeDTO> update(TradeDTO tradeDTO) {
        log.debug("Request to update Trade : {}", tradeDTO);
        return tradeRepository.save(tradeMapper.toEntity(tradeDTO)).map(tradeMapper::toDto);
    }

    /**
     * Partially update a trade.
     *
     * @param tradeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TradeDTO> partialUpdate(TradeDTO tradeDTO) {
        log.debug("Request to partially update Trade : {}", tradeDTO);

        return tradeRepository
            .findById(tradeDTO.getId())
            .map(existingTrade -> {
                tradeMapper.partialUpdate(existingTrade, tradeDTO);

                return existingTrade;
            })
            .flatMap(tradeRepository::save)
            .map(tradeMapper::toDto);
    }

    /**
     * Get all the trades.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Trades");
        return tradeRepository.findAllBy(pageable).map(tradeMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<TradeDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return tradeRepository.findAllByFilter(filter, pageable).map(tradeMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return tradeRepository.countByFilter(filter);
    }


    /**
     * Get all the trades with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<TradeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return tradeRepository.findAllWithEagerRelationships(pageable).map(tradeMapper::toDto);
    }

    /**
     * Returns the number of trades available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tradeRepository.count();
    }

    /**
     * Get one trade by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TradeDTO> findOne(Long id) {
        log.debug("Request to get Trade : {}", id);
        return tradeRepository.findOneWithEagerRelationships(id).map(tradeMapper::toDto);
    }

    /**
     * Delete the trade by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Trade : {}", id);
        return tradeRepository.deleteById(id);
    }
}
