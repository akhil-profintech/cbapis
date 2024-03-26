package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.FundsTransferTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.FundsTransferTransactionDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferTransactionDetailsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetails}.
 */
@RestController
@RequestMapping("/api/funds-transfer-transaction-details")
public class FundsTransferTransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FundsTransferTransactionDetailsResource.class);

    private static final String ENTITY_NAME = "fundsTransferTransactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundsTransferTransactionDetailsService fundsTransferTransactionDetailsService;

    private final FundsTransferTransactionDetailsRepository fundsTransferTransactionDetailsRepository;

    public FundsTransferTransactionDetailsResource(
        FundsTransferTransactionDetailsService fundsTransferTransactionDetailsService,
        FundsTransferTransactionDetailsRepository fundsTransferTransactionDetailsRepository
    ) {
        this.fundsTransferTransactionDetailsService = fundsTransferTransactionDetailsService;
        this.fundsTransferTransactionDetailsRepository = fundsTransferTransactionDetailsRepository;
    }

    /**
     * {@code POST  /funds-transfer-transaction-details} : Create a new fundsTransferTransactionDetails.
     *
     * @param fundsTransferTransactionDetailsDTO the fundsTransferTransactionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundsTransferTransactionDetailsDTO, or with status {@code 400 (Bad Request)} if the fundsTransferTransactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FundsTransferTransactionDetailsDTO>> createFundsTransferTransactionDetails(
        @Valid @RequestBody FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FundsTransferTransactionDetails : {}", fundsTransferTransactionDetailsDTO);
        if (fundsTransferTransactionDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new fundsTransferTransactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return fundsTransferTransactionDetailsService
            .save(fundsTransferTransactionDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/funds-transfer-transaction-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /funds-transfer-transaction-details/:id} : Updates an existing fundsTransferTransactionDetails.
     *
     * @param id the id of the fundsTransferTransactionDetailsDTO to save.
     * @param fundsTransferTransactionDetailsDTO the fundsTransferTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsTransferTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the fundsTransferTransactionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundsTransferTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FundsTransferTransactionDetailsDTO>> updateFundsTransferTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FundsTransferTransactionDetails : {}, {}", id, fundsTransferTransactionDetailsDTO);
        if (fundsTransferTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsTransferTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fundsTransferTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return fundsTransferTransactionDetailsService
                    .update(fundsTransferTransactionDetailsDTO)
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
     * {@code PATCH  /funds-transfer-transaction-details/:id} : Partial updates given fields of an existing fundsTransferTransactionDetails, field will ignore if it is null
     *
     * @param id the id of the fundsTransferTransactionDetailsDTO to save.
     * @param fundsTransferTransactionDetailsDTO the fundsTransferTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsTransferTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the fundsTransferTransactionDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fundsTransferTransactionDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundsTransferTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FundsTransferTransactionDetailsDTO>> partialUpdateFundsTransferTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug(
            "REST request to partial update FundsTransferTransactionDetails partially : {}, {}",
            id,
            fundsTransferTransactionDetailsDTO
        );
        if (fundsTransferTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsTransferTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fundsTransferTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FundsTransferTransactionDetailsDTO> result = fundsTransferTransactionDetailsService.partialUpdate(
                    fundsTransferTransactionDetailsDTO
                );

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
     * {@code GET  /funds-transfer-transaction-details} : get all the fundsTransferTransactionDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundsTransferTransactionDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FundsTransferTransactionDetailsDTO>>> getAllFundsTransferTransactionDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of FundsTransferTransactionDetails");
        if (StringUtils.isEmpty(filter)) {
        return fundsTransferTransactionDetailsService
            .countAll()
            .zipWith(fundsTransferTransactionDetailsService.findAll(pageable).collectList())
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
            return fundsTransferTransactionDetailsService
                .countAllByFilter(filter)
                .zipWith(fundsTransferTransactionDetailsService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /funds-transfer-transaction-details/:id} : get the "id" fundsTransferTransactionDetails.
     *
     * @param id the id of the fundsTransferTransactionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundsTransferTransactionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FundsTransferTransactionDetailsDTO>> getFundsTransferTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get FundsTransferTransactionDetails : {}", id);
        Mono<FundsTransferTransactionDetailsDTO> fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundsTransferTransactionDetailsDTO);
    }

    /**
     * {@code DELETE  /funds-transfer-transaction-details/:id} : delete the "id" fundsTransferTransactionDetails.
     *
     * @param id the id of the fundsTransferTransactionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFundsTransferTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete FundsTransferTransactionDetails : {}", id);
        return fundsTransferTransactionDetailsService
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
