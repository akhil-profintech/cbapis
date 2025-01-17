package in.pft.apis.creditbazaar.gateway.service;

import in.pft.apis.creditbazaar.gateway.repository.FundsTransferRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FundsTransferMapper;
import in.pft.apis.creditbazaar.gateway.utils.IdAndUlidGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.gateway.domain.FundsTransfer}.
 */
@Service
@Transactional
public class FundsTransferService {

    private final Logger log = LoggerFactory.getLogger(FundsTransferService.class);

    private final FundsTransferRepository fundsTransferRepository;

    private final FundsTransferMapper fundsTransferMapper;

    public FundsTransferService(FundsTransferRepository fundsTransferRepository, FundsTransferMapper fundsTransferMapper) {
        this.fundsTransferRepository = fundsTransferRepository;
        this.fundsTransferMapper = fundsTransferMapper;
    }

    /**
     * Save a fundsTransfer.
     *
     * @param fundsTransferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FundsTransferDTO> save(FundsTransferDTO fundsTransferDTO) {
        log.debug("Request to save FundsTransfer : {}", fundsTransferDTO);
        fundsTransferDTO.setFundsTransferId(IdAndUlidGeneration.generateUlid());
        return fundsTransferRepository.save(fundsTransferMapper.toEntity(fundsTransferDTO))
            .flatMap(savedEntity->{
                fundsTransferDTO.setFundsTransferRefNo("STCD-PFT-RNH-PTS-PBY-FRCR-PTS-"+savedEntity.getId());
                    return fundsTransferRepository.findById(savedEntity.getId())
                        .flatMap(existingEntity->{
                            existingEntity.setFundsTransferRefNo(fundsTransferDTO.getFundsTransferRefNo());
                            return fundsTransferRepository.save(existingEntity).map(fundsTransferMapper::toDto);
                        });
            });
    }

    /**
     * Update a fundsTransfer.
     *
     * @param fundsTransferDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<FundsTransferDTO> update(FundsTransferDTO fundsTransferDTO) {
        log.debug("Request to update FundsTransfer : {}", fundsTransferDTO);
        return fundsTransferRepository.save(fundsTransferMapper.toEntity(fundsTransferDTO)).map(fundsTransferMapper::toDto);
    }

    /**
     * Partially update a fundsTransfer.
     *
     * @param fundsTransferDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<FundsTransferDTO> partialUpdate(FundsTransferDTO fundsTransferDTO) {
        log.debug("Request to partially update FundsTransfer : {}", fundsTransferDTO);

        return fundsTransferRepository
            .findById(fundsTransferDTO.getId())
            .map(existingFundsTransfer -> {
                fundsTransferMapper.partialUpdate(existingFundsTransfer, fundsTransferDTO);

                return existingFundsTransfer;
            })
            .flatMap(fundsTransferRepository::save)
            .map(fundsTransferMapper::toDto);
    }

    /**
     * Get all the fundsTransfers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<FundsTransferDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FundsTransfers");
        return fundsTransferRepository.findAllBy(pageable).map(fundsTransferMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Flux<FundsTransferDTO> findAllByFilter(String filter, Pageable pageable) {
        log.debug("Request to get all AnchorTraders by filter");
        return fundsTransferRepository.findAllByFilter(filter, pageable).map(fundsTransferMapper::toDto);
    }

    public Mono<Long> countAllByFilter(String filter) {
        return fundsTransferRepository.countByFilter(filter);
    }

    /**
     * Get all the fundsTransfers with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<FundsTransferDTO> findAllWithEagerRelationships(Pageable pageable) {
        return fundsTransferRepository.findAllWithEagerRelationships(pageable).map(fundsTransferMapper::toDto);
    }

    /**
     * Returns the number of fundsTransfers available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return fundsTransferRepository.count();
    }

    /**
     * Get one fundsTransfer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<FundsTransferDTO> findOne(Long id) {
        log.debug("Request to get FundsTransfer : {}", id);
        return fundsTransferRepository.findOneWithEagerRelationships(id).map(fundsTransferMapper::toDto);
    }

    /**
     * Delete the fundsTransfer by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete FundsTransfer : {}", id);
        return fundsTransferRepository.deleteById(id);
    }
}
