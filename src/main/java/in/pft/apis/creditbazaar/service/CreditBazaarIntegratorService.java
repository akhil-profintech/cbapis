package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.CreditBazaarIntegratorRepository;
import in.pft.apis.creditbazaar.service.dto.CreditBazaarIntegratorDTO;
import in.pft.apis.creditbazaar.service.mapper.CreditBazaarIntegratorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.CreditBazaarIntegrator}.
 */
@Service
@Transactional
public class CreditBazaarIntegratorService {

    private final Logger log = LoggerFactory.getLogger(CreditBazaarIntegratorService.class);

    private final CreditBazaarIntegratorRepository creditBazaarIntegratorRepository;

    private final CreditBazaarIntegratorMapper creditBazaarIntegratorMapper;

    public CreditBazaarIntegratorService(
        CreditBazaarIntegratorRepository creditBazaarIntegratorRepository,
        CreditBazaarIntegratorMapper creditBazaarIntegratorMapper
    ) {
        this.creditBazaarIntegratorRepository = creditBazaarIntegratorRepository;
        this.creditBazaarIntegratorMapper = creditBazaarIntegratorMapper;
    }

    /**
     * Save a creditBazaarIntegrator.
     *
     * @param creditBazaarIntegratorDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreditBazaarIntegratorDTO> save(CreditBazaarIntegratorDTO creditBazaarIntegratorDTO) {
        log.debug("Request to save CreditBazaarIntegrator : {}", creditBazaarIntegratorDTO);
        return creditBazaarIntegratorRepository
            .save(creditBazaarIntegratorMapper.toEntity(creditBazaarIntegratorDTO))
            .map(creditBazaarIntegratorMapper::toDto);
    }

    /**
     * Update a creditBazaarIntegrator.
     *
     * @param creditBazaarIntegratorDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreditBazaarIntegratorDTO> update(CreditBazaarIntegratorDTO creditBazaarIntegratorDTO) {
        log.debug("Request to update CreditBazaarIntegrator : {}", creditBazaarIntegratorDTO);
        return creditBazaarIntegratorRepository
            .save(creditBazaarIntegratorMapper.toEntity(creditBazaarIntegratorDTO))
            .map(creditBazaarIntegratorMapper::toDto);
    }

    /**
     * Partially update a creditBazaarIntegrator.
     *
     * @param creditBazaarIntegratorDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreditBazaarIntegratorDTO> partialUpdate(CreditBazaarIntegratorDTO creditBazaarIntegratorDTO) {
        log.debug("Request to partially update CreditBazaarIntegrator : {}", creditBazaarIntegratorDTO);

        return creditBazaarIntegratorRepository
            .findById(creditBazaarIntegratorDTO.getId())
            .map(existingCreditBazaarIntegrator -> {
                creditBazaarIntegratorMapper.partialUpdate(existingCreditBazaarIntegrator, creditBazaarIntegratorDTO);

                return existingCreditBazaarIntegrator;
            })
            .flatMap(creditBazaarIntegratorRepository::save)
            .map(creditBazaarIntegratorMapper::toDto);
    }

    /**
     * Get all the creditBazaarIntegrators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreditBazaarIntegratorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CreditBazaarIntegrators");
        return creditBazaarIntegratorRepository.findAllBy(pageable).map(creditBazaarIntegratorMapper::toDto);
    }

    /**
     * Returns the number of creditBazaarIntegrators available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return creditBazaarIntegratorRepository.count();
    }

    /**
     * Get one creditBazaarIntegrator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreditBazaarIntegratorDTO> findOne(Long id) {
        log.debug("Request to get CreditBazaarIntegrator : {}", id);
        return creditBazaarIntegratorRepository.findById(id).map(creditBazaarIntegratorMapper::toDto);
    }

    /**
     * Delete the creditBazaarIntegrator by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreditBazaarIntegrator : {}", id);
        return creditBazaarIntegratorRepository.deleteById(id);
    }
}
