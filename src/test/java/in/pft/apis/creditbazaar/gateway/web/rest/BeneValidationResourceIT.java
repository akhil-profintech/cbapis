package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.BeneValidation;
import in.pft.apis.creditbazaar.gateway.repository.BeneValidationRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.BeneValidationService;
import in.pft.apis.creditbazaar.gateway.service.dto.BeneValidationDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.BeneValidationMapper;
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
 * Integration tests for the {@link BeneValidationResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class BeneValidationResourceIT {

    private static final String DEFAULT_BENEVALIDATION_ID = "AAAAAAAAAA";
    private static final String UPDATED_BENEVALIDATION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FIN_REQ_ID = "AAAAAAAAAA";
    private static final String UPDATED_FIN_REQ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_GRP_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GRP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REMITTER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTER_MOBILE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_REMITTER_MOBILE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_DEBTOR_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DEBTOR_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CREDITOR_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_CREDITOR_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IFSC_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MERCHANT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MERCHANT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_META_DATA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_META_DATA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_META_DATA_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_META_DATA_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_META_DATA_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_META_DATA_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_META_DATA_TIME = "AAAAAAAAAA";
    private static final String UPDATED_META_DATA_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_CREDITOR_ACCOUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_CREDITOR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_CREDITOR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_RESPONSE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_RESPONSE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_TRANSACTION_TIME = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_TRANSACTION_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RESOURCE_DATA_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_RESOURCE_DATA_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTUPDATED_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LASTUPDATED_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bene-validations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BeneValidationRepository beneValidationRepository;

    @Mock
    private BeneValidationRepository beneValidationRepositoryMock;

    @Autowired
    private BeneValidationMapper beneValidationMapper;

    @Mock
    private BeneValidationService beneValidationServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private BeneValidation beneValidation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeneValidation createEntity(EntityManager em) {
        BeneValidation beneValidation = new BeneValidation()
            .benevalidationId(DEFAULT_BENEVALIDATION_ID)
            .finReqId(DEFAULT_FIN_REQ_ID)
            .subGrpId(DEFAULT_SUB_GRP_ID)
            .remitterName(DEFAULT_REMITTER_NAME)
            .remitterMobileNumber(DEFAULT_REMITTER_MOBILE_NUMBER)
            .debtorAccountId(DEFAULT_DEBTOR_ACCOUNT_ID)
            .accountType(DEFAULT_ACCOUNT_TYPE)
            .creditorAccountId(DEFAULT_CREDITOR_ACCOUNT_ID)
            .ifscCode(DEFAULT_IFSC_CODE)
            .paymentDescription(DEFAULT_PAYMENT_DESCRIPTION)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .merchantCode(DEFAULT_MERCHANT_CODE)
            .identifier(DEFAULT_IDENTIFIER)
            .requestDateTime(DEFAULT_REQUEST_DATE_TIME)
            .metaDataStatus(DEFAULT_META_DATA_STATUS)
            .metaDataMessage(DEFAULT_META_DATA_MESSAGE)
            .metaDataVersion(DEFAULT_META_DATA_VERSION)
            .metaDataTime(DEFAULT_META_DATA_TIME)
            .resourceDataCreditorAccountId(DEFAULT_RESOURCE_DATA_CREDITOR_ACCOUNT_ID)
            .resourceDataCreditorName(DEFAULT_RESOURCE_DATA_CREDITOR_NAME)
            .resourceDataTransactionReferenceNumber(DEFAULT_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER)
            .resourceDataTransactionId(DEFAULT_RESOURCE_DATA_TRANSACTION_ID)
            .resourceDataResponseCode(DEFAULT_RESOURCE_DATA_RESPONSE_CODE)
            .resourceDataTransactionTime(DEFAULT_RESOURCE_DATA_TRANSACTION_TIME)
            .resourceDataIdentifier(DEFAULT_RESOURCE_DATA_IDENTIFIER)
            .responseDateTime(DEFAULT_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(DEFAULT_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return beneValidation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BeneValidation createUpdatedEntity(EntityManager em) {
        BeneValidation beneValidation = new BeneValidation()
            .benevalidationId(UPDATED_BENEVALIDATION_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterMobileNumber(UPDATED_REMITTER_MOBILE_NUMBER)
            .debtorAccountId(UPDATED_DEBTOR_ACCOUNT_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .creditorAccountId(UPDATED_CREDITOR_ACCOUNT_ID)
            .ifscCode(UPDATED_IFSC_CODE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .merchantCode(UPDATED_MERCHANT_CODE)
            .identifier(UPDATED_IDENTIFIER)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .metaDataStatus(UPDATED_META_DATA_STATUS)
            .metaDataMessage(UPDATED_META_DATA_MESSAGE)
            .metaDataVersion(UPDATED_META_DATA_VERSION)
            .metaDataTime(UPDATED_META_DATA_TIME)
            .resourceDataCreditorAccountId(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID)
            .resourceDataCreditorName(UPDATED_RESOURCE_DATA_CREDITOR_NAME)
            .resourceDataTransactionReferenceNumber(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER)
            .resourceDataTransactionId(UPDATED_RESOURCE_DATA_TRANSACTION_ID)
            .resourceDataResponseCode(UPDATED_RESOURCE_DATA_RESPONSE_CODE)
            .resourceDataTransactionTime(UPDATED_RESOURCE_DATA_TRANSACTION_TIME)
            .resourceDataIdentifier(UPDATED_RESOURCE_DATA_IDENTIFIER)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        return beneValidation;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(BeneValidation.class).block();
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
        beneValidation = createEntity(em);
    }

    @Test
    void createBeneValidation() throws Exception {
        int databaseSizeBeforeCreate = beneValidationRepository.findAll().collectList().block().size();
        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeCreate + 1);
        BeneValidation testBeneValidation = beneValidationList.get(beneValidationList.size() - 1);
        assertThat(testBeneValidation.getBenevalidationId()).isEqualTo(DEFAULT_BENEVALIDATION_ID);
        assertThat(testBeneValidation.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testBeneValidation.getSubGrpId()).isEqualTo(DEFAULT_SUB_GRP_ID);
        assertThat(testBeneValidation.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testBeneValidation.getRemitterMobileNumber()).isEqualTo(DEFAULT_REMITTER_MOBILE_NUMBER);
        assertThat(testBeneValidation.getDebtorAccountId()).isEqualTo(DEFAULT_DEBTOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testBeneValidation.getCreditorAccountId()).isEqualTo(DEFAULT_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testBeneValidation.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testBeneValidation.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getMerchantCode()).isEqualTo(DEFAULT_MERCHANT_CODE);
        assertThat(testBeneValidation.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testBeneValidation.getRequestDateTime()).isEqualTo(DEFAULT_REQUEST_DATE_TIME);
        assertThat(testBeneValidation.getMetaDataStatus()).isEqualTo(DEFAULT_META_DATA_STATUS);
        assertThat(testBeneValidation.getMetaDataMessage()).isEqualTo(DEFAULT_META_DATA_MESSAGE);
        assertThat(testBeneValidation.getMetaDataVersion()).isEqualTo(DEFAULT_META_DATA_VERSION);
        assertThat(testBeneValidation.getMetaDataTime()).isEqualTo(DEFAULT_META_DATA_TIME);
        assertThat(testBeneValidation.getResourceDataCreditorAccountId()).isEqualTo(DEFAULT_RESOURCE_DATA_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getResourceDataCreditorName()).isEqualTo(DEFAULT_RESOURCE_DATA_CREDITOR_NAME);
        assertThat(testBeneValidation.getResourceDataTransactionReferenceNumber())
            .isEqualTo(DEFAULT_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getResourceDataTransactionId()).isEqualTo(DEFAULT_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testBeneValidation.getResourceDataResponseCode()).isEqualTo(DEFAULT_RESOURCE_DATA_RESPONSE_CODE);
        assertThat(testBeneValidation.getResourceDataTransactionTime()).isEqualTo(DEFAULT_RESOURCE_DATA_TRANSACTION_TIME);
        assertThat(testBeneValidation.getResourceDataIdentifier()).isEqualTo(DEFAULT_RESOURCE_DATA_IDENTIFIER);
        assertThat(testBeneValidation.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testBeneValidation.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testBeneValidation.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    void createBeneValidationWithExistingId() throws Exception {
        // Create the BeneValidation with an existing ID
        beneValidation.setId(1L);
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        int databaseSizeBeforeCreate = beneValidationRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkBenevalidationIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneValidationRepository.findAll().collectList().block().size();
        // set the field null
        beneValidation.setBenevalidationId(null);

        // Create the BeneValidation, which fails.
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRequestDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneValidationRepository.findAll().collectList().block().size();
        // set the field null
        beneValidation.setRequestDateTime(null);

        // Create the BeneValidation, which fails.
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkResponseDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneValidationRepository.findAll().collectList().block().size();
        // set the field null
        beneValidation.setResponseDateTime(null);

        // Create the BeneValidation, which fails.
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastupdatedDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneValidationRepository.findAll().collectList().block().size();
        // set the field null
        beneValidation.setLastupdatedDateTime(null);

        // Create the BeneValidation, which fails.
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = beneValidationRepository.findAll().collectList().block().size();
        // set the field null
        beneValidation.setLastUpdatedBy(null);

        // Create the BeneValidation, which fails.
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllBeneValidations() {
        // Initialize the database
        beneValidationRepository.save(beneValidation).block();

        // Get all the beneValidationList
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
            .value(hasItem(beneValidation.getId().intValue()))
            .jsonPath("$.[*].benevalidationId")
            .value(hasItem(DEFAULT_BENEVALIDATION_ID))
            .jsonPath("$.[*].finReqId")
            .value(hasItem(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.[*].subGrpId")
            .value(hasItem(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.[*].remitterName")
            .value(hasItem(DEFAULT_REMITTER_NAME))
            .jsonPath("$.[*].remitterMobileNumber")
            .value(hasItem(DEFAULT_REMITTER_MOBILE_NUMBER))
            .jsonPath("$.[*].debtorAccountId")
            .value(hasItem(DEFAULT_DEBTOR_ACCOUNT_ID))
            .jsonPath("$.[*].accountType")
            .value(hasItem(DEFAULT_ACCOUNT_TYPE))
            .jsonPath("$.[*].creditorAccountId")
            .value(hasItem(DEFAULT_CREDITOR_ACCOUNT_ID))
            .jsonPath("$.[*].ifscCode")
            .value(hasItem(DEFAULT_IFSC_CODE))
            .jsonPath("$.[*].paymentDescription")
            .value(hasItem(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].merchantCode")
            .value(hasItem(DEFAULT_MERCHANT_CODE))
            .jsonPath("$.[*].identifier")
            .value(hasItem(DEFAULT_IDENTIFIER))
            .jsonPath("$.[*].requestDateTime")
            .value(hasItem(DEFAULT_REQUEST_DATE_TIME))
            .jsonPath("$.[*].metaDataStatus")
            .value(hasItem(DEFAULT_META_DATA_STATUS))
            .jsonPath("$.[*].metaDataMessage")
            .value(hasItem(DEFAULT_META_DATA_MESSAGE))
            .jsonPath("$.[*].metaDataVersion")
            .value(hasItem(DEFAULT_META_DATA_VERSION))
            .jsonPath("$.[*].metaDataTime")
            .value(hasItem(DEFAULT_META_DATA_TIME))
            .jsonPath("$.[*].resourceDataCreditorAccountId")
            .value(hasItem(DEFAULT_RESOURCE_DATA_CREDITOR_ACCOUNT_ID))
            .jsonPath("$.[*].resourceDataCreditorName")
            .value(hasItem(DEFAULT_RESOURCE_DATA_CREDITOR_NAME))
            .jsonPath("$.[*].resourceDataTransactionReferenceNumber")
            .value(hasItem(DEFAULT_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].resourceDataTransactionId")
            .value(hasItem(DEFAULT_RESOURCE_DATA_TRANSACTION_ID))
            .jsonPath("$.[*].resourceDataResponseCode")
            .value(hasItem(DEFAULT_RESOURCE_DATA_RESPONSE_CODE))
            .jsonPath("$.[*].resourceDataTransactionTime")
            .value(hasItem(DEFAULT_RESOURCE_DATA_TRANSACTION_TIME))
            .jsonPath("$.[*].resourceDataIdentifier")
            .value(hasItem(DEFAULT_RESOURCE_DATA_IDENTIFIER))
            .jsonPath("$.[*].responseDateTime")
            .value(hasItem(DEFAULT_RESPONSE_DATE_TIME))
            .jsonPath("$.[*].lastupdatedDateTime")
            .value(hasItem(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.[*].lastUpdatedBy")
            .value(hasItem(DEFAULT_LAST_UPDATED_BY));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBeneValidationsWithEagerRelationshipsIsEnabled() {
        when(beneValidationServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(beneValidationServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllBeneValidationsWithEagerRelationshipsIsNotEnabled() {
        when(beneValidationServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(beneValidationRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getBeneValidation() {
        // Initialize the database
        beneValidationRepository.save(beneValidation).block();

        // Get the beneValidation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, beneValidation.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(beneValidation.getId().intValue()))
            .jsonPath("$.benevalidationId")
            .value(is(DEFAULT_BENEVALIDATION_ID))
            .jsonPath("$.finReqId")
            .value(is(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.subGrpId")
            .value(is(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.remitterName")
            .value(is(DEFAULT_REMITTER_NAME))
            .jsonPath("$.remitterMobileNumber")
            .value(is(DEFAULT_REMITTER_MOBILE_NUMBER))
            .jsonPath("$.debtorAccountId")
            .value(is(DEFAULT_DEBTOR_ACCOUNT_ID))
            .jsonPath("$.accountType")
            .value(is(DEFAULT_ACCOUNT_TYPE))
            .jsonPath("$.creditorAccountId")
            .value(is(DEFAULT_CREDITOR_ACCOUNT_ID))
            .jsonPath("$.ifscCode")
            .value(is(DEFAULT_IFSC_CODE))
            .jsonPath("$.paymentDescription")
            .value(is(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.merchantCode")
            .value(is(DEFAULT_MERCHANT_CODE))
            .jsonPath("$.identifier")
            .value(is(DEFAULT_IDENTIFIER))
            .jsonPath("$.requestDateTime")
            .value(is(DEFAULT_REQUEST_DATE_TIME))
            .jsonPath("$.metaDataStatus")
            .value(is(DEFAULT_META_DATA_STATUS))
            .jsonPath("$.metaDataMessage")
            .value(is(DEFAULT_META_DATA_MESSAGE))
            .jsonPath("$.metaDataVersion")
            .value(is(DEFAULT_META_DATA_VERSION))
            .jsonPath("$.metaDataTime")
            .value(is(DEFAULT_META_DATA_TIME))
            .jsonPath("$.resourceDataCreditorAccountId")
            .value(is(DEFAULT_RESOURCE_DATA_CREDITOR_ACCOUNT_ID))
            .jsonPath("$.resourceDataCreditorName")
            .value(is(DEFAULT_RESOURCE_DATA_CREDITOR_NAME))
            .jsonPath("$.resourceDataTransactionReferenceNumber")
            .value(is(DEFAULT_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.resourceDataTransactionId")
            .value(is(DEFAULT_RESOURCE_DATA_TRANSACTION_ID))
            .jsonPath("$.resourceDataResponseCode")
            .value(is(DEFAULT_RESOURCE_DATA_RESPONSE_CODE))
            .jsonPath("$.resourceDataTransactionTime")
            .value(is(DEFAULT_RESOURCE_DATA_TRANSACTION_TIME))
            .jsonPath("$.resourceDataIdentifier")
            .value(is(DEFAULT_RESOURCE_DATA_IDENTIFIER))
            .jsonPath("$.responseDateTime")
            .value(is(DEFAULT_RESPONSE_DATE_TIME))
            .jsonPath("$.lastupdatedDateTime")
            .value(is(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.lastUpdatedBy")
            .value(is(DEFAULT_LAST_UPDATED_BY));
    }

    @Test
    void getNonExistingBeneValidation() {
        // Get the beneValidation
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingBeneValidation() throws Exception {
        // Initialize the database
        beneValidationRepository.save(beneValidation).block();

        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();

        // Update the beneValidation
        BeneValidation updatedBeneValidation = beneValidationRepository.findById(beneValidation.getId()).block();
        updatedBeneValidation
            .benevalidationId(UPDATED_BENEVALIDATION_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterMobileNumber(UPDATED_REMITTER_MOBILE_NUMBER)
            .debtorAccountId(UPDATED_DEBTOR_ACCOUNT_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .creditorAccountId(UPDATED_CREDITOR_ACCOUNT_ID)
            .ifscCode(UPDATED_IFSC_CODE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .merchantCode(UPDATED_MERCHANT_CODE)
            .identifier(UPDATED_IDENTIFIER)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .metaDataStatus(UPDATED_META_DATA_STATUS)
            .metaDataMessage(UPDATED_META_DATA_MESSAGE)
            .metaDataVersion(UPDATED_META_DATA_VERSION)
            .metaDataTime(UPDATED_META_DATA_TIME)
            .resourceDataCreditorAccountId(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID)
            .resourceDataCreditorName(UPDATED_RESOURCE_DATA_CREDITOR_NAME)
            .resourceDataTransactionReferenceNumber(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER)
            .resourceDataTransactionId(UPDATED_RESOURCE_DATA_TRANSACTION_ID)
            .resourceDataResponseCode(UPDATED_RESOURCE_DATA_RESPONSE_CODE)
            .resourceDataTransactionTime(UPDATED_RESOURCE_DATA_TRANSACTION_TIME)
            .resourceDataIdentifier(UPDATED_RESOURCE_DATA_IDENTIFIER)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(updatedBeneValidation);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, beneValidationDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
        BeneValidation testBeneValidation = beneValidationList.get(beneValidationList.size() - 1);
        assertThat(testBeneValidation.getBenevalidationId()).isEqualTo(UPDATED_BENEVALIDATION_ID);
        assertThat(testBeneValidation.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testBeneValidation.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testBeneValidation.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testBeneValidation.getRemitterMobileNumber()).isEqualTo(UPDATED_REMITTER_MOBILE_NUMBER);
        assertThat(testBeneValidation.getDebtorAccountId()).isEqualTo(UPDATED_DEBTOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testBeneValidation.getCreditorAccountId()).isEqualTo(UPDATED_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testBeneValidation.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testBeneValidation.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getMerchantCode()).isEqualTo(UPDATED_MERCHANT_CODE);
        assertThat(testBeneValidation.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testBeneValidation.getRequestDateTime()).isEqualTo(UPDATED_REQUEST_DATE_TIME);
        assertThat(testBeneValidation.getMetaDataStatus()).isEqualTo(UPDATED_META_DATA_STATUS);
        assertThat(testBeneValidation.getMetaDataMessage()).isEqualTo(UPDATED_META_DATA_MESSAGE);
        assertThat(testBeneValidation.getMetaDataVersion()).isEqualTo(UPDATED_META_DATA_VERSION);
        assertThat(testBeneValidation.getMetaDataTime()).isEqualTo(UPDATED_META_DATA_TIME);
        assertThat(testBeneValidation.getResourceDataCreditorAccountId()).isEqualTo(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getResourceDataCreditorName()).isEqualTo(UPDATED_RESOURCE_DATA_CREDITOR_NAME);
        assertThat(testBeneValidation.getResourceDataTransactionReferenceNumber())
            .isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getResourceDataTransactionId()).isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testBeneValidation.getResourceDataResponseCode()).isEqualTo(UPDATED_RESOURCE_DATA_RESPONSE_CODE);
        assertThat(testBeneValidation.getResourceDataTransactionTime()).isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_TIME);
        assertThat(testBeneValidation.getResourceDataIdentifier()).isEqualTo(UPDATED_RESOURCE_DATA_IDENTIFIER);
        assertThat(testBeneValidation.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testBeneValidation.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testBeneValidation.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void putNonExistingBeneValidation() throws Exception {
        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();
        beneValidation.setId(longCount.incrementAndGet());

        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, beneValidationDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchBeneValidation() throws Exception {
        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();
        beneValidation.setId(longCount.incrementAndGet());

        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamBeneValidation() throws Exception {
        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();
        beneValidation.setId(longCount.incrementAndGet());

        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateBeneValidationWithPatch() throws Exception {
        // Initialize the database
        beneValidationRepository.save(beneValidation).block();

        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();

        // Update the beneValidation using partial update
        BeneValidation partialUpdatedBeneValidation = new BeneValidation();
        partialUpdatedBeneValidation.setId(beneValidation.getId());

        partialUpdatedBeneValidation
            .subGrpId(UPDATED_SUB_GRP_ID)
            .remitterMobileNumber(UPDATED_REMITTER_MOBILE_NUMBER)
            .debtorAccountId(UPDATED_DEBTOR_ACCOUNT_ID)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .identifier(UPDATED_IDENTIFIER)
            .metaDataStatus(UPDATED_META_DATA_STATUS)
            .metaDataMessage(UPDATED_META_DATA_MESSAGE)
            .resourceDataCreditorAccountId(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID)
            .resourceDataCreditorName(UPDATED_RESOURCE_DATA_CREDITOR_NAME)
            .resourceDataTransactionReferenceNumber(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER)
            .resourceDataTransactionId(UPDATED_RESOURCE_DATA_TRANSACTION_ID)
            .resourceDataIdentifier(UPDATED_RESOURCE_DATA_IDENTIFIER)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBeneValidation.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBeneValidation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
        BeneValidation testBeneValidation = beneValidationList.get(beneValidationList.size() - 1);
        assertThat(testBeneValidation.getBenevalidationId()).isEqualTo(DEFAULT_BENEVALIDATION_ID);
        assertThat(testBeneValidation.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testBeneValidation.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testBeneValidation.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testBeneValidation.getRemitterMobileNumber()).isEqualTo(UPDATED_REMITTER_MOBILE_NUMBER);
        assertThat(testBeneValidation.getDebtorAccountId()).isEqualTo(UPDATED_DEBTOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getAccountType()).isEqualTo(DEFAULT_ACCOUNT_TYPE);
        assertThat(testBeneValidation.getCreditorAccountId()).isEqualTo(DEFAULT_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testBeneValidation.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testBeneValidation.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getMerchantCode()).isEqualTo(DEFAULT_MERCHANT_CODE);
        assertThat(testBeneValidation.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testBeneValidation.getRequestDateTime()).isEqualTo(DEFAULT_REQUEST_DATE_TIME);
        assertThat(testBeneValidation.getMetaDataStatus()).isEqualTo(UPDATED_META_DATA_STATUS);
        assertThat(testBeneValidation.getMetaDataMessage()).isEqualTo(UPDATED_META_DATA_MESSAGE);
        assertThat(testBeneValidation.getMetaDataVersion()).isEqualTo(DEFAULT_META_DATA_VERSION);
        assertThat(testBeneValidation.getMetaDataTime()).isEqualTo(DEFAULT_META_DATA_TIME);
        assertThat(testBeneValidation.getResourceDataCreditorAccountId()).isEqualTo(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getResourceDataCreditorName()).isEqualTo(UPDATED_RESOURCE_DATA_CREDITOR_NAME);
        assertThat(testBeneValidation.getResourceDataTransactionReferenceNumber())
            .isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getResourceDataTransactionId()).isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testBeneValidation.getResourceDataResponseCode()).isEqualTo(DEFAULT_RESOURCE_DATA_RESPONSE_CODE);
        assertThat(testBeneValidation.getResourceDataTransactionTime()).isEqualTo(DEFAULT_RESOURCE_DATA_TRANSACTION_TIME);
        assertThat(testBeneValidation.getResourceDataIdentifier()).isEqualTo(UPDATED_RESOURCE_DATA_IDENTIFIER);
        assertThat(testBeneValidation.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testBeneValidation.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testBeneValidation.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void fullUpdateBeneValidationWithPatch() throws Exception {
        // Initialize the database
        beneValidationRepository.save(beneValidation).block();

        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();

        // Update the beneValidation using partial update
        BeneValidation partialUpdatedBeneValidation = new BeneValidation();
        partialUpdatedBeneValidation.setId(beneValidation.getId());

        partialUpdatedBeneValidation
            .benevalidationId(UPDATED_BENEVALIDATION_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterMobileNumber(UPDATED_REMITTER_MOBILE_NUMBER)
            .debtorAccountId(UPDATED_DEBTOR_ACCOUNT_ID)
            .accountType(UPDATED_ACCOUNT_TYPE)
            .creditorAccountId(UPDATED_CREDITOR_ACCOUNT_ID)
            .ifscCode(UPDATED_IFSC_CODE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .merchantCode(UPDATED_MERCHANT_CODE)
            .identifier(UPDATED_IDENTIFIER)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .metaDataStatus(UPDATED_META_DATA_STATUS)
            .metaDataMessage(UPDATED_META_DATA_MESSAGE)
            .metaDataVersion(UPDATED_META_DATA_VERSION)
            .metaDataTime(UPDATED_META_DATA_TIME)
            .resourceDataCreditorAccountId(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID)
            .resourceDataCreditorName(UPDATED_RESOURCE_DATA_CREDITOR_NAME)
            .resourceDataTransactionReferenceNumber(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER)
            .resourceDataTransactionId(UPDATED_RESOURCE_DATA_TRANSACTION_ID)
            .resourceDataResponseCode(UPDATED_RESOURCE_DATA_RESPONSE_CODE)
            .resourceDataTransactionTime(UPDATED_RESOURCE_DATA_TRANSACTION_TIME)
            .resourceDataIdentifier(UPDATED_RESOURCE_DATA_IDENTIFIER)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedBeneValidation.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedBeneValidation))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
        BeneValidation testBeneValidation = beneValidationList.get(beneValidationList.size() - 1);
        assertThat(testBeneValidation.getBenevalidationId()).isEqualTo(UPDATED_BENEVALIDATION_ID);
        assertThat(testBeneValidation.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testBeneValidation.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testBeneValidation.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testBeneValidation.getRemitterMobileNumber()).isEqualTo(UPDATED_REMITTER_MOBILE_NUMBER);
        assertThat(testBeneValidation.getDebtorAccountId()).isEqualTo(UPDATED_DEBTOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getAccountType()).isEqualTo(UPDATED_ACCOUNT_TYPE);
        assertThat(testBeneValidation.getCreditorAccountId()).isEqualTo(UPDATED_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testBeneValidation.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testBeneValidation.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getMerchantCode()).isEqualTo(UPDATED_MERCHANT_CODE);
        assertThat(testBeneValidation.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testBeneValidation.getRequestDateTime()).isEqualTo(UPDATED_REQUEST_DATE_TIME);
        assertThat(testBeneValidation.getMetaDataStatus()).isEqualTo(UPDATED_META_DATA_STATUS);
        assertThat(testBeneValidation.getMetaDataMessage()).isEqualTo(UPDATED_META_DATA_MESSAGE);
        assertThat(testBeneValidation.getMetaDataVersion()).isEqualTo(UPDATED_META_DATA_VERSION);
        assertThat(testBeneValidation.getMetaDataTime()).isEqualTo(UPDATED_META_DATA_TIME);
        assertThat(testBeneValidation.getResourceDataCreditorAccountId()).isEqualTo(UPDATED_RESOURCE_DATA_CREDITOR_ACCOUNT_ID);
        assertThat(testBeneValidation.getResourceDataCreditorName()).isEqualTo(UPDATED_RESOURCE_DATA_CREDITOR_NAME);
        assertThat(testBeneValidation.getResourceDataTransactionReferenceNumber())
            .isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testBeneValidation.getResourceDataTransactionId()).isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testBeneValidation.getResourceDataResponseCode()).isEqualTo(UPDATED_RESOURCE_DATA_RESPONSE_CODE);
        assertThat(testBeneValidation.getResourceDataTransactionTime()).isEqualTo(UPDATED_RESOURCE_DATA_TRANSACTION_TIME);
        assertThat(testBeneValidation.getResourceDataIdentifier()).isEqualTo(UPDATED_RESOURCE_DATA_IDENTIFIER);
        assertThat(testBeneValidation.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testBeneValidation.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testBeneValidation.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void patchNonExistingBeneValidation() throws Exception {
        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();
        beneValidation.setId(longCount.incrementAndGet());

        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, beneValidationDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchBeneValidation() throws Exception {
        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();
        beneValidation.setId(longCount.incrementAndGet());

        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamBeneValidation() throws Exception {
        int databaseSizeBeforeUpdate = beneValidationRepository.findAll().collectList().block().size();
        beneValidation.setId(longCount.incrementAndGet());

        // Create the BeneValidation
        BeneValidationDTO beneValidationDTO = beneValidationMapper.toDto(beneValidation);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(beneValidationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the BeneValidation in the database
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteBeneValidation() {
        // Initialize the database
        beneValidationRepository.save(beneValidation).block();

        int databaseSizeBeforeDelete = beneValidationRepository.findAll().collectList().block().size();

        // Delete the beneValidation
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, beneValidation.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<BeneValidation> beneValidationList = beneValidationRepository.findAll().collectList().block();
        assertThat(beneValidationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
