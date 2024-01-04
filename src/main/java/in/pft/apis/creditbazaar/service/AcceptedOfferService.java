package in.pft.apis.creditbazaar.service;

import de.huxhorn.sulky.ulid.ULID;
import in.pft.apis.creditbazaar.repository.AcceptedOfferRepository;
import in.pft.apis.creditbazaar.service.dto.AcceptedOfferDTO;
import in.pft.apis.creditbazaar.service.mapper.AcceptedOfferMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.AcceptedOffer}.
 */
@Service
@Transactional
public class AcceptedOfferService {

    private final Logger log = LoggerFactory.getLogger(AcceptedOfferService.class);

    private final AcceptedOfferRepository acceptedOfferRepository;

    private final AcceptedOfferMapper acceptedOfferMapper;

    private static final ULID ulid=new ULID();

    public AcceptedOfferService(AcceptedOfferRepository acceptedOfferRepository, AcceptedOfferMapper acceptedOfferMapper) {
        this.acceptedOfferRepository = acceptedOfferRepository;
        this.acceptedOfferMapper = acceptedOfferMapper;
    }

    public String generateUlid()
    {
        return ulid.nextULID();
    }

    /**
     * Save a acceptedOffer.
     *
     * @param acceptedOfferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AcceptedOfferDTO> save(AcceptedOfferDTO acceptedOfferDTO) {
        log.debug("Request to save AcceptedOffer : {}", acceptedOfferDTO);

        return acceptedOfferRepository
            .save(acceptedOfferMapper.toEntity(acceptedOfferDTO))
            .flatMap(savedEntity->{

                acceptedOfferDTO.setOfferId(generateUlid());
                acceptedOfferDTO.setAcceptedOfferRefNo("OFAD-IKF-"+savedEntity.getId());

               return acceptedOfferRepository.findById(savedEntity.getId())
                   .flatMap(existingEntity->{

                   existingEntity.setOfferId(acceptedOfferDTO.getOfferId());
                   existingEntity.setAcceptedOfferRefNo(acceptedOfferDTO.getAcceptedOfferRefNo());

                   return acceptedOfferRepository.save(existingEntity).map(acceptedOfferMapper::toDto);
                   });
            });
    }

    /**
     * Update a acceptedOffer.
     *
     * @param acceptedOfferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<AcceptedOfferDTO> update(AcceptedOfferDTO acceptedOfferDTO) {
        log.debug("Request to update AcceptedOffer : {}", acceptedOfferDTO);
        return acceptedOfferRepository.save(acceptedOfferMapper.toEntity(acceptedOfferDTO)).map(acceptedOfferMapper::toDto);
    }

    /**
     * Partially update a acceptedOffer.
     *
     * @param acceptedOfferDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<AcceptedOfferDTO> partialUpdate(AcceptedOfferDTO acceptedOfferDTO) {
        log.debug("Request to partially update AcceptedOffer : {}", acceptedOfferDTO);

        return acceptedOfferRepository
            .findById(acceptedOfferDTO.getId())
            .map(existingAcceptedOffer -> {
                acceptedOfferMapper.partialUpdate(existingAcceptedOffer, acceptedOfferDTO);

                return existingAcceptedOffer;
            })
            .flatMap(acceptedOfferRepository::save)
            .map(acceptedOfferMapper::toDto);
    }

    /**
     * Get all the acceptedOffers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<AcceptedOfferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AcceptedOffers");
        return acceptedOfferRepository.findAllBy(pageable).map(acceptedOfferMapper::toDto);
    }

    /**
     * Get all the acceptedOffers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<AcceptedOfferDTO> findAllWithEagerRelationships(Pageable pageable) {
        return acceptedOfferRepository.findAllWithEagerRelationships(pageable).map(acceptedOfferMapper::toDto);
    }

    /**
     * Returns the number of acceptedOffers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return acceptedOfferRepository.count();
    }

    /**
     * Get one acceptedOffer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<AcceptedOfferDTO> findOne(Long id) {
        log.debug("Request to get AcceptedOffer : {}", id);
        return acceptedOfferRepository.findOneWithEagerRelationships(id).map(acceptedOfferMapper::toDto);
    }

    /**
     * Delete the acceptedOffer by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete AcceptedOffer : {}", id);
        return acceptedOfferRepository.deleteById(id);
    }
}
