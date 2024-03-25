package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.EscrowTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.EscrowTransactionDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.EscrowTransactionDetailsMapper;
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
 * Integration tests for the {@link EscrowTransactionDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class EscrowTransactionDetailsResourceIT {

    private static final Long DEFAULT_ESCROW_TRNX_DETAILS_ID = 1L;
    private static final Long UPDATED_ESCROW_TRNX_DETAILS_ID = 2L;

    private static final String DEFAULT_ESCROW_TRNX_DETAILS_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_ESCROW_TRNX_DETAILS_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_AMT = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_AMT = "BBBBBBBBBB";

    private static final String DEFAULT_BATCH_AMT_CCD = "AAAAAAAAAA";
    private static final String UPDATED_BATCH_AMT_CCD = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_VA_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VA_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_UTR_NO = "AAAAAAAAAA";
    private static final String UPDATED_UTR_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_GENERATION_TIME = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_GENERATION_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REMITTER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTER_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REMITTER_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IFSC_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/escrow-transaction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EscrowTransactionDetailsRepository escrowTransactionDetailsRepository;

    @Mock
    private EscrowTransactionDetailsRepository escrowTransactionDetailsRepositoryMock;

    @Autowired
    private EscrowTransactionDetailsMapper escrowTransactionDetailsMapper;

    @Mock
    private EscrowTransactionDetailsService escrowTransactionDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private EscrowTransactionDetails escrowTransactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EscrowTransactionDetails createEntity(EntityManager em) {
        EscrowTransactionDetails escrowTransactionDetails = new EscrowTransactionDetails()
            .escrowTrnxDetailsId(DEFAULT_ESCROW_TRNX_DETAILS_ID)
            .escrowTrnxDetailsUlidId(DEFAULT_ESCROW_TRNX_DETAILS_ULID_ID)
            .customerCode(DEFAULT_CUSTOMER_CODE)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .productCode(DEFAULT_PRODUCT_CODE)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .batchAmt(DEFAULT_BATCH_AMT)
            .batchAmtCcd(DEFAULT_BATCH_AMT_CCD)
            .creditDate(DEFAULT_CREDIT_DATE)
            .vaNumber(DEFAULT_VA_NUMBER)
            .utrNo(DEFAULT_UTR_NO)
            .creditGenerationTime(DEFAULT_CREDIT_GENERATION_TIME)
            .remitterName(DEFAULT_REMITTER_NAME)
            .remitterAccountNumber(DEFAULT_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(DEFAULT_IFSC_CODE);
        return escrowTransactionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EscrowTransactionDetails createUpdatedEntity(EntityManager em) {
        EscrowTransactionDetails escrowTransactionDetails = new EscrowTransactionDetails()
            .escrowTrnxDetailsId(UPDATED_ESCROW_TRNX_DETAILS_ID)
            .escrowTrnxDetailsUlidId(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .productCode(UPDATED_PRODUCT_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .vaNumber(UPDATED_VA_NUMBER)
            .utrNo(UPDATED_UTR_NO)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE);
        return escrowTransactionDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(EscrowTransactionDetails.class).block();
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
        escrowTransactionDetails = createEntity(em);
    }

    @Test
    void createEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        EscrowTransactionDetails testEscrowTransactionDetails = escrowTransactionDetailsList.get(escrowTransactionDetailsList.size() - 1);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsId()).isEqualTo(DEFAULT_ESCROW_TRNX_DETAILS_ID);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsUlidId()).isEqualTo(DEFAULT_ESCROW_TRNX_DETAILS_ULID_ID);
        assertThat(testEscrowTransactionDetails.getCustomerCode()).isEqualTo(DEFAULT_CUSTOMER_CODE);
        assertThat(testEscrowTransactionDetails.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testEscrowTransactionDetails.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testEscrowTransactionDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testEscrowTransactionDetails.getBatchAmt()).isEqualTo(DEFAULT_BATCH_AMT);
        assertThat(testEscrowTransactionDetails.getBatchAmtCcd()).isEqualTo(DEFAULT_BATCH_AMT_CCD);
        assertThat(testEscrowTransactionDetails.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testEscrowTransactionDetails.getVaNumber()).isEqualTo(DEFAULT_VA_NUMBER);
        assertThat(testEscrowTransactionDetails.getUtrNo()).isEqualTo(DEFAULT_UTR_NO);
        assertThat(testEscrowTransactionDetails.getCreditGenerationTime()).isEqualTo(DEFAULT_CREDIT_GENERATION_TIME);
        assertThat(testEscrowTransactionDetails.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testEscrowTransactionDetails.getRemitterAccountNumber()).isEqualTo(DEFAULT_REMITTER_ACCOUNT_NUMBER);
        assertThat(testEscrowTransactionDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
    }

    @Test
    void createEscrowTransactionDetailsWithExistingId() throws Exception {
        // Create the EscrowTransactionDetails with an existing ID
        escrowTransactionDetails.setId(1L);
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        int databaseSizeBeforeCreate = escrowTransactionDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCustomerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setCustomerCode(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setCustomerName(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkProductCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setProductCode(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setTransactionType(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBatchAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setBatchAmt(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBatchAmtCcdIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setBatchAmtCcd(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setCreditDate(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVaNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setVaNumber(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUtrNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setUtrNo(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreditGenerationTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setCreditGenerationTime(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRemitterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setRemitterName(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRemitterAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setRemitterAccountNumber(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIfscCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        escrowTransactionDetails.setIfscCode(null);

        // Create the EscrowTransactionDetails, which fails.
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllEscrowTransactionDetails() {
        // Initialize the database
        escrowTransactionDetailsRepository.save(escrowTransactionDetails).block();

        // Get all the escrowTransactionDetailsList
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
            .value(hasItem(escrowTransactionDetails.getId().intValue()))
            .jsonPath("$.[*].escrowTrnxDetailsId")
            .value(hasItem(DEFAULT_ESCROW_TRNX_DETAILS_ID.intValue()))
            .jsonPath("$.[*].escrowTrnxDetailsUlidId")
            .value(hasItem(DEFAULT_ESCROW_TRNX_DETAILS_ULID_ID))
            .jsonPath("$.[*].customerCode")
            .value(hasItem(DEFAULT_CUSTOMER_CODE))
            .jsonPath("$.[*].customerName")
            .value(hasItem(DEFAULT_CUSTOMER_NAME))
            .jsonPath("$.[*].productCode")
            .value(hasItem(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.[*].transactionType")
            .value(hasItem(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.[*].batchAmt")
            .value(hasItem(DEFAULT_BATCH_AMT))
            .jsonPath("$.[*].batchAmtCcd")
            .value(hasItem(DEFAULT_BATCH_AMT_CCD))
            .jsonPath("$.[*].creditDate")
            .value(hasItem(DEFAULT_CREDIT_DATE))
            .jsonPath("$.[*].vaNumber")
            .value(hasItem(DEFAULT_VA_NUMBER))
            .jsonPath("$.[*].utrNo")
            .value(hasItem(DEFAULT_UTR_NO))
            .jsonPath("$.[*].creditGenerationTime")
            .value(hasItem(DEFAULT_CREDIT_GENERATION_TIME))
            .jsonPath("$.[*].remitterName")
            .value(hasItem(DEFAULT_REMITTER_NAME))
            .jsonPath("$.[*].remitterAccountNumber")
            .value(hasItem(DEFAULT_REMITTER_ACCOUNT_NUMBER))
            .jsonPath("$.[*].ifscCode")
            .value(hasItem(DEFAULT_IFSC_CODE));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEscrowTransactionDetailsWithEagerRelationshipsIsEnabled() {
        when(escrowTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(escrowTransactionDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllEscrowTransactionDetailsWithEagerRelationshipsIsNotEnabled() {
        when(escrowTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(escrowTransactionDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getEscrowTransactionDetails() {
        // Initialize the database
        escrowTransactionDetailsRepository.save(escrowTransactionDetails).block();

        // Get the escrowTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, escrowTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(escrowTransactionDetails.getId().intValue()))
            .jsonPath("$.escrowTrnxDetailsId")
            .value(is(DEFAULT_ESCROW_TRNX_DETAILS_ID.intValue()))
            .jsonPath("$.escrowTrnxDetailsUlidId")
            .value(is(DEFAULT_ESCROW_TRNX_DETAILS_ULID_ID))
            .jsonPath("$.customerCode")
            .value(is(DEFAULT_CUSTOMER_CODE))
            .jsonPath("$.customerName")
            .value(is(DEFAULT_CUSTOMER_NAME))
            .jsonPath("$.productCode")
            .value(is(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.transactionType")
            .value(is(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.batchAmt")
            .value(is(DEFAULT_BATCH_AMT))
            .jsonPath("$.batchAmtCcd")
            .value(is(DEFAULT_BATCH_AMT_CCD))
            .jsonPath("$.creditDate")
            .value(is(DEFAULT_CREDIT_DATE))
            .jsonPath("$.vaNumber")
            .value(is(DEFAULT_VA_NUMBER))
            .jsonPath("$.utrNo")
            .value(is(DEFAULT_UTR_NO))
            .jsonPath("$.creditGenerationTime")
            .value(is(DEFAULT_CREDIT_GENERATION_TIME))
            .jsonPath("$.remitterName")
            .value(is(DEFAULT_REMITTER_NAME))
            .jsonPath("$.remitterAccountNumber")
            .value(is(DEFAULT_REMITTER_ACCOUNT_NUMBER))
            .jsonPath("$.ifscCode")
            .value(is(DEFAULT_IFSC_CODE));
    }

    @Test
    void getNonExistingEscrowTransactionDetails() {
        // Get the escrowTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingEscrowTransactionDetails() throws Exception {
        // Initialize the database
        escrowTransactionDetailsRepository.save(escrowTransactionDetails).block();

        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the escrowTransactionDetails
        EscrowTransactionDetails updatedEscrowTransactionDetails = escrowTransactionDetailsRepository
            .findById(escrowTransactionDetails.getId())
            .block();
        updatedEscrowTransactionDetails
            .escrowTrnxDetailsId(UPDATED_ESCROW_TRNX_DETAILS_ID)
            .escrowTrnxDetailsUlidId(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .productCode(UPDATED_PRODUCT_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .vaNumber(UPDATED_VA_NUMBER)
            .utrNo(UPDATED_UTR_NO)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE);
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(updatedEscrowTransactionDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, escrowTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        EscrowTransactionDetails testEscrowTransactionDetails = escrowTransactionDetailsList.get(escrowTransactionDetailsList.size() - 1);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsId()).isEqualTo(UPDATED_ESCROW_TRNX_DETAILS_ID);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsUlidId()).isEqualTo(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID);
        assertThat(testEscrowTransactionDetails.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testEscrowTransactionDetails.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testEscrowTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testEscrowTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testEscrowTransactionDetails.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testEscrowTransactionDetails.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testEscrowTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testEscrowTransactionDetails.getVaNumber()).isEqualTo(UPDATED_VA_NUMBER);
        assertThat(testEscrowTransactionDetails.getUtrNo()).isEqualTo(UPDATED_UTR_NO);
        assertThat(testEscrowTransactionDetails.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testEscrowTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testEscrowTransactionDetails.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testEscrowTransactionDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
    }

    @Test
    void putNonExistingEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        escrowTransactionDetails.setId(longCount.incrementAndGet());

        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, escrowTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        escrowTransactionDetails.setId(longCount.incrementAndGet());

        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        escrowTransactionDetails.setId(longCount.incrementAndGet());

        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateEscrowTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        escrowTransactionDetailsRepository.save(escrowTransactionDetails).block();

        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the escrowTransactionDetails using partial update
        EscrowTransactionDetails partialUpdatedEscrowTransactionDetails = new EscrowTransactionDetails();
        partialUpdatedEscrowTransactionDetails.setId(escrowTransactionDetails.getId());

        partialUpdatedEscrowTransactionDetails
            .escrowTrnxDetailsId(UPDATED_ESCROW_TRNX_DETAILS_ID)
            .escrowTrnxDetailsUlidId(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID)
            .productCode(UPDATED_PRODUCT_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEscrowTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEscrowTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        EscrowTransactionDetails testEscrowTransactionDetails = escrowTransactionDetailsList.get(escrowTransactionDetailsList.size() - 1);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsId()).isEqualTo(UPDATED_ESCROW_TRNX_DETAILS_ID);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsUlidId()).isEqualTo(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID);
        assertThat(testEscrowTransactionDetails.getCustomerCode()).isEqualTo(DEFAULT_CUSTOMER_CODE);
        assertThat(testEscrowTransactionDetails.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testEscrowTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testEscrowTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testEscrowTransactionDetails.getBatchAmt()).isEqualTo(DEFAULT_BATCH_AMT);
        assertThat(testEscrowTransactionDetails.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testEscrowTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testEscrowTransactionDetails.getVaNumber()).isEqualTo(DEFAULT_VA_NUMBER);
        assertThat(testEscrowTransactionDetails.getUtrNo()).isEqualTo(DEFAULT_UTR_NO);
        assertThat(testEscrowTransactionDetails.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testEscrowTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testEscrowTransactionDetails.getRemitterAccountNumber()).isEqualTo(DEFAULT_REMITTER_ACCOUNT_NUMBER);
        assertThat(testEscrowTransactionDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
    }

    @Test
    void fullUpdateEscrowTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        escrowTransactionDetailsRepository.save(escrowTransactionDetails).block();

        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the escrowTransactionDetails using partial update
        EscrowTransactionDetails partialUpdatedEscrowTransactionDetails = new EscrowTransactionDetails();
        partialUpdatedEscrowTransactionDetails.setId(escrowTransactionDetails.getId());

        partialUpdatedEscrowTransactionDetails
            .escrowTrnxDetailsId(UPDATED_ESCROW_TRNX_DETAILS_ID)
            .escrowTrnxDetailsUlidId(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .productCode(UPDATED_PRODUCT_CODE)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .vaNumber(UPDATED_VA_NUMBER)
            .utrNo(UPDATED_UTR_NO)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedEscrowTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedEscrowTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        EscrowTransactionDetails testEscrowTransactionDetails = escrowTransactionDetailsList.get(escrowTransactionDetailsList.size() - 1);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsId()).isEqualTo(UPDATED_ESCROW_TRNX_DETAILS_ID);
        assertThat(testEscrowTransactionDetails.getEscrowTrnxDetailsUlidId()).isEqualTo(UPDATED_ESCROW_TRNX_DETAILS_ULID_ID);
        assertThat(testEscrowTransactionDetails.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testEscrowTransactionDetails.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testEscrowTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testEscrowTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testEscrowTransactionDetails.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testEscrowTransactionDetails.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testEscrowTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testEscrowTransactionDetails.getVaNumber()).isEqualTo(UPDATED_VA_NUMBER);
        assertThat(testEscrowTransactionDetails.getUtrNo()).isEqualTo(UPDATED_UTR_NO);
        assertThat(testEscrowTransactionDetails.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testEscrowTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testEscrowTransactionDetails.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testEscrowTransactionDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
    }

    @Test
    void patchNonExistingEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        escrowTransactionDetails.setId(longCount.incrementAndGet());

        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, escrowTransactionDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        escrowTransactionDetails.setId(longCount.incrementAndGet());

        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamEscrowTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = escrowTransactionDetailsRepository.findAll().collectList().block().size();
        escrowTransactionDetails.setId(longCount.incrementAndGet());

        // Create the EscrowTransactionDetails
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO = escrowTransactionDetailsMapper.toDto(escrowTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(escrowTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the EscrowTransactionDetails in the database
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteEscrowTransactionDetails() {
        // Initialize the database
        escrowTransactionDetailsRepository.save(escrowTransactionDetails).block();

        int databaseSizeBeforeDelete = escrowTransactionDetailsRepository.findAll().collectList().block().size();

        // Delete the escrowTransactionDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, escrowTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<EscrowTransactionDetails> escrowTransactionDetailsList = escrowTransactionDetailsRepository.findAll().collectList().block();
        assertThat(escrowTransactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
