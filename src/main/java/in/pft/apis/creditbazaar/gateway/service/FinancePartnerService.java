package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.FinancePartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FinancePartnerMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.FinancePartner}.
 */
@Service
@Transactional
public class FinancePartnerService {

    private final Logger log = LoggerFactory.getLogger(FinancePartnerService.class);

    private final FinancePartnerRepository financePartnerRepository;

    private final FinancePartnerMapper financePartnerMapper;

    public FinancePartnerService(FinancePartnerRepository financePartnerRepository, FinancePartnerMapper financePartnerMapper) {
        this.financePartnerRepository = financePartnerRepository;
        this.financePartnerMapper = financePartnerMapper;
    }

    /**
     * Save a financePartner.
     *
     * @param financePartnerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FinancePartnerDTO> save(FinancePartnerDTO financePartnerDTO) {
        log.debug("Request to save FinancePartner : {}", financePartnerDTO);
        financePartnerDTO.setFpUlidId(IdAndUlidGeneration.generateUlid());
        financePartnerDTO.setFpId(IdAndUlidGeneration.generateUniqueLong(9));
        return financePartnerRepository.save(financePartnerMapper.toEntity(financePartnerDTO))
            .map(financePartnerMapper::toDto);
    }

    /**
     * Update a financePartner.
     *
     * @param financePartnerDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FinancePartnerDTO> update(FinancePartnerDTO financePartnerDTO) {
        log.debug("Request to update FinancePartner : {}", financePartnerDTO);
        return financePartnerRepository.save(financePartnerMapper.toEntity(financePartnerDTO)).map(financePartnerMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<FinancePartnerDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all FinancePartners");
        return financePartnerRepository.findAllByFilter(filter, pageable).map(financePartnerMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return financePartnerRepository.countByFilter(filter);
    }

    /**
     * Partially update a financePartner.
     *
     * @param financePartnerDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<FinancePartnerDTO> partialUpdate(FinancePartnerDTO financePartnerDTO) {
        log.debug("Request to partially update FinancePartner : {}", financePartnerDTO);

        return financePartnerRepository
            .findById(financePartnerDTO.getId())
            .map(existingFinancePartner -> {
                financePartnerMapper.partialUpdate(existingFinancePartner, financePartnerDTO);

                return existingFinancePartner;
            })
            .flatMap(financePartnerRepository::save)
            .map(financePartnerMapper::toDto);
    }

    /**
     * Get all the financePartners.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<FinancePartnerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinancePartners");
        return financePartnerRepository.findAllBy(pageable).map(financePartnerMapper::toDto);
    }

    /**
     * Returns the number of financePartners available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return financePartnerRepository.count();
    }

    /**
     * Get one financePartner by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<FinancePartnerDTO> findOne(Long id) {
        log.debug("Request to get FinancePartner : {}", id);
        return financePartnerRepository.findById(id).map(financePartnerMapper::toDto);
    }

    /**
     * Delete the financePartner by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FinancePartner : {}", id);
        return financePartnerRepository.deleteById(id);
    }
}
