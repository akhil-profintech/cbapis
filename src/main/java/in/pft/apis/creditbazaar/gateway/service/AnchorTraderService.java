package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.AnchorTraderRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.AnchorTraderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration.generateUlid;
import static in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration.generateUniqueLong;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.AnchorTrader}.
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
        anchorTraderDTO.setAtId(generateUniqueLong(9));
        anchorTraderDTO.setAtUlidId(generateUlid());
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

    @Transactional(readOnly = true)
    public Flux<AnchorTraderDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return anchorTraderRepository.findAllByFilter(filter, pageable).map(anchorTraderMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return anchorTraderRepository.countByFilter(filter);
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
