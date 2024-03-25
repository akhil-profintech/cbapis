package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.CBCREProcessRepository;
import in.pft.apis.creditbazaar.gateway.service.CBCREProcessService;
import in.pft.apis.creditbazaar.gateway.service.dto.CBCREProcessDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.CBCREProcess}.
 */
@RestController
@RequestMapping("/api/cbcre-processes")
public class CBCREProcessResource {

    private final Logger log = LoggerFactory.getLogger(CBCREProcessResource.class);

    private static final String ENTITY_NAME = "cBCREProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CBCREProcessService cBCREProcessService;

    private final CBCREProcessRepository cBCREProcessRepository;

    public CBCREProcessResource(CBCREProcessService cBCREProcessService, CBCREProcessRepository cBCREProcessRepository) {
        this.cBCREProcessService = cBCREProcessService;
        this.cBCREProcessRepository = cBCREProcessRepository;
    }

    /**
     * {@code POST  /cbcre-processes} : Create a new cBCREProcess.
     *
     * @param cBCREProcessDTO the cBCREProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cBCREProcessDTO, or with status {@code 400 (Bad Request)} if the cBCREProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CBCREProcessDTO>> createCBCREProcess(@RequestBody CBCREProcessDTO cBCREProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save CBCREProcess : {}", cBCREProcessDTO);
        if (cBCREProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new cBCREProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return cBCREProcessService
            .save(cBCREProcessDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/cbcre-processes/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /cbcre-processes/:id} : Updates an existing cBCREProcess.
     *
     * @param id the id of the cBCREProcessDTO to save.
     * @param cBCREProcessDTO the cBCREProcessDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cBCREProcessDTO,
     * or with status {@code 400 (Bad Request)} if the cBCREProcessDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cBCREProcessDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CBCREProcessDTO>> updateCBCREProcess(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CBCREProcessDTO cBCREProcessDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CBCREProcess : {}, {}", id, cBCREProcessDTO);
        if (cBCREProcessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cBCREProcessDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cBCREProcessRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return cBCREProcessService
                    .update(cBCREProcessDTO)
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
     * {@code PATCH  /cbcre-processes/:id} : Partial updates given fields of an existing cBCREProcess, field will ignore if it is null
     *
     * @param id the id of the cBCREProcessDTO to save.
     * @param cBCREProcessDTO the cBCREProcessDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cBCREProcessDTO,
     * or with status {@code 400 (Bad Request)} if the cBCREProcessDTO is not valid,
     * or with status {@code 404 (Not Found)} if the cBCREProcessDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the cBCREProcessDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CBCREProcessDTO>> partialUpdateCBCREProcess(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CBCREProcessDTO cBCREProcessDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CBCREProcess partially : {}, {}", id, cBCREProcessDTO);
        if (cBCREProcessDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cBCREProcessDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return cBCREProcessRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CBCREProcessDTO> result = cBCREProcessService.partialUpdate(cBCREProcessDTO);

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
     * {@code GET  /cbcre-processes} : get all the cBCREProcesses.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cBCREProcesses in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CBCREProcessDTO>>> getAllCBCREProcesses(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of CBCREProcesses");
        return cBCREProcessService
            .countAll()
            .zipWith(cBCREProcessService.findAll(pageable).collectList())
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
     * {@code GET  /cbcre-processes/:id} : get the "id" cBCREProcess.
     *
     * @param id the id of the cBCREProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cBCREProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CBCREProcessDTO>> getCBCREProcess(@PathVariable("id") Long id) {
        log.debug("REST request to get CBCREProcess : {}", id);
        Mono<CBCREProcessDTO> cBCREProcessDTO = cBCREProcessService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cBCREProcessDTO);
    }

    /**
     * {@code DELETE  /cbcre-processes/:id} : delete the "id" cBCREProcess.
     *
     * @param id the id of the cBCREProcessDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCBCREProcess(@PathVariable("id") Long id) {
        log.debug("REST request to delete CBCREProcess : {}", id);
        return cBCREProcessService
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
