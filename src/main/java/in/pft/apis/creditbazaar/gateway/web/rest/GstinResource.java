package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.GstinRepository;
import in.pft.apis.creditbazaar.gateway.service.GstinService;
import in.pft.apis.creditbazaar.gateway.service.dto.GstinDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.Gstin}.
 */
@RestController
@RequestMapping("/api/gstins")
public class GstinResource {

    private final Logger log = LoggerFactory.getLogger(GstinResource.class);

    private static final String ENTITY_NAME = "gstin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GstinService gstinService;

    private final GstinRepository gstinRepository;

    public GstinResource(GstinService gstinService, GstinRepository gstinRepository) {
        this.gstinService = gstinService;
        this.gstinRepository = gstinRepository;
    }

    /**
     * {@code POST  /gstins} : Create a new gstin.
     *
     * @param gstinDTO the gstinDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gstinDTO, or with status {@code 400 (Bad Request)} if the gstin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<GstinDTO>> createGstin(@Valid @RequestBody GstinDTO gstinDTO) throws URISyntaxException {
        log.debug("REST request to save Gstin : {}", gstinDTO);
        if (gstinDTO.getId() != null) {
            throw new BadRequestAlertException("A new gstin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return gstinService
            .save(gstinDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/gstins/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /gstins/:id} : Updates an existing gstin.
     *
     * @param id the id of the gstinDTO to save.
     * @param gstinDTO the gstinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gstinDTO,
     * or with status {@code 400 (Bad Request)} if the gstinDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gstinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<GstinDTO>> updateGstin(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GstinDTO gstinDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Gstin : {}, {}", id, gstinDTO);
        if (gstinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gstinDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return gstinRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return gstinService
                    .update(gstinDTO)
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
     * {@code PATCH  /gstins/:id} : Partial updates given fields of an existing gstin, field will ignore if it is null
     *
     * @param id the id of the gstinDTO to save.
     * @param gstinDTO the gstinDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gstinDTO,
     * or with status {@code 400 (Bad Request)} if the gstinDTO is not valid,
     * or with status {@code 404 (Not Found)} if the gstinDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the gstinDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<GstinDTO>> partialUpdateGstin(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GstinDTO gstinDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Gstin partially : {}, {}", id, gstinDTO);
        if (gstinDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, gstinDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return gstinRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<GstinDTO> result = gstinService.partialUpdate(gstinDTO);

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
     * {@code GET  /gstins} : get all the gstins.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gstins in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<GstinDTO>>> getAllGstins(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Gstins");
        return gstinService
            .countAll()
            .zipWith(gstinService.findAll(pageable).collectList())
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
     * {@code GET  /gstins/:id} : get the "id" gstin.
     *
     * @param id the id of the gstinDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gstinDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<GstinDTO>> getGstin(@PathVariable("id") Long id) {
        log.debug("REST request to get Gstin : {}", id);
        Mono<GstinDTO> gstinDTO = gstinService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gstinDTO);
    }

    /**
     * {@code DELETE  /gstins/:id} : delete the "id" gstin.
     *
     * @param id the id of the gstinDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteGstin(@PathVariable("id") Long id) {
        log.debug("REST request to delete Gstin : {}", id);
        return gstinService
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
