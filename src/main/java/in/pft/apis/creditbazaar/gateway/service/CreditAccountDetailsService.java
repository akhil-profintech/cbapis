package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.CreditAccountDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.CreditAccountDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CreditAccountDetailsMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.CreditAccountDetails}.
 */
@Service
@Transactional
public class CreditAccountDetailsService {

    private final Logger log = LoggerFactory.getLogger(CreditAccountDetailsService.class);

    private final CreditAccountDetailsRepository creditAccountDetailsRepository;

    private final CreditAccountDetailsMapper creditAccountDetailsMapper;

    public CreditAccountDetailsService(
        CreditAccountDetailsRepository creditAccountDetailsRepository,
        CreditAccountDetailsMapper creditAccountDetailsMapper
    ) {
        this.creditAccountDetailsRepository = creditAccountDetailsRepository;
        this.creditAccountDetailsMapper = creditAccountDetailsMapper;
    }

    /**
     * Save a creditAccountDetails.
     *
     * @param creditAccountDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreditAccountDetailsDTO> save(CreditAccountDetailsDTO creditAccountDetailsDTO) {
        log.debug("Request to save CreditAccountDetails : {}", creditAccountDetailsDTO);
        creditAccountDetailsDTO.setCreditAccDetailsId(IdAndUlidGeneration.generateUniqueLong(9));
        creditAccountDetailsDTO.setCreditAccountDetailsUlidId(IdAndUlidGeneration.generateUlid());
        return creditAccountDetailsRepository
            .save(creditAccountDetailsMapper.toEntity(creditAccountDetailsDTO))
            .map(creditAccountDetailsMapper::toDto);
    }

    /**
     * Update a creditAccountDetails.
     *
     * @param creditAccountDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<CreditAccountDetailsDTO> update(CreditAccountDetailsDTO creditAccountDetailsDTO) {
        log.debug("Request to update CreditAccountDetails : {}", creditAccountDetailsDTO);
        return creditAccountDetailsRepository
            .save(creditAccountDetailsMapper.toEntity(creditAccountDetailsDTO))
            .map(creditAccountDetailsMapper::toDto);
    }

    /**
     * Partially update a creditAccountDetails.
     *
     * @param creditAccountDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<CreditAccountDetailsDTO> partialUpdate(CreditAccountDetailsDTO creditAccountDetailsDTO) {
        log.debug("Request to partially update CreditAccountDetails : {}", creditAccountDetailsDTO);

        return creditAccountDetailsRepository
            .findById(creditAccountDetailsDTO.getId())
            .map(existingCreditAccountDetails -> {
                creditAccountDetailsMapper.partialUpdate(existingCreditAccountDetails, creditAccountDetailsDTO);

                return existingCreditAccountDetails;
            })
            .flatMap(creditAccountDetailsRepository::save)
            .map(creditAccountDetailsMapper::toDto);
    }

    /**
     * Get all the creditAccountDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<CreditAccountDetailsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CreditAccountDetails");
        return creditAccountDetailsRepository.findAllBy(pageable).map(creditAccountDetailsMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<CreditAccountDetailsDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return creditAccountDetailsRepository.findAllByFilter(filter, pageable).map(creditAccountDetailsMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return creditAccountDetailsRepository.countByFilter(filter);
    }

    /**
     * Get all the creditAccountDetails with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<CreditAccountDetailsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return creditAccountDetailsRepository.findAllWithEagerRelationships(pageable).map(creditAccountDetailsMapper::toDto);
    }

    /**
     * Returns the number of creditAccountDetails available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return creditAccountDetailsRepository.count();
    }

    /**
     * Get one creditAccountDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<CreditAccountDetailsDTO> findOne(Long id) {
        log.debug("Request to get CreditAccountDetails : {}", id);
        return creditAccountDetailsRepository.findOneWithEagerRelationships(id).map(creditAccountDetailsMapper::toDto);
    }

    /**
     * Delete the creditAccountDetails by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete CreditAccountDetails : {}", id);
        return creditAccountDetailsRepository.deleteById(id);
    }
}
