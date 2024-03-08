package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.RepaymentRepository;
import in.pft.apis.creditbazaar.gateway.service.RepaymentService;
import in.pft.apis.creditbazaar.gateway.service.dto.RepaymentDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.Repayment}.
 */
@RestController
@RequestMapping("/api/repayments")
public class RepaymentResource {

    private final Logger log = LoggerFactory.getLogger(RepaymentResource.class);

    private static final String ENTITY_NAME = "repayment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RepaymentService repaymentService;

    private final RepaymentRepository repaymentRepository;

    public RepaymentResource(RepaymentService repaymentService, RepaymentRepository repaymentRepository) {
        this.repaymentService = repaymentService;
        this.repaymentRepository = repaymentRepository;
    }

    /**
     * {@code POST  /repayments} : Create a new repayment.
     *
     * @param repaymentDTO the repaymentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new repaymentDTO, or with status {@code 400 (Bad Request)} if the repayment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<RepaymentDTO>> createRepayment(@Valid @RequestBody RepaymentDTO repaymentDTO) throws URISyntaxException {
        log.debug("REST request to save Repayment : {}", repaymentDTO);
        if (repaymentDTO.getId() != null) {
            throw new BadRequestAlertException("A new repayment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return repaymentService
            .save(repaymentDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/repayments/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /repayments/:id} : Updates an existing repayment.
     *
     * @param id the id of the repaymentDTO to save.
     * @param repaymentDTO the repaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated repaymentDTO,
     * or with status {@code 400 (Bad Request)} if the repaymentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the repaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<RepaymentDTO>> updateRepayment(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RepaymentDTO repaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Repayment : {}, {}", id, repaymentDTO);
        if (repaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, repaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return repaymentRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return repaymentService
                    .update(repaymentDTO)
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
     * {@code PATCH  /repayments/:id} : Partial updates given fields of an existing repayment, field will ignore if it is null
     *
     * @param id the id of the repaymentDTO to save.
     * @param repaymentDTO the repaymentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated repaymentDTO,
     * or with status {@code 400 (Bad Request)} if the repaymentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the repaymentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the repaymentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<RepaymentDTO>> partialUpdateRepayment(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RepaymentDTO repaymentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Repayment partially : {}, {}", id, repaymentDTO);
        if (repaymentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, repaymentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return repaymentRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<RepaymentDTO> result = repaymentService.partialUpdate(repaymentDTO);

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
     * {@code GET  /repayments} : get all the repayments.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of repayments in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<RepaymentDTO>>> getAllRepayments(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Repayments");
        if (StringUtils.isEmpty(filter)) {
        return repaymentService
            .countAll()
            .zipWith(repaymentService.findAll(pageable).collectList())
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
            return repaymentService
                .countAllByFilter(filter)
                .zipWith(repaymentService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /repayments/:id} : get the "id" repayment.
     *
     * @param id the id of the repaymentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the repaymentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<RepaymentDTO>> getRepayment(@PathVariable("id") Long id) {
        log.debug("REST request to get Repayment : {}", id);
        Mono<RepaymentDTO> repaymentDTO = repaymentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(repaymentDTO);
    }

    /**
     * {@code DELETE  /repayments/:id} : delete the "id" repayment.
     *
     * @param id the id of the repaymentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteRepayment(@PathVariable("id") Long id) {
        log.debug("REST request to delete Repayment : {}", id);
        return repaymentService
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
