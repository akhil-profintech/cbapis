package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.RepaymentRepository;
import in.pft.apis.creditbazaar.service.RepaymentService;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import in.pft.apis.creditbazaar.service.mapper.RepaymentMapper;
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

    private static final String DEFAULT_REPAYMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPAYMENT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REPAYMENT_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_REPAYMENT_REF_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_ACCEPTED_OFFER_ID = 1L;
    private static final Long UPDATED_ACCEPTED_OFFER_ID = 2L;

    private static final Long DEFAULT_OFFER_ID = 1L;
    private static final Long UPDATED_OFFER_ID = 2L;

    private static final LocalDate DEFAULT_OFFER_ACCEPTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFER_ACCEPTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DBMT_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DBMTSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_DBMTSTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DBMT_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_DBMT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DBMT_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_FT_TRNX_DETAILS_ID = "AAAAAAAAAA";
    private static final String UPDATED_FT_TRNX_DETAILS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION_TRNX_DETAILS_ID = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_TRNX_DETAILS_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_DOC_ID = 1L;
    private static final Long UPDATED_DOC_ID = 2L;

    private static final String DEFAULT_FINANCE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_REQUEST_ID = "BBBBBBBBBB";

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

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

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
            .repaymentRefNo(DEFAULT_REPAYMENT_REF_NO)
            .acceptedOfferId(DEFAULT_ACCEPTED_OFFER_ID)
            .offerId(DEFAULT_OFFER_ID)
            .offerAcceptedDate(DEFAULT_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(DEFAULT_DBMT_REQUEST_ID)
            .dbmtstatus(DEFAULT_DBMTSTATUS)
            .dbmtDateTime(DEFAULT_DBMT_DATE_TIME)
            .dbmtId(DEFAULT_DBMT_ID)
            .dbmtAmount(DEFAULT_DBMT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .repaymentStatus(DEFAULT_REPAYMENT_STATUS)
            .repaymentDate(DEFAULT_REPAYMENT_DATE)
            .repaymentAmount(DEFAULT_REPAYMENT_AMOUNT)
            .ftTrnxDetailsId(DEFAULT_FT_TRNX_DETAILS_ID)
            .collectionTrnxDetailsId(DEFAULT_COLLECTION_TRNX_DETAILS_ID)
            .docId(DEFAULT_DOC_ID)
            .financeRequestId(DEFAULT_FINANCE_REQUEST_ID)
            .repaymentDueDate(DEFAULT_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(DEFAULT_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(DEFAULT_AMOUNT_REPAYED)
            .amountToBePaid(DEFAULT_AMOUNT_TO_BE_PAID)
            .sourceAccountName(DEFAULT_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(DEFAULT_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(DEFAULT_IFSC_CODE)
            .status(DEFAULT_STATUS)
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
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferId(UPDATED_ACCEPTED_OFFER_ID)
            .offerId(UPDATED_OFFER_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtstatus(UPDATED_DBMTSTATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .collectionTrnxDetailsId(UPDATED_COLLECTION_TRNX_DETAILS_ID)
            .docId(UPDATED_DOC_ID)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED)
            .amountToBePaid(UPDATED_AMOUNT_TO_BE_PAID)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(UPDATED_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .status(UPDATED_STATUS)
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
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(DEFAULT_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferId()).isEqualTo(DEFAULT_ACCEPTED_OFFER_ID);
        assertThat(testRepayment.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(DEFAULT_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtstatus()).isEqualTo(DEFAULT_DBMTSTATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(DEFAULT_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(DEFAULT_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(DEFAULT_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(DEFAULT_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(DEFAULT_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFtTrnxDetailsId()).isEqualTo(DEFAULT_FT_TRNX_DETAILS_ID);
        assertThat(testRepayment.getCollectionTrnxDetailsId()).isEqualTo(DEFAULT_COLLECTION_TRNX_DETAILS_ID);
        assertThat(testRepayment.getDocId()).isEqualTo(DEFAULT_DOC_ID);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(DEFAULT_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(DEFAULT_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(DEFAULT_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(DEFAULT_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testRepayment.getStatus()).isEqualTo(DEFAULT_STATUS);
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
    void checkAcceptedOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setAcceptedOfferId(null);

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
    void checkOfferIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setOfferId(null);

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
    void checkDbmtstatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = repaymentRepository.findAll().collectList().block().size();
        // set the field null
        repayment.setDbmtstatus(null);

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
            .value(hasItem(DEFAULT_REPAYMENT_ID))
            .jsonPath("$.[*].repaymentRefNo")
            .value(hasItem(DEFAULT_REPAYMENT_REF_NO))
            .jsonPath("$.[*].acceptedOfferId")
            .value(hasItem(DEFAULT_ACCEPTED_OFFER_ID.intValue()))
            .jsonPath("$.[*].offerId")
            .value(hasItem(DEFAULT_OFFER_ID.intValue()))
            .jsonPath("$.[*].offerAcceptedDate")
            .value(hasItem(DEFAULT_OFFER_ACCEPTED_DATE.toString()))
            .jsonPath("$.[*].dbmtRequestId")
            .value(hasItem(DEFAULT_DBMT_REQUEST_ID))
            .jsonPath("$.[*].dbmtstatus")
            .value(hasItem(DEFAULT_DBMTSTATUS))
            .jsonPath("$.[*].dbmtDateTime")
            .value(hasItem(DEFAULT_DBMT_DATE_TIME))
            .jsonPath("$.[*].dbmtId")
            .value(hasItem(DEFAULT_DBMT_ID))
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
            .jsonPath("$.[*].ftTrnxDetailsId")
            .value(hasItem(DEFAULT_FT_TRNX_DETAILS_ID))
            .jsonPath("$.[*].collectionTrnxDetailsId")
            .value(hasItem(DEFAULT_COLLECTION_TRNX_DETAILS_ID))
            .jsonPath("$.[*].docId")
            .value(hasItem(DEFAULT_DOC_ID.intValue()))
            .jsonPath("$.[*].financeRequestId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_ID))
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
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
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
            .value(is(DEFAULT_REPAYMENT_ID))
            .jsonPath("$.repaymentRefNo")
            .value(is(DEFAULT_REPAYMENT_REF_NO))
            .jsonPath("$.acceptedOfferId")
            .value(is(DEFAULT_ACCEPTED_OFFER_ID.intValue()))
            .jsonPath("$.offerId")
            .value(is(DEFAULT_OFFER_ID.intValue()))
            .jsonPath("$.offerAcceptedDate")
            .value(is(DEFAULT_OFFER_ACCEPTED_DATE.toString()))
            .jsonPath("$.dbmtRequestId")
            .value(is(DEFAULT_DBMT_REQUEST_ID))
            .jsonPath("$.dbmtstatus")
            .value(is(DEFAULT_DBMTSTATUS))
            .jsonPath("$.dbmtDateTime")
            .value(is(DEFAULT_DBMT_DATE_TIME))
            .jsonPath("$.dbmtId")
            .value(is(DEFAULT_DBMT_ID))
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
            .jsonPath("$.ftTrnxDetailsId")
            .value(is(DEFAULT_FT_TRNX_DETAILS_ID))
            .jsonPath("$.collectionTrnxDetailsId")
            .value(is(DEFAULT_COLLECTION_TRNX_DETAILS_ID))
            .jsonPath("$.docId")
            .value(is(DEFAULT_DOC_ID.intValue()))
            .jsonPath("$.financeRequestId")
            .value(is(DEFAULT_FINANCE_REQUEST_ID))
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
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
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
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferId(UPDATED_ACCEPTED_OFFER_ID)
            .offerId(UPDATED_OFFER_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtstatus(UPDATED_DBMTSTATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .collectionTrnxDetailsId(UPDATED_COLLECTION_TRNX_DETAILS_ID)
            .docId(UPDATED_DOC_ID)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED)
            .amountToBePaid(UPDATED_AMOUNT_TO_BE_PAID)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(UPDATED_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .status(UPDATED_STATUS)
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
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(UPDATED_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ID);
        assertThat(testRepayment.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtstatus()).isEqualTo(UPDATED_DBMTSTATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(UPDATED_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(UPDATED_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(UPDATED_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFtTrnxDetailsId()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ID);
        assertThat(testRepayment.getCollectionTrnxDetailsId()).isEqualTo(UPDATED_COLLECTION_TRNX_DETAILS_ID);
        assertThat(testRepayment.getDocId()).isEqualTo(UPDATED_DOC_ID);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(UPDATED_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(UPDATED_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(UPDATED_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(UPDATED_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testRepayment.getStatus()).isEqualTo(UPDATED_STATUS);
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
            .acceptedOfferId(UPDATED_ACCEPTED_OFFER_ID)
            .offerId(UPDATED_OFFER_ID)
            .dbmtstatus(UPDATED_DBMTSTATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .docId(UPDATED_DOC_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
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
        assertThat(testRepayment.getRepaymentId()).isEqualTo(DEFAULT_REPAYMENT_ID);
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(DEFAULT_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ID);
        assertThat(testRepayment.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(DEFAULT_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtstatus()).isEqualTo(UPDATED_DBMTSTATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(DEFAULT_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(DEFAULT_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(UPDATED_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(UPDATED_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFtTrnxDetailsId()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ID);
        assertThat(testRepayment.getCollectionTrnxDetailsId()).isEqualTo(DEFAULT_COLLECTION_TRNX_DETAILS_ID);
        assertThat(testRepayment.getDocId()).isEqualTo(UPDATED_DOC_ID);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(UPDATED_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(UPDATED_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(DEFAULT_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(DEFAULT_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(DEFAULT_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testRepayment.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRepayment.getReferenceNumber()).isEqualTo(UPDATED_REFERENCE_NUMBER);
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
            .repaymentRefNo(UPDATED_REPAYMENT_REF_NO)
            .acceptedOfferId(UPDATED_ACCEPTED_OFFER_ID)
            .offerId(UPDATED_OFFER_ID)
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE)
            .dbmtRequestId(UPDATED_DBMT_REQUEST_ID)
            .dbmtstatus(UPDATED_DBMTSTATUS)
            .dbmtDateTime(UPDATED_DBMT_DATE_TIME)
            .dbmtId(UPDATED_DBMT_ID)
            .dbmtAmount(UPDATED_DBMT_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .repaymentStatus(UPDATED_REPAYMENT_STATUS)
            .repaymentDate(UPDATED_REPAYMENT_DATE)
            .repaymentAmount(UPDATED_REPAYMENT_AMOUNT)
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .collectionTrnxDetailsId(UPDATED_COLLECTION_TRNX_DETAILS_ID)
            .docId(UPDATED_DOC_ID)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .repaymentDueDate(UPDATED_REPAYMENT_DUE_DATE)
            .totalRepaymentAmount(UPDATED_TOTAL_REPAYMENT_AMOUNT)
            .amountRepayed(UPDATED_AMOUNT_REPAYED)
            .amountToBePaid(UPDATED_AMOUNT_TO_BE_PAID)
            .sourceAccountName(UPDATED_SOURCE_ACCOUNT_NAME)
            .sourceAccountNumber(UPDATED_SOURCE_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .status(UPDATED_STATUS)
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
        assertThat(testRepayment.getRepaymentRefNo()).isEqualTo(UPDATED_REPAYMENT_REF_NO);
        assertThat(testRepayment.getAcceptedOfferId()).isEqualTo(UPDATED_ACCEPTED_OFFER_ID);
        assertThat(testRepayment.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testRepayment.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
        assertThat(testRepayment.getDbmtRequestId()).isEqualTo(UPDATED_DBMT_REQUEST_ID);
        assertThat(testRepayment.getDbmtstatus()).isEqualTo(UPDATED_DBMTSTATUS);
        assertThat(testRepayment.getDbmtDateTime()).isEqualTo(UPDATED_DBMT_DATE_TIME);
        assertThat(testRepayment.getDbmtId()).isEqualTo(UPDATED_DBMT_ID);
        assertThat(testRepayment.getDbmtAmount()).isEqualTo(UPDATED_DBMT_AMOUNT);
        assertThat(testRepayment.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testRepayment.getRepaymentStatus()).isEqualTo(UPDATED_REPAYMENT_STATUS);
        assertThat(testRepayment.getRepaymentDate()).isEqualTo(UPDATED_REPAYMENT_DATE);
        assertThat(testRepayment.getRepaymentAmount()).isEqualTo(UPDATED_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getFtTrnxDetailsId()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ID);
        assertThat(testRepayment.getCollectionTrnxDetailsId()).isEqualTo(UPDATED_COLLECTION_TRNX_DETAILS_ID);
        assertThat(testRepayment.getDocId()).isEqualTo(UPDATED_DOC_ID);
        assertThat(testRepayment.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testRepayment.getRepaymentDueDate()).isEqualTo(UPDATED_REPAYMENT_DUE_DATE);
        assertThat(testRepayment.getTotalRepaymentAmount()).isEqualTo(UPDATED_TOTAL_REPAYMENT_AMOUNT);
        assertThat(testRepayment.getAmountRepayed()).isEqualTo(UPDATED_AMOUNT_REPAYED);
        assertThat(testRepayment.getAmountToBePaid()).isEqualTo(UPDATED_AMOUNT_TO_BE_PAID);
        assertThat(testRepayment.getSourceAccountName()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NAME);
        assertThat(testRepayment.getSourceAccountNumber()).isEqualTo(UPDATED_SOURCE_ACCOUNT_NUMBER);
        assertThat(testRepayment.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testRepayment.getStatus()).isEqualTo(UPDATED_STATUS);
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
