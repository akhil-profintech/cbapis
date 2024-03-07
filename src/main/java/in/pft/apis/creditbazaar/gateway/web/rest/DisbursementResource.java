package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.DisbursementRepository;
import in.pft.apis.creditbazaar.gateway.service.DisbursementService;
import in.pft.apis.creditbazaar.gateway.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.Disbursement}.
 */
@RestController
@RequestMapping("/api/disbursements")
public class DisbursementResource {

    private final Logger log = LoggerFactory.getLogger(DisbursementResource.class);

    private static final String ENTITY_NAME = "disbursement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DisbursementService disbursementService;

    private final DisbursementRepository disbursementRepository;

    public DisbursementResource(DisbursementService disbursementService, DisbursementRepository disbursementRepository) {
        this.disbursementService = disbursementService;
        this.disbursementRepository = disbursementRepository;
    }

    /**
     * {@code POST  /disbursements} : Create a new disbursement.
     *
     * @param disbursementDTO the disbursementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new disbursementDTO, or with status {@code 400 (Bad Request)} if the disbursement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<DisbursementDTO>> createDisbursement(@Valid @RequestBody DisbursementDTO disbursementDTO)
        throws URISyntaxException {
        log.debug("REST request to save Disbursement : {}", disbursementDTO);
        if (disbursementDTO.getId() != null) {
            throw new BadRequestAlertException("A new disbursement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return disbursementService
            .save(disbursementDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/disbursements/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /disbursements/:id} : Updates an existing disbursement.
     *
     * @param id the id of the disbursementDTO to save.
     * @param disbursementDTO the disbursementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated disbursementDTO,
     * or with status {@code 400 (Bad Request)} if the disbursementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the disbursementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<DisbursementDTO>> updateDisbursement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DisbursementDTO disbursementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Disbursement : {}, {}", id, disbursementDTO);
        if (disbursementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, disbursementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return disbursementRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return disbursementService
                    .update(disbursementDTO)
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
     * {@code PATCH  /disbursements/:id} : Partial updates given fields of an existing disbursement, field will ignore if it is null
     *
     * @param id the id of the disbursementDTO to save.
     * @param disbursementDTO the disbursementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated disbursementDTO,
     * or with status {@code 400 (Bad Request)} if the disbursementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the disbursementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the disbursementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DisbursementDTO>> partialUpdateDisbursement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DisbursementDTO disbursementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Disbursement partially : {}, {}", id, disbursementDTO);
        if (disbursementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, disbursementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return disbursementRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DisbursementDTO> result = disbursementService.partialUpdate(disbursementDTO);

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
     * {@code GET  /disbursements} : get all the disbursements.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of disbursements in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<DisbursementDTO>>> getAllDisbursements(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Disbursements");
        if (StringUtils.isEmpty(filter)) {
        return disbursementService
            .countAll()
            .zipWith(disbursementService.findAll(pageable).collectList())
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
            );}
        else{
            return disbursementService
                .countAllByFilter(filter)
                .zipWith(disbursementService.findAllByFilter(filter, pageable).collectList())
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
    }

    /**
     * {@code GET  /disbursements/:id} : get the "id" disbursement.
     *
     * @param id the id of the disbursementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the disbursementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<DisbursementDTO>> getDisbursement(@PathVariable("id") Long id) {
        log.debug("REST request to get Disbursement : {}", id);
        Mono<DisbursementDTO> disbursementDTO = disbursementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(disbursementDTO);
    }

    /**
     * {@code DELETE  /disbursements/:id} : delete the "id" disbursement.
     *
     * @param id the id of the disbursementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteDisbursement(@PathVariable("id") Long id) {
        log.debug("REST request to delete Disbursement : {}", id);
        return disbursementService
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
