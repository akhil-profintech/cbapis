package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.AnchorTraderPartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderPartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.AnchorTraderPartnerMapper;
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
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner}.
 */
@Service
@Transactional
public class AnchorTraderPartnerService {

    private final Logger log = LoggerFactory.getLogger(AnchorTraderPartnerService.class);

    private final AnchorTraderPartnerRepository anchorTraderPartnerRepository;

    private final AnchorTraderPartnerMapper anchorTraderPartnerMapper;

    public AnchorTraderPartnerService(
        AnchorTraderPartnerRepository anchorTraderPartnerRepository,
        AnchorTraderPartnerMapper anchorTraderPartnerMapper
    ) {
        this.anchorTraderPartnerRepository = anchorTraderPartnerRepository;
        this.anchorTraderPartnerMapper = anchorTraderPartnerMapper;
    }
    /**
     * Save a anchorTraderPartner.
     *
     * @param anchorTraderPartnerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnchorTraderPartnerDTO> save(AnchorTraderPartnerDTO anchorTraderPartnerDTO) {
        log.debug("Request to save AnchorTraderPartner : {}", anchorTraderPartnerDTO);
        anchorTraderPartnerDTO.setAtpartnerId(generateUniqueLong(9));
        anchorTraderPartnerDTO.setAtpartnerUlidId(generateUlid());
        return anchorTraderPartnerRepository
            .save(anchorTraderPartnerMapper.toEntity(anchorTraderPartnerDTO))
            .map(anchorTraderPartnerMapper::toDto);
    }

    /**
     * Update a anchorTraderPartner.
     *
     * @param anchorTraderPartnerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AnchorTraderPartnerDTO> update(AnchorTraderPartnerDTO anchorTraderPartnerDTO) {
        log.debug("Request to update AnchorTraderPartner : {}", anchorTraderPartnerDTO);
        return anchorTraderPartnerRepository
            .save(anchorTraderPartnerMapper.toEntity(anchorTraderPartnerDTO))
            .map(anchorTraderPartnerMapper::toDto);
    }

    /**
     * Partially update a anchorTraderPartner.
     *
     * @param anchorTraderPartnerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AnchorTraderPartnerDTO> partialUpdate(AnchorTraderPartnerDTO anchorTraderPartnerDTO) {
        log.debug("Request to partially update AnchorTraderPartner : {}", anchorTraderPartnerDTO);

        return anchorTraderPartnerRepository
            .findById(anchorTraderPartnerDTO.getId())
            .map(existingAnchorTraderPartner -> {
                anchorTraderPartnerMapper.partialUpdate(existingAnchorTraderPartner, anchorTraderPartnerDTO);

                return existingAnchorTraderPartner;
            })
            .flatMap(anchorTraderPartnerRepository::save)
            .map(anchorTraderPartnerMapper::toDto);
    }

    /**
     * Get all the anchorTraderPartners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AnchorTraderPartnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnchorTraderPartners");
        return anchorTraderPartnerRepository.findAllBy(pageable).map(anchorTraderPartnerMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<AnchorTraderPartnerDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraderPartners");
        return anchorTraderPartnerRepository.findAllByFilter(filter, pageable).map(anchorTraderPartnerMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return anchorTraderPartnerRepository.countByFilter(filter);
    }



    /**
     * Get all the anchorTraderPartners with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<AnchorTraderPartnerDTO> findAllWithEagerRelationships(Pageable pageable) {
        return anchorTraderPartnerRepository.findAllWithEagerRelationships(pageable).map(anchorTraderPartnerMapper::toDto);
    }

    /**
     * Returns the number of anchorTraderPartners available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return anchorTraderPartnerRepository.count();
    }

    /**
     * Get one anchorTraderPartner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AnchorTraderPartnerDTO> findOne(Long id) {
        log.debug("Request to get AnchorTraderPartner : {}", id);
        return anchorTraderPartnerRepository.findOneWithEagerRelationships(id).map(anchorTraderPartnerMapper::toDto);
    }

    /**
     * Delete the anchorTraderPartner by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AnchorTraderPartner : {}", id);
        return anchorTraderPartnerRepository.deleteById(id);
    }
}
