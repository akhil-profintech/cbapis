package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.FinancePartnerRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FinancePartnerMapper;
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
 * Integration tests for the {@link FinancePartnerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FinancePartnerResourceIT {

    private static final Long DEFAULT_FP_ID = 1L;
    private static final Long UPDATED_FP_ID = 2L;

    private static final String DEFAULT_FP_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_FP_ULID_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_TOS_DOCUMENT = "AAAAAAAAAA";
    private static final String UPDATED_TOS_DOCUMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/finance-partners";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FinancePartnerRepository financePartnerRepository;

    @Autowired
    private FinancePartnerMapper financePartnerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FinancePartner financePartner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancePartner createEntity(EntityManager em) {
        FinancePartner financePartner = new FinancePartner()
            .fpId(DEFAULT_FP_ID)
            .fpUlidId(DEFAULT_FP_ULID_ID)
            .tenantId(DEFAULT_TENANT_ID)
            .orgId(DEFAULT_ORG_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .orgName(DEFAULT_ORG_NAME)
            .gstId(DEFAULT_GST_ID)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .tosDocument(DEFAULT_TOS_DOCUMENT);
        return financePartner;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinancePartner createUpdatedEntity(EntityManager em) {
        FinancePartner financePartner = new FinancePartner()
            .fpId(UPDATED_FP_ID)
            .fpUlidId(UPDATED_FP_ULID_ID)
            .tenantId(UPDATED_TENANT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tosDocument(UPDATED_TOS_DOCUMENT);
        return financePartner;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FinancePartner.class).block();
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
        financePartner = createEntity(em);
    }

    @Test
    void createFinancePartner() throws Exception {
        int databaseSizeBeforeCreate = financePartnerRepository.findAll().collectList().block().size();
        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeCreate + 1);
        FinancePartner testFinancePartner = financePartnerList.get(financePartnerList.size() - 1);
        assertThat(testFinancePartner.getFpId()).isEqualTo(DEFAULT_FP_ID);
        assertThat(testFinancePartner.getFpUlidId()).isEqualTo(DEFAULT_FP_ULID_ID);
        assertThat(testFinancePartner.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testFinancePartner.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testFinancePartner.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testFinancePartner.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testFinancePartner.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testFinancePartner.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testFinancePartner.getTosDocument()).isEqualTo(DEFAULT_TOS_DOCUMENT);
    }

    @Test
    void createFinancePartnerWithExistingId() throws Exception {
        // Create the FinancePartner with an existing ID
        financePartner.setId(1L);
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        int databaseSizeBeforeCreate = financePartnerRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = financePartnerRepository.findAll().collectList().block().size();
        // set the field null
        financePartner.setTenantId(null);

        // Create the FinancePartner, which fails.
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = financePartnerRepository.findAll().collectList().block().size();
        // set the field null
        financePartner.setOrgId(null);

        // Create the FinancePartner, which fails.
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = financePartnerRepository.findAll().collectList().block().size();
        // set the field null
        financePartner.setCustomerName(null);

        // Create the FinancePartner, which fails.
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = financePartnerRepository.findAll().collectList().block().size();
        // set the field null
        financePartner.setOrgName(null);

        // Create the FinancePartner, which fails.
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGstIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = financePartnerRepository.findAll().collectList().block().size();
        // set the field null
        financePartner.setGstId(null);

        // Create the FinancePartner, which fails.
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = financePartnerRepository.findAll().collectList().block().size();
        // set the field null
        financePartner.setPhoneNumber(null);

        // Create the FinancePartner, which fails.
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFinancePartners() {
        // Initialize the database
        financePartnerRepository.save(financePartner).block();

        // Get all the financePartnerList
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
            .value(hasItem(financePartner.getId().intValue()))
            .jsonPath("$.[*].fpId")
            .value(hasItem(DEFAULT_FP_ID.intValue()))
            .jsonPath("$.[*].fpUlidId")
            .value(hasItem(DEFAULT_FP_ULID_ID))
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
            .value(hasItem(DEFAULT_PHONE_NUMBER.intValue()))
            .jsonPath("$.[*].tosDocument")
            .value(hasItem(DEFAULT_TOS_DOCUMENT));
    }

    @Test
    void getFinancePartner() {
        // Initialize the database
        financePartnerRepository.save(financePartner).block();

        // Get the financePartner
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, financePartner.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(financePartner.getId().intValue()))
            .jsonPath("$.fpId")
            .value(is(DEFAULT_FP_ID.intValue()))
            .jsonPath("$.fpUlidId")
            .value(is(DEFAULT_FP_ULID_ID))
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
            .value(is(DEFAULT_PHONE_NUMBER.intValue()))
            .jsonPath("$.tosDocument")
            .value(is(DEFAULT_TOS_DOCUMENT));
    }

    @Test
    void getNonExistingFinancePartner() {
        // Get the financePartner
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFinancePartner() throws Exception {
        // Initialize the database
        financePartnerRepository.save(financePartner).block();

        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();

        // Update the financePartner
        FinancePartner updatedFinancePartner = financePartnerRepository.findById(financePartner.getId()).block();
        updatedFinancePartner
            .fpId(UPDATED_FP_ID)
            .fpUlidId(UPDATED_FP_ULID_ID)
            .tenantId(UPDATED_TENANT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tosDocument(UPDATED_TOS_DOCUMENT);
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(updatedFinancePartner);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, financePartnerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
        FinancePartner testFinancePartner = financePartnerList.get(financePartnerList.size() - 1);
        assertThat(testFinancePartner.getFpId()).isEqualTo(UPDATED_FP_ID);
        assertThat(testFinancePartner.getFpUlidId()).isEqualTo(UPDATED_FP_ULID_ID);
        assertThat(testFinancePartner.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testFinancePartner.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testFinancePartner.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testFinancePartner.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testFinancePartner.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testFinancePartner.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testFinancePartner.getTosDocument()).isEqualTo(UPDATED_TOS_DOCUMENT);
    }

    @Test
    void putNonExistingFinancePartner() throws Exception {
        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();
        financePartner.setId(longCount.incrementAndGet());

        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, financePartnerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFinancePartner() throws Exception {
        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();
        financePartner.setId(longCount.incrementAndGet());

        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFinancePartner() throws Exception {
        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();
        financePartner.setId(longCount.incrementAndGet());

        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFinancePartnerWithPatch() throws Exception {
        // Initialize the database
        financePartnerRepository.save(financePartner).block();

        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();

        // Update the financePartner using partial update
        FinancePartner partialUpdatedFinancePartner = new FinancePartner();
        partialUpdatedFinancePartner.setId(financePartner.getId());

        partialUpdatedFinancePartner
            .fpUlidId(UPDATED_FP_ULID_ID)
            .orgId(UPDATED_ORG_ID)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFinancePartner.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFinancePartner))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
        FinancePartner testFinancePartner = financePartnerList.get(financePartnerList.size() - 1);
        assertThat(testFinancePartner.getFpId()).isEqualTo(DEFAULT_FP_ID);
        assertThat(testFinancePartner.getFpUlidId()).isEqualTo(UPDATED_FP_ULID_ID);
        assertThat(testFinancePartner.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testFinancePartner.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testFinancePartner.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testFinancePartner.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testFinancePartner.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testFinancePartner.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testFinancePartner.getTosDocument()).isEqualTo(DEFAULT_TOS_DOCUMENT);
    }

    @Test
    void fullUpdateFinancePartnerWithPatch() throws Exception {
        // Initialize the database
        financePartnerRepository.save(financePartner).block();

        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();

        // Update the financePartner using partial update
        FinancePartner partialUpdatedFinancePartner = new FinancePartner();
        partialUpdatedFinancePartner.setId(financePartner.getId());

        partialUpdatedFinancePartner
            .fpId(UPDATED_FP_ID)
            .fpUlidId(UPDATED_FP_ULID_ID)
            .tenantId(UPDATED_TENANT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tosDocument(UPDATED_TOS_DOCUMENT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFinancePartner.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFinancePartner))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
        FinancePartner testFinancePartner = financePartnerList.get(financePartnerList.size() - 1);
        assertThat(testFinancePartner.getFpId()).isEqualTo(UPDATED_FP_ID);
        assertThat(testFinancePartner.getFpUlidId()).isEqualTo(UPDATED_FP_ULID_ID);
        assertThat(testFinancePartner.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testFinancePartner.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testFinancePartner.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testFinancePartner.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testFinancePartner.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testFinancePartner.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testFinancePartner.getTosDocument()).isEqualTo(UPDATED_TOS_DOCUMENT);
    }

    @Test
    void patchNonExistingFinancePartner() throws Exception {
        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();
        financePartner.setId(longCount.incrementAndGet());

        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, financePartnerDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFinancePartner() throws Exception {
        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();
        financePartner.setId(longCount.incrementAndGet());

        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFinancePartner() throws Exception {
        int databaseSizeBeforeUpdate = financePartnerRepository.findAll().collectList().block().size();
        financePartner.setId(longCount.incrementAndGet());

        // Create the FinancePartner
        FinancePartnerDTO financePartnerDTO = financePartnerMapper.toDto(financePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financePartnerDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FinancePartner in the database
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFinancePartner() {
        // Initialize the database
        financePartnerRepository.save(financePartner).block();

        int databaseSizeBeforeDelete = financePartnerRepository.findAll().collectList().block().size();

        // Delete the financePartner
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, financePartner.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FinancePartner> financePartnerList = financePartnerRepository.findAll().collectList().block();
        assertThat(financePartnerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
