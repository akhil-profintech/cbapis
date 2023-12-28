package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.CreditAccountDetails;
import in.pft.apis.creditbazaar.repository.CreditAccountDetailsRepository;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.service.CreditAccountDetailsService;
import in.pft.apis.creditbazaar.service.dto.CreditAccountDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.CreditAccountDetailsMapper;
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
 * Integration tests for the {@link CreditAccountDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreditAccountDetailsResourceIT {

    private static final Long DEFAULT_CREDIT_ACC_DETAILS_ID = 1L;
    private static final Long UPDATED_CREDIT_ACC_DETAILS_ID = 2L;

    private static final Long DEFAULT_TENANT_ID = 1L;
    private static final Long UPDATED_TENANT_ID = 2L;

    private static final Long DEFAULT_CUSTOMER_ID = 1L;
    private static final Long UPDATED_CUSTOMER_ID = 2L;

    private static final String DEFAULT_ACC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IFSC_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_ACC_NUMBER = 1L;
    private static final Long UPDATED_ACC_NUMBER = 2L;

    private static final String ENTITY_API_URL = "/api/credit-account-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreditAccountDetailsRepository creditAccountDetailsRepository;

    @Mock
    private CreditAccountDetailsRepository creditAccountDetailsRepositoryMock;

    @Autowired
    private CreditAccountDetailsMapper creditAccountDetailsMapper;

    @Mock
    private CreditAccountDetailsService creditAccountDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreditAccountDetails creditAccountDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditAccountDetails createEntity(EntityManager em) {
        CreditAccountDetails creditAccountDetails = new CreditAccountDetails()
            .creditAccDetailsId(DEFAULT_CREDIT_ACC_DETAILS_ID)
            .tenantId(DEFAULT_TENANT_ID)
            .customerId(DEFAULT_CUSTOMER_ID)
            .accName(DEFAULT_ACC_NAME)
            .ifscCode(DEFAULT_IFSC_CODE)
            .accNumber(DEFAULT_ACC_NUMBER);
        return creditAccountDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditAccountDetails createUpdatedEntity(EntityManager em) {
        CreditAccountDetails creditAccountDetails = new CreditAccountDetails()
            .creditAccDetailsId(UPDATED_CREDIT_ACC_DETAILS_ID)
            .tenantId(UPDATED_TENANT_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .accNumber(UPDATED_ACC_NUMBER);
        return creditAccountDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreditAccountDetails.class).block();
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
        creditAccountDetails = createEntity(em);
    }

    @Test
    void createCreditAccountDetails() throws Exception {
        int databaseSizeBeforeCreate = creditAccountDetailsRepository.findAll().collectList().block().size();
        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        CreditAccountDetails testCreditAccountDetails = creditAccountDetailsList.get(creditAccountDetailsList.size() - 1);
        assertThat(testCreditAccountDetails.getCreditAccDetailsId()).isEqualTo(DEFAULT_CREDIT_ACC_DETAILS_ID);
        assertThat(testCreditAccountDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testCreditAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testCreditAccountDetails.getAccName()).isEqualTo(DEFAULT_ACC_NAME);
        assertThat(testCreditAccountDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testCreditAccountDetails.getAccNumber()).isEqualTo(DEFAULT_ACC_NUMBER);
    }

    @Test
    void createCreditAccountDetailsWithExistingId() throws Exception {
        // Create the CreditAccountDetails with an existing ID
        creditAccountDetails.setId(1L);
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        int databaseSizeBeforeCreate = creditAccountDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCreditAccDetailsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        creditAccountDetails.setCreditAccDetailsId(null);

        // Create the CreditAccountDetails, which fails.
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        creditAccountDetails.setTenantId(null);

        // Create the CreditAccountDetails, which fails.
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        creditAccountDetails.setCustomerId(null);

        // Create the CreditAccountDetails, which fails.
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAccNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        creditAccountDetails.setAccName(null);

        // Create the CreditAccountDetails, which fails.
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIfscCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        creditAccountDetails.setIfscCode(null);

        // Create the CreditAccountDetails, which fails.
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditAccountDetailsRepository.findAll().collectList().block().size();
        // set the field null
        creditAccountDetails.setAccNumber(null);

        // Create the CreditAccountDetails, which fails.
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllCreditAccountDetails() {
        // Initialize the database
        creditAccountDetailsRepository.save(creditAccountDetails).block();

        // Get all the creditAccountDetailsList
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
            .value(hasItem(creditAccountDetails.getId().intValue()))
            .jsonPath("$.[*].creditAccDetailsId")
            .value(hasItem(DEFAULT_CREDIT_ACC_DETAILS_ID.intValue()))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID.intValue()))
            .jsonPath("$.[*].customerId")
            .value(hasItem(DEFAULT_CUSTOMER_ID.intValue()))
            .jsonPath("$.[*].accName")
            .value(hasItem(DEFAULT_ACC_NAME))
            .jsonPath("$.[*].ifscCode")
            .value(hasItem(DEFAULT_IFSC_CODE))
            .jsonPath("$.[*].accNumber")
            .value(hasItem(DEFAULT_ACC_NUMBER.intValue()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCreditAccountDetailsWithEagerRelationshipsIsEnabled() {
        when(creditAccountDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(creditAccountDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCreditAccountDetailsWithEagerRelationshipsIsNotEnabled() {
        when(creditAccountDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(creditAccountDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getCreditAccountDetails() {
        // Initialize the database
        creditAccountDetailsRepository.save(creditAccountDetails).block();

        // Get the creditAccountDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, creditAccountDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(creditAccountDetails.getId().intValue()))
            .jsonPath("$.creditAccDetailsId")
            .value(is(DEFAULT_CREDIT_ACC_DETAILS_ID.intValue()))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID.intValue()))
            .jsonPath("$.customerId")
            .value(is(DEFAULT_CUSTOMER_ID.intValue()))
            .jsonPath("$.accName")
            .value(is(DEFAULT_ACC_NAME))
            .jsonPath("$.ifscCode")
            .value(is(DEFAULT_IFSC_CODE))
            .jsonPath("$.accNumber")
            .value(is(DEFAULT_ACC_NUMBER.intValue()));
    }

    @Test
    void getNonExistingCreditAccountDetails() {
        // Get the creditAccountDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreditAccountDetails() throws Exception {
        // Initialize the database
        creditAccountDetailsRepository.save(creditAccountDetails).block();

        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();

        // Update the creditAccountDetails
        CreditAccountDetails updatedCreditAccountDetails = creditAccountDetailsRepository.findById(creditAccountDetails.getId()).block();
        updatedCreditAccountDetails
            .creditAccDetailsId(UPDATED_CREDIT_ACC_DETAILS_ID)
            .tenantId(UPDATED_TENANT_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .accNumber(UPDATED_ACC_NUMBER);
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(updatedCreditAccountDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, creditAccountDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        CreditAccountDetails testCreditAccountDetails = creditAccountDetailsList.get(creditAccountDetailsList.size() - 1);
        assertThat(testCreditAccountDetails.getCreditAccDetailsId()).isEqualTo(UPDATED_CREDIT_ACC_DETAILS_ID);
        assertThat(testCreditAccountDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testCreditAccountDetails.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testCreditAccountDetails.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testCreditAccountDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testCreditAccountDetails.getAccNumber()).isEqualTo(UPDATED_ACC_NUMBER);
    }

    @Test
    void putNonExistingCreditAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();
        creditAccountDetails.setId(longCount.incrementAndGet());

        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, creditAccountDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreditAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();
        creditAccountDetails.setId(longCount.incrementAndGet());

        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreditAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();
        creditAccountDetails.setId(longCount.incrementAndGet());

        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreditAccountDetailsWithPatch() throws Exception {
        // Initialize the database
        creditAccountDetailsRepository.save(creditAccountDetails).block();

        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();

        // Update the creditAccountDetails using partial update
        CreditAccountDetails partialUpdatedCreditAccountDetails = new CreditAccountDetails();
        partialUpdatedCreditAccountDetails.setId(creditAccountDetails.getId());

        partialUpdatedCreditAccountDetails.creditAccDetailsId(UPDATED_CREDIT_ACC_DETAILS_ID).accName(UPDATED_ACC_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreditAccountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreditAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        CreditAccountDetails testCreditAccountDetails = creditAccountDetailsList.get(creditAccountDetailsList.size() - 1);
        assertThat(testCreditAccountDetails.getCreditAccDetailsId()).isEqualTo(UPDATED_CREDIT_ACC_DETAILS_ID);
        assertThat(testCreditAccountDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testCreditAccountDetails.getCustomerId()).isEqualTo(DEFAULT_CUSTOMER_ID);
        assertThat(testCreditAccountDetails.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testCreditAccountDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testCreditAccountDetails.getAccNumber()).isEqualTo(DEFAULT_ACC_NUMBER);
    }

    @Test
    void fullUpdateCreditAccountDetailsWithPatch() throws Exception {
        // Initialize the database
        creditAccountDetailsRepository.save(creditAccountDetails).block();

        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();

        // Update the creditAccountDetails using partial update
        CreditAccountDetails partialUpdatedCreditAccountDetails = new CreditAccountDetails();
        partialUpdatedCreditAccountDetails.setId(creditAccountDetails.getId());

        partialUpdatedCreditAccountDetails
            .creditAccDetailsId(UPDATED_CREDIT_ACC_DETAILS_ID)
            .tenantId(UPDATED_TENANT_ID)
            .customerId(UPDATED_CUSTOMER_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .accNumber(UPDATED_ACC_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreditAccountDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreditAccountDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
        CreditAccountDetails testCreditAccountDetails = creditAccountDetailsList.get(creditAccountDetailsList.size() - 1);
        assertThat(testCreditAccountDetails.getCreditAccDetailsId()).isEqualTo(UPDATED_CREDIT_ACC_DETAILS_ID);
        assertThat(testCreditAccountDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testCreditAccountDetails.getCustomerId()).isEqualTo(UPDATED_CUSTOMER_ID);
        assertThat(testCreditAccountDetails.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testCreditAccountDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testCreditAccountDetails.getAccNumber()).isEqualTo(UPDATED_ACC_NUMBER);
    }

    @Test
    void patchNonExistingCreditAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();
        creditAccountDetails.setId(longCount.incrementAndGet());

        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, creditAccountDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreditAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();
        creditAccountDetails.setId(longCount.incrementAndGet());

        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreditAccountDetails() throws Exception {
        int databaseSizeBeforeUpdate = creditAccountDetailsRepository.findAll().collectList().block().size();
        creditAccountDetails.setId(longCount.incrementAndGet());

        // Create the CreditAccountDetails
        CreditAccountDetailsDTO creditAccountDetailsDTO = creditAccountDetailsMapper.toDto(creditAccountDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditAccountDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreditAccountDetails in the database
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreditAccountDetails() {
        // Initialize the database
        creditAccountDetailsRepository.save(creditAccountDetails).block();

        int databaseSizeBeforeDelete = creditAccountDetailsRepository.findAll().collectList().block().size();

        // Delete the creditAccountDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, creditAccountDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreditAccountDetails> creditAccountDetailsList = creditAccountDetailsRepository.findAll().collectList().block();
        assertThat(creditAccountDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
