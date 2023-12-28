package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.ClientCodesRepository;
import in.pft.apis.creditbazaar.service.dto.ClientCodesDTO;
import in.pft.apis.creditbazaar.service.mapper.ClientCodesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.ClientCodes}.
 */
@Service
@Transactional
public class ClientCodesService {

    private final Logger log = LoggerFactory.getLogger(ClientCodesService.class);

    private final ClientCodesRepository clientCodesRepository;

    private final ClientCodesMapper clientCodesMapper;

    public ClientCodesService(ClientCodesRepository clientCodesRepository, ClientCodesMapper clientCodesMapper) {
        this.clientCodesRepository = clientCodesRepository;
        this.clientCodesMapper = clientCodesMapper;
    }

    /**
     * Save a clientCodes.
     *
     * @param clientCodesDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ClientCodesDTO> save(ClientCodesDTO clientCodesDTO) {
        log.debug("Request to save ClientCodes : {}", clientCodesDTO);
        return clientCodesRepository.save(clientCodesMapper.toEntity(clientCodesDTO)).map(clientCodesMapper::toDto);
    }

    /**
     * Update a clientCodes.
     *
     * @param clientCodesDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<ClientCodesDTO> update(ClientCodesDTO clientCodesDTO) {
        log.debug("Request to update ClientCodes : {}", clientCodesDTO);
        return clientCodesRepository.save(clientCodesMapper.toEntity(clientCodesDTO)).map(clientCodesMapper::toDto);
    }

    /**
     * Partially update a clientCodes.
     *
     * @param clientCodesDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<ClientCodesDTO> partialUpdate(ClientCodesDTO clientCodesDTO) {
        log.debug("Request to partially update ClientCodes : {}", clientCodesDTO);

        return clientCodesRepository
            .findById(clientCodesDTO.getId())
            .map(existingClientCodes -> {
                clientCodesMapper.partialUpdate(existingClientCodes, clientCodesDTO);

                return existingClientCodes;
            })
            .flatMap(clientCodesRepository::save)
            .map(clientCodesMapper::toDto);
    }

    /**
     * Get all the clientCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<ClientCodesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClientCodes");
        return clientCodesRepository.findAllBy(pageable).map(clientCodesMapper::toDto);
    }

    /**
     * Returns the number of clientCodes available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return clientCodesRepository.count();
    }

    /**
     * Get one clientCodes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<ClientCodesDTO> findOne(Long id) {
        log.debug("Request to get ClientCodes : {}", id);
        return clientCodesRepository.findById(id).map(clientCodesMapper::toDto);
    }

    /**
     * Delete the clientCodes by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete ClientCodes : {}", id);
        return clientCodesRepository.deleteById(id);
    }
}
