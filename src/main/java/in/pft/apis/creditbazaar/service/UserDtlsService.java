package in.pft.apis.creditbazaar.service;

import in.pft.apis.creditbazaar.repository.UserDtlsRepository;
import in.pft.apis.creditbazaar.service.dto.UserDtlsDTO;
import in.pft.apis.creditbazaar.service.mapper.UserDtlsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link in.pft.apis.creditbazaar.domain.UserDtls}.
 */
@Service
@Transactional
public class UserDtlsService {

    private final Logger log = LoggerFactory.getLogger(UserDtlsService.class);

    private final UserDtlsRepository userDtlsRepository;

    private final UserDtlsMapper userDtlsMapper;

    public UserDtlsService(UserDtlsRepository userDtlsRepository, UserDtlsMapper userDtlsMapper) {
        this.userDtlsRepository = userDtlsRepository;
        this.userDtlsMapper = userDtlsMapper;
    }

    /**
     * Save a userDtls.
     *
     * @param userDtlsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UserDtlsDTO> save(UserDtlsDTO userDtlsDTO) {
        log.debug("Request to save UserDtls : {}", userDtlsDTO);
        return userDtlsRepository.save(userDtlsMapper.toEntity(userDtlsDTO)).map(userDtlsMapper::toDto);
    }

    /**
     * Update a userDtls.
     *
     * @param userDtlsDTO the entity to save.
     * @return the persisted entity.
     */
    public Mono<UserDtlsDTO> update(UserDtlsDTO userDtlsDTO) {
        log.debug("Request to update UserDtls : {}", userDtlsDTO);
        return userDtlsRepository.save(userDtlsMapper.toEntity(userDtlsDTO)).map(userDtlsMapper::toDto);
    }

    /**
     * Partially update a userDtls.
     *
     * @param userDtlsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<UserDtlsDTO> partialUpdate(UserDtlsDTO userDtlsDTO) {
        log.debug("Request to partially update UserDtls : {}", userDtlsDTO);

        return userDtlsRepository
            .findById(userDtlsDTO.getId())
            .map(existingUserDtls -> {
                userDtlsMapper.partialUpdate(existingUserDtls, userDtlsDTO);

                return existingUserDtls;
            })
            .flatMap(userDtlsRepository::save)
            .map(userDtlsMapper::toDto);
    }

    /**
     * Get all the userDtls.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Flux<UserDtlsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserDtls");
        return userDtlsRepository.findAllBy(pageable).map(userDtlsMapper::toDto);
    }

    /**
     * Get all the userDtls with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Flux<UserDtlsDTO> findAllWithEagerRelationships(Pageable pageable) {
        return userDtlsRepository.findAllWithEagerRelationships(pageable).map(userDtlsMapper::toDto);
    }

    /**
     * Returns the number of userDtls available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return userDtlsRepository.count();
    }

    /**
     * Get one userDtls by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Mono<UserDtlsDTO> findOne(Long id) {
        log.debug("Request to get UserDtls : {}", id);
        return userDtlsRepository.findOneWithEagerRelationships(id).map(userDtlsMapper::toDto);
    }

    /**
     * Delete the userDtls by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(Long id) {
        log.debug("Request to delete UserDtls : {}", id);
        return userDtlsRepository.deleteById(id);
    }
}
