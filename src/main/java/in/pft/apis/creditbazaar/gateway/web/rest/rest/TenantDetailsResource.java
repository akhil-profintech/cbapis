package in.pft.apis.creditbazaar.gateway.web.rest.rest;

import in.pft.apis.creditbazaar.gateway.repository.TenantDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.TenantDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.TenantDetailsDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.TenantDetails}.
 */
@RestController
@RequestMapping("/api/tenant-details")
public class TenantDetailsResource {

    private final Logger log = LoggerFactory.getLogger(TenantDetailsResource.class);

    private static final String ENTITY_NAME = "tenantDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TenantDetailsService tenantDetailsService;

    private final TenantDetailsRepository tenantDetailsRepository;

    public TenantDetailsResource(TenantDetailsService tenantDetailsService, TenantDetailsRepository tenantDetailsRepository) {
        this.tenantDetailsService = tenantDetailsService;
        this.tenantDetailsRepository = tenantDetailsRepository;
    }

    /**
     * {@code POST  /tenant-details} : Create a new tenantDetails.
     *
     * @param tenantDetailsDTO the tenantDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tenantDetailsDTO, or with status {@code 400 (Bad Request)} if the tenantDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<TenantDetailsDTO>> createTenantDetails(@RequestBody TenantDetailsDTO tenantDetailsDTO)
        throws URISyntaxException {
        log.debug("REST request to save TenantDetails : {}", tenantDetailsDTO);
        if (tenantDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new tenantDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return tenantDetailsService
            .save(tenantDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/tenant-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /tenant-details/:id} : Updates an existing tenantDetails.
     *
     * @param id the id of the tenantDetailsDTO to save.
     * @param tenantDetailsDTO the tenantDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenantDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the tenantDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tenantDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TenantDetailsDTO>> updateTenantDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenantDetailsDTO tenantDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TenantDetails : {}, {}", id, tenantDetailsDTO);
        if (tenantDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenantDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tenantDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return tenantDetailsService
                    .update(tenantDetailsDTO)
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
     * {@code PATCH  /tenant-details/:id} : Partial updates given fields of an existing tenantDetails, field will ignore if it is null
     *
     * @param id the id of the tenantDetailsDTO to save.
     * @param tenantDetailsDTO the tenantDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tenantDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the tenantDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tenantDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tenantDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<TenantDetailsDTO>> partialUpdateTenantDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TenantDetailsDTO tenantDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TenantDetails partially : {}, {}", id, tenantDetailsDTO);
        if (tenantDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tenantDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tenantDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<TenantDetailsDTO> result = tenantDetailsService.partialUpdate(tenantDetailsDTO);

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
     * {@code GET  /tenant-details} : get all the tenantDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tenantDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<TenantDetailsDTO>>> getAllTenantDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of TenantDetails");
        return tenantDetailsService
            .countAll()
            .zipWith(tenantDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /tenant-details/:id} : get the "id" tenantDetails.
     *
     * @param id the id of the tenantDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tenantDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TenantDetailsDTO>> getTenantDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get TenantDetails : {}", id);
        Mono<TenantDetailsDTO> tenantDetailsDTO = tenantDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tenantDetailsDTO);
    }

    /**
     * {@code DELETE  /tenant-details/:id} : delete the "id" tenantDetails.
     *
     * @param id the id of the tenantDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTenantDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete TenantDetails : {}", id);
        return tenantDetailsService
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
