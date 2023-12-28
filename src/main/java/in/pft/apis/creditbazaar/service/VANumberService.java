package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.VANumberRepository;
import in.pft.apis.creditbazaar.service.dto.VANumberDTO;
import in.pft.apis.creditbazaar.service.mapper.VANumberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.VANumber}.
 */
@Service
@Transactional
public class VANumberService {

    private final Logger log = LoggerFactory.getLogger(VANumberService.class);

    private final VANumberRepository vANumberRepository;

    private final VANumberMapper vANumberMapper;

    public VANumberService(VANumberRepository vANumberRepository, VANumberMapper vANumberMapper) {
        this.vANumberRepository = vANumberRepository;
        this.vANumberMapper = vANumberMapper;
    }

    /**
     * Save a vANumber.
     *
     * @param vANumberDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VANumberDTO> save(VANumberDTO vANumberDTO) {
        log.debug("Request to save VANumber : {}", vANumberDTO);
        return vANumberRepository.save(vANumberMapper.toEntity(vANumberDTO)).map(vANumberMapper::toDto);
    }

    /**
     * Update a vANumber.
     *
     * @param vANumberDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<VANumberDTO> update(VANumberDTO vANumberDTO) {
        log.debug("Request to update VANumber : {}", vANumberDTO);
        return vANumberRepository.save(vANumberMapper.toEntity(vANumberDTO)).map(vANumberMapper::toDto);
    }

    /**
     * Partially update a vANumber.
     *
     * @param vANumberDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<VANumberDTO> partialUpdate(VANumberDTO vANumberDTO) {
        log.debug("Request to partially update VANumber : {}", vANumberDTO);

        return vANumberRepository
            .findById(vANumberDTO.getId())
            .map(existingVANumber -> {
                vANumberMapper.partialUpdate(existingVANumber, vANumberDTO);

                return existingVANumber;
            })
            .flatMap(vANumberRepository::save)
            .map(vANumberMapper::toDto);
    }

    /**
     * Get all the vANumbers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<VANumberDTO> findAll(Pageable pageable) {
        log.debug("Request to get all VANumbers");
        return vANumberRepository.findAllBy(pageable).map(vANumberMapper::toDto);
    }

    /**
     * Get all the vANumbers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<VANumberDTO> findAllWithEagerRelationships(Pageable pageable) {
        return vANumberRepository.findAllWithEagerRelationships(pageable).map(vANumberMapper::toDto);
    }

    /**
     * Returns the number of vANumbers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return vANumberRepository.count();
    }

    /**
     * Get one vANumber by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<VANumberDTO> findOne(Long id) {
        log.debug("Request to get VANumber : {}", id);
        return vANumberRepository.findOneWithEagerRelationships(id).map(vANumberMapper::toDto);
    }

    /**
     * Delete the vANumber by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete VANumber : {}", id);
        return vANumberRepository.deleteById(id);
    }
}
