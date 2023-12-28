package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.AcceptedOfferRepository;
import in.pft.apis.creditbazaar.service.AcceptedOfferService;
import in.pft.apis.creditbazaar.service.dto.AcceptedOfferDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.AcceptedOffer}.
 */
@RestController
@RequestMapping("/api/accepted-offers")
public class AcceptedOfferResource {

    private final Logger log = LoggerFactory.getLogger(AcceptedOfferResource.class);

    private static final String ENTITY_NAME = "acceptedOffer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AcceptedOfferService acceptedOfferService;

    private final AcceptedOfferRepository acceptedOfferRepository;

    public AcceptedOfferResource(AcceptedOfferService acceptedOfferService, AcceptedOfferRepository acceptedOfferRepository) {
        this.acceptedOfferService = acceptedOfferService;
        this.acceptedOfferRepository = acceptedOfferRepository;
    }

    /**
     * {@code POST  /accepted-offers} : Create a new acceptedOffer.
     *
     * @param acceptedOfferDTO the acceptedOfferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new acceptedOfferDTO, or with status {@code 400 (Bad Request)} if the acceptedOffer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<AcceptedOfferDTO>> createAcceptedOffer(@Valid @RequestBody AcceptedOfferDTO acceptedOfferDTO)
        throws URISyntaxException {
        log.debug("REST request to save AcceptedOffer : {}", acceptedOfferDTO);
        if (acceptedOfferDTO.getId() != null) {
            throw new BadRequestAlertException("A new acceptedOffer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return acceptedOfferService
            .save(acceptedOfferDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/accepted-offers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /accepted-offers/:id} : Updates an existing acceptedOffer.
     *
     * @param id the id of the acceptedOfferDTO to save.
     * @param acceptedOfferDTO the acceptedOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acceptedOfferDTO,
     * or with status {@code 400 (Bad Request)} if the acceptedOfferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the acceptedOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<AcceptedOfferDTO>> updateAcceptedOffer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AcceptedOfferDTO acceptedOfferDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AcceptedOffer : {}, {}", id, acceptedOfferDTO);
        if (acceptedOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acceptedOfferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return acceptedOfferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return acceptedOfferService
                    .update(acceptedOfferDTO)
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
     * {@code PATCH  /accepted-offers/:id} : Partial updates given fields of an existing acceptedOffer, field will ignore if it is null
     *
     * @param id the id of the acceptedOfferDTO to save.
     * @param acceptedOfferDTO the acceptedOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated acceptedOfferDTO,
     * or with status {@code 400 (Bad Request)} if the acceptedOfferDTO is not valid,
     * or with status {@code 404 (Not Found)} if the acceptedOfferDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the acceptedOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AcceptedOfferDTO>> partialUpdateAcceptedOffer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AcceptedOfferDTO acceptedOfferDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AcceptedOffer partially : {}, {}", id, acceptedOfferDTO);
        if (acceptedOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, acceptedOfferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return acceptedOfferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AcceptedOfferDTO> result = acceptedOfferService.partialUpdate(acceptedOfferDTO);

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
     * {@code GET  /accepted-offers} : get all the acceptedOffers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of acceptedOffers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<AcceptedOfferDTO>>> getAllAcceptedOffers(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of AcceptedOffers");
        return acceptedOfferService
            .countAll()
            .zipWith(acceptedOfferService.findAll(pageable).collectList())
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
     * {@code GET  /accepted-offers/:id} : get the "id" acceptedOffer.
     *
     * @param id the id of the acceptedOfferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the acceptedOfferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<AcceptedOfferDTO>> getAcceptedOffer(@PathVariable("id") Long id) {
        log.debug("REST request to get AcceptedOffer : {}", id);
        Mono<AcceptedOfferDTO> acceptedOfferDTO = acceptedOfferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(acceptedOfferDTO);
    }

    /**
     * {@code DELETE  /accepted-offers/:id} : delete the "id" acceptedOffer.
     *
     * @param id the id of the acceptedOfferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAcceptedOffer(@PathVariable("id") Long id) {
        log.debug("REST request to delete AcceptedOffer : {}", id);
        return acceptedOfferService
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
