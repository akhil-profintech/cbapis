package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.CreditBazaarIntegrator;
import in.pft.apis.creditbazaar.gateway.repository.CreditBazaarIntegratorRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.dto.CreditBazaarIntegratorDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CreditBazaarIntegratorMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CreditBazaarIntegratorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CreditBazaarIntegratorResourceIT {

    private static final String DEFAULT_INTEGRATOR_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_INTEGRATOR_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GST_ID = "AAAAAAAAAA";
    private static final String UPDATED_GST_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE_NUMBER = 1L;
    private static final Long UPDATED_PHONE_NUMBER = 2L;

    private static final String ENTITY_API_URL = "/api/credit-bazaar-integrators";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CreditBazaarIntegratorRepository creditBazaarIntegratorRepository;

    @Autowired
    private CreditBazaarIntegratorMapper creditBazaarIntegratorMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CreditBazaarIntegrator creditBazaarIntegrator;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditBazaarIntegrator createEntity(EntityManager em) {
        CreditBazaarIntegrator creditBazaarIntegrator = new CreditBazaarIntegrator()
            .integratorUlidId(DEFAULT_INTEGRATOR_ULID_ID)
            .tenantId(DEFAULT_TENANT_ID)
            .orgId(DEFAULT_ORG_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .orgName(DEFAULT_ORG_NAME)
            .gstId(DEFAULT_GST_ID)
            .phoneNumber(DEFAULT_PHONE_NUMBER);
        return creditBazaarIntegrator;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CreditBazaarIntegrator createUpdatedEntity(EntityManager em) {
        CreditBazaarIntegrator creditBazaarIntegrator = new CreditBazaarIntegrator()
            .integratorUlidId(UPDATED_INTEGRATOR_ULID_ID)
            .tenantId(UPDATED_TENANT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        return creditBazaarIntegrator;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CreditBazaarIntegrator.class).block();
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
        creditBazaarIntegrator = createEntity(em);
    }

    @Test
    void createCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeCreate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeCreate + 1);
        CreditBazaarIntegrator testCreditBazaarIntegrator = creditBazaarIntegratorList.get(creditBazaarIntegratorList.size() - 1);
        assertThat(testCreditBazaarIntegrator.getIntegratorUlidId()).isEqualTo(DEFAULT_INTEGRATOR_ULID_ID);
        assertThat(testCreditBazaarIntegrator.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testCreditBazaarIntegrator.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testCreditBazaarIntegrator.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testCreditBazaarIntegrator.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testCreditBazaarIntegrator.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testCreditBazaarIntegrator.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    void createCreditBazaarIntegratorWithExistingId() throws Exception {
        // Create the CreditBazaarIntegrator with an existing ID
        creditBazaarIntegrator.setId(1L);
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        int databaseSizeBeforeCreate = creditBazaarIntegratorRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // set the field null
        creditBazaarIntegrator.setTenantId(null);

        // Create the CreditBazaarIntegrator, which fails.
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // set the field null
        creditBazaarIntegrator.setOrgId(null);

        // Create the CreditBazaarIntegrator, which fails.
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // set the field null
        creditBazaarIntegrator.setCustomerName(null);

        // Create the CreditBazaarIntegrator, which fails.
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // set the field null
        creditBazaarIntegrator.setOrgName(null);

        // Create the CreditBazaarIntegrator, which fails.
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGstIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // set the field null
        creditBazaarIntegrator.setGstId(null);

        // Create the CreditBazaarIntegrator, which fails.
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        // set the field null
        creditBazaarIntegrator.setPhoneNumber(null);

        // Create the CreditBazaarIntegrator, which fails.
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllCreditBazaarIntegrators() {
        // Initialize the database
        creditBazaarIntegratorRepository.save(creditBazaarIntegrator).block();

        // Get all the creditBazaarIntegratorList
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
            .value(hasItem(creditBazaarIntegrator.getId().intValue()))
            .jsonPath("$.[*].integratorUlidId")
            .value(hasItem(DEFAULT_INTEGRATOR_ULID_ID))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID))
            .jsonPath("$.[*].orgId")
            .value(hasItem(DEFAULT_ORG_ID))
            .jsonPath("$.[*].customerName")
            .value(hasItem(DEFAULT_CUSTOMER_NAME))
            .jsonPath("$.[*].orgName")
            .value(hasItem(DEFAULT_ORG_NAME))
            .jsonPath("$.[*].gstId")
            .value(hasItem(DEFAULT_GST_ID))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER.intValue()));
    }

    @Test
    void getCreditBazaarIntegrator() {
        // Initialize the database
        creditBazaarIntegratorRepository.save(creditBazaarIntegrator).block();

        // Get the creditBazaarIntegrator
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, creditBazaarIntegrator.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(creditBazaarIntegrator.getId().intValue()))
            .jsonPath("$.integratorUlidId")
            .value(is(DEFAULT_INTEGRATOR_ULID_ID))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID))
            .jsonPath("$.orgId")
            .value(is(DEFAULT_ORG_ID))
            .jsonPath("$.customerName")
            .value(is(DEFAULT_CUSTOMER_NAME))
            .jsonPath("$.orgName")
            .value(is(DEFAULT_ORG_NAME))
            .jsonPath("$.gstId")
            .value(is(DEFAULT_GST_ID))
            .jsonPath("$.phoneNumber")
            .value(is(DEFAULT_PHONE_NUMBER.intValue()));
    }

    @Test
    void getNonExistingCreditBazaarIntegrator() {
        // Get the creditBazaarIntegrator
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCreditBazaarIntegrator() throws Exception {
        // Initialize the database
        creditBazaarIntegratorRepository.save(creditBazaarIntegrator).block();

        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();

        // Update the creditBazaarIntegrator
        CreditBazaarIntegrator updatedCreditBazaarIntegrator = creditBazaarIntegratorRepository
            .findById(creditBazaarIntegrator.getId())
            .block();
        updatedCreditBazaarIntegrator
            .integratorUlidId(UPDATED_INTEGRATOR_ULID_ID)
            .tenantId(UPDATED_TENANT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER);
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(updatedCreditBazaarIntegrator);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, creditBazaarIntegratorDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
        CreditBazaarIntegrator testCreditBazaarIntegrator = creditBazaarIntegratorList.get(creditBazaarIntegratorList.size() - 1);
        assertThat(testCreditBazaarIntegrator.getIntegratorUlidId()).isEqualTo(UPDATED_INTEGRATOR_ULID_ID);
        assertThat(testCreditBazaarIntegrator.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testCreditBazaarIntegrator.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testCreditBazaarIntegrator.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCreditBazaarIntegrator.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testCreditBazaarIntegrator.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testCreditBazaarIntegrator.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    void putNonExistingCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        creditBazaarIntegrator.setId(longCount.incrementAndGet());

        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, creditBazaarIntegratorDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        creditBazaarIntegrator.setId(longCount.incrementAndGet());

        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        creditBazaarIntegrator.setId(longCount.incrementAndGet());

        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCreditBazaarIntegratorWithPatch() throws Exception {
        // Initialize the database
        creditBazaarIntegratorRepository.save(creditBazaarIntegrator).block();

        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();

        // Update the creditBazaarIntegrator using partial update
        CreditBazaarIntegrator partialUpdatedCreditBazaarIntegrator = new CreditBazaarIntegrator();
        partialUpdatedCreditBazaarIntegrator.setId(creditBazaarIntegrator.getId());

        partialUpdatedCreditBazaarIntegrator.customerName(UPDATED_CUSTOMER_NAME).orgName(UPDATED_ORG_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreditBazaarIntegrator.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreditBazaarIntegrator))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
        CreditBazaarIntegrator testCreditBazaarIntegrator = creditBazaarIntegratorList.get(creditBazaarIntegratorList.size() - 1);
        assertThat(testCreditBazaarIntegrator.getIntegratorUlidId()).isEqualTo(DEFAULT_INTEGRATOR_ULID_ID);
        assertThat(testCreditBazaarIntegrator.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testCreditBazaarIntegrator.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testCreditBazaarIntegrator.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCreditBazaarIntegrator.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testCreditBazaarIntegrator.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testCreditBazaarIntegrator.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
    }

    @Test
    void fullUpdateCreditBazaarIntegratorWithPatch() throws Exception {
        // Initialize the database
        creditBazaarIntegratorRepository.save(creditBazaarIntegrator).block();

        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();

        // Update the creditBazaarIntegrator using partial update
        CreditBazaarIntegrator partialUpdatedCreditBazaarIntegrator = new CreditBazaarIntegrator();
        partialUpdatedCreditBazaarIntegrator.setId(creditBazaarIntegrator.getId());

        partialUpdatedCreditBazaarIntegrator
            .integratorUlidId(UPDATED_INTEGRATOR_ULID_ID)
            .tenantId(UPDATED_TENANT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCreditBazaarIntegrator.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCreditBazaarIntegrator))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
        CreditBazaarIntegrator testCreditBazaarIntegrator = creditBazaarIntegratorList.get(creditBazaarIntegratorList.size() - 1);
        assertThat(testCreditBazaarIntegrator.getIntegratorUlidId()).isEqualTo(UPDATED_INTEGRATOR_ULID_ID);
        assertThat(testCreditBazaarIntegrator.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testCreditBazaarIntegrator.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testCreditBazaarIntegrator.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCreditBazaarIntegrator.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testCreditBazaarIntegrator.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testCreditBazaarIntegrator.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
    }

    @Test
    void patchNonExistingCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        creditBazaarIntegrator.setId(longCount.incrementAndGet());

        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, creditBazaarIntegratorDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        creditBazaarIntegrator.setId(longCount.incrementAndGet());

        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCreditBazaarIntegrator() throws Exception {
        int databaseSizeBeforeUpdate = creditBazaarIntegratorRepository.findAll().collectList().block().size();
        creditBazaarIntegrator.setId(longCount.incrementAndGet());

        // Create the CreditBazaarIntegrator
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = creditBazaarIntegratorMapper.toDto(creditBazaarIntegrator);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(creditBazaarIntegratorDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CreditBazaarIntegrator in the database
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCreditBazaarIntegrator() {
        // Initialize the database
        creditBazaarIntegratorRepository.save(creditBazaarIntegrator).block();

        int databaseSizeBeforeDelete = creditBazaarIntegratorRepository.findAll().collectList().block().size();

        // Delete the creditBazaarIntegrator
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, creditBazaarIntegrator.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CreditBazaarIntegrator> creditBazaarIntegratorList = creditBazaarIntegratorRepository.findAll().collectList().block();
        assertThat(creditBazaarIntegratorList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
