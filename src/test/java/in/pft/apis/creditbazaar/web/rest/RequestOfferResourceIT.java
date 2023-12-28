package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.RequestOffer;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.RequestOfferRepository;
import in.pft.apis.creditbazaar.service.RequestOfferService;
import in.pft.apis.creditbazaar.service.dto.RequestOfferDTO;
import in.pft.apis.creditbazaar.service.mapper.RequestOfferMapper;
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
 * Integration tests for the {@link RequestOfferResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class RequestOfferResourceIT {

    private static final String DEFAULT_REQ_OFF_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQ_OFF_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_OFFER_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_OFFER_REF_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_OFFER_VALUE = 1L;
    private static final Long UPDATED_OFFER_VALUE = 2L;

    private static final Long DEFAULT_REQUEST_AMT = 1L;
    private static final Long UPDATED_REQUEST_AMT = 2L;

    private static final Long DEFAULT_TRADE_VALUE = 1L;
    private static final Long UPDATED_TRADE_VALUE = 2L;

    private static final Float DEFAULT_MARGIN_PTG = 1F;
    private static final Float UPDATED_MARGIN_PTG = 2F;

    private static final Long DEFAULT_MARGIN_VALUE = 1L;
    private static final Long UPDATED_MARGIN_VALUE = 2L;

    private static final Long DEFAULT_AMOUNT_AFT_MARGIN = 1L;
    private static final Long UPDATED_AMOUNT_AFT_MARGIN = 2L;

    private static final Float DEFAULT_INTEREST_PTG = 1F;
    private static final Float UPDATED_INTEREST_PTG = 2F;

    private static final Long DEFAULT_TERM = 1L;
    private static final Long UPDATED_TERM = 2L;

    private static final Float DEFAULT_INTEREST_VALUE = 1F;
    private static final Float UPDATED_INTEREST_VALUE = 2F;

    private static final Long DEFAULT_NET_AMOUNT = 1L;
    private static final Long UPDATED_NET_AMOUNT = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FINANCE_REQUEST_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FINANCE_REQUEST_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ANCHOR_TRADER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_GST = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_GST = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_GST = "BBBBBBBBBB";

    private static final String DEFAULT_SELLER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SELLER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BUYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BUYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_GST_COMPLIANCE_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_ERP_PEER_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/request-offers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RequestOfferRepository requestOfferRepository;

    @Mock
    private RequestOfferRepository requestOfferRepositoryMock;

    @Autowired
    private RequestOfferMapper requestOfferMapper;

    @Mock
    private RequestOfferService requestOfferServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private RequestOffer requestOffer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestOffer createEntity(EntityManager em) {
        RequestOffer requestOffer = new RequestOffer()
            .reqOffId(DEFAULT_REQ_OFF_ID)
            .requestOfferRefNo(DEFAULT_REQUEST_OFFER_REF_NO)
            .offerValue(DEFAULT_OFFER_VALUE)
            .requestAmt(DEFAULT_REQUEST_AMT)
            .tradeValue(DEFAULT_TRADE_VALUE)
            .marginPtg(DEFAULT_MARGIN_PTG)
            .marginValue(DEFAULT_MARGIN_VALUE)
            .amountAftMargin(DEFAULT_AMOUNT_AFT_MARGIN)
            .interestPtg(DEFAULT_INTEREST_PTG)
            .term(DEFAULT_TERM)
            .interestValue(DEFAULT_INTEREST_VALUE)
            .netAmount(DEFAULT_NET_AMOUNT)
            .status(DEFAULT_STATUS)
            .financeRequestDate(DEFAULT_FINANCE_REQUEST_DATE)
            .anchorTraderName(DEFAULT_ANCHOR_TRADER_NAME)
            .tradePartnerName(DEFAULT_TRADE_PARTNER_NAME)
            .anchorTraderGst(DEFAULT_ANCHOR_TRADER_GST)
            .tradePartnerGst(DEFAULT_TRADE_PARTNER_GST)
            .sellerName(DEFAULT_SELLER_NAME)
            .buyerName(DEFAULT_BUYER_NAME)
            .anchorTraderGstComplianceScore(DEFAULT_ANCHOR_TRADER_GST_COMPLIANCE_SCORE)
            .anchorTraderErpPeerScore(DEFAULT_ANCHOR_TRADER_ERP_PEER_SCORE);
        return requestOffer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RequestOffer createUpdatedEntity(EntityManager em) {
        RequestOffer requestOffer = new RequestOffer()
            .reqOffId(UPDATED_REQ_OFF_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .offerValue(UPDATED_OFFER_VALUE)
            .requestAmt(UPDATED_REQUEST_AMT)
            .tradeValue(UPDATED_TRADE_VALUE)
            .marginPtg(UPDATED_MARGIN_PTG)
            .marginValue(UPDATED_MARGIN_VALUE)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .interestPtg(UPDATED_INTEREST_PTG)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS)
            .financeRequestDate(UPDATED_FINANCE_REQUEST_DATE)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .anchorTraderGst(UPDATED_ANCHOR_TRADER_GST)
            .tradePartnerGst(UPDATED_TRADE_PARTNER_GST)
            .sellerName(UPDATED_SELLER_NAME)
            .buyerName(UPDATED_BUYER_NAME)
            .anchorTraderGstComplianceScore(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE)
            .anchorTraderErpPeerScore(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);
        return requestOffer;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(RequestOffer.class).block();
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
        requestOffer = createEntity(em);
    }

    @Test
    void createRequestOffer() throws Exception {
        int databaseSizeBeforeCreate = requestOfferRepository.findAll().collectList().block().size();
        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeCreate + 1);
        RequestOffer testRequestOffer = requestOfferList.get(requestOfferList.size() - 1);
        assertThat(testRequestOffer.getReqOffId()).isEqualTo(DEFAULT_REQ_OFF_ID);
        assertThat(testRequestOffer.getRequestOfferRefNo()).isEqualTo(DEFAULT_REQUEST_OFFER_REF_NO);
        assertThat(testRequestOffer.getOfferValue()).isEqualTo(DEFAULT_OFFER_VALUE);
        assertThat(testRequestOffer.getRequestAmt()).isEqualTo(DEFAULT_REQUEST_AMT);
        assertThat(testRequestOffer.getTradeValue()).isEqualTo(DEFAULT_TRADE_VALUE);
        assertThat(testRequestOffer.getMarginPtg()).isEqualTo(DEFAULT_MARGIN_PTG);
        assertThat(testRequestOffer.getMarginValue()).isEqualTo(DEFAULT_MARGIN_VALUE);
        assertThat(testRequestOffer.getAmountAftMargin()).isEqualTo(DEFAULT_AMOUNT_AFT_MARGIN);
        assertThat(testRequestOffer.getInterestPtg()).isEqualTo(DEFAULT_INTEREST_PTG);
        assertThat(testRequestOffer.getTerm()).isEqualTo(DEFAULT_TERM);
        assertThat(testRequestOffer.getInterestValue()).isEqualTo(DEFAULT_INTEREST_VALUE);
        assertThat(testRequestOffer.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testRequestOffer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testRequestOffer.getFinanceRequestDate()).isEqualTo(DEFAULT_FINANCE_REQUEST_DATE);
        assertThat(testRequestOffer.getAnchorTraderName()).isEqualTo(DEFAULT_ANCHOR_TRADER_NAME);
        assertThat(testRequestOffer.getTradePartnerName()).isEqualTo(DEFAULT_TRADE_PARTNER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGst()).isEqualTo(DEFAULT_ANCHOR_TRADER_GST);
        assertThat(testRequestOffer.getTradePartnerGst()).isEqualTo(DEFAULT_TRADE_PARTNER_GST);
        assertThat(testRequestOffer.getSellerName()).isEqualTo(DEFAULT_SELLER_NAME);
        assertThat(testRequestOffer.getBuyerName()).isEqualTo(DEFAULT_BUYER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGstComplianceScore()).isEqualTo(DEFAULT_ANCHOR_TRADER_GST_COMPLIANCE_SCORE);
        assertThat(testRequestOffer.getAnchorTraderErpPeerScore()).isEqualTo(DEFAULT_ANCHOR_TRADER_ERP_PEER_SCORE);
    }

    @Test
    void createRequestOfferWithExistingId() throws Exception {
        // Create the RequestOffer with an existing ID
        requestOffer.setId(1L);
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        int databaseSizeBeforeCreate = requestOfferRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkOfferValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setOfferValue(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRequestAmtIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setRequestAmt(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTradeValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setTradeValue(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMarginPtgIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setMarginPtg(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMarginValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setMarginValue(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAmountAftMarginIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setAmountAftMargin(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInterestPtgIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setInterestPtg(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTermIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setTerm(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInterestValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setInterestValue(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNetAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setNetAmount(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setStatus(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkFinanceRequestDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setFinanceRequestDate(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAnchorTraderNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setAnchorTraderName(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTradePartnerNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = requestOfferRepository.findAll().collectList().block().size();
        // set the field null
        requestOffer.setTradePartnerName(null);

        // Create the RequestOffer, which fails.
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllRequestOffers() {
        // Initialize the database
        requestOfferRepository.save(requestOffer).block();

        // Get all the requestOfferList
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
            .value(hasItem(requestOffer.getId().intValue()))
            .jsonPath("$.[*].reqOffId")
            .value(hasItem(DEFAULT_REQ_OFF_ID))
            .jsonPath("$.[*].requestOfferRefNo")
            .value(hasItem(DEFAULT_REQUEST_OFFER_REF_NO))
            .jsonPath("$.[*].offerValue")
            .value(hasItem(DEFAULT_OFFER_VALUE.intValue()))
            .jsonPath("$.[*].requestAmt")
            .value(hasItem(DEFAULT_REQUEST_AMT.intValue()))
            .jsonPath("$.[*].tradeValue")
            .value(hasItem(DEFAULT_TRADE_VALUE.intValue()))
            .jsonPath("$.[*].marginPtg")
            .value(hasItem(DEFAULT_MARGIN_PTG.doubleValue()))
            .jsonPath("$.[*].marginValue")
            .value(hasItem(DEFAULT_MARGIN_VALUE.intValue()))
            .jsonPath("$.[*].amountAftMargin")
            .value(hasItem(DEFAULT_AMOUNT_AFT_MARGIN.intValue()))
            .jsonPath("$.[*].interestPtg")
            .value(hasItem(DEFAULT_INTEREST_PTG.doubleValue()))
            .jsonPath("$.[*].term")
            .value(hasItem(DEFAULT_TERM.intValue()))
            .jsonPath("$.[*].interestValue")
            .value(hasItem(DEFAULT_INTEREST_VALUE.doubleValue()))
            .jsonPath("$.[*].netAmount")
            .value(hasItem(DEFAULT_NET_AMOUNT.intValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].financeRequestDate")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_DATE.toString()))
            .jsonPath("$.[*].anchorTraderName")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_NAME))
            .jsonPath("$.[*].tradePartnerName")
            .value(hasItem(DEFAULT_TRADE_PARTNER_NAME))
            .jsonPath("$.[*].anchorTraderGst")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_GST))
            .jsonPath("$.[*].tradePartnerGst")
            .value(hasItem(DEFAULT_TRADE_PARTNER_GST))
            .jsonPath("$.[*].sellerName")
            .value(hasItem(DEFAULT_SELLER_NAME))
            .jsonPath("$.[*].buyerName")
            .value(hasItem(DEFAULT_BUYER_NAME))
            .jsonPath("$.[*].anchorTraderGstComplianceScore")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_GST_COMPLIANCE_SCORE))
            .jsonPath("$.[*].anchorTraderErpPeerScore")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_ERP_PEER_SCORE));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRequestOffersWithEagerRelationshipsIsEnabled() {
        when(requestOfferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(requestOfferServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllRequestOffersWithEagerRelationshipsIsNotEnabled() {
        when(requestOfferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(requestOfferRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getRequestOffer() {
        // Initialize the database
        requestOfferRepository.save(requestOffer).block();

        // Get the requestOffer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, requestOffer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(requestOffer.getId().intValue()))
            .jsonPath("$.reqOffId")
            .value(is(DEFAULT_REQ_OFF_ID))
            .jsonPath("$.requestOfferRefNo")
            .value(is(DEFAULT_REQUEST_OFFER_REF_NO))
            .jsonPath("$.offerValue")
            .value(is(DEFAULT_OFFER_VALUE.intValue()))
            .jsonPath("$.requestAmt")
            .value(is(DEFAULT_REQUEST_AMT.intValue()))
            .jsonPath("$.tradeValue")
            .value(is(DEFAULT_TRADE_VALUE.intValue()))
            .jsonPath("$.marginPtg")
            .value(is(DEFAULT_MARGIN_PTG.doubleValue()))
            .jsonPath("$.marginValue")
            .value(is(DEFAULT_MARGIN_VALUE.intValue()))
            .jsonPath("$.amountAftMargin")
            .value(is(DEFAULT_AMOUNT_AFT_MARGIN.intValue()))
            .jsonPath("$.interestPtg")
            .value(is(DEFAULT_INTEREST_PTG.doubleValue()))
            .jsonPath("$.term")
            .value(is(DEFAULT_TERM.intValue()))
            .jsonPath("$.interestValue")
            .value(is(DEFAULT_INTEREST_VALUE.doubleValue()))
            .jsonPath("$.netAmount")
            .value(is(DEFAULT_NET_AMOUNT.intValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.financeRequestDate")
            .value(is(DEFAULT_FINANCE_REQUEST_DATE.toString()))
            .jsonPath("$.anchorTraderName")
            .value(is(DEFAULT_ANCHOR_TRADER_NAME))
            .jsonPath("$.tradePartnerName")
            .value(is(DEFAULT_TRADE_PARTNER_NAME))
            .jsonPath("$.anchorTraderGst")
            .value(is(DEFAULT_ANCHOR_TRADER_GST))
            .jsonPath("$.tradePartnerGst")
            .value(is(DEFAULT_TRADE_PARTNER_GST))
            .jsonPath("$.sellerName")
            .value(is(DEFAULT_SELLER_NAME))
            .jsonPath("$.buyerName")
            .value(is(DEFAULT_BUYER_NAME))
            .jsonPath("$.anchorTraderGstComplianceScore")
            .value(is(DEFAULT_ANCHOR_TRADER_GST_COMPLIANCE_SCORE))
            .jsonPath("$.anchorTraderErpPeerScore")
            .value(is(DEFAULT_ANCHOR_TRADER_ERP_PEER_SCORE));
    }

    @Test
    void getNonExistingRequestOffer() {
        // Get the requestOffer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingRequestOffer() throws Exception {
        // Initialize the database
        requestOfferRepository.save(requestOffer).block();

        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();

        // Update the requestOffer
        RequestOffer updatedRequestOffer = requestOfferRepository.findById(requestOffer.getId()).block();
        updatedRequestOffer
            .reqOffId(UPDATED_REQ_OFF_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .offerValue(UPDATED_OFFER_VALUE)
            .requestAmt(UPDATED_REQUEST_AMT)
            .tradeValue(UPDATED_TRADE_VALUE)
            .marginPtg(UPDATED_MARGIN_PTG)
            .marginValue(UPDATED_MARGIN_VALUE)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .interestPtg(UPDATED_INTEREST_PTG)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS)
            .financeRequestDate(UPDATED_FINANCE_REQUEST_DATE)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .anchorTraderGst(UPDATED_ANCHOR_TRADER_GST)
            .tradePartnerGst(UPDATED_TRADE_PARTNER_GST)
            .sellerName(UPDATED_SELLER_NAME)
            .buyerName(UPDATED_BUYER_NAME)
            .anchorTraderGstComplianceScore(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE)
            .anchorTraderErpPeerScore(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(updatedRequestOffer);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, requestOfferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
        RequestOffer testRequestOffer = requestOfferList.get(requestOfferList.size() - 1);
        assertThat(testRequestOffer.getReqOffId()).isEqualTo(UPDATED_REQ_OFF_ID);
        assertThat(testRequestOffer.getRequestOfferRefNo()).isEqualTo(UPDATED_REQUEST_OFFER_REF_NO);
        assertThat(testRequestOffer.getOfferValue()).isEqualTo(UPDATED_OFFER_VALUE);
        assertThat(testRequestOffer.getRequestAmt()).isEqualTo(UPDATED_REQUEST_AMT);
        assertThat(testRequestOffer.getTradeValue()).isEqualTo(UPDATED_TRADE_VALUE);
        assertThat(testRequestOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testRequestOffer.getMarginValue()).isEqualTo(UPDATED_MARGIN_VALUE);
        assertThat(testRequestOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testRequestOffer.getInterestPtg()).isEqualTo(UPDATED_INTEREST_PTG);
        assertThat(testRequestOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testRequestOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testRequestOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testRequestOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRequestOffer.getFinanceRequestDate()).isEqualTo(UPDATED_FINANCE_REQUEST_DATE);
        assertThat(testRequestOffer.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testRequestOffer.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGst()).isEqualTo(UPDATED_ANCHOR_TRADER_GST);
        assertThat(testRequestOffer.getTradePartnerGst()).isEqualTo(UPDATED_TRADE_PARTNER_GST);
        assertThat(testRequestOffer.getSellerName()).isEqualTo(UPDATED_SELLER_NAME);
        assertThat(testRequestOffer.getBuyerName()).isEqualTo(UPDATED_BUYER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGstComplianceScore()).isEqualTo(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE);
        assertThat(testRequestOffer.getAnchorTraderErpPeerScore()).isEqualTo(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);
    }

    @Test
    void putNonExistingRequestOffer() throws Exception {
        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();
        requestOffer.setId(longCount.incrementAndGet());

        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, requestOfferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchRequestOffer() throws Exception {
        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();
        requestOffer.setId(longCount.incrementAndGet());

        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamRequestOffer() throws Exception {
        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();
        requestOffer.setId(longCount.incrementAndGet());

        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateRequestOfferWithPatch() throws Exception {
        // Initialize the database
        requestOfferRepository.save(requestOffer).block();

        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();

        // Update the requestOffer using partial update
        RequestOffer partialUpdatedRequestOffer = new RequestOffer();
        partialUpdatedRequestOffer.setId(requestOffer.getId());

        partialUpdatedRequestOffer
            .reqOffId(UPDATED_REQ_OFF_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .tradeValue(UPDATED_TRADE_VALUE)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .status(UPDATED_STATUS)
            .financeRequestDate(UPDATED_FINANCE_REQUEST_DATE)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .tradePartnerGst(UPDATED_TRADE_PARTNER_GST)
            .buyerName(UPDATED_BUYER_NAME)
            .anchorTraderGstComplianceScore(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE)
            .anchorTraderErpPeerScore(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRequestOffer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestOffer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
        RequestOffer testRequestOffer = requestOfferList.get(requestOfferList.size() - 1);
        assertThat(testRequestOffer.getReqOffId()).isEqualTo(UPDATED_REQ_OFF_ID);
        assertThat(testRequestOffer.getRequestOfferRefNo()).isEqualTo(UPDATED_REQUEST_OFFER_REF_NO);
        assertThat(testRequestOffer.getOfferValue()).isEqualTo(DEFAULT_OFFER_VALUE);
        assertThat(testRequestOffer.getRequestAmt()).isEqualTo(DEFAULT_REQUEST_AMT);
        assertThat(testRequestOffer.getTradeValue()).isEqualTo(UPDATED_TRADE_VALUE);
        assertThat(testRequestOffer.getMarginPtg()).isEqualTo(DEFAULT_MARGIN_PTG);
        assertThat(testRequestOffer.getMarginValue()).isEqualTo(DEFAULT_MARGIN_VALUE);
        assertThat(testRequestOffer.getAmountAftMargin()).isEqualTo(DEFAULT_AMOUNT_AFT_MARGIN);
        assertThat(testRequestOffer.getInterestPtg()).isEqualTo(DEFAULT_INTEREST_PTG);
        assertThat(testRequestOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testRequestOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testRequestOffer.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testRequestOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRequestOffer.getFinanceRequestDate()).isEqualTo(UPDATED_FINANCE_REQUEST_DATE);
        assertThat(testRequestOffer.getAnchorTraderName()).isEqualTo(DEFAULT_ANCHOR_TRADER_NAME);
        assertThat(testRequestOffer.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGst()).isEqualTo(DEFAULT_ANCHOR_TRADER_GST);
        assertThat(testRequestOffer.getTradePartnerGst()).isEqualTo(UPDATED_TRADE_PARTNER_GST);
        assertThat(testRequestOffer.getSellerName()).isEqualTo(DEFAULT_SELLER_NAME);
        assertThat(testRequestOffer.getBuyerName()).isEqualTo(UPDATED_BUYER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGstComplianceScore()).isEqualTo(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE);
        assertThat(testRequestOffer.getAnchorTraderErpPeerScore()).isEqualTo(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);
    }

    @Test
    void fullUpdateRequestOfferWithPatch() throws Exception {
        // Initialize the database
        requestOfferRepository.save(requestOffer).block();

        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();

        // Update the requestOffer using partial update
        RequestOffer partialUpdatedRequestOffer = new RequestOffer();
        partialUpdatedRequestOffer.setId(requestOffer.getId());

        partialUpdatedRequestOffer
            .reqOffId(UPDATED_REQ_OFF_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .offerValue(UPDATED_OFFER_VALUE)
            .requestAmt(UPDATED_REQUEST_AMT)
            .tradeValue(UPDATED_TRADE_VALUE)
            .marginPtg(UPDATED_MARGIN_PTG)
            .marginValue(UPDATED_MARGIN_VALUE)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .interestPtg(UPDATED_INTEREST_PTG)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS)
            .financeRequestDate(UPDATED_FINANCE_REQUEST_DATE)
            .anchorTraderName(UPDATED_ANCHOR_TRADER_NAME)
            .tradePartnerName(UPDATED_TRADE_PARTNER_NAME)
            .anchorTraderGst(UPDATED_ANCHOR_TRADER_GST)
            .tradePartnerGst(UPDATED_TRADE_PARTNER_GST)
            .sellerName(UPDATED_SELLER_NAME)
            .buyerName(UPDATED_BUYER_NAME)
            .anchorTraderGstComplianceScore(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE)
            .anchorTraderErpPeerScore(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedRequestOffer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedRequestOffer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
        RequestOffer testRequestOffer = requestOfferList.get(requestOfferList.size() - 1);
        assertThat(testRequestOffer.getReqOffId()).isEqualTo(UPDATED_REQ_OFF_ID);
        assertThat(testRequestOffer.getRequestOfferRefNo()).isEqualTo(UPDATED_REQUEST_OFFER_REF_NO);
        assertThat(testRequestOffer.getOfferValue()).isEqualTo(UPDATED_OFFER_VALUE);
        assertThat(testRequestOffer.getRequestAmt()).isEqualTo(UPDATED_REQUEST_AMT);
        assertThat(testRequestOffer.getTradeValue()).isEqualTo(UPDATED_TRADE_VALUE);
        assertThat(testRequestOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testRequestOffer.getMarginValue()).isEqualTo(UPDATED_MARGIN_VALUE);
        assertThat(testRequestOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testRequestOffer.getInterestPtg()).isEqualTo(UPDATED_INTEREST_PTG);
        assertThat(testRequestOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testRequestOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testRequestOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testRequestOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testRequestOffer.getFinanceRequestDate()).isEqualTo(UPDATED_FINANCE_REQUEST_DATE);
        assertThat(testRequestOffer.getAnchorTraderName()).isEqualTo(UPDATED_ANCHOR_TRADER_NAME);
        assertThat(testRequestOffer.getTradePartnerName()).isEqualTo(UPDATED_TRADE_PARTNER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGst()).isEqualTo(UPDATED_ANCHOR_TRADER_GST);
        assertThat(testRequestOffer.getTradePartnerGst()).isEqualTo(UPDATED_TRADE_PARTNER_GST);
        assertThat(testRequestOffer.getSellerName()).isEqualTo(UPDATED_SELLER_NAME);
        assertThat(testRequestOffer.getBuyerName()).isEqualTo(UPDATED_BUYER_NAME);
        assertThat(testRequestOffer.getAnchorTraderGstComplianceScore()).isEqualTo(UPDATED_ANCHOR_TRADER_GST_COMPLIANCE_SCORE);
        assertThat(testRequestOffer.getAnchorTraderErpPeerScore()).isEqualTo(UPDATED_ANCHOR_TRADER_ERP_PEER_SCORE);
    }

    @Test
    void patchNonExistingRequestOffer() throws Exception {
        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();
        requestOffer.setId(longCount.incrementAndGet());

        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, requestOfferDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchRequestOffer() throws Exception {
        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();
        requestOffer.setId(longCount.incrementAndGet());

        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamRequestOffer() throws Exception {
        int databaseSizeBeforeUpdate = requestOfferRepository.findAll().collectList().block().size();
        requestOffer.setId(longCount.incrementAndGet());

        // Create the RequestOffer
        RequestOfferDTO requestOfferDTO = requestOfferMapper.toDto(requestOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(requestOfferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the RequestOffer in the database
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteRequestOffer() {
        // Initialize the database
        requestOfferRepository.save(requestOffer).block();

        int databaseSizeBeforeDelete = requestOfferRepository.findAll().collectList().block().size();

        // Delete the requestOffer
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, requestOffer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<RequestOffer> requestOfferList = requestOfferRepository.findAll().collectList().block();
        assertThat(requestOfferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
