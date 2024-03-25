package in.pft.apis.creditbazaar.gateway.web.rest.rest;

import in.pft.apis.creditbazaar.gateway.repository.TradeChannelRepository;
import in.pft.apis.creditbazaar.gateway.service.TradeChannelService;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeChannelDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.TradeChannel}.
 */
@RestController
@RequestMapping("/api/trade-channels")
public class TradeChannelResource {

    private final Logger log = LoggerFactory.getLogger(TradeChannelResource.class);

    private static final String ENTITY_NAME = "tradeChannel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradeChannelService tradeChannelService;

    private final TradeChannelRepository tradeChannelRepository;

    public TradeChannelResource(TradeChannelService tradeChannelService, TradeChannelRepository tradeChannelRepository) {
        this.tradeChannelService = tradeChannelService;
        this.tradeChannelRepository = tradeChannelRepository;
    }

    /**
     * {@code POST  /trade-channels} : Create a new tradeChannel.
     *
     * @param tradeChannelDTO the tradeChannelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradeChannelDTO, or with status {@code 400 (Bad Request)} if the tradeChannel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<TradeChannelDTO>> createTradeChannel(@RequestBody TradeChannelDTO tradeChannelDTO)
        throws URISyntaxException {
        log.debug("REST request to save TradeChannel : {}", tradeChannelDTO);
        if (tradeChannelDTO.getId() != null) {
            throw new BadRequestAlertException("A new tradeChannel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return tradeChannelService
            .save(tradeChannelDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/trade-channels/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /trade-channels/:id} : Updates an existing tradeChannel.
     *
     * @param id the id of the tradeChannelDTO to save.
     * @param tradeChannelDTO the tradeChannelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeChannelDTO,
     * or with status {@code 400 (Bad Request)} if the tradeChannelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradeChannelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TradeChannelDTO>> updateTradeChannel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeChannelDTO tradeChannelDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TradeChannel : {}, {}", id, tradeChannelDTO);
        if (tradeChannelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeChannelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradeChannelRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return tradeChannelService
                    .update(tradeChannelDTO)
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
     * {@code PATCH  /trade-channels/:id} : Partial updates given fields of an existing tradeChannel, field will ignore if it is null
     *
     * @param id the id of the tradeChannelDTO to save.
     * @param tradeChannelDTO the tradeChannelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeChannelDTO,
     * or with status {@code 400 (Bad Request)} if the tradeChannelDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tradeChannelDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradeChannelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<TradeChannelDTO>> partialUpdateTradeChannel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeChannelDTO tradeChannelDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TradeChannel partially : {}, {}", id, tradeChannelDTO);
        if (tradeChannelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeChannelDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradeChannelRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<TradeChannelDTO> result = tradeChannelService.partialUpdate(tradeChannelDTO);

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
     * {@code GET  /trade-channels} : get all the tradeChannels.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradeChannels in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<TradeChannelDTO>>> getAllTradeChannels(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of TradeChannels");
        return tradeChannelService
            .countAll()
            .zipWith(tradeChannelService.findAll(pageable).collectList())
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
     * {@code GET  /trade-channels/:id} : get the "id" tradeChannel.
     *
     * @param id the id of the tradeChannelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradeChannelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TradeChannelDTO>> getTradeChannel(@PathVariable("id") Long id) {
        log.debug("REST request to get TradeChannel : {}", id);
        Mono<TradeChannelDTO> tradeChannelDTO = tradeChannelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tradeChannelDTO);
    }

    /**
     * {@code DELETE  /trade-channels/:id} : delete the "id" tradeChannel.
     *
     * @param id the id of the tradeChannelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTradeChannel(@PathVariable("id") Long id) {
        log.debug("REST request to delete TradeChannel : {}", id);
        return tradeChannelService
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
