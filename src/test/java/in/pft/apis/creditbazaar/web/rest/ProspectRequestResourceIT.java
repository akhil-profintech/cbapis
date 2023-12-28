package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.ProspectRequest;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.ProspectRequestRepository;
import in.pft.apis.creditbazaar.service.ProspectRequestService;
import in.pft.apis.creditbazaar.service.dto.ProspectRequestDTO;
import in.pft.apis.creditbazaar.service.mapper.ProspectRequestMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

/**
 * Integration tests for the {@link ProspectRequestResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ProspectRequestResourceIT {

    private static final Long DEFAULT_PROSPECT_REQUEST_ID = 1L;
    private static final Long UPDATED_PROSPECT_REQUEST_ID = 2L;

    private static final Long DEFAULT_ANCHOR_TRADER_ID = 1L;
    private static final Long UPDATED_ANCHOR_TRADER_ID = 2L;

    private static final String DEFAULT_REQUEST_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_AMOUNT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROSPECT_REQUEST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROSPECT_REQUEST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/prospect-requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProspectRequestRepository prospectRequestRepository;

    @Mock
    private ProspectRequestRepository prospectRequestRepositoryMock;

    @Autowired
    private ProspectRequestMapper prospectRequestMapper;

    @Mock
    private ProspectRequestService prospectRequestServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ProspectRequest prospectRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProspectRequest createEntity(EntityManager em) {
        ProspectRequest prospectRequest = new ProspectRequest()
            .prospectRequestId(DEFAULT_PROSPECT_REQUEST_ID)
            .anchorTraderId(DEFAULT_ANCHOR_TRADER_ID)
            .requestAmount(DEFAULT_REQUEST_AMOUNT)
            .prospectRequestDate(DEFAULT_PROSPECT_REQUEST_DATE)
            .currency(DEFAULT_CURRENCY);
        return prospectRequest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProspectRequest createUpdatedEntity(EntityManager em) {
        ProspectRequest prospectRequest = new ProspectRequest()
            .prospectRequestId(UPDATED_PROSPECT_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .prospectRequestDate(UPDATED_PROSPECT_REQUEST_DATE)
            .currency(UPDATED_CURRENCY);
        return prospectRequest;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ProspectRequest.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        prospectRequest = createEntity(em);
    }

    @Test
    void createProspectRequest() throws Exception {
        int databaseSizeBeforeCreate = prospectRequestRepository.findAll().collectList().block().size();
        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeCreate + 1);
        ProspectRequest testProspectRequest = prospectRequestList.get(prospectRequestList.size() - 1);
        assertThat(testProspectRequest.getProspectRequestId()).isEqualTo(DEFAULT_PROSPECT_REQUEST_ID);
        assertThat(testProspectRequest.getAnchorTraderId()).isEqualTo(DEFAULT_ANCHOR_TRADER_ID);
        assertThat(testProspectRequest.getRequestAmount()).isEqualTo(DEFAULT_REQUEST_AMOUNT);
        assertThat(testProspectRequest.getProspectRequestDate()).isEqualTo(DEFAULT_PROSPECT_REQUEST_DATE);
        assertThat(testProspectRequest.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
    }

    @Test
    void createProspectRequestWithExistingId() throws Exception {
        // Create the ProspectRequest with an existing ID
        prospectRequest.setId(1L);
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        int databaseSizeBeforeCreate = prospectRequestRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkProspectRequestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRequestRepository.findAll().collectList().block().size();
        // set the field null
        prospectRequest.setProspectRequestId(null);

        // Create the ProspectRequest, which fails.
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAnchorTraderIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRequestRepository.findAll().collectList().block().size();
        // set the field null
        prospectRequest.setAnchorTraderId(null);

        // Create the ProspectRequest, which fails.
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRequestAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRequestRepository.findAll().collectList().block().size();
        // set the field null
        prospectRequest.setRequestAmount(null);

        // Create the ProspectRequest, which fails.
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkProspectRequestDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRequestRepository.findAll().collectList().block().size();
        // set the field null
        prospectRequest.setProspectRequestDate(null);

        // Create the ProspectRequest, which fails.
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = prospectRequestRepository.findAll().collectList().block().size();
        // set the field null
        prospectRequest.setCurrency(null);

        // Create the ProspectRequest, which fails.
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllProspectRequests() {
        // Initialize the database
        prospectRequestRepository.save(prospectRequest).block();

        // Get all the prospectRequestList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(prospectRequest.getId().intValue()))
            .jsonPath("$.[*].prospectRequestId")
            .value(hasItem(DEFAULT_PROSPECT_REQUEST_ID.intValue()))
            .jsonPath("$.[*].anchorTraderId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_ID.intValue()))
            .jsonPath("$.[*].requestAmount")
            .value(hasItem(DEFAULT_REQUEST_AMOUNT))
            .jsonPath("$.[*].prospectRequestDate")
            .value(hasItem(DEFAULT_PROSPECT_REQUEST_DATE.toString()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProspectRequestsWithEagerRelationshipsIsEnabled() {
        when(prospectRequestServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(prospectRequestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProspectRequestsWithEagerRelationshipsIsNotEnabled() {
        when(prospectRequestServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(prospectRequestRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getProspectRequest() {
        // Initialize the database
        prospectRequestRepository.save(prospectRequest).block();

        // Get the prospectRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, prospectRequest.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(prospectRequest.getId().intValue()))
            .jsonPath("$.prospectRequestId")
            .value(is(DEFAULT_PROSPECT_REQUEST_ID.intValue()))
            .jsonPath("$.anchorTraderId")
            .value(is(DEFAULT_ANCHOR_TRADER_ID.intValue()))
            .jsonPath("$.requestAmount")
            .value(is(DEFAULT_REQUEST_AMOUNT))
            .jsonPath("$.prospectRequestDate")
            .value(is(DEFAULT_PROSPECT_REQUEST_DATE.toString()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY));
    }

    @Test
    void getNonExistingProspectRequest() {
        // Get the prospectRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingProspectRequest() throws Exception {
        // Initialize the database
        prospectRequestRepository.save(prospectRequest).block();

        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();

        // Update the prospectRequest
        ProspectRequest updatedProspectRequest = prospectRequestRepository.findById(prospectRequest.getId()).block();
        updatedProspectRequest
            .prospectRequestId(UPDATED_PROSPECT_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .prospectRequestDate(UPDATED_PROSPECT_REQUEST_DATE)
            .currency(UPDATED_CURRENCY);
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(updatedProspectRequest);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, prospectRequestDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
        ProspectRequest testProspectRequest = prospectRequestList.get(prospectRequestList.size() - 1);
        assertThat(testProspectRequest.getProspectRequestId()).isEqualTo(UPDATED_PROSPECT_REQUEST_ID);
        assertThat(testProspectRequest.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testProspectRequest.getRequestAmount()).isEqualTo(UPDATED_REQUEST_AMOUNT);
        assertThat(testProspectRequest.getProspectRequestDate()).isEqualTo(UPDATED_PROSPECT_REQUEST_DATE);
        assertThat(testProspectRequest.getCurrency()).isEqualTo(UPDATED_CURRENCY);
    }

    @Test
    void putNonExistingProspectRequest() throws Exception {
        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();
        prospectRequest.setId(longCount.incrementAndGet());

        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, prospectRequestDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchProspectRequest() throws Exception {
        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();
        prospectRequest.setId(longCount.incrementAndGet());

        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamProspectRequest() throws Exception {
        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();
        prospectRequest.setId(longCount.incrementAndGet());

        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateProspectRequestWithPatch() throws Exception {
        // Initialize the database
        prospectRequestRepository.save(prospectRequest).block();

        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();

        // Update the prospectRequest using partial update
        ProspectRequest partialUpdatedProspectRequest = new ProspectRequest();
        partialUpdatedProspectRequest.setId(prospectRequest.getId());

        partialUpdatedProspectRequest
            .prospectRequestId(UPDATED_PROSPECT_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .prospectRequestDate(UPDATED_PROSPECT_REQUEST_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedProspectRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedProspectRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
        ProspectRequest testProspectRequest = prospectRequestList.get(prospectRequestList.size() - 1);
        assertThat(testProspectRequest.getProspectRequestId()).isEqualTo(UPDATED_PROSPECT_REQUEST_ID);
        assertThat(testProspectRequest.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testProspectRequest.getRequestAmount()).isEqualTo(UPDATED_REQUEST_AMOUNT);
        assertThat(testProspectRequest.getProspectRequestDate()).isEqualTo(UPDATED_PROSPECT_REQUEST_DATE);
        assertThat(testProspectRequest.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
    }

    @Test
    void fullUpdateProspectRequestWithPatch() throws Exception {
        // Initialize the database
        prospectRequestRepository.save(prospectRequest).block();

        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();

        // Update the prospectRequest using partial update
        ProspectRequest partialUpdatedProspectRequest = new ProspectRequest();
        partialUpdatedProspectRequest.setId(prospectRequest.getId());

        partialUpdatedProspectRequest
            .prospectRequestId(UPDATED_PROSPECT_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .prospectRequestDate(UPDATED_PROSPECT_REQUEST_DATE)
            .currency(UPDATED_CURRENCY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedProspectRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedProspectRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
        ProspectRequest testProspectRequest = prospectRequestList.get(prospectRequestList.size() - 1);
        assertThat(testProspectRequest.getProspectRequestId()).isEqualTo(UPDATED_PROSPECT_REQUEST_ID);
        assertThat(testProspectRequest.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testProspectRequest.getRequestAmount()).isEqualTo(UPDATED_REQUEST_AMOUNT);
        assertThat(testProspectRequest.getProspectRequestDate()).isEqualTo(UPDATED_PROSPECT_REQUEST_DATE);
        assertThat(testProspectRequest.getCurrency()).isEqualTo(UPDATED_CURRENCY);
    }

    @Test
    void patchNonExistingProspectRequest() throws Exception {
        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();
        prospectRequest.setId(longCount.incrementAndGet());

        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, prospectRequestDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchProspectRequest() throws Exception {
        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();
        prospectRequest.setId(longCount.incrementAndGet());

        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamProspectRequest() throws Exception {
        int databaseSizeBeforeUpdate = prospectRequestRepository.findAll().collectList().block().size();
        prospectRequest.setId(longCount.incrementAndGet());

        // Create the ProspectRequest
        ProspectRequestDTO prospectRequestDTO = prospectRequestMapper.toDto(prospectRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(prospectRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ProspectRequest in the database
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteProspectRequest() {
        // Initialize the database
        prospectRequestRepository.save(prospectRequest).block();

        int databaseSizeBeforeDelete = prospectRequestRepository.findAll().collectList().block().size();

        // Delete the prospectRequest
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, prospectRequest.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ProspectRequest> prospectRequestList = prospectRequestRepository.findAll().collectList().block();
        assertThat(prospectRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
