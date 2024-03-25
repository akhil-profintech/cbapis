package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.IndividualAssessmentRepository;
import in.pft.apis.creditbazaar.gateway.service.IndividualAssessmentService;
import in.pft.apis.creditbazaar.gateway.service.dto.IndividualAssessmentDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.IndividualAssessmentMapper;
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
 * Integration tests for the {@link IndividualAssessmentResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class IndividualAssessmentResourceIT {

    private static final Long DEFAULT_ASSESSMENT_ID = 1L;
    private static final Long UPDATED_ASSESSMENT_ID = 2L;

    private static final String DEFAULT_ASSESSMENT_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSESSMENT_ULID_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_CREDIT_SCORE = 1D;
    private static final Double UPDATED_CREDIT_SCORE = 2D;

    private static final String DEFAULT_FINAL_VERDICT = "AAAAAAAAAA";
    private static final String UPDATED_FINAL_VERDICT = "BBBBBBBBBB";

    private static final String DEFAULT_CRE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TIMESTAMP = "AAAAAAAAAA";
    private static final String UPDATED_TIMESTAMP = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_GST = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_INVOICE_AMOUNT = 1L;
    private static final Long UPDATED_INVOICE_AMOUNT = 2L;

    private static final String DEFAULT_INVOICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BASE_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_BASE_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_CTIN = "AAAAAAAAAA";
    private static final String UPDATED_CTIN = "BBBBBBBBBB";

    private static final String DEFAULT_INV_DATE = "AAAAAAAAAA";
    private static final String UPDATED_INV_DATE = "BBBBBBBBBB";

    private static final Long DEFAULT_CB_PROCESS_ID = 1L;
    private static final Long UPDATED_CB_PROCESS_ID = 2L;

    private static final Boolean DEFAULT_GRN_PRESENT = false;
    private static final Boolean UPDATED_GRN_PRESENT = true;

    private static final Boolean DEFAULT_EINVOICE_PRESENT = false;
    private static final Boolean UPDATED_EINVOICE_PRESENT = true;

    private static final Boolean DEFAULT_EWAY_BILL_PRESENT = false;
    private static final Boolean UPDATED_EWAY_BILL_PRESENT = true;

    private static final Boolean DEFAULT_TRADE_PARTNER_CONFIRMATION = false;
    private static final Boolean UPDATED_TRADE_PARTNER_CONFIRMATION = true;

    private static final String ENTITY_API_URL = "/api/individual-assessments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IndividualAssessmentRepository individualAssessmentRepository;

    @Mock
    private IndividualAssessmentRepository individualAssessmentRepositoryMock;

    @Autowired
    private IndividualAssessmentMapper individualAssessmentMapper;

    @Mock
    private IndividualAssessmentService individualAssessmentServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private IndividualAssessment individualAssessment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndividualAssessment createEntity(EntityManager em) {
        IndividualAssessment individualAssessment = new IndividualAssessment()
            .assessmentId(DEFAULT_ASSESSMENT_ID)
            .assessmentUlidId(DEFAULT_ASSESSMENT_ULID_ID)
            .creditScore(DEFAULT_CREDIT_SCORE)
            .finalVerdict(DEFAULT_FINAL_VERDICT)
            .creRequestId(DEFAULT_CRE_REQUEST_ID)
            .timestamp(DEFAULT_TIMESTAMP)
            .tradePartnerGST(DEFAULT_TRADE_PARTNER_GST)
            .tradePartnerId(DEFAULT_TRADE_PARTNER_ID)
            .invoiceAmount(DEFAULT_INVOICE_AMOUNT)
            .invoiceId(DEFAULT_INVOICE_ID)
            .baseScore(DEFAULT_BASE_SCORE)
            .ctin(DEFAULT_CTIN)
            .invDate(DEFAULT_INV_DATE)
            .cbProcessId(DEFAULT_CB_PROCESS_ID)
            .grnPresent(DEFAULT_GRN_PRESENT)
            .einvoicePresent(DEFAULT_EINVOICE_PRESENT)
            .ewayBillPresent(DEFAULT_EWAY_BILL_PRESENT)
            .tradePartnerConfirmation(DEFAULT_TRADE_PARTNER_CONFIRMATION);
        return individualAssessment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IndividualAssessment createUpdatedEntity(EntityManager em) {
        IndividualAssessment individualAssessment = new IndividualAssessment()
            .assessmentId(UPDATED_ASSESSMENT_ID)
            .assessmentUlidId(UPDATED_ASSESSMENT_ULID_ID)
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalVerdict(UPDATED_FINAL_VERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradePartnerGST(UPDATED_TRADE_PARTNER_GST)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID)
            .baseScore(UPDATED_BASE_SCORE)
            .ctin(UPDATED_CTIN)
            .invDate(UPDATED_INV_DATE)
            .cbProcessId(UPDATED_CB_PROCESS_ID)
            .grnPresent(UPDATED_GRN_PRESENT)
            .einvoicePresent(UPDATED_EINVOICE_PRESENT)
            .ewayBillPresent(UPDATED_EWAY_BILL_PRESENT)
            .tradePartnerConfirmation(UPDATED_TRADE_PARTNER_CONFIRMATION);
        return individualAssessment;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(IndividualAssessment.class).block();
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
        individualAssessment = createEntity(em);
    }

    @Test
    void createIndividualAssessment() throws Exception {
        int databaseSizeBeforeCreate = individualAssessmentRepository.findAll().collectList().block().size();
        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeCreate + 1);
        IndividualAssessment testIndividualAssessment = individualAssessmentList.get(individualAssessmentList.size() - 1);
        assertThat(testIndividualAssessment.getAssessmentId()).isEqualTo(DEFAULT_ASSESSMENT_ID);
        assertThat(testIndividualAssessment.getAssessmentUlidId()).isEqualTo(DEFAULT_ASSESSMENT_ULID_ID);
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(DEFAULT_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalVerdict()).isEqualTo(DEFAULT_FINAL_VERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradePartnerGST()).isEqualTo(DEFAULT_TRADE_PARTNER_GST);
        assertThat(testIndividualAssessment.getTradePartnerId()).isEqualTo(DEFAULT_TRADE_PARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(DEFAULT_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(DEFAULT_INVOICE_ID);
        assertThat(testIndividualAssessment.getBaseScore()).isEqualTo(DEFAULT_BASE_SCORE);
        assertThat(testIndividualAssessment.getCtin()).isEqualTo(DEFAULT_CTIN);
        assertThat(testIndividualAssessment.getInvDate()).isEqualTo(DEFAULT_INV_DATE);
        assertThat(testIndividualAssessment.getCbProcessId()).isEqualTo(DEFAULT_CB_PROCESS_ID);
        assertThat(testIndividualAssessment.getGrnPresent()).isEqualTo(DEFAULT_GRN_PRESENT);
        assertThat(testIndividualAssessment.getEinvoicePresent()).isEqualTo(DEFAULT_EINVOICE_PRESENT);
        assertThat(testIndividualAssessment.getEwayBillPresent()).isEqualTo(DEFAULT_EWAY_BILL_PRESENT);
        assertThat(testIndividualAssessment.getTradePartnerConfirmation()).isEqualTo(DEFAULT_TRADE_PARTNER_CONFIRMATION);
    }

    @Test
    void createIndividualAssessmentWithExistingId() throws Exception {
        // Create the IndividualAssessment with an existing ID
        individualAssessment.setId(1L);
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        int databaseSizeBeforeCreate = individualAssessmentRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllIndividualAssessments() {
        // Initialize the database
        individualAssessmentRepository.save(individualAssessment).block();

        // Get all the individualAssessmentList
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
            .value(hasItem(individualAssessment.getId().intValue()))
            .jsonPath("$.[*].assessmentId")
            .value(hasItem(DEFAULT_ASSESSMENT_ID.intValue()))
            .jsonPath("$.[*].assessmentUlidId")
            .value(hasItem(DEFAULT_ASSESSMENT_ULID_ID))
            .jsonPath("$.[*].creditScore")
            .value(hasItem(DEFAULT_CREDIT_SCORE.doubleValue()))
            .jsonPath("$.[*].finalVerdict")
            .value(hasItem(DEFAULT_FINAL_VERDICT))
            .jsonPath("$.[*].creRequestId")
            .value(hasItem(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(DEFAULT_TIMESTAMP))
            .jsonPath("$.[*].tradePartnerGST")
            .value(hasItem(DEFAULT_TRADE_PARTNER_GST))
            .jsonPath("$.[*].tradePartnerId")
            .value(hasItem(DEFAULT_TRADE_PARTNER_ID))
            .jsonPath("$.[*].invoiceAmount")
            .value(hasItem(DEFAULT_INVOICE_AMOUNT.intValue()))
            .jsonPath("$.[*].invoiceId")
            .value(hasItem(DEFAULT_INVOICE_ID))
            .jsonPath("$.[*].baseScore")
            .value(hasItem(DEFAULT_BASE_SCORE))
            .jsonPath("$.[*].ctin")
            .value(hasItem(DEFAULT_CTIN))
            .jsonPath("$.[*].invDate")
            .value(hasItem(DEFAULT_INV_DATE))
            .jsonPath("$.[*].cbProcessId")
            .value(hasItem(DEFAULT_CB_PROCESS_ID.intValue()))
            .jsonPath("$.[*].grnPresent")
            .value(hasItem(DEFAULT_GRN_PRESENT.booleanValue()))
            .jsonPath("$.[*].einvoicePresent")
            .value(hasItem(DEFAULT_EINVOICE_PRESENT.booleanValue()))
            .jsonPath("$.[*].ewayBillPresent")
            .value(hasItem(DEFAULT_EWAY_BILL_PRESENT.booleanValue()))
            .jsonPath("$.[*].tradePartnerConfirmation")
            .value(hasItem(DEFAULT_TRADE_PARTNER_CONFIRMATION.booleanValue()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllIndividualAssessmentsWithEagerRelationshipsIsEnabled() {
        when(individualAssessmentServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(individualAssessmentServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllIndividualAssessmentsWithEagerRelationshipsIsNotEnabled() {
        when(individualAssessmentServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(individualAssessmentRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getIndividualAssessment() {
        // Initialize the database
        individualAssessmentRepository.save(individualAssessment).block();

        // Get the individualAssessment
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, individualAssessment.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(individualAssessment.getId().intValue()))
            .jsonPath("$.assessmentId")
            .value(is(DEFAULT_ASSESSMENT_ID.intValue()))
            .jsonPath("$.assessmentUlidId")
            .value(is(DEFAULT_ASSESSMENT_ULID_ID))
            .jsonPath("$.creditScore")
            .value(is(DEFAULT_CREDIT_SCORE.doubleValue()))
            .jsonPath("$.finalVerdict")
            .value(is(DEFAULT_FINAL_VERDICT))
            .jsonPath("$.creRequestId")
            .value(is(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.timestamp")
            .value(is(DEFAULT_TIMESTAMP))
            .jsonPath("$.tradePartnerGST")
            .value(is(DEFAULT_TRADE_PARTNER_GST))
            .jsonPath("$.tradePartnerId")
            .value(is(DEFAULT_TRADE_PARTNER_ID))
            .jsonPath("$.invoiceAmount")
            .value(is(DEFAULT_INVOICE_AMOUNT.intValue()))
            .jsonPath("$.invoiceId")
            .value(is(DEFAULT_INVOICE_ID))
            .jsonPath("$.baseScore")
            .value(is(DEFAULT_BASE_SCORE))
            .jsonPath("$.ctin")
            .value(is(DEFAULT_CTIN))
            .jsonPath("$.invDate")
            .value(is(DEFAULT_INV_DATE))
            .jsonPath("$.cbProcessId")
            .value(is(DEFAULT_CB_PROCESS_ID.intValue()))
            .jsonPath("$.grnPresent")
            .value(is(DEFAULT_GRN_PRESENT.booleanValue()))
            .jsonPath("$.einvoicePresent")
            .value(is(DEFAULT_EINVOICE_PRESENT.booleanValue()))
            .jsonPath("$.ewayBillPresent")
            .value(is(DEFAULT_EWAY_BILL_PRESENT.booleanValue()))
            .jsonPath("$.tradePartnerConfirmation")
            .value(is(DEFAULT_TRADE_PARTNER_CONFIRMATION.booleanValue()));
    }

    @Test
    void getNonExistingIndividualAssessment() {
        // Get the individualAssessment
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingIndividualAssessment() throws Exception {
        // Initialize the database
        individualAssessmentRepository.save(individualAssessment).block();

        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();

        // Update the individualAssessment
        IndividualAssessment updatedIndividualAssessment = individualAssessmentRepository.findById(individualAssessment.getId()).block();
        updatedIndividualAssessment
            .assessmentId(UPDATED_ASSESSMENT_ID)
            .assessmentUlidId(UPDATED_ASSESSMENT_ULID_ID)
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalVerdict(UPDATED_FINAL_VERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradePartnerGST(UPDATED_TRADE_PARTNER_GST)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID)
            .baseScore(UPDATED_BASE_SCORE)
            .ctin(UPDATED_CTIN)
            .invDate(UPDATED_INV_DATE)
            .cbProcessId(UPDATED_CB_PROCESS_ID)
            .grnPresent(UPDATED_GRN_PRESENT)
            .einvoicePresent(UPDATED_EINVOICE_PRESENT)
            .ewayBillPresent(UPDATED_EWAY_BILL_PRESENT)
            .tradePartnerConfirmation(UPDATED_TRADE_PARTNER_CONFIRMATION);
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(updatedIndividualAssessment);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, individualAssessmentDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
        IndividualAssessment testIndividualAssessment = individualAssessmentList.get(individualAssessmentList.size() - 1);
        assertThat(testIndividualAssessment.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
        assertThat(testIndividualAssessment.getAssessmentUlidId()).isEqualTo(UPDATED_ASSESSMENT_ULID_ID);
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(UPDATED_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalVerdict()).isEqualTo(UPDATED_FINAL_VERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradePartnerGST()).isEqualTo(UPDATED_TRADE_PARTNER_GST);
        assertThat(testIndividualAssessment.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
        assertThat(testIndividualAssessment.getBaseScore()).isEqualTo(UPDATED_BASE_SCORE);
        assertThat(testIndividualAssessment.getCtin()).isEqualTo(UPDATED_CTIN);
        assertThat(testIndividualAssessment.getInvDate()).isEqualTo(UPDATED_INV_DATE);
        assertThat(testIndividualAssessment.getCbProcessId()).isEqualTo(UPDATED_CB_PROCESS_ID);
        assertThat(testIndividualAssessment.getGrnPresent()).isEqualTo(UPDATED_GRN_PRESENT);
        assertThat(testIndividualAssessment.getEinvoicePresent()).isEqualTo(UPDATED_EINVOICE_PRESENT);
        assertThat(testIndividualAssessment.getEwayBillPresent()).isEqualTo(UPDATED_EWAY_BILL_PRESENT);
        assertThat(testIndividualAssessment.getTradePartnerConfirmation()).isEqualTo(UPDATED_TRADE_PARTNER_CONFIRMATION);
    }

    @Test
    void putNonExistingIndividualAssessment() throws Exception {
        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();
        individualAssessment.setId(longCount.incrementAndGet());

        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, individualAssessmentDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchIndividualAssessment() throws Exception {
        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();
        individualAssessment.setId(longCount.incrementAndGet());

        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamIndividualAssessment() throws Exception {
        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();
        individualAssessment.setId(longCount.incrementAndGet());

        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateIndividualAssessmentWithPatch() throws Exception {
        // Initialize the database
        individualAssessmentRepository.save(individualAssessment).block();

        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();

        // Update the individualAssessment using partial update
        IndividualAssessment partialUpdatedIndividualAssessment = new IndividualAssessment();
        partialUpdatedIndividualAssessment.setId(individualAssessment.getId());

        partialUpdatedIndividualAssessment
            .assessmentUlidId(UPDATED_ASSESSMENT_ULID_ID)
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalVerdict(UPDATED_FINAL_VERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .tradePartnerGST(UPDATED_TRADE_PARTNER_GST)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .baseScore(UPDATED_BASE_SCORE)
            .ctin(UPDATED_CTIN)
            .invDate(UPDATED_INV_DATE)
            .grnPresent(UPDATED_GRN_PRESENT)
            .ewayBillPresent(UPDATED_EWAY_BILL_PRESENT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedIndividualAssessment.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedIndividualAssessment))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
        IndividualAssessment testIndividualAssessment = individualAssessmentList.get(individualAssessmentList.size() - 1);
        assertThat(testIndividualAssessment.getAssessmentId()).isEqualTo(DEFAULT_ASSESSMENT_ID);
        assertThat(testIndividualAssessment.getAssessmentUlidId()).isEqualTo(UPDATED_ASSESSMENT_ULID_ID);
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(UPDATED_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalVerdict()).isEqualTo(UPDATED_FINAL_VERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradePartnerGST()).isEqualTo(UPDATED_TRADE_PARTNER_GST);
        assertThat(testIndividualAssessment.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(DEFAULT_INVOICE_ID);
        assertThat(testIndividualAssessment.getBaseScore()).isEqualTo(UPDATED_BASE_SCORE);
        assertThat(testIndividualAssessment.getCtin()).isEqualTo(UPDATED_CTIN);
        assertThat(testIndividualAssessment.getInvDate()).isEqualTo(UPDATED_INV_DATE);
        assertThat(testIndividualAssessment.getCbProcessId()).isEqualTo(DEFAULT_CB_PROCESS_ID);
        assertThat(testIndividualAssessment.getGrnPresent()).isEqualTo(UPDATED_GRN_PRESENT);
        assertThat(testIndividualAssessment.getEinvoicePresent()).isEqualTo(DEFAULT_EINVOICE_PRESENT);
        assertThat(testIndividualAssessment.getEwayBillPresent()).isEqualTo(UPDATED_EWAY_BILL_PRESENT);
        assertThat(testIndividualAssessment.getTradePartnerConfirmation()).isEqualTo(DEFAULT_TRADE_PARTNER_CONFIRMATION);
    }

    @Test
    void fullUpdateIndividualAssessmentWithPatch() throws Exception {
        // Initialize the database
        individualAssessmentRepository.save(individualAssessment).block();

        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();

        // Update the individualAssessment using partial update
        IndividualAssessment partialUpdatedIndividualAssessment = new IndividualAssessment();
        partialUpdatedIndividualAssessment.setId(individualAssessment.getId());

        partialUpdatedIndividualAssessment
            .assessmentId(UPDATED_ASSESSMENT_ID)
            .assessmentUlidId(UPDATED_ASSESSMENT_ULID_ID)
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalVerdict(UPDATED_FINAL_VERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradePartnerGST(UPDATED_TRADE_PARTNER_GST)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID)
            .baseScore(UPDATED_BASE_SCORE)
            .ctin(UPDATED_CTIN)
            .invDate(UPDATED_INV_DATE)
            .cbProcessId(UPDATED_CB_PROCESS_ID)
            .grnPresent(UPDATED_GRN_PRESENT)
            .einvoicePresent(UPDATED_EINVOICE_PRESENT)
            .ewayBillPresent(UPDATED_EWAY_BILL_PRESENT)
            .tradePartnerConfirmation(UPDATED_TRADE_PARTNER_CONFIRMATION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedIndividualAssessment.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedIndividualAssessment))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
        IndividualAssessment testIndividualAssessment = individualAssessmentList.get(individualAssessmentList.size() - 1);
        assertThat(testIndividualAssessment.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
        assertThat(testIndividualAssessment.getAssessmentUlidId()).isEqualTo(UPDATED_ASSESSMENT_ULID_ID);
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(UPDATED_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalVerdict()).isEqualTo(UPDATED_FINAL_VERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradePartnerGST()).isEqualTo(UPDATED_TRADE_PARTNER_GST);
        assertThat(testIndividualAssessment.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
        assertThat(testIndividualAssessment.getBaseScore()).isEqualTo(UPDATED_BASE_SCORE);
        assertThat(testIndividualAssessment.getCtin()).isEqualTo(UPDATED_CTIN);
        assertThat(testIndividualAssessment.getInvDate()).isEqualTo(UPDATED_INV_DATE);
        assertThat(testIndividualAssessment.getCbProcessId()).isEqualTo(UPDATED_CB_PROCESS_ID);
        assertThat(testIndividualAssessment.getGrnPresent()).isEqualTo(UPDATED_GRN_PRESENT);
        assertThat(testIndividualAssessment.getEinvoicePresent()).isEqualTo(UPDATED_EINVOICE_PRESENT);
        assertThat(testIndividualAssessment.getEwayBillPresent()).isEqualTo(UPDATED_EWAY_BILL_PRESENT);
        assertThat(testIndividualAssessment.getTradePartnerConfirmation()).isEqualTo(UPDATED_TRADE_PARTNER_CONFIRMATION);
    }

    @Test
    void patchNonExistingIndividualAssessment() throws Exception {
        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();
        individualAssessment.setId(longCount.incrementAndGet());

        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, individualAssessmentDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchIndividualAssessment() throws Exception {
        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();
        individualAssessment.setId(longCount.incrementAndGet());

        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamIndividualAssessment() throws Exception {
        int databaseSizeBeforeUpdate = individualAssessmentRepository.findAll().collectList().block().size();
        individualAssessment.setId(longCount.incrementAndGet());

        // Create the IndividualAssessment
        IndividualAssessmentDTO individualAssessmentDTO = individualAssessmentMapper.toDto(individualAssessment);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(individualAssessmentDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the IndividualAssessment in the database
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteIndividualAssessment() {
        // Initialize the database
        individualAssessmentRepository.save(individualAssessment).block();

        int databaseSizeBeforeDelete = individualAssessmentRepository.findAll().collectList().block().size();

        // Delete the individualAssessment
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, individualAssessment.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<IndividualAssessment> individualAssessmentList = individualAssessmentRepository.findAll().collectList().block();
        assertThat(individualAssessmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
