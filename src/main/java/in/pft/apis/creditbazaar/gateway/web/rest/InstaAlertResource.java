package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.InstaAlertRepository;
import in.pft.apis.creditbazaar.gateway.service.InstaAlertService;
import in.pft.apis.creditbazaar.gateway.service.dto.InstaAlertDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.InstaAlert}.
 */
@RestController
@RequestMapping("/api/insta-alerts")
public class InstaAlertResource {

    private final Logger log = LoggerFactory.getLogger(InstaAlertResource.class);

    private static final String ENTITY_NAME = "instaAlert";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InstaAlertService instaAlertService;

    private final InstaAlertRepository instaAlertRepository;

    public InstaAlertResource(InstaAlertService instaAlertService, InstaAlertRepository instaAlertRepository) {
        this.instaAlertService = instaAlertService;
        this.instaAlertRepository = instaAlertRepository;
    }

    /**
     * {@code POST  /insta-alerts} : Create a new instaAlert.
     *
     * @param instaAlertDTO the instaAlertDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new instaAlertDTO, or with status {@code 400 (Bad Request)} if the instaAlert has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<InstaAlertDTO>> createInstaAlert(@Valid @RequestBody InstaAlertDTO instaAlertDTO) throws URISyntaxException {
        log.debug("REST request to save InstaAlert : {}", instaAlertDTO);
        if (instaAlertDTO.getId() != null) {
            throw new BadRequestAlertException("A new instaAlert cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return instaAlertService
            .save(instaAlertDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/insta-alerts/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /insta-alerts/:id} : Updates an existing instaAlert.
     *
     * @param id the id of the instaAlertDTO to save.
     * @param instaAlertDTO the instaAlertDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instaAlertDTO,
     * or with status {@code 400 (Bad Request)} if the instaAlertDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the instaAlertDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<InstaAlertDTO>> updateInstaAlert(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InstaAlertDTO instaAlertDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InstaAlert : {}, {}", id, instaAlertDTO);
        if (instaAlertDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, instaAlertDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return instaAlertRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return instaAlertService
                    .update(instaAlertDTO)
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
     * {@code PATCH  /insta-alerts/:id} : Partial updates given fields of an existing instaAlert, field will ignore if it is null
     *
     * @param id the id of the instaAlertDTO to save.
     * @param instaAlertDTO the instaAlertDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated instaAlertDTO,
     * or with status {@code 400 (Bad Request)} if the instaAlertDTO is not valid,
     * or with status {@code 404 (Not Found)} if the instaAlertDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the instaAlertDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<InstaAlertDTO>> partialUpdateInstaAlert(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InstaAlertDTO instaAlertDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InstaAlert partially : {}, {}", id, instaAlertDTO);
        if (instaAlertDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, instaAlertDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return instaAlertRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<InstaAlertDTO> result = instaAlertService.partialUpdate(instaAlertDTO);

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
     * {@code GET  /insta-alerts} : get all the instaAlerts.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of instaAlerts in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<InstaAlertDTO>>> getAllInstaAlerts(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of InstaAlerts");
        return instaAlertService
            .countAll()
            .zipWith(instaAlertService.findAll(pageable).collectList())
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
     * {@code GET  /insta-alerts/:id} : get the "id" instaAlert.
     *
     * @param id the id of the instaAlertDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the instaAlertDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<InstaAlertDTO>> getInstaAlert(@PathVariable("id") Long id) {
        log.debug("REST request to get InstaAlert : {}", id);
        Mono<InstaAlertDTO> instaAlertDTO = instaAlertService.findOne(id);
        return ResponseUtil.wrapOrNotFound(instaAlertDTO);
    }

    /**
     * {@code DELETE  /insta-alerts/:id} : delete the "id" instaAlert.
     *
     * @param id the id of the instaAlertDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteInstaAlert(@PathVariable("id") Long id) {
        log.debug("REST request to delete InstaAlert : {}", id);
        return instaAlertService
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
