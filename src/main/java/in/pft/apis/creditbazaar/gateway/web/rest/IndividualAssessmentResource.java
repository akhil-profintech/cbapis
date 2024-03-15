package in.pft.apis.creditbazaar.gateway.web.rest;

import in.pft.apis.creditbazaar.gateway.repository.IndividualAssessmentRepository;
import in.pft.apis.creditbazaar.gateway.service.IndividualAssessmentService;
import in.pft.apis.creditbazaar.gateway.service.dto.IndividualAssessmentDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment}.
 */
@RestController
@RequestMapping("/api/individual-assessments")
public class IndividualAssessmentResource {

    private final Logger log = LoggerFactory.getLogger(IndividualAssessmentResource.class);

    private static final String ENTITY_NAME = "individualAssessment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IndividualAssessmentService individualAssessmentService;

    private final IndividualAssessmentRepository individualAssessmentRepository;

    public IndividualAssessmentResource(
        IndividualAssessmentService individualAssessmentService,
        IndividualAssessmentRepository individualAssessmentRepository
    ) {
        this.individualAssessmentService = individualAssessmentService;
        this.individualAssessmentRepository = individualAssessmentRepository;
    }

    /**
     * {@code POST  /individual-assessments} : Create a new individualAssessment.
     *
     * @param individualAssessmentDTO the individualAssessmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new individualAssessmentDTO, or with status {@code 400 (Bad Request)} if the individualAssessment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<IndividualAssessmentDTO>> createIndividualAssessment(
        @RequestBody IndividualAssessmentDTO individualAssessmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to save IndividualAssessment : {}", individualAssessmentDTO);
        if (individualAssessmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new individualAssessment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return individualAssessmentService
            .save(individualAssessmentDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/individual-assessments/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /individual-assessments/:id} : Updates an existing individualAssessment.
     *
     * @param id the id of the individualAssessmentDTO to save.
     * @param individualAssessmentDTO the individualAssessmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated individualAssessmentDTO,
     * or with status {@code 400 (Bad Request)} if the individualAssessmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the individualAssessmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<IndividualAssessmentDTO>> updateIndividualAssessment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody IndividualAssessmentDTO individualAssessmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to update IndividualAssessment : {}, {}", id, individualAssessmentDTO);
        if (individualAssessmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, individualAssessmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return individualAssessmentRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return individualAssessmentService
                    .update(individualAssessmentDTO)
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
     * {@code PATCH  /individual-assessments/:id} : Partial updates given fields of an existing individualAssessment, field will ignore if it is null
     *
     * @param id the id of the individualAssessmentDTO to save.
     * @param individualAssessmentDTO the individualAssessmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated individualAssessmentDTO,
     * or with status {@code 400 (Bad Request)} if the individualAssessmentDTO is not valid,
     * or with status {@code 404 (Not Found)} if the individualAssessmentDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the individualAssessmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<IndividualAssessmentDTO>> partialUpdateIndividualAssessment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody IndividualAssessmentDTO individualAssessmentDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update IndividualAssessment partially : {}, {}", id, individualAssessmentDTO);
        if (individualAssessmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, individualAssessmentDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return individualAssessmentRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<IndividualAssessmentDTO> result = individualAssessmentService.partialUpdate(individualAssessmentDTO);

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
     * {@code GET  /individual-assessments} : get all the individualAssessments.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of individualAssessments in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<IndividualAssessmentDTO>>> getAllIndividualAssessments(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(value = "filter",required = false) String filter,
        ServerHttpRequest request,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of IndividualAssessments");
        if (StringUtils.isEmpty(filter)) {
        return individualAssessmentService
            .countAll()
            .zipWith(individualAssessmentService.findAll(pageable).collectList())
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
            return individualAssessmentService
                .countAllByFilter(filter)
                .zipWith(individualAssessmentService.findAllByFilter(filter, pageable).collectList())
                .map(countWithEntities ->
                    ResponseEntity
                        .ok()
                        .headers(
                            PaginationUtil.generatePaginationHttpHeaders(
                                ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                                new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                            )
                        )
                        .body(countWithEntities.getT2()));
        }
    }

    /**
     * {@code GET  /individual-assessments/:id} : get the "id" individualAssessment.
     *
     * @param id the id of the individualAssessmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the individualAssessmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<IndividualAssessmentDTO>> getIndividualAssessment(@PathVariable("id") Long id) {
        log.debug("REST request to get IndividualAssessment : {}", id);
        Mono<IndividualAssessmentDTO> individualAssessmentDTO = individualAssessmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(individualAssessmentDTO);
    }

    /**
     * {@code DELETE  /individual-assessments/:id} : delete the "id" individualAssessment.
     *
     * @param id the id of the individualAssessmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteIndividualAssessment(@PathVariable("id") Long id) {
        log.debug("REST request to delete IndividualAssessment : {}", id);
        return individualAssessmentService
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
