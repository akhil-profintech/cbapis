package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.InstaAlert;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.InstaAlertRepository;
import in.pft.apis.creditbazaar.gateway.service.InstaAlertService;
import in.pft.apis.creditbazaar.gateway.service.dto.InstaAlertDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.InstaAlertMapper;
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
 * Integration tests for the {@link InstaAlertResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class InstaAlertResourceIT {

    private static final Long DEFAULT_INSTA_ALERT_ID = 1L;
    private static final Long UPDATED_INSTA_ALERT_ID = 2L;

    private static final String DEFAULT_FIN_REQ_ID = "AAAAAAAAAA";
    private static final String UPDATED_FIN_REQ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_GRP_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GRP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_POOLING_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_POOLING_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_BATCH_AMT = 1L;
    private static final Long UPDATED_BATCH_AMT = 2L;

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

    private static final String DEFAULT_LASTUPDATED_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LASTUPDATED_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_KEY = "AAAAAAAAAA";
    private static final String UPDATED_DATA_KEY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/insta-alerts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InstaAlertRepository instaAlertRepository;

    @Mock
    private InstaAlertRepository instaAlertRepositoryMock;

    @Autowired
    private InstaAlertMapper instaAlertMapper;

    @Mock
    private InstaAlertService instaAlertServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private InstaAlert instaAlert;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstaAlert createEntity(EntityManager em) {
        InstaAlert instaAlert = new InstaAlert()
            .instaAlertId(DEFAULT_INSTA_ALERT_ID)
            .finReqId(DEFAULT_FIN_REQ_ID)
            .subGrpId(DEFAULT_SUB_GRP_ID)
            .customerCode(DEFAULT_CUSTOMER_CODE)
            .customerName(DEFAULT_CUSTOMER_NAME)
            .productCode(DEFAULT_PRODUCT_CODE)
            .poolingAccountNumber(DEFAULT_POOLING_ACCOUNT_NUMBER)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .batchAmt(DEFAULT_BATCH_AMT)
            .batchAmtCcd(DEFAULT_BATCH_AMT_CCD)
            .creditDate(DEFAULT_CREDIT_DATE)
            .vaNumber(DEFAULT_VA_NUMBER)
            .utrNo(DEFAULT_UTR_NO)
            .creditGenerationTime(DEFAULT_CREDIT_GENERATION_TIME)
            .remitterName(DEFAULT_REMITTER_NAME)
            .remitterAccountNumber(DEFAULT_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(DEFAULT_IFSC_CODE)
            .lastupdatedDateTime(DEFAULT_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY)
            .dataKey(DEFAULT_DATA_KEY);
        return instaAlert;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static InstaAlert createUpdatedEntity(EntityManager em) {
        InstaAlert instaAlert = new InstaAlert()
            .instaAlertId(UPDATED_INSTA_ALERT_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .productCode(UPDATED_PRODUCT_CODE)
            .poolingAccountNumber(UPDATED_POOLING_ACCOUNT_NUMBER)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .vaNumber(UPDATED_VA_NUMBER)
            .utrNo(UPDATED_UTR_NO)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .dataKey(UPDATED_DATA_KEY);
        return instaAlert;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(InstaAlert.class).block();
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
        instaAlert = createEntity(em);
    }

    @Test
    void createInstaAlert() throws Exception {
        int databaseSizeBeforeCreate = instaAlertRepository.findAll().collectList().block().size();
        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeCreate + 1);
        InstaAlert testInstaAlert = instaAlertList.get(instaAlertList.size() - 1);
        assertThat(testInstaAlert.getInstaAlertId()).isEqualTo(DEFAULT_INSTA_ALERT_ID);
        assertThat(testInstaAlert.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testInstaAlert.getSubGrpId()).isEqualTo(DEFAULT_SUB_GRP_ID);
        assertThat(testInstaAlert.getCustomerCode()).isEqualTo(DEFAULT_CUSTOMER_CODE);
        assertThat(testInstaAlert.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testInstaAlert.getProductCode()).isEqualTo(DEFAULT_PRODUCT_CODE);
        assertThat(testInstaAlert.getPoolingAccountNumber()).isEqualTo(DEFAULT_POOLING_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testInstaAlert.getBatchAmt()).isEqualTo(DEFAULT_BATCH_AMT);
        assertThat(testInstaAlert.getBatchAmtCcd()).isEqualTo(DEFAULT_BATCH_AMT_CCD);
        assertThat(testInstaAlert.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testInstaAlert.getVaNumber()).isEqualTo(DEFAULT_VA_NUMBER);
        assertThat(testInstaAlert.getUtrNo()).isEqualTo(DEFAULT_UTR_NO);
        assertThat(testInstaAlert.getCreditGenerationTime()).isEqualTo(DEFAULT_CREDIT_GENERATION_TIME);
        assertThat(testInstaAlert.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testInstaAlert.getRemitterAccountNumber()).isEqualTo(DEFAULT_REMITTER_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testInstaAlert.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testInstaAlert.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
        assertThat(testInstaAlert.getDataKey()).isEqualTo(DEFAULT_DATA_KEY);
    }

    @Test
    void createInstaAlertWithExistingId() throws Exception {
        // Create the InstaAlert with an existing ID
        instaAlert.setId(1L);
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        int databaseSizeBeforeCreate = instaAlertRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkInstaAlertIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = instaAlertRepository.findAll().collectList().block().size();
        // set the field null
        instaAlert.setInstaAlertId(null);

        // Create the InstaAlert, which fails.
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastupdatedDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = instaAlertRepository.findAll().collectList().block().size();
        // set the field null
        instaAlert.setLastupdatedDateTime(null);

        // Create the InstaAlert, which fails.
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = instaAlertRepository.findAll().collectList().block().size();
        // set the field null
        instaAlert.setLastUpdatedBy(null);

        // Create the InstaAlert, which fails.
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllInstaAlerts() {
        // Initialize the database
        instaAlertRepository.save(instaAlert).block();

        // Get all the instaAlertList
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
            .value(hasItem(instaAlert.getId().intValue()))
            .jsonPath("$.[*].instaAlertId")
            .value(hasItem(DEFAULT_INSTA_ALERT_ID.intValue()))
            .jsonPath("$.[*].finReqId")
            .value(hasItem(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.[*].subGrpId")
            .value(hasItem(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.[*].customerCode")
            .value(hasItem(DEFAULT_CUSTOMER_CODE))
            .jsonPath("$.[*].customerName")
            .value(hasItem(DEFAULT_CUSTOMER_NAME))
            .jsonPath("$.[*].productCode")
            .value(hasItem(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.[*].poolingAccountNumber")
            .value(hasItem(DEFAULT_POOLING_ACCOUNT_NUMBER))
            .jsonPath("$.[*].transactionType")
            .value(hasItem(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.[*].batchAmt")
            .value(hasItem(DEFAULT_BATCH_AMT.intValue()))
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
            .value(hasItem(DEFAULT_IFSC_CODE))
            .jsonPath("$.[*].lastupdatedDateTime")
            .value(hasItem(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.[*].lastUpdatedBy")
            .value(hasItem(DEFAULT_LAST_UPDATED_BY))
            .jsonPath("$.[*].dataKey")
            .value(hasItem(DEFAULT_DATA_KEY));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllInstaAlertsWithEagerRelationshipsIsEnabled() {
        when(instaAlertServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(instaAlertServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllInstaAlertsWithEagerRelationshipsIsNotEnabled() {
        when(instaAlertServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(instaAlertRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getInstaAlert() {
        // Initialize the database
        instaAlertRepository.save(instaAlert).block();

        // Get the instaAlert
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, instaAlert.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(instaAlert.getId().intValue()))
            .jsonPath("$.instaAlertId")
            .value(is(DEFAULT_INSTA_ALERT_ID.intValue()))
            .jsonPath("$.finReqId")
            .value(is(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.subGrpId")
            .value(is(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.customerCode")
            .value(is(DEFAULT_CUSTOMER_CODE))
            .jsonPath("$.customerName")
            .value(is(DEFAULT_CUSTOMER_NAME))
            .jsonPath("$.productCode")
            .value(is(DEFAULT_PRODUCT_CODE))
            .jsonPath("$.poolingAccountNumber")
            .value(is(DEFAULT_POOLING_ACCOUNT_NUMBER))
            .jsonPath("$.transactionType")
            .value(is(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.batchAmt")
            .value(is(DEFAULT_BATCH_AMT.intValue()))
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
            .value(is(DEFAULT_IFSC_CODE))
            .jsonPath("$.lastupdatedDateTime")
            .value(is(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.lastUpdatedBy")
            .value(is(DEFAULT_LAST_UPDATED_BY))
            .jsonPath("$.dataKey")
            .value(is(DEFAULT_DATA_KEY));
    }

    @Test
    void getNonExistingInstaAlert() {
        // Get the instaAlert
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingInstaAlert() throws Exception {
        // Initialize the database
        instaAlertRepository.save(instaAlert).block();

        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();

        // Update the instaAlert
        InstaAlert updatedInstaAlert = instaAlertRepository.findById(instaAlert.getId()).block();
        updatedInstaAlert
            .instaAlertId(UPDATED_INSTA_ALERT_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .productCode(UPDATED_PRODUCT_CODE)
            .poolingAccountNumber(UPDATED_POOLING_ACCOUNT_NUMBER)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .vaNumber(UPDATED_VA_NUMBER)
            .utrNo(UPDATED_UTR_NO)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .dataKey(UPDATED_DATA_KEY);
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(updatedInstaAlert);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, instaAlertDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
        InstaAlert testInstaAlert = instaAlertList.get(instaAlertList.size() - 1);
        assertThat(testInstaAlert.getInstaAlertId()).isEqualTo(UPDATED_INSTA_ALERT_ID);
        assertThat(testInstaAlert.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testInstaAlert.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testInstaAlert.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testInstaAlert.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testInstaAlert.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testInstaAlert.getPoolingAccountNumber()).isEqualTo(UPDATED_POOLING_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testInstaAlert.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testInstaAlert.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testInstaAlert.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testInstaAlert.getVaNumber()).isEqualTo(UPDATED_VA_NUMBER);
        assertThat(testInstaAlert.getUtrNo()).isEqualTo(UPDATED_UTR_NO);
        assertThat(testInstaAlert.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testInstaAlert.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testInstaAlert.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testInstaAlert.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testInstaAlert.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testInstaAlert.getDataKey()).isEqualTo(UPDATED_DATA_KEY);
    }

    @Test
    void putNonExistingInstaAlert() throws Exception {
        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();
        instaAlert.setId(longCount.incrementAndGet());

        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, instaAlertDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchInstaAlert() throws Exception {
        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();
        instaAlert.setId(longCount.incrementAndGet());

        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamInstaAlert() throws Exception {
        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();
        instaAlert.setId(longCount.incrementAndGet());

        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateInstaAlertWithPatch() throws Exception {
        // Initialize the database
        instaAlertRepository.save(instaAlert).block();

        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();

        // Update the instaAlert using partial update
        InstaAlert partialUpdatedInstaAlert = new InstaAlert();
        partialUpdatedInstaAlert.setId(instaAlert.getId());

        partialUpdatedInstaAlert
            .subGrpId(UPDATED_SUB_GRP_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .productCode(UPDATED_PRODUCT_CODE)
            .poolingAccountNumber(UPDATED_POOLING_ACCOUNT_NUMBER)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .creditDate(UPDATED_CREDIT_DATE)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInstaAlert.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInstaAlert))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
        InstaAlert testInstaAlert = instaAlertList.get(instaAlertList.size() - 1);
        assertThat(testInstaAlert.getInstaAlertId()).isEqualTo(DEFAULT_INSTA_ALERT_ID);
        assertThat(testInstaAlert.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testInstaAlert.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testInstaAlert.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testInstaAlert.getCustomerName()).isEqualTo(DEFAULT_CUSTOMER_NAME);
        assertThat(testInstaAlert.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testInstaAlert.getPoolingAccountNumber()).isEqualTo(UPDATED_POOLING_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testInstaAlert.getBatchAmt()).isEqualTo(DEFAULT_BATCH_AMT);
        assertThat(testInstaAlert.getBatchAmtCcd()).isEqualTo(DEFAULT_BATCH_AMT_CCD);
        assertThat(testInstaAlert.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testInstaAlert.getVaNumber()).isEqualTo(DEFAULT_VA_NUMBER);
        assertThat(testInstaAlert.getUtrNo()).isEqualTo(DEFAULT_UTR_NO);
        assertThat(testInstaAlert.getCreditGenerationTime()).isEqualTo(DEFAULT_CREDIT_GENERATION_TIME);
        assertThat(testInstaAlert.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testInstaAlert.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testInstaAlert.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testInstaAlert.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testInstaAlert.getDataKey()).isEqualTo(DEFAULT_DATA_KEY);
    }

    @Test
    void fullUpdateInstaAlertWithPatch() throws Exception {
        // Initialize the database
        instaAlertRepository.save(instaAlert).block();

        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();

        // Update the instaAlert using partial update
        InstaAlert partialUpdatedInstaAlert = new InstaAlert();
        partialUpdatedInstaAlert.setId(instaAlert.getId());

        partialUpdatedInstaAlert
            .instaAlertId(UPDATED_INSTA_ALERT_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .customerCode(UPDATED_CUSTOMER_CODE)
            .customerName(UPDATED_CUSTOMER_NAME)
            .productCode(UPDATED_PRODUCT_CODE)
            .poolingAccountNumber(UPDATED_POOLING_ACCOUNT_NUMBER)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .batchAmt(UPDATED_BATCH_AMT)
            .batchAmtCcd(UPDATED_BATCH_AMT_CCD)
            .creditDate(UPDATED_CREDIT_DATE)
            .vaNumber(UPDATED_VA_NUMBER)
            .utrNo(UPDATED_UTR_NO)
            .creditGenerationTime(UPDATED_CREDIT_GENERATION_TIME)
            .remitterName(UPDATED_REMITTER_NAME)
            .remitterAccountNumber(UPDATED_REMITTER_ACCOUNT_NUMBER)
            .ifscCode(UPDATED_IFSC_CODE)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY)
            .dataKey(UPDATED_DATA_KEY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedInstaAlert.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedInstaAlert))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
        InstaAlert testInstaAlert = instaAlertList.get(instaAlertList.size() - 1);
        assertThat(testInstaAlert.getInstaAlertId()).isEqualTo(UPDATED_INSTA_ALERT_ID);
        assertThat(testInstaAlert.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testInstaAlert.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testInstaAlert.getCustomerCode()).isEqualTo(UPDATED_CUSTOMER_CODE);
        assertThat(testInstaAlert.getCustomerName()).isEqualTo(UPDATED_CUSTOMER_NAME);
        assertThat(testInstaAlert.getProductCode()).isEqualTo(UPDATED_PRODUCT_CODE);
        assertThat(testInstaAlert.getPoolingAccountNumber()).isEqualTo(UPDATED_POOLING_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testInstaAlert.getBatchAmt()).isEqualTo(UPDATED_BATCH_AMT);
        assertThat(testInstaAlert.getBatchAmtCcd()).isEqualTo(UPDATED_BATCH_AMT_CCD);
        assertThat(testInstaAlert.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testInstaAlert.getVaNumber()).isEqualTo(UPDATED_VA_NUMBER);
        assertThat(testInstaAlert.getUtrNo()).isEqualTo(UPDATED_UTR_NO);
        assertThat(testInstaAlert.getCreditGenerationTime()).isEqualTo(UPDATED_CREDIT_GENERATION_TIME);
        assertThat(testInstaAlert.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testInstaAlert.getRemitterAccountNumber()).isEqualTo(UPDATED_REMITTER_ACCOUNT_NUMBER);
        assertThat(testInstaAlert.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testInstaAlert.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testInstaAlert.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
        assertThat(testInstaAlert.getDataKey()).isEqualTo(UPDATED_DATA_KEY);
    }

    @Test
    void patchNonExistingInstaAlert() throws Exception {
        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();
        instaAlert.setId(longCount.incrementAndGet());

        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, instaAlertDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchInstaAlert() throws Exception {
        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();
        instaAlert.setId(longCount.incrementAndGet());

        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamInstaAlert() throws Exception {
        int databaseSizeBeforeUpdate = instaAlertRepository.findAll().collectList().block().size();
        instaAlert.setId(longCount.incrementAndGet());

        // Create the InstaAlert
        InstaAlertDTO instaAlertDTO = instaAlertMapper.toDto(instaAlert);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(instaAlertDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the InstaAlert in the database
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteInstaAlert() {
        // Initialize the database
        instaAlertRepository.save(instaAlert).block();

        int databaseSizeBeforeDelete = instaAlertRepository.findAll().collectList().block().size();

        // Delete the instaAlert
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, instaAlert.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<InstaAlert> instaAlertList = instaAlertRepository.findAll().collectList().block();
        assertThat(instaAlertList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
