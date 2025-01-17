package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.PlacedOfferRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.PlacedOfferDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.PlacedOfferMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration.generateUlid;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.PlacedOffer}.
 */
@Service
@Transactional
public class PlacedOfferService {

    private final Logger log = LoggerFactory.getLogger(PlacedOfferService.class);

    private final PlacedOfferRepository placedOfferRepository;

    private final PlacedOfferMapper placedOfferMapper;

    public PlacedOfferService(PlacedOfferRepository placedOfferRepository, PlacedOfferMapper placedOfferMapper) {
        this.placedOfferRepository = placedOfferRepository;
        this.placedOfferMapper = placedOfferMapper;
    }

    /**
     * Save a placedOffer.
     *
     * @param placedOfferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<PlacedOfferDTO> save(PlacedOfferDTO placedOfferDTO) {
        log.debug("Request to save PlacedOffer : {}", placedOfferDTO);
        placedOfferDTO.setPlacedOfferUlidId(generateUlid());
        placedOfferDTO.setReqOffUlidId(generateUlid());
        return placedOfferRepository.save(placedOfferMapper.toEntity(placedOfferDTO))
            .flatMap(savedEntity -> {
                placedOfferDTO.setPlacedOfferRefNo("OFPD-PBY-FRCR-PTS-" + savedEntity.getId());
                placedOfferDTO.setRequestOfferRefNo("ROCR-PBY-FRCR-PTS-" + savedEntity.getId());
                return placedOfferRepository.findById(savedEntity.getId())
                    .flatMap(existingEntity -> {
                        existingEntity.setPlacedOfferRefNo(placedOfferDTO.getPlacedOfferRefNo());
                        existingEntity.setRequestOfferRefNo(placedOfferDTO.getRequestOfferRefNo());
                        return placedOfferRepository.save(existingEntity)
                            .map(placedOfferMapper::toDto);
                    });
            });
    }

    /**
     * Update a placedOffer.
     *
     * @param placedOfferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<PlacedOfferDTO> update(PlacedOfferDTO placedOfferDTO) {
        log.debug("Request to update PlacedOffer : {}", placedOfferDTO);
        return placedOfferRepository.save(placedOfferMapper.toEntity(placedOfferDTO)).map(placedOfferMapper::toDto);
    }

    /**
     * Partially update a placedOffer.
     *
     * @param placedOfferDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<PlacedOfferDTO> partialUpdate(PlacedOfferDTO placedOfferDTO) {
        log.debug("Request to partially update PlacedOffer : {}", placedOfferDTO);

        return placedOfferRepository
            .findById(placedOfferDTO.getId())
            .map(existingPlacedOffer -> {
                placedOfferMapper.partialUpdate(existingPlacedOffer, placedOfferDTO);

                return existingPlacedOffer;
            })
            .flatMap(placedOfferRepository::save)
            .map(placedOfferMapper::toDto);
    }

    /**
     * Get all the placedOffers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<PlacedOfferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlacedOffers");
        return placedOfferRepository.findAllBy(pageable).map(placedOfferMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<PlacedOfferDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return placedOfferRepository.findAllByFilter(filter, pageable).map(placedOfferMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return placedOfferRepository.countByFilter(filter);
    }

    /**
     * Get all the placedOffers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<PlacedOfferDTO> findAllWithEagerRelationships(Pageable pageable) {
        return placedOfferRepository.findAllWithEagerRelationships(pageable).map(placedOfferMapper::toDto);
    }

    /**
     * Returns the number of placedOffers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return placedOfferRepository.count();
    }

    /**
     * Get one placedOffer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<PlacedOfferDTO> findOne(Long id) {
        log.debug("Request to get PlacedOffer : {}", id);
        return placedOfferRepository.findOneWithEagerRelationships(id).map(placedOfferMapper::toDto);
    }

    /**
     * Delete the placedOffer by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete PlacedOffer : {}", id);
        return placedOfferRepository.deleteById(id);
    }
}
