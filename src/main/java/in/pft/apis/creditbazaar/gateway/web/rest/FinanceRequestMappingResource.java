package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.FinanceRequestMappingRepository;
import in.pft.apis.creditbazaar.gateway.service.FinanceRequestMappingService;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestMappingDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.FinanceRequestMapping}.
 */
@RestController
@RequestMapping("/api/finance-request-mappings")
public class FinanceRequestMappingResource {

    private final Logger log = LoggerFactory.getLogger(FinanceRequestMappingResource.class);

    private static final String ENTITY_NAME = "financeRequestMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinanceRequestMappingService financeRequestMappingService;

    private final FinanceRequestMappingRepository financeRequestMappingRepository;

    public FinanceRequestMappingResource(
        FinanceRequestMappingService financeRequestMappingService,
        FinanceRequestMappingRepository financeRequestMappingRepository
    ) {
        this.financeRequestMappingService = financeRequestMappingService;
        this.financeRequestMappingRepository = financeRequestMappingRepository;
    }

    /**
     * {@code POST  /finance-request-mappings} : Create a new financeRequestMapping.
     *
     * @param financeRequestMappingDTO the financeRequestMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financeRequestMappingDTO, or with status {@code 400 (Bad Request)} if the financeRequestMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FinanceRequestMappingDTO>> createFinanceRequestMapping(
        @RequestBody FinanceRequestMappingDTO financeRequestMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FinanceRequestMapping : {}", financeRequestMappingDTO);
        if (financeRequestMappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new financeRequestMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return financeRequestMappingService
            .save(financeRequestMappingDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/finance-request-mappings/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /finance-request-mappings/:id} : Updates an existing financeRequestMapping.
     *
     * @param id the id of the financeRequestMappingDTO to save.
     * @param financeRequestMappingDTO the financeRequestMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financeRequestMappingDTO,
     * or with status {@code 400 (Bad Request)} if the financeRequestMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financeRequestMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FinanceRequestMappingDTO>> updateFinanceRequestMapping(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FinanceRequestMappingDTO financeRequestMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FinanceRequestMapping : {}, {}", id, financeRequestMappingDTO);
        if (financeRequestMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financeRequestMappingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return financeRequestMappingRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return financeRequestMappingService
                    .update(financeRequestMappingDTO)
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
     * {@code PATCH  /finance-request-mappings/:id} : Partial updates given fields of an existing financeRequestMapping, field will ignore if it is null
     *
     * @param id the id of the financeRequestMappingDTO to save.
     * @param financeRequestMappingDTO the financeRequestMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financeRequestMappingDTO,
     * or with status {@code 400 (Bad Request)} if the financeRequestMappingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the financeRequestMappingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the financeRequestMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FinanceRequestMappingDTO>> partialUpdateFinanceRequestMapping(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FinanceRequestMappingDTO financeRequestMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FinanceRequestMapping partially : {}, {}", id, financeRequestMappingDTO);
        if (financeRequestMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financeRequestMappingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return financeRequestMappingRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FinanceRequestMappingDTO> result = financeRequestMappingService.partialUpdate(financeRequestMappingDTO);

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
     * {@code GET  /finance-request-mappings} : get all the financeRequestMappings.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of financeRequestMappings in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FinanceRequestMappingDTO>>> getAllFinanceRequestMappings(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of FinanceRequestMappings");
        return financeRequestMappingService
            .countAll()
            .zipWith(financeRequestMappingService.findAll(pageable).collectList())
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
     * {@code GET  /finance-request-mappings/:id} : get the "id" financeRequestMapping.
     *
     * @param id the id of the financeRequestMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financeRequestMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FinanceRequestMappingDTO>> getFinanceRequestMapping(@PathVariable("id") Long id) {
        log.debug("REST request to get FinanceRequestMapping : {}", id);
        Mono<FinanceRequestMappingDTO> financeRequestMappingDTO = financeRequestMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financeRequestMappingDTO);
    }

    /**
     * {@code DELETE  /finance-request-mappings/:id} : delete the "id" financeRequestMapping.
     *
     * @param id the id of the financeRequestMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFinanceRequestMapping(@PathVariable("id") Long id) {
        log.debug("REST request to delete FinanceRequestMapping : {}", id);
        return financeRequestMappingService
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
