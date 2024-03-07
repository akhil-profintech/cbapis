package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.TradePartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.TradePartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.TradePartnerMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.TradePartner}.
 */
@Service
@Transactional
public class TradePartnerService {

    private final Logger log = LoggerFactory.getLogger(TradePartnerService.class);

    private final TradePartnerRepository tradePartnerRepository;

    private final TradePartnerMapper tradePartnerMapper;

    public TradePartnerService(TradePartnerRepository tradePartnerRepository, TradePartnerMapper tradePartnerMapper) {
        this.tradePartnerRepository = tradePartnerRepository;
        this.tradePartnerMapper = tradePartnerMapper;
    }

    /**
     * Save a tradePartner.
     *
     * @param tradePartnerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradePartnerDTO> save(TradePartnerDTO tradePartnerDTO) {
        log.debug("Request to save TradePartner : {}", tradePartnerDTO);
        tradePartnerDTO.setTpId(IdAndUlidGeneration.generateUniqueLong(9));
        tradePartnerDTO.setTpUlidId(IdAndUlidGeneration.generateUlid());
        return tradePartnerRepository.save(tradePartnerMapper.toEntity(tradePartnerDTO)).map(tradePartnerMapper::toDto);
    }

    /**
     * Update a tradePartner.
     *
     * @param tradePartnerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TradePartnerDTO> update(TradePartnerDTO tradePartnerDTO) {
        log.debug("Request to update TradePartner : {}", tradePartnerDTO);
        return tradePartnerRepository.save(tradePartnerMapper.toEntity(tradePartnerDTO)).map(tradePartnerMapper::toDto);
    }

    /**
     * Partially update a tradePartner.
     *
     * @param tradePartnerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TradePartnerDTO> partialUpdate(TradePartnerDTO tradePartnerDTO) {
        log.debug("Request to partially update TradePartner : {}", tradePartnerDTO);

        return tradePartnerRepository
            .findById(tradePartnerDTO.getId())
            .map(existingTradePartner -> {
                tradePartnerMapper.partialUpdate(existingTradePartner, tradePartnerDTO);

                return existingTradePartner;
            })
            .flatMap(tradePartnerRepository::save)
            .map(tradePartnerMapper::toDto);
    }

    /**
     * Get all the tradePartners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TradePartnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TradePartners");
        return tradePartnerRepository.findAllBy(pageable).map(tradePartnerMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<TradePartnerDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return tradePartnerRepository.findAllByFilter(filter, pageable).map(tradePartnerMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return tradePartnerRepository.countByFilter(filter);
    }

    /**
     * Returns the number of tradePartners available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tradePartnerRepository.count();
    }

    /**
     * Get one tradePartner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TradePartnerDTO> findOne(Long id) {
        log.debug("Request to get TradePartner : {}", id);
        return tradePartnerRepository.findById(id).map(tradePartnerMapper::toDto);
    }

    /**
     * Delete the tradePartner by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TradePartner : {}", id);
        return tradePartnerRepository.deleteById(id);
    }
}
