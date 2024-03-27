package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Settlement;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.SettlementRepository;
import in.pft.apis.creditbazaar.gateway.service.SettlementService;
import in.pft.apis.creditbazaar.gateway.service.dto.SettlementDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.SettlementMapper;
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
 * Integration tests for the {@link SettlementResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class SettlementResourceIT {

    private static final Long DEFAULT_SETTLEMENT_ID = 1L;
    private static final Long UPDATED_SETTLEMENT_ID = 2L;

    private static final String DEFAULT_SETTLEMENT_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SETTLEMENT_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPTED_OFFER_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTED_OFFER_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PLACED_OFFER_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_PLACED_OFFER_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_OFF_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQ_OFF_ULID_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_DBMT_REQUEST_ID = 1L;
    private static final Long UPDATED_DBMT_REQUEST_ID = 2L;

    private static final Long DEFAULT_DBMT_ID = 1L;
    private static final Long UPDATED_DBMT_ID = 2L;

    private static final String DEFAULT_DBMT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DBMT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_DBMT_AMOUNT = 1L;
    private static final Long UPDATED_DBMT_AMOUNT = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/settlements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SettlementRepository settlementRepository;

    @Mock
    private SettlementRepository settlementRepositoryMock;

    @Autowired
    private SettlementMapper settlementMapper;

    @Mock
    private SettlementService settlementServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Settlement settlement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Settlement createEntity(EntityManager em) {
        Settlement settlement = new Settlement()
            .settlementId(DEFAULT_SETTLEMENT_ID)
            .settlementUlidId(DEFAULT_SETTLEMENT_ULID_ID)
            .settlementRefNo(DEFAULT_SETTLEMENT_REF_NO)
            .acceptedOfferUlidId(DEFAULT_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(DEFAULT_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(DEFAULT_REQ_OFF_ULID_ID)
            .dbmtRequestId(DEFAULT_DBMT_REQUEST_ID)
            .dbmtId(DEFAULT_DBMT_ID)
            .dbmtDate(DEFAULT_DBMT_DATE)
            .dbmtStatus(DEFAULT_DBMT_STATUS)
            .dbmtAmount(DEFAULT_DBMT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .recordStatus(DEFAULT_RECORD_STATUS);
        return settlement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Settlement createUpdatedEntity(EntityManager em) {
        Settlement settlement = new Settlement()
            .settlementId(UPDATED_SETTLEMENT_ID)
            .settlementUlidId(UPDATED_SETTLEMENT_ULID_ID)
            .settlementRefNo(UPDATED_SETTLEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtDate(UPDATED_DBMT_DATE)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .recordStatus(UPDATED_RECORD_STATUS);
        return settlement;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Settlement.class).block();
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
        settlement = createEntity(em);
    }

    @Test
    void createSettlement() throws Exception {
        int databaseSizeBeforeCreate = settlementRepository.findAll().collectList().block().size();
        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeCreate + 1);
        Settlement testSettlement = settlementList.get(settlementList.size() - 1);
        assertThat(testSettlement.getSettlementId()).isEqualTo(DEFAULT_SETTLEMENT_ID);
        assertThat(testSettlement.getSettlementUlidId()).isEqualTo(DEFAULT_SETTLEMENT_ULID_ID);
        assertThat(testSettlement.getSettlementRefNo()).isEqualTo(DEFAULT_SETTLEMENT_REF_NO);
        assertThat(testSettlement.getAcceptedOfferUlidId()).isEqualTo(DEFAULT_ACCEPTED_OFFER_ULID_ID);
        assertThat(testSettlement.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testSettlement.getReqOffUlidId()).isEqualTo(DEFAULT_REQ_OFF_ULID_ID);
        assertThat(testSettlement.getDbmtRequestId()).isEqualTo(DEFAULT_DBMT_REQUEST_ID);
        assertThat(testSettlement.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testSettlement.getDbmtDate()).isEqualTo(DEFAULT_DBMT_DATE);
        assertThat(testSettlement.getDbmtStatus()).isEqualTo(DEFAULT_DBMT_STATUS);
        assertThat(testSettlement.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testSettlement.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testSettlement.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
    }

    @Test
    void createSettlementWithExistingId() throws Exception {
        // Create the Settlement with an existing ID
        settlement.setId(1L);
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        int databaseSizeBeforeCreate = settlementRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkPlacedOfferUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setPlacedOfferUlidId(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkReqOffUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setReqOffUlidId(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtRequestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setDbmtRequestId(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setDbmtId(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setDbmtDate(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setDbmtStatus(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setDbmtAmount(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = settlementRepository.findAll().collectList().block().size();
        // set the field null
        settlement.setCurrency(null);

        // Create the Settlement, which fails.
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllSettlements() {
        // Initialize the database
        settlementRepository.save(settlement).block();

        // Get all the settlementList
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
            .value(hasItem(settlement.getId().intValue()))
            .jsonPath("$.[*].settlementId")
            .value(hasItem(DEFAULT_SETTLEMENT_ID.intValue()))
            .jsonPath("$.[*].settlementUlidId")
            .value(hasItem(DEFAULT_SETTLEMENT_ULID_ID))
            .jsonPath("$.[*].settlementRefNo")
            .value(hasItem(DEFAULT_SETTLEMENT_REF_NO))
            .jsonPath("$.[*].acceptedOfferUlidId")
            .value(hasItem(DEFAULT_ACCEPTED_OFFER_ULID_ID))
            .jsonPath("$.[*].placedOfferUlidId")
            .value(hasItem(DEFAULT_PLACED_OFFER_ULID_ID))
            .jsonPath("$.[*].reqOffUlidId")
            .value(hasItem(DEFAULT_REQ_OFF_ULID_ID))
            .jsonPath("$.[*].dbmtRequestId")
            .value(hasItem(DEFAULT_DBMT_REQUEST_ID.intValue()))
            .jsonPath("$.[*].dbmtId")
            .value(hasItem(DEFAULT_DBMT_ID.intValue()))
            .jsonPath("$.[*].dbmtDate")
            .value(hasItem(DEFAULT_DBMT_DATE))
            .jsonPath("$.[*].dbmtStatus")
            .value(hasItem(DEFAULT_DBMT_STATUS))
            .jsonPath("$.[*].dbmtAmount")
            .value(hasItem(DEFAULT_DBMT_AMOUNT.intValue()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSettlementsWithEagerRelationshipsIsEnabled() {
        when(settlementServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(settlementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSettlementsWithEagerRelationshipsIsNotEnabled() {
        when(settlementServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(settlementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getSettlement() {
        // Initialize the database
        settlementRepository.save(settlement).block();

        // Get the settlement
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, settlement.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(settlement.getId().intValue()))
            .jsonPath("$.settlementId")
            .value(is(DEFAULT_SETTLEMENT_ID.intValue()))
            .jsonPath("$.settlementUlidId")
            .value(is(DEFAULT_SETTLEMENT_ULID_ID))
            .jsonPath("$.settlementRefNo")
            .value(is(DEFAULT_SETTLEMENT_REF_NO))
            .jsonPath("$.acceptedOfferUlidId")
            .value(is(DEFAULT_ACCEPTED_OFFER_ULID_ID))
            .jsonPath("$.placedOfferUlidId")
            .value(is(DEFAULT_PLACED_OFFER_ULID_ID))
            .jsonPath("$.reqOffUlidId")
            .value(is(DEFAULT_REQ_OFF_ULID_ID))
            .jsonPath("$.dbmtRequestId")
            .value(is(DEFAULT_DBMT_REQUEST_ID.intValue()))
            .jsonPath("$.dbmtId")
            .value(is(DEFAULT_DBMT_ID.intValue()))
            .jsonPath("$.dbmtDate")
            .value(is(DEFAULT_DBMT_DATE))
            .jsonPath("$.dbmtStatus")
            .value(is(DEFAULT_DBMT_STATUS))
            .jsonPath("$.dbmtAmount")
            .value(is(DEFAULT_DBMT_AMOUNT.intValue()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.recordStatus")
            .value(is(DEFAULT_RECORD_STATUS));
    }

    @Test
    void getNonExistingSettlement() {
        // Get the settlement
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingSettlement() throws Exception {
        // Initialize the database
        settlementRepository.save(settlement).block();

        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();

        // Update the settlement
        Settlement updatedSettlement = settlementRepository.findById(settlement.getId()).block();
        updatedSettlement
            .settlementId(UPDATED_SETTLEMENT_ID)
            .settlementUlidId(UPDATED_SETTLEMENT_ULID_ID)
            .settlementRefNo(UPDATED_SETTLEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtDate(UPDATED_DBMT_DATE)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .recordStatus(UPDATED_RECORD_STATUS);
        SettlementDTO settlementDTO = settlementMapper.toDto(updatedSettlement);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, settlementDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
        Settlement testSettlement = settlementList.get(settlementList.size() - 1);
        assertThat(testSettlement.getSettlementId()).isEqualTo(UPDATED_SETTLEMENT_ID);
        assertThat(testSettlement.getSettlementUlidId()).isEqualTo(UPDATED_SETTLEMENT_ULID_ID);
        assertThat(testSettlement.getSettlementRefNo()).isEqualTo(UPDATED_SETTLEMENT_REF_NO);
        assertThat(testSettlement.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testSettlement.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testSettlement.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testSettlement.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testSettlement.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testSettlement.getDbmtDate()).isEqualTo(UPDATED_DBMT_DATE);
        assertThat(testSettlement.getDbmtStatus()).isEqualTo(UPDATED_DBMT_STATUS);
        assertThat(testSettlement.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testSettlement.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testSettlement.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
    }

    @Test
    void putNonExistingSettlement() throws Exception {
        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();
        settlement.setId(longCount.incrementAndGet());

        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, settlementDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchSettlement() throws Exception {
        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();
        settlement.setId(longCount.incrementAndGet());

        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamSettlement() throws Exception {
        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();
        settlement.setId(longCount.incrementAndGet());

        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateSettlementWithPatch() throws Exception {
        // Initialize the database
        settlementRepository.save(settlement).block();

        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();

        // Update the settlement using partial update
        Settlement partialUpdatedSettlement = new Settlement();
        partialUpdatedSettlement.setId(settlement.getId());

        partialUpdatedSettlement
            .settlementUlidId(UPDATED_SETTLEMENT_ULID_ID)
            .settlementRefNo(UPDATED_SETTLEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .currency(UPDATED_CURRENCY)
            .recordStatus(UPDATED_RECORD_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSettlement.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSettlement))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
        Settlement testSettlement = settlementList.get(settlementList.size() - 1);
        assertThat(testSettlement.getSettlementId()).isEqualTo(DEFAULT_SETTLEMENT_ID);
        assertThat(testSettlement.getSettlementUlidId()).isEqualTo(UPDATED_SETTLEMENT_ULID_ID);
        assertThat(testSettlement.getSettlementRefNo()).isEqualTo(UPDATED_SETTLEMENT_REF_NO);
        assertThat(testSettlement.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testSettlement.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testSettlement.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testSettlement.getDbmtRequestId()).isEqualTo(DEFAULT_DBMT_REQUEST_ID);
        assertThat(testSettlement.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testSettlement.getDbmtDate()).isEqualTo(DEFAULT_DBMT_DATE);
        assertThat(testSettlement.getDbmtStatus()).isEqualTo(DEFAULT_DBMT_STATUS);
        assertThat(testSettlement.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testSettlement.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testSettlement.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
    }

    @Test
    void fullUpdateSettlementWithPatch() throws Exception {
        // Initialize the database
        settlementRepository.save(settlement).block();

        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();

        // Update the settlement using partial update
        Settlement partialUpdatedSettlement = new Settlement();
        partialUpdatedSettlement.setId(settlement.getId());

        partialUpdatedSettlement
            .settlementId(UPDATED_SETTLEMENT_ID)
            .settlementUlidId(UPDATED_SETTLEMENT_ULID_ID)
            .settlementRefNo(UPDATED_SETTLEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtDate(UPDATED_DBMT_DATE)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .recordStatus(UPDATED_RECORD_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedSettlement.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedSettlement))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
        Settlement testSettlement = settlementList.get(settlementList.size() - 1);
        assertThat(testSettlement.getSettlementId()).isEqualTo(UPDATED_SETTLEMENT_ID);
        assertThat(testSettlement.getSettlementUlidId()).isEqualTo(UPDATED_SETTLEMENT_ULID_ID);
        assertThat(testSettlement.getSettlementRefNo()).isEqualTo(UPDATED_SETTLEMENT_REF_NO);
        assertThat(testSettlement.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testSettlement.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testSettlement.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testSettlement.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testSettlement.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testSettlement.getDbmtDate()).isEqualTo(UPDATED_DBMT_DATE);
        assertThat(testSettlement.getDbmtStatus()).isEqualTo(UPDATED_DBMT_STATUS);
        assertThat(testSettlement.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testSettlement.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testSettlement.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
    }

    @Test
    void patchNonExistingSettlement() throws Exception {
        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();
        settlement.setId(longCount.incrementAndGet());

        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, settlementDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchSettlement() throws Exception {
        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();
        settlement.setId(longCount.incrementAndGet());

        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamSettlement() throws Exception {
        int databaseSizeBeforeUpdate = settlementRepository.findAll().collectList().block().size();
        settlement.setId(longCount.incrementAndGet());

        // Create the Settlement
        SettlementDTO settlementDTO = settlementMapper.toDto(settlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(settlementDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Settlement in the database
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteSettlement() {
        // Initialize the database
        settlementRepository.save(settlement).block();

        int databaseSizeBeforeDelete = settlementRepository.findAll().collectList().block().size();

        // Delete the settlement
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, settlement.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Settlement> settlementList = settlementRepository.findAll().collectList().block();
        assertThat(settlementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
