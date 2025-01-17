package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.PlacedOfferRepository;
import in.pft.apis.creditbazaar.gateway.service.PlacedOfferService;
import in.pft.apis.creditbazaar.gateway.service.dto.PlacedOfferDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.PlacedOffer}.
 */
@RestController
@RequestMapping("/api/placed-offers")
public class PlacedOfferResource {

    private final Logger log = LoggerFactory.getLogger(PlacedOfferResource.class);

    private static final String ENTITY_NAME = "placedOffer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PlacedOfferService placedOfferService;

    private final PlacedOfferRepository placedOfferRepository;

    public PlacedOfferResource(PlacedOfferService placedOfferService, PlacedOfferRepository placedOfferRepository) {
        this.placedOfferService = placedOfferService;
        this.placedOfferRepository = placedOfferRepository;
    }

    /**
     * {@code POST  /placed-offers} : Create a new placedOffer.
     *
     * @param placedOfferDTO the placedOfferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new placedOfferDTO, or with status {@code 400 (Bad Request)} if the placedOffer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<PlacedOfferDTO>> createPlacedOffer(@Valid @RequestBody PlacedOfferDTO placedOfferDTO)
        throws URISyntaxException {
        log.debug("REST request to save PlacedOffer : {}", placedOfferDTO);
        if (placedOfferDTO.getId() != null) {
            throw new BadRequestAlertException("A new placedOffer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return placedOfferService
            .save(placedOfferDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/placed-offers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /placed-offers/:id} : Updates an existing placedOffer.
     *
     * @param id the id of the placedOfferDTO to save.
     * @param placedOfferDTO the placedOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated placedOfferDTO,
     * or with status {@code 400 (Bad Request)} if the placedOfferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the placedOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<PlacedOfferDTO>> updatePlacedOffer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PlacedOfferDTO placedOfferDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PlacedOffer : {}, {}", id, placedOfferDTO);
        if (placedOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, placedOfferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return placedOfferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return placedOfferService
                    .update(placedOfferDTO)
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
     * {@code PATCH  /placed-offers/:id} : Partial updates given fields of an existing placedOffer, field will ignore if it is null
     *
     * @param id the id of the placedOfferDTO to save.
     * @param placedOfferDTO the placedOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated placedOfferDTO,
     * or with status {@code 400 (Bad Request)} if the placedOfferDTO is not valid,
     * or with status {@code 404 (Not Found)} if the placedOfferDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the placedOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PlacedOfferDTO>> partialUpdatePlacedOffer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PlacedOfferDTO placedOfferDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PlacedOffer partially : {}, {}", id, placedOfferDTO);
        if (placedOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, placedOfferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return placedOfferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PlacedOfferDTO> result = placedOfferService.partialUpdate(placedOfferDTO);

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
     * {@code GET  /placed-offers} : get all the placedOffers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of placedOffers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<PlacedOfferDTO>>> getAllPlacedOffers(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of PlacedOffers");
        if (StringUtils.isEmpty(filter)) {
        return placedOfferService
            .countAll()
            .zipWith(placedOfferService.findAll(pageable).collectList())
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
            return placedOfferService
                .countAllByFilter(filter)
                .zipWith(placedOfferService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /placed-offers/:id} : get the "id" placedOffer.
     *
     * @param id the id of the placedOfferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the placedOfferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<PlacedOfferDTO>> getPlacedOffer(@PathVariable("id") Long id) {
        log.debug("REST request to get PlacedOffer : {}", id);
        Mono<PlacedOfferDTO> placedOfferDTO = placedOfferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(placedOfferDTO);
    }

    /**
     * {@code DELETE  /placed-offers/:id} : delete the "id" placedOffer.
     *
     * @param id the id of the placedOfferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePlacedOffer(@PathVariable("id") Long id) {
        log.debug("REST request to delete PlacedOffer : {}", id);
        return placedOfferService
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
