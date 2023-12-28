package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.AnchorTrader;
import in.pft.apis.creditbazaar.repository.AnchorTraderRepository;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.service.mapper.AnchorTraderMapper;
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
 * Integration tests for the {@link AnchorTraderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AnchorTraderResourceIT {

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AT_ID = "AAAAAAAAAA";
    private static final String UPDATED_AT_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_ANCHOR_TRADER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_GST = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_PAN = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_PAN = "BBBBBBBBBB";

    private static final String DEFAULT_KYC_COMPLETED = "AAAAAAAAAA";
    private static final String UPDATED_KYC_COMPLETED = "BBBBBBBBBB";

    private static final String DEFAULT_BANK_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_BANK_DETAILS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/anchor-traders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnchorTraderRepository anchorTraderRepository;

    @Autowired
    private AnchorTraderMapper anchorTraderMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AnchorTrader anchorTrader;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnchorTrader createEntity(EntityManager em) {
        AnchorTrader anchorTrader = new AnchorTrader()
            .tenantId(DEFAULT_TENANT_ID)
            .atId(DEFAULT_AT_ID)
            .orgId(DEFAULT_ORG_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .orgName(DEFAULT_ORG_NAME)
            .gstId(DEFAULT_GST_ID)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .anchorTraderName(DEFAULT_ANCHOR_TRADER_NAME)
            .location(DEFAULT_LOCATION)
            .anchorTraderGST(DEFAULT_ANCHOR_TRADER_GST)
            .anchorTraderSector(DEFAULT_ANCHOR_TRADER_SECTOR)
            .anchorTraderPAN(DEFAULT_ANCHOR_TRADER_PAN)
            .kycCompleted(DEFAULT_KYC_COMPLETED)
            .bankDetails(DEFAULT_BANK_DETAILS);
        return anchorTrader;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnchorTrader createUpdatedEntity(EntityManager em) {
        AnchorTrader anchorTrader = new AnchorTrader()
            .tenantId(UPDATED_TENANT_ID)
            .atId(UPDATED_AT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .location(UPDATED_LOCATION)
            .anchorTraderGST(UPDATED_ANCHOR_TRADER_GST)
            .anchorTraderSector(UPDATED_ANCHOR_TRADER_SECTOR)
            .anchorTraderPAN(UPDATED_ANCHOR_TRADER_PAN)
            .kycCompleted(UPDATED_KYC_COMPLETED)
            .bankDetails(UPDATED_BANK_DETAILS);
        return anchorTrader;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AnchorTrader.class).block();
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
        anchorTrader = createEntity(em);
    }

    @Test
    void createAnchorTrader() throws Exception {
        int databaseSizeBeforeCreate = anchorTraderRepository.findAll().collectList().block().size();
        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeCreate + 1);
        AnchorTrader testAnchorTrader = anchorTraderList.get(anchorTraderList.size() - 1);
        assertThat(testAnchorTrader.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testAnchorTrader.getAtId()).isEqualTo(DEFAULT_AT_ID);
        assertThat(testAnchorTrader.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testAnchorTrader.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testAnchorTrader.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testAnchorTrader.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testAnchorTrader.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAnchorTrader.getAnchorTraderName()).isEqualTo(DEFAULT_ANCHOR_TRADER_NAME);
        assertThat(testAnchorTrader.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testAnchorTrader.getAnchorTraderGST()).isEqualTo(DEFAULT_ANCHOR_TRADER_GST);
        assertThat(testAnchorTrader.getAnchorTraderSector()).isEqualTo(DEFAULT_ANCHOR_TRADER_SECTOR);
        assertThat(testAnchorTrader.getAnchorTraderPAN()).isEqualTo(DEFAULT_ANCHOR_TRADER_PAN);
        assertThat(testAnchorTrader.getKycCompleted()).isEqualTo(DEFAULT_KYC_COMPLETED);
        assertThat(testAnchorTrader.getBankDetails()).isEqualTo(DEFAULT_BANK_DETAILS);
    }

    @Test
    void createAnchorTraderWithExistingId() throws Exception {
        // Create the AnchorTrader with an existing ID
        anchorTrader.setId(1L);
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        int databaseSizeBeforeCreate = anchorTraderRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setTenantId(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAtIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setAtId(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setOrgId(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setCustomerName(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setOrgName(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGstIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setGstId(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = anchorTraderRepository.findAll().collectList().block().size();
        // set the field null
        anchorTrader.setPhoneNumber(null);

        // Create the AnchorTrader, which fails.
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAnchorTraders() {
        // Initialize the database
        anchorTraderRepository.save(anchorTrader).block();

        // Get all the anchorTraderList
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
            .value(hasItem(anchorTrader.getId().intValue()))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID))
            .jsonPath("$.[*].atId")
            .value(hasItem(DEFAULT_AT_ID))
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
            .jsonPath("$.[*].anchorTraderName")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_NAME))
            .jsonPath("$.[*].location")
            .value(hasItem(DEFAULT_LOCATION))
            .jsonPath("$.[*].anchorTraderGST")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_GST))
            .jsonPath("$.[*].anchorTraderSector")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_SECTOR))
            .jsonPath("$.[*].anchorTraderPAN")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_PAN))
            .jsonPath("$.[*].kycCompleted")
            .value(hasItem(DEFAULT_KYC_COMPLETED))
            .jsonPath("$.[*].bankDetails")
            .value(hasItem(DEFAULT_BANK_DETAILS));
    }

    @Test
    void getAnchorTrader() {
        // Initialize the database
        anchorTraderRepository.save(anchorTrader).block();

        // Get the anchorTrader
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, anchorTrader.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(anchorTrader.getId().intValue()))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID))
            .jsonPath("$.atId")
            .value(is(DEFAULT_AT_ID))
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
            .jsonPath("$.anchorTraderName")
            .value(is(DEFAULT_ANCHOR_TRADER_NAME))
            .jsonPath("$.location")
            .value(is(DEFAULT_LOCATION))
            .jsonPath("$.anchorTraderGST")
            .value(is(DEFAULT_ANCHOR_TRADER_GST))
            .jsonPath("$.anchorTraderSector")
            .value(is(DEFAULT_ANCHOR_TRADER_SECTOR))
            .jsonPath("$.anchorTraderPAN")
            .value(is(DEFAULT_ANCHOR_TRADER_PAN))
            .jsonPath("$.kycCompleted")
            .value(is(DEFAULT_KYC_COMPLETED))
            .jsonPath("$.bankDetails")
            .value(is(DEFAULT_BANK_DETAILS));
    }

    @Test
    void getNonExistingAnchorTrader() {
        // Get the anchorTrader
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAnchorTrader() throws Exception {
        // Initialize the database
        anchorTraderRepository.save(anchorTrader).block();

        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();

        // Update the anchorTrader
        AnchorTrader updatedAnchorTrader = anchorTraderRepository.findById(anchorTrader.getId()).block();
        updatedAnchorTrader
            .tenantId(UPDATED_TENANT_ID)
            .atId(UPDATED_AT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .location(UPDATED_LOCATION)
            .anchorTraderGST(UPDATED_ANCHOR_TRADER_GST)
            .anchorTraderSector(UPDATED_ANCHOR_TRADER_SECTOR)
            .anchorTraderPAN(UPDATED_ANCHOR_TRADER_PAN)
            .kycCompleted(UPDATED_KYC_COMPLETED)
            .bankDetails(UPDATED_BANK_DETAILS);
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(updatedAnchorTrader);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, anchorTraderDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
        AnchorTrader testAnchorTrader = anchorTraderList.get(anchorTraderList.size() - 1);
        assertThat(testAnchorTrader.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testAnchorTrader.getAtId()).isEqualTo(UPDATED_AT_ID);
        assertThat(testAnchorTrader.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testAnchorTrader.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testAnchorTrader.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testAnchorTrader.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testAnchorTrader.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAnchorTrader.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testAnchorTrader.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testAnchorTrader.getAnchorTraderGST()).isEqualTo(UPDATED_ANCHOR_TRADER_GST);
        assertThat(testAnchorTrader.getAnchorTraderSector()).isEqualTo(UPDATED_ANCHOR_TRADER_SECTOR);
        assertThat(testAnchorTrader.getAnchorTraderPAN()).isEqualTo(UPDATED_ANCHOR_TRADER_PAN);
        assertThat(testAnchorTrader.getKycCompleted()).isEqualTo(UPDATED_KYC_COMPLETED);
        assertThat(testAnchorTrader.getBankDetails()).isEqualTo(UPDATED_BANK_DETAILS);
    }

    @Test
    void putNonExistingAnchorTrader() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();
        anchorTrader.setId(longCount.incrementAndGet());

        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, anchorTraderDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAnchorTrader() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();
        anchorTrader.setId(longCount.incrementAndGet());

        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAnchorTrader() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();
        anchorTrader.setId(longCount.incrementAndGet());

        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAnchorTraderWithPatch() throws Exception {
        // Initialize the database
        anchorTraderRepository.save(anchorTrader).block();

        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();

        // Update the anchorTrader using partial update
        AnchorTrader partialUpdatedAnchorTrader = new AnchorTrader();
        partialUpdatedAnchorTrader.setId(anchorTrader.getId());

        partialUpdatedAnchorTrader.tenantId(UPDATED_TENANT_ID).atId(UPDATED_AT_ID).orgId(UPDATED_ORG_ID).orgName(UPDATED_ORG_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAnchorTrader.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAnchorTrader))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
        AnchorTrader testAnchorTrader = anchorTraderList.get(anchorTraderList.size() - 1);
        assertThat(testAnchorTrader.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testAnchorTrader.getAtId()).isEqualTo(UPDATED_AT_ID);
        assertThat(testAnchorTrader.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testAnchorTrader.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testAnchorTrader.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testAnchorTrader.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testAnchorTrader.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAnchorTrader.getAnchorTraderName()).isEqualTo(DEFAULT_ANCHOR_TRADER_NAME);
        assertThat(testAnchorTrader.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testAnchorTrader.getAnchorTraderGST()).isEqualTo(DEFAULT_ANCHOR_TRADER_GST);
        assertThat(testAnchorTrader.getAnchorTraderSector()).isEqualTo(DEFAULT_ANCHOR_TRADER_SECTOR);
        assertThat(testAnchorTrader.getAnchorTraderPAN()).isEqualTo(DEFAULT_ANCHOR_TRADER_PAN);
        assertThat(testAnchorTrader.getKycCompleted()).isEqualTo(DEFAULT_KYC_COMPLETED);
        assertThat(testAnchorTrader.getBankDetails()).isEqualTo(DEFAULT_BANK_DETAILS);
    }

    @Test
    void fullUpdateAnchorTraderWithPatch() throws Exception {
        // Initialize the database
        anchorTraderRepository.save(anchorTrader).block();

        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();

        // Update the anchorTrader using partial update
        AnchorTrader partialUpdatedAnchorTrader = new AnchorTrader();
        partialUpdatedAnchorTrader.setId(anchorTrader.getId());

        partialUpdatedAnchorTrader
            .tenantId(UPDATED_TENANT_ID)
            .atId(UPDATED_AT_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .location(UPDATED_LOCATION)
            .anchorTraderGST(UPDATED_ANCHOR_TRADER_GST)
            .anchorTraderSector(UPDATED_ANCHOR_TRADER_SECTOR)
            .anchorTraderPAN(UPDATED_ANCHOR_TRADER_PAN)
            .kycCompleted(UPDATED_KYC_COMPLETED)
            .bankDetails(UPDATED_BANK_DETAILS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAnchorTrader.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAnchorTrader))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
        AnchorTrader testAnchorTrader = anchorTraderList.get(anchorTraderList.size() - 1);
        assertThat(testAnchorTrader.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testAnchorTrader.getAtId()).isEqualTo(UPDATED_AT_ID);
        assertThat(testAnchorTrader.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testAnchorTrader.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testAnchorTrader.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testAnchorTrader.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testAnchorTrader.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAnchorTrader.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testAnchorTrader.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testAnchorTrader.getAnchorTraderGST()).isEqualTo(UPDATED_ANCHOR_TRADER_GST);
        assertThat(testAnchorTrader.getAnchorTraderSector()).isEqualTo(UPDATED_ANCHOR_TRADER_SECTOR);
        assertThat(testAnchorTrader.getAnchorTraderPAN()).isEqualTo(UPDATED_ANCHOR_TRADER_PAN);
        assertThat(testAnchorTrader.getKycCompleted()).isEqualTo(UPDATED_KYC_COMPLETED);
        assertThat(testAnchorTrader.getBankDetails()).isEqualTo(UPDATED_BANK_DETAILS);
    }

    @Test
    void patchNonExistingAnchorTrader() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();
        anchorTrader.setId(longCount.incrementAndGet());

        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, anchorTraderDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAnchorTrader() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();
        anchorTrader.setId(longCount.incrementAndGet());

        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAnchorTrader() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderRepository.findAll().collectList().block().size();
        anchorTrader.setId(longCount.incrementAndGet());

        // Create the AnchorTrader
        AnchorTraderDTO anchorTraderDTO = anchorTraderMapper.toDto(anchorTrader);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AnchorTrader in the database
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAnchorTrader() {
        // Initialize the database
        anchorTraderRepository.save(anchorTrader).block();

        int databaseSizeBeforeDelete = anchorTraderRepository.findAll().collectList().block().size();

        // Delete the anchorTrader
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, anchorTrader.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AnchorTrader> anchorTraderList = anchorTraderRepository.findAll().collectList().block();
        assertThat(anchorTraderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
