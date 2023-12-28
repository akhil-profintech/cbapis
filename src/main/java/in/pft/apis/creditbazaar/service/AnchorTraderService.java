package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.AnchorTraderRepository;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.service.mapper.AnchorTraderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.AnchorTrader}.
 */
@Service
@Transactional
public class AnchorTraderService {

    private final Logger log = LoggerFactory.getLogger(AnchorTraderService.class);

    private final AnchorTraderRepository anchorTraderRepository;

    private final AnchorTraderMapper anchorTraderMapper;

    public AnchorTraderService(AnchorTraderRepository anchorTraderRepository, AnchorTraderMapper anchorTraderMapper) {
        this.anchorTraderRepository = anchorTraderRepository;
        this.anchorTraderMapper = anchorTraderMapper;
    }

    /**
     * Save a anchorTrader.
     *
     * @param anchorTraderDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnchorTraderDTO> save(AnchorTraderDTO anchorTraderDTO) {
        log.debug("Request to save AnchorTrader : {}", anchorTraderDTO);
        return anchorTraderRepository.save(anchorTraderMapper.toEntity(anchorTraderDTO)).map(anchorTraderMapper::toDto);
    }

    /**
     * Update a anchorTrader.
     *
     * @param anchorTraderDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnchorTraderDTO> update(AnchorTraderDTO anchorTraderDTO) {
        log.debug("Request to update AnchorTrader : {}", anchorTraderDTO);
        return anchorTraderRepository.save(anchorTraderMapper.toEntity(anchorTraderDTO)).map(anchorTraderMapper::toDto);
    }

    /**
     * Partially update a anchorTrader.
     *
     * @param anchorTraderDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnchorTraderDTO> partialUpdate(AnchorTraderDTO anchorTraderDTO) {
        log.debug("Request to partially update AnchorTrader : {}", anchorTraderDTO);

        return anchorTraderRepository
            .findById(anchorTraderDTO.getId())
            .map(existingAnchorTrader -> {
                anchorTraderMapper.partialUpdate(existingAnchorTrader, anchorTraderDTO);

                return existingAnchorTrader;
            })
            .flatMap(anchorTraderRepository::save)
            .map(anchorTraderMapper::toDto);
    }

    /**
     * Get all the anchorTraders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnchorTraderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnchorTraders");
        return anchorTraderRepository.findAllBy(pageable).map(anchorTraderMapper::toDto);
    }

    /**
     * Returns the number of anchorTraders available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return anchorTraderRepository.count();
    }

    /**
     * Get one anchorTrader by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnchorTraderDTO> findOne(Long id) {
        log.debug("Request to get AnchorTrader : {}", id);
        return anchorTraderRepository.findById(id).map(anchorTraderMapper::toDto);
    }

    /**
     * Delete the anchorTrader by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnchorTrader : {}", id);
        return anchorTraderRepository.deleteById(id);
    }
}
