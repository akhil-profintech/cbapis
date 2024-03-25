package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.gateway.domain.enumeration.SettlementType;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.ParticipantSettlementRepository;
import in.pft.apis.creditbazaar.gateway.service.ParticipantSettlementService;
import in.pft.apis.creditbazaar.gateway.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.ParticipantSettlementMapper;
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
 * Integration tests for the {@link ParticipantSettlementResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ParticipantSettlementResourceIT {

    private static final Long DEFAULT_PARTICIPANT_SETTLEMENT_ID = 1L;
    private static final Long UPDATED_PARTICIPANT_SETTLEMENT_ID = 2L;

    private static final String DEFAULT_PARTICIPANT_SETTLEMENT_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_PARTICIPANT_SETTLEMENT_ULID_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_PARTICIPANT_ID = 1L;
    private static final Long UPDATED_PARTICIPANT_ID = 2L;

    private static final String DEFAULT_PARTICIPANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PARTICIPANT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GST_ID = "AAAAAAAAAA";
    private static final String UPDATED_GST_ID = "BBBBBBBBBB";

    private static final SettlementType DEFAULT_SETTLEMENT_TYPE = SettlementType.ATSettlement;
    private static final SettlementType UPDATED_SETTLEMENT_TYPE = SettlementType.TPSettlement;

    private static final Long DEFAULT_SETTLEMENT_AMOUNT = 1L;
    private static final Long UPDATED_SETTLEMENT_AMOUNT = 2L;

    private static final String DEFAULT_SETTLEMENT_DATE = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SETTLEMENT_DUE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_DUE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_SETTLEMENT_CONTACT_INFO = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_CONTACT_INFO = "BBBBBBBBBB";

    private static final String DEFAULT_PATRON_OF_PAYMENT = "AAAAAAAAAA";
    private static final String UPDATED_PATRON_OF_PAYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_RECIPIENT_OF_PAYMENT = "AAAAAAAAAA";
    private static final String UPDATED_RECIPIENT_OF_PAYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_SETTLEMENT_METHOD = "AAAAAAAAAA";
    private static final String UPDATED_SETTLEMENT_METHOD = "BBBBBBBBBB";

    private static final Long DEFAULT_TENANT_ID = 1L;
    private static final Long UPDATED_TENANT_ID = 2L;

    private static final String DEFAULT_ACC_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACC_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IFSC_CODE = "AAAAAAAAAA";
    private static final String UPDATED_IFSC_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_ACC_NUMBER = 1L;
    private static final Long UPDATED_ACC_NUMBER = 2L;

    private static final String DEFAULT_DOC_ID = "AAAAAAAAAA";
    private static final String UPDATED_DOC_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/participant-settlements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ParticipantSettlementRepository participantSettlementRepository;

    @Mock
    private ParticipantSettlementRepository participantSettlementRepositoryMock;

    @Autowired
    private ParticipantSettlementMapper participantSettlementMapper;

    @Mock
    private ParticipantSettlementService participantSettlementServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ParticipantSettlement participantSettlement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParticipantSettlement createEntity(EntityManager em) {
        ParticipantSettlement participantSettlement = new ParticipantSettlement()
            .participantSettlementId(DEFAULT_PARTICIPANT_SETTLEMENT_ID)
            .participantSettlementUlidId(DEFAULT_PARTICIPANT_SETTLEMENT_ULID_ID)
            .participantId(DEFAULT_PARTICIPANT_ID)
            .participantName(DEFAULT_PARTICIPANT_NAME)
            .gstId(DEFAULT_GST_ID)
            .settlementType(DEFAULT_SETTLEMENT_TYPE)
            .settlementAmount(DEFAULT_SETTLEMENT_AMOUNT)
            .settlementDate(DEFAULT_SETTLEMENT_DATE)
            .settlementDueDate(DEFAULT_SETTLEMENT_DUE_DATE)
            .settlementContactInfo(DEFAULT_SETTLEMENT_CONTACT_INFO)
            .patronOfPayment(DEFAULT_PATRON_OF_PAYMENT)
            .recipientOfPayment(DEFAULT_RECIPIENT_OF_PAYMENT)
            .settlementMethod(DEFAULT_SETTLEMENT_METHOD)
            .tenantId(DEFAULT_TENANT_ID)
            .accName(DEFAULT_ACC_NAME)
            .ifscCode(DEFAULT_IFSC_CODE)
            .accNumber(DEFAULT_ACC_NUMBER)
            .docId(DEFAULT_DOC_ID);
        return participantSettlement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParticipantSettlement createUpdatedEntity(EntityManager em) {
        ParticipantSettlement participantSettlement = new ParticipantSettlement()
            .participantSettlementId(UPDATED_PARTICIPANT_SETTLEMENT_ID)
            .participantSettlementUlidId(UPDATED_PARTICIPANT_SETTLEMENT_ULID_ID)
            .participantId(UPDATED_PARTICIPANT_ID)
            .participantName(UPDATED_PARTICIPANT_NAME)
            .gstId(UPDATED_GST_ID)
            .settlementType(UPDATED_SETTLEMENT_TYPE)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .settlementDueDate(UPDATED_SETTLEMENT_DUE_DATE)
            .settlementContactInfo(UPDATED_SETTLEMENT_CONTACT_INFO)
            .patronOfPayment(UPDATED_PATRON_OF_PAYMENT)
            .recipientOfPayment(UPDATED_RECIPIENT_OF_PAYMENT)
            .settlementMethod(UPDATED_SETTLEMENT_METHOD)
            .tenantId(UPDATED_TENANT_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .accNumber(UPDATED_ACC_NUMBER)
            .docId(UPDATED_DOC_ID);
        return participantSettlement;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ParticipantSettlement.class).block();
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
        participantSettlement = createEntity(em);
    }

    @Test
    void createParticipantSettlement() throws Exception {
        int databaseSizeBeforeCreate = participantSettlementRepository.findAll().collectList().block().size();
        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeCreate + 1);
        ParticipantSettlement testParticipantSettlement = participantSettlementList.get(participantSettlementList.size() - 1);
        assertThat(testParticipantSettlement.getParticipantSettlementId()).isEqualTo(DEFAULT_PARTICIPANT_SETTLEMENT_ID);
        assertThat(testParticipantSettlement.getParticipantSettlementUlidId()).isEqualTo(DEFAULT_PARTICIPANT_SETTLEMENT_ULID_ID);
        assertThat(testParticipantSettlement.getParticipantId()).isEqualTo(DEFAULT_PARTICIPANT_ID);
        assertThat(testParticipantSettlement.getParticipantName()).isEqualTo(DEFAULT_PARTICIPANT_NAME);
        assertThat(testParticipantSettlement.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testParticipantSettlement.getSettlementType()).isEqualTo(DEFAULT_SETTLEMENT_TYPE);
        assertThat(testParticipantSettlement.getSettlementAmount()).isEqualTo(DEFAULT_SETTLEMENT_AMOUNT);
        assertThat(testParticipantSettlement.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testParticipantSettlement.getSettlementDueDate()).isEqualTo(DEFAULT_SETTLEMENT_DUE_DATE);
        assertThat(testParticipantSettlement.getSettlementContactInfo()).isEqualTo(DEFAULT_SETTLEMENT_CONTACT_INFO);
        assertThat(testParticipantSettlement.getPatronOfPayment()).isEqualTo(DEFAULT_PATRON_OF_PAYMENT);
        assertThat(testParticipantSettlement.getRecipientOfPayment()).isEqualTo(DEFAULT_RECIPIENT_OF_PAYMENT);
        assertThat(testParticipantSettlement.getSettlementMethod()).isEqualTo(DEFAULT_SETTLEMENT_METHOD);
        assertThat(testParticipantSettlement.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testParticipantSettlement.getAccName()).isEqualTo(DEFAULT_ACC_NAME);
        assertThat(testParticipantSettlement.getIfscCode()).isEqualTo(DEFAULT_IFSC_CODE);
        assertThat(testParticipantSettlement.getAccNumber()).isEqualTo(DEFAULT_ACC_NUMBER);
        assertThat(testParticipantSettlement.getDocId()).isEqualTo(DEFAULT_DOC_ID);
    }

    @Test
    void createParticipantSettlementWithExistingId() throws Exception {
        // Create the ParticipantSettlement with an existing ID
        participantSettlement.setId(1L);
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        int databaseSizeBeforeCreate = participantSettlementRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkParticipantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setParticipantId(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkParticipantNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setParticipantName(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGstIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setGstId(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSettlementTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setSettlementType(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSettlementAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setSettlementAmount(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSettlementDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setSettlementDate(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSettlementDueDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setSettlementDueDate(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSettlementContactInfoIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setSettlementContactInfo(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPatronOfPaymentIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setPatronOfPayment(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRecipientOfPaymentIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setRecipientOfPayment(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkSettlementMethodIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setSettlementMethod(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setTenantId(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAccNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setAccName(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkIfscCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setIfscCode(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setAccNumber(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDocIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = participantSettlementRepository.findAll().collectList().block().size();
        // set the field null
        participantSettlement.setDocId(null);

        // Create the ParticipantSettlement, which fails.
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllParticipantSettlements() {
        // Initialize the database
        participantSettlementRepository.save(participantSettlement).block();

        // Get all the participantSettlementList
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
            .value(hasItem(participantSettlement.getId().intValue()))
            .jsonPath("$.[*].participantSettlementId")
            .value(hasItem(DEFAULT_PARTICIPANT_SETTLEMENT_ID.intValue()))
            .jsonPath("$.[*].participantSettlementUlidId")
            .value(hasItem(DEFAULT_PARTICIPANT_SETTLEMENT_ULID_ID))
            .jsonPath("$.[*].participantId")
            .value(hasItem(DEFAULT_PARTICIPANT_ID.intValue()))
            .jsonPath("$.[*].participantName")
            .value(hasItem(DEFAULT_PARTICIPANT_NAME))
            .jsonPath("$.[*].gstId")
            .value(hasItem(DEFAULT_GST_ID))
            .jsonPath("$.[*].settlementType")
            .value(hasItem(DEFAULT_SETTLEMENT_TYPE.toString()))
            .jsonPath("$.[*].settlementAmount")
            .value(hasItem(DEFAULT_SETTLEMENT_AMOUNT.intValue()))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(DEFAULT_SETTLEMENT_DATE))
            .jsonPath("$.[*].settlementDueDate")
            .value(hasItem(DEFAULT_SETTLEMENT_DUE_DATE))
            .jsonPath("$.[*].settlementContactInfo")
            .value(hasItem(DEFAULT_SETTLEMENT_CONTACT_INFO))
            .jsonPath("$.[*].patronOfPayment")
            .value(hasItem(DEFAULT_PATRON_OF_PAYMENT))
            .jsonPath("$.[*].recipientOfPayment")
            .value(hasItem(DEFAULT_RECIPIENT_OF_PAYMENT))
            .jsonPath("$.[*].settlementMethod")
            .value(hasItem(DEFAULT_SETTLEMENT_METHOD))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID.intValue()))
            .jsonPath("$.[*].accName")
            .value(hasItem(DEFAULT_ACC_NAME))
            .jsonPath("$.[*].ifscCode")
            .value(hasItem(DEFAULT_IFSC_CODE))
            .jsonPath("$.[*].accNumber")
            .value(hasItem(DEFAULT_ACC_NUMBER.intValue()))
            .jsonPath("$.[*].docId")
            .value(hasItem(DEFAULT_DOC_ID));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllParticipantSettlementsWithEagerRelationshipsIsEnabled() {
        when(participantSettlementServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(participantSettlementServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllParticipantSettlementsWithEagerRelationshipsIsNotEnabled() {
        when(participantSettlementServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(participantSettlementRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getParticipantSettlement() {
        // Initialize the database
        participantSettlementRepository.save(participantSettlement).block();

        // Get the participantSettlement
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, participantSettlement.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(participantSettlement.getId().intValue()))
            .jsonPath("$.participantSettlementId")
            .value(is(DEFAULT_PARTICIPANT_SETTLEMENT_ID.intValue()))
            .jsonPath("$.participantSettlementUlidId")
            .value(is(DEFAULT_PARTICIPANT_SETTLEMENT_ULID_ID))
            .jsonPath("$.participantId")
            .value(is(DEFAULT_PARTICIPANT_ID.intValue()))
            .jsonPath("$.participantName")
            .value(is(DEFAULT_PARTICIPANT_NAME))
            .jsonPath("$.gstId")
            .value(is(DEFAULT_GST_ID))
            .jsonPath("$.settlementType")
            .value(is(DEFAULT_SETTLEMENT_TYPE.toString()))
            .jsonPath("$.settlementAmount")
            .value(is(DEFAULT_SETTLEMENT_AMOUNT.intValue()))
            .jsonPath("$.settlementDate")
            .value(is(DEFAULT_SETTLEMENT_DATE))
            .jsonPath("$.settlementDueDate")
            .value(is(DEFAULT_SETTLEMENT_DUE_DATE))
            .jsonPath("$.settlementContactInfo")
            .value(is(DEFAULT_SETTLEMENT_CONTACT_INFO))
            .jsonPath("$.patronOfPayment")
            .value(is(DEFAULT_PATRON_OF_PAYMENT))
            .jsonPath("$.recipientOfPayment")
            .value(is(DEFAULT_RECIPIENT_OF_PAYMENT))
            .jsonPath("$.settlementMethod")
            .value(is(DEFAULT_SETTLEMENT_METHOD))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID.intValue()))
            .jsonPath("$.accName")
            .value(is(DEFAULT_ACC_NAME))
            .jsonPath("$.ifscCode")
            .value(is(DEFAULT_IFSC_CODE))
            .jsonPath("$.accNumber")
            .value(is(DEFAULT_ACC_NUMBER.intValue()))
            .jsonPath("$.docId")
            .value(is(DEFAULT_DOC_ID));
    }

    @Test
    void getNonExistingParticipantSettlement() {
        // Get the participantSettlement
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingParticipantSettlement() throws Exception {
        // Initialize the database
        participantSettlementRepository.save(participantSettlement).block();

        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();

        // Update the participantSettlement
        ParticipantSettlement updatedParticipantSettlement = participantSettlementRepository
            .findById(participantSettlement.getId())
            .block();
        updatedParticipantSettlement
            .participantSettlementId(UPDATED_PARTICIPANT_SETTLEMENT_ID)
            .participantSettlementUlidId(UPDATED_PARTICIPANT_SETTLEMENT_ULID_ID)
            .participantId(UPDATED_PARTICIPANT_ID)
            .participantName(UPDATED_PARTICIPANT_NAME)
            .gstId(UPDATED_GST_ID)
            .settlementType(UPDATED_SETTLEMENT_TYPE)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .settlementDueDate(UPDATED_SETTLEMENT_DUE_DATE)
            .settlementContactInfo(UPDATED_SETTLEMENT_CONTACT_INFO)
            .patronOfPayment(UPDATED_PATRON_OF_PAYMENT)
            .recipientOfPayment(UPDATED_RECIPIENT_OF_PAYMENT)
            .settlementMethod(UPDATED_SETTLEMENT_METHOD)
            .tenantId(UPDATED_TENANT_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .accNumber(UPDATED_ACC_NUMBER)
            .docId(UPDATED_DOC_ID);
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(updatedParticipantSettlement);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, participantSettlementDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
        ParticipantSettlement testParticipantSettlement = participantSettlementList.get(participantSettlementList.size() - 1);
        assertThat(testParticipantSettlement.getParticipantSettlementId()).isEqualTo(UPDATED_PARTICIPANT_SETTLEMENT_ID);
        assertThat(testParticipantSettlement.getParticipantSettlementUlidId()).isEqualTo(UPDATED_PARTICIPANT_SETTLEMENT_ULID_ID);
        assertThat(testParticipantSettlement.getParticipantId()).isEqualTo(UPDATED_PARTICIPANT_ID);
        assertThat(testParticipantSettlement.getParticipantName()).isEqualTo(UPDATED_PARTICIPANT_NAME);
        assertThat(testParticipantSettlement.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testParticipantSettlement.getSettlementType()).isEqualTo(UPDATED_SETTLEMENT_TYPE);
        assertThat(testParticipantSettlement.getSettlementAmount()).isEqualTo(UPDATED_SETTLEMENT_AMOUNT);
        assertThat(testParticipantSettlement.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testParticipantSettlement.getSettlementDueDate()).isEqualTo(UPDATED_SETTLEMENT_DUE_DATE);
        assertThat(testParticipantSettlement.getSettlementContactInfo()).isEqualTo(UPDATED_SETTLEMENT_CONTACT_INFO);
        assertThat(testParticipantSettlement.getPatronOfPayment()).isEqualTo(UPDATED_PATRON_OF_PAYMENT);
        assertThat(testParticipantSettlement.getRecipientOfPayment()).isEqualTo(UPDATED_RECIPIENT_OF_PAYMENT);
        assertThat(testParticipantSettlement.getSettlementMethod()).isEqualTo(UPDATED_SETTLEMENT_METHOD);
        assertThat(testParticipantSettlement.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testParticipantSettlement.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testParticipantSettlement.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testParticipantSettlement.getAccNumber()).isEqualTo(UPDATED_ACC_NUMBER);
        assertThat(testParticipantSettlement.getDocId()).isEqualTo(UPDATED_DOC_ID);
    }

    @Test
    void putNonExistingParticipantSettlement() throws Exception {
        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();
        participantSettlement.setId(longCount.incrementAndGet());

        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, participantSettlementDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchParticipantSettlement() throws Exception {
        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();
        participantSettlement.setId(longCount.incrementAndGet());

        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamParticipantSettlement() throws Exception {
        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();
        participantSettlement.setId(longCount.incrementAndGet());

        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateParticipantSettlementWithPatch() throws Exception {
        // Initialize the database
        participantSettlementRepository.save(participantSettlement).block();

        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();

        // Update the participantSettlement using partial update
        ParticipantSettlement partialUpdatedParticipantSettlement = new ParticipantSettlement();
        partialUpdatedParticipantSettlement.setId(participantSettlement.getId());

        partialUpdatedParticipantSettlement
            .participantSettlementId(UPDATED_PARTICIPANT_SETTLEMENT_ID)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .patronOfPayment(UPDATED_PATRON_OF_PAYMENT)
            .settlementMethod(UPDATED_SETTLEMENT_METHOD)
            .ifscCode(UPDATED_IFSC_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParticipantSettlement.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParticipantSettlement))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
        ParticipantSettlement testParticipantSettlement = participantSettlementList.get(participantSettlementList.size() - 1);
        assertThat(testParticipantSettlement.getParticipantSettlementId()).isEqualTo(UPDATED_PARTICIPANT_SETTLEMENT_ID);
        assertThat(testParticipantSettlement.getParticipantSettlementUlidId()).isEqualTo(DEFAULT_PARTICIPANT_SETTLEMENT_ULID_ID);
        assertThat(testParticipantSettlement.getParticipantId()).isEqualTo(DEFAULT_PARTICIPANT_ID);
        assertThat(testParticipantSettlement.getParticipantName()).isEqualTo(DEFAULT_PARTICIPANT_NAME);
        assertThat(testParticipantSettlement.getGstId()).isEqualTo(DEFAULT_GST_ID);
        assertThat(testParticipantSettlement.getSettlementType()).isEqualTo(DEFAULT_SETTLEMENT_TYPE);
        assertThat(testParticipantSettlement.getSettlementAmount()).isEqualTo(DEFAULT_SETTLEMENT_AMOUNT);
        assertThat(testParticipantSettlement.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testParticipantSettlement.getSettlementDueDate()).isEqualTo(DEFAULT_SETTLEMENT_DUE_DATE);
        assertThat(testParticipantSettlement.getSettlementContactInfo()).isEqualTo(DEFAULT_SETTLEMENT_CONTACT_INFO);
        assertThat(testParticipantSettlement.getPatronOfPayment()).isEqualTo(UPDATED_PATRON_OF_PAYMENT);
        assertThat(testParticipantSettlement.getRecipientOfPayment()).isEqualTo(DEFAULT_RECIPIENT_OF_PAYMENT);
        assertThat(testParticipantSettlement.getSettlementMethod()).isEqualTo(UPDATED_SETTLEMENT_METHOD);
        assertThat(testParticipantSettlement.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testParticipantSettlement.getAccName()).isEqualTo(DEFAULT_ACC_NAME);
        assertThat(testParticipantSettlement.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testParticipantSettlement.getAccNumber()).isEqualTo(DEFAULT_ACC_NUMBER);
        assertThat(testParticipantSettlement.getDocId()).isEqualTo(DEFAULT_DOC_ID);
    }

    @Test
    void fullUpdateParticipantSettlementWithPatch() throws Exception {
        // Initialize the database
        participantSettlementRepository.save(participantSettlement).block();

        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();

        // Update the participantSettlement using partial update
        ParticipantSettlement partialUpdatedParticipantSettlement = new ParticipantSettlement();
        partialUpdatedParticipantSettlement.setId(participantSettlement.getId());

        partialUpdatedParticipantSettlement
            .participantSettlementId(UPDATED_PARTICIPANT_SETTLEMENT_ID)
            .participantSettlementUlidId(UPDATED_PARTICIPANT_SETTLEMENT_ULID_ID)
            .participantId(UPDATED_PARTICIPANT_ID)
            .participantName(UPDATED_PARTICIPANT_NAME)
            .gstId(UPDATED_GST_ID)
            .settlementType(UPDATED_SETTLEMENT_TYPE)
            .settlementAmount(UPDATED_SETTLEMENT_AMOUNT)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .settlementDueDate(UPDATED_SETTLEMENT_DUE_DATE)
            .settlementContactInfo(UPDATED_SETTLEMENT_CONTACT_INFO)
            .patronOfPayment(UPDATED_PATRON_OF_PAYMENT)
            .recipientOfPayment(UPDATED_RECIPIENT_OF_PAYMENT)
            .settlementMethod(UPDATED_SETTLEMENT_METHOD)
            .tenantId(UPDATED_TENANT_ID)
            .accName(UPDATED_ACC_NAME)
            .ifscCode(UPDATED_IFSC_CODE)
            .accNumber(UPDATED_ACC_NUMBER)
            .docId(UPDATED_DOC_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedParticipantSettlement.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedParticipantSettlement))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
        ParticipantSettlement testParticipantSettlement = participantSettlementList.get(participantSettlementList.size() - 1);
        assertThat(testParticipantSettlement.getParticipantSettlementId()).isEqualTo(UPDATED_PARTICIPANT_SETTLEMENT_ID);
        assertThat(testParticipantSettlement.getParticipantSettlementUlidId()).isEqualTo(UPDATED_PARTICIPANT_SETTLEMENT_ULID_ID);
        assertThat(testParticipantSettlement.getParticipantId()).isEqualTo(UPDATED_PARTICIPANT_ID);
        assertThat(testParticipantSettlement.getParticipantName()).isEqualTo(UPDATED_PARTICIPANT_NAME);
        assertThat(testParticipantSettlement.getGstId()).isEqualTo(UPDATED_GST_ID);
        assertThat(testParticipantSettlement.getSettlementType()).isEqualTo(UPDATED_SETTLEMENT_TYPE);
        assertThat(testParticipantSettlement.getSettlementAmount()).isEqualTo(UPDATED_SETTLEMENT_AMOUNT);
        assertThat(testParticipantSettlement.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testParticipantSettlement.getSettlementDueDate()).isEqualTo(UPDATED_SETTLEMENT_DUE_DATE);
        assertThat(testParticipantSettlement.getSettlementContactInfo()).isEqualTo(UPDATED_SETTLEMENT_CONTACT_INFO);
        assertThat(testParticipantSettlement.getPatronOfPayment()).isEqualTo(UPDATED_PATRON_OF_PAYMENT);
        assertThat(testParticipantSettlement.getRecipientOfPayment()).isEqualTo(UPDATED_RECIPIENT_OF_PAYMENT);
        assertThat(testParticipantSettlement.getSettlementMethod()).isEqualTo(UPDATED_SETTLEMENT_METHOD);
        assertThat(testParticipantSettlement.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testParticipantSettlement.getAccName()).isEqualTo(UPDATED_ACC_NAME);
        assertThat(testParticipantSettlement.getIfscCode()).isEqualTo(UPDATED_IFSC_CODE);
        assertThat(testParticipantSettlement.getAccNumber()).isEqualTo(UPDATED_ACC_NUMBER);
        assertThat(testParticipantSettlement.getDocId()).isEqualTo(UPDATED_DOC_ID);
    }

    @Test
    void patchNonExistingParticipantSettlement() throws Exception {
        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();
        participantSettlement.setId(longCount.incrementAndGet());

        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, participantSettlementDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchParticipantSettlement() throws Exception {
        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();
        participantSettlement.setId(longCount.incrementAndGet());

        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamParticipantSettlement() throws Exception {
        int databaseSizeBeforeUpdate = participantSettlementRepository.findAll().collectList().block().size();
        participantSettlement.setId(longCount.incrementAndGet());

        // Create the ParticipantSettlement
        ParticipantSettlementDTO participantSettlementDTO = participantSettlementMapper.toDto(participantSettlement);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(participantSettlementDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ParticipantSettlement in the database
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteParticipantSettlement() {
        // Initialize the database
        participantSettlementRepository.save(participantSettlement).block();

        int databaseSizeBeforeDelete = participantSettlementRepository.findAll().collectList().block().size();

        // Delete the participantSettlement
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, participantSettlement.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ParticipantSettlement> participantSettlementList = participantSettlementRepository.findAll().collectList().block();
        assertThat(participantSettlementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
