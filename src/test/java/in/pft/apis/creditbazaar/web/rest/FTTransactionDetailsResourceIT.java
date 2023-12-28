package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.FTTransactionDetails;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.FTTransactionDetailsRepository;
import in.pft.apis.creditbazaar.service.FTTransactionDetailsService;
import in.pft.apis.creditbazaar.service.dto.FTTransactionDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.FTTransactionDetailsMapper;
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
 * Integration tests for the {@link FTTransactionDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FTTransactionDetailsResourceIT {

    private static final Long DEFAULT_TRNX_DETAILS_ID = 1L;
    private static final Long UPDATED_TRNX_DETAILS_ID = 2L;

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

    private static final String ENTITY_API_URL = "/api/ft-transaction-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FTTransactionDetailsRepository fTTransactionDetailsRepository;

    @Mock
    private FTTransactionDetailsRepository fTTransactionDetailsRepositoryMock;

    @Autowired
    private FTTransactionDetailsMapper fTTransactionDetailsMapper;

    @Mock
    private FTTransactionDetailsService fTTransactionDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FTTransactionDetails fTTransactionDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FTTransactionDetails createEntity(EntityManager em) {
        FTTransactionDetails fTTransactionDetails = new FTTransactionDetails()
            .trnxDetailsId(DEFAULT_TRNX_DETAILS_ID)
            .transactionID(DEFAULT_TRANSACTION_ID)
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
        return fTTransactionDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FTTransactionDetails createUpdatedEntity(EntityManager em) {
        FTTransactionDetails fTTransactionDetails = new FTTransactionDetails()
            .trnxDetailsId(UPDATED_TRNX_DETAILS_ID)
            .transactionID(UPDATED_TRANSACTION_ID)
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
        return fTTransactionDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FTTransactionDetails.class).block();
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
        fTTransactionDetails = createEntity(em);
    }

    @Test
    void createFTTransactionDetails() throws Exception {
        int databaseSizeBeforeCreate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        FTTransactionDetails testFTTransactionDetails = fTTransactionDetailsList.get(fTTransactionDetailsList.size() - 1);
        assertThat(testFTTransactionDetails.getTrnxDetailsId()).isEqualTo(DEFAULT_TRNX_DETAILS_ID);
        assertThat(testFTTransactionDetails.getTransactionID()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testFTTransactionDetails.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testFTTransactionDetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFTTransactionDetails.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFTTransactionDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testFTTransactionDetails.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testFTTransactionDetails.getBeneficiaryIFSC()).isEqualTo(DEFAULT_BENEFICIARY_IFSC);
        assertThat(testFTTransactionDetails.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testFTTransactionDetails.getBeneficiaryAddress()).isEqualTo(DEFAULT_BENEFICIARY_ADDRESS);
        assertThat(testFTTransactionDetails.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testFTTransactionDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testFTTransactionDetails.getTransactionRefNo()).isEqualTo(DEFAULT_TRANSACTION_REF_NO);
        assertThat(testFTTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(DEFAULT_TRNX_META_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTransactionDateTime()).isEqualTo(DEFAULT_TRANSACTION_DATE_TIME);
    }

    @Test
    void createFTTransactionDetailsWithExistingId() throws Exception {
        // Create the FTTransactionDetails with an existing ID
        fTTransactionDetails.setId(1L);
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        int databaseSizeBeforeCreate = fTTransactionDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTrnxDetailsIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTrnxDetailsId(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionIDIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTransactionID(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDebitAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setDebitAccountNumber(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCreditAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setCreditAccountNumber(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRemitterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setRemitterName(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setAmount(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setCurrency(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTransactionType(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPaymentDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setPaymentDescription(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBeneficiaryIFSCIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setBeneficiaryIFSC(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBeneficiaryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setBeneficiaryName(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkBeneficiaryAddressIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setBeneficiaryAddress(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkEmailIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setEmailId(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMobileNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setMobileNo(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionRefNoIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTransactionRefNo(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTrnxResourceDataStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTrnxResourceDataStatus(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTrnxMetaDataStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTrnxMetaDataStatus(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fTTransactionDetailsRepository.findAll().collectList().block().size();
        // set the field null
        fTTransactionDetails.setTransactionDateTime(null);

        // Create the FTTransactionDetails, which fails.
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllFTTransactionDetails() {
        // Initialize the database
        fTTransactionDetailsRepository.save(fTTransactionDetails).block();

        // Get all the fTTransactionDetailsList
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
            .value(hasItem(fTTransactionDetails.getId().intValue()))
            .jsonPath("$.[*].trnxDetailsId")
            .value(hasItem(DEFAULT_TRNX_DETAILS_ID.intValue()))
            .jsonPath("$.[*].transactionID")
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
    void getAllFTTransactionDetailsWithEagerRelationshipsIsEnabled() {
        when(fTTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(fTTransactionDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllFTTransactionDetailsWithEagerRelationshipsIsNotEnabled() {
        when(fTTransactionDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(fTTransactionDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getFTTransactionDetails() {
        // Initialize the database
        fTTransactionDetailsRepository.save(fTTransactionDetails).block();

        // Get the fTTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, fTTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(fTTransactionDetails.getId().intValue()))
            .jsonPath("$.trnxDetailsId")
            .value(is(DEFAULT_TRNX_DETAILS_ID.intValue()))
            .jsonPath("$.transactionID")
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
    void getNonExistingFTTransactionDetails() {
        // Get the fTTransactionDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFTTransactionDetails() throws Exception {
        // Initialize the database
        fTTransactionDetailsRepository.save(fTTransactionDetails).block();

        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the fTTransactionDetails
        FTTransactionDetails updatedFTTransactionDetails = fTTransactionDetailsRepository.findById(fTTransactionDetails.getId()).block();
        updatedFTTransactionDetails
            .trnxDetailsId(UPDATED_TRNX_DETAILS_ID)
            .transactionID(UPDATED_TRANSACTION_ID)
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
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(updatedFTTransactionDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fTTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        FTTransactionDetails testFTTransactionDetails = fTTransactionDetailsList.get(fTTransactionDetailsList.size() - 1);
        assertThat(testFTTransactionDetails.getTrnxDetailsId()).isEqualTo(UPDATED_TRNX_DETAILS_ID);
        assertThat(testFTTransactionDetails.getTransactionID()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFTTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFTTransactionDetails.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFTTransactionDetails.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFTTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testFTTransactionDetails.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testFTTransactionDetails.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFTTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFTTransactionDetails.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFTTransactionDetails.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFTTransactionDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testFTTransactionDetails.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFTTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
    }

    @Test
    void putNonExistingFTTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        fTTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, fTTransactionDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFTTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        fTTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFTTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        fTTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFTTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        fTTransactionDetailsRepository.save(fTTransactionDetails).block();

        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the fTTransactionDetails using partial update
        FTTransactionDetails partialUpdatedFTTransactionDetails = new FTTransactionDetails();
        partialUpdatedFTTransactionDetails.setId(fTTransactionDetails.getId());

        partialUpdatedFTTransactionDetails
            .transactionID(UPDATED_TRANSACTION_ID)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .beneficiaryAddress(UPDATED_BENEFICIARY_ADDRESS)
            .transactionRefNo(UPDATED_TRANSACTION_REF_NO)
            .trnxMetaDataStatus(UPDATED_TRNX_META_DATA_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFTTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFTTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        FTTransactionDetails testFTTransactionDetails = fTTransactionDetailsList.get(fTTransactionDetailsList.size() - 1);
        assertThat(testFTTransactionDetails.getTrnxDetailsId()).isEqualTo(DEFAULT_TRNX_DETAILS_ID);
        assertThat(testFTTransactionDetails.getTransactionID()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFTTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getRemitterName()).isEqualTo(DEFAULT_REMITTER_NAME);
        assertThat(testFTTransactionDetails.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testFTTransactionDetails.getCurrency()).isEqualTo(DEFAULT_CURRENCY);
        assertThat(testFTTransactionDetails.getTransactionType()).isEqualTo(DEFAULT_TRANSACTION_TYPE);
        assertThat(testFTTransactionDetails.getPaymentDescription()).isEqualTo(DEFAULT_PAYMENT_DESCRIPTION);
        assertThat(testFTTransactionDetails.getBeneficiaryIFSC()).isEqualTo(DEFAULT_BENEFICIARY_IFSC);
        assertThat(testFTTransactionDetails.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testFTTransactionDetails.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFTTransactionDetails.getEmailId()).isEqualTo(DEFAULT_EMAIL_ID);
        assertThat(testFTTransactionDetails.getMobileNo()).isEqualTo(DEFAULT_MOBILE_NO);
        assertThat(testFTTransactionDetails.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFTTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(DEFAULT_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTransactionDateTime()).isEqualTo(DEFAULT_TRANSACTION_DATE_TIME);
    }

    @Test
    void fullUpdateFTTransactionDetailsWithPatch() throws Exception {
        // Initialize the database
        fTTransactionDetailsRepository.save(fTTransactionDetails).block();

        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();

        // Update the fTTransactionDetails using partial update
        FTTransactionDetails partialUpdatedFTTransactionDetails = new FTTransactionDetails();
        partialUpdatedFTTransactionDetails.setId(fTTransactionDetails.getId());

        partialUpdatedFTTransactionDetails
            .trnxDetailsId(UPDATED_TRNX_DETAILS_ID)
            .transactionID(UPDATED_TRANSACTION_ID)
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
            .uri(ENTITY_API_URL_ID, partialUpdatedFTTransactionDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFTTransactionDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
        FTTransactionDetails testFTTransactionDetails = fTTransactionDetailsList.get(fTTransactionDetailsList.size() - 1);
        assertThat(testFTTransactionDetails.getTrnxDetailsId()).isEqualTo(UPDATED_TRNX_DETAILS_ID);
        assertThat(testFTTransactionDetails.getTransactionID()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testFTTransactionDetails.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testFTTransactionDetails.getRemitterName()).isEqualTo(UPDATED_REMITTER_NAME);
        assertThat(testFTTransactionDetails.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testFTTransactionDetails.getCurrency()).isEqualTo(UPDATED_CURRENCY);
        assertThat(testFTTransactionDetails.getTransactionType()).isEqualTo(UPDATED_TRANSACTION_TYPE);
        assertThat(testFTTransactionDetails.getPaymentDescription()).isEqualTo(UPDATED_PAYMENT_DESCRIPTION);
        assertThat(testFTTransactionDetails.getBeneficiaryIFSC()).isEqualTo(UPDATED_BENEFICIARY_IFSC);
        assertThat(testFTTransactionDetails.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testFTTransactionDetails.getBeneficiaryAddress()).isEqualTo(UPDATED_BENEFICIARY_ADDRESS);
        assertThat(testFTTransactionDetails.getEmailId()).isEqualTo(UPDATED_EMAIL_ID);
        assertThat(testFTTransactionDetails.getMobileNo()).isEqualTo(UPDATED_MOBILE_NO);
        assertThat(testFTTransactionDetails.getTransactionRefNo()).isEqualTo(UPDATED_TRANSACTION_REF_NO);
        assertThat(testFTTransactionDetails.getTrnxResourceDataStatus()).isEqualTo(UPDATED_TRNX_RESOURCE_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTrnxMetaDataStatus()).isEqualTo(UPDATED_TRNX_META_DATA_STATUS);
        assertThat(testFTTransactionDetails.getTransactionDateTime()).isEqualTo(UPDATED_TRANSACTION_DATE_TIME);
    }

    @Test
    void patchNonExistingFTTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        fTTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, fTTransactionDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFTTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        fTTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFTTransactionDetails() throws Exception {
        int databaseSizeBeforeUpdate = fTTransactionDetailsRepository.findAll().collectList().block().size();
        fTTransactionDetails.setId(longCount.incrementAndGet());

        // Create the FTTransactionDetails
        FTTransactionDetailsDTO fTTransactionDetailsDTO = fTTransactionDetailsMapper.toDto(fTTransactionDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(fTTransactionDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FTTransactionDetails in the database
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFTTransactionDetails() {
        // Initialize the database
        fTTransactionDetailsRepository.save(fTTransactionDetails).block();

        int databaseSizeBeforeDelete = fTTransactionDetailsRepository.findAll().collectList().block().size();

        // Delete the fTTransactionDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, fTTransactionDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FTTransactionDetails> fTTransactionDetailsList = fTTransactionDetailsRepository.findAll().collectList().block();
        assertThat(fTTransactionDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
