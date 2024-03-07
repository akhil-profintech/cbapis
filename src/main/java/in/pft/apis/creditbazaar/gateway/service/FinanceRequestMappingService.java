package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.FinanceRequestMappingRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestMappingDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FinanceRequestMappingMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.FinanceRequestMapping}.
 */
@Service
@Transactional
public class FinanceRequestMappingService {

    private final Logger log = LoggerFactory.getLogger(FinanceRequestMappingService.class);

    private final FinanceRequestMappingRepository financeRequestMappingRepository;

    private final FinanceRequestMappingMapper financeRequestMappingMapper;

    public FinanceRequestMappingService(
        FinanceRequestMappingRepository financeRequestMappingRepository,
        FinanceRequestMappingMapper financeRequestMappingMapper
    ) {
        this.financeRequestMappingRepository = financeRequestMappingRepository;
        this.financeRequestMappingMapper = financeRequestMappingMapper;
    }

    /**
     * Save a financeRequestMapping.
     *
     * @param financeRequestMappingDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FinanceRequestMappingDTO> save(FinanceRequestMappingDTO financeRequestMappingDTO) {
        log.debug("Request to save FinanceRequestMapping : {}", financeRequestMappingDTO);
        financeRequestMappingDTO.setFinanceRequestId(IdAndUlidGeneration.generateUniqueLong(9));
        financeRequestMappingDTO.setFinanceRequestMappingUlidId(IdAndUlidGeneration.generateUlid());
        return financeRequestMappingRepository
            .save(financeRequestMappingMapper.toEntity(financeRequestMappingDTO))
            .map(financeRequestMappingMapper::toDto);
    }

    /**
     * Update a financeRequestMapping.
     *
     * @param financeRequestMappingDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FinanceRequestMappingDTO> update(FinanceRequestMappingDTO financeRequestMappingDTO) {
        log.debug("Request to update FinanceRequestMapping : {}", financeRequestMappingDTO);
        return financeRequestMappingRepository
            .save(financeRequestMappingMapper.toEntity(financeRequestMappingDTO))
            .map(financeRequestMappingMapper::toDto);
    }

    /**
     * Partially update a financeRequestMapping.
     *
     * @param financeRequestMappingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<FinanceRequestMappingDTO> partialUpdate(FinanceRequestMappingDTO financeRequestMappingDTO) {
        log.debug("Request to partially update FinanceRequestMapping : {}", financeRequestMappingDTO);

        return financeRequestMappingRepository
            .findById(financeRequestMappingDTO.getId())
            .map(existingFinanceRequestMapping -> {
                financeRequestMappingMapper.partialUpdate(existingFinanceRequestMapping, financeRequestMappingDTO);

                return existingFinanceRequestMapping;
            })
            .flatMap(financeRequestMappingRepository::save)
            .map(financeRequestMappingMapper::toDto);
    }

    /**
     * Get all the financeRequestMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<FinanceRequestMappingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinanceRequestMappings");
        return financeRequestMappingRepository.findAllBy(pageable).map(financeRequestMappingMapper::toDto);
    }

    /**
     * Returns the number of financeRequestMappings available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return financeRequestMappingRepository.count();
    }

    /**
     * Get one financeRequestMapping by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<FinanceRequestMappingDTO> findOne(Long id) {
        log.debug("Request to get FinanceRequestMapping : {}", id);
        return financeRequestMappingRepository.findById(id).map(financeRequestMappingMapper::toDto);
    }

    /**
     * Delete the financeRequestMapping by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FinanceRequestMapping : {}", id);
        return financeRequestMappingRepository.deleteById(id);
    }
}
