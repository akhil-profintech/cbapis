package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.FinancePartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.FinancePartnerService;
import in.pft.apis.creditbazaar.gateway.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.gateway.web.rest.errors.BadRequestAlertException;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.FinancePartner}.
 */
@RestController
@RequestMapping("/api/finance-partners")
public class FinancePartnerResource {

    private final Logger log = LoggerFactory.getLogger(FinancePartnerResource.class);

    private static final String ENTITY_NAME = "financePartner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinancePartnerService financePartnerService;

    private final FinancePartnerRepository financePartnerRepository;

    public FinancePartnerResource(FinancePartnerService financePartnerService, FinancePartnerRepository financePartnerRepository) {
        this.financePartnerService = financePartnerService;
        this.financePartnerRepository = financePartnerRepository;
    }

    /**
     * {@code POST  /finance-partners} : Create a new financePartner.
     *
     * @param financePartnerDTO the financePartnerDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financePartnerDTO, or with status {@code 400 (Bad Request)} if the financePartner has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<FinancePartnerDTO>> createFinancePartner(@Valid @RequestBody FinancePartnerDTO financePartnerDTO)
        throws URISyntaxException {
        log.debug("REST request to save FinancePartner : {}", financePartnerDTO);
        if (financePartnerDTO.getId() != null) {
            throw new BadRequestAlertException("A new financePartner cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return financePartnerService
            .save(financePartnerDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/finance-partners/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /finance-partners/:id} : Updates an existing financePartner.
     *
     * @param id the id of the financePartnerDTO to save.
     * @param financePartnerDTO the financePartnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financePartnerDTO,
     * or with status {@code 400 (Bad Request)} if the financePartnerDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financePartnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<FinancePartnerDTO>> updateFinancePartner(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FinancePartnerDTO financePartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FinancePartner : {}, {}", id, financePartnerDTO);
        if (financePartnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financePartnerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return financePartnerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return financePartnerService
                    .update(financePartnerDTO)
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
     * {@code PATCH  /finance-partners/:id} : Partial updates given fields of an existing financePartner, field will ignore if it is null
     *
     * @param id the id of the financePartnerDTO to save.
     * @param financePartnerDTO the financePartnerDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financePartnerDTO,
     * or with status {@code 400 (Bad Request)} if the financePartnerDTO is not valid,
     * or with status {@code 404 (Not Found)} if the financePartnerDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the financePartnerDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<FinancePartnerDTO>> partialUpdateFinancePartner(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FinancePartnerDTO financePartnerDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FinancePartner partially : {}, {}", id, financePartnerDTO);
        if (financePartnerDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, financePartnerDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return financePartnerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<FinancePartnerDTO> result = financePartnerService.partialUpdate(financePartnerDTO);

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
     * {@code GET  /finance-partners} : get all the financePartners.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of financePartners in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<FinancePartnerDTO>>> getAllFinancePartners(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of FinancePartners");
        return financePartnerService
            .countAll()
            .zipWith(financePartnerService.findAll(pageable).collectList())
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
     * {@code GET  /finance-partners/:id} : get the "id" financePartner.
     *
     * @param id the id of the financePartnerDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financePartnerDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<FinancePartnerDTO>> getFinancePartner(@PathVariable("id") Long id) {
        log.debug("REST request to get FinancePartner : {}", id);
        Mono<FinancePartnerDTO> financePartnerDTO = financePartnerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(financePartnerDTO);
    }

    /**
     * {@code DELETE  /finance-partners/:id} : delete the "id" financePartner.
     *
     * @param id the id of the financePartnerDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteFinancePartner(@PathVariable("id") Long id) {
        log.debug("REST request to delete FinancePartner : {}", id);
        return financePartnerService
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
