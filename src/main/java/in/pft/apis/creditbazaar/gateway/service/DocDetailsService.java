package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.DocDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.DocDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.DocDetailsMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.DocDetails}.
 */
@Service
@Transactional
public class DocDetailsService {

    private final Logger log = LoggerFactory.getLogger(DocDetailsService.class);

    private final DocDetailsRepository docDetailsRepository;

    private final DocDetailsMapper docDetailsMapper;

    public DocDetailsService(DocDetailsRepository docDetailsRepository, DocDetailsMapper docDetailsMapper) {
        this.docDetailsRepository = docDetailsRepository;
        this.docDetailsMapper = docDetailsMapper;
    }

    /**
     * Save a docDetails.
     *
     * @param docDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<DocDetailsDTO> save(DocDetailsDTO docDetailsDTO) {
        log.debug("Request to save DocDetails : {}", docDetailsDTO);
        docDetailsDTO.setDocDetailsId(IdAndUlidGeneration.generateUniqueLong(9));
        docDetailsDTO.setDocDetailsUlidId(IdAndUlidGeneration.generateUlid());
        return docDetailsRepository.save(docDetailsMapper.toEntity(docDetailsDTO)).map(docDetailsMapper::toDto);
    }

    /**
     * Update a docDetails.
     *
     * @param docDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<DocDetailsDTO> update(DocDetailsDTO docDetailsDTO) {
        log.debug("Request to update DocDetails : {}", docDetailsDTO);
        return docDetailsRepository.save(docDetailsMapper.toEntity(docDetailsDTO)).map(docDetailsMapper::toDto);
    }

    /**
     * Partially update a docDetails.
     *
     * @param docDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<DocDetailsDTO> partialUpdate(DocDetailsDTO docDetailsDTO) {
        log.debug("Request to partially update DocDetails : {}", docDetailsDTO);

        return docDetailsRepository
            .findById(docDetailsDTO.getId())
            .map(existingDocDetails -> {
                docDetailsMapper.partialUpdate(existingDocDetails, docDetailsDTO);

                return existingDocDetails;
            })
            .flatMap(docDetailsRepository::save)
            .map(docDetailsMapper::toDto);
    }

    /**
     * Get all the docDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<DocDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DocDetails");
        return docDetailsRepository.findAllBy(pageable).map(docDetailsMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<DocDetailsDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return docDetailsRepository.findAllByFilter(filter, pageable).map(docDetailsMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return docDetailsRepository.countByFilter(filter);
    }


    /**
     * Get all the docDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<DocDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return docDetailsRepository.findAllWithEagerRelationships(pageable).map(docDetailsMapper::toDto);
    }

    /**
     * Returns the number of docDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return docDetailsRepository.count();
    }

    /**
     * Get one docDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<DocDetailsDTO> findOne(Long id) {
        log.debug("Request to get DocDetails : {}", id);
        return docDetailsRepository.findOneWithEagerRelationships(id).map(docDetailsMapper::toDto);
    }

    /**
     * Delete the docDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete DocDetails : {}", id);
        return docDetailsRepository.deleteById(id);
    }
}
