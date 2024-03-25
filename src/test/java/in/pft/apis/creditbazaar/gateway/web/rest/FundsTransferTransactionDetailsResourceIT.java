package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetails;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.FundsTransferTransactionDetailsRepository;
import in.pft.apis.creditbazaar.gateway.service.FundsTransferTransactionDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FundsTransferTransactionDetailsMapper;
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
 * Integration tests for the {@link FundsTransferTransactionDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FundsTransferTransactionDetailsResourceIT {

    private static final Long DEFAULT_FT_TRNX_DETAILS_ID = 1L;
    private static final Long UPDATED_FT_TRNX_DETAILS_ID = 2L;

    private static final String DEFAULT_FT_TRNX_DETAILS_ULID = "AAAAAAAAAA";
    private static final String UPDATED_FT_TRNX_DETAILS_ULID = "BBBBBBBBBB";

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

    private static final String DEFAULT_TRANSACTION_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_RESOURCE_DATA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_RESOURCE_DATA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TRNX_META_DATA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRNX_META_DATA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_DATE_TIME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/funds-transfer-transaction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FundsTransferTransactionDetailsRepository fundsTransferTransactionDetailsRepository;

    @Mock
    private FundsTransferTransactionDetailsRepository fundsTransferTransactionDetailsRepositoryMock;

    @Autowired
    private FundsTransferTransactionDetailsMapper fundsTransferTransactionDetailsMapper;

    @Mock
    private FundsTransferTransactionDetailsService fundsTransferTransactionDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FundsTransferTransactionDetails fundsTransferTransactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsTransferTransactionDetails createEntity(EntityManager em) {
        FundsTransferTransactionDetails fundsTransferTransactionDetails = new FundsTransferTransactionDetails()
            .ftTrnxDetailsId(DEFAULT_FT_TRNX_DETAILS_ID)
            .ftTrnxDetailsUlid(DEFAULT_FT_TRNX_DETAILS_ULID)
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
            .transactionRefNo(DEFAULT_TRANSACTION_REF_NO)
            .trnxResourceDataStatus(DEFAULT_TRNX_RESOURCE_DATA_STATUS)
            .trnxMetaDataStatus(DEFAULT_TRNX_META_DATA_STATUS)
            .transactionDateTime(DEFAULT_TRANSACTION_DATE_TIME);
        return fundsTransferTransactionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsTransferTransactionDetails createUpdatedEntity(EntityManager em) {
        FundsTransferTransactionDetails fundsTransferTransactionDetails = new FundsTransferTransactionDetails()
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .ftTrnxDetailsUlid(UPDATED_FT_TRNX_DETAILS_ULID)
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
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME);
        return fundsTransferTransactionDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FundsTransferTransactionDetails.class).block();
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
        fundsTransferTransactionDetails = createEntity(em);
    }

    @Test
    void createFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FundsTransferTransactionDetails testFundsTransferTransactionDetails = fundsTransferTransactionDetailsList.get(
            fundsTransferTransactionDetailsList.size() - 1
        );
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsId()).isEqualTo(DEFAULT_FT_TRNX_DETAILS_ID);
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsUlid()).isEqualTo(DEFAULT_FT_TRNX_DETAILS_ULID);
        assertThat(testFundsTransferTransactionDetails.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testFundsTransferTransactionDetails.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testFundsTransferTransactionDetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFundsTransferTransactionDetails.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFundsTransferTransactionDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testFundsTransferTransactionDetails.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryIFSC()).isEqualTo(DEFAULT_BENEFICIARY_IFSC);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryAddress()).isEqualTo(DEFAULT_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransferTransactionDetails.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testFundsTransferTransactionDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testFundsTransferTransactionDetails.getTransactionRefNo()).isEqualTo(DEFAULT_TRANSACTION_REF_NO);
        assertThat(testFundsTransferTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(DEFAULT_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTransactionDateTime()).isEqualTo(DEFAULT_TRANSACTION_DATE_TIME);
    }

    @Test
    void createFundsTransferTransactionDetailsWithExistingId() throws Exception {
        // Create the FundsTransferTransactionDetails with an existing ID
        fundsTransferTransactionDetails.setId(1L);
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        int databaseSizeBeforeCreate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTransactionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setTransactionId(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDebitAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setDebitAccountNumber(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreditAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setCreditAccountNumber(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRemitterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setRemitterName(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setAmount(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setCurrency(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setTransactionType(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPaymentDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setPaymentDescription(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBeneficiaryIFSCIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setBeneficiaryIFSC(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBeneficiaryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setBeneficiaryName(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBeneficiaryAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setBeneficiaryAddress(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEmailIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setEmailId(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMobileNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setMobileNo(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionRefNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setTransactionRefNo(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTrnxResourceDataStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setTrnxResourceDataStatus(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTrnxMetaDataStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setTrnxMetaDataStatus(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fundsTransferTransactionDetails.setTransactionDateTime(null);

        // Create the FundsTransferTransactionDetails, which fails.
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFundsTransferTransactionDetails() {
        // Initialize the database
        fundsTransferTransactionDetailsRepository.save(fundsTransferTransactionDetails).block();

        // Get all the fundsTransferTransactionDetailsList
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
            .value(hasItem(fundsTransferTransactionDetails.getId().intValue()))
            .jsonPath("$.[*].ftTrnxDetailsId")
            .value(hasItem(DEFAULT_FT_TRNX_DETAILS_ID.intValue()))
            .jsonPath("$.[*].ftTrnxDetailsUlid")
            .value(hasItem(DEFAULT_FT_TRNX_DETAILS_ULID))
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
            .jsonPath("$.[*].transactionRefNo")
            .value(hasItem(DEFAULT_TRANSACTION_REF_NO))
            .jsonPath("$.[*].trnxResourceDataStatus")
            .value(hasItem(DEFAULT_TRNX_RESOURCE_DATA_STATUS))
            .jsonPath("$.[*].trnxMetaDataStatus")
            .value(hasItem(DEFAULT_TRNX_META_DATA_STATUS))
            .jsonPath("$.[*].transactionDateTime")
            .value(hasItem(DEFAULT_TRANSACTION_DATE_TIME));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFundsTransferTransactionDetailsWithEagerRelationshipsIsEnabled() {
        when(fundsTransferTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(fundsTransferTransactionDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFundsTransferTransactionDetailsWithEagerRelationshipsIsNotEnabled() {
        when(fundsTransferTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(fundsTransferTransactionDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getFundsTransferTransactionDetails() {
        // Initialize the database
        fundsTransferTransactionDetailsRepository.save(fundsTransferTransactionDetails).block();

        // Get the fundsTransferTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, fundsTransferTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(fundsTransferTransactionDetails.getId().intValue()))
            .jsonPath("$.ftTrnxDetailsId")
            .value(is(DEFAULT_FT_TRNX_DETAILS_ID.intValue()))
            .jsonPath("$.ftTrnxDetailsUlid")
            .value(is(DEFAULT_FT_TRNX_DETAILS_ULID))
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
            .jsonPath("$.transactionRefNo")
            .value(is(DEFAULT_TRANSACTION_REF_NO))
            .jsonPath("$.trnxResourceDataStatus")
            .value(is(DEFAULT_TRNX_RESOURCE_DATA_STATUS))
            .jsonPath("$.trnxMetaDataStatus")
            .value(is(DEFAULT_TRNX_META_DATA_STATUS))
            .jsonPath("$.transactionDateTime")
            .value(is(DEFAULT_TRANSACTION_DATE_TIME));
    }

    @Test
    void getNonExistingFundsTransferTransactionDetails() {
        // Get the fundsTransferTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFundsTransferTransactionDetails() throws Exception {
        // Initialize the database
        fundsTransferTransactionDetailsRepository.save(fundsTransferTransactionDetails).block();

        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the fundsTransferTransactionDetails
        FundsTransferTransactionDetails updatedFundsTransferTransactionDetails = fundsTransferTransactionDetailsRepository
            .findById(fundsTransferTransactionDetails.getId())
            .block();
        updatedFundsTransferTransactionDetails
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .ftTrnxDetailsUlid(UPDATED_FT_TRNX_DETAILS_ULID)
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
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME);
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            updatedFundsTransferTransactionDetails
        );

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fundsTransferTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        FundsTransferTransactionDetails testFundsTransferTransactionDetails = fundsTransferTransactionDetailsList.get(
            fundsTransferTransactionDetailsList.size() - 1
        );
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsId()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ID);
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsUlid()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ULID);
        assertThat(testFundsTransferTransactionDetails.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFundsTransferTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFundsTransferTransactionDetails.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFundsTransferTransactionDetails.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFundsTransferTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testFundsTransferTransactionDetails.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransferTransactionDetails.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFundsTransferTransactionDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testFundsTransferTransactionDetails.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFundsTransferTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
    }

    @Test
    void putNonExistingFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        fundsTransferTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fundsTransferTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        fundsTransferTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        fundsTransferTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFundsTransferTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        fundsTransferTransactionDetailsRepository.save(fundsTransferTransactionDetails).block();

        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the fundsTransferTransactionDetails using partial update
        FundsTransferTransactionDetails partialUpdatedFundsTransferTransactionDetails = new FundsTransferTransactionDetails();
        partialUpdatedFundsTransferTransactionDetails.setId(fundsTransferTransactionDetails.getId());

        partialUpdatedFundsTransferTransactionDetails
            .transactionId(UPDATED_TRANSACTION_ID)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .remitterName(UPDATED_REMITTER_NAME)
            .beneficiaryIFSC(UPDATED_BENEFICIARY_IFSC)
            .emailId(UPDATED_EMAIL_ID)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFundsTransferTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFundsTransferTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        FundsTransferTransactionDetails testFundsTransferTransactionDetails = fundsTransferTransactionDetailsList.get(
            fundsTransferTransactionDetailsList.size() - 1
        );
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsId()).isEqualTo(DEFAULT_FT_TRNX_DETAILS_ID);
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsUlid()).isEqualTo(DEFAULT_FT_TRNX_DETAILS_ULID);
        assertThat(testFundsTransferTransactionDetails.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFundsTransferTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFundsTransferTransactionDetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFundsTransferTransactionDetails.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFundsTransferTransactionDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testFundsTransferTransactionDetails.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryAddress()).isEqualTo(DEFAULT_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransferTransactionDetails.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFundsTransferTransactionDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testFundsTransferTransactionDetails.getTransactionRefNo()).isEqualTo(DEFAULT_TRANSACTION_REF_NO);
        assertThat(testFundsTransferTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(DEFAULT_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
    }

    @Test
    void fullUpdateFundsTransferTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        fundsTransferTransactionDetailsRepository.save(fundsTransferTransactionDetails).block();

        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the fundsTransferTransactionDetails using partial update
        FundsTransferTransactionDetails partialUpdatedFundsTransferTransactionDetails = new FundsTransferTransactionDetails();
        partialUpdatedFundsTransferTransactionDetails.setId(fundsTransferTransactionDetails.getId());

        partialUpdatedFundsTransferTransactionDetails
            .ftTrnxDetailsId(UPDATED_FT_TRNX_DETAILS_ID)
            .ftTrnxDetailsUlid(UPDATED_FT_TRNX_DETAILS_ULID)
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
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxResourceDataStatus(UPDATED_TRNX_RESOURCE_DATA_STATUS)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS)
            .transactionDateTime(UPDATED_TRANSACTION_DATE_TIME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFundsTransferTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFundsTransferTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        FundsTransferTransactionDetails testFundsTransferTransactionDetails = fundsTransferTransactionDetailsList.get(
            fundsTransferTransactionDetailsList.size() - 1
        );
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsId()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ID);
        assertThat(testFundsTransferTransactionDetails.getFtTrnxDetailsUlid()).isEqualTo(UPDATED_FT_TRNX_DETAILS_ULID);
        assertThat(testFundsTransferTransactionDetails.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFundsTransferTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFundsTransferTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFundsTransferTransactionDetails.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFundsTransferTransactionDetails.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFundsTransferTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testFundsTransferTransactionDetails.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFundsTransferTransactionDetails.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFundsTransferTransactionDetails.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFundsTransferTransactionDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testFundsTransferTransactionDetails.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFundsTransferTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFundsTransferTransactionDetails.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
    }

    @Test
    void patchNonExistingFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        fundsTransferTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, fundsTransferTransactionDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        fundsTransferTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFundsTransferTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();
        fundsTransferTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FundsTransferTransactionDetails
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO = fundsTransferTransactionDetailsMapper.toDto(
            fundsTransferTransactionDetails
        );

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fundsTransferTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FundsTransferTransactionDetails in the database
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFundsTransferTransactionDetails() {
        // Initialize the database
        fundsTransferTransactionDetailsRepository.save(fundsTransferTransactionDetails).block();

        int databaseSizeBeforeDelete = fundsTransferTransactionDetailsRepository.findAll().collectList().block().size();

        // Delete the fundsTransferTransactionDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, fundsTransferTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FundsTransferTransactionDetails> fundsTransferTransactionDetailsList = fundsTransferTransactionDetailsRepository
            .findAll()
            .collectList()
            .block();
        assertThat(fundsTransferTransactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
