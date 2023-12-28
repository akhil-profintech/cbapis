package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.CreditBazaarIntegratorRepository;
import in.pft.apis.creditbazaar.service.CreditBazaarIntegratorService;
import in.pft.apis.creditbazaar.service.dto.CreditBazaarIntegratorDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.CreditBazaarIntegrator}.
 */
@RestController
@RequestMapping("/api/credit-bazaar-integrators")
public class CreditBazaarIntegratorResource {

    private final Logger log = LoggerFactory.getLogger(CreditBazaarIntegratorResource.class);

    private static final String ENTITY_NAME = "creditBazaarIntegrator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditBazaarIntegratorService creditBazaarIntegratorService;

    private final CreditBazaarIntegratorRepository creditBazaarIntegratorRepository;

    public CreditBazaarIntegratorResource(
        CreditBazaarIntegratorService creditBazaarIntegratorService,
        CreditBazaarIntegratorRepository creditBazaarIntegratorRepository
    ) {
        this.creditBazaarIntegratorService = creditBazaarIntegratorService;
        this.creditBazaarIntegratorRepository = creditBazaarIntegratorRepository;
    }

    /**
     * {@code POST  /credit-bazaar-integrators} : Create a new creditBazaarIntegrator.
     *
     * @param creditBazaarIntegratorDTO the creditBazaarIntegratorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditBazaarIntegratorDTO, or with status {@code 400 (Bad Request)} if the creditBazaarIntegrator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CreditBazaarIntegratorDTO>> createCreditBazaarIntegrator(
        @Valid @RequestBody CreditBazaarIntegratorDTO creditBazaarIntegratorDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CreditBazaarIntegrator : {}", creditBazaarIntegratorDTO);
        if (creditBazaarIntegratorDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditBazaarIntegrator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return creditBazaarIntegratorService
            .save(creditBazaarIntegratorDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/credit-bazaar-integrators/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /credit-bazaar-integrators/:id} : Updates an existing creditBazaarIntegrator.
     *
     * @param id the id of the creditBazaarIntegratorDTO to save.
     * @param creditBazaarIntegratorDTO the creditBazaarIntegratorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditBazaarIntegratorDTO,
     * or with status {@code 400 (Bad Request)} if the creditBazaarIntegratorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditBazaarIntegratorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreditBazaarIntegratorDTO>> updateCreditBazaarIntegrator(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CreditBazaarIntegratorDTO creditBazaarIntegratorDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreditBazaarIntegrator : {}, {}", id, creditBazaarIntegratorDTO);
        if (creditBazaarIntegratorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditBazaarIntegratorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return creditBazaarIntegratorRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return creditBazaarIntegratorService
                    .update(creditBazaarIntegratorDTO)
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
     * {@code PATCH  /credit-bazaar-integrators/:id} : Partial updates given fields of an existing creditBazaarIntegrator, field will ignore if it is null
     *
     * @param id the id of the creditBazaarIntegratorDTO to save.
     * @param creditBazaarIntegratorDTO the creditBazaarIntegratorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditBazaarIntegratorDTO,
     * or with status {@code 400 (Bad Request)} if the creditBazaarIntegratorDTO is not valid,
     * or with status {@code 404 (Not Found)} if the creditBazaarIntegratorDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the creditBazaarIntegratorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CreditBazaarIntegratorDTO>> partialUpdateCreditBazaarIntegrator(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CreditBazaarIntegratorDTO creditBazaarIntegratorDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreditBazaarIntegrator partially : {}, {}", id, creditBazaarIntegratorDTO);
        if (creditBazaarIntegratorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditBazaarIntegratorDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return creditBazaarIntegratorRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CreditBazaarIntegratorDTO> result = creditBazaarIntegratorService.partialUpdate(creditBazaarIntegratorDTO);

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
     * {@code GET  /credit-bazaar-integrators} : get all the creditBazaarIntegrators.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditBazaarIntegrators in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CreditBazaarIntegratorDTO>>> getAllCreditBazaarIntegrators(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of CreditBazaarIntegrators");
        return creditBazaarIntegratorService
            .countAll()
            .zipWith(creditBazaarIntegratorService.findAll(pageable).collectList())
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
     * {@code GET  /credit-bazaar-integrators/:id} : get the "id" creditBazaarIntegrator.
     *
     * @param id the id of the creditBazaarIntegratorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditBazaarIntegratorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreditBazaarIntegratorDTO>> getCreditBazaarIntegrator(@PathVariable("id") Long id) {
        log.debug("REST request to get CreditBazaarIntegrator : {}", id);
        Mono<CreditBazaarIntegratorDTO> creditBazaarIntegratorDTO = creditBazaarIntegratorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditBazaarIntegratorDTO);
    }

    /**
     * {@code DELETE  /credit-bazaar-integrators/:id} : delete the "id" creditBazaarIntegrator.
     *
     * @param id the id of the creditBazaarIntegratorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCreditBazaarIntegrator(@PathVariable("id") Long id) {
        log.debug("REST request to delete CreditBazaarIntegrator : {}", id);
        return creditBazaarIntegratorService
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
