package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.TradeRepository;
import in.pft.apis.creditbazaar.service.TradeService;
import in.pft.apis.creditbazaar.service.dto.TradeDTO;
import in.pft.apis.creditbazaar.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.Trade}.
 */
@RestController
@RequestMapping("/api/trades")
public class TradeResource {

    private final Logger log = LoggerFactory.getLogger(TradeResource.class);

    private static final String ENTITY_NAME = "trade";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradeService tradeService;

    private final TradeRepository tradeRepository;

    public TradeResource(TradeService tradeService, TradeRepository tradeRepository) {
        this.tradeService = tradeService;
        this.tradeRepository = tradeRepository;
    }

    /**
     * {@code POST  /trades} : Create a new trade.
     *
     * @param tradeDTO the tradeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradeDTO, or with status {@code 400 (Bad Request)} if the trade has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<TradeDTO>> createTrade(@RequestBody TradeDTO tradeDTO) throws URISyntaxException {
        log.debug("REST request to save Trade : {}", tradeDTO);
        if (tradeDTO.getId() != null) {
            throw new BadRequestAlertException("A new trade cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return tradeService
            .save(tradeDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/trades/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /trades/:id} : Updates an existing trade.
     *
     * @param id the id of the tradeDTO to save.
     * @param tradeDTO the tradeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeDTO,
     * or with status {@code 400 (Bad Request)} if the tradeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TradeDTO>> updateTrade(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeDTO tradeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Trade : {}, {}", id, tradeDTO);
        if (tradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return tradeService
                    .update(tradeDTO)
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
     * {@code PATCH  /trades/:id} : Partial updates given fields of an existing trade, field will ignore if it is null
     *
     * @param id the id of the tradeDTO to save.
     * @param tradeDTO the tradeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeDTO,
     * or with status {@code 400 (Bad Request)} if the tradeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tradeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<TradeDTO>> partialUpdateTrade(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeDTO tradeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Trade partially : {}, {}", id, tradeDTO);
        if (tradeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradeRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<TradeDTO> result = tradeService.partialUpdate(tradeDTO);

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
     * {@code GET  /trades} : get all the trades.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trades in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<TradeDTO>>> getAllTrades(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Trades");
        return tradeService
            .countAll()
            .zipWith(tradeService.findAll(pageable).collectList())
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
     * {@code GET  /trades/:id} : get the "id" trade.
     *
     * @param id the id of the tradeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TradeDTO>> getTrade(@PathVariable("id") Long id) {
        log.debug("REST request to get Trade : {}", id);
        Mono<TradeDTO> tradeDTO = tradeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tradeDTO);
    }

    /**
     * {@code DELETE  /trades/:id} : delete the "id" trade.
     *
     * @param id the id of the tradeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTrade(@PathVariable("id") Long id) {
        log.debug("REST request to delete Trade : {}", id);
        return tradeService
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
