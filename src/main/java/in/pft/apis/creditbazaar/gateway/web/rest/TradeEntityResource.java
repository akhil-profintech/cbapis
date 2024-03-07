package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.TradeEntityRepository;
import in.pft.apis.creditbazaar.gateway.service.TradeEntityService;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeEntityDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.TradeEntity}.
 */
@RestController
@RequestMapping("/api/trade-entities")
public class TradeEntityResource {

    private final Logger log = LoggerFactory.getLogger(TradeEntityResource.class);

    private static final String ENTITY_NAME = "tradeEntity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradeEntityService tradeEntityService;

    private final TradeEntityRepository tradeEntityRepository;

    public TradeEntityResource(TradeEntityService tradeEntityService, TradeEntityRepository tradeEntityRepository) {
        this.tradeEntityService = tradeEntityService;
        this.tradeEntityRepository = tradeEntityRepository;
    }

    /**
     * {@code POST  /trade-entities} : Create a new tradeEntity.
     *
     * @param tradeEntityDTO the tradeEntityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradeEntityDTO, or with status {@code 400 (Bad Request)} if the tradeEntity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<TradeEntityDTO>> createTradeEntity(@RequestBody TradeEntityDTO tradeEntityDTO) throws URISyntaxException {
        log.debug("REST request to save TradeEntity : {}", tradeEntityDTO);
        if (tradeEntityDTO.getId() != null) {
            throw new BadRequestAlertException("A new tradeEntity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return tradeEntityService
            .save(tradeEntityDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/trade-entities/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /trade-entities/:id} : Updates an existing tradeEntity.
     *
     * @param id the id of the tradeEntityDTO to save.
     * @param tradeEntityDTO the tradeEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeEntityDTO,
     * or with status {@code 400 (Bad Request)} if the tradeEntityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradeEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TradeEntityDTO>> updateTradeEntity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeEntityDTO tradeEntityDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TradeEntity : {}, {}", id, tradeEntityDTO);
        if (tradeEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeEntityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradeEntityRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return tradeEntityService
                    .update(tradeEntityDTO)
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
     * {@code PATCH  /trade-entities/:id} : Partial updates given fields of an existing tradeEntity, field will ignore if it is null
     *
     * @param id the id of the tradeEntityDTO to save.
     * @param tradeEntityDTO the tradeEntityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeEntityDTO,
     * or with status {@code 400 (Bad Request)} if the tradeEntityDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tradeEntityDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradeEntityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<TradeEntityDTO>> partialUpdateTradeEntity(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeEntityDTO tradeEntityDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TradeEntity partially : {}, {}", id, tradeEntityDTO);
        if (tradeEntityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeEntityDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradeEntityRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<TradeEntityDTO> result = tradeEntityService.partialUpdate(tradeEntityDTO);

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
     * {@code GET  /trade-entities} : get all the tradeEntities.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradeEntities in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<TradeEntityDTO>>> getAllTradeEntities(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of TradeEntities");
        return tradeEntityService
            .countAll()
            .zipWith(tradeEntityService.findAll(pageable).collectList())
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
     * {@code GET  /trade-entities/:id} : get the "id" tradeEntity.
     *
     * @param id the id of the tradeEntityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradeEntityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TradeEntityDTO>> getTradeEntity(@PathVariable("id") Long id) {
        log.debug("REST request to get TradeEntity : {}", id);
        Mono<TradeEntityDTO> tradeEntityDTO = tradeEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tradeEntityDTO);
    }

    /**
     * {@code DELETE  /trade-entities/:id} : delete the "id" tradeEntity.
     *
     * @param id the id of the tradeEntityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTradeEntity(@PathVariable("id") Long id) {
        log.debug("REST request to delete TradeEntity : {}", id);
        return tradeEntityService
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
