package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.EscrowAccountDetails;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.EscrowAccountDetailsRepository;
import in.pft.apis.creditbazaar.service.EscrowAccountDetailsService;
import in.pft.apis.creditbazaar.service.dto.EscrowAccountDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.EscrowAccountDetailsMapper;
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
 * Integration tests for the {@link EscrowAccountDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EscrowAccountDetailsResourceIT {

    private static final Long DEFAULT_ESCROW_DETAILS_ID = 1L;
    private static final Long UPDATED_ESCROW_DETAILS_ID = 2L;

    private static final Long DEFAULT_TENANT_ID = 1L;
    private static final Long UPDATED_TENANT_ID = 2L;

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final String DEFAULT_ACC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IFSC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VIRTUAL_ACC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VIRTUAL_ACC_NUMBER = "BBBBBBBBBB";

    private static final Long DEFAULT_POOLING_ACC_NUMBER = 1L;
    private static final Long UPDATED_POOLING_ACC_NUMBER = 2L;

    private static final String ENTITY_API_URL = "/api/escrow-account-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EscrowAccountDetailsRepository escrowAccountDetailsRepository;

    @Mock
    private EscrowAccountDetailsRepository escrowAccountDetailsRepositoryMock;

    @Autowired
    private EscrowAccountDetailsMapper escrowAccountDetailsMapper;

    @Mock
    private EscrowAccountDetailsService escrowAccountDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EscrowAccountDetails escrowAccountDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EscrowAccountDetails createEntity(EntityManager em) {
        EscrowAccountDetails escrowAccountDetails = new EscrowAccountDetails()
            .escrowDetailsId(DEFAULT_ESCROW_DETAILS_ID)
            .tenantId(DEFAULT_TENANT_ID)
            .customerId(DEFAULT_CUSTOMER_ID)
            .accName(DEFAULT_ACC_NAME)
            .ifscCode(DEFAULT_IFSC_CODE)
            .virtualAccNumber(DEFAULT_VIRTUAL_ACC_NUMBER)
            .poolingAccNumber(DEFAULT_POOLING_ACC_NUMBER);
        return escrowAccountDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EscrowAccountDetails createUpdatedEntity(EntityManager em) {
        EscrowAccountDetails escrowAccountDetails = new EscrowAccountDetails()
            .escrowDetailsId(UPDATED_ESCROW_DETAILS_ID)
            .tenantId(UPDATED_TENANT_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .virtualAccNumber(UPDATED_VIRTUAL_ACC_NUMBER)
            .poolingAccNumber(UPDATED_POOLING_ACC_NUMBER);
        return escrowAccountDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EscrowAccountDetails.class).block();
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
        escrowAccountDetails = createEntity(em);
    }

    @Test
    void createEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeCreate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        EscrowAccountDetails testEscrowAccountDetails = escrowAccountDetailsList.get(escrowAccountDetailsList.size() - 1);
        assertThat(testEscrowAccountDetails.getEscrowDetailsId()).isEqualTo(DEFAULT_ESCROW_DETAILS_ID);
        assertThat(testEscrowAccountDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testEscrowAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testEscrowAccountDetails.getAccName()).isEqualTo(DEFAULT_ACC_NAME);
        assertThat(testEscrowAccountDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testEscrowAccountDetails.getVirtualAccNumber()).isEqualTo(DEFAULT_VIRTUAL_ACC_NUMBER);
        assertThat(testEscrowAccountDetails.getPoolingAccNumber()).isEqualTo(DEFAULT_POOLING_ACC_NUMBER);
    }

    @Test
    void createEscrowAccountDetailsWithExistingId() throws Exception {
        // Create the EscrowAccountDetails with an existing ID
        escrowAccountDetails.setId(1L);
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        int databaseSizeBeforeCreate = escrowAccountDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkEscrowDetailsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setEscrowDetailsId(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setTenantId(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setCustomerId(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAccNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setAccName(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIfscCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setIfscCode(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVirtualAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setVirtualAccNumber(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPoolingAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowAccountDetails.setPoolingAccNumber(null);

        // Create the EscrowAccountDetails, which fails.
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllEscrowAccountDetails() {
        // Initialize the database
        escrowAccountDetailsRepository.save(escrowAccountDetails).block();

        // Get all the escrowAccountDetailsList
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
            .value(hasItem(escrowAccountDetails.getId().intValue()))
            .jsonPath("$.[*].escrowDetailsId")
            .value(hasItem(DEFAULT_ESCROW_DETAILS_ID.intValue()))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID.intValue()))
            .jsonPath("$.[*].customerId")
            .value(hasItem(DEFAULT_CUSTOMER_ID.intValue()))
            .jsonPath("$.[*].accName")
            .value(hasItem(DEFAULT_ACC_NAME))
            .jsonPath("$.[*].ifscCode")
            .value(hasItem(DEFAULT_IFSC_CODE))
            .jsonPath("$.[*].virtualAccNumber")
            .value(hasItem(DEFAULT_VIRTUAL_ACC_NUMBER))
            .jsonPath("$.[*].poolingAccNumber")
            .value(hasItem(DEFAULT_POOLING_ACC_NUMBER.intValue()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEscrowAccountDetailsWithEagerRelationshipsIsEnabled() {
        when(escrowAccountDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(escrowAccountDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEscrowAccountDetailsWithEagerRelationshipsIsNotEnabled() {
        when(escrowAccountDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(escrowAccountDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getEscrowAccountDetails() {
        // Initialize the database
        escrowAccountDetailsRepository.save(escrowAccountDetails).block();

        // Get the escrowAccountDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, escrowAccountDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(escrowAccountDetails.getId().intValue()))
            .jsonPath("$.escrowDetailsId")
            .value(is(DEFAULT_ESCROW_DETAILS_ID.intValue()))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID.intValue()))
            .jsonPath("$.customerId")
            .value(is(DEFAULT_CUSTOMER_ID.intValue()))
            .jsonPath("$.accName")
            .value(is(DEFAULT_ACC_NAME))
            .jsonPath("$.ifscCode")
            .value(is(DEFAULT_IFSC_CODE))
            .jsonPath("$.virtualAccNumber")
            .value(is(DEFAULT_VIRTUAL_ACC_NUMBER))
            .jsonPath("$.poolingAccNumber")
            .value(is(DEFAULT_POOLING_ACC_NUMBER.intValue()));
    }

    @Test
    void getNonExistingEscrowAccountDetails() {
        // Get the escrowAccountDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingEscrowAccountDetails() throws Exception {
        // Initialize the database
        escrowAccountDetailsRepository.save(escrowAccountDetails).block();

        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();

        // Update the escrowAccountDetails
        EscrowAccountDetails updatedEscrowAccountDetails = escrowAccountDetailsRepository.findById(escrowAccountDetails.getId()).block();
        updatedEscrowAccountDetails
            .escrowDetailsId(UPDATED_ESCROW_DETAILS_ID)
            .tenantId(UPDATED_TENANT_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .virtualAccNumber(UPDATED_VIRTUAL_ACC_NUMBER)
            .poolingAccNumber(UPDATED_POOLING_ACC_NUMBER);
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(updatedEscrowAccountDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, escrowAccountDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        EscrowAccountDetails testEscrowAccountDetails = escrowAccountDetailsList.get(escrowAccountDetailsList.size() - 1);
        assertThat(testEscrowAccountDetails.getEscrowDetailsId()).isEqualTo(UPDATED_ESCROW_DETAILS_ID);
        assertThat(testEscrowAccountDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testEscrowAccountDetails.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testEscrowAccountDetails.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testEscrowAccountDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testEscrowAccountDetails.getVirtualAccNumber()).isEqualTo(UPDATED_VIRTUAL_ACC_NUMBER);
        assertThat(testEscrowAccountDetails.getPoolingAccNumber()).isEqualTo(UPDATED_POOLING_ACC_NUMBER);
    }

    @Test
    void putNonExistingEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        escrowAccountDetails.setId(longCount.incrementAndGet());

        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, escrowAccountDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        escrowAccountDetails.setId(longCount.incrementAndGet());

        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        escrowAccountDetails.setId(longCount.incrementAndGet());

        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEscrowAccountDetailsWithPatch() throws Exception {
        // Initialize the database
        escrowAccountDetailsRepository.save(escrowAccountDetails).block();

        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();

        // Update the escrowAccountDetails using partial update
        EscrowAccountDetails partialUpdatedEscrowAccountDetails = new EscrowAccountDetails();
        partialUpdatedEscrowAccountDetails.setId(escrowAccountDetails.getId());

        partialUpdatedEscrowAccountDetails.escrowDetailsId(UPDATED_ESCROW_DETAILS_ID).poolingAccNumber(UPDATED_POOLING_ACC_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEscrowAccountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEscrowAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        EscrowAccountDetails testEscrowAccountDetails = escrowAccountDetailsList.get(escrowAccountDetailsList.size() - 1);
        assertThat(testEscrowAccountDetails.getEscrowDetailsId()).isEqualTo(UPDATED_ESCROW_DETAILS_ID);
        assertThat(testEscrowAccountDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testEscrowAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testEscrowAccountDetails.getAccName()).isEqualTo(DEFAULT_ACC_NAME);
        assertThat(testEscrowAccountDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testEscrowAccountDetails.getVirtualAccNumber()).isEqualTo(DEFAULT_VIRTUAL_ACC_NUMBER);
        assertThat(testEscrowAccountDetails.getPoolingAccNumber()).isEqualTo(UPDATED_POOLING_ACC_NUMBER);
    }

    @Test
    void fullUpdateEscrowAccountDetailsWithPatch() throws Exception {
        // Initialize the database
        escrowAccountDetailsRepository.save(escrowAccountDetails).block();

        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();

        // Update the escrowAccountDetails using partial update
        EscrowAccountDetails partialUpdatedEscrowAccountDetails = new EscrowAccountDetails();
        partialUpdatedEscrowAccountDetails.setId(escrowAccountDetails.getId());

        partialUpdatedEscrowAccountDetails
            .escrowDetailsId(UPDATED_ESCROW_DETAILS_ID)
            .tenantId(UPDATED_TENANT_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .virtualAccNumber(UPDATED_VIRTUAL_ACC_NUMBER)
            .poolingAccNumber(UPDATED_POOLING_ACC_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEscrowAccountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEscrowAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        EscrowAccountDetails testEscrowAccountDetails = escrowAccountDetailsList.get(escrowAccountDetailsList.size() - 1);
        assertThat(testEscrowAccountDetails.getEscrowDetailsId()).isEqualTo(UPDATED_ESCROW_DETAILS_ID);
        assertThat(testEscrowAccountDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testEscrowAccountDetails.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testEscrowAccountDetails.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testEscrowAccountDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testEscrowAccountDetails.getVirtualAccNumber()).isEqualTo(UPDATED_VIRTUAL_ACC_NUMBER);
        assertThat(testEscrowAccountDetails.getPoolingAccNumber()).isEqualTo(UPDATED_POOLING_ACC_NUMBER);
    }

    @Test
    void patchNonExistingEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        escrowAccountDetails.setId(longCount.incrementAndGet());

        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, escrowAccountDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        escrowAccountDetails.setId(longCount.incrementAndGet());

        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEscrowAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowAccountDetailsRepository.findAll().collectList().block().size();
        escrowAccountDetails.setId(longCount.incrementAndGet());

        // Create the EscrowAccountDetails
        EscrowAccountDetailsDTO escrowAccountDetailsDTO = escrowAccountDetailsMapper.toDto(escrowAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EscrowAccountDetails in the database
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEscrowAccountDetails() {
        // Initialize the database
        escrowAccountDetailsRepository.save(escrowAccountDetails).block();

        int databaseSizeBeforeDelete = escrowAccountDetailsRepository.findAll().collectList().block().size();

        // Delete the escrowAccountDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, escrowAccountDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<EscrowAccountDetails> escrowAccountDetailsList = escrowAccountDetailsRepository.findAll().collectList().block();
        assertThat(escrowAccountDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
