package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.CreditAccountDetailsRepository;
import in.pft.apis.creditbazaar.service.CreditAccountDetailsService;
import in.pft.apis.creditbazaar.service.dto.CreditAccountDetailsDTO;
import in.pft.apis.creditbazaar.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.CreditAccountDetails}.
 */
@RestController
@RequestMapping("/api/credit-account-details")
public class CreditAccountDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CreditAccountDetailsResource.class);

    private static final String ENTITY_NAME = "creditAccountDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditAccountDetailsService creditAccountDetailsService;

    private final CreditAccountDetailsRepository creditAccountDetailsRepository;

    public CreditAccountDetailsResource(
        CreditAccountDetailsService creditAccountDetailsService,
        CreditAccountDetailsRepository creditAccountDetailsRepository
    ) {
        this.creditAccountDetailsService = creditAccountDetailsService;
        this.creditAccountDetailsRepository = creditAccountDetailsRepository;
    }

    /**
     * {@code POST  /credit-account-details} : Create a new creditAccountDetails.
     *
     * @param creditAccountDetailsDTO the creditAccountDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditAccountDetailsDTO, or with status {@code 400 (Bad Request)} if the creditAccountDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CreditAccountDetailsDTO>> createCreditAccountDetails(
        @Valid @RequestBody CreditAccountDetailsDTO creditAccountDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CreditAccountDetails : {}", creditAccountDetailsDTO);
        if (creditAccountDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditAccountDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return creditAccountDetailsService
            .save(creditAccountDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/credit-account-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /credit-account-details/:id} : Updates an existing creditAccountDetails.
     *
     * @param id the id of the creditAccountDetailsDTO to save.
     * @param creditAccountDetailsDTO the creditAccountDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditAccountDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the creditAccountDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditAccountDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreditAccountDetailsDTO>> updateCreditAccountDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CreditAccountDetailsDTO creditAccountDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreditAccountDetails : {}, {}", id, creditAccountDetailsDTO);
        if (creditAccountDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditAccountDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return creditAccountDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return creditAccountDetailsService
                    .update(creditAccountDetailsDTO)
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
     * {@code PATCH  /credit-account-details/:id} : Partial updates given fields of an existing creditAccountDetails, field will ignore if it is null
     *
     * @param id the id of the creditAccountDetailsDTO to save.
     * @param creditAccountDetailsDTO the creditAccountDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditAccountDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the creditAccountDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the creditAccountDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the creditAccountDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreditAccountDetailsDTO>> partialUpdateCreditAccountDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CreditAccountDetailsDTO creditAccountDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreditAccountDetails partially : {}, {}", id, creditAccountDetailsDTO);
        if (creditAccountDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditAccountDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return creditAccountDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreditAccountDetailsDTO> result = creditAccountDetailsService.partialUpdate(creditAccountDetailsDTO);

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
     * {@code GET  /credit-account-details} : get all the creditAccountDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditAccountDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CreditAccountDetailsDTO>>> getAllCreditAccountDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of CreditAccountDetails");
        return creditAccountDetailsService
            .countAll()
            .zipWith(creditAccountDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /credit-account-details/:id} : get the "id" creditAccountDetails.
     *
     * @param id the id of the creditAccountDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditAccountDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreditAccountDetailsDTO>> getCreditAccountDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get CreditAccountDetails : {}", id);
        Mono<CreditAccountDetailsDTO> creditAccountDetailsDTO = creditAccountDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditAccountDetailsDTO);
    }

    /**
     * {@code DELETE  /credit-account-details/:id} : delete the "id" creditAccountDetails.
     *
     * @param id the id of the creditAccountDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCreditAccountDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete CreditAccountDetails : {}", id);
        return creditAccountDetailsService
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
