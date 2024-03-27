package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.FundsTransferRepository;
import in.pft.apis.creditbazaar.gateway.service.FundsTransferService;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.FundsTransfer}.
 */
@RestController
@RequestMapping("/api/funds-transfers")
public class FundsTransferResource {

    private final Logger log = LoggerFactory.getLogger(FundsTransferResource.class);

    private static final String ENTITY_NAME = "fundsTransfer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundsTransferService fundsTransferService;

    private final FundsTransferRepository fundsTransferRepository;

    public FundsTransferResource(FundsTransferService fundsTransferService, FundsTransferRepository fundsTransferRepository) {
        this.fundsTransferService = fundsTransferService;
        this.fundsTransferRepository = fundsTransferRepository;
    }

    /**
     * {@code POST  /funds-transfers} : Create a new fundsTransfer.
     *
     * @param fundsTransferDTO the fundsTransferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundsTransferDTO, or with status {@code 400 (Bad Request)} if the fundsTransfer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FundsTransferDTO>> createFundsTransfer(@Valid @RequestBody FundsTransferDTO fundsTransferDTO)
        throws URISyntaxException {
        log.debug("REST request to save FundsTransfer : {}", fundsTransferDTO);
        if (fundsTransferDTO.getId() != null) {
            throw new BadRequestAlertException("A new fundsTransfer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return fundsTransferService
            .save(fundsTransferDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/funds-transfers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /funds-transfers/:id} : Updates an existing fundsTransfer.
     *
     * @param id the id of the fundsTransferDTO to save.
     * @param fundsTransferDTO the fundsTransferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsTransferDTO,
     * or with status {@code 400 (Bad Request)} if the fundsTransferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundsTransferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FundsTransferDTO>> updateFundsTransfer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FundsTransferDTO fundsTransferDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FundsTransfer : {}, {}", id, fundsTransferDTO);
        if (fundsTransferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsTransferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fundsTransferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return fundsTransferService
                    .update(fundsTransferDTO)
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
     * {@code PATCH  /funds-transfers/:id} : Partial updates given fields of an existing fundsTransfer, field will ignore if it is null
     *
     * @param id the id of the fundsTransferDTO to save.
     * @param fundsTransferDTO the fundsTransferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundsTransferDTO,
     * or with status {@code 400 (Bad Request)} if the fundsTransferDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fundsTransferDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundsTransferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FundsTransferDTO>> partialUpdateFundsTransfer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FundsTransferDTO fundsTransferDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FundsTransfer partially : {}, {}", id, fundsTransferDTO);
        if (fundsTransferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundsTransferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return fundsTransferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FundsTransferDTO> result = fundsTransferService.partialUpdate(fundsTransferDTO);

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
     * {@code GET  /funds-transfers} : get all the fundsTransfers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundsTransfers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FundsTransferDTO>>> getAllFundsTransfers(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of FundsTransfers");
        if (StringUtils.isEmpty(filter)) {
        return fundsTransferService
            .countAll()
            .zipWith(fundsTransferService.findAll(pageable).collectList())
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
            return fundsTransferService
                .countAllByFilter(filter)
                .zipWith(fundsTransferService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /funds-transfers/:id} : get the "id" fundsTransfer.
     *
     * @param id the id of the fundsTransferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundsTransferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FundsTransferDTO>> getFundsTransfer(@PathVariable("id") Long id) {
        log.debug("REST request to get FundsTransfer : {}", id);
        Mono<FundsTransferDTO> fundsTransferDTO = fundsTransferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundsTransferDTO);
    }

    /**
     * {@code DELETE  /funds-transfers/:id} : delete the "id" fundsTransfer.
     *
     * @param id the id of the fundsTransferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFundsTransfer(@PathVariable("id") Long id) {
        log.debug("REST request to delete FundsTransfer : {}", id);
        return fundsTransferService
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
