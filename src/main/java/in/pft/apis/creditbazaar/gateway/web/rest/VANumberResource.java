package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.VANumberRepository;
import in.pft.apis.creditbazaar.gateway.service.VANumberService;
import in.pft.apis.creditbazaar.gateway.service.dto.VANumberDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.VANumber}.
 */
@RestController
@RequestMapping("/api/va-numbers")
public class VANumberResource {

    private final Logger log = LoggerFactory.getLogger(VANumberResource.class);

    private static final String ENTITY_NAME = "vANumber";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VANumberService vANumberService;

    private final VANumberRepository vANumberRepository;

    public VANumberResource(VANumberService vANumberService, VANumberRepository vANumberRepository) {
        this.vANumberService = vANumberService;
        this.vANumberRepository = vANumberRepository;
    }

    /**
     * {@code POST  /va-numbers} : Create a new vANumber.
     *
     * @param vANumberDTO the vANumberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vANumberDTO, or with status {@code 400 (Bad Request)} if the vANumber has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<VANumberDTO>> createVANumber(@Valid @RequestBody VANumberDTO vANumberDTO) throws URISyntaxException {
        log.debug("REST request to save VANumber : {}", vANumberDTO);
        if (vANumberDTO.getId() != null) {
            throw new BadRequestAlertException("A new vANumber cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return vANumberService
            .save(vANumberDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/va-numbers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /va-numbers/:id} : Updates an existing vANumber.
     *
     * @param id the id of the vANumberDTO to save.
     * @param vANumberDTO the vANumberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vANumberDTO,
     * or with status {@code 400 (Bad Request)} if the vANumberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vANumberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<VANumberDTO>> updateVANumber(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody VANumberDTO vANumberDTO
    ) throws URISyntaxException {
        log.debug("REST request to update VANumber : {}, {}", id, vANumberDTO);
        if (vANumberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vANumberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return vANumberRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return vANumberService
                    .update(vANumberDTO)
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
     * {@code PATCH  /va-numbers/:id} : Partial updates given fields of an existing vANumber, field will ignore if it is null
     *
     * @param id the id of the vANumberDTO to save.
     * @param vANumberDTO the vANumberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vANumberDTO,
     * or with status {@code 400 (Bad Request)} if the vANumberDTO is not valid,
     * or with status {@code 404 (Not Found)} if the vANumberDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the vANumberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<VANumberDTO>> partialUpdateVANumber(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody VANumberDTO vANumberDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update VANumber partially : {}, {}", id, vANumberDTO);
        if (vANumberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vANumberDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return vANumberRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<VANumberDTO> result = vANumberService.partialUpdate(vANumberDTO);

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
     * {@code GET  /va-numbers} : get all the vANumbers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vANumbers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<VANumberDTO>>> getAllVANumbers(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of VANumbers");
        return vANumberService
            .countAll()
            .zipWith(vANumberService.findAll(pageable).collectList())
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
     * {@code GET  /va-numbers/:id} : get the "id" vANumber.
     *
     * @param id the id of the vANumberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vANumberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<VANumberDTO>> getVANumber(@PathVariable("id") Long id) {
        log.debug("REST request to get VANumber : {}", id);
        Mono<VANumberDTO> vANumberDTO = vANumberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(vANumberDTO);
    }

    /**
     * {@code DELETE  /va-numbers/:id} : delete the "id" vANumber.
     *
     * @param id the id of the vANumberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVANumber(@PathVariable("id") Long id) {
        log.debug("REST request to delete VANumber : {}", id);
        return vANumberService
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
