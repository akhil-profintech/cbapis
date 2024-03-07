package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.CREObservationsRepository;
import in.pft.apis.creditbazaar.gateway.service.CREObservationsService;
import in.pft.apis.creditbazaar.gateway.service.dto.CREObservationsDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.CREObservations}.
 */
@RestController
@RequestMapping("/api/cre-observations")
public class CREObservationsResource {

    private final Logger log = LoggerFactory.getLogger(CREObservationsResource.class);

    private static final String ENTITY_NAME = "cREObservations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CREObservationsService cREObservationsService;

    private final CREObservationsRepository cREObservationsRepository;

    public CREObservationsResource(CREObservationsService cREObservationsService, CREObservationsRepository cREObservationsRepository) {
        this.cREObservationsService = cREObservationsService;
        this.cREObservationsRepository = cREObservationsRepository;
    }

    /**
     * {@code POST  /cre-observations} : Create a new cREObservations.
     *
     * @param cREObservationsDTO the cREObservationsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cREObservationsDTO, or with status {@code 400 (Bad Request)} if the cREObservations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CREObservationsDTO>> createCREObservations(@RequestBody CREObservationsDTO cREObservationsDTO)
        throws URISyntaxException {
        log.debug("REST request to save CREObservations : {}", cREObservationsDTO);
        if (cREObservationsDTO.getId() != null) {
            throw new BadRequestAlertException("A new cREObservations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return cREObservationsService
            .save(cREObservationsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/cre-observations/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /cre-observations/:id} : Updates an existing cREObservations.
     *
     * @param id the id of the cREObservationsDTO to save.
     * @param cREObservationsDTO the cREObservationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cREObservationsDTO,
     * or with status {@code 400 (Bad Request)} if the cREObservationsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cREObservationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CREObservationsDTO>> updateCREObservations(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CREObservationsDTO cREObservationsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CREObservations : {}, {}", id, cREObservationsDTO);
        if (cREObservationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cREObservationsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cREObservationsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return cREObservationsService
                    .update(cREObservationsDTO)
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
     * {@code PATCH  /cre-observations/:id} : Partial updates given fields of an existing cREObservations, field will ignore if it is null
     *
     * @param id the id of the cREObservationsDTO to save.
     * @param cREObservationsDTO the cREObservationsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cREObservationsDTO,
     * or with status {@code 400 (Bad Request)} if the cREObservationsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cREObservationsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cREObservationsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CREObservationsDTO>> partialUpdateCREObservations(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CREObservationsDTO cREObservationsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CREObservations partially : {}, {}", id, cREObservationsDTO);
        if (cREObservationsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cREObservationsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cREObservationsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CREObservationsDTO> result = cREObservationsService.partialUpdate(cREObservationsDTO);

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
     * {@code GET  /cre-observations} : get all the cREObservations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cREObservations in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CREObservationsDTO>>> getAllCREObservations(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of CREObservations");
        return cREObservationsService
            .countAll()
            .zipWith(cREObservationsService.findAll(pageable).collectList())
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
     * {@code GET  /cre-observations/:id} : get the "id" cREObservations.
     *
     * @param id the id of the cREObservationsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cREObservationsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CREObservationsDTO>> getCREObservations(@PathVariable("id") Long id) {
        log.debug("REST request to get CREObservations : {}", id);
        Mono<CREObservationsDTO> cREObservationsDTO = cREObservationsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cREObservationsDTO);
    }

    /**
     * {@code DELETE  /cre-observations/:id} : delete the "id" cREObservations.
     *
     * @param id the id of the cREObservationsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCREObservations(@PathVariable("id") Long id) {
        log.debug("REST request to delete CREObservations : {}", id);
        return cREObservationsService
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
