package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.RequestOfferRepository;
import in.pft.apis.creditbazaar.service.RequestOfferService;
import in.pft.apis.creditbazaar.service.dto.RequestOfferDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.RequestOffer}.
 */
@RestController
@RequestMapping("/api/request-offers")
public class RequestOfferResource {

    private final Logger log = LoggerFactory.getLogger(RequestOfferResource.class);

    private static final String ENTITY_NAME = "requestOffer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestOfferService requestOfferService;

    private final RequestOfferRepository requestOfferRepository;

    public RequestOfferResource(RequestOfferService requestOfferService, RequestOfferRepository requestOfferRepository) {
        this.requestOfferService = requestOfferService;
        this.requestOfferRepository = requestOfferRepository;
    }

    /**
     * {@code POST  /request-offers} : Create a new requestOffer.
     *
     * @param requestOfferDTO the requestOfferDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestOfferDTO, or with status {@code 400 (Bad Request)} if the requestOffer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<RequestOfferDTO>> createRequestOffer(@Valid @RequestBody RequestOfferDTO requestOfferDTO)
        throws URISyntaxException {
        log.debug("REST request to save RequestOffer : {}", requestOfferDTO);
        if (requestOfferDTO.getId() != null) {
            throw new BadRequestAlertException("A new requestOffer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return requestOfferService
            .save(requestOfferDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/request-offers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /request-offers/:id} : Updates an existing requestOffer.
     *
     * @param id the id of the requestOfferDTO to save.
     * @param requestOfferDTO the requestOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestOfferDTO,
     * or with status {@code 400 (Bad Request)} if the requestOfferDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<RequestOfferDTO>> updateRequestOffer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RequestOfferDTO requestOfferDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RequestOffer : {}, {}", id, requestOfferDTO);
        if (requestOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestOfferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return requestOfferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return requestOfferService
                    .update(requestOfferDTO)
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
     * {@code PATCH  /request-offers/:id} : Partial updates given fields of an existing requestOffer, field will ignore if it is null
     *
     * @param id the id of the requestOfferDTO to save.
     * @param requestOfferDTO the requestOfferDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestOfferDTO,
     * or with status {@code 400 (Bad Request)} if the requestOfferDTO is not valid,
     * or with status {@code 404 (Not Found)} if the requestOfferDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the requestOfferDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<RequestOfferDTO>> partialUpdateRequestOffer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RequestOfferDTO requestOfferDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RequestOffer partially : {}, {}", id, requestOfferDTO);
        if (requestOfferDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestOfferDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return requestOfferRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<RequestOfferDTO> result = requestOfferService.partialUpdate(requestOfferDTO);

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
     * {@code GET  /request-offers} : get all the requestOffers.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestOffers in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<RequestOfferDTO>>> getAllRequestOffers(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of RequestOffers");
        return requestOfferService
            .countAll()
            .zipWith(requestOfferService.findAll(pageable).collectList())
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
     * {@code GET  /request-offers/:id} : get the "id" requestOffer.
     *
     * @param id the id of the requestOfferDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestOfferDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<RequestOfferDTO>> getRequestOffer(@PathVariable("id") Long id) {
        log.debug("REST request to get RequestOffer : {}", id);
        Mono<RequestOfferDTO> requestOfferDTO = requestOfferService.findOne(id);
        return ResponseUtil.wrapOrNotFound(requestOfferDTO);
    }

    /**
     * {@code DELETE  /request-offers/:id} : delete the "id" requestOffer.
     *
     * @param id the id of the requestOfferDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteRequestOffer(@PathVariable("id") Long id) {
        log.debug("REST request to delete RequestOffer : {}", id);
        return requestOfferService
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
