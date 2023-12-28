package in.pft.apis.creditbazaar.web.rest;

import in.pft.apis.creditbazaar.repository.ClientCodesRepository;
import in.pft.apis.creditbazaar.service.ClientCodesService;
import in.pft.apis.creditbazaar.service.dto.ClientCodesDTO;
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
 * REST controller for managing {@link in.pft.apis.creditbazaar.domain.ClientCodes}.
 */
@RestController
@RequestMapping("/api/client-codes")
public class ClientCodesResource {

    private final Logger log = LoggerFactory.getLogger(ClientCodesResource.class);

    private static final String ENTITY_NAME = "clientCodes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClientCodesService clientCodesService;

    private final ClientCodesRepository clientCodesRepository;

    public ClientCodesResource(ClientCodesService clientCodesService, ClientCodesRepository clientCodesRepository) {
        this.clientCodesService = clientCodesService;
        this.clientCodesRepository = clientCodesRepository;
    }

    /**
     * {@code POST  /client-codes} : Create a new clientCodes.
     *
     * @param clientCodesDTO the clientCodesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new clientCodesDTO, or with status {@code 400 (Bad Request)} if the clientCodes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<ClientCodesDTO>> createClientCodes(@RequestBody ClientCodesDTO clientCodesDTO) throws URISyntaxException {
        log.debug("REST request to save ClientCodes : {}", clientCodesDTO);
        if (clientCodesDTO.getId() != null) {
            throw new BadRequestAlertException("A new clientCodes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return clientCodesService
            .save(clientCodesDTO)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/client-codes/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /client-codes/:id} : Updates an existing clientCodes.
     *
     * @param id the id of the clientCodesDTO to save.
     * @param clientCodesDTO the clientCodesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientCodesDTO,
     * or with status {@code 400 (Bad Request)} if the clientCodesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the clientCodesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<ClientCodesDTO>> updateClientCodes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientCodesDTO clientCodesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ClientCodes : {}, {}", id, clientCodesDTO);
        if (clientCodesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientCodesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return clientCodesRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return clientCodesService
                    .update(clientCodesDTO)
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
     * {@code PATCH  /client-codes/:id} : Partial updates given fields of an existing clientCodes, field will ignore if it is null
     *
     * @param id the id of the clientCodesDTO to save.
     * @param clientCodesDTO the clientCodesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated clientCodesDTO,
     * or with status {@code 400 (Bad Request)} if the clientCodesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the clientCodesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the clientCodesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ClientCodesDTO>> partialUpdateClientCodes(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ClientCodesDTO clientCodesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ClientCodes partially : {}, {}", id, clientCodesDTO);
        if (clientCodesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, clientCodesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return clientCodesRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ClientCodesDTO> result = clientCodesService.partialUpdate(clientCodesDTO);

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
     * {@code GET  /client-codes} : get all the clientCodes.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of clientCodes in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<ClientCodesDTO>>> getAllClientCodes(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of ClientCodes");
        return clientCodesService
            .countAll()
            .zipWith(clientCodesService.findAll(pageable).collectList())
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
     * {@code GET  /client-codes/:id} : get the "id" clientCodes.
     *
     * @param id the id of the clientCodesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the clientCodesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClientCodesDTO>> getClientCodes(@PathVariable("id") Long id) {
        log.debug("REST request to get ClientCodes : {}", id);
        Mono<ClientCodesDTO> clientCodesDTO = clientCodesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clientCodesDTO);
    }

    /**
     * {@code DELETE  /client-codes/:id} : delete the "id" clientCodes.
     *
     * @param id the id of the clientCodesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteClientCodes(@PathVariable("id") Long id) {
        log.debug("REST request to delete ClientCodes : {}", id);
        return clientCodesService
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
