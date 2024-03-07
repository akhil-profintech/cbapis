package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.EscrowAccountDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.EscrowAccountDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowAccountDetailsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.EscrowAccountDetails}.
 */
@RestController
@RequestMapping("/api/escrow-account-details")
public class EscrowAccountDetailsResource {

    private final Logger log = LoggerFactory.getLogger(EscrowAccountDetailsResource.class);

    private static final String ENTITY_NAME = "escrowAccountDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EscrowAccountDetailsService escrowAccountDetailsService;

    private final EscrowAccountDetailsRepository escrowAccountDetailsRepository;

    public EscrowAccountDetailsResource(
        EscrowAccountDetailsService escrowAccountDetailsService,
        EscrowAccountDetailsRepository escrowAccountDetailsRepository
    ) {
        this.escrowAccountDetailsService = escrowAccountDetailsService;
        this.escrowAccountDetailsRepository = escrowAccountDetailsRepository;
    }

    /**
     * {@code POST  /escrow-account-details} : Create a new escrowAccountDetails.
     *
     * @param escrowAccountDetailsDTO the escrowAccountDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new escrowAccountDetailsDTO, or with status {@code 400 (Bad Request)} if the escrowAccountDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<EscrowAccountDetailsDTO>> createEscrowAccountDetails(
        @Valid @RequestBody EscrowAccountDetailsDTO escrowAccountDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save EscrowAccountDetails : {}", escrowAccountDetailsDTO);
        if (escrowAccountDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new escrowAccountDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return escrowAccountDetailsService
            .save(escrowAccountDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/escrow-account-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /escrow-account-details/:id} : Updates an existing escrowAccountDetails.
     *
     * @param id the id of the escrowAccountDetailsDTO to save.
     * @param escrowAccountDetailsDTO the escrowAccountDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated escrowAccountDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the escrowAccountDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the escrowAccountDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<EscrowAccountDetailsDTO>> updateEscrowAccountDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EscrowAccountDetailsDTO escrowAccountDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EscrowAccountDetails : {}, {}", id, escrowAccountDetailsDTO);
        if (escrowAccountDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, escrowAccountDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return escrowAccountDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return escrowAccountDetailsService
                    .update(escrowAccountDetailsDTO)
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
     * {@code PATCH  /escrow-account-details/:id} : Partial updates given fields of an existing escrowAccountDetails, field will ignore if it is null
     *
     * @param id the id of the escrowAccountDetailsDTO to save.
     * @param escrowAccountDetailsDTO the escrowAccountDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated escrowAccountDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the escrowAccountDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the escrowAccountDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the escrowAccountDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<EscrowAccountDetailsDTO>> partialUpdateEscrowAccountDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EscrowAccountDetailsDTO escrowAccountDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EscrowAccountDetails partially : {}, {}", id, escrowAccountDetailsDTO);
        if (escrowAccountDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, escrowAccountDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return escrowAccountDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<EscrowAccountDetailsDTO> result = escrowAccountDetailsService.partialUpdate(escrowAccountDetailsDTO);

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
     * {@code GET  /escrow-account-details} : get all the escrowAccountDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of escrowAccountDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<EscrowAccountDetailsDTO>>> getAllEscrowAccountDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of EscrowAccountDetails");
        return escrowAccountDetailsService
            .countAll()
            .zipWith(escrowAccountDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /escrow-account-details/:id} : get the "id" escrowAccountDetails.
     *
     * @param id the id of the escrowAccountDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the escrowAccountDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<EscrowAccountDetailsDTO>> getEscrowAccountDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get EscrowAccountDetails : {}", id);
        Mono<EscrowAccountDetailsDTO> escrowAccountDetailsDTO = escrowAccountDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(escrowAccountDetailsDTO);
    }

    /**
     * {@code DELETE  /escrow-account-details/:id} : delete the "id" escrowAccountDetails.
     *
     * @param id the id of the escrowAccountDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteEscrowAccountDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete EscrowAccountDetails : {}", id);
        return escrowAccountDetailsService
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
