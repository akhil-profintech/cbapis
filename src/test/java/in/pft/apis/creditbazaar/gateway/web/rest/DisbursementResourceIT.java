package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Disbursement;
import in.pft.apis.creditbazaar.gateway.repository.DisbursementRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.DisbursementService;
import in.pft.apis.creditbazaar.gateway.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.DisbursementMapper;
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
 * Integration tests for the {@link DisbursementResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DisbursementResourceIT {

    private static final Long DEFAULT_DBMT_ID = 1L;
    private static final Long UPDATED_DBMT_ID = 2L;

    private static final String DEFAULT_DISBURSEMENT_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_DISBURSEMENT_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DISBURSEMENT_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_DISBURSEMENT_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPTED_OFFER_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTED_OFFER_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PLACED_OFFER_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_PLACED_OFFER_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_OFF_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQ_OFF_ULID_ID = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OFFER_ACCEPTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFER_ACCEPTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DBMT_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_REQUEST_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_DBMT_REQ_AMOUNT = 1L;
    private static final Long UPDATED_DBMT_REQ_AMOUNT = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DBMT_REQUEST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DBMT_REQUEST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DBMT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_OFFER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_STATUS = "BBBBBBBBBB";

    private static final Long DEFAULT_DOC_ID = 1L;
    private static final Long UPDATED_DOC_ID = 2L;

    private static final String DEFAULT_DBMT_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_DATE_TIME = "BBBBBBBBBB";

    private static final Long DEFAULT_DBMT_AMOUNT = 1L;
    private static final Long UPDATED_DBMT_AMOUNT = 2L;

    private static final Long DEFAULT_FINANCE_REQUEST_ID = 1L;
    private static final Long UPDATED_FINANCE_REQUEST_ID = 2L;

    private static final String DEFAULT_AMOUNT_TO_BE_DISBURSED = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_TO_BE_DISBURSED = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESTINATION_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DESTINATION_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION_BY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_BY_DATE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/disbursements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DisbursementRepository disbursementRepository;

    @Mock
    private DisbursementRepository disbursementRepositoryMock;

    @Autowired
    private DisbursementMapper disbursementMapper;

    @Mock
    private DisbursementService disbursementServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Disbursement disbursement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disbursement createEntity(EntityManager em) {
        Disbursement disbursement = new Disbursement()
            .dbmtId(DEFAULT_DBMT_ID)
            .disbursementUlidId(DEFAULT_DISBURSEMENT_ULID_ID)
            .disbursementRefNo(DEFAULT_DISBURSEMENT_REF_NO)
            .acceptedOfferUlidId(DEFAULT_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(DEFAULT_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(DEFAULT_REQ_OFF_ULID_ID)
            .offerAcceptedDate(DEFAULT_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(DEFAULT_DBMT_REQUEST_ID)
            .dbmtReqAmount(DEFAULT_DBMT_REQ_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .dbmtRequestDate(DEFAULT_DBMT_REQUEST_DATE)
            .dbmtStatus(DEFAULT_DBMT_STATUS)
            .offerStatus(DEFAULT_OFFER_STATUS)
            .docId(DEFAULT_DOC_ID)
            .dbmtDateTime(DEFAULT_DBMT_DATE_TIME)
            .dbmtAmount(DEFAULT_DBMT_AMOUNT)
            .financeRequestId(DEFAULT_FINANCE_REQUEST_ID)
            .amountToBeDisbursed(DEFAULT_AMOUNT_TO_BE_DISBURSED)
            .destinationAccountName(DEFAULT_DESTINATION_ACCOUNT_NAME)
            .destinationAccountNumber(DEFAULT_DESTINATION_ACCOUNT_NUMBER)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .actionByDate(DEFAULT_ACTION_BY_DATE);
        return disbursement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Disbursement createUpdatedEntity(EntityManager em) {
        Disbursement disbursement = new Disbursement()
            .dbmtId(UPDATED_DBMT_ID)
            .disbursementUlidId(UPDATED_DISBURSEMENT_ULID_ID)
            .disbursementRefNo(UPDATED_DISBURSEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtReqAmount(UPDATED_DBMT_REQ_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .dbmtRequestDate(UPDATED_DBMT_REQUEST_DATE)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .offerStatus(UPDATED_OFFER_STATUS)
            .docId(UPDATED_DOC_ID)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .amountToBeDisbursed(UPDATED_AMOUNT_TO_BE_DISBURSED)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationAccountNumber(UPDATED_DESTINATION_ACCOUNT_NUMBER)
            .recordStatus(UPDATED_RECORD_STATUS)
            .actionByDate(UPDATED_ACTION_BY_DATE);
        return disbursement;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Disbursement.class).block();
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
        disbursement = createEntity(em);
    }

    @Test
    void createDisbursement() throws Exception {
        int databaseSizeBeforeCreate = disbursementRepository.findAll().collectList().block().size();
        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeCreate + 1);
        Disbursement testDisbursement = disbursementList.get(disbursementList.size() - 1);
        assertThat(testDisbursement.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testDisbursement.getDisbursementUlidId()).isEqualTo(DEFAULT_DISBURSEMENT_ULID_ID);
        assertThat(testDisbursement.getDisbursementRefNo()).isEqualTo(DEFAULT_DISBURSEMENT_REF_NO);
        assertThat(testDisbursement.getAcceptedOfferUlidId()).isEqualTo(DEFAULT_ACCEPTED_OFFER_ULID_ID);
        assertThat(testDisbursement.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testDisbursement.getReqOffUlidId()).isEqualTo(DEFAULT_REQ_OFF_ULID_ID);
        assertThat(testDisbursement.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
        assertThat(testDisbursement.getDbmtRequestId()).isEqualTo(DEFAULT_DBMT_REQUEST_ID);
        assertThat(testDisbursement.getDbmtReqAmount()).isEqualTo(DEFAULT_DBMT_REQ_AMOUNT);
        assertThat(testDisbursement.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testDisbursement.getDbmtRequestDate()).isEqualTo(DEFAULT_DBMT_REQUEST_DATE);
        assertThat(testDisbursement.getDbmtStatus()).isEqualTo(DEFAULT_DBMT_STATUS);
        assertThat(testDisbursement.getOfferStatus()).isEqualTo(DEFAULT_OFFER_STATUS);
        assertThat(testDisbursement.getDocId()).isEqualTo(DEFAULT_DOC_ID);
        assertThat(testDisbursement.getDbmtDateTime()).isEqualTo(DEFAULT_DBMT_DATE_TIME);
        assertThat(testDisbursement.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testDisbursement.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testDisbursement.getAmountToBeDisbursed()).isEqualTo(DEFAULT_AMOUNT_TO_BE_DISBURSED);
        assertThat(testDisbursement.getDestinationAccountName()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_NAME);
        assertThat(testDisbursement.getDestinationAccountNumber()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_NUMBER);
        assertThat(testDisbursement.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testDisbursement.getActionByDate()).isEqualTo(DEFAULT_ACTION_BY_DATE);
    }

    @Test
    void createDisbursementWithExistingId() throws Exception {
        // Create the Disbursement with an existing ID
        disbursement.setId(1L);
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        int databaseSizeBeforeCreate = disbursementRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkAcceptedOfferUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setAcceptedOfferUlidId(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPlacedOfferUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setPlacedOfferUlidId(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkReqOffUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setReqOffUlidId(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOfferAcceptedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setOfferAcceptedDate(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtRequestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setDbmtRequestId(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtReqAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setDbmtReqAmount(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setCurrency(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtRequestDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setDbmtRequestDate(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = disbursementRepository.findAll().collectList().block().size();
        // set the field null
        disbursement.setDbmtStatus(null);

        // Create the Disbursement, which fails.
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDisbursements() {
        // Initialize the database
        disbursementRepository.save(disbursement).block();

        // Get all the disbursementList
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
            .value(hasItem(disbursement.getId().intValue()))
            .jsonPath("$.[*].dbmtId")
            .value(hasItem(DEFAULT_DBMT_ID.intValue()))
            .jsonPath("$.[*].disbursementUlidId")
            .value(hasItem(DEFAULT_DISBURSEMENT_ULID_ID))
            .jsonPath("$.[*].disbursementRefNo")
            .value(hasItem(DEFAULT_DISBURSEMENT_REF_NO))
            .jsonPath("$.[*].acceptedOfferUlidId")
            .value(hasItem(DEFAULT_ACCEPTED_OFFER_ULID_ID))
            .jsonPath("$.[*].placedOfferUlidId")
            .value(hasItem(DEFAULT_PLACED_OFFER_ULID_ID))
            .jsonPath("$.[*].reqOffUlidId")
            .value(hasItem(DEFAULT_REQ_OFF_ULID_ID))
            .jsonPath("$.[*].offerAcceptedDate")
            .value(hasItem(DEFAULT_OFFER_ACCEPTED_DATE.toString()))
            .jsonPath("$.[*].dbmtRequestId")
            .value(hasItem(DEFAULT_DBMT_REQUEST_ID))
            .jsonPath("$.[*].dbmtReqAmount")
            .value(hasItem(DEFAULT_DBMT_REQ_AMOUNT.intValue()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].dbmtRequestDate")
            .value(hasItem(DEFAULT_DBMT_REQUEST_DATE.toString()))
            .jsonPath("$.[*].dbmtStatus")
            .value(hasItem(DEFAULT_DBMT_STATUS))
            .jsonPath("$.[*].offerStatus")
            .value(hasItem(DEFAULT_OFFER_STATUS))
            .jsonPath("$.[*].docId")
            .value(hasItem(DEFAULT_DOC_ID.intValue()))
            .jsonPath("$.[*].dbmtDateTime")
            .value(hasItem(DEFAULT_DBMT_DATE_TIME))
            .jsonPath("$.[*].dbmtAmount")
            .value(hasItem(DEFAULT_DBMT_AMOUNT.intValue()))
            .jsonPath("$.[*].financeRequestId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_ID.intValue()))
            .jsonPath("$.[*].amountToBeDisbursed")
            .value(hasItem(DEFAULT_AMOUNT_TO_BE_DISBURSED))
            .jsonPath("$.[*].destinationAccountName")
            .value(hasItem(DEFAULT_DESTINATION_ACCOUNT_NAME))
            .jsonPath("$.[*].destinationAccountNumber")
            .value(hasItem(DEFAULT_DESTINATION_ACCOUNT_NUMBER))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS))
            .jsonPath("$.[*].actionByDate")
            .value(hasItem(DEFAULT_ACTION_BY_DATE));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDisbursementsWithEagerRelationshipsIsEnabled() {
        when(disbursementServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(disbursementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDisbursementsWithEagerRelationshipsIsNotEnabled() {
        when(disbursementServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(disbursementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getDisbursement() {
        // Initialize the database
        disbursementRepository.save(disbursement).block();

        // Get the disbursement
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, disbursement.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(disbursement.getId().intValue()))
            .jsonPath("$.dbmtId")
            .value(is(DEFAULT_DBMT_ID.intValue()))
            .jsonPath("$.disbursementUlidId")
            .value(is(DEFAULT_DISBURSEMENT_ULID_ID))
            .jsonPath("$.disbursementRefNo")
            .value(is(DEFAULT_DISBURSEMENT_REF_NO))
            .jsonPath("$.acceptedOfferUlidId")
            .value(is(DEFAULT_ACCEPTED_OFFER_ULID_ID))
            .jsonPath("$.placedOfferUlidId")
            .value(is(DEFAULT_PLACED_OFFER_ULID_ID))
            .jsonPath("$.reqOffUlidId")
            .value(is(DEFAULT_REQ_OFF_ULID_ID))
            .jsonPath("$.offerAcceptedDate")
            .value(is(DEFAULT_OFFER_ACCEPTED_DATE.toString()))
            .jsonPath("$.dbmtRequestId")
            .value(is(DEFAULT_DBMT_REQUEST_ID))
            .jsonPath("$.dbmtReqAmount")
            .value(is(DEFAULT_DBMT_REQ_AMOUNT.intValue()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.dbmtRequestDate")
            .value(is(DEFAULT_DBMT_REQUEST_DATE.toString()))
            .jsonPath("$.dbmtStatus")
            .value(is(DEFAULT_DBMT_STATUS))
            .jsonPath("$.offerStatus")
            .value(is(DEFAULT_OFFER_STATUS))
            .jsonPath("$.docId")
            .value(is(DEFAULT_DOC_ID.intValue()))
            .jsonPath("$.dbmtDateTime")
            .value(is(DEFAULT_DBMT_DATE_TIME))
            .jsonPath("$.dbmtAmount")
            .value(is(DEFAULT_DBMT_AMOUNT.intValue()))
            .jsonPath("$.financeRequestId")
            .value(is(DEFAULT_FINANCE_REQUEST_ID.intValue()))
            .jsonPath("$.amountToBeDisbursed")
            .value(is(DEFAULT_AMOUNT_TO_BE_DISBURSED))
            .jsonPath("$.destinationAccountName")
            .value(is(DEFAULT_DESTINATION_ACCOUNT_NAME))
            .jsonPath("$.destinationAccountNumber")
            .value(is(DEFAULT_DESTINATION_ACCOUNT_NUMBER))
            .jsonPath("$.recordStatus")
            .value(is(DEFAULT_RECORD_STATUS))
            .jsonPath("$.actionByDate")
            .value(is(DEFAULT_ACTION_BY_DATE));
    }

    @Test
    void getNonExistingDisbursement() {
        // Get the disbursement
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDisbursement() throws Exception {
        // Initialize the database
        disbursementRepository.save(disbursement).block();

        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();

        // Update the disbursement
        Disbursement updatedDisbursement = disbursementRepository.findById(disbursement.getId()).block();
        updatedDisbursement
            .dbmtId(UPDATED_DBMT_ID)
            .disbursementUlidId(UPDATED_DISBURSEMENT_ULID_ID)
            .disbursementRefNo(UPDATED_DISBURSEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtReqAmount(UPDATED_DBMT_REQ_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .dbmtRequestDate(UPDATED_DBMT_REQUEST_DATE)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .offerStatus(UPDATED_OFFER_STATUS)
            .docId(UPDATED_DOC_ID)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .amountToBeDisbursed(UPDATED_AMOUNT_TO_BE_DISBURSED)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationAccountNumber(UPDATED_DESTINATION_ACCOUNT_NUMBER)
            .recordStatus(UPDATED_RECORD_STATUS)
            .actionByDate(UPDATED_ACTION_BY_DATE);
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(updatedDisbursement);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, disbursementDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
        Disbursement testDisbursement = disbursementList.get(disbursementList.size() - 1);
        assertThat(testDisbursement.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testDisbursement.getDisbursementUlidId()).isEqualTo(UPDATED_DISBURSEMENT_ULID_ID);
        assertThat(testDisbursement.getDisbursementRefNo()).isEqualTo(UPDATED_DISBURSEMENT_REF_NO);
        assertThat(testDisbursement.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testDisbursement.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testDisbursement.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testDisbursement.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testDisbursement.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testDisbursement.getDbmtReqAmount()).isEqualTo(UPDATED_DBMT_REQ_AMOUNT);
        assertThat(testDisbursement.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testDisbursement.getDbmtRequestDate()).isEqualTo(UPDATED_DBMT_REQUEST_DATE);
        assertThat(testDisbursement.getDbmtStatus()).isEqualTo(UPDATED_DBMT_STATUS);
        assertThat(testDisbursement.getOfferStatus()).isEqualTo(UPDATED_OFFER_STATUS);
        assertThat(testDisbursement.getDocId()).isEqualTo(UPDATED_DOC_ID);
        assertThat(testDisbursement.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testDisbursement.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testDisbursement.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testDisbursement.getAmountToBeDisbursed()).isEqualTo(UPDATED_AMOUNT_TO_BE_DISBURSED);
        assertThat(testDisbursement.getDestinationAccountName()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_NAME);
        assertThat(testDisbursement.getDestinationAccountNumber()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_NUMBER);
        assertThat(testDisbursement.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDisbursement.getActionByDate()).isEqualTo(UPDATED_ACTION_BY_DATE);
    }

    @Test
    void putNonExistingDisbursement() throws Exception {
        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();
        disbursement.setId(longCount.incrementAndGet());

        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, disbursementDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDisbursement() throws Exception {
        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();
        disbursement.setId(longCount.incrementAndGet());

        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDisbursement() throws Exception {
        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();
        disbursement.setId(longCount.incrementAndGet());

        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDisbursementWithPatch() throws Exception {
        // Initialize the database
        disbursementRepository.save(disbursement).block();

        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();

        // Update the disbursement using partial update
        Disbursement partialUpdatedDisbursement = new Disbursement();
        partialUpdatedDisbursement.setId(disbursement.getId());

        partialUpdatedDisbursement
            .dbmtId(UPDATED_DBMT_ID)
            .disbursementUlidId(UPDATED_DISBURSEMENT_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtRequestDate(UPDATED_DBMT_REQUEST_DATE)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .recordStatus(UPDATED_RECORD_STATUS)
            .actionByDate(UPDATED_ACTION_BY_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDisbursement.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDisbursement))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
        Disbursement testDisbursement = disbursementList.get(disbursementList.size() - 1);
        assertThat(testDisbursement.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testDisbursement.getDisbursementUlidId()).isEqualTo(UPDATED_DISBURSEMENT_ULID_ID);
        assertThat(testDisbursement.getDisbursementRefNo()).isEqualTo(DEFAULT_DISBURSEMENT_REF_NO);
        assertThat(testDisbursement.getAcceptedOfferUlidId()).isEqualTo(DEFAULT_ACCEPTED_OFFER_ULID_ID);
        assertThat(testDisbursement.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testDisbursement.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testDisbursement.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
        assertThat(testDisbursement.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testDisbursement.getDbmtReqAmount()).isEqualTo(DEFAULT_DBMT_REQ_AMOUNT);
        assertThat(testDisbursement.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testDisbursement.getDbmtRequestDate()).isEqualTo(UPDATED_DBMT_REQUEST_DATE);
        assertThat(testDisbursement.getDbmtStatus()).isEqualTo(DEFAULT_DBMT_STATUS);
        assertThat(testDisbursement.getOfferStatus()).isEqualTo(DEFAULT_OFFER_STATUS);
        assertThat(testDisbursement.getDocId()).isEqualTo(DEFAULT_DOC_ID);
        assertThat(testDisbursement.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testDisbursement.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testDisbursement.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testDisbursement.getAmountToBeDisbursed()).isEqualTo(DEFAULT_AMOUNT_TO_BE_DISBURSED);
        assertThat(testDisbursement.getDestinationAccountName()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_NAME);
        assertThat(testDisbursement.getDestinationAccountNumber()).isEqualTo(DEFAULT_DESTINATION_ACCOUNT_NUMBER);
        assertThat(testDisbursement.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDisbursement.getActionByDate()).isEqualTo(UPDATED_ACTION_BY_DATE);
    }

    @Test
    void fullUpdateDisbursementWithPatch() throws Exception {
        // Initialize the database
        disbursementRepository.save(disbursement).block();

        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();

        // Update the disbursement using partial update
        Disbursement partialUpdatedDisbursement = new Disbursement();
        partialUpdatedDisbursement.setId(disbursement.getId());

        partialUpdatedDisbursement
            .dbmtId(UPDATED_DBMT_ID)
            .disbursementUlidId(UPDATED_DISBURSEMENT_ULID_ID)
            .disbursementRefNo(UPDATED_DISBURSEMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtReqAmount(UPDATED_DBMT_REQ_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .dbmtRequestDate(UPDATED_DBMT_REQUEST_DATE)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .offerStatus(UPDATED_OFFER_STATUS)
            .docId(UPDATED_DOC_ID)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .amountToBeDisbursed(UPDATED_AMOUNT_TO_BE_DISBURSED)
            .destinationAccountName(UPDATED_DESTINATION_ACCOUNT_NAME)
            .destinationAccountNumber(UPDATED_DESTINATION_ACCOUNT_NUMBER)
            .recordStatus(UPDATED_RECORD_STATUS)
            .actionByDate(UPDATED_ACTION_BY_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDisbursement.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDisbursement))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
        Disbursement testDisbursement = disbursementList.get(disbursementList.size() - 1);
        assertThat(testDisbursement.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testDisbursement.getDisbursementUlidId()).isEqualTo(UPDATED_DISBURSEMENT_ULID_ID);
        assertThat(testDisbursement.getDisbursementRefNo()).isEqualTo(UPDATED_DISBURSEMENT_REF_NO);
        assertThat(testDisbursement.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testDisbursement.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testDisbursement.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testDisbursement.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testDisbursement.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testDisbursement.getDbmtReqAmount()).isEqualTo(UPDATED_DBMT_REQ_AMOUNT);
        assertThat(testDisbursement.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testDisbursement.getDbmtRequestDate()).isEqualTo(UPDATED_DBMT_REQUEST_DATE);
        assertThat(testDisbursement.getDbmtStatus()).isEqualTo(UPDATED_DBMT_STATUS);
        assertThat(testDisbursement.getOfferStatus()).isEqualTo(UPDATED_OFFER_STATUS);
        assertThat(testDisbursement.getDocId()).isEqualTo(UPDATED_DOC_ID);
        assertThat(testDisbursement.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testDisbursement.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testDisbursement.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testDisbursement.getAmountToBeDisbursed()).isEqualTo(UPDATED_AMOUNT_TO_BE_DISBURSED);
        assertThat(testDisbursement.getDestinationAccountName()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_NAME);
        assertThat(testDisbursement.getDestinationAccountNumber()).isEqualTo(UPDATED_DESTINATION_ACCOUNT_NUMBER);
        assertThat(testDisbursement.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testDisbursement.getActionByDate()).isEqualTo(UPDATED_ACTION_BY_DATE);
    }

    @Test
    void patchNonExistingDisbursement() throws Exception {
        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();
        disbursement.setId(longCount.incrementAndGet());

        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, disbursementDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDisbursement() throws Exception {
        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();
        disbursement.setId(longCount.incrementAndGet());

        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDisbursement() throws Exception {
        int databaseSizeBeforeUpdate = disbursementRepository.findAll().collectList().block().size();
        disbursement.setId(longCount.incrementAndGet());

        // Create the Disbursement
        DisbursementDTO disbursementDTO = disbursementMapper.toDto(disbursement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(disbursementDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Disbursement in the database
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDisbursement() {
        // Initialize the database
        disbursementRepository.save(disbursement).block();

        int databaseSizeBeforeDelete = disbursementRepository.findAll().collectList().block().size();

        // Delete the disbursement
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, disbursement.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Disbursement> disbursementList = disbursementRepository.findAll().collectList().block();
        assertThat(disbursementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
