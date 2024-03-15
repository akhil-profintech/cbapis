package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.EscrowTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.EscrowTransactionDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowTransactionDetailsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails}.
 */
@RestController
@RequestMapping("/api/escrow-transaction-details")
public class EscrowTransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(EscrowTransactionDetailsResource.class);

    private static final String ENTITY_NAME = "escrowTransactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EscrowTransactionDetailsService escrowTransactionDetailsService;

    private final EscrowTransactionDetailsRepository escrowTransactionDetailsRepository;

    public EscrowTransactionDetailsResource(
        EscrowTransactionDetailsService escrowTransactionDetailsService,
        EscrowTransactionDetailsRepository escrowTransactionDetailsRepository
    ) {
        this.escrowTransactionDetailsService = escrowTransactionDetailsService;
        this.escrowTransactionDetailsRepository = escrowTransactionDetailsRepository;
    }

    /**
     * {@code POST  /escrow-transaction-details} : Create a new escrowTransactionDetails.
     *
     * @param escrowTransactionDetailsDTO the escrowTransactionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new escrowTransactionDetailsDTO, or with status {@code 400 (Bad Request)} if the escrowTransactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<EscrowTransactionDetailsDTO>> createEscrowTransactionDetails(
        @Valid @RequestBody EscrowTransactionDetailsDTO escrowTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save EscrowTransactionDetails : {}", escrowTransactionDetailsDTO);
        if (escrowTransactionDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new escrowTransactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return escrowTransactionDetailsService
            .save(escrowTransactionDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/escrow-transaction-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /escrow-transaction-details/:id} : Updates an existing escrowTransactionDetails.
     *
     * @param id the id of the escrowTransactionDetailsDTO to save.
     * @param escrowTransactionDetailsDTO the escrowTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated escrowTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the escrowTransactionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the escrowTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<EscrowTransactionDetailsDTO>> updateEscrowTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EscrowTransactionDetailsDTO escrowTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EscrowTransactionDetails : {}, {}", id, escrowTransactionDetailsDTO);
        if (escrowTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, escrowTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return escrowTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return escrowTransactionDetailsService
                    .update(escrowTransactionDetailsDTO)
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
     * {@code PATCH  /escrow-transaction-details/:id} : Partial updates given fields of an existing escrowTransactionDetails, field will ignore if it is null
     *
     * @param id the id of the escrowTransactionDetailsDTO to save.
     * @param escrowTransactionDetailsDTO the escrowTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated escrowTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the escrowTransactionDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the escrowTransactionDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the escrowTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<EscrowTransactionDetailsDTO>> partialUpdateEscrowTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EscrowTransactionDetailsDTO escrowTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EscrowTransactionDetails partially : {}, {}", id, escrowTransactionDetailsDTO);
        if (escrowTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, escrowTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return escrowTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<EscrowTransactionDetailsDTO> result = escrowTransactionDetailsService.partialUpdate(escrowTransactionDetailsDTO);

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
     * {@code GET  /escrow-transaction-details} : get all the escrowTransactionDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of escrowTransactionDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<EscrowTransactionDetailsDTO>>> getAllEscrowTransactionDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of EscrowTransactionDetails");
        if (StringUtils.isEmpty(filter)) {
        return escrowTransactionDetailsService
            .countAll()
            .zipWith(escrowTransactionDetailsService.findAll(pageable).collectList())
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
            return escrowTransactionDetailsService
                .countAllByFilter(filter)
                .zipWith(escrowTransactionDetailsService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /escrow-transaction-details/:id} : get the "id" escrowTransactionDetails.
     *
     * @param id the id of the escrowTransactionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the escrowTransactionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<EscrowTransactionDetailsDTO>> getEscrowTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get EscrowTransactionDetails : {}", id);
        Mono<EscrowTransactionDetailsDTO> escrowTransactionDetailsDTO = escrowTransactionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(escrowTransactionDetailsDTO);
    }

    /**
     * {@code DELETE  /escrow-transaction-details/:id} : delete the "id" escrowTransactionDetails.
     *
     * @param id the id of the escrowTransactionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteEscrowTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete EscrowTransactionDetails : {}", id);
        return escrowTransactionDetailsService
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
