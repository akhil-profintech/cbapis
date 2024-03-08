package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.FinanceRequestRepository;
import in.pft.apis.creditbazaar.gateway.service.FinanceRequestService;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.FinanceRequest}.
 */
@RestController
@RequestMapping("/api/finance-requests")
public class FinanceRequestResource {

    private final Logger log = LoggerFactory.getLogger(FinanceRequestResource.class);

    private static final String ENTITY_NAME = "financeRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinanceRequestService financeRequestService;

    private final FinanceRequestRepository financeRequestRepository;

    public FinanceRequestResource(FinanceRequestService financeRequestService, FinanceRequestRepository financeRequestRepository) {
        this.financeRequestService = financeRequestService;
        this.financeRequestRepository = financeRequestRepository;
    }

    /**
     * {@code POST  /finance-requests} : Create a new financeRequest.
     *
     * @param financeRequestDTO the financeRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financeRequestDTO, or with status {@code 400 (Bad Request)} if the financeRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FinanceRequestDTO>> createFinanceRequest(@Valid @RequestBody FinanceRequestDTO financeRequestDTO)
        throws URISyntaxException {
        log.debug("REST request to save FinanceRequest : {}", financeRequestDTO);
        if (financeRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new financeRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return financeRequestService
            .save(financeRequestDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/finance-requests/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /finance-requests/:id} : Updates an existing financeRequest.
     *
     * @param id the id of the financeRequestDTO to save.
     * @param financeRequestDTO the financeRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financeRequestDTO,
     * or with status {@code 400 (Bad Request)} if the financeRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financeRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FinanceRequestDTO>> updateFinanceRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FinanceRequestDTO financeRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FinanceRequest : {}, {}", id, financeRequestDTO);
        if (financeRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financeRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return financeRequestRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return financeRequestService
                    .update(financeRequestDTO)
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
     * {@code PATCH  /finance-requests/:id} : Partial updates given fields of an existing financeRequest, field will ignore if it is null
     *
     * @param id the id of the financeRequestDTO to save.
     * @param financeRequestDTO the financeRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financeRequestDTO,
     * or with status {@code 400 (Bad Request)} if the financeRequestDTO is not valid,
     * or with status {@code 404 (Not Found)} if the financeRequestDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the financeRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FinanceRequestDTO>> partialUpdateFinanceRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FinanceRequestDTO financeRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FinanceRequest partially : {}, {}", id, financeRequestDTO);
        if (financeRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financeRequestDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return financeRequestRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FinanceRequestDTO> result = financeRequestService.partialUpdate(financeRequestDTO);

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
     * {@code GET  /finance-requests} : get all the financeRequests.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of financeRequests in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FinanceRequestDTO>>> getAllFinanceRequests(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of FinanceRequests");
        if (StringUtils.isEmpty(filter)) {
        return financeRequestService
            .countAll()
            .zipWith(financeRequestService.findAll(pageable).collectList())
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
            return financeRequestService
                .countAllByFilter(filter)
                .zipWith(financeRequestService.findAllByFilter(filter, pageable).collectList())
                .map(countWithEntities ->
                    ResponseEntity
                        .ok()
                        .headers(
                            PaginationUtil.generatePaginationHttpHeaders(
                                ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                                new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                            )
                        )
                        .body(countWithEntities.getT2()));
        }
    }

    /**
     * {@code GET  /finance-requests/:id} : get the "id" financeRequest.
     *
     * @param id the id of the financeRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financeRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FinanceRequestDTO>> getFinanceRequest(@PathVariable("id") Long id) {
        log.debug("REST request to get FinanceRequest : {}", id);
        Mono<FinanceRequestDTO> financeRequestDTO = financeRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financeRequestDTO);
    }

    /**
     * {@code DELETE  /finance-requests/:id} : delete the "id" financeRequest.
     *
     * @param id the id of the financeRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFinanceRequest(@PathVariable("id") Long id) {
        log.debug("REST request to delete FinanceRequest : {}", id);
        return financeRequestService
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
