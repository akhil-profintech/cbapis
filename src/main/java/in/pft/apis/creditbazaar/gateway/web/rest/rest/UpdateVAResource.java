package in.pft.apis.creditbazaar.gateway.web.rest.rest;

import in.pft.apis.creditbazaar.gateway.repository.UpdateVARepository;
import in.pft.apis.creditbazaar.gateway.service.UpdateVAService;
import in.pft.apis.creditbazaar.gateway.service.dto.UpdateVADTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.UpdateVA}.
 */
@RestController
@RequestMapping("/api/update-vas")
public class UpdateVAResource {

    private final Logger log = LoggerFactory.getLogger(UpdateVAResource.class);

    private static final String ENTITY_NAME = "updateVA";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UpdateVAService updateVAService;

    private final UpdateVARepository updateVARepository;

    public UpdateVAResource(UpdateVAService updateVAService, UpdateVARepository updateVARepository) {
        this.updateVAService = updateVAService;
        this.updateVARepository = updateVARepository;
    }

    /**
     * {@code POST  /update-vas} : Create a new updateVA.
     *
     * @param updateVADTO the updateVADTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new updateVADTO, or with status {@code 400 (Bad Request)} if the updateVA has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<UpdateVADTO>> createUpdateVA(@Valid @RequestBody UpdateVADTO updateVADTO) throws URISyntaxException {
        log.debug("REST request to save UpdateVA : {}", updateVADTO);
        if (updateVADTO.getId() != null) {
            throw new BadRequestAlertException("A new updateVA cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return updateVAService
            .save(updateVADTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/update-vas/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /update-vas/:id} : Updates an existing updateVA.
     *
     * @param id the id of the updateVADTO to save.
     * @param updateVADTO the updateVADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated updateVADTO,
     * or with status {@code 400 (Bad Request)} if the updateVADTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the updateVADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<UpdateVADTO>> updateUpdateVA(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody UpdateVADTO updateVADTO
    ) throws URISyntaxException {
        log.debug("REST request to update UpdateVA : {}, {}", id, updateVADTO);
        if (updateVADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, updateVADTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return updateVARepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return updateVAService
                    .update(updateVADTO)
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
     * {@code PATCH  /update-vas/:id} : Partial updates given fields of an existing updateVA, field will ignore if it is null
     *
     * @param id the id of the updateVADTO to save.
     * @param updateVADTO the updateVADTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated updateVADTO,
     * or with status {@code 400 (Bad Request)} if the updateVADTO is not valid,
     * or with status {@code 404 (Not Found)} if the updateVADTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the updateVADTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<UpdateVADTO>> partialUpdateUpdateVA(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody UpdateVADTO updateVADTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update UpdateVA partially : {}, {}", id, updateVADTO);
        if (updateVADTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, updateVADTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return updateVARepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<UpdateVADTO> result = updateVAService.partialUpdate(updateVADTO);

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
     * {@code GET  /update-vas} : get all the updateVAS.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of updateVAS in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<UpdateVADTO>>> getAllUpdateVAS(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of UpdateVAS");
        return updateVAService
            .countAll()
            .zipWith(updateVAService.findAll(pageable).collectList())
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
     * {@code GET  /update-vas/:id} : get the "id" updateVA.
     *
     * @param id the id of the updateVADTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updateVADTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<UpdateVADTO>> getUpdateVA(@PathVariable("id") Long id) {
        log.debug("REST request to get UpdateVA : {}", id);
        Mono<UpdateVADTO> updateVADTO = updateVAService.findOne(id);
        return ResponseUtil.wrapOrNotFound(updateVADTO);
    }

    /**
     * {@code DELETE  /update-vas/:id} : delete the "id" updateVA.
     *
     * @param id the id of the updateVADTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUpdateVA(@PathVariable("id") Long id) {
        log.debug("REST request to delete UpdateVA : {}", id);
        return updateVAService
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
