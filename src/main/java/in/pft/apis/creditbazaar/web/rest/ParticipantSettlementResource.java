package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.ParticipantSettlementRepository;
import in.pft.apis.creditbazaar.service.ParticipantSettlementService;
import in.pft.apis.creditbazaar.service.dto.ParticipantSettlementDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.ParticipantSettlement}.
 */
@RestController
@RequestMapping("/api/participant-settlements")
public class ParticipantSettlementResource {

    private final Logger log = LoggerFactory.getLogger(ParticipantSettlementResource.class);

    private static final String ENTITY_NAME = "participantSettlement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParticipantSettlementService participantSettlementService;

    private final ParticipantSettlementRepository participantSettlementRepository;

    public ParticipantSettlementResource(
        ParticipantSettlementService participantSettlementService,
        ParticipantSettlementRepository participantSettlementRepository
    ) {
        this.participantSettlementService = participantSettlementService;
        this.participantSettlementRepository = participantSettlementRepository;
    }

    /**
     * {@code POST  /participant-settlements} : Create a new participantSettlement.
     *
     * @param participantSettlementDTO the participantSettlementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new participantSettlementDTO, or with status {@code 400 (Bad Request)} if the participantSettlement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ParticipantSettlementDTO>> createParticipantSettlement(
        @Valid @RequestBody ParticipantSettlementDTO participantSettlementDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ParticipantSettlement : {}", participantSettlementDTO);
        if (participantSettlementDTO.getId() != null) {
            throw new BadRequestAlertException("A new participantSettlement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return participantSettlementService
            .save(participantSettlementDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/participant-settlements/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /participant-settlements/:id} : Updates an existing participantSettlement.
     *
     * @param id the id of the participantSettlementDTO to save.
     * @param participantSettlementDTO the participantSettlementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated participantSettlementDTO,
     * or with status {@code 400 (Bad Request)} if the participantSettlementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the participantSettlementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ParticipantSettlementDTO>> updateParticipantSettlement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ParticipantSettlementDTO participantSettlementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ParticipantSettlement : {}, {}", id, participantSettlementDTO);
        if (participantSettlementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, participantSettlementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return participantSettlementRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return participantSettlementService
                    .update(participantSettlementDTO)
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
     * {@code PATCH  /participant-settlements/:id} : Partial updates given fields of an existing participantSettlement, field will ignore if it is null
     *
     * @param id the id of the participantSettlementDTO to save.
     * @param participantSettlementDTO the participantSettlementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated participantSettlementDTO,
     * or with status {@code 400 (Bad Request)} if the participantSettlementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the participantSettlementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the participantSettlementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ParticipantSettlementDTO>> partialUpdateParticipantSettlement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ParticipantSettlementDTO participantSettlementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ParticipantSettlement partially : {}, {}", id, participantSettlementDTO);
        if (participantSettlementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, participantSettlementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return participantSettlementRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ParticipantSettlementDTO> result = participantSettlementService.partialUpdate(participantSettlementDTO);

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
     * {@code GET  /participant-settlements} : get all the participantSettlements.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of participantSettlements in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ParticipantSettlementDTO>>> getAllParticipantSettlements(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ParticipantSettlements");
        return participantSettlementService
            .countAll()
            .zipWith(participantSettlementService.findAll(pageable).collectList())
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
     * {@code GET  /participant-settlements/:id} : get the "id" participantSettlement.
     *
     * @param id the id of the participantSettlementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the participantSettlementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ParticipantSettlementDTO>> getParticipantSettlement(@PathVariable("id") Long id) {
        log.debug("REST request to get ParticipantSettlement : {}", id);
        Mono<ParticipantSettlementDTO> participantSettlementDTO = participantSettlementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(participantSettlementDTO);
    }

    /**
     * {@code DELETE  /participant-settlements/:id} : delete the "id" participantSettlement.
     *
     * @param id the id of the participantSettlementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteParticipantSettlement(@PathVariable("id") Long id) {
        log.debug("REST request to delete ParticipantSettlement : {}", id);
        return participantSettlementService
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
