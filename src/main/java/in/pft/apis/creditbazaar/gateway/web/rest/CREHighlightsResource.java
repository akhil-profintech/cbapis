package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.CREHighlightsRepository;
import in.pft.apis.creditbazaar.gateway.service.CREHighlightsService;
import in.pft.apis.creditbazaar.gateway.service.dto.CREHighlightsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.CREHighlights}.
 */
@RestController
@RequestMapping("/api/cre-highlights")
public class CREHighlightsResource {

    private final Logger log = LoggerFactory.getLogger(CREHighlightsResource.class);

    private static final String ENTITY_NAME = "cREHighlights";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CREHighlightsService cREHighlightsService;

    private final CREHighlightsRepository cREHighlightsRepository;

    public CREHighlightsResource(CREHighlightsService cREHighlightsService, CREHighlightsRepository cREHighlightsRepository) {
        this.cREHighlightsService = cREHighlightsService;
        this.cREHighlightsRepository = cREHighlightsRepository;
    }

    /**
     * {@code POST  /cre-highlights} : Create a new cREHighlights.
     *
     * @param cREHighlightsDTO the cREHighlightsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cREHighlightsDTO, or with status {@code 400 (Bad Request)} if the cREHighlights has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CREHighlightsDTO>> createCREHighlights(@RequestBody CREHighlightsDTO cREHighlightsDTO)
        throws URISyntaxException {
        log.debug("REST request to save CREHighlights : {}", cREHighlightsDTO);
        if (cREHighlightsDTO.getId() != null) {
            throw new BadRequestAlertException("A new cREHighlights cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return cREHighlightsService
            .save(cREHighlightsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/cre-highlights/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /cre-highlights/:id} : Updates an existing cREHighlights.
     *
     * @param id the id of the cREHighlightsDTO to save.
     * @param cREHighlightsDTO the cREHighlightsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cREHighlightsDTO,
     * or with status {@code 400 (Bad Request)} if the cREHighlightsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cREHighlightsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CREHighlightsDTO>> updateCREHighlights(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CREHighlightsDTO cREHighlightsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CREHighlights : {}, {}", id, cREHighlightsDTO);
        if (cREHighlightsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cREHighlightsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cREHighlightsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return cREHighlightsService
                    .update(cREHighlightsDTO)
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
     * {@code PATCH  /cre-highlights/:id} : Partial updates given fields of an existing cREHighlights, field will ignore if it is null
     *
     * @param id the id of the cREHighlightsDTO to save.
     * @param cREHighlightsDTO the cREHighlightsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cREHighlightsDTO,
     * or with status {@code 400 (Bad Request)} if the cREHighlightsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cREHighlightsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cREHighlightsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CREHighlightsDTO>> partialUpdateCREHighlights(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CREHighlightsDTO cREHighlightsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CREHighlights partially : {}, {}", id, cREHighlightsDTO);
        if (cREHighlightsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cREHighlightsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cREHighlightsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CREHighlightsDTO> result = cREHighlightsService.partialUpdate(cREHighlightsDTO);

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
     * {@code GET  /cre-highlights} : get all the cREHighlights.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cREHighlights in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CREHighlightsDTO>>> getAllCREHighlights(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of CREHighlights");
        return cREHighlightsService
            .countAll()
            .zipWith(cREHighlightsService.findAll(pageable).collectList())
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
     * {@code GET  /cre-highlights/:id} : get the "id" cREHighlights.
     *
     * @param id the id of the cREHighlightsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cREHighlightsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CREHighlightsDTO>> getCREHighlights(@PathVariable("id") Long id) {
        log.debug("REST request to get CREHighlights : {}", id);
        Mono<CREHighlightsDTO> cREHighlightsDTO = cREHighlightsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cREHighlightsDTO);
    }

    /**
     * {@code DELETE  /cre-highlights/:id} : delete the "id" cREHighlights.
     *
     * @param id the id of the cREHighlightsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCREHighlights(@PathVariable("id") Long id) {
        log.debug("REST request to delete CREHighlights : {}", id);
        return cREHighlightsService
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
