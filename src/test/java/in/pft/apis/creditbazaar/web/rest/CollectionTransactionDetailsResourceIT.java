package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.CollectionTransactionDetails;
import in.pft.apis.creditbazaar.repository.CollectionTransactionDetailsRepository;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.service.CollectionTransactionDetailsService;
import in.pft.apis.creditbazaar.service.dto.CollectionTransactionDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.CollectionTransactionDetailsMapper;
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
 * Integration tests for the {@link CollectionTransactionDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CollectionTransactionDetailsResourceIT {

    private static final Long DEFAULT_TRNX_DETAILSID = 1L;
    private static final Long UPDATED_TRNX_DETAILSID = 2L;

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

    private static final String ENTITY_API_URL = "/api/collection-transaction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CollectionTransactionDetailsRepository collectionTransactionDetailsRepository;

    @Mock
    private CollectionTransactionDetailsRepository collectionTransactionDetailsRepositoryMock;

    @Autowired
    private CollectionTransactionDetailsMapper collectionTransactionDetailsMapper;

    @Mock
    private CollectionTransactionDetailsService collectionTransactionDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CollectionTransactionDetails collectionTransactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionTransactionDetails createEntity(EntityManager em) {
        CollectionTransactionDetails collectionTransactionDetails = new CollectionTransactionDetails()
            .trnxDetailsid(DEFAULT_TRNX_DETAILSID)
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
        return collectionTransactionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CollectionTransactionDetails createUpdatedEntity(EntityManager em) {
        CollectionTransactionDetails collectionTransactionDetails = new CollectionTransactionDetails()
            .trnxDetailsid(UPDATED_TRNX_DETAILSID)
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
        return collectionTransactionDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CollectionTransactionDetails.class).block();
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
        collectionTransactionDetails = createEntity(em);
    }

    @Test
    void createCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        CollectionTransactionDetails testCollectionTransactionDetails = collectionTransactionDetailsList.get(
            collectionTransactionDetailsList.size() - 1
        );
        assertThat(testCollectionTransactionDetails.getTrnxDetailsid()).isEqualTo(DEFAULT_TRNX_DETAILSID);
        assertThat(testCollectionTransactionDetails.getCustomerCode()).isEqualTo(DEFAULT_CUSTOMER_CODE);
        assertThat(testCollectionTransactionDetails.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testCollectionTransactionDetails.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testCollectionTransactionDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testCollectionTransactionDetails.getBatchAmt()).isEqualTo(DEFAULT_BATCH_AMT);
        assertThat(testCollectionTransactionDetails.getBatchAmtCcd()).isEqualTo(DEFAULT_BATCH_AMT_CCD);
        assertThat(testCollectionTransactionDetails.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testCollectionTransactionDetails.getVaNumber()).isEqualTo(DEFAULT_VA_NUMBER);
        assertThat(testCollectionTransactionDetails.getUtrNo()).isEqualTo(DEFAULT_UTR_NO);
        assertThat(testCollectionTransactionDetails.getCreditGenerationTime()).isEqualTo(DEFAULT_CREDIT_GENERATION_TIME);
        assertThat(testCollectionTransactionDetails.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testCollectionTransactionDetails.getRemitterAccountNumber()).isEqualTo(DEFAULT_REMITTER_ACCOUNT_NUMBER);
        assertThat(testCollectionTransactionDetails.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
    }

    @Test
    void createCollectionTransactionDetailsWithExistingId() throws Exception {
        // Create the CollectionTransactionDetails with an existing ID
        collectionTransactionDetails.setId(1L);
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        int databaseSizeBeforeCreate = collectionTransactionDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTrnxDetailsidIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setTrnxDetailsid(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setCustomerCode(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCustomerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setCustomerName(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkProductCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setProductCode(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setTransactionType(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBatchAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setBatchAmt(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBatchAmtCcdIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setBatchAmtCcd(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreditDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setCreditDate(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVaNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setVaNumber(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkUtrNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setUtrNo(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreditGenerationTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setCreditGenerationTime(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRemitterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setRemitterName(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRemitterAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setRemitterAccountNumber(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIfscCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        collectionTransactionDetails.setIfscCode(null);

        // Create the CollectionTransactionDetails, which fails.
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllCollectionTransactionDetails() {
        // Initialize the database
        collectionTransactionDetailsRepository.save(collectionTransactionDetails).block();

        // Get all the collectionTransactionDetailsList
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
            .value(hasItem(collectionTransactionDetails.getId().intValue()))
            .jsonPath("$.[*].trnxDetailsid")
            .value(hasItem(DEFAULT_TRNX_DETAILSID.intValue()))
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
    void getAllCollectionTransactionDetailsWithEagerRelationshipsIsEnabled() {
        when(collectionTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(collectionTransactionDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCollectionTransactionDetailsWithEagerRelationshipsIsNotEnabled() {
        when(collectionTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(collectionTransactionDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getCollectionTransactionDetails() {
        // Initialize the database
        collectionTransactionDetailsRepository.save(collectionTransactionDetails).block();

        // Get the collectionTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, collectionTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(collectionTransactionDetails.getId().intValue()))
            .jsonPath("$.trnxDetailsid")
            .value(is(DEFAULT_TRNX_DETAILSID.intValue()))
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
    void getNonExistingCollectionTransactionDetails() {
        // Get the collectionTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCollectionTransactionDetails() throws Exception {
        // Initialize the database
        collectionTransactionDetailsRepository.save(collectionTransactionDetails).block();

        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the collectionTransactionDetails
        CollectionTransactionDetails updatedCollectionTransactionDetails = collectionTransactionDetailsRepository
            .findById(collectionTransactionDetails.getId())
            .block();
        updatedCollectionTransactionDetails
            .trnxDetailsid(UPDATED_TRNX_DETAILSID)
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
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            updatedCollectionTransactionDetails
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, collectionTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        CollectionTransactionDetails testCollectionTransactionDetails = collectionTransactionDetailsList.get(
            collectionTransactionDetailsList.size() - 1
        );
        assertThat(testCollectionTransactionDetails.getTrnxDetailsid()).isEqualTo(UPDATED_TRNX_DETAILSID);
        assertThat(testCollectionTransactionDetails.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testCollectionTransactionDetails.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCollectionTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testCollectionTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testCollectionTransactionDetails.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testCollectionTransactionDetails.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testCollectionTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testCollectionTransactionDetails.getVaNumber()).isEqualTo(UPDATED_VA_NUMBER);
        assertThat(testCollectionTransactionDetails.getUtrNo()).isEqualTo(UPDATED_UTR_NO);
        assertThat(testCollectionTransactionDetails.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testCollectionTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testCollectionTransactionDetails.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testCollectionTransactionDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
    }

    @Test
    void putNonExistingCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        collectionTransactionDetails.setId(longCount.incrementAndGet());

        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, collectionTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        collectionTransactionDetails.setId(longCount.incrementAndGet());

        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        collectionTransactionDetails.setId(longCount.incrementAndGet());

        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCollectionTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        collectionTransactionDetailsRepository.save(collectionTransactionDetails).block();

        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the collectionTransactionDetails using partial update
        CollectionTransactionDetails partialUpdatedCollectionTransactionDetails = new CollectionTransactionDetails();
        partialUpdatedCollectionTransactionDetails.setId(collectionTransactionDetails.getId());

        partialUpdatedCollectionTransactionDetails
            .trnxDetailsid(UPDATED_TRNX_DETAILSID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .ifscCode(UPDATED_IFSC_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCollectionTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCollectionTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        CollectionTransactionDetails testCollectionTransactionDetails = collectionTransactionDetailsList.get(
            collectionTransactionDetailsList.size() - 1
        );
        assertThat(testCollectionTransactionDetails.getTrnxDetailsid()).isEqualTo(UPDATED_TRNX_DETAILSID);
        assertThat(testCollectionTransactionDetails.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testCollectionTransactionDetails.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCollectionTransactionDetails.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testCollectionTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testCollectionTransactionDetails.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testCollectionTransactionDetails.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testCollectionTransactionDetails.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testCollectionTransactionDetails.getVaNumber()).isEqualTo(DEFAULT_VA_NUMBER);
        assertThat(testCollectionTransactionDetails.getUtrNo()).isEqualTo(DEFAULT_UTR_NO);
        assertThat(testCollectionTransactionDetails.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testCollectionTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testCollectionTransactionDetails.getRemitterAccountNumber()).isEqualTo(DEFAULT_REMITTER_ACCOUNT_NUMBER);
        assertThat(testCollectionTransactionDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
    }

    @Test
    void fullUpdateCollectionTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        collectionTransactionDetailsRepository.save(collectionTransactionDetails).block();

        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the collectionTransactionDetails using partial update
        CollectionTransactionDetails partialUpdatedCollectionTransactionDetails = new CollectionTransactionDetails();
        partialUpdatedCollectionTransactionDetails.setId(collectionTransactionDetails.getId());

        partialUpdatedCollectionTransactionDetails
            .trnxDetailsid(UPDATED_TRNX_DETAILSID)
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
            .uri(ENTITY_API_URL_ID, partialUpdatedCollectionTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCollectionTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        CollectionTransactionDetails testCollectionTransactionDetails = collectionTransactionDetailsList.get(
            collectionTransactionDetailsList.size() - 1
        );
        assertThat(testCollectionTransactionDetails.getTrnxDetailsid()).isEqualTo(UPDATED_TRNX_DETAILSID);
        assertThat(testCollectionTransactionDetails.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testCollectionTransactionDetails.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testCollectionTransactionDetails.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testCollectionTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testCollectionTransactionDetails.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testCollectionTransactionDetails.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testCollectionTransactionDetails.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testCollectionTransactionDetails.getVaNumber()).isEqualTo(UPDATED_VA_NUMBER);
        assertThat(testCollectionTransactionDetails.getUtrNo()).isEqualTo(UPDATED_UTR_NO);
        assertThat(testCollectionTransactionDetails.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testCollectionTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testCollectionTransactionDetails.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testCollectionTransactionDetails.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
    }

    @Test
    void patchNonExistingCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        collectionTransactionDetails.setId(longCount.incrementAndGet());

        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, collectionTransactionDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        collectionTransactionDetails.setId(longCount.incrementAndGet());

        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCollectionTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = collectionTransactionDetailsRepository.findAll().collectList().block().size();
        collectionTransactionDetails.setId(longCount.incrementAndGet());

        // Create the CollectionTransactionDetails
        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = collectionTransactionDetailsMapper.toDto(
            collectionTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(collectionTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CollectionTransactionDetails in the database
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCollectionTransactionDetails() {
        // Initialize the database
        collectionTransactionDetailsRepository.save(collectionTransactionDetails).block();

        int databaseSizeBeforeDelete = collectionTransactionDetailsRepository.findAll().collectList().block().size();

        // Delete the collectionTransactionDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, collectionTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CollectionTransactionDetails> collectionTransactionDetailsList = collectionTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(collectionTransactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
