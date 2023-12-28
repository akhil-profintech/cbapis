package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.CBCREProcess;
import in.pft.apis.creditbazaar.repository.CBCREProcessRepository;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.service.mapper.CBCREProcessMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link CBCREProcessResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CBCREProcessResourceIT {

    private static final String DEFAULT_CB_PROCESS_ID = "AAAAAAAAAA";
    private static final String UPDATED_CB_PROCESS_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHORT_TRADER_GST = "AAAAAAAAAA";
    private static final String UPDATED_ANCHORT_TRADER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PROCESS_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_PROCESS_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CRE_PROCESS_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CRE_PROCESS_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_CRE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_REQUEST_ID = "BBBBBBBBBB";

    private static final Float DEFAULT_CUMILATIVETRADESCORE = 1F;
    private static final Float UPDATED_CUMILATIVETRADESCORE = 2F;

    private static final String DEFAULT_TIMESTAMP = "AAAAAAAAAA";
    private static final String UPDATED_TIMESTAMP = "BBBBBBBBBB";

    private static final Long DEFAULT_TOTAL_AMOUNT_REQUESTED = 1L;
    private static final Long UPDATED_TOTAL_AMOUNT_REQUESTED = 2L;

    private static final Long DEFAULT_TOTAL_INVOICE_AMOUNT = 1L;
    private static final Long UPDATED_TOTAL_INVOICE_AMOUNT = 2L;

    private static final String ENTITY_API_URL = "/api/cbcre-processes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CBCREProcessRepository cBCREProcessRepository;

    @Autowired
    private CBCREProcessMapper cBCREProcessMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CBCREProcess cBCREProcess;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CBCREProcess createEntity(EntityManager em) {
        CBCREProcess cBCREProcess = new CBCREProcess()
            .cbProcessId(DEFAULT_CB_PROCESS_ID)
            .financeRequestId(DEFAULT_FINANCE_REQUEST_ID)
            .anchorTraderId(DEFAULT_ANCHOR_TRADER_ID)
            .anchortTraderGst(DEFAULT_ANCHORT_TRADER_GST)
            .financeAmount(DEFAULT_FINANCE_AMOUNT)
            .processDateTime(DEFAULT_PROCESS_DATE_TIME)
            .creProcessStatus(DEFAULT_CRE_PROCESS_STATUS)
            .responseDateTime(DEFAULT_RESPONSE_DATE_TIME)
            .creRequestId(DEFAULT_CRE_REQUEST_ID)
            .cumilativetradescore(DEFAULT_CUMILATIVETRADESCORE)
            .timestamp(DEFAULT_TIMESTAMP)
            .totalAmountRequested(DEFAULT_TOTAL_AMOUNT_REQUESTED)
            .totalInvoiceAmount(DEFAULT_TOTAL_INVOICE_AMOUNT);
        return cBCREProcess;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CBCREProcess createUpdatedEntity(EntityManager em) {
        CBCREProcess cBCREProcess = new CBCREProcess()
            .cbProcessId(UPDATED_CB_PROCESS_ID)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .anchortTraderGst(UPDATED_ANCHORT_TRADER_GST)
            .financeAmount(UPDATED_FINANCE_AMOUNT)
            .processDateTime(UPDATED_PROCESS_DATE_TIME)
            .creProcessStatus(UPDATED_CRE_PROCESS_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .cumilativetradescore(UPDATED_CUMILATIVETRADESCORE)
            .timestamp(UPDATED_TIMESTAMP)
            .totalAmountRequested(UPDATED_TOTAL_AMOUNT_REQUESTED)
            .totalInvoiceAmount(UPDATED_TOTAL_INVOICE_AMOUNT);
        return cBCREProcess;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CBCREProcess.class).block();
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
        cBCREProcess = createEntity(em);
    }

    @Test
    void createCBCREProcess() throws Exception {
        int databaseSizeBeforeCreate = cBCREProcessRepository.findAll().collectList().block().size();
        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeCreate + 1);
        CBCREProcess testCBCREProcess = cBCREProcessList.get(cBCREProcessList.size() - 1);
        assertThat(testCBCREProcess.getCbProcessId()).isEqualTo(DEFAULT_CB_PROCESS_ID);
        assertThat(testCBCREProcess.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testCBCREProcess.getAnchorTraderId()).isEqualTo(DEFAULT_ANCHOR_TRADER_ID);
        assertThat(testCBCREProcess.getAnchortTraderGst()).isEqualTo(DEFAULT_ANCHORT_TRADER_GST);
        assertThat(testCBCREProcess.getFinanceAmount()).isEqualTo(DEFAULT_FINANCE_AMOUNT);
        assertThat(testCBCREProcess.getProcessDateTime()).isEqualTo(DEFAULT_PROCESS_DATE_TIME);
        assertThat(testCBCREProcess.getCreProcessStatus()).isEqualTo(DEFAULT_CRE_PROCESS_STATUS);
        assertThat(testCBCREProcess.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testCBCREProcess.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testCBCREProcess.getCumilativetradescore()).isEqualTo(DEFAULT_CUMILATIVETRADESCORE);
        assertThat(testCBCREProcess.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testCBCREProcess.getTotalAmountRequested()).isEqualTo(DEFAULT_TOTAL_AMOUNT_REQUESTED);
        assertThat(testCBCREProcess.getTotalInvoiceAmount()).isEqualTo(DEFAULT_TOTAL_INVOICE_AMOUNT);
    }

    @Test
    void createCBCREProcessWithExistingId() throws Exception {
        // Create the CBCREProcess with an existing ID
        cBCREProcess.setId(1L);
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        int databaseSizeBeforeCreate = cBCREProcessRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCBCREProcesses() {
        // Initialize the database
        cBCREProcessRepository.save(cBCREProcess).block();

        // Get all the cBCREProcessList
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
            .value(hasItem(cBCREProcess.getId().intValue()))
            .jsonPath("$.[*].cbProcessId")
            .value(hasItem(DEFAULT_CB_PROCESS_ID))
            .jsonPath("$.[*].financeRequestId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_ID))
            .jsonPath("$.[*].anchorTraderId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.[*].anchortTraderGst")
            .value(hasItem(DEFAULT_ANCHORT_TRADER_GST))
            .jsonPath("$.[*].financeAmount")
            .value(hasItem(DEFAULT_FINANCE_AMOUNT))
            .jsonPath("$.[*].processDateTime")
            .value(hasItem(DEFAULT_PROCESS_DATE_TIME))
            .jsonPath("$.[*].creProcessStatus")
            .value(hasItem(DEFAULT_CRE_PROCESS_STATUS))
            .jsonPath("$.[*].responseDateTime")
            .value(hasItem(DEFAULT_RESPONSE_DATE_TIME))
            .jsonPath("$.[*].creRequestId")
            .value(hasItem(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.[*].cumilativetradescore")
            .value(hasItem(DEFAULT_CUMILATIVETRADESCORE.doubleValue()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(DEFAULT_TIMESTAMP))
            .jsonPath("$.[*].totalAmountRequested")
            .value(hasItem(DEFAULT_TOTAL_AMOUNT_REQUESTED.intValue()))
            .jsonPath("$.[*].totalInvoiceAmount")
            .value(hasItem(DEFAULT_TOTAL_INVOICE_AMOUNT.intValue()));
    }

    @Test
    void getCBCREProcess() {
        // Initialize the database
        cBCREProcessRepository.save(cBCREProcess).block();

        // Get the cBCREProcess
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, cBCREProcess.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(cBCREProcess.getId().intValue()))
            .jsonPath("$.cbProcessId")
            .value(is(DEFAULT_CB_PROCESS_ID))
            .jsonPath("$.financeRequestId")
            .value(is(DEFAULT_FINANCE_REQUEST_ID))
            .jsonPath("$.anchorTraderId")
            .value(is(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.anchortTraderGst")
            .value(is(DEFAULT_ANCHORT_TRADER_GST))
            .jsonPath("$.financeAmount")
            .value(is(DEFAULT_FINANCE_AMOUNT))
            .jsonPath("$.processDateTime")
            .value(is(DEFAULT_PROCESS_DATE_TIME))
            .jsonPath("$.creProcessStatus")
            .value(is(DEFAULT_CRE_PROCESS_STATUS))
            .jsonPath("$.responseDateTime")
            .value(is(DEFAULT_RESPONSE_DATE_TIME))
            .jsonPath("$.creRequestId")
            .value(is(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.cumilativetradescore")
            .value(is(DEFAULT_CUMILATIVETRADESCORE.doubleValue()))
            .jsonPath("$.timestamp")
            .value(is(DEFAULT_TIMESTAMP))
            .jsonPath("$.totalAmountRequested")
            .value(is(DEFAULT_TOTAL_AMOUNT_REQUESTED.intValue()))
            .jsonPath("$.totalInvoiceAmount")
            .value(is(DEFAULT_TOTAL_INVOICE_AMOUNT.intValue()));
    }

    @Test
    void getNonExistingCBCREProcess() {
        // Get the cBCREProcess
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCBCREProcess() throws Exception {
        // Initialize the database
        cBCREProcessRepository.save(cBCREProcess).block();

        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();

        // Update the cBCREProcess
        CBCREProcess updatedCBCREProcess = cBCREProcessRepository.findById(cBCREProcess.getId()).block();
        updatedCBCREProcess
            .cbProcessId(UPDATED_CB_PROCESS_ID)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .anchortTraderGst(UPDATED_ANCHORT_TRADER_GST)
            .financeAmount(UPDATED_FINANCE_AMOUNT)
            .processDateTime(UPDATED_PROCESS_DATE_TIME)
            .creProcessStatus(UPDATED_CRE_PROCESS_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .cumilativetradescore(UPDATED_CUMILATIVETRADESCORE)
            .timestamp(UPDATED_TIMESTAMP)
            .totalAmountRequested(UPDATED_TOTAL_AMOUNT_REQUESTED)
            .totalInvoiceAmount(UPDATED_TOTAL_INVOICE_AMOUNT);
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(updatedCBCREProcess);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cBCREProcessDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
        CBCREProcess testCBCREProcess = cBCREProcessList.get(cBCREProcessList.size() - 1);
        assertThat(testCBCREProcess.getCbProcessId()).isEqualTo(UPDATED_CB_PROCESS_ID);
        assertThat(testCBCREProcess.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testCBCREProcess.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testCBCREProcess.getAnchortTraderGst()).isEqualTo(UPDATED_ANCHORT_TRADER_GST);
        assertThat(testCBCREProcess.getFinanceAmount()).isEqualTo(UPDATED_FINANCE_AMOUNT);
        assertThat(testCBCREProcess.getProcessDateTime()).isEqualTo(UPDATED_PROCESS_DATE_TIME);
        assertThat(testCBCREProcess.getCreProcessStatus()).isEqualTo(UPDATED_CRE_PROCESS_STATUS);
        assertThat(testCBCREProcess.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testCBCREProcess.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCBCREProcess.getCumilativetradescore()).isEqualTo(UPDATED_CUMILATIVETRADESCORE);
        assertThat(testCBCREProcess.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCBCREProcess.getTotalAmountRequested()).isEqualTo(UPDATED_TOTAL_AMOUNT_REQUESTED);
        assertThat(testCBCREProcess.getTotalInvoiceAmount()).isEqualTo(UPDATED_TOTAL_INVOICE_AMOUNT);
    }

    @Test
    void putNonExistingCBCREProcess() throws Exception {
        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();
        cBCREProcess.setId(longCount.incrementAndGet());

        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cBCREProcessDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCBCREProcess() throws Exception {
        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();
        cBCREProcess.setId(longCount.incrementAndGet());

        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCBCREProcess() throws Exception {
        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();
        cBCREProcess.setId(longCount.incrementAndGet());

        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCBCREProcessWithPatch() throws Exception {
        // Initialize the database
        cBCREProcessRepository.save(cBCREProcess).block();

        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();

        // Update the cBCREProcess using partial update
        CBCREProcess partialUpdatedCBCREProcess = new CBCREProcess();
        partialUpdatedCBCREProcess.setId(cBCREProcess.getId());

        partialUpdatedCBCREProcess
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .anchortTraderGst(UPDATED_ANCHORT_TRADER_GST)
            .creProcessStatus(UPDATED_CRE_PROCESS_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .cumilativetradescore(UPDATED_CUMILATIVETRADESCORE)
            .timestamp(UPDATED_TIMESTAMP)
            .totalInvoiceAmount(UPDATED_TOTAL_INVOICE_AMOUNT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCBCREProcess.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCBCREProcess))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
        CBCREProcess testCBCREProcess = cBCREProcessList.get(cBCREProcessList.size() - 1);
        assertThat(testCBCREProcess.getCbProcessId()).isEqualTo(DEFAULT_CB_PROCESS_ID);
        assertThat(testCBCREProcess.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testCBCREProcess.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testCBCREProcess.getAnchortTraderGst()).isEqualTo(UPDATED_ANCHORT_TRADER_GST);
        assertThat(testCBCREProcess.getFinanceAmount()).isEqualTo(DEFAULT_FINANCE_AMOUNT);
        assertThat(testCBCREProcess.getProcessDateTime()).isEqualTo(DEFAULT_PROCESS_DATE_TIME);
        assertThat(testCBCREProcess.getCreProcessStatus()).isEqualTo(UPDATED_CRE_PROCESS_STATUS);
        assertThat(testCBCREProcess.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testCBCREProcess.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCBCREProcess.getCumilativetradescore()).isEqualTo(UPDATED_CUMILATIVETRADESCORE);
        assertThat(testCBCREProcess.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCBCREProcess.getTotalAmountRequested()).isEqualTo(DEFAULT_TOTAL_AMOUNT_REQUESTED);
        assertThat(testCBCREProcess.getTotalInvoiceAmount()).isEqualTo(UPDATED_TOTAL_INVOICE_AMOUNT);
    }

    @Test
    void fullUpdateCBCREProcessWithPatch() throws Exception {
        // Initialize the database
        cBCREProcessRepository.save(cBCREProcess).block();

        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();

        // Update the cBCREProcess using partial update
        CBCREProcess partialUpdatedCBCREProcess = new CBCREProcess();
        partialUpdatedCBCREProcess.setId(cBCREProcess.getId());

        partialUpdatedCBCREProcess
            .cbProcessId(UPDATED_CB_PROCESS_ID)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .anchortTraderGst(UPDATED_ANCHORT_TRADER_GST)
            .financeAmount(UPDATED_FINANCE_AMOUNT)
            .processDateTime(UPDATED_PROCESS_DATE_TIME)
            .creProcessStatus(UPDATED_CRE_PROCESS_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .cumilativetradescore(UPDATED_CUMILATIVETRADESCORE)
            .timestamp(UPDATED_TIMESTAMP)
            .totalAmountRequested(UPDATED_TOTAL_AMOUNT_REQUESTED)
            .totalInvoiceAmount(UPDATED_TOTAL_INVOICE_AMOUNT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCBCREProcess.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCBCREProcess))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
        CBCREProcess testCBCREProcess = cBCREProcessList.get(cBCREProcessList.size() - 1);
        assertThat(testCBCREProcess.getCbProcessId()).isEqualTo(UPDATED_CB_PROCESS_ID);
        assertThat(testCBCREProcess.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testCBCREProcess.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testCBCREProcess.getAnchortTraderGst()).isEqualTo(UPDATED_ANCHORT_TRADER_GST);
        assertThat(testCBCREProcess.getFinanceAmount()).isEqualTo(UPDATED_FINANCE_AMOUNT);
        assertThat(testCBCREProcess.getProcessDateTime()).isEqualTo(UPDATED_PROCESS_DATE_TIME);
        assertThat(testCBCREProcess.getCreProcessStatus()).isEqualTo(UPDATED_CRE_PROCESS_STATUS);
        assertThat(testCBCREProcess.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testCBCREProcess.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCBCREProcess.getCumilativetradescore()).isEqualTo(UPDATED_CUMILATIVETRADESCORE);
        assertThat(testCBCREProcess.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testCBCREProcess.getTotalAmountRequested()).isEqualTo(UPDATED_TOTAL_AMOUNT_REQUESTED);
        assertThat(testCBCREProcess.getTotalInvoiceAmount()).isEqualTo(UPDATED_TOTAL_INVOICE_AMOUNT);
    }

    @Test
    void patchNonExistingCBCREProcess() throws Exception {
        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();
        cBCREProcess.setId(longCount.incrementAndGet());

        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, cBCREProcessDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCBCREProcess() throws Exception {
        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();
        cBCREProcess.setId(longCount.incrementAndGet());

        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCBCREProcess() throws Exception {
        int databaseSizeBeforeUpdate = cBCREProcessRepository.findAll().collectList().block().size();
        cBCREProcess.setId(longCount.incrementAndGet());

        // Create the CBCREProcess
        CBCREProcessDTO cBCREProcessDTO = cBCREProcessMapper.toDto(cBCREProcess);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cBCREProcessDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CBCREProcess in the database
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCBCREProcess() {
        // Initialize the database
        cBCREProcessRepository.save(cBCREProcess).block();

        int databaseSizeBeforeDelete = cBCREProcessRepository.findAll().collectList().block().size();

        // Delete the cBCREProcess
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, cBCREProcess.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CBCREProcess> cBCREProcessList = cBCREProcessRepository.findAll().collectList().block();
        assertThat(cBCREProcessList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
