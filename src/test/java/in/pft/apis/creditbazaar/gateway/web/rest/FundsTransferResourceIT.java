package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.FundsTransfer;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.FundsTransferRepository;
import in.pft.apis.creditbazaar.gateway.service.FundsTransferService;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FundsTransferMapper;
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
 * Integration tests for the {@link FundsTransferResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FundsTransferResourceIT {

    private static final String DEFAULT_FUNDS_TRANSFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FUNDS_TRANSFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FUNDS_TRANSFER_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_FUNDS_TRANSFER_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FIN_REQ_ID = "AAAAAAAAAA";
    private static final String UPDATED_FIN_REQ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_GRP_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GRP_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_TRANSACTION_ID = 1L;
    private static final Long UPDATED_TRANSACTION_ID = 2L;

    private static final String DEFAULT_DEBIT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REMITTER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    private static final String DEFAULT_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYMENT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_IFSC = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_IFSC = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_ID = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_MOBILE_NO = 1L;
    private static final Long UPDATED_MOBILE_NO = 2L;

    private static final String DEFAULT_MESSAGE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_META_DATA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_META_DATA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_META_DATA_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_META_DATA_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_META_DATA_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_META_DATA_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_META_DATA_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_META_DATA_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_RESOURCE_DATA_BENEFICIARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_RESOURCE_DATA_BENEFICIARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_RESOURCE_DATA_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_RESOURCE_DATA_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_RESOURCE_DATA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_RESOURCE_DATA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FUNDS_TRANSFER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_FUNDS_TRANSFER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LASTUPDATED_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LASTUPDATED_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/funds-transfers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FundsTransferRepository fundsTransferRepository;

    @Mock
    private FundsTransferRepository fundsTransferRepositoryMock;

    @Autowired
    private FundsTransferMapper fundsTransferMapper;

    @Mock
    private FundsTransferService fundsTransferServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FundsTransfer fundsTransfer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsTransfer createEntity(EntityManager em) {
        FundsTransfer fundsTransfer = new FundsTransfer()
            .fundsTransferId(DEFAULT_FUNDS_TRANSFER_ID)
            .fundsTransferRefNo(DEFAULT_FUNDS_TRANSFER_REF_NO)
            .finReqId(DEFAULT_FIN_REQ_ID)
            .subGrpId(DEFAULT_SUB_GRP_ID)
            .transactionId(DEFAULT_TRANSACTION_ID)
            .debitAccountNumber(DEFAULT_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(DEFAULT_CREDIT_ACCOUNT_NUMBER)
            .remitterName(DEFAULT_REMITTER_NAME)
            .amount(DEFAULT_AMOUNT)
            .currency(DEFAULT_CURRENCY)
            .transactionType(DEFAULT_TRANSACTION_TYPE)
            .paymentDescription(DEFAULT_PAYMENT_DESCRIPTION)
            .beneficiaryIFSC(DEFAULT_BENEFICIARY_IFSC)
            .beneficiaryName(DEFAULT_BENEFICIARY_NAME)
            .beneficiaryAddress(DEFAULT_BENEFICIARY_ADDRESS)
            .emailId(DEFAULT_EMAIL_ID)
            .mobileNo(DEFAULT_MOBILE_NO)
            .messageType(DEFAULT_MESSAGE_TYPE)
            .transactionDateTime(DEFAULT_TRANSACTION_DATE_TIME)
            .transactionRefNo(DEFAULT_TRANSACTION_REF_NO)
            .trnxMetaDataStatus(DEFAULT_TRNX_META_DATA_STATUS)
            .trnxMetaDataMessage(DEFAULT_TRNX_META_DATA_MESSAGE)
            .trnxMetaDataVersion(DEFAULT_TRNX_META_DATA_VERSION)
            .trnxMetaDataTime(DEFAULT_TRNX_META_DATA_TIME)
            .trnxResourceDataBeneficiaryName(DEFAULT_TRNX_RESOURCE_DATA_BENEFICIARY_NAME)
            .trnxResourceDataTransactionId(DEFAULT_TRNX_RESOURCE_DATA_TRANSACTION_ID)
            .trnxResourceDataStatus(DEFAULT_TRNX_RESOURCE_DATA_STATUS)
            .fundsTransferStatus(DEFAULT_FUNDS_TRANSFER_STATUS)
            .lastupdatedDateTime(DEFAULT_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return fundsTransfer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsTransfer createUpdatedEntity(EntityManager em) {
        FundsTransfer fundsTransfer = new FundsTransfer()
            .fundsTransferId(UPDATED_FUNDS_TRANSFER_ID)
            .fundsTransferRefNo(UPDATED_FUNDS_TRANSFER_REF_NO)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .remitterName(UPDATED_REMITTER_NAME)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .beneficiaryIFSC(UPDATED_BENEFICIARY_IFSC)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAddress(UPDATED_BENEFICIARY_ADDRESS)
            .emailId(UPDATED_EMAIL_ID)
            .mobileNo(UPDATED_MOBILE_NO)
            .messageType(UPDATED_MESSAGE_TYPE)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME)
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS)
            .trnxMetaDataMessage(UPDATED_TRNX_META_DATA_MESSAGE)
            .trnxMetaDataVersion(UPDATED_TRNX_META_DATA_VERSION)
            .trnxMetaDataTime(UPDATED_TRNX_META_DATA_TIME)
            .trnxResourceDataBeneficiaryName(UPDATED_TRNX_RESOURCE_DATA_BENEFICIARY_NAME)
            .trnxResourceDataTransactionId(UPDATED_TRNX_RESOURCE_DATA_TRANSACTION_ID)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .fundsTransferStatus(UPDATED_FUNDS_TRANSFER_STATUS)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        return fundsTransfer;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FundsTransfer.class).block();
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
        fundsTransfer = createEntity(em);
    }

    @Test
    void createFundsTransfer() throws Exception {
        int databaseSizeBeforeCreate = fundsTransferRepository.findAll().collectList().block().size();
        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeCreate + 1);
        FundsTransfer testFundsTransfer = fundsTransferList.get(fundsTransferList.size() - 1);
        assertThat(testFundsTransfer.getFundsTransferId()).isEqualTo(DEFAULT_FUNDS_TRANSFER_ID);
        assertThat(testFundsTransfer.getFundsTransferRefNo()).isEqualTo(DEFAULT_FUNDS_TRANSFER_REF_NO);
        assertThat(testFundsTransfer.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testFundsTransfer.getSubGrpId()).isEqualTo(DEFAULT_SUB_GRP_ID);
        assertThat(testFundsTransfer.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testFundsTransfer.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testFundsTransfer.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFundsTransfer.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFundsTransfer.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testFundsTransfer.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransfer.getBeneficiaryIFSC()).isEqualTo(DEFAULT_BENEFICIARY_IFSC);
        assertThat(testFundsTransfer.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getBeneficiaryAddress()).isEqualTo(DEFAULT_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransfer.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testFundsTransfer.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testFundsTransfer.getMessageType()).isEqualTo(DEFAULT_MESSAGE_TYPE);
        assertThat(testFundsTransfer.getTransactionDateTime()).isEqualTo(DEFAULT_TRANSACTION_DATE_TIME);
        assertThat(testFundsTransfer.getTransactionRefNo()).isEqualTo(DEFAULT_TRANSACTION_REF_NO);
        assertThat(testFundsTransfer.getTrnxMetaDataStatus()).isEqualTo(DEFAULT_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransfer.getTrnxMetaDataMessage()).isEqualTo(DEFAULT_TRNX_META_DATA_MESSAGE);
        assertThat(testFundsTransfer.getTrnxMetaDataVersion()).isEqualTo(DEFAULT_TRNX_META_DATA_VERSION);
        assertThat(testFundsTransfer.getTrnxMetaDataTime()).isEqualTo(DEFAULT_TRNX_META_DATA_TIME);
        assertThat(testFundsTransfer.getTrnxResourceDataBeneficiaryName()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getTrnxResourceDataTransactionId()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testFundsTransfer.getTrnxResourceDataStatus()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransfer.getFundsTransferStatus()).isEqualTo(DEFAULT_FUNDS_TRANSFER_STATUS);
        assertThat(testFundsTransfer.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testFundsTransfer.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    void createFundsTransferWithExistingId() throws Exception {
        // Create the FundsTransfer with an existing ID
        fundsTransfer.setId(1L);
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        int databaseSizeBeforeCreate = fundsTransferRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTransactionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransfer.setTransactionId(null);

        // Create the FundsTransfer, which fails.
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastupdatedDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransfer.setLastupdatedDateTime(null);

        // Create the FundsTransfer, which fails.
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransfer.setLastUpdatedBy(null);

        // Create the FundsTransfer, which fails.
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFundsTransfers() {
        // Initialize the database
        fundsTransferRepository.save(fundsTransfer).block();

        // Get all the fundsTransferList
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
            .value(hasItem(fundsTransfer.getId().intValue()))
            .jsonPath("$.[*].fundsTransferId")
            .value(hasItem(DEFAULT_FUNDS_TRANSFER_ID))
            .jsonPath("$.[*].fundsTransferRefNo")
            .value(hasItem(DEFAULT_FUNDS_TRANSFER_REF_NO))
            .jsonPath("$.[*].finReqId")
            .value(hasItem(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.[*].subGrpId")
            .value(hasItem(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID.intValue()))
            .jsonPath("$.[*].debitAccountNumber")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].creditAccountNumber")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].remitterName")
            .value(hasItem(DEFAULT_REMITTER_NAME))
            .jsonPath("$.[*].amount")
            .value(hasItem(DEFAULT_AMOUNT.intValue()))
            .jsonPath("$.[*].currency")
            .value(hasItem(DEFAULT_CURRENCY))
            .jsonPath("$.[*].transactionType")
            .value(hasItem(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.[*].paymentDescription")
            .value(hasItem(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.[*].beneficiaryIFSC")
            .value(hasItem(DEFAULT_BENEFICIARY_IFSC))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].beneficiaryAddress")
            .value(hasItem(DEFAULT_BENEFICIARY_ADDRESS))
            .jsonPath("$.[*].emailId")
            .value(hasItem(DEFAULT_EMAIL_ID))
            .jsonPath("$.[*].mobileNo")
            .value(hasItem(DEFAULT_MOBILE_NO.intValue()))
            .jsonPath("$.[*].messageType")
            .value(hasItem(DEFAULT_MESSAGE_TYPE))
            .jsonPath("$.[*].transactionDateTime")
            .value(hasItem(DEFAULT_TRANSACTION_DATE_TIME))
            .jsonPath("$.[*].transactionRefNo")
            .value(hasItem(DEFAULT_TRANSACTION_REF_NO))
            .jsonPath("$.[*].trnxMetaDataStatus")
            .value(hasItem(DEFAULT_TRNX_META_DATA_STATUS))
            .jsonPath("$.[*].trnxMetaDataMessage")
            .value(hasItem(DEFAULT_TRNX_META_DATA_MESSAGE))
            .jsonPath("$.[*].trnxMetaDataVersion")
            .value(hasItem(DEFAULT_TRNX_META_DATA_VERSION))
            .jsonPath("$.[*].trnxMetaDataTime")
            .value(hasItem(DEFAULT_TRNX_META_DATA_TIME))
            .jsonPath("$.[*].trnxResourceDataBeneficiaryName")
            .value(hasItem(DEFAULT_TRNX_RESOURCE_DATA_BENEFICIARY_NAME))
            .jsonPath("$.[*].trnxResourceDataTransactionId")
            .value(hasItem(DEFAULT_TRNX_RESOURCE_DATA_TRANSACTION_ID))
            .jsonPath("$.[*].trnxResourceDataStatus")
            .value(hasItem(DEFAULT_TRNX_RESOURCE_DATA_STATUS))
            .jsonPath("$.[*].fundsTransferStatus")
            .value(hasItem(DEFAULT_FUNDS_TRANSFER_STATUS))
            .jsonPath("$.[*].lastupdatedDateTime")
            .value(hasItem(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.[*].lastUpdatedBy")
            .value(hasItem(DEFAULT_LAST_UPDATED_BY));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFundsTransfersWithEagerRelationshipsIsEnabled() {
        when(fundsTransferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(fundsTransferServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFundsTransfersWithEagerRelationshipsIsNotEnabled() {
        when(fundsTransferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(fundsTransferRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getFundsTransfer() {
        // Initialize the database
        fundsTransferRepository.save(fundsTransfer).block();

        // Get the fundsTransfer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, fundsTransfer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(fundsTransfer.getId().intValue()))
            .jsonPath("$.fundsTransferId")
            .value(is(DEFAULT_FUNDS_TRANSFER_ID))
            .jsonPath("$.fundsTransferRefNo")
            .value(is(DEFAULT_FUNDS_TRANSFER_REF_NO))
            .jsonPath("$.finReqId")
            .value(is(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.subGrpId")
            .value(is(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.transactionId")
            .value(is(DEFAULT_TRANSACTION_ID.intValue()))
            .jsonPath("$.debitAccountNumber")
            .value(is(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.creditAccountNumber")
            .value(is(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.remitterName")
            .value(is(DEFAULT_REMITTER_NAME))
            .jsonPath("$.amount")
            .value(is(DEFAULT_AMOUNT.intValue()))
            .jsonPath("$.currency")
            .value(is(DEFAULT_CURRENCY))
            .jsonPath("$.transactionType")
            .value(is(DEFAULT_TRANSACTION_TYPE))
            .jsonPath("$.paymentDescription")
            .value(is(DEFAULT_PAYMENT_DESCRIPTION))
            .jsonPath("$.beneficiaryIFSC")
            .value(is(DEFAULT_BENEFICIARY_IFSC))
            .jsonPath("$.beneficiaryName")
            .value(is(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.beneficiaryAddress")
            .value(is(DEFAULT_BENEFICIARY_ADDRESS))
            .jsonPath("$.emailId")
            .value(is(DEFAULT_EMAIL_ID))
            .jsonPath("$.mobileNo")
            .value(is(DEFAULT_MOBILE_NO.intValue()))
            .jsonPath("$.messageType")
            .value(is(DEFAULT_MESSAGE_TYPE))
            .jsonPath("$.transactionDateTime")
            .value(is(DEFAULT_TRANSACTION_DATE_TIME))
            .jsonPath("$.transactionRefNo")
            .value(is(DEFAULT_TRANSACTION_REF_NO))
            .jsonPath("$.trnxMetaDataStatus")
            .value(is(DEFAULT_TRNX_META_DATA_STATUS))
            .jsonPath("$.trnxMetaDataMessage")
            .value(is(DEFAULT_TRNX_META_DATA_MESSAGE))
            .jsonPath("$.trnxMetaDataVersion")
            .value(is(DEFAULT_TRNX_META_DATA_VERSION))
            .jsonPath("$.trnxMetaDataTime")
            .value(is(DEFAULT_TRNX_META_DATA_TIME))
            .jsonPath("$.trnxResourceDataBeneficiaryName")
            .value(is(DEFAULT_TRNX_RESOURCE_DATA_BENEFICIARY_NAME))
            .jsonPath("$.trnxResourceDataTransactionId")
            .value(is(DEFAULT_TRNX_RESOURCE_DATA_TRANSACTION_ID))
            .jsonPath("$.trnxResourceDataStatus")
            .value(is(DEFAULT_TRNX_RESOURCE_DATA_STATUS))
            .jsonPath("$.fundsTransferStatus")
            .value(is(DEFAULT_FUNDS_TRANSFER_STATUS))
            .jsonPath("$.lastupdatedDateTime")
            .value(is(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.lastUpdatedBy")
            .value(is(DEFAULT_LAST_UPDATED_BY));
    }

    @Test
    void getNonExistingFundsTransfer() {
        // Get the fundsTransfer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFundsTransfer() throws Exception {
        // Initialize the database
        fundsTransferRepository.save(fundsTransfer).block();

        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();

        // Update the fundsTransfer
        FundsTransfer updatedFundsTransfer = fundsTransferRepository.findById(fundsTransfer.getId()).block();
        updatedFundsTransfer
            .fundsTransferId(UPDATED_FUNDS_TRANSFER_ID)
            .fundsTransferRefNo(UPDATED_FUNDS_TRANSFER_REF_NO)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .remitterName(UPDATED_REMITTER_NAME)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .beneficiaryIFSC(UPDATED_BENEFICIARY_IFSC)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAddress(UPDATED_BENEFICIARY_ADDRESS)
            .emailId(UPDATED_EMAIL_ID)
            .mobileNo(UPDATED_MOBILE_NO)
            .messageType(UPDATED_MESSAGE_TYPE)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME)
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS)
            .trnxMetaDataMessage(UPDATED_TRNX_META_DATA_MESSAGE)
            .trnxMetaDataVersion(UPDATED_TRNX_META_DATA_VERSION)
            .trnxMetaDataTime(UPDATED_TRNX_META_DATA_TIME)
            .trnxResourceDataBeneficiaryName(UPDATED_TRNX_RESOURCE_DATA_BENEFICIARY_NAME)
            .trnxResourceDataTransactionId(UPDATED_TRNX_RESOURCE_DATA_TRANSACTION_ID)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .fundsTransferStatus(UPDATED_FUNDS_TRANSFER_STATUS)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(updatedFundsTransfer);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fundsTransferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
        FundsTransfer testFundsTransfer = fundsTransferList.get(fundsTransferList.size() - 1);
        assertThat(testFundsTransfer.getFundsTransferId()).isEqualTo(UPDATED_FUNDS_TRANSFER_ID);
        assertThat(testFundsTransfer.getFundsTransferRefNo()).isEqualTo(UPDATED_FUNDS_TRANSFER_REF_NO);
        assertThat(testFundsTransfer.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testFundsTransfer.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testFundsTransfer.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFundsTransfer.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFundsTransfer.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFundsTransfer.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFundsTransfer.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testFundsTransfer.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransfer.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFundsTransfer.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransfer.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFundsTransfer.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testFundsTransfer.getMessageType()).isEqualTo(UPDATED_MESSAGE_TYPE);
        assertThat(testFundsTransfer.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
        assertThat(testFundsTransfer.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFundsTransfer.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransfer.getTrnxMetaDataMessage()).isEqualTo(UPDATED_TRNX_META_DATA_MESSAGE);
        assertThat(testFundsTransfer.getTrnxMetaDataVersion()).isEqualTo(UPDATED_TRNX_META_DATA_VERSION);
        assertThat(testFundsTransfer.getTrnxMetaDataTime()).isEqualTo(UPDATED_TRNX_META_DATA_TIME);
        assertThat(testFundsTransfer.getTrnxResourceDataBeneficiaryName()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getTrnxResourceDataTransactionId()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testFundsTransfer.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransfer.getFundsTransferStatus()).isEqualTo(UPDATED_FUNDS_TRANSFER_STATUS);
        assertThat(testFundsTransfer.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testFundsTransfer.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void putNonExistingFundsTransfer() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();
        fundsTransfer.setId(longCount.incrementAndGet());

        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fundsTransferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFundsTransfer() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();
        fundsTransfer.setId(longCount.incrementAndGet());

        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFundsTransfer() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();
        fundsTransfer.setId(longCount.incrementAndGet());

        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFundsTransferWithPatch() throws Exception {
        // Initialize the database
        fundsTransferRepository.save(fundsTransfer).block();

        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();

        // Update the fundsTransfer using partial update
        FundsTransfer partialUpdatedFundsTransfer = new FundsTransfer();
        partialUpdatedFundsTransfer.setId(fundsTransfer.getId());

        partialUpdatedFundsTransfer
            .fundsTransferRefNo(UPDATED_FUNDS_TRANSFER_REF_NO)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .beneficiaryIFSC(UPDATED_BENEFICIARY_IFSC)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAddress(UPDATED_BENEFICIARY_ADDRESS)
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxMetaDataMessage(UPDATED_TRNX_META_DATA_MESSAGE)
            .trnxMetaDataVersion(UPDATED_TRNX_META_DATA_VERSION)
            .trnxMetaDataTime(UPDATED_TRNX_META_DATA_TIME)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .fundsTransferStatus(UPDATED_FUNDS_TRANSFER_STATUS)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFundsTransfer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFundsTransfer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
        FundsTransfer testFundsTransfer = fundsTransferList.get(fundsTransferList.size() - 1);
        assertThat(testFundsTransfer.getFundsTransferId()).isEqualTo(DEFAULT_FUNDS_TRANSFER_ID);
        assertThat(testFundsTransfer.getFundsTransferRefNo()).isEqualTo(UPDATED_FUNDS_TRANSFER_REF_NO);
        assertThat(testFundsTransfer.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testFundsTransfer.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testFundsTransfer.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFundsTransfer.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testFundsTransfer.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFundsTransfer.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFundsTransfer.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testFundsTransfer.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransfer.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFundsTransfer.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransfer.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testFundsTransfer.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testFundsTransfer.getMessageType()).isEqualTo(DEFAULT_MESSAGE_TYPE);
        assertThat(testFundsTransfer.getTransactionDateTime()).isEqualTo(DEFAULT_TRANSACTION_DATE_TIME);
        assertThat(testFundsTransfer.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFundsTransfer.getTrnxMetaDataStatus()).isEqualTo(DEFAULT_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransfer.getTrnxMetaDataMessage()).isEqualTo(UPDATED_TRNX_META_DATA_MESSAGE);
        assertThat(testFundsTransfer.getTrnxMetaDataVersion()).isEqualTo(UPDATED_TRNX_META_DATA_VERSION);
        assertThat(testFundsTransfer.getTrnxMetaDataTime()).isEqualTo(UPDATED_TRNX_META_DATA_TIME);
        assertThat(testFundsTransfer.getTrnxResourceDataBeneficiaryName()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getTrnxResourceDataTransactionId()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testFundsTransfer.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransfer.getFundsTransferStatus()).isEqualTo(UPDATED_FUNDS_TRANSFER_STATUS);
        assertThat(testFundsTransfer.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testFundsTransfer.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void fullUpdateFundsTransferWithPatch() throws Exception {
        // Initialize the database
        fundsTransferRepository.save(fundsTransfer).block();

        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();

        // Update the fundsTransfer using partial update
        FundsTransfer partialUpdatedFundsTransfer = new FundsTransfer();
        partialUpdatedFundsTransfer.setId(fundsTransfer.getId());

        partialUpdatedFundsTransfer
            .fundsTransferId(UPDATED_FUNDS_TRANSFER_ID)
            .fundsTransferRefNo(UPDATED_FUNDS_TRANSFER_REF_NO)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .transactionId(UPDATED_TRANSACTION_ID)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .remitterName(UPDATED_REMITTER_NAME)
            .amount(UPDATED_AMOUNT)
            .currency(UPDATED_CURRENCY)
            .transactionType(UPDATED_TRANSACTION_TYPE)
            .paymentDescription(UPDATED_PAYMENT_DESCRIPTION)
            .beneficiaryIFSC(UPDATED_BENEFICIARY_IFSC)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAddress(UPDATED_BENEFICIARY_ADDRESS)
            .emailId(UPDATED_EMAIL_ID)
            .mobileNo(UPDATED_MOBILE_NO)
            .messageType(UPDATED_MESSAGE_TYPE)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME)
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS)
            .trnxMetaDataMessage(UPDATED_TRNX_META_DATA_MESSAGE)
            .trnxMetaDataVersion(UPDATED_TRNX_META_DATA_VERSION)
            .trnxMetaDataTime(UPDATED_TRNX_META_DATA_TIME)
            .trnxResourceDataBeneficiaryName(UPDATED_TRNX_RESOURCE_DATA_BENEFICIARY_NAME)
            .trnxResourceDataTransactionId(UPDATED_TRNX_RESOURCE_DATA_TRANSACTION_ID)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .fundsTransferStatus(UPDATED_FUNDS_TRANSFER_STATUS)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFundsTransfer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFundsTransfer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
        FundsTransfer testFundsTransfer = fundsTransferList.get(fundsTransferList.size() - 1);
        assertThat(testFundsTransfer.getFundsTransferId()).isEqualTo(UPDATED_FUNDS_TRANSFER_ID);
        assertThat(testFundsTransfer.getFundsTransferRefNo()).isEqualTo(UPDATED_FUNDS_TRANSFER_REF_NO);
        assertThat(testFundsTransfer.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testFundsTransfer.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testFundsTransfer.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFundsTransfer.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransfer.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFundsTransfer.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFundsTransfer.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFundsTransfer.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testFundsTransfer.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransfer.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFundsTransfer.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransfer.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFundsTransfer.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testFundsTransfer.getMessageType()).isEqualTo(UPDATED_MESSAGE_TYPE);
        assertThat(testFundsTransfer.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
        assertThat(testFundsTransfer.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFundsTransfer.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransfer.getTrnxMetaDataMessage()).isEqualTo(UPDATED_TRNX_META_DATA_MESSAGE);
        assertThat(testFundsTransfer.getTrnxMetaDataVersion()).isEqualTo(UPDATED_TRNX_META_DATA_VERSION);
        assertThat(testFundsTransfer.getTrnxMetaDataTime()).isEqualTo(UPDATED_TRNX_META_DATA_TIME);
        assertThat(testFundsTransfer.getTrnxResourceDataBeneficiaryName()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_BENEFICIARY_NAME);
        assertThat(testFundsTransfer.getTrnxResourceDataTransactionId()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_TRANSACTION_ID);
        assertThat(testFundsTransfer.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransfer.getFundsTransferStatus()).isEqualTo(UPDATED_FUNDS_TRANSFER_STATUS);
        assertThat(testFundsTransfer.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testFundsTransfer.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void patchNonExistingFundsTransfer() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();
        fundsTransfer.setId(longCount.incrementAndGet());

        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, fundsTransferDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFundsTransfer() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();
        fundsTransfer.setId(longCount.incrementAndGet());

        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFundsTransfer() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferRepository.findAll().collectList().block().size();
        fundsTransfer.setId(longCount.incrementAndGet());

        // Create the FundsTransfer
        FundsTransferDTO fundsTransferDTO = fundsTransferMapper.toDto(fundsTransfer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FundsTransfer in the database
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFundsTransfer() {
        // Initialize the database
        fundsTransferRepository.save(fundsTransfer).block();

        int databaseSizeBeforeDelete = fundsTransferRepository.findAll().collectList().block().size();

        // Delete the fundsTransfer
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, fundsTransfer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FundsTransfer> fundsTransferList = fundsTransferRepository.findAll().collectList().block();
        assertThat(fundsTransferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
