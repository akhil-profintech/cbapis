package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.TenantDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.TenantDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.TenantDetailsMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.TenantDetails}.
 */
@Service
@Transactional
public class TenantDetailsService {

    private final Logger log = LoggerFactory.getLogger(TenantDetailsService.class);

    private final TenantDetailsRepository tenantDetailsRepository;

    private final TenantDetailsMapper tenantDetailsMapper;

    public TenantDetailsService(TenantDetailsRepository tenantDetailsRepository, TenantDetailsMapper tenantDetailsMapper) {
        this.tenantDetailsRepository = tenantDetailsRepository;
        this.tenantDetailsMapper = tenantDetailsMapper;
    }

    /**
     * Save a tenantDetails.
     *
     * @param tenantDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TenantDetailsDTO> save(TenantDetailsDTO tenantDetailsDTO) {
        log.debug("Request to save TenantDetails : {}", tenantDetailsDTO);
        tenantDetailsDTO.setTenantUlidId(IdAndUlidGeneration.generateUlid());
        return tenantDetailsRepository.save(tenantDetailsMapper.toEntity(tenantDetailsDTO)).map(tenantDetailsMapper::toDto);
    }

    /**
     * Update a tenantDetails.
     *
     * @param tenantDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<TenantDetailsDTO> update(TenantDetailsDTO tenantDetailsDTO) {
        log.debug("Request to update TenantDetails : {}", tenantDetailsDTO);
        return tenantDetailsRepository.save(tenantDetailsMapper.toEntity(tenantDetailsDTO)).map(tenantDetailsMapper::toDto);
    }

    /**
     * Partially update a tenantDetails.
     *
     * @param tenantDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<TenantDetailsDTO> partialUpdate(TenantDetailsDTO tenantDetailsDTO) {
        log.debug("Request to partially update TenantDetails : {}", tenantDetailsDTO);

        return tenantDetailsRepository
            .findById(tenantDetailsDTO.getId())
            .map(existingTenantDetails -> {
                tenantDetailsMapper.partialUpdate(existingTenantDetails, tenantDetailsDTO);

                return existingTenantDetails;
            })
            .flatMap(tenantDetailsRepository::save)
            .map(tenantDetailsMapper::toDto);
    }

    /**
     * Get all the tenantDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<TenantDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TenantDetails");
        return tenantDetailsRepository.findAllBy(pageable).map(tenantDetailsMapper::toDto);
    }

    /**
     * Returns the number of tenantDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return tenantDetailsRepository.count();
    }

    /**
     * Get one tenantDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<TenantDetailsDTO> findOne(Long id) {
        log.debug("Request to get TenantDetails : {}", id);
        return tenantDetailsRepository.findById(id).map(tenantDetailsMapper::toDto);
    }

    /**
     * Delete the tenantDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete TenantDetails : {}", id);
        return tenantDetailsRepository.deleteById(id);
    }
}
