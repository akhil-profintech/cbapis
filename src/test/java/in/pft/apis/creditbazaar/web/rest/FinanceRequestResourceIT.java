package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.FinanceRequestRepository;
import in.pft.apis.creditbazaar.service.FinanceRequestService;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.mapper.FinanceRequestMapper;
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
 * Integration tests for the {@link FinanceRequestResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FinanceRequestResourceIT {

    private static final String DEFAULT_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_REQUEST_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_REQUEST_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_AMOUNT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REQUEST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REQUEST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/finance-requests";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FinanceRequestRepository financeRequestRepository;

    @Mock
    private FinanceRequestRepository financeRequestRepositoryMock;

    @Autowired
    private FinanceRequestMapper financeRequestMapper;

    @Mock
    private FinanceRequestService financeRequestServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FinanceRequest financeRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinanceRequest createEntity(EntityManager em) {
        FinanceRequest financeRequest = new FinanceRequest()
            .requestId(DEFAULT_REQUEST_ID)
            .financeRequestRefNo(DEFAULT_FINANCE_REQUEST_REF_NO)
            .requestAmount(DEFAULT_REQUEST_AMOUNT)
            .requestDate(DEFAULT_REQUEST_DATE)
            .currency(DEFAULT_CURRENCY)
            .requestStatus(DEFAULT_REQUEST_STATUS)
            .dueDate(DEFAULT_DUE_DATE);
        return financeRequest;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinanceRequest createUpdatedEntity(EntityManager em) {
        FinanceRequest financeRequest = new FinanceRequest()
            .requestId(UPDATED_REQUEST_ID)
            .financeRequestRefNo(UPDATED_FINANCE_REQUEST_REF_NO)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .currency(UPDATED_CURRENCY)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .dueDate(UPDATED_DUE_DATE);
        return financeRequest;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FinanceRequest.class).block();
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
        financeRequest = createEntity(em);
    }

    @Test
    void createFinanceRequest() throws Exception {
        int databaseSizeBeforeCreate = financeRequestRepository.findAll().collectList().block().size();
        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeCreate + 1);
        FinanceRequest testFinanceRequest = financeRequestList.get(financeRequestList.size() - 1);
        assertThat(testFinanceRequest.getRequestId()).isEqualTo(DEFAULT_REQUEST_ID);
        assertThat(testFinanceRequest.getFinanceRequestRefNo()).isEqualTo(DEFAULT_FINANCE_REQUEST_REF_NO);
        assertThat(testFinanceRequest.getRequestAmount()).isEqualTo(DEFAULT_REQUEST_AMOUNT);
        assertThat(testFinanceRequest.getRequestDate()).isEqualTo(DEFAULT_REQUEST_DATE);
        assertThat(testFinanceRequest.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFinanceRequest.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
        assertThat(testFinanceRequest.getDueDate()).isEqualTo(DEFAULT_DUE_DATE);
    }

    @Test
    void createFinanceRequestWithExistingId() throws Exception {
        // Create the FinanceRequest with an existing ID
        financeRequest.setId(1L);
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        int databaseSizeBeforeCreate = financeRequestRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkRequestAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = financeRequestRepository.findAll().collectList().block().size();
        // set the field null
        financeRequest.setRequestAmount(null);

        // Create the FinanceRequest, which fails.
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRequestDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = financeRequestRepository.findAll().collectList().block().size();
        // set the field null
        financeRequest.setRequestDate(null);

        // Create the FinanceRequest, which fails.
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = financeRequestRepository.findAll().collectList().block().size();
        // set the field null
        financeRequest.setCurrency(null);

        // Create the FinanceRequest, which fails.
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRequestStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = financeRequestRepository.findAll().collectList().block().size();
        // set the field null
        financeRequest.setRequestStatus(null);

        // Create the FinanceRequest, which fails.
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDueDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = financeRequestRepository.findAll().collectList().block().size();
        // set the field null
        financeRequest.setDueDate(null);

        // Create the FinanceRequest, which fails.
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFinanceRequests() {
        // Initialize the database
        financeRequestRepository.save(financeRequest).block();

        // Get all the financeRequestList
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
            .value(hasItem(financeRequest.getId().intValue()))
            .jsonPath("$.[*].requestId")
            .value(hasItem(DEFAULT_REQUEST_ID))
            .jsonPath("$.[*].financeRequestRefNo")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_REF_NO))
            .jsonPath("$.[*].requestAmount")
            .value(hasItem(DEFAULT_REQUEST_AMOUNT))
            .jsonPath("$.[*].requestDate")
            .value(hasItem(DEFAULT_REQUEST_DATE.toString()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].requestStatus")
            .value(hasItem(DEFAULT_REQUEST_STATUS))
            .jsonPath("$.[*].dueDate")
            .value(hasItem(DEFAULT_DUE_DATE.toString()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFinanceRequestsWithEagerRelationshipsIsEnabled() {
        when(financeRequestServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(financeRequestServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFinanceRequestsWithEagerRelationshipsIsNotEnabled() {
        when(financeRequestServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(financeRequestRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getFinanceRequest() {
        // Initialize the database
        financeRequestRepository.save(financeRequest).block();

        // Get the financeRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, financeRequest.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(financeRequest.getId().intValue()))
            .jsonPath("$.requestId")
            .value(is(DEFAULT_REQUEST_ID))
            .jsonPath("$.financeRequestRefNo")
            .value(is(DEFAULT_FINANCE_REQUEST_REF_NO))
            .jsonPath("$.requestAmount")
            .value(is(DEFAULT_REQUEST_AMOUNT))
            .jsonPath("$.requestDate")
            .value(is(DEFAULT_REQUEST_DATE.toString()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.requestStatus")
            .value(is(DEFAULT_REQUEST_STATUS))
            .jsonPath("$.dueDate")
            .value(is(DEFAULT_DUE_DATE.toString()));
    }

    @Test
    void getNonExistingFinanceRequest() {
        // Get the financeRequest
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFinanceRequest() throws Exception {
        // Initialize the database
        financeRequestRepository.save(financeRequest).block();

        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();

        // Update the financeRequest
        FinanceRequest updatedFinanceRequest = financeRequestRepository.findById(financeRequest.getId()).block();
        updatedFinanceRequest
            .requestId(UPDATED_REQUEST_ID)
            .financeRequestRefNo(UPDATED_FINANCE_REQUEST_REF_NO)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .currency(UPDATED_CURRENCY)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .dueDate(UPDATED_DUE_DATE);
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(updatedFinanceRequest);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, financeRequestDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
        FinanceRequest testFinanceRequest = financeRequestList.get(financeRequestList.size() - 1);
        assertThat(testFinanceRequest.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testFinanceRequest.getFinanceRequestRefNo()).isEqualTo(UPDATED_FINANCE_REQUEST_REF_NO);
        assertThat(testFinanceRequest.getRequestAmount()).isEqualTo(UPDATED_REQUEST_AMOUNT);
        assertThat(testFinanceRequest.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testFinanceRequest.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFinanceRequest.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
        assertThat(testFinanceRequest.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
    }

    @Test
    void putNonExistingFinanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();
        financeRequest.setId(longCount.incrementAndGet());

        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, financeRequestDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFinanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();
        financeRequest.setId(longCount.incrementAndGet());

        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFinanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();
        financeRequest.setId(longCount.incrementAndGet());

        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFinanceRequestWithPatch() throws Exception {
        // Initialize the database
        financeRequestRepository.save(financeRequest).block();

        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();

        // Update the financeRequest using partial update
        FinanceRequest partialUpdatedFinanceRequest = new FinanceRequest();
        partialUpdatedFinanceRequest.setId(financeRequest.getId());

        partialUpdatedFinanceRequest
            .requestId(UPDATED_REQUEST_ID)
            .financeRequestRefNo(UPDATED_FINANCE_REQUEST_REF_NO)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .dueDate(UPDATED_DUE_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFinanceRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFinanceRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
        FinanceRequest testFinanceRequest = financeRequestList.get(financeRequestList.size() - 1);
        assertThat(testFinanceRequest.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testFinanceRequest.getFinanceRequestRefNo()).isEqualTo(UPDATED_FINANCE_REQUEST_REF_NO);
        assertThat(testFinanceRequest.getRequestAmount()).isEqualTo(UPDATED_REQUEST_AMOUNT);
        assertThat(testFinanceRequest.getRequestDate()).isEqualTo(DEFAULT_REQUEST_DATE);
        assertThat(testFinanceRequest.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFinanceRequest.getRequestStatus()).isEqualTo(DEFAULT_REQUEST_STATUS);
        assertThat(testFinanceRequest.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
    }

    @Test
    void fullUpdateFinanceRequestWithPatch() throws Exception {
        // Initialize the database
        financeRequestRepository.save(financeRequest).block();

        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();

        // Update the financeRequest using partial update
        FinanceRequest partialUpdatedFinanceRequest = new FinanceRequest();
        partialUpdatedFinanceRequest.setId(financeRequest.getId());

        partialUpdatedFinanceRequest
            .requestId(UPDATED_REQUEST_ID)
            .financeRequestRefNo(UPDATED_FINANCE_REQUEST_REF_NO)
            .requestAmount(UPDATED_REQUEST_AMOUNT)
            .requestDate(UPDATED_REQUEST_DATE)
            .currency(UPDATED_CURRENCY)
            .requestStatus(UPDATED_REQUEST_STATUS)
            .dueDate(UPDATED_DUE_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFinanceRequest.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFinanceRequest))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
        FinanceRequest testFinanceRequest = financeRequestList.get(financeRequestList.size() - 1);
        assertThat(testFinanceRequest.getRequestId()).isEqualTo(UPDATED_REQUEST_ID);
        assertThat(testFinanceRequest.getFinanceRequestRefNo()).isEqualTo(UPDATED_FINANCE_REQUEST_REF_NO);
        assertThat(testFinanceRequest.getRequestAmount()).isEqualTo(UPDATED_REQUEST_AMOUNT);
        assertThat(testFinanceRequest.getRequestDate()).isEqualTo(UPDATED_REQUEST_DATE);
        assertThat(testFinanceRequest.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFinanceRequest.getRequestStatus()).isEqualTo(UPDATED_REQUEST_STATUS);
        assertThat(testFinanceRequest.getDueDate()).isEqualTo(UPDATED_DUE_DATE);
    }

    @Test
    void patchNonExistingFinanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();
        financeRequest.setId(longCount.incrementAndGet());

        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, financeRequestDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFinanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();
        financeRequest.setId(longCount.incrementAndGet());

        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFinanceRequest() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestRepository.findAll().collectList().block().size();
        financeRequest.setId(longCount.incrementAndGet());

        // Create the FinanceRequest
        FinanceRequestDTO financeRequestDTO = financeRequestMapper.toDto(financeRequest);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FinanceRequest in the database
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFinanceRequest() {
        // Initialize the database
        financeRequestRepository.save(financeRequest).block();

        int databaseSizeBeforeDelete = financeRequestRepository.findAll().collectList().block().size();

        // Delete the financeRequest
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, financeRequest.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FinanceRequest> financeRequestList = financeRequestRepository.findAll().collectList().block();
        assertThat(financeRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
