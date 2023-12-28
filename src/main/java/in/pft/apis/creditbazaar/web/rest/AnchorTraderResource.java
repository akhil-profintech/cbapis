package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.AnchorTraderRepository;
import in.pft.apis.creditbazaar.service.AnchorTraderService;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.AnchorTrader}.
 */
@RestController
@RequestMapping("/api/anchor-traders")
public class AnchorTraderResource {

    private final Logger log = LoggerFactory.getLogger(AnchorTraderResource.class);

    private static final String ENTITY_NAME = "anchorTrader";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnchorTraderService anchorTraderService;

    private final AnchorTraderRepository anchorTraderRepository;

    public AnchorTraderResource(AnchorTraderService anchorTraderService, AnchorTraderRepository anchorTraderRepository) {
        this.anchorTraderService = anchorTraderService;
        this.anchorTraderRepository = anchorTraderRepository;
    }

    /**
     * {@code POST  /anchor-traders} : Create a new anchorTrader.
     *
     * @param anchorTraderDTO the anchorTraderDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new anchorTraderDTO, or with status {@code 400 (Bad Request)} if the anchorTrader has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<AnchorTraderDTO>> createAnchorTrader(@Valid @RequestBody AnchorTraderDTO anchorTraderDTO)
        throws URISyntaxException {
        log.debug("REST request to save AnchorTrader : {}", anchorTraderDTO);
        if (anchorTraderDTO.getId() != null) {
            throw new BadRequestAlertException("A new anchorTrader cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return anchorTraderService
            .save(anchorTraderDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/anchor-traders/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /anchor-traders/:id} : Updates an existing anchorTrader.
     *
     * @param id the id of the anchorTraderDTO to save.
     * @param anchorTraderDTO the anchorTraderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anchorTraderDTO,
     * or with status {@code 400 (Bad Request)} if the anchorTraderDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the anchorTraderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<AnchorTraderDTO>> updateAnchorTrader(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AnchorTraderDTO anchorTraderDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnchorTrader : {}, {}", id, anchorTraderDTO);
        if (anchorTraderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, anchorTraderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return anchorTraderRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return anchorTraderService
                    .update(anchorTraderDTO)
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
     * {@code PATCH  /anchor-traders/:id} : Partial updates given fields of an existing anchorTrader, field will ignore if it is null
     *
     * @param id the id of the anchorTraderDTO to save.
     * @param anchorTraderDTO the anchorTraderDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anchorTraderDTO,
     * or with status {@code 400 (Bad Request)} if the anchorTraderDTO is not valid,
     * or with status {@code 404 (Not Found)} if the anchorTraderDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the anchorTraderDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AnchorTraderDTO>> partialUpdateAnchorTrader(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AnchorTraderDTO anchorTraderDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnchorTrader partially : {}, {}", id, anchorTraderDTO);
        if (anchorTraderDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, anchorTraderDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return anchorTraderRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AnchorTraderDTO> result = anchorTraderService.partialUpdate(anchorTraderDTO);

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
     * {@code GET  /anchor-traders} : get all the anchorTraders.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of anchorTraders in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<AnchorTraderDTO>>> getAllAnchorTraders(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of AnchorTraders");
        return anchorTraderService
            .countAll()
            .zipWith(anchorTraderService.findAll(pageable).collectList())
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
     * {@code GET  /anchor-traders/:id} : get the "id" anchorTrader.
     *
     * @param id the id of the anchorTraderDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the anchorTraderDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<AnchorTraderDTO>> getAnchorTrader(@PathVariable("id") Long id) {
        log.debug("REST request to get AnchorTrader : {}", id);
        Mono<AnchorTraderDTO> anchorTraderDTO = anchorTraderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anchorTraderDTO);
    }

    /**
     * {@code DELETE  /anchor-traders/:id} : delete the "id" anchorTrader.
     *
     * @param id the id of the anchorTraderDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAnchorTrader(@PathVariable("id") Long id) {
        log.debug("REST request to delete AnchorTrader : {}", id);
        return anchorTraderService
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
