package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.TradePartner;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.TradePartnerRepository;
import in.pft.apis.creditbazaar.service.dto.TradePartnerDTO;
import in.pft.apis.creditbazaar.service.mapper.TradePartnerMapper;
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
 * Integration tests for the {@link TradePartnerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TradePartnerResourceIT {

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TP_ID = "AAAAAAAAAA";
    private static final String UPDATED_TP_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_TRADE_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_TRADEPARTNER_GST = "AAAAAAAAAA";
    private static final String UPDATED_TRADEPARTNER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/trade-partners";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TradePartnerRepository tradePartnerRepository;

    @Autowired
    private TradePartnerMapper tradePartnerMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TradePartner tradePartner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradePartner createEntity(EntityManager em) {
        TradePartner tradePartner = new TradePartner()
            .tenantId(DEFAULT_TENANT_ID)
            .tpId(DEFAULT_TP_ID)
            .orgId(DEFAULT_ORG_ID)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .orgName(DEFAULT_ORG_NAME)
            .gstId(DEFAULT_GST_ID)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .tradePartnerName(DEFAULT_TRADE_PARTNER_NAME)
            .location(DEFAULT_LOCATION)
            .tradepartnerGST(DEFAULT_TRADEPARTNER_GST)
            .tradePartnerSector(DEFAULT_TRADE_PARTNER_SECTOR)
            .acceptanceFromTradePartner(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER);
        return tradePartner;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradePartner createUpdatedEntity(EntityManager em) {
        TradePartner tradePartner = new TradePartner()
            .tenantId(UPDATED_TENANT_ID)
            .tpId(UPDATED_TP_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .location(UPDATED_LOCATION)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
        return tradePartner;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TradePartner.class).block();
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
        tradePartner = createEntity(em);
    }

    @Test
    void createTradePartner() throws Exception {
        int databaseSizeBeforeCreate = tradePartnerRepository.findAll().collectList().block().size();
        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeCreate + 1);
        TradePartner testTradePartner = tradePartnerList.get(tradePartnerList.size() - 1);
        assertThat(testTradePartner.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTradePartner.getTpId()).isEqualTo(DEFAULT_TP_ID);
        assertThat(testTradePartner.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testTradePartner.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testTradePartner.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testTradePartner.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testTradePartner.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testTradePartner.getTradePartnerName()).isEqualTo(DEFAULT_TRADE_PARTNER_NAME);
        assertThat(testTradePartner.getLocation()).isEqualTo(DEFAULT_LOCATION);
        assertThat(testTradePartner.getTradepartnerGST()).isEqualTo(DEFAULT_TRADEPARTNER_GST);
        assertThat(testTradePartner.getTradePartnerSector()).isEqualTo(DEFAULT_TRADE_PARTNER_SECTOR);
        assertThat(testTradePartner.getAcceptanceFromTradePartner()).isEqualTo(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER);
    }

    @Test
    void createTradePartnerWithExistingId() throws Exception {
        // Create the TradePartner with an existing ID
        tradePartner.setId(1L);
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        int databaseSizeBeforeCreate = tradePartnerRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setTenantId(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTpIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setTpId(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setOrgId(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setCustomerName(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOrgNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setOrgName(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGstIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setGstId(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = tradePartnerRepository.findAll().collectList().block().size();
        // set the field null
        tradePartner.setPhoneNumber(null);

        // Create the TradePartner, which fails.
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllTradePartners() {
        // Initialize the database
        tradePartnerRepository.save(tradePartner).block();

        // Get all the tradePartnerList
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
            .value(hasItem(tradePartner.getId().intValue()))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID))
            .jsonPath("$.[*].tpId")
            .value(hasItem(DEFAULT_TP_ID))
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
            .jsonPath("$.[*].tradePartnerName")
            .value(hasItem(DEFAULT_TRADE_PARTNER_NAME))
            .jsonPath("$.[*].location")
            .value(hasItem(DEFAULT_LOCATION))
            .jsonPath("$.[*].tradepartnerGST")
            .value(hasItem(DEFAULT_TRADEPARTNER_GST))
            .jsonPath("$.[*].tradePartnerSector")
            .value(hasItem(DEFAULT_TRADE_PARTNER_SECTOR))
            .jsonPath("$.[*].acceptanceFromTradePartner")
            .value(hasItem(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER));
    }

    @Test
    void getTradePartner() {
        // Initialize the database
        tradePartnerRepository.save(tradePartner).block();

        // Get the tradePartner
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tradePartner.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tradePartner.getId().intValue()))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID))
            .jsonPath("$.tpId")
            .value(is(DEFAULT_TP_ID))
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
            .jsonPath("$.tradePartnerName")
            .value(is(DEFAULT_TRADE_PARTNER_NAME))
            .jsonPath("$.location")
            .value(is(DEFAULT_LOCATION))
            .jsonPath("$.tradepartnerGST")
            .value(is(DEFAULT_TRADEPARTNER_GST))
            .jsonPath("$.tradePartnerSector")
            .value(is(DEFAULT_TRADE_PARTNER_SECTOR))
            .jsonPath("$.acceptanceFromTradePartner")
            .value(is(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER));
    }

    @Test
    void getNonExistingTradePartner() {
        // Get the tradePartner
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingTradePartner() throws Exception {
        // Initialize the database
        tradePartnerRepository.save(tradePartner).block();

        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();

        // Update the tradePartner
        TradePartner updatedTradePartner = tradePartnerRepository.findById(tradePartner.getId()).block();
        updatedTradePartner
            .tenantId(UPDATED_TENANT_ID)
            .tpId(UPDATED_TP_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .location(UPDATED_LOCATION)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(updatedTradePartner);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradePartnerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
        TradePartner testTradePartner = tradePartnerList.get(tradePartnerList.size() - 1);
        assertThat(testTradePartner.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTradePartner.getTpId()).isEqualTo(UPDATED_TP_ID);
        assertThat(testTradePartner.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testTradePartner.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testTradePartner.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testTradePartner.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testTradePartner.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTradePartner.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testTradePartner.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testTradePartner.getTradepartnerGST()).isEqualTo(UPDATED_TRADEPARTNER_GST);
        assertThat(testTradePartner.getTradePartnerSector()).isEqualTo(UPDATED_TRADE_PARTNER_SECTOR);
        assertThat(testTradePartner.getAcceptanceFromTradePartner()).isEqualTo(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
    }

    @Test
    void putNonExistingTradePartner() throws Exception {
        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();
        tradePartner.setId(longCount.incrementAndGet());

        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradePartnerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTradePartner() throws Exception {
        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();
        tradePartner.setId(longCount.incrementAndGet());

        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTradePartner() throws Exception {
        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();
        tradePartner.setId(longCount.incrementAndGet());

        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTradePartnerWithPatch() throws Exception {
        // Initialize the database
        tradePartnerRepository.save(tradePartner).block();

        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();

        // Update the tradePartner using partial update
        TradePartner partialUpdatedTradePartner = new TradePartner();
        partialUpdatedTradePartner.setId(tradePartner.getId());

        partialUpdatedTradePartner
            .tpId(UPDATED_TP_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .location(UPDATED_LOCATION)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTradePartner.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTradePartner))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
        TradePartner testTradePartner = tradePartnerList.get(tradePartnerList.size() - 1);
        assertThat(testTradePartner.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTradePartner.getTpId()).isEqualTo(UPDATED_TP_ID);
        assertThat(testTradePartner.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testTradePartner.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testTradePartner.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testTradePartner.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testTradePartner.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTradePartner.getTradePartnerName()).isEqualTo(DEFAULT_TRADE_PARTNER_NAME);
        assertThat(testTradePartner.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testTradePartner.getTradepartnerGST()).isEqualTo(UPDATED_TRADEPARTNER_GST);
        assertThat(testTradePartner.getTradePartnerSector()).isEqualTo(DEFAULT_TRADE_PARTNER_SECTOR);
        assertThat(testTradePartner.getAcceptanceFromTradePartner()).isEqualTo(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
    }

    @Test
    void fullUpdateTradePartnerWithPatch() throws Exception {
        // Initialize the database
        tradePartnerRepository.save(tradePartner).block();

        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();

        // Update the tradePartner using partial update
        TradePartner partialUpdatedTradePartner = new TradePartner();
        partialUpdatedTradePartner.setId(tradePartner.getId());

        partialUpdatedTradePartner
            .tenantId(UPDATED_TENANT_ID)
            .tpId(UPDATED_TP_ID)
            .orgId(UPDATED_ORG_ID)
            .customerName(UPDATED_CUSTOMER_NAME)
            .orgName(UPDATED_ORG_NAME)
            .gstId(UPDATED_GST_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .location(UPDATED_LOCATION)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTradePartner.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTradePartner))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
        TradePartner testTradePartner = tradePartnerList.get(tradePartnerList.size() - 1);
        assertThat(testTradePartner.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTradePartner.getTpId()).isEqualTo(UPDATED_TP_ID);
        assertThat(testTradePartner.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testTradePartner.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testTradePartner.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testTradePartner.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testTradePartner.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testTradePartner.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testTradePartner.getLocation()).isEqualTo(UPDATED_LOCATION);
        assertThat(testTradePartner.getTradepartnerGST()).isEqualTo(UPDATED_TRADEPARTNER_GST);
        assertThat(testTradePartner.getTradePartnerSector()).isEqualTo(UPDATED_TRADE_PARTNER_SECTOR);
        assertThat(testTradePartner.getAcceptanceFromTradePartner()).isEqualTo(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
    }

    @Test
    void patchNonExistingTradePartner() throws Exception {
        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();
        tradePartner.setId(longCount.incrementAndGet());

        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, tradePartnerDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTradePartner() throws Exception {
        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();
        tradePartner.setId(longCount.incrementAndGet());

        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTradePartner() throws Exception {
        int databaseSizeBeforeUpdate = tradePartnerRepository.findAll().collectList().block().size();
        tradePartner.setId(longCount.incrementAndGet());

        // Create the TradePartner
        TradePartnerDTO tradePartnerDTO = tradePartnerMapper.toDto(tradePartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradePartnerDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TradePartner in the database
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTradePartner() {
        // Initialize the database
        tradePartnerRepository.save(tradePartner).block();

        int databaseSizeBeforeDelete = tradePartnerRepository.findAll().collectList().block().size();

        // Delete the tradePartner
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, tradePartner.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<TradePartner> tradePartnerList = tradePartnerRepository.findAll().collectList().block();
        assertThat(tradePartnerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
