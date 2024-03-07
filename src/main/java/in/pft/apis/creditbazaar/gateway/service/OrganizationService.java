package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.OrganizationRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.OrganizationDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.OrganizationMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.Organization}.
 */
@Service
@Transactional
public class OrganizationService {

    private final Logger log = LoggerFactory.getLogger(OrganizationService.class);

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    public OrganizationService(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    /**
     * Save a organization.
     *
     * @param organizationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<OrganizationDTO> save(OrganizationDTO organizationDTO) {
        log.debug("Request to save Organization : {}", organizationDTO);
        organizationDTO.setOrgId(IdAndUlidGeneration.generateUniqueLong(9));
        organizationDTO.setOrgUlidId(IdAndUlidGeneration.generateUlid());
        return organizationRepository.save(organizationMapper.toEntity(organizationDTO)).map(organizationMapper::toDto);
    }

    /**
     * Update a organization.
     *
     * @param organizationDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<OrganizationDTO> update(OrganizationDTO organizationDTO) {
        log.debug("Request to update Organization : {}", organizationDTO);
        return organizationRepository.save(organizationMapper.toEntity(organizationDTO)).map(organizationMapper::toDto);
    }

    /**
     * Partially update a organization.
     *
     * @param organizationDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<OrganizationDTO> partialUpdate(OrganizationDTO organizationDTO) {
        log.debug("Request to partially update Organization : {}", organizationDTO);

        return organizationRepository
            .findById(organizationDTO.getId())
            .map(existingOrganization -> {
                organizationMapper.partialUpdate(existingOrganization, organizationDTO);

                return existingOrganization;
            })
            .flatMap(organizationRepository::save)
            .map(organizationMapper::toDto);
    }

    /**
     * Get all the organizations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<OrganizationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Organizations");
        return organizationRepository.findAllBy(pageable).map(organizationMapper::toDto);
    }

    /**
     * Returns the number of organizations available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return organizationRepository.count();
    }

    /**
     * Get one organization by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<OrganizationDTO> findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        return organizationRepository.findById(id).map(organizationMapper::toDto);
    }

    /**
     * Delete the organization by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        return organizationRepository.deleteById(id);
    }
}
