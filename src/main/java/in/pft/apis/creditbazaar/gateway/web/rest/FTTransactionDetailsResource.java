package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.FTTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.FTTransactionDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.FTTransactionDetailsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.FTTransactionDetails}.
 */
@RestController
@RequestMapping("/api/ft-transaction-details")
public class FTTransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(FTTransactionDetailsResource.class);

    private static final String ENTITY_NAME = "fTTransactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FTTransactionDetailsService fTTransactionDetailsService;

    private final FTTransactionDetailsRepository fTTransactionDetailsRepository;

    public FTTransactionDetailsResource(
        FTTransactionDetailsService fTTransactionDetailsService,
        FTTransactionDetailsRepository fTTransactionDetailsRepository
    ) {
        this.fTTransactionDetailsService = fTTransactionDetailsService;
        this.fTTransactionDetailsRepository = fTTransactionDetailsRepository;
    }

    /**
     * {@code POST  /ft-transaction-details} : Create a new fTTransactionDetails.
     *
     * @param fTTransactionDetailsDTO the fTTransactionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fTTransactionDetailsDTO, or with status {@code 400 (Bad Request)} if the fTTransactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FTTransactionDetailsDTO>> createFTTransactionDetails(
        @Valid @RequestBody FTTransactionDetailsDTO fTTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FTTransactionDetails : {}", fTTransactionDetailsDTO);
        if (fTTransactionDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new fTTransactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return fTTransactionDetailsService
            .save(fTTransactionDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/ft-transaction-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /ft-transaction-details/:id} : Updates an existing fTTransactionDetails.
     *
     * @param id the id of the fTTransactionDetailsDTO to save.
     * @param fTTransactionDetailsDTO the fTTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fTTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the fTTransactionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fTTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FTTransactionDetailsDTO>> updateFTTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FTTransactionDetailsDTO fTTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FTTransactionDetails : {}, {}", id, fTTransactionDetailsDTO);
        if (fTTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fTTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fTTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return fTTransactionDetailsService
                    .update(fTTransactionDetailsDTO)
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
     * {@code PATCH  /ft-transaction-details/:id} : Partial updates given fields of an existing fTTransactionDetails, field will ignore if it is null
     *
     * @param id the id of the fTTransactionDetailsDTO to save.
     * @param fTTransactionDetailsDTO the fTTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fTTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the fTTransactionDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fTTransactionDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fTTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FTTransactionDetailsDTO>> partialUpdateFTTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FTTransactionDetailsDTO fTTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FTTransactionDetails partially : {}, {}", id, fTTransactionDetailsDTO);
        if (fTTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fTTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fTTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FTTransactionDetailsDTO> result = fTTransactionDetailsService.partialUpdate(fTTransactionDetailsDTO);

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
     * {@code GET  /ft-transaction-details} : get all the fTTransactionDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fTTransactionDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FTTransactionDetailsDTO>>> getAllFTTransactionDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of FTTransactionDetails");
        if (StringUtils.isEmpty(filter)) {
            return fTTransactionDetailsService
                .countAll()
                .zipWith(fTTransactionDetailsService.findAll(pageable).collectList())
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
        else{
            return fTTransactionDetailsService
                .countAllByFilter(filter)
                .zipWith(fTTransactionDetailsService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /ft-transaction-details/:id} : get the "id" fTTransactionDetails.
     *
     * @param id the id of the fTTransactionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fTTransactionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FTTransactionDetailsDTO>> getFTTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get FTTransactionDetails : {}", id);
        Mono<FTTransactionDetailsDTO> fTTransactionDetailsDTO = fTTransactionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fTTransactionDetailsDTO);
    }

    /**
     * {@code DELETE  /ft-transaction-details/:id} : delete the "id" fTTransactionDetails.
     *
     * @param id the id of the fTTransactionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFTTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete FTTransactionDetails : {}", id);
        return fTTransactionDetailsService
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
