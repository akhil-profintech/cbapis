package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.DocDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.DocDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.DocDetailsDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.DocDetails}.
 */
@RestController
@RequestMapping("/api/doc-details")
public class DocDetailsResource {

    private final Logger log = LoggerFactory.getLogger(DocDetailsResource.class);

    private static final String ENTITY_NAME = "docDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocDetailsService docDetailsService;

    private final DocDetailsRepository docDetailsRepository;

    public DocDetailsResource(DocDetailsService docDetailsService, DocDetailsRepository docDetailsRepository) {
        this.docDetailsService = docDetailsService;
        this.docDetailsRepository = docDetailsRepository;
    }

    /**
     * {@code POST  /doc-details} : Create a new docDetails.
     *
     * @param docDetailsDTO the docDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new docDetailsDTO, or with status {@code 400 (Bad Request)} if the docDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<DocDetailsDTO>> createDocDetails(@Valid @RequestBody DocDetailsDTO docDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save DocDetails : {}", docDetailsDTO);
        if (docDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new docDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return docDetailsService
            .save(docDetailsDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/doc-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /doc-details/:id} : Updates an existing docDetails.
     *
     * @param id the id of the docDetailsDTO to save.
     * @param docDetailsDTO the docDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated docDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the docDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the docDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<DocDetailsDTO>> updateDocDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DocDetailsDTO docDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DocDetails : {}, {}", id, docDetailsDTO);
        if (docDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, docDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return docDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return docDetailsService
                    .update(docDetailsDTO)
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
     * {@code PATCH  /doc-details/:id} : Partial updates given fields of an existing docDetails, field will ignore if it is null
     *
     * @param id the id of the docDetailsDTO to save.
     * @param docDetailsDTO the docDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated docDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the docDetailsDTO is not valid,
     * or with status {@code 404 (Not Found)} if the docDetailsDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the docDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DocDetailsDTO>> partialUpdateDocDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DocDetailsDTO docDetailsDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DocDetails partially : {}, {}", id, docDetailsDTO);
        if (docDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, docDetailsDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return docDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DocDetailsDTO> result = docDetailsService.partialUpdate(docDetailsDTO);

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
     * {@code GET  /doc-details} : get all the docDetails.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of docDetails in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<DocDetailsDTO>>> getAllDocDetails(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of DocDetails");
        if (StringUtils.isEmpty(filter)) {
        return docDetailsService
            .countAll()
            .zipWith(docDetailsService.findAll(pageable).collectList())
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
            return docDetailsService
                .countAllByFilter(filter)
                .zipWith(docDetailsService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /doc-details/:id} : get the "id" docDetails.
     *
     * @param id the id of the docDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the docDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<DocDetailsDTO>> getDocDetails(@PathVariable("id") Long id) {
        log.debug("REST request to get DocDetails : {}", id);
        Mono<DocDetailsDTO> docDetailsDTO = docDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(docDetailsDTO);
    }

    /**
     * {@code DELETE  /doc-details/:id} : delete the "id" docDetails.
     *
     * @param id the id of the docDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteDocDetails(@PathVariable("id") Long id) {
        log.debug("REST request to delete DocDetails : {}", id);
        return docDetailsService
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
