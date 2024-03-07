package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.ContextRepository;
import in.pft.apis.creditbazaar.gateway.service.ContextService;
import in.pft.apis.creditbazaar.gateway.service.dto.ContextDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.Context}.
 */
@RestController
@RequestMapping("/api/contexts")
public class ContextResource {

    private final Logger log = LoggerFactory.getLogger(ContextResource.class);

    private static final String ENTITY_NAME = "context";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContextService contextService;

    private final ContextRepository contextRepository;

    public ContextResource(ContextService contextService, ContextRepository contextRepository) {
        this.contextService = contextService;
        this.contextRepository = contextRepository;
    }

    /**
     * {@code POST  /contexts} : Create a new context.
     *
     * @param contextDTO the contextDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contextDTO, or with status {@code 400 (Bad Request)} if the context has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ContextDTO>> createContext(@Valid @RequestBody ContextDTO contextDTO) throws URISyntaxException {
        log.debug("REST request to save Context : {}", contextDTO);
        if (contextDTO.getId() != null) {
            throw new BadRequestAlertException("A new context cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return contextService
            .save(contextDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/contexts/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /contexts/:id} : Updates an existing context.
     *
     * @param id the id of the contextDTO to save.
     * @param contextDTO the contextDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contextDTO,
     * or with status {@code 400 (Bad Request)} if the contextDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contextDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ContextDTO>> updateContext(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ContextDTO contextDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Context : {}, {}", id, contextDTO);
        if (contextDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contextDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return contextRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return contextService
                    .update(contextDTO)
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
     * {@code PATCH  /contexts/:id} : Partial updates given fields of an existing context, field will ignore if it is null
     *
     * @param id the id of the contextDTO to save.
     * @param contextDTO the contextDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contextDTO,
     * or with status {@code 400 (Bad Request)} if the contextDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contextDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contextDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ContextDTO>> partialUpdateContext(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ContextDTO contextDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Context partially : {}, {}", id, contextDTO);
        if (contextDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contextDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return contextRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ContextDTO> result = contextService.partialUpdate(contextDTO);

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
     * {@code GET  /contexts} : get all the contexts.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contexts in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ContextDTO>>> getAllContexts(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of Contexts");
        return contextService
            .countAll()
            .zipWith(contextService.findAll(pageable).collectList())
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
     * {@code GET  /contexts/:id} : get the "id" context.
     *
     * @param id the id of the contextDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contextDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ContextDTO>> getContext(@PathVariable("id") Long id) {
        log.debug("REST request to get Context : {}", id);
        Mono<ContextDTO> contextDTO = contextService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contextDTO);
    }

    /**
     * {@code DELETE  /contexts/:id} : delete the "id" context.
     *
     * @param id the id of the contextDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteContext(@PathVariable("id") Long id) {
        log.debug("REST request to delete Context : {}", id);
        return contextService
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
