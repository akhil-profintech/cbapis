package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.SettlementRepository;
import in.pft.apis.creditbazaar.service.SettlementService;
import in.pft.apis.creditbazaar.service.dto.SettlementDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.Settlement}.
 */
@RestController
@RequestMapping("/api/settlements")
public class SettlementResource {

    private final Logger log = LoggerFactory.getLogger(SettlementResource.class);

    private static final String ENTITY_NAME = "settlement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SettlementService settlementService;

    private final SettlementRepository settlementRepository;

    public SettlementResource(SettlementService settlementService, SettlementRepository settlementRepository) {
        this.settlementService = settlementService;
        this.settlementRepository = settlementRepository;
    }

    /**
     * {@code POST  /settlements} : Create a new settlement.
     *
     * @param settlementDTO the settlementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new settlementDTO, or with status {@code 400 (Bad Request)} if the settlement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<SettlementDTO>> createSettlement(@Valid @RequestBody SettlementDTO settlementDTO) throws URISyntaxException {
        log.debug("REST request to save Settlement : {}", settlementDTO);
        if (settlementDTO.getId() != null) {
            throw new BadRequestAlertException("A new settlement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return settlementService
            .save(settlementDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/settlements/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /settlements/:id} : Updates an existing settlement.
     *
     * @param id the id of the settlementDTO to save.
     * @param settlementDTO the settlementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated settlementDTO,
     * or with status {@code 400 (Bad Request)} if the settlementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the settlementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<SettlementDTO>> updateSettlement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SettlementDTO settlementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Settlement : {}, {}", id, settlementDTO);
        if (settlementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, settlementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return settlementRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return settlementService
                    .update(settlementDTO)
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
     * {@code PATCH  /settlements/:id} : Partial updates given fields of an existing settlement, field will ignore if it is null
     *
     * @param id the id of the settlementDTO to save.
     * @param settlementDTO the settlementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated settlementDTO,
     * or with status {@code 400 (Bad Request)} if the settlementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the settlementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the settlementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<SettlementDTO>> partialUpdateSettlement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SettlementDTO settlementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Settlement partially : {}, {}", id, settlementDTO);
        if (settlementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, settlementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return settlementRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<SettlementDTO> result = settlementService.partialUpdate(settlementDTO);

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
     * {@code GET  /settlements} : get all the settlements.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of settlements in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<SettlementDTO>>> getAllSettlements(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of Settlements");
        return settlementService
            .countAll()
            .zipWith(settlementService.findAll(pageable).collectList())
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
     * {@code GET  /settlements/:id} : get the "id" settlement.
     *
     * @param id the id of the settlementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the settlementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<SettlementDTO>> getSettlement(@PathVariable("id") Long id) {
        log.debug("REST request to get Settlement : {}", id);
        Mono<SettlementDTO> settlementDTO = settlementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(settlementDTO);
    }

    /**
     * {@code DELETE  /settlements/:id} : delete the "id" settlement.
     *
     * @param id the id of the settlementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteSettlement(@PathVariable("id") Long id) {
        log.debug("REST request to delete Settlement : {}", id);
        return settlementService
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
