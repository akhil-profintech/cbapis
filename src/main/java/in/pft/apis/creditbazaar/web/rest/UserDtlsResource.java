package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.UserDtlsRepository;
import in.pft.apis.creditbazaar.service.UserDtlsService;
import in.pft.apis.creditbazaar.service.dto.UserDtlsDTO;
import in.pft.apis.creditbazaar.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.ForwardedHeaderUtils;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.UserDtls}.
 */
@RestController
@RequestMapping("/api/user-dtls")
public class UserDtlsResource {

    private final Logger log = LoggerFactory.getLogger(UserDtlsResource.class);

    private static final String ENTITY_NAME = "userDtls";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserDtlsService userDtlsService;

    private final UserDtlsRepository userDtlsRepository;

    public UserDtlsResource(UserDtlsService userDtlsService, UserDtlsRepository userDtlsRepository) {
        this.userDtlsService = userDtlsService;
        this.userDtlsRepository = userDtlsRepository;
    }

    /**
     * {@code POST  /user-dtls} : Create a new userDtls.
     *
     * @param userDtlsDTO the userDtlsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDtlsDTO, or with status {@code 400 (Bad Request)} if the userDtls has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<UserDtlsDTO>> createUserDtls(@RequestBody UserDtlsDTO userDtlsDTO) throws URISyntaxException {
        log.debug("REST request to save UserDtls : {}", userDtlsDTO);
        if (userDtlsDTO.getId() != null) {
            throw new BadRequestAlertException("A new userDtls cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return userDtlsService
            .save(userDtlsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/user-dtls/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /user-dtls/:id} : Updates an existing userDtls.
     *
     * @param id the id of the userDtlsDTO to save.
     * @param userDtlsDTO the userDtlsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDtlsDTO,
     * or with status {@code 400 (Bad Request)} if the userDtlsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userDtlsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<UserDtlsDTO>> updateUserDtls(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserDtlsDTO userDtlsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update UserDtls : {}, {}", id, userDtlsDTO);
        if (userDtlsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userDtlsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return userDtlsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return userDtlsService
                    .update(userDtlsDTO)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /user-dtls/:id} : Partial updates given fields of an existing userDtls, field will ignore if it is null
     *
     * @param id the id of the userDtlsDTO to save.
     * @param userDtlsDTO the userDtlsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userDtlsDTO,
     * or with status {@code 400 (Bad Request)} if the userDtlsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the userDtlsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the userDtlsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<UserDtlsDTO>> partialUpdateUserDtls(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserDtlsDTO userDtlsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserDtls partially : {}, {}", id, userDtlsDTO);
        if (userDtlsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userDtlsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return userDtlsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<UserDtlsDTO> result = userDtlsService.partialUpdate(userDtlsDTO);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /user-dtls} : get all the userDtls.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userDtls in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<UserDtlsDTO>>> getAllUserDtls(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of UserDtls");
        return userDtlsService
            .countAll()
            .zipWith(userDtlsService.findAll(pageable).collectList())
            .map(countWithEntities ->
                ResponseEntity
                    .ok()
                    .headers(
                        PaginationUtil.generatePaginationHttpHeaders(
                            ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                            new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                        )
                    )
                    .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /user-dtls/:id} : get the "id" userDtls.
     *
     * @param id the id of the userDtlsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDtlsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<UserDtlsDTO>> getUserDtls(@PathVariable("id") Long id) {
        log.debug("REST request to get UserDtls : {}", id);
        Mono<UserDtlsDTO> userDtlsDTO = userDtlsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDtlsDTO);
    }

    /**
     * {@code DELETE  /user-dtls/:id} : delete the "id" userDtls.
     *
     * @param id the id of the userDtlsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUserDtls(@PathVariable("id") Long id) {
        log.debug("REST request to delete UserDtls : {}", id);
        return userDtlsService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }
}
