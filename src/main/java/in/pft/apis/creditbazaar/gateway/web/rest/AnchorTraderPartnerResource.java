package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.AnchorTraderPartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.AnchorTraderPartnerService;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderPartnerDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner}.
 */
@RestController
@RequestMapping("/api/anchor-trader-partners")
public class AnchorTraderPartnerResource {

    private final Logger log = LoggerFactory.getLogger(AnchorTraderPartnerResource.class);

    private static final String ENTITY_NAME = "anchorTraderPartner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnchorTraderPartnerService anchorTraderPartnerService;

    private final AnchorTraderPartnerRepository anchorTraderPartnerRepository;

    public AnchorTraderPartnerResource(
        AnchorTraderPartnerService anchorTraderPartnerService,
        AnchorTraderPartnerRepository anchorTraderPartnerRepository
    ) {
        this.anchorTraderPartnerService = anchorTraderPartnerService;
        this.anchorTraderPartnerRepository = anchorTraderPartnerRepository;
    }

    /**
     * {@code POST  /anchor-trader-partners} : Create a new anchorTraderPartner.
     *
     * @param anchorTraderPartnerDTO the anchorTraderPartnerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new anchorTraderPartnerDTO, or with status {@code 400 (Bad Request)} if the anchorTraderPartner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<AnchorTraderPartnerDTO>> createAnchorTraderPartner(
        @RequestBody AnchorTraderPartnerDTO anchorTraderPartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AnchorTraderPartner : {}", anchorTraderPartnerDTO);
        if (anchorTraderPartnerDTO.getId() != null) {
            throw new BadRequestAlertException("A new anchorTraderPartner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return anchorTraderPartnerService
            .save(anchorTraderPartnerDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/anchor-trader-partners/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /anchor-trader-partners/:id} : Updates an existing anchorTraderPartner.
     *
     * @param id the id of the anchorTraderPartnerDTO to save.
     * @param anchorTraderPartnerDTO the anchorTraderPartnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anchorTraderPartnerDTO,
     * or with status {@code 400 (Bad Request)} if the anchorTraderPartnerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the anchorTraderPartnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<AnchorTraderPartnerDTO>> updateAnchorTraderPartner(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnchorTraderPartnerDTO anchorTraderPartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AnchorTraderPartner : {}, {}", id, anchorTraderPartnerDTO);
        if (anchorTraderPartnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, anchorTraderPartnerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return anchorTraderPartnerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return anchorTraderPartnerService
                    .update(anchorTraderPartnerDTO)
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
     * {@code PATCH  /anchor-trader-partners/:id} : Partial updates given fields of an existing anchorTraderPartner, field will ignore if it is null
     *
     * @param id the id of the anchorTraderPartnerDTO to save.
     * @param anchorTraderPartnerDTO the anchorTraderPartnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated anchorTraderPartnerDTO,
     * or with status {@code 400 (Bad Request)} if the anchorTraderPartnerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the anchorTraderPartnerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the anchorTraderPartnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AnchorTraderPartnerDTO>> partialUpdateAnchorTraderPartner(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AnchorTraderPartnerDTO anchorTraderPartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AnchorTraderPartner partially : {}, {}", id, anchorTraderPartnerDTO);
        if (anchorTraderPartnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, anchorTraderPartnerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return anchorTraderPartnerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AnchorTraderPartnerDTO> result = anchorTraderPartnerService.partialUpdate(anchorTraderPartnerDTO);

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
     * {@code GET  /anchor-trader-partners} : get all the anchorTraderPartners.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of anchorTraderPartners in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<AnchorTraderPartnerDTO>>> getAllAnchorTraderPartners(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of AnchorTraderPartners");
        if (StringUtils.isEmpty(filter)) {
        return anchorTraderPartnerService
            .countAll()
            .zipWith(anchorTraderPartnerService.findAll(pageable).collectList())
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
                return anchorTraderPartnerService
                    .countAllByFilter(filter)
                    .zipWith(anchorTraderPartnerService.findAllByFilter(filter, pageable).collectList())
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
     * {@code GET  /anchor-trader-partners/:id} : get the "id" anchorTraderPartner.
     *
     * @param id the id of the anchorTraderPartnerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the anchorTraderPartnerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<AnchorTraderPartnerDTO>> getAnchorTraderPartner(@PathVariable("id") Long id) {
        log.debug("REST request to get AnchorTraderPartner : {}", id);
        Mono<AnchorTraderPartnerDTO> anchorTraderPartnerDTO = anchorTraderPartnerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(anchorTraderPartnerDTO);
    }

    /**
     * {@code DELETE  /anchor-trader-partners/:id} : delete the "id" anchorTraderPartner.
     *
     * @param id the id of the anchorTraderPartnerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteAnchorTraderPartner(@PathVariable("id") Long id) {
        log.debug("REST request to delete AnchorTraderPartner : {}", id);
        return anchorTraderPartnerService
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
