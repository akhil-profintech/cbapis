package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.GstinRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.GstinDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.GstinMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.Gstin}.
 */
@Service
@Transactional
public class GstinService {

    private final Logger log = LoggerFactory.getLogger(GstinService.class);

    private final GstinRepository gstinRepository;

    private final GstinMapper gstinMapper;

    public GstinService(GstinRepository gstinRepository, GstinMapper gstinMapper) {
        this.gstinRepository = gstinRepository;
        this.gstinMapper = gstinMapper;
    }

    /**
     * Save a gstin.
     *
     * @param gstinDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<GstinDTO> save(GstinDTO gstinDTO) {
        log.debug("Request to save Gstin : {}", gstinDTO);
        return gstinRepository.save(gstinMapper.toEntity(gstinDTO)).map(gstinMapper::toDto);
    }

    /**
     * Update a gstin.
     *
     * @param gstinDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<GstinDTO> update(GstinDTO gstinDTO) {
        log.debug("Request to update Gstin : {}", gstinDTO);
        return gstinRepository.save(gstinMapper.toEntity(gstinDTO)).map(gstinMapper::toDto);
    }

    /**
     * Partially update a gstin.
     *
     * @param gstinDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<GstinDTO> partialUpdate(GstinDTO gstinDTO) {
        log.debug("Request to partially update Gstin : {}", gstinDTO);

        return gstinRepository
            .findById(gstinDTO.getId())
            .map(existingGstin -> {
                gstinMapper.partialUpdate(existingGstin, gstinDTO);

                return existingGstin;
            })
            .flatMap(gstinRepository::save)
            .map(gstinMapper::toDto);
    }

    /**
     * Get all the gstins.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<GstinDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Gstins");
        return gstinRepository.findAllBy(pageable).map(gstinMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<GstinDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all EscrowTransactionDetails");
        return gstinRepository.findAllByFilter(filter, pageable).map(gstinMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return gstinRepository.countByFilter(filter);
    }

    /**
     * Get all the gstins with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<GstinDTO> findAllWithEagerRelationships(Pageable pageable) {
        return gstinRepository.findAllWithEagerRelationships(pageable).map(gstinMapper::toDto);
    }

    /**
     * Returns the number of gstins available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return gstinRepository.count();
    }

    /**
     * Get one gstin by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<GstinDTO> findOne(Long id) {
        log.debug("Request to get Gstin : {}", id);
        return gstinRepository.findOneWithEagerRelationships(id).map(gstinMapper::toDto);
    }

    /**
     * Delete the gstin by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Gstin : {}", id);
        return gstinRepository.deleteById(id);
    }
}
