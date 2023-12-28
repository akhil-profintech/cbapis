package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.BeneValidationRepository;
import in.pft.apis.creditbazaar.service.BeneValidationService;
import in.pft.apis.creditbazaar.service.dto.BeneValidationDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.BeneValidation}.
 */
@RestController
@RequestMapping("/api/bene-validations")
public class BeneValidationResource {

    private final Logger log = LoggerFactory.getLogger(BeneValidationResource.class);

    private static final String ENTITY_NAME = "beneValidation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BeneValidationService beneValidationService;

    private final BeneValidationRepository beneValidationRepository;

    public BeneValidationResource(BeneValidationService beneValidationService, BeneValidationRepository beneValidationRepository) {
        this.beneValidationService = beneValidationService;
        this.beneValidationRepository = beneValidationRepository;
    }

    /**
     * {@code POST  /bene-validations} : Create a new beneValidation.
     *
     * @param beneValidationDTO the beneValidationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new beneValidationDTO, or with status {@code 400 (Bad Request)} if the beneValidation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<BeneValidationDTO>> createBeneValidation(@Valid @RequestBody BeneValidationDTO beneValidationDTO)
        throws URISyntaxException {
        log.debug("REST request to save BeneValidation : {}", beneValidationDTO);
        if (beneValidationDTO.getId() != null) {
            throw new BadRequestAlertException("A new beneValidation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return beneValidationService
            .save(beneValidationDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/bene-validations/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /bene-validations/:id} : Updates an existing beneValidation.
     *
     * @param id the id of the beneValidationDTO to save.
     * @param beneValidationDTO the beneValidationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beneValidationDTO,
     * or with status {@code 400 (Bad Request)} if the beneValidationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the beneValidationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<BeneValidationDTO>> updateBeneValidation(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BeneValidationDTO beneValidationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BeneValidation : {}, {}", id, beneValidationDTO);
        if (beneValidationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beneValidationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return beneValidationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return beneValidationService
                    .update(beneValidationDTO)
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
     * {@code PATCH  /bene-validations/:id} : Partial updates given fields of an existing beneValidation, field will ignore if it is null
     *
     * @param id the id of the beneValidationDTO to save.
     * @param beneValidationDTO the beneValidationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated beneValidationDTO,
     * or with status {@code 400 (Bad Request)} if the beneValidationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the beneValidationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the beneValidationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<BeneValidationDTO>> partialUpdateBeneValidation(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BeneValidationDTO beneValidationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BeneValidation partially : {}, {}", id, beneValidationDTO);
        if (beneValidationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, beneValidationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return beneValidationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<BeneValidationDTO> result = beneValidationService.partialUpdate(beneValidationDTO);

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
     * {@code GET  /bene-validations} : get all the beneValidations.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of beneValidations in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<BeneValidationDTO>>> getAllBeneValidations(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of BeneValidations");
        return beneValidationService
            .countAll()
            .zipWith(beneValidationService.findAll(pageable).collectList())
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
     * {@code GET  /bene-validations/:id} : get the "id" beneValidation.
     *
     * @param id the id of the beneValidationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the beneValidationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<BeneValidationDTO>> getBeneValidation(@PathVariable("id") Long id) {
        log.debug("REST request to get BeneValidation : {}", id);
        Mono<BeneValidationDTO> beneValidationDTO = beneValidationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(beneValidationDTO);
    }

    /**
     * {@code DELETE  /bene-validations/:id} : delete the "id" beneValidation.
     *
     * @param id the id of the beneValidationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteBeneValidation(@PathVariable("id") Long id) {
        log.debug("REST request to delete BeneValidation : {}", id);
        return beneValidationService
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
