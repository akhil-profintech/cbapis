package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.CBCREProcessRepository;
import in.pft.apis.creditbazaar.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.service.mapper.CBCREProcessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.CBCREProcess}.
 */
@Service
@Transactional
public class CBCREProcessService {

    private final Logger log = LoggerFactory.getLogger(CBCREProcessService.class);

    private final CBCREProcessRepository cBCREProcessRepository;

    private final CBCREProcessMapper cBCREProcessMapper;

    public CBCREProcessService(CBCREProcessRepository cBCREProcessRepository, CBCREProcessMapper cBCREProcessMapper) {
        this.cBCREProcessRepository = cBCREProcessRepository;
        this.cBCREProcessMapper = cBCREProcessMapper;
    }

    /**
     * Save a cBCREProcess.
     *
     * @param cBCREProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CBCREProcessDTO> save(CBCREProcessDTO cBCREProcessDTO) {
        log.debug("Request to save CBCREProcess : {}", cBCREProcessDTO);
        return cBCREProcessRepository.save(cBCREProcessMapper.toEntity(cBCREProcessDTO)).map(cBCREProcessMapper::toDto);
    }

    /**
     * Update a cBCREProcess.
     *
     * @param cBCREProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CBCREProcessDTO> update(CBCREProcessDTO cBCREProcessDTO) {
        log.debug("Request to update CBCREProcess : {}", cBCREProcessDTO);
        return cBCREProcessRepository.save(cBCREProcessMapper.toEntity(cBCREProcessDTO)).map(cBCREProcessMapper::toDto);
    }

    /**
     * Partially update a cBCREProcess.
     *
     * @param cBCREProcessDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CBCREProcessDTO> partialUpdate(CBCREProcessDTO cBCREProcessDTO) {
        log.debug("Request to partially update CBCREProcess : {}", cBCREProcessDTO);

        return cBCREProcessRepository
            .findById(cBCREProcessDTO.getId())
            .map(existingCBCREProcess -> {
                cBCREProcessMapper.partialUpdate(existingCBCREProcess, cBCREProcessDTO);

                return existingCBCREProcess;
            })
            .flatMap(cBCREProcessRepository::save)
            .map(cBCREProcessMapper::toDto);
    }

    /**
     * Get all the cBCREProcesses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CBCREProcessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CBCREProcesses");
        return cBCREProcessRepository.findAllBy(pageable).map(cBCREProcessMapper::toDto);
    }

    /**
     * Returns the number of cBCREProcesses available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return cBCREProcessRepository.count();
    }

    /**
     * Get one cBCREProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CBCREProcessDTO> findOne(Long id) {
        log.debug("Request to get CBCREProcess : {}", id);
        return cBCREProcessRepository.findById(id).map(cBCREProcessMapper::toDto);
    }

    /**
     * Delete the cBCREProcess by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CBCREProcess : {}", id);
        return cBCREProcessRepository.deleteById(id);
    }
}
