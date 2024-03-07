package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.RequestOfferRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.RequestOfferDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.RequestOfferMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.RequestOffer}.
 */
@Service
@Transactional
public class RequestOfferService {

    private final Logger log = LoggerFactory.getLogger(RequestOfferService.class);

    private final RequestOfferRepository requestOfferRepository;

    private final RequestOfferMapper requestOfferMapper;

    public RequestOfferService(RequestOfferRepository requestOfferRepository, RequestOfferMapper requestOfferMapper) {
        this.requestOfferRepository = requestOfferRepository;
        this.requestOfferMapper = requestOfferMapper;
    }

    /**
     * Save a requestOffer.
     *
     * @param requestOfferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<RequestOfferDTO> save(RequestOfferDTO requestOfferDTO) {
        log.debug("Request to save RequestOffer : {}", requestOfferDTO);
        requestOfferDTO.setReqOffUlidId(IdAndUlidGeneration.generateUlid());
        return requestOfferRepository.save(requestOfferMapper.toEntity(requestOfferDTO))
            .flatMap(savedEntity->{
                requestOfferDTO.setReqOfferRefNo("ROCR-PBY-FRCR-PTS-"+savedEntity.getId());
                    return requestOfferRepository.findById(savedEntity.getId())
                        .flatMap(existingEntity->{
                            existingEntity.setReqOfferRefNo(requestOfferDTO.getReqOfferRefNo());
                            return requestOfferRepository.save(existingEntity)
                                .map(requestOfferMapper::toDto);
                        });
            });
    }

    /**
     * Update a requestOffer.
     *
     * @param requestOfferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<RequestOfferDTO> update(RequestOfferDTO requestOfferDTO) {
        log.debug("Request to update RequestOffer : {}", requestOfferDTO);
        return requestOfferRepository.save(requestOfferMapper.toEntity(requestOfferDTO)).map(requestOfferMapper::toDto);
    }

    /**
     * Partially update a requestOffer.
     *
     * @param requestOfferDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<RequestOfferDTO> partialUpdate(RequestOfferDTO requestOfferDTO) {
        log.debug("Request to partially update RequestOffer : {}", requestOfferDTO);

        return requestOfferRepository
            .findById(requestOfferDTO.getId())
            .map(existingRequestOffer -> {
                requestOfferMapper.partialUpdate(existingRequestOffer, requestOfferDTO);

                return existingRequestOffer;
            })
            .flatMap(requestOfferRepository::save)
            .map(requestOfferMapper::toDto);
    }

    /**
     * Get all the requestOffers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<RequestOfferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestOffers");
        return requestOfferRepository.findAllBy(pageable).map(requestOfferMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<RequestOfferDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return requestOfferRepository.findAllByFilter(filter, pageable).map(requestOfferMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return requestOfferRepository.countByFilter(filter);
    }

    /**
     * Get all the requestOffers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<RequestOfferDTO> findAllWithEagerRelationships(Pageable pageable) {
        return requestOfferRepository.findAllWithEagerRelationships(pageable).map(requestOfferMapper::toDto);
    }

    /**
     * Returns the number of requestOffers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return requestOfferRepository.count();
    }

    /**
     * Get one requestOffer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<RequestOfferDTO> findOne(Long id) {
        log.debug("Request to get RequestOffer : {}", id);
        return requestOfferRepository.findOneWithEagerRelationships(id).map(requestOfferMapper::toDto);
    }

    /**
     * Delete the requestOffer by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete RequestOffer : {}", id);
        return requestOfferRepository.deleteById(id);
    }
}
