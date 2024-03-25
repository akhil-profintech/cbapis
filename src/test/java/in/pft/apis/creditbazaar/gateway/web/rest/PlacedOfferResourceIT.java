package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.PlacedOffer;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.PlacedOfferRepository;
import in.pft.apis.creditbazaar.gateway.service.PlacedOfferService;
import in.pft.apis.creditbazaar.gateway.service.dto.PlacedOfferDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.PlacedOfferMapper;
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
 * Integration tests for the {@link PlacedOfferResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class PlacedOfferResourceIT {

    private static final String DEFAULT_PLACED_OFFER_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_PLACED_OFFER_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PLACED_OFFER_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_PLACED_OFFER_REF_NO = "BBBBBBBBBB";

    private static final String DEFAULT_REQ_OFF_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_REQ_OFF_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_OFFER_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_OFFER_REF_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_VALUE = 1L;
    private static final Long UPDATED_VALUE = 2L;

    private static final Long DEFAULT_REQ_AMOUNT = 1L;
    private static final Long UPDATED_REQ_AMOUNT = 2L;

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

    private static final Long DEFAULT_INTEREST_VALUE = 1L;
    private static final Long UPDATED_INTEREST_VALUE = 2L;

    private static final Long DEFAULT_NET_AMOUNT = 1L;
    private static final Long UPDATED_NET_AMOUNT = 2L;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_OFFER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PLACED_OFFER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLACED_OFFER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ANCHOR_TRADER = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER = "BBBBBBBBBB";

    private static final String DEFAULT_DISBURSAL_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_DISBURSAL_AMOUNT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/placed-offers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PlacedOfferRepository placedOfferRepository;

    @Mock
    private PlacedOfferRepository placedOfferRepositoryMock;

    @Autowired
    private PlacedOfferMapper placedOfferMapper;

    @Mock
    private PlacedOfferService placedOfferServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private PlacedOffer placedOffer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlacedOffer createEntity(EntityManager em) {
        PlacedOffer placedOffer = new PlacedOffer()
            .placedOfferUlidId(DEFAULT_PLACED_OFFER_ULID_ID)
            .placedOfferRefNo(DEFAULT_PLACED_OFFER_REF_NO)
            .reqOffUlidId(DEFAULT_REQ_OFF_ULID_ID)
            .requestOfferRefNo(DEFAULT_REQUEST_OFFER_REF_NO)
            .value(DEFAULT_VALUE)
            .reqAmount(DEFAULT_REQ_AMOUNT)
            .marginPtg(DEFAULT_MARGIN_PTG)
            .marginValue(DEFAULT_MARGIN_VALUE)
            .amountAftMargin(DEFAULT_AMOUNT_AFT_MARGIN)
            .interestPtg(DEFAULT_INTEREST_PTG)
            .term(DEFAULT_TERM)
            .interestValue(DEFAULT_INTEREST_VALUE)
            .netAmount(DEFAULT_NET_AMOUNT)
            .status(DEFAULT_STATUS)
            .offerDate(DEFAULT_OFFER_DATE)
            .placedOfferDate(DEFAULT_PLACED_OFFER_DATE)
            .anchorTrader(DEFAULT_ANCHOR_TRADER)
            .tradePartner(DEFAULT_TRADE_PARTNER)
            .disbursalAmount(DEFAULT_DISBURSAL_AMOUNT);
        return placedOffer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlacedOffer createUpdatedEntity(EntityManager em) {
        PlacedOffer placedOffer = new PlacedOffer()
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .placedOfferRefNo(UPDATED_PLACED_OFFER_REF_NO)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .value(UPDATED_VALUE)
            .reqAmount(UPDATED_REQ_AMOUNT)
            .marginPtg(UPDATED_MARGIN_PTG)
            .marginValue(UPDATED_MARGIN_VALUE)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .interestPtg(UPDATED_INTEREST_PTG)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS)
            .offerDate(UPDATED_OFFER_DATE)
            .placedOfferDate(UPDATED_PLACED_OFFER_DATE)
            .anchorTrader(UPDATED_ANCHOR_TRADER)
            .tradePartner(UPDATED_TRADE_PARTNER)
            .disbursalAmount(UPDATED_DISBURSAL_AMOUNT);
        return placedOffer;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(PlacedOffer.class).block();
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
        placedOffer = createEntity(em);
    }

    @Test
    void createPlacedOffer() throws Exception {
        int databaseSizeBeforeCreate = placedOfferRepository.findAll().collectList().block().size();
        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeCreate + 1);
        PlacedOffer testPlacedOffer = placedOfferList.get(placedOfferList.size() - 1);
        assertThat(testPlacedOffer.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testPlacedOffer.getPlacedOfferRefNo()).isEqualTo(DEFAULT_PLACED_OFFER_REF_NO);
        assertThat(testPlacedOffer.getReqOffUlidId()).isEqualTo(DEFAULT_REQ_OFF_ULID_ID);
        assertThat(testPlacedOffer.getRequestOfferRefNo()).isEqualTo(DEFAULT_REQUEST_OFFER_REF_NO);
        assertThat(testPlacedOffer.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testPlacedOffer.getReqAmount()).isEqualTo(DEFAULT_REQ_AMOUNT);
        assertThat(testPlacedOffer.getMarginPtg()).isEqualTo(DEFAULT_MARGIN_PTG);
        assertThat(testPlacedOffer.getMarginValue()).isEqualTo(DEFAULT_MARGIN_VALUE);
        assertThat(testPlacedOffer.getAmountAftMargin()).isEqualTo(DEFAULT_AMOUNT_AFT_MARGIN);
        assertThat(testPlacedOffer.getInterestPtg()).isEqualTo(DEFAULT_INTEREST_PTG);
        assertThat(testPlacedOffer.getTerm()).isEqualTo(DEFAULT_TERM);
        assertThat(testPlacedOffer.getInterestValue()).isEqualTo(DEFAULT_INTEREST_VALUE);
        assertThat(testPlacedOffer.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testPlacedOffer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPlacedOffer.getOfferDate()).isEqualTo(DEFAULT_OFFER_DATE);
        assertThat(testPlacedOffer.getPlacedOfferDate()).isEqualTo(DEFAULT_PLACED_OFFER_DATE);
        assertThat(testPlacedOffer.getAnchorTrader()).isEqualTo(DEFAULT_ANCHOR_TRADER);
        assertThat(testPlacedOffer.getTradePartner()).isEqualTo(DEFAULT_TRADE_PARTNER);
        assertThat(testPlacedOffer.getDisbursalAmount()).isEqualTo(DEFAULT_DISBURSAL_AMOUNT);
    }

    @Test
    void createPlacedOfferWithExistingId() throws Exception {
        // Create the PlacedOffer with an existing ID
        placedOffer.setId(1L);
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        int databaseSizeBeforeCreate = placedOfferRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setValue(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkReqAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setReqAmount(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMarginPtgIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setMarginPtg(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMarginValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setMarginValue(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAmountAftMarginIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setAmountAftMargin(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInterestPtgIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setInterestPtg(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTermIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setTerm(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInterestValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setInterestValue(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNetAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setNetAmount(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setStatus(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOfferDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setOfferDate(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDisbursalAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = placedOfferRepository.findAll().collectList().block().size();
        // set the field null
        placedOffer.setDisbursalAmount(null);

        // Create the PlacedOffer, which fails.
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllPlacedOffers() {
        // Initialize the database
        placedOfferRepository.save(placedOffer).block();

        // Get all the placedOfferList
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
            .value(hasItem(placedOffer.getId().intValue()))
            .jsonPath("$.[*].placedOfferUlidId")
            .value(hasItem(DEFAULT_PLACED_OFFER_ULID_ID))
            .jsonPath("$.[*].placedOfferRefNo")
            .value(hasItem(DEFAULT_PLACED_OFFER_REF_NO))
            .jsonPath("$.[*].reqOffUlidId")
            .value(hasItem(DEFAULT_REQ_OFF_ULID_ID))
            .jsonPath("$.[*].requestOfferRefNo")
            .value(hasItem(DEFAULT_REQUEST_OFFER_REF_NO))
            .jsonPath("$.[*].value")
            .value(hasItem(DEFAULT_VALUE.intValue()))
            .jsonPath("$.[*].reqAmount")
            .value(hasItem(DEFAULT_REQ_AMOUNT.intValue()))
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
            .value(hasItem(DEFAULT_INTEREST_VALUE.intValue()))
            .jsonPath("$.[*].netAmount")
            .value(hasItem(DEFAULT_NET_AMOUNT.intValue()))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].offerDate")
            .value(hasItem(DEFAULT_OFFER_DATE.toString()))
            .jsonPath("$.[*].placedOfferDate")
            .value(hasItem(DEFAULT_PLACED_OFFER_DATE.toString()))
            .jsonPath("$.[*].anchorTrader")
            .value(hasItem(DEFAULT_ANCHOR_TRADER))
            .jsonPath("$.[*].tradePartner")
            .value(hasItem(DEFAULT_TRADE_PARTNER))
            .jsonPath("$.[*].disbursalAmount")
            .value(hasItem(DEFAULT_DISBURSAL_AMOUNT));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPlacedOffersWithEagerRelationshipsIsEnabled() {
        when(placedOfferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(placedOfferServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPlacedOffersWithEagerRelationshipsIsNotEnabled() {
        when(placedOfferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(placedOfferRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getPlacedOffer() {
        // Initialize the database
        placedOfferRepository.save(placedOffer).block();

        // Get the placedOffer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, placedOffer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(placedOffer.getId().intValue()))
            .jsonPath("$.placedOfferUlidId")
            .value(is(DEFAULT_PLACED_OFFER_ULID_ID))
            .jsonPath("$.placedOfferRefNo")
            .value(is(DEFAULT_PLACED_OFFER_REF_NO))
            .jsonPath("$.reqOffUlidId")
            .value(is(DEFAULT_REQ_OFF_ULID_ID))
            .jsonPath("$.requestOfferRefNo")
            .value(is(DEFAULT_REQUEST_OFFER_REF_NO))
            .jsonPath("$.value")
            .value(is(DEFAULT_VALUE.intValue()))
            .jsonPath("$.reqAmount")
            .value(is(DEFAULT_REQ_AMOUNT.intValue()))
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
            .value(is(DEFAULT_INTEREST_VALUE.intValue()))
            .jsonPath("$.netAmount")
            .value(is(DEFAULT_NET_AMOUNT.intValue()))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.offerDate")
            .value(is(DEFAULT_OFFER_DATE.toString()))
            .jsonPath("$.placedOfferDate")
            .value(is(DEFAULT_PLACED_OFFER_DATE.toString()))
            .jsonPath("$.anchorTrader")
            .value(is(DEFAULT_ANCHOR_TRADER))
            .jsonPath("$.tradePartner")
            .value(is(DEFAULT_TRADE_PARTNER))
            .jsonPath("$.disbursalAmount")
            .value(is(DEFAULT_DISBURSAL_AMOUNT));
    }

    @Test
    void getNonExistingPlacedOffer() {
        // Get the placedOffer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingPlacedOffer() throws Exception {
        // Initialize the database
        placedOfferRepository.save(placedOffer).block();

        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();

        // Update the placedOffer
        PlacedOffer updatedPlacedOffer = placedOfferRepository.findById(placedOffer.getId()).block();
        updatedPlacedOffer
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .placedOfferRefNo(UPDATED_PLACED_OFFER_REF_NO)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .value(UPDATED_VALUE)
            .reqAmount(UPDATED_REQ_AMOUNT)
            .marginPtg(UPDATED_MARGIN_PTG)
            .marginValue(UPDATED_MARGIN_VALUE)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .interestPtg(UPDATED_INTEREST_PTG)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS)
            .offerDate(UPDATED_OFFER_DATE)
            .placedOfferDate(UPDATED_PLACED_OFFER_DATE)
            .anchorTrader(UPDATED_ANCHOR_TRADER)
            .tradePartner(UPDATED_TRADE_PARTNER)
            .disbursalAmount(UPDATED_DISBURSAL_AMOUNT);
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(updatedPlacedOffer);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, placedOfferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
        PlacedOffer testPlacedOffer = placedOfferList.get(placedOfferList.size() - 1);
        assertThat(testPlacedOffer.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testPlacedOffer.getPlacedOfferRefNo()).isEqualTo(UPDATED_PLACED_OFFER_REF_NO);
        assertThat(testPlacedOffer.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testPlacedOffer.getRequestOfferRefNo()).isEqualTo(UPDATED_REQUEST_OFFER_REF_NO);
        assertThat(testPlacedOffer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPlacedOffer.getReqAmount()).isEqualTo(UPDATED_REQ_AMOUNT);
        assertThat(testPlacedOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testPlacedOffer.getMarginValue()).isEqualTo(UPDATED_MARGIN_VALUE);
        assertThat(testPlacedOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testPlacedOffer.getInterestPtg()).isEqualTo(UPDATED_INTEREST_PTG);
        assertThat(testPlacedOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testPlacedOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testPlacedOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testPlacedOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPlacedOffer.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testPlacedOffer.getPlacedOfferDate()).isEqualTo(UPDATED_PLACED_OFFER_DATE);
        assertThat(testPlacedOffer.getAnchorTrader()).isEqualTo(UPDATED_ANCHOR_TRADER);
        assertThat(testPlacedOffer.getTradePartner()).isEqualTo(UPDATED_TRADE_PARTNER);
        assertThat(testPlacedOffer.getDisbursalAmount()).isEqualTo(UPDATED_DISBURSAL_AMOUNT);
    }

    @Test
    void putNonExistingPlacedOffer() throws Exception {
        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();
        placedOffer.setId(longCount.incrementAndGet());

        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, placedOfferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchPlacedOffer() throws Exception {
        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();
        placedOffer.setId(longCount.incrementAndGet());

        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamPlacedOffer() throws Exception {
        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();
        placedOffer.setId(longCount.incrementAndGet());

        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdatePlacedOfferWithPatch() throws Exception {
        // Initialize the database
        placedOfferRepository.save(placedOffer).block();

        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();

        // Update the placedOffer using partial update
        PlacedOffer partialUpdatedPlacedOffer = new PlacedOffer();
        partialUpdatedPlacedOffer.setId(placedOffer.getId());

        partialUpdatedPlacedOffer
            .placedOfferRefNo(UPDATED_PLACED_OFFER_REF_NO)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .value(UPDATED_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .offerDate(UPDATED_OFFER_DATE)
            .placedOfferDate(UPDATED_PLACED_OFFER_DATE)
            .anchorTrader(UPDATED_ANCHOR_TRADER)
            .tradePartner(UPDATED_TRADE_PARTNER);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPlacedOffer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPlacedOffer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
        PlacedOffer testPlacedOffer = placedOfferList.get(placedOfferList.size() - 1);
        assertThat(testPlacedOffer.getPlacedOfferUlidId()).isEqualTo(DEFAULT_PLACED_OFFER_ULID_ID);
        assertThat(testPlacedOffer.getPlacedOfferRefNo()).isEqualTo(UPDATED_PLACED_OFFER_REF_NO);
        assertThat(testPlacedOffer.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testPlacedOffer.getRequestOfferRefNo()).isEqualTo(DEFAULT_REQUEST_OFFER_REF_NO);
        assertThat(testPlacedOffer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPlacedOffer.getReqAmount()).isEqualTo(DEFAULT_REQ_AMOUNT);
        assertThat(testPlacedOffer.getMarginPtg()).isEqualTo(DEFAULT_MARGIN_PTG);
        assertThat(testPlacedOffer.getMarginValue()).isEqualTo(DEFAULT_MARGIN_VALUE);
        assertThat(testPlacedOffer.getAmountAftMargin()).isEqualTo(DEFAULT_AMOUNT_AFT_MARGIN);
        assertThat(testPlacedOffer.getInterestPtg()).isEqualTo(DEFAULT_INTEREST_PTG);
        assertThat(testPlacedOffer.getTerm()).isEqualTo(DEFAULT_TERM);
        assertThat(testPlacedOffer.getInterestValue()).isEqualTo(DEFAULT_INTEREST_VALUE);
        assertThat(testPlacedOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testPlacedOffer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testPlacedOffer.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testPlacedOffer.getPlacedOfferDate()).isEqualTo(UPDATED_PLACED_OFFER_DATE);
        assertThat(testPlacedOffer.getAnchorTrader()).isEqualTo(UPDATED_ANCHOR_TRADER);
        assertThat(testPlacedOffer.getTradePartner()).isEqualTo(UPDATED_TRADE_PARTNER);
        assertThat(testPlacedOffer.getDisbursalAmount()).isEqualTo(DEFAULT_DISBURSAL_AMOUNT);
    }

    @Test
    void fullUpdatePlacedOfferWithPatch() throws Exception {
        // Initialize the database
        placedOfferRepository.save(placedOffer).block();

        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();

        // Update the placedOffer using partial update
        PlacedOffer partialUpdatedPlacedOffer = new PlacedOffer();
        partialUpdatedPlacedOffer.setId(placedOffer.getId());

        partialUpdatedPlacedOffer
            .placedOfferUlidId(UPDATED_PLACED_OFFER_ULID_ID)
            .placedOfferRefNo(UPDATED_PLACED_OFFER_REF_NO)
            .reqOffUlidId(UPDATED_REQ_OFF_ULID_ID)
            .requestOfferRefNo(UPDATED_REQUEST_OFFER_REF_NO)
            .value(UPDATED_VALUE)
            .reqAmount(UPDATED_REQ_AMOUNT)
            .marginPtg(UPDATED_MARGIN_PTG)
            .marginValue(UPDATED_MARGIN_VALUE)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .interestPtg(UPDATED_INTEREST_PTG)
            .term(UPDATED_TERM)
            .interestValue(UPDATED_INTEREST_VALUE)
            .netAmount(UPDATED_NET_AMOUNT)
            .status(UPDATED_STATUS)
            .offerDate(UPDATED_OFFER_DATE)
            .placedOfferDate(UPDATED_PLACED_OFFER_DATE)
            .anchorTrader(UPDATED_ANCHOR_TRADER)
            .tradePartner(UPDATED_TRADE_PARTNER)
            .disbursalAmount(UPDATED_DISBURSAL_AMOUNT);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedPlacedOffer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedPlacedOffer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
        PlacedOffer testPlacedOffer = placedOfferList.get(placedOfferList.size() - 1);
        assertThat(testPlacedOffer.getPlacedOfferUlidId()).isEqualTo(UPDATED_PLACED_OFFER_ULID_ID);
        assertThat(testPlacedOffer.getPlacedOfferRefNo()).isEqualTo(UPDATED_PLACED_OFFER_REF_NO);
        assertThat(testPlacedOffer.getReqOffUlidId()).isEqualTo(UPDATED_REQ_OFF_ULID_ID);
        assertThat(testPlacedOffer.getRequestOfferRefNo()).isEqualTo(UPDATED_REQUEST_OFFER_REF_NO);
        assertThat(testPlacedOffer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testPlacedOffer.getReqAmount()).isEqualTo(UPDATED_REQ_AMOUNT);
        assertThat(testPlacedOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testPlacedOffer.getMarginValue()).isEqualTo(UPDATED_MARGIN_VALUE);
        assertThat(testPlacedOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testPlacedOffer.getInterestPtg()).isEqualTo(UPDATED_INTEREST_PTG);
        assertThat(testPlacedOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testPlacedOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testPlacedOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testPlacedOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testPlacedOffer.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testPlacedOffer.getPlacedOfferDate()).isEqualTo(UPDATED_PLACED_OFFER_DATE);
        assertThat(testPlacedOffer.getAnchorTrader()).isEqualTo(UPDATED_ANCHOR_TRADER);
        assertThat(testPlacedOffer.getTradePartner()).isEqualTo(UPDATED_TRADE_PARTNER);
        assertThat(testPlacedOffer.getDisbursalAmount()).isEqualTo(UPDATED_DISBURSAL_AMOUNT);
    }

    @Test
    void patchNonExistingPlacedOffer() throws Exception {
        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();
        placedOffer.setId(longCount.incrementAndGet());

        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, placedOfferDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchPlacedOffer() throws Exception {
        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();
        placedOffer.setId(longCount.incrementAndGet());

        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamPlacedOffer() throws Exception {
        int databaseSizeBeforeUpdate = placedOfferRepository.findAll().collectList().block().size();
        placedOffer.setId(longCount.incrementAndGet());

        // Create the PlacedOffer
        PlacedOfferDTO placedOfferDTO = placedOfferMapper.toDto(placedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(placedOfferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the PlacedOffer in the database
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deletePlacedOffer() {
        // Initialize the database
        placedOfferRepository.save(placedOffer).block();

        int databaseSizeBeforeDelete = placedOfferRepository.findAll().collectList().block().size();

        // Delete the placedOffer
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, placedOffer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<PlacedOffer> placedOfferList = placedOfferRepository.findAll().collectList().block();
        assertThat(placedOfferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
