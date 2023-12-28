package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.IndividualAssessmentRepository;
import in.pft.apis.creditbazaar.service.IndividualAssessmentService;
import in.pft.apis.creditbazaar.service.dto.IndividualAssessmentDTO;
import in.pft.apis.creditbazaar.service.mapper.IndividualAssessmentMapper;
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

    private static final String DEFAULT_ASSESSMENT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ASSESSMENT_ID = "BBBBBBBBBB";

    private static final Double DEFAULT_CREDIT_SCORE = 1D;
    private static final Double UPDATED_CREDIT_SCORE = 2D;

    private static final String DEFAULT_FINALVERDICT = "AAAAAAAAAA";
    private static final String UPDATED_FINALVERDICT = "BBBBBBBBBB";

    private static final String DEFAULT_CRE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TIMESTAMP = "AAAAAAAAAA";
    private static final String UPDATED_TIMESTAMP = "BBBBBBBBBB";

    private static final String DEFAULT_TRADEPARTNER_GST = "AAAAAAAAAA";
    private static final String UPDATED_TRADEPARTNER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_TRADEPARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADEPARTNER_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_INVOICE_AMOUNT = 1L;
    private static final Long UPDATED_INVOICE_AMOUNT = 2L;

    private static final String DEFAULT_INVOICE_ID = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_ID = "BBBBBBBBBB";

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
            .creditScore(DEFAULT_CREDIT_SCORE)
            .finalverdict(DEFAULT_FINALVERDICT)
            .creRequestId(DEFAULT_CRE_REQUEST_ID)
            .timestamp(DEFAULT_TIMESTAMP)
            .tradepartnerGST(DEFAULT_TRADEPARTNER_GST)
            .tradepartnerId(DEFAULT_TRADEPARTNER_ID)
            .invoiceAmount(DEFAULT_INVOICE_AMOUNT)
            .invoiceId(DEFAULT_INVOICE_ID);
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
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalverdict(UPDATED_FINALVERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .tradepartnerId(UPDATED_TRADEPARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID);
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
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(DEFAULT_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalverdict()).isEqualTo(DEFAULT_FINALVERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradepartnerGST()).isEqualTo(DEFAULT_TRADEPARTNER_GST);
        assertThat(testIndividualAssessment.getTradepartnerId()).isEqualTo(DEFAULT_TRADEPARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(DEFAULT_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(DEFAULT_INVOICE_ID);
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
            .value(hasItem(DEFAULT_ASSESSMENT_ID))
            .jsonPath("$.[*].creditScore")
            .value(hasItem(DEFAULT_CREDIT_SCORE.doubleValue()))
            .jsonPath("$.[*].finalverdict")
            .value(hasItem(DEFAULT_FINALVERDICT))
            .jsonPath("$.[*].creRequestId")
            .value(hasItem(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(DEFAULT_TIMESTAMP))
            .jsonPath("$.[*].tradepartnerGST")
            .value(hasItem(DEFAULT_TRADEPARTNER_GST))
            .jsonPath("$.[*].tradepartnerId")
            .value(hasItem(DEFAULT_TRADEPARTNER_ID))
            .jsonPath("$.[*].invoiceAmount")
            .value(hasItem(DEFAULT_INVOICE_AMOUNT.intValue()))
            .jsonPath("$.[*].invoiceId")
            .value(hasItem(DEFAULT_INVOICE_ID));
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
            .value(is(DEFAULT_ASSESSMENT_ID))
            .jsonPath("$.creditScore")
            .value(is(DEFAULT_CREDIT_SCORE.doubleValue()))
            .jsonPath("$.finalverdict")
            .value(is(DEFAULT_FINALVERDICT))
            .jsonPath("$.creRequestId")
            .value(is(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.timestamp")
            .value(is(DEFAULT_TIMESTAMP))
            .jsonPath("$.tradepartnerGST")
            .value(is(DEFAULT_TRADEPARTNER_GST))
            .jsonPath("$.tradepartnerId")
            .value(is(DEFAULT_TRADEPARTNER_ID))
            .jsonPath("$.invoiceAmount")
            .value(is(DEFAULT_INVOICE_AMOUNT.intValue()))
            .jsonPath("$.invoiceId")
            .value(is(DEFAULT_INVOICE_ID));
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
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalverdict(UPDATED_FINALVERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .tradepartnerId(UPDATED_TRADEPARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID);
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
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(UPDATED_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalverdict()).isEqualTo(UPDATED_FINALVERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradepartnerGST()).isEqualTo(UPDATED_TRADEPARTNER_GST);
        assertThat(testIndividualAssessment.getTradepartnerId()).isEqualTo(UPDATED_TRADEPARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
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
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalverdict(UPDATED_FINALVERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradepartnerId(UPDATED_TRADEPARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID);

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
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(UPDATED_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalverdict()).isEqualTo(UPDATED_FINALVERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradepartnerGST()).isEqualTo(DEFAULT_TRADEPARTNER_GST);
        assertThat(testIndividualAssessment.getTradepartnerId()).isEqualTo(UPDATED_TRADEPARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
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
            .creditScore(UPDATED_CREDIT_SCORE)
            .finalverdict(UPDATED_FINALVERDICT)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .tradepartnerGST(UPDATED_TRADEPARTNER_GST)
            .tradepartnerId(UPDATED_TRADEPARTNER_ID)
            .invoiceAmount(UPDATED_INVOICE_AMOUNT)
            .invoiceId(UPDATED_INVOICE_ID);

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
        assertThat(testIndividualAssessment.getCreditScore()).isEqualTo(UPDATED_CREDIT_SCORE);
        assertThat(testIndividualAssessment.getFinalverdict()).isEqualTo(UPDATED_FINALVERDICT);
        assertThat(testIndividualAssessment.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testIndividualAssessment.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testIndividualAssessment.getTradepartnerGST()).isEqualTo(UPDATED_TRADEPARTNER_GST);
        assertThat(testIndividualAssessment.getTradepartnerId()).isEqualTo(UPDATED_TRADEPARTNER_ID);
        assertThat(testIndividualAssessment.getInvoiceAmount()).isEqualTo(UPDATED_INVOICE_AMOUNT);
        assertThat(testIndividualAssessment.getInvoiceId()).isEqualTo(UPDATED_INVOICE_ID);
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
