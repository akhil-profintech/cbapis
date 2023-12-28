package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.ProspectRequestRepository;
import in.pft.apis.creditbazaar.service.ProspectRequestService;
import in.pft.apis.creditbazaar.service.dto.ProspectRequestDTO;
import in.pft.apis.creditbazaar.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.ProspectRequest}.
 */
@RestController
@RequestMapping("/api/prospect-requests")
public class ProspectRequestResource {

    private final Logger log = LoggerFactory.getLogger(ProspectRequestResource.class);

    private static final String ENTITY_NAME = "prospectRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProspectRequestService prospectRequestService;

    private final ProspectRequestRepository prospectRequestRepository;

    public ProspectRequestResource(ProspectRequestService prospectRequestService, ProspectRequestRepository prospectRequestRepository) {
        this.prospectRequestService = prospectRequestService;
        this.prospectRequestRepository = prospectRequestRepository;
    }

    /**
     * {@code POST  /prospect-requests} : Create a new prospectRequest.
     *
     * @param prospectRequestDTO the prospectRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prospectRequestDTO, or with status {@code 400 (Bad Request)} if the prospectRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ProspectRequestDTO>> createProspectRequest(@Valid @RequestBody ProspectRequestDTO prospectRequestDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProspectRequest : {}", prospectRequestDTO);
        if (prospectRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new prospectRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return prospectRequestService
            .save(prospectRequestDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/prospect-requests/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /prospect-requests/:id} : Updates an existing prospectRequest.
     *
     * @param id the id of the prospectRequestDTO to save.
     * @param prospectRequestDTO the prospectRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prospectRequestDTO,
     * or with status {@code 400 (Bad Request)} if the prospectRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prospectRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ProspectRequestDTO>> updateProspectRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProspectRequestDTO prospectRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProspectRequest : {}, {}", id, prospectRequestDTO);
        if (prospectRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prospectRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return prospectRequestRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return prospectRequestService
                    .update(prospectRequestDTO)
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
     * {@code PATCH  /prospect-requests/:id} : Partial updates given fields of an existing prospectRequest, field will ignore if it is null
     *
     * @param id the id of the prospectRequestDTO to save.
     * @param prospectRequestDTO the prospectRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prospectRequestDTO,
     * or with status {@code 400 (Bad Request)} if the prospectRequestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the prospectRequestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the prospectRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ProspectRequestDTO>> partialUpdateProspectRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProspectRequestDTO prospectRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProspectRequest partially : {}, {}", id, prospectRequestDTO);
        if (prospectRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, prospectRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return prospectRequestRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ProspectRequestDTO> result = prospectRequestService.partialUpdate(prospectRequestDTO);

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
     * {@code GET  /prospect-requests} : get all the prospectRequests.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prospectRequests in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ProspectRequestDTO>>> getAllProspectRequests(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ProspectRequests");
        return prospectRequestService
            .countAll()
            .zipWith(prospectRequestService.findAll(pageable).collectList())
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
     * {@code GET  /prospect-requests/:id} : get the "id" prospectRequest.
     *
     * @param id the id of the prospectRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prospectRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ProspectRequestDTO>> getProspectRequest(@PathVariable("id") Long id) {
        log.debug("REST request to get ProspectRequest : {}", id);
        Mono<ProspectRequestDTO> prospectRequestDTO = prospectRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prospectRequestDTO);
    }

    /**
     * {@code DELETE  /prospect-requests/:id} : delete the "id" prospectRequest.
     *
     * @param id the id of the prospectRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteProspectRequest(@PathVariable("id") Long id) {
        log.debug("REST request to delete ProspectRequest : {}", id);
        return prospectRequestService
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
