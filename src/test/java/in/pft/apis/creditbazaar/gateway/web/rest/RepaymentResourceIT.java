package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Repayment;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.RepaymentRepository;
import in.pft.apis.creditbazaar.gateway.service.RepaymentService;
import in.pft.apis.creditbazaar.gateway.service.dto.RepaymentDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.RepaymentMapper;
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
 * Integration tests for the {@link RepaymentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class RepaymentResourceIT {

    private static final Long DEFAULT_REPAYMENT_ID = 1L;
    private static final Long UPDATED_REPAYMENT_ID = 2L;

    private static final String DEFAULT_REPAYMENT_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPAYMENT_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REPAYMENT_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_REPAYMENT_REF_NO = "BBBBBBBBBB";

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

    private static final String DEFAULT_DBMT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DBMT_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_DATE_TIME = "BBBBBBBBBB";

    private static final Long DEFAULT_DBMT_ID = 1L;
    private static final Long UPDATED_DBMT_ID = 2L;

    private static final Long DEFAULT_DBMT_AMOUNT = 1L;
    private static final Long UPDATED_DBMT_AMOUNT = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_REPAYMENT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_REPAYMENT_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REPAYMENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REPAYMENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_REPAYMENT_AMOUNT = 1L;
    private static final Long UPDATED_REPAYMENT_AMOUNT = 2L;

    private static final Long DEFAULT_FINANCE_REQUEST_ID = 1L;
    private static final Long UPDATED_FINANCE_REQUEST_ID = 2L;

    private static final LocalDate DEFAULT_REPAYMENT_DUE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REPAYMENT_DUE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TOTAL_REPAYMENT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_REPAYMENT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT_REPAYED = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_REPAYED = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT_TO_BE_PAID = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_TO_BE_PAID = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IFSC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/repayments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RepaymentRepository repaymentRepository;

    @Mock
    private RepaymentRepository repaymentRepositoryMock;

    @Autowired
    private RepaymentMapper repaymentMapper;

    @Mock
    private RepaymentService repaymentServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Repayment repayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Repayment createEntity(EntityManager em) {
        Repayment repayment = new Repayment()
            .repaymentId(DEFAULT_REPAYMENT_ID)
            .repaymentUlidId(DEFAULT_REPAYMENT_ULID_ID)
            .repaymentRefNo(DEFAULT_REPAYMENT_REF_NO)
            .acceptedOfferUlidId(DEFAULT_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(DEFAULT_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(DEFAULT_REQ_OFF_ULID_ID)
            .offerAcceptedDate(DEFAULT_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(DEFAULT_DBMT_REQUEST_ID)
            .dbmtStatus(DEFAULT_DBMT_STATUS)
            .dbmtDateTime(DEFAULT_DBMT_DATE_TIME)
            .dbmtId(DEFAULT_DBMT_ID)
            .dbmtAmount(DEFAULT_DBMT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .repaymentStatus(DEFAULT_REPAYMENT_STATUS)
            .repaymentDate(DEFAULT_REPAYMENT_DATE)
            .repaymentAmount(DEFAULT_REPAYMENT_AMOUNT)
            .financeRequestId(DEFAULT_FINANCE_REQUEST_ID)
            .repaymentDueDate(DEFAULT_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(DEFAULT_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(DEFAULT_AMOUNT_REPAYED)
            .amountToBePaid(DEFAULT_AMOUNT_TO_BE_PAID)
            .sourceAccountName(DEFAULT_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(DEFAULT_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(DEFAULT_IFSC_CODE)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .referenceNumber(DEFAULT_REFERENCE_NUMBER);
        return repayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Repayment createUpdatedEntity(EntityManager em) {
        Repayment repayment = new Repayment()
            .repaymentId(UPDATED_REPAYMENT_ID)
            .repaymentUlidId(UPDATED_REPAYMENT_ULID_ID)
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED)
            .amountToBePaid(UPDATED_AMOUNT_TO_BE_PAID)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(UPDATED_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .recordStatus(UPDATED_RECORD_STATUS)
            .referenceNumber(UPDATED_REFERENCE_NUMBER);
        return repayment;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Repayment.class).block();
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
        repayment = createEntity(em);
    }

    @Test
    void createRepayment() throws Exception {
        int databaseSizeBeforeCreate = repaymentRepository.findAll().collectList().block().size();
        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeCreate + 1);
        Repayment testRepayment = repaymentList.get(repaymentList.size() - 1);
        assertThat(testRepayment.getRepaymentId()).isEqualTo(DEFAULT_REPAYMENT_ID);
        assertThat(testRepayment.getRepaymentUlidId()).isEqualTo(DEFAULT_REPAYMENT_ULID_ID);
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(DEFAULT_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferUlidId()).isEqualTo(DEFAULT_ACCEPTED_OFFER_ULID_ID);
        assertThat(testRepayment.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testRepayment.getReqOffUlidId()).isEqualTo(DEFAULT_REQ_OFF_ULID_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(DEFAULT_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtStatus()).isEqualTo(DEFAULT_DBMT_STATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(DEFAULT_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(DEFAULT_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(DEFAULT_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(DEFAULT_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(DEFAULT_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(DEFAULT_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(DEFAULT_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(DEFAULT_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testRepayment.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testRepayment.getReferenceNumber()).isEqualTo(DEFAULT_REFERENCE_NUMBER);
    }

    @Test
    void createRepaymentWithExistingId() throws Exception {
        // Create the Repayment with an existing ID
        repayment.setId(1L);
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        int databaseSizeBeforeCreate = repaymentRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkAcceptedOfferUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setAcceptedOfferUlidId(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPlacedOfferUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setPlacedOfferUlidId(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkReqOffUlidIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setReqOffUlidId(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOfferAcceptedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setOfferAcceptedDate(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtRequestIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setDbmtRequestId(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setDbmtStatus(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setDbmtDateTime(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setDbmtId(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDbmtAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setDbmtAmount(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setCurrency(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRepaymentStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setRepaymentStatus(null);

        // Create the Repayment, which fails.
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRepayments() {
        // Initialize the database
        repaymentRepository.save(repayment).block();

        // Get all the repaymentList
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
            .value(hasItem(repayment.getId().intValue()))
            .jsonPath("$.[*].repaymentId")
            .value(hasItem(DEFAULT_REPAYMENT_ID.intValue()))
            .jsonPath("$.[*].repaymentUlidId")
            .value(hasItem(DEFAULT_REPAYMENT_ULID_ID))
            .jsonPath("$.[*].repaymentRefNo")
            .value(hasItem(DEFAULT_REPAYMENT_REF_NO))
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
            .jsonPath("$.[*].dbmtStatus")
            .value(hasItem(DEFAULT_DBMT_STATUS))
            .jsonPath("$.[*].dbmtDateTime")
            .value(hasItem(DEFAULT_DBMT_DATE_TIME))
            .jsonPath("$.[*].dbmtId")
            .value(hasItem(DEFAULT_DBMT_ID.intValue()))
            .jsonPath("$.[*].dbmtAmount")
            .value(hasItem(DEFAULT_DBMT_AMOUNT.intValue()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].repaymentStatus")
            .value(hasItem(DEFAULT_REPAYMENT_STATUS))
            .jsonPath("$.[*].repaymentDate")
            .value(hasItem(DEFAULT_REPAYMENT_DATE.toString()))
            .jsonPath("$.[*].repaymentAmount")
            .value(hasItem(DEFAULT_REPAYMENT_AMOUNT.intValue()))
            .jsonPath("$.[*].financeRequestId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_ID.intValue()))
            .jsonPath("$.[*].repaymentDueDate")
            .value(hasItem(DEFAULT_REPAYMENT_DUE_DATE.toString()))
            .jsonPath("$.[*].totalRepaymentAmount")
            .value(hasItem(DEFAULT_TOTAL_REPAYMENT_AMOUNT))
            .jsonPath("$.[*].amountRepayed")
            .value(hasItem(DEFAULT_AMOUNT_REPAYED))
            .jsonPath("$.[*].amountToBePaid")
            .value(hasItem(DEFAULT_AMOUNT_TO_BE_PAID))
            .jsonPath("$.[*].sourceAccountName")
            .value(hasItem(DEFAULT_SOURCE_ACCOUNT_NAME))
            .jsonPath("$.[*].sourceAccountNumber")
            .value(hasItem(DEFAULT_SOURCE_ACCOUNT_NUMBER))
            .jsonPath("$.[*].ifscCode")
            .value(hasItem(DEFAULT_IFSC_CODE))
            .jsonPath("$.[*].recordStatus")
            .value(hasItem(DEFAULT_RECORD_STATUS))
            .jsonPath("$.[*].referenceNumber")
            .value(hasItem(DEFAULT_REFERENCE_NUMBER));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRepaymentsWithEagerRelationshipsIsEnabled() {
        when(repaymentServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(repaymentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRepaymentsWithEagerRelationshipsIsNotEnabled() {
        when(repaymentServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(repaymentRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getRepayment() {
        // Initialize the database
        repaymentRepository.save(repayment).block();

        // Get the repayment
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, repayment.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(repayment.getId().intValue()))
            .jsonPath("$.repaymentId")
            .value(is(DEFAULT_REPAYMENT_ID.intValue()))
            .jsonPath("$.repaymentUlidId")
            .value(is(DEFAULT_REPAYMENT_ULID_ID))
            .jsonPath("$.repaymentRefNo")
            .value(is(DEFAULT_REPAYMENT_REF_NO))
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
            .jsonPath("$.dbmtStatus")
            .value(is(DEFAULT_DBMT_STATUS))
            .jsonPath("$.dbmtDateTime")
            .value(is(DEFAULT_DBMT_DATE_TIME))
            .jsonPath("$.dbmtId")
            .value(is(DEFAULT_DBMT_ID.intValue()))
            .jsonPath("$.dbmtAmount")
            .value(is(DEFAULT_DBMT_AMOUNT.intValue()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.repaymentStatus")
            .value(is(DEFAULT_REPAYMENT_STATUS))
            .jsonPath("$.repaymentDate")
            .value(is(DEFAULT_REPAYMENT_DATE.toString()))
            .jsonPath("$.repaymentAmount")
            .value(is(DEFAULT_REPAYMENT_AMOUNT.intValue()))
            .jsonPath("$.financeRequestId")
            .value(is(DEFAULT_FINANCE_REQUEST_ID.intValue()))
            .jsonPath("$.repaymentDueDate")
            .value(is(DEFAULT_REPAYMENT_DUE_DATE.toString()))
            .jsonPath("$.totalRepaymentAmount")
            .value(is(DEFAULT_TOTAL_REPAYMENT_AMOUNT))
            .jsonPath("$.amountRepayed")
            .value(is(DEFAULT_AMOUNT_REPAYED))
            .jsonPath("$.amountToBePaid")
            .value(is(DEFAULT_AMOUNT_TO_BE_PAID))
            .jsonPath("$.sourceAccountName")
            .value(is(DEFAULT_SOURCE_ACCOUNT_NAME))
            .jsonPath("$.sourceAccountNumber")
            .value(is(DEFAULT_SOURCE_ACCOUNT_NUMBER))
            .jsonPath("$.ifscCode")
            .value(is(DEFAULT_IFSC_CODE))
            .jsonPath("$.recordStatus")
            .value(is(DEFAULT_RECORD_STATUS))
            .jsonPath("$.referenceNumber")
            .value(is(DEFAULT_REFERENCE_NUMBER));
    }

    @Test
    void getNonExistingRepayment() {
        // Get the repayment
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingRepayment() throws Exception {
        // Initialize the database
        repaymentRepository.save(repayment).block();

        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();

        // Update the repayment
        Repayment updatedRepayment = repaymentRepository.findById(repayment.getId()).block();
        updatedRepayment
            .repaymentId(UPDATED_REPAYMENT_ID)
            .repaymentUlidId(UPDATED_REPAYMENT_ULID_ID)
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED)
            .amountToBePaid(UPDATED_AMOUNT_TO_BE_PAID)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(UPDATED_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .recordStatus(UPDATED_RECORD_STATUS)
            .referenceNumber(UPDATED_REFERENCE_NUMBER);
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(updatedRepayment);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, repaymentDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
        Repayment testRepayment = repaymentList.get(repaymentList.size() - 1);
        assertThat(testRepayment.getRepaymentId()).isEqualTo(UPDATED_REPAYMENT_ID);
        assertThat(testRepayment.getRepaymentUlidId()).isEqualTo(UPDATED_REPAYMENT_ULID_ID);
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(UPDATED_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testRepayment.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testRepayment.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtStatus()).isEqualTo(UPDATED_DBMT_STATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(UPDATED_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(UPDATED_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(UPDATED_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(UPDATED_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(UPDATED_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(UPDATED_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(UPDATED_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testRepayment.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testRepayment.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
    }

    @Test
    void putNonExistingRepayment() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();
        repayment.setId(longCount.incrementAndGet());

        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, repaymentDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRepayment() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();
        repayment.setId(longCount.incrementAndGet());

        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRepayment() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();
        repayment.setId(longCount.incrementAndGet());

        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRepaymentWithPatch() throws Exception {
        // Initialize the database
        repaymentRepository.save(repayment).block();

        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();

        // Update the repayment using partial update
        Repayment partialUpdatedRepayment = new Repayment();
        partialUpdatedRepayment.setId(repayment.getId());

        partialUpdatedRepayment
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRepayment.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRepayment))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
        Repayment testRepayment = repaymentList.get(repaymentList.size() - 1);
        assertThat(testRepayment.getRepaymentId()).isEqualTo(DEFAULT_REPAYMENT_ID);
        assertThat(testRepayment.getRepaymentUlidId()).isEqualTo(DEFAULT_REPAYMENT_ULID_ID);
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(UPDATED_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testRepayment.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testRepayment.getReqOffUlidId()).isEqualTo(DEFAULT_REQ_OFF_ULID_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtStatus()).isEqualTo(DEFAULT_DBMT_STATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(UPDATED_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(UPDATED_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(DEFAULT_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(DEFAULT_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(UPDATED_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(UPDATED_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(DEFAULT_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testRepayment.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testRepayment.getReferenceNumber()).isEqualTo(DEFAULT_REFERENCE_NUMBER);
    }

    @Test
    void fullUpdateRepaymentWithPatch() throws Exception {
        // Initialize the database
        repaymentRepository.save(repayment).block();

        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();

        // Update the repayment using partial update
        Repayment partialUpdatedRepayment = new Repayment();
        partialUpdatedRepayment.setId(repayment.getId());

        partialUpdatedRepayment
            .repaymentId(UPDATED_REPAYMENT_ID)
            .repaymentUlidId(UPDATED_REPAYMENT_ULID_ID)
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferUlidId(UPDATED_ACCEPTED_OFFER_ULID_ID)
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtStatus(UPDATED_DBMT_STATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED)
            .amountToBePaid(UPDATED_AMOUNT_TO_BE_PAID)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(UPDATED_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .recordStatus(UPDATED_RECORD_STATUS)
            .referenceNumber(UPDATED_REFERENCE_NUMBER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRepayment.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRepayment))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
        Repayment testRepayment = repaymentList.get(repaymentList.size() - 1);
        assertThat(testRepayment.getRepaymentId()).isEqualTo(UPDATED_REPAYMENT_ID);
        assertThat(testRepayment.getRepaymentUlidId()).isEqualTo(UPDATED_REPAYMENT_ULID_ID);
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(UPDATED_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferUlidId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ULID_ID);
        assertThat(testRepayment.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testRepayment.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtStatus()).isEqualTo(UPDATED_DBMT_STATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(UPDATED_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(UPDATED_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(UPDATED_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(UPDATED_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(UPDATED_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(UPDATED_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(UPDATED_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testRepayment.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testRepayment.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
    }

    @Test
    void patchNonExistingRepayment() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();
        repayment.setId(longCount.incrementAndGet());

        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, repaymentDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRepayment() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();
        repayment.setId(longCount.incrementAndGet());

        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRepayment() throws Exception {
        int databaseSizeBeforeUpdate = repaymentRepository.findAll().collectList().block().size();
        repayment.setId(longCount.incrementAndGet());

        // Create the Repayment
        RepaymentDTO repaymentDTO = repaymentMapper.toDto(repayment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(repaymentDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Repayment in the database
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRepayment() {
        // Initialize the database
        repaymentRepository.save(repayment).block();

        int databaseSizeBeforeDelete = repaymentRepository.findAll().collectList().block().size();

        // Delete the repayment
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, repayment.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Repayment> repaymentList = repaymentRepository.findAll().collectList().block();
        assertThat(repaymentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
