package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.CollectionTransactionDetailsRepository;
import in.pft.apis.creditbazaar.service.CollectionTransactionDetailsService;
import in.pft.apis.creditbazaar.service.dto.CollectionTransactionDetailsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.CollectionTransactionDetails}.
 */
@RestController
@RequestMapping("/api/collection-transaction-details")
public class CollectionTransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(CollectionTransactionDetailsResource.class);

    private static final String ENTITY_NAME = "collectionTransactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CollectionTransactionDetailsService collectionTransactionDetailsService;

    private final CollectionTransactionDetailsRepository collectionTransactionDetailsRepository;

    public CollectionTransactionDetailsResource(
        CollectionTransactionDetailsService collectionTransactionDetailsService,
        CollectionTransactionDetailsRepository collectionTransactionDetailsRepository
    ) {
        this.collectionTransactionDetailsService = collectionTransactionDetailsService;
        this.collectionTransactionDetailsRepository = collectionTransactionDetailsRepository;
    }

    /**
     * {@code POST  /collection-transaction-details} : Create a new collectionTransactionDetails.
     *
     * @param collectionTransactionDetailsDTO the collectionTransactionDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new collectionTransactionDetailsDTO, or with status {@code 400 (Bad Request)} if the collectionTransactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<CollectionTransactionDetailsDTO>> createCollectionTransactionDetails(
        @Valid @RequestBody CollectionTransactionDetailsDTO collectionTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CollectionTransactionDetails : {}", collectionTransactionDetailsDTO);
        if (collectionTransactionDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new collectionTransactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return collectionTransactionDetailsService
            .save(collectionTransactionDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/collection-transaction-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /collection-transaction-details/:id} : Updates an existing collectionTransactionDetails.
     *
     * @param id the id of the collectionTransactionDetailsDTO to save.
     * @param collectionTransactionDetailsDTO the collectionTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the collectionTransactionDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the collectionTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CollectionTransactionDetailsDTO>> updateCollectionTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CollectionTransactionDetailsDTO collectionTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CollectionTransactionDetails : {}, {}", id, collectionTransactionDetailsDTO);
        if (collectionTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, collectionTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return collectionTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return collectionTransactionDetailsService
                    .update(collectionTransactionDetailsDTO)
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
     * {@code PATCH  /collection-transaction-details/:id} : Partial updates given fields of an existing collectionTransactionDetails, field will ignore if it is null
     *
     * @param id the id of the collectionTransactionDetailsDTO to save.
     * @param collectionTransactionDetailsDTO the collectionTransactionDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated collectionTransactionDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the collectionTransactionDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the collectionTransactionDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the collectionTransactionDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CollectionTransactionDetailsDTO>> partialUpdateCollectionTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CollectionTransactionDetailsDTO collectionTransactionDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CollectionTransactionDetails partially : {}, {}", id, collectionTransactionDetailsDTO);
        if (collectionTransactionDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, collectionTransactionDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return collectionTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CollectionTransactionDetailsDTO> result = collectionTransactionDetailsService.partialUpdate(
                    collectionTransactionDetailsDTO
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
     * {@code GET  /collection-transaction-details} : get all the collectionTransactionDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of collectionTransactionDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<CollectionTransactionDetailsDTO>>> getAllCollectionTransactionDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of CollectionTransactionDetails");
        return collectionTransactionDetailsService
            .countAll()
            .zipWith(collectionTransactionDetailsService.findAll(pageable).collectList())
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
     * {@code GET  /collection-transaction-details/:id} : get the "id" collectionTransactionDetails.
     *
     * @param id the id of the collectionTransactionDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the collectionTransactionDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CollectionTransactionDetailsDTO>> getCollectionTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get CollectionTransactionDetails : {}", id);
        Mono<CollectionTransactionDetailsDTO> collectionTransactionDetailsDTO = collectionTransactionDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(collectionTransactionDetailsDTO);
    }

    /**
     * {@code DELETE  /collection-transaction-details/:id} : delete the "id" collectionTransactionDetails.
     *
     * @param id the id of the collectionTransactionDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCollectionTransactionDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete CollectionTransactionDetails : {}", id);
        return collectionTransactionDetailsService
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
