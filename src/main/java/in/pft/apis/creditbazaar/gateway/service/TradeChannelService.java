package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.TradeChannelRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeChannelDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.TradeChannelMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.TradeChannel}.
 */
@Service
@Transactional
public class TradeChannelService {

    private final Logger log = LoggerFactory.getLogger(TradeChannelService.class);

    private final TradeChannelRepository tradeChannelRepository;

    private final TradeChannelMapper tradeChannelMapper;

    public TradeChannelService(TradeChannelRepository tradeChannelRepository, TradeChannelMapper tradeChannelMapper) {
        this.tradeChannelRepository = tradeChannelRepository;
        this.tradeChannelMapper = tradeChannelMapper;
    }

    /**
     * Save a tradeChannel.
     *
     * @param tradeChannelDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradeChannelDTO> save(TradeChannelDTO tradeChannelDTO) {
        log.debug("Request to save TradeChannel : {}", tradeChannelDTO);
        tradeChannelDTO.setTradeChannelUlidId(IdAndUlidGeneration.generateUlid());
        return tradeChannelRepository.save(tradeChannelMapper.toEntity(tradeChannelDTO)).map(tradeChannelMapper::toDto);
    }

    /**
     * Update a tradeChannel.
     *
     * @param tradeChannelDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradeChannelDTO> update(TradeChannelDTO tradeChannelDTO) {
        log.debug("Request to update TradeChannel : {}", tradeChannelDTO);
        return tradeChannelRepository.save(tradeChannelMapper.toEntity(tradeChannelDTO)).map(tradeChannelMapper::toDto);
    }

    /**
     * Partially update a tradeChannel.
     *
     * @param tradeChannelDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TradeChannelDTO> partialUpdate(TradeChannelDTO tradeChannelDTO) {
        log.debug("Request to partially update TradeChannel : {}", tradeChannelDTO);

        return tradeChannelRepository
            .findById(tradeChannelDTO.getId())
            .map(existingTradeChannel -> {
                tradeChannelMapper.partialUpdate(existingTradeChannel, tradeChannelDTO);

                return existingTradeChannel;
            })
            .flatMap(tradeChannelRepository::save)
            .map(tradeChannelMapper::toDto);
    }

    /**
     * Get all the tradeChannels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TradeChannelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TradeChannels");
        return tradeChannelRepository.findAllBy(pageable).map(tradeChannelMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<TradeChannelDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return tradeChannelRepository.findAllByFilter(filter, pageable).map(tradeChannelMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return tradeChannelRepository.countByFilter(filter);
    }
    /**
     * Returns the number of tradeChannels available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tradeChannelRepository.count();
    }

    /**
     * Get one tradeChannel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TradeChannelDTO> findOne(Long id) {
        log.debug("Request to get TradeChannel : {}", id);
        return tradeChannelRepository.findById(id).map(tradeChannelMapper::toDto);
    }

    /**
     * Delete the tradeChannel by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TradeChannel : {}", id);
        return tradeChannelRepository.deleteById(id);
    }
}
