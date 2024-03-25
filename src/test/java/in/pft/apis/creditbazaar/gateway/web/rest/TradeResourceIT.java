package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Trade;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.TradeRepository;
import in.pft.apis.creditbazaar.gateway.service.TradeService;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.TradeMapper;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TradeResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TradeResourceIT {

    private static final String DEFAULT_TRADE_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_SELLER_GST_ID = "AAAAAAAAAA";
    private static final String UPDATED_SELLER_GST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_GST_ID = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_GST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_AMOUNT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_INVOICE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INVOICE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_INVOICE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_INVOICE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_DOC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_DOC_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_DOC_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_DOC_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_DOC_CREDIBILITY = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_DOC_CREDIBILITY = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_MILESTONE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_MILESTONE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_ADVANCE_PAYMENT = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_ADVANCE_PAYMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_NAME = "BBBBBBBBBB";

    private static final Long DEFAULT_INVOICE_TERM = 1L;
    private static final Long UPDATED_INVOICE_TERM = 2L;

    private static final String DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER = "BBBBBBBBBB";

    private static final String DEFAULT_REASON_FOR_FINANCE = "AAAAAAAAAA";
    private static final String UPDATED_REASON_FOR_FINANCE = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_SECTOR = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_SECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_GST_COMPLIANCE_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/trades";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TradeRepository tradeRepository;

    @Mock
    private TradeRepository tradeRepositoryMock;

    @Autowired
    private TradeMapper tradeMapper;

    @Mock
    private TradeService tradeServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Trade trade;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trade createEntity(EntityManager em) {
        Trade trade = new Trade()
            .tradeUlidId(DEFAULT_TRADE_ULID_ID)
            .tradeRefNo(DEFAULT_TRADE_REF_NO)
            .sellerGstId(DEFAULT_SELLER_GST_ID)
            .buyerGstId(DEFAULT_BUYER_GST_ID)
            .tradeAmount(DEFAULT_TRADE_AMOUNT)
            .invoiceDate(DEFAULT_INVOICE_DATE)
            .invoiceNumber(DEFAULT_INVOICE_NUMBER)
            .tradeDocType(DEFAULT_TRADE_DOC_TYPE)
            .tradeDocSource(DEFAULT_TRADE_DOC_SOURCE)
            .tradeDocCredibility(DEFAULT_TRADE_DOC_CREDIBILITY)
            .tradeMilestoneStatus(DEFAULT_TRADE_MILESTONE_STATUS)
            .tradeAdvancePayment(DEFAULT_TRADE_ADVANCE_PAYMENT)
            .anchorTraderName(DEFAULT_ANCHOR_TRADER_NAME)
            .tradePartnerName(DEFAULT_TRADE_PARTNER_NAME)
            .invoiceTerm(DEFAULT_INVOICE_TERM)
            .acceptanceFromTradePartner(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER)
            .reasonForFinance(DEFAULT_REASON_FOR_FINANCE)
            .tradePartnerSector(DEFAULT_TRADE_PARTNER_SECTOR)
            .tradePartnerLocation(DEFAULT_TRADE_PARTNER_LOCATION)
            .tradePartnerGstComplianceScore(DEFAULT_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
        return trade;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Trade createUpdatedEntity(EntityManager em) {
        Trade trade = new Trade()
            .tradeUlidId(UPDATED_TRADE_ULID_ID)
            .tradeRefNo(UPDATED_TRADE_REF_NO)
            .sellerGstId(UPDATED_SELLER_GST_ID)
            .buyerGstId(UPDATED_BUYER_GST_ID)
            .tradeAmount(UPDATED_TRADE_AMOUNT)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .tradeDocType(UPDATED_TRADE_DOC_TYPE)
            .tradeDocSource(UPDATED_TRADE_DOC_SOURCE)
            .tradeDocCredibility(UPDATED_TRADE_DOC_CREDIBILITY)
            .tradeMilestoneStatus(UPDATED_TRADE_MILESTONE_STATUS)
            .tradeAdvancePayment(UPDATED_TRADE_ADVANCE_PAYMENT)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .invoiceTerm(UPDATED_INVOICE_TERM)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER)
            .reasonForFinance(UPDATED_REASON_FOR_FINANCE)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .tradePartnerLocation(UPDATED_TRADE_PARTNER_LOCATION)
            .tradePartnerGstComplianceScore(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
        return trade;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Trade.class).block();
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
        trade = createEntity(em);
    }

    @Test
    void createTrade() throws Exception {
        int databaseSizeBeforeCreate = tradeRepository.findAll().collectList().block().size();
        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeCreate + 1);
        Trade testTrade = tradeList.get(tradeList.size() - 1);
        assertThat(testTrade.getTradeUlidId()).isEqualTo(DEFAULT_TRADE_ULID_ID);
        assertThat(testTrade.getTradeRefNo()).isEqualTo(DEFAULT_TRADE_REF_NO);
        assertThat(testTrade.getSellerGstId()).isEqualTo(DEFAULT_SELLER_GST_ID);
        assertThat(testTrade.getBuyerGstId()).isEqualTo(DEFAULT_BUYER_GST_ID);
        assertThat(testTrade.getTradeAmount()).isEqualTo(DEFAULT_TRADE_AMOUNT);
        assertThat(testTrade.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testTrade.getInvoiceNumber()).isEqualTo(DEFAULT_INVOICE_NUMBER);
        assertThat(testTrade.getTradeDocType()).isEqualTo(DEFAULT_TRADE_DOC_TYPE);
        assertThat(testTrade.getTradeDocSource()).isEqualTo(DEFAULT_TRADE_DOC_SOURCE);
        assertThat(testTrade.getTradeDocCredibility()).isEqualTo(DEFAULT_TRADE_DOC_CREDIBILITY);
        assertThat(testTrade.getTradeMilestoneStatus()).isEqualTo(DEFAULT_TRADE_MILESTONE_STATUS);
        assertThat(testTrade.getTradeAdvancePayment()).isEqualTo(DEFAULT_TRADE_ADVANCE_PAYMENT);
        assertThat(testTrade.getAnchorTraderName()).isEqualTo(DEFAULT_ANCHOR_TRADER_NAME);
        assertThat(testTrade.getTradePartnerName()).isEqualTo(DEFAULT_TRADE_PARTNER_NAME);
        assertThat(testTrade.getInvoiceTerm()).isEqualTo(DEFAULT_INVOICE_TERM);
        assertThat(testTrade.getAcceptanceFromTradePartner()).isEqualTo(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER);
        assertThat(testTrade.getReasonForFinance()).isEqualTo(DEFAULT_REASON_FOR_FINANCE);
        assertThat(testTrade.getTradePartnerSector()).isEqualTo(DEFAULT_TRADE_PARTNER_SECTOR);
        assertThat(testTrade.getTradePartnerLocation()).isEqualTo(DEFAULT_TRADE_PARTNER_LOCATION);
        assertThat(testTrade.getTradePartnerGstComplianceScore()).isEqualTo(DEFAULT_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
    }

    @Test
    void createTradeWithExistingId() throws Exception {
        // Create the Trade with an existing ID
        trade.setId(1L);
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        int databaseSizeBeforeCreate = tradeRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllTrades() {
        // Initialize the database
        tradeRepository.save(trade).block();

        // Get all the tradeList
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
            .value(hasItem(trade.getId().intValue()))
            .jsonPath("$.[*].tradeUlidId")
            .value(hasItem(DEFAULT_TRADE_ULID_ID))
            .jsonPath("$.[*].tradeRefNo")
            .value(hasItem(DEFAULT_TRADE_REF_NO))
            .jsonPath("$.[*].sellerGstId")
            .value(hasItem(DEFAULT_SELLER_GST_ID))
            .jsonPath("$.[*].buyerGstId")
            .value(hasItem(DEFAULT_BUYER_GST_ID))
            .jsonPath("$.[*].tradeAmount")
            .value(hasItem(DEFAULT_TRADE_AMOUNT))
            .jsonPath("$.[*].invoiceDate")
            .value(hasItem(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.[*].invoiceNumber")
            .value(hasItem(DEFAULT_INVOICE_NUMBER))
            .jsonPath("$.[*].tradeDocType")
            .value(hasItem(DEFAULT_TRADE_DOC_TYPE))
            .jsonPath("$.[*].tradeDocSource")
            .value(hasItem(DEFAULT_TRADE_DOC_SOURCE))
            .jsonPath("$.[*].tradeDocCredibility")
            .value(hasItem(DEFAULT_TRADE_DOC_CREDIBILITY))
            .jsonPath("$.[*].tradeMilestoneStatus")
            .value(hasItem(DEFAULT_TRADE_MILESTONE_STATUS))
            .jsonPath("$.[*].tradeAdvancePayment")
            .value(hasItem(DEFAULT_TRADE_ADVANCE_PAYMENT))
            .jsonPath("$.[*].anchorTraderName")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_NAME))
            .jsonPath("$.[*].tradePartnerName")
            .value(hasItem(DEFAULT_TRADE_PARTNER_NAME))
            .jsonPath("$.[*].invoiceTerm")
            .value(hasItem(DEFAULT_INVOICE_TERM.intValue()))
            .jsonPath("$.[*].acceptanceFromTradePartner")
            .value(hasItem(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER))
            .jsonPath("$.[*].reasonForFinance")
            .value(hasItem(DEFAULT_REASON_FOR_FINANCE))
            .jsonPath("$.[*].tradePartnerSector")
            .value(hasItem(DEFAULT_TRADE_PARTNER_SECTOR))
            .jsonPath("$.[*].tradePartnerLocation")
            .value(hasItem(DEFAULT_TRADE_PARTNER_LOCATION))
            .jsonPath("$.[*].tradePartnerGstComplianceScore")
            .value(hasItem(DEFAULT_TRADE_PARTNER_GST_COMPLIANCE_SCORE));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTradesWithEagerRelationshipsIsEnabled() {
        when(tradeServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(tradeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTradesWithEagerRelationshipsIsNotEnabled() {
        when(tradeServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(tradeRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getTrade() {
        // Initialize the database
        tradeRepository.save(trade).block();

        // Get the trade
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, trade.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(trade.getId().intValue()))
            .jsonPath("$.tradeUlidId")
            .value(is(DEFAULT_TRADE_ULID_ID))
            .jsonPath("$.tradeRefNo")
            .value(is(DEFAULT_TRADE_REF_NO))
            .jsonPath("$.sellerGstId")
            .value(is(DEFAULT_SELLER_GST_ID))
            .jsonPath("$.buyerGstId")
            .value(is(DEFAULT_BUYER_GST_ID))
            .jsonPath("$.tradeAmount")
            .value(is(DEFAULT_TRADE_AMOUNT))
            .jsonPath("$.invoiceDate")
            .value(is(DEFAULT_INVOICE_DATE.toString()))
            .jsonPath("$.invoiceNumber")
            .value(is(DEFAULT_INVOICE_NUMBER))
            .jsonPath("$.tradeDocType")
            .value(is(DEFAULT_TRADE_DOC_TYPE))
            .jsonPath("$.tradeDocSource")
            .value(is(DEFAULT_TRADE_DOC_SOURCE))
            .jsonPath("$.tradeDocCredibility")
            .value(is(DEFAULT_TRADE_DOC_CREDIBILITY))
            .jsonPath("$.tradeMilestoneStatus")
            .value(is(DEFAULT_TRADE_MILESTONE_STATUS))
            .jsonPath("$.tradeAdvancePayment")
            .value(is(DEFAULT_TRADE_ADVANCE_PAYMENT))
            .jsonPath("$.anchorTraderName")
            .value(is(DEFAULT_ANCHOR_TRADER_NAME))
            .jsonPath("$.tradePartnerName")
            .value(is(DEFAULT_TRADE_PARTNER_NAME))
            .jsonPath("$.invoiceTerm")
            .value(is(DEFAULT_INVOICE_TERM.intValue()))
            .jsonPath("$.acceptanceFromTradePartner")
            .value(is(DEFAULT_ACCEPTANCE_FROM_TRADE_PARTNER))
            .jsonPath("$.reasonForFinance")
            .value(is(DEFAULT_REASON_FOR_FINANCE))
            .jsonPath("$.tradePartnerSector")
            .value(is(DEFAULT_TRADE_PARTNER_SECTOR))
            .jsonPath("$.tradePartnerLocation")
            .value(is(DEFAULT_TRADE_PARTNER_LOCATION))
            .jsonPath("$.tradePartnerGstComplianceScore")
            .value(is(DEFAULT_TRADE_PARTNER_GST_COMPLIANCE_SCORE));
    }

    @Test
    void getNonExistingTrade() {
        // Get the trade
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingTrade() throws Exception {
        // Initialize the database
        tradeRepository.save(trade).block();

        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();

        // Update the trade
        Trade updatedTrade = tradeRepository.findById(trade.getId()).block();
        updatedTrade
            .tradeUlidId(UPDATED_TRADE_ULID_ID)
            .tradeRefNo(UPDATED_TRADE_REF_NO)
            .sellerGstId(UPDATED_SELLER_GST_ID)
            .buyerGstId(UPDATED_BUYER_GST_ID)
            .tradeAmount(UPDATED_TRADE_AMOUNT)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .tradeDocType(UPDATED_TRADE_DOC_TYPE)
            .tradeDocSource(UPDATED_TRADE_DOC_SOURCE)
            .tradeDocCredibility(UPDATED_TRADE_DOC_CREDIBILITY)
            .tradeMilestoneStatus(UPDATED_TRADE_MILESTONE_STATUS)
            .tradeAdvancePayment(UPDATED_TRADE_ADVANCE_PAYMENT)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .invoiceTerm(UPDATED_INVOICE_TERM)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER)
            .reasonForFinance(UPDATED_REASON_FOR_FINANCE)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .tradePartnerLocation(UPDATED_TRADE_PARTNER_LOCATION)
            .tradePartnerGstComplianceScore(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
        TradeDTO tradeDTO = tradeMapper.toDto(updatedTrade);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradeDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
        Trade testTrade = tradeList.get(tradeList.size() - 1);
        assertThat(testTrade.getTradeUlidId()).isEqualTo(UPDATED_TRADE_ULID_ID);
        assertThat(testTrade.getTradeRefNo()).isEqualTo(UPDATED_TRADE_REF_NO);
        assertThat(testTrade.getSellerGstId()).isEqualTo(UPDATED_SELLER_GST_ID);
        assertThat(testTrade.getBuyerGstId()).isEqualTo(UPDATED_BUYER_GST_ID);
        assertThat(testTrade.getTradeAmount()).isEqualTo(UPDATED_TRADE_AMOUNT);
        assertThat(testTrade.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testTrade.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testTrade.getTradeDocType()).isEqualTo(UPDATED_TRADE_DOC_TYPE);
        assertThat(testTrade.getTradeDocSource()).isEqualTo(UPDATED_TRADE_DOC_SOURCE);
        assertThat(testTrade.getTradeDocCredibility()).isEqualTo(UPDATED_TRADE_DOC_CREDIBILITY);
        assertThat(testTrade.getTradeMilestoneStatus()).isEqualTo(UPDATED_TRADE_MILESTONE_STATUS);
        assertThat(testTrade.getTradeAdvancePayment()).isEqualTo(UPDATED_TRADE_ADVANCE_PAYMENT);
        assertThat(testTrade.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testTrade.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testTrade.getInvoiceTerm()).isEqualTo(UPDATED_INVOICE_TERM);
        assertThat(testTrade.getAcceptanceFromTradePartner()).isEqualTo(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
        assertThat(testTrade.getReasonForFinance()).isEqualTo(UPDATED_REASON_FOR_FINANCE);
        assertThat(testTrade.getTradePartnerSector()).isEqualTo(UPDATED_TRADE_PARTNER_SECTOR);
        assertThat(testTrade.getTradePartnerLocation()).isEqualTo(UPDATED_TRADE_PARTNER_LOCATION);
        assertThat(testTrade.getTradePartnerGstComplianceScore()).isEqualTo(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
    }

    @Test
    void putNonExistingTrade() throws Exception {
        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();
        trade.setId(longCount.incrementAndGet());

        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradeDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTrade() throws Exception {
        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();
        trade.setId(longCount.incrementAndGet());

        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTrade() throws Exception {
        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();
        trade.setId(longCount.incrementAndGet());

        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTradeWithPatch() throws Exception {
        // Initialize the database
        tradeRepository.save(trade).block();

        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();

        // Update the trade using partial update
        Trade partialUpdatedTrade = new Trade();
        partialUpdatedTrade.setId(trade.getId());

        partialUpdatedTrade
            .tradeUlidId(UPDATED_TRADE_ULID_ID)
            .buyerGstId(UPDATED_BUYER_GST_ID)
            .tradeAmount(UPDATED_TRADE_AMOUNT)
            .tradeDocCredibility(UPDATED_TRADE_DOC_CREDIBILITY)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .invoiceTerm(UPDATED_INVOICE_TERM)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER)
            .reasonForFinance(UPDATED_REASON_FOR_FINANCE)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .tradePartnerGstComplianceScore(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTrade.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTrade))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
        Trade testTrade = tradeList.get(tradeList.size() - 1);
        assertThat(testTrade.getTradeUlidId()).isEqualTo(UPDATED_TRADE_ULID_ID);
        assertThat(testTrade.getTradeRefNo()).isEqualTo(DEFAULT_TRADE_REF_NO);
        assertThat(testTrade.getSellerGstId()).isEqualTo(DEFAULT_SELLER_GST_ID);
        assertThat(testTrade.getBuyerGstId()).isEqualTo(UPDATED_BUYER_GST_ID);
        assertThat(testTrade.getTradeAmount()).isEqualTo(UPDATED_TRADE_AMOUNT);
        assertThat(testTrade.getInvoiceDate()).isEqualTo(DEFAULT_INVOICE_DATE);
        assertThat(testTrade.getInvoiceNumber()).isEqualTo(DEFAULT_INVOICE_NUMBER);
        assertThat(testTrade.getTradeDocType()).isEqualTo(DEFAULT_TRADE_DOC_TYPE);
        assertThat(testTrade.getTradeDocSource()).isEqualTo(DEFAULT_TRADE_DOC_SOURCE);
        assertThat(testTrade.getTradeDocCredibility()).isEqualTo(UPDATED_TRADE_DOC_CREDIBILITY);
        assertThat(testTrade.getTradeMilestoneStatus()).isEqualTo(DEFAULT_TRADE_MILESTONE_STATUS);
        assertThat(testTrade.getTradeAdvancePayment()).isEqualTo(DEFAULT_TRADE_ADVANCE_PAYMENT);
        assertThat(testTrade.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testTrade.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testTrade.getInvoiceTerm()).isEqualTo(UPDATED_INVOICE_TERM);
        assertThat(testTrade.getAcceptanceFromTradePartner()).isEqualTo(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
        assertThat(testTrade.getReasonForFinance()).isEqualTo(UPDATED_REASON_FOR_FINANCE);
        assertThat(testTrade.getTradePartnerSector()).isEqualTo(UPDATED_TRADE_PARTNER_SECTOR);
        assertThat(testTrade.getTradePartnerLocation()).isEqualTo(DEFAULT_TRADE_PARTNER_LOCATION);
        assertThat(testTrade.getTradePartnerGstComplianceScore()).isEqualTo(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
    }

    @Test
    void fullUpdateTradeWithPatch() throws Exception {
        // Initialize the database
        tradeRepository.save(trade).block();

        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();

        // Update the trade using partial update
        Trade partialUpdatedTrade = new Trade();
        partialUpdatedTrade.setId(trade.getId());

        partialUpdatedTrade
            .tradeUlidId(UPDATED_TRADE_ULID_ID)
            .tradeRefNo(UPDATED_TRADE_REF_NO)
            .sellerGstId(UPDATED_SELLER_GST_ID)
            .buyerGstId(UPDATED_BUYER_GST_ID)
            .tradeAmount(UPDATED_TRADE_AMOUNT)
            .invoiceDate(UPDATED_INVOICE_DATE)
            .invoiceNumber(UPDATED_INVOICE_NUMBER)
            .tradeDocType(UPDATED_TRADE_DOC_TYPE)
            .tradeDocSource(UPDATED_TRADE_DOC_SOURCE)
            .tradeDocCredibility(UPDATED_TRADE_DOC_CREDIBILITY)
            .tradeMilestoneStatus(UPDATED_TRADE_MILESTONE_STATUS)
            .tradeAdvancePayment(UPDATED_TRADE_ADVANCE_PAYMENT)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .invoiceTerm(UPDATED_INVOICE_TERM)
            .acceptanceFromTradePartner(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER)
            .reasonForFinance(UPDATED_REASON_FOR_FINANCE)
            .tradePartnerSector(UPDATED_TRADE_PARTNER_SECTOR)
            .tradePartnerLocation(UPDATED_TRADE_PARTNER_LOCATION)
            .tradePartnerGstComplianceScore(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTrade.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTrade))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
        Trade testTrade = tradeList.get(tradeList.size() - 1);
        assertThat(testTrade.getTradeUlidId()).isEqualTo(UPDATED_TRADE_ULID_ID);
        assertThat(testTrade.getTradeRefNo()).isEqualTo(UPDATED_TRADE_REF_NO);
        assertThat(testTrade.getSellerGstId()).isEqualTo(UPDATED_SELLER_GST_ID);
        assertThat(testTrade.getBuyerGstId()).isEqualTo(UPDATED_BUYER_GST_ID);
        assertThat(testTrade.getTradeAmount()).isEqualTo(UPDATED_TRADE_AMOUNT);
        assertThat(testTrade.getInvoiceDate()).isEqualTo(UPDATED_INVOICE_DATE);
        assertThat(testTrade.getInvoiceNumber()).isEqualTo(UPDATED_INVOICE_NUMBER);
        assertThat(testTrade.getTradeDocType()).isEqualTo(UPDATED_TRADE_DOC_TYPE);
        assertThat(testTrade.getTradeDocSource()).isEqualTo(UPDATED_TRADE_DOC_SOURCE);
        assertThat(testTrade.getTradeDocCredibility()).isEqualTo(UPDATED_TRADE_DOC_CREDIBILITY);
        assertThat(testTrade.getTradeMilestoneStatus()).isEqualTo(UPDATED_TRADE_MILESTONE_STATUS);
        assertThat(testTrade.getTradeAdvancePayment()).isEqualTo(UPDATED_TRADE_ADVANCE_PAYMENT);
        assertThat(testTrade.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testTrade.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testTrade.getInvoiceTerm()).isEqualTo(UPDATED_INVOICE_TERM);
        assertThat(testTrade.getAcceptanceFromTradePartner()).isEqualTo(UPDATED_ACCEPTANCE_FROM_TRADE_PARTNER);
        assertThat(testTrade.getReasonForFinance()).isEqualTo(UPDATED_REASON_FOR_FINANCE);
        assertThat(testTrade.getTradePartnerSector()).isEqualTo(UPDATED_TRADE_PARTNER_SECTOR);
        assertThat(testTrade.getTradePartnerLocation()).isEqualTo(UPDATED_TRADE_PARTNER_LOCATION);
        assertThat(testTrade.getTradePartnerGstComplianceScore()).isEqualTo(UPDATED_TRADE_PARTNER_GST_COMPLIANCE_SCORE);
    }

    @Test
    void patchNonExistingTrade() throws Exception {
        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();
        trade.setId(longCount.incrementAndGet());

        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, tradeDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTrade() throws Exception {
        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();
        trade.setId(longCount.incrementAndGet());

        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTrade() throws Exception {
        int databaseSizeBeforeUpdate = tradeRepository.findAll().collectList().block().size();
        trade.setId(longCount.incrementAndGet());

        // Create the Trade
        TradeDTO tradeDTO = tradeMapper.toDto(trade);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Trade in the database
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTrade() {
        // Initialize the database
        tradeRepository.save(trade).block();

        int databaseSizeBeforeDelete = tradeRepository.findAll().collectList().block().size();

        // Delete the trade
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, trade.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Trade> tradeList = tradeRepository.findAll().collectList().block();
        assertThat(tradeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
