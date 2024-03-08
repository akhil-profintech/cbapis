package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.TradePartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.TradePartnerService;
import in.pft.apis.creditbazaar.gateway.service.dto.TradePartnerDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.TradePartner}.
 */
@RestController
@RequestMapping("/api/trade-partners")
public class TradePartnerResource {

    private final Logger log = LoggerFactory.getLogger(TradePartnerResource.class);

    private static final String ENTITY_NAME = "tradePartner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradePartnerService tradePartnerService;

    private final TradePartnerRepository tradePartnerRepository;

    public TradePartnerResource(TradePartnerService tradePartnerService, TradePartnerRepository tradePartnerRepository) {
        this.tradePartnerService = tradePartnerService;
        this.tradePartnerRepository = tradePartnerRepository;
    }

    /**
     * {@code POST  /trade-partners} : Create a new tradePartner.
     *
     * @param tradePartnerDTO the tradePartnerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradePartnerDTO, or with status {@code 400 (Bad Request)} if the tradePartner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<TradePartnerDTO>> createTradePartner(@Valid @RequestBody TradePartnerDTO tradePartnerDTO)
        throws URISyntaxException {
        log.debug("REST request to save TradePartner : {}", tradePartnerDTO);
        if (tradePartnerDTO.getId() != null) {
            throw new BadRequestAlertException("A new tradePartner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return tradePartnerService
            .save(tradePartnerDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/trade-partners/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /trade-partners/:id} : Updates an existing tradePartner.
     *
     * @param id the id of the tradePartnerDTO to save.
     * @param tradePartnerDTO the tradePartnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradePartnerDTO,
     * or with status {@code 400 (Bad Request)} if the tradePartnerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradePartnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<TradePartnerDTO>> updateTradePartner(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TradePartnerDTO tradePartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TradePartner : {}, {}", id, tradePartnerDTO);
        if (tradePartnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradePartnerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradePartnerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return tradePartnerService
                    .update(tradePartnerDTO)
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
     * {@code PATCH  /trade-partners/:id} : Partial updates given fields of an existing tradePartner, field will ignore if it is null
     *
     * @param id the id of the tradePartnerDTO to save.
     * @param tradePartnerDTO the tradePartnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradePartnerDTO,
     * or with status {@code 400 (Bad Request)} if the tradePartnerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tradePartnerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradePartnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<TradePartnerDTO>> partialUpdateTradePartner(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody TradePartnerDTO tradePartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TradePartner partially : {}, {}", id, tradePartnerDTO);
        if (tradePartnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradePartnerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return tradePartnerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<TradePartnerDTO> result = tradePartnerService.partialUpdate(tradePartnerDTO);

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
     * {@code GET  /trade-partners} : get all the tradePartners.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradePartners in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<TradePartnerDTO>>> getAllTradePartners(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of TradePartners");
        if (StringUtils.isEmpty(filter)) {
        return tradePartnerService
            .countAll()
            .zipWith(tradePartnerService.findAll(pageable).collectList())
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
            return tradePartnerService
                .countAllByFilter(filter)
                .zipWith(tradePartnerService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /trade-partners/:id} : get the "id" tradePartner.
     *
     * @param id the id of the tradePartnerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradePartnerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<TradePartnerDTO>> getTradePartner(@PathVariable("id") Long id) {
        log.debug("REST request to get TradePartner : {}", id);
        Mono<TradePartnerDTO> tradePartnerDTO = tradePartnerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tradePartnerDTO);
    }

    /**
     * {@code DELETE  /trade-partners/:id} : delete the "id" tradePartner.
     *
     * @param id the id of the tradePartnerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteTradePartner(@PathVariable("id") Long id) {
        log.debug("REST request to delete TradePartner : {}", id);
        return tradePartnerService
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
