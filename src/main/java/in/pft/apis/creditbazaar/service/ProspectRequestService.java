package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.ProspectRequestRepository;
import in.pft.apis.creditbazaar.service.dto.ProspectRequestDTO;
import in.pft.apis.creditbazaar.service.mapper.ProspectRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.ProspectRequest}.
 */
@Service
@Transactional
public class ProspectRequestService {

    private final Logger log = LoggerFactory.getLogger(ProspectRequestService.class);

    private final ProspectRequestRepository prospectRequestRepository;

    private final ProspectRequestMapper prospectRequestMapper;

    public ProspectRequestService(ProspectRequestRepository prospectRequestRepository, ProspectRequestMapper prospectRequestMapper) {
        this.prospectRequestRepository = prospectRequestRepository;
        this.prospectRequestMapper = prospectRequestMapper;
    }

    /**
     * Save a prospectRequest.
     *
     * @param prospectRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ProspectRequestDTO> save(ProspectRequestDTO prospectRequestDTO) {
        log.debug("Request to save ProspectRequest : {}", prospectRequestDTO);
        return prospectRequestRepository.save(prospectRequestMapper.toEntity(prospectRequestDTO)).map(prospectRequestMapper::toDto);
    }

    /**
     * Update a prospectRequest.
     *
     * @param prospectRequestDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ProspectRequestDTO> update(ProspectRequestDTO prospectRequestDTO) {
        log.debug("Request to update ProspectRequest : {}", prospectRequestDTO);
        return prospectRequestRepository.save(prospectRequestMapper.toEntity(prospectRequestDTO)).map(prospectRequestMapper::toDto);
    }

    /**
     * Partially update a prospectRequest.
     *
     * @param prospectRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ProspectRequestDTO> partialUpdate(ProspectRequestDTO prospectRequestDTO) {
        log.debug("Request to partially update ProspectRequest : {}", prospectRequestDTO);

        return prospectRequestRepository
            .findById(prospectRequestDTO.getId())
            .map(existingProspectRequest -> {
                prospectRequestMapper.partialUpdate(existingProspectRequest, prospectRequestDTO);

                return existingProspectRequest;
            })
            .flatMap(prospectRequestRepository::save)
            .map(prospectRequestMapper::toDto);
    }

    /**
     * Get all the prospectRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ProspectRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProspectRequests");
        return prospectRequestRepository.findAllBy(pageable).map(prospectRequestMapper::toDto);
    }

    /**
     * Get all the prospectRequests with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<ProspectRequestDTO> findAllWithEagerRelationships(Pageable pageable) {
        return prospectRequestRepository.findAllWithEagerRelationships(pageable).map(prospectRequestMapper::toDto);
    }

    /**
     * Returns the number of prospectRequests available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return prospectRequestRepository.count();
    }

    /**
     * Get one prospectRequest by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ProspectRequestDTO> findOne(Long id) {
        log.debug("Request to get ProspectRequest : {}", id);
        return prospectRequestRepository.findOneWithEagerRelationships(id).map(prospectRequestMapper::toDto);
    }

    /**
     * Delete the prospectRequest by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ProspectRequest : {}", id);
        return prospectRequestRepository.deleteById(id);
    }
}
