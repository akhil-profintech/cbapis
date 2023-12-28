package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.AcceptedOffer;
import in.pft.apis.creditbazaar.repository.AcceptedOfferRepository;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.service.AcceptedOfferService;
import in.pft.apis.creditbazaar.service.dto.AcceptedOfferDTO;
import in.pft.apis.creditbazaar.service.mapper.AcceptedOfferMapper;
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
 * Integration tests for the {@link AcceptedOfferResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AcceptedOfferResourceIT {

    private static final String DEFAULT_OFFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_OFFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPTED_OFFER_REF_NO = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTED_OFFER_REF_NO = "BBBBBBBBBB";

    private static final Long DEFAULT_REQ_OFF_ID = 1L;
    private static final Long UPDATED_REQ_OFF_ID = 2L;

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

    private static final LocalDate DEFAULT_OFFER_ACCEPTED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFER_ACCEPTED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/accepted-offers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AcceptedOfferRepository acceptedOfferRepository;

    @Mock
    private AcceptedOfferRepository acceptedOfferRepositoryMock;

    @Autowired
    private AcceptedOfferMapper acceptedOfferMapper;

    @Mock
    private AcceptedOfferService acceptedOfferServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AcceptedOffer acceptedOffer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AcceptedOffer createEntity(EntityManager em) {
        AcceptedOffer acceptedOffer = new AcceptedOffer()
            .offerId(DEFAULT_OFFER_ID)
            .acceptedOfferRefNo(DEFAULT_ACCEPTED_OFFER_REF_NO)
            .reqOffId(DEFAULT_REQ_OFF_ID)
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
            .offerAcceptedDate(DEFAULT_OFFER_ACCEPTED_DATE);
        return acceptedOffer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AcceptedOffer createUpdatedEntity(EntityManager em) {
        AcceptedOffer acceptedOffer = new AcceptedOffer()
            .offerId(UPDATED_OFFER_ID)
            .acceptedOfferRefNo(UPDATED_ACCEPTED_OFFER_REF_NO)
            .reqOffId(UPDATED_REQ_OFF_ID)
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
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE);
        return acceptedOffer;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AcceptedOffer.class).block();
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
        acceptedOffer = createEntity(em);
    }

    @Test
    void createAcceptedOffer() throws Exception {
        int databaseSizeBeforeCreate = acceptedOfferRepository.findAll().collectList().block().size();
        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeCreate + 1);
        AcceptedOffer testAcceptedOffer = acceptedOfferList.get(acceptedOfferList.size() - 1);
        assertThat(testAcceptedOffer.getOfferId()).isEqualTo(DEFAULT_OFFER_ID);
        assertThat(testAcceptedOffer.getAcceptedOfferRefNo()).isEqualTo(DEFAULT_ACCEPTED_OFFER_REF_NO);
        assertThat(testAcceptedOffer.getReqOffId()).isEqualTo(DEFAULT_REQ_OFF_ID);
        assertThat(testAcceptedOffer.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testAcceptedOffer.getReqAmount()).isEqualTo(DEFAULT_REQ_AMOUNT);
        assertThat(testAcceptedOffer.getMarginPtg()).isEqualTo(DEFAULT_MARGIN_PTG);
        assertThat(testAcceptedOffer.getMarginValue()).isEqualTo(DEFAULT_MARGIN_VALUE);
        assertThat(testAcceptedOffer.getAmountAftMargin()).isEqualTo(DEFAULT_AMOUNT_AFT_MARGIN);
        assertThat(testAcceptedOffer.getInterestPtg()).isEqualTo(DEFAULT_INTEREST_PTG);
        assertThat(testAcceptedOffer.getTerm()).isEqualTo(DEFAULT_TERM);
        assertThat(testAcceptedOffer.getInterestValue()).isEqualTo(DEFAULT_INTEREST_VALUE);
        assertThat(testAcceptedOffer.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testAcceptedOffer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAcceptedOffer.getOfferDate()).isEqualTo(DEFAULT_OFFER_DATE);
        assertThat(testAcceptedOffer.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
    }

    @Test
    void createAcceptedOfferWithExistingId() throws Exception {
        // Create the AcceptedOffer with an existing ID
        acceptedOffer.setId(1L);
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        int databaseSizeBeforeCreate = acceptedOfferRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkReqOffIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setReqOffId(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setValue(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkReqAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setReqAmount(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMarginPtgIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setMarginPtg(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkMarginValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setMarginValue(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkAmountAftMarginIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setAmountAftMargin(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInterestPtgIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setInterestPtg(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTermIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setTerm(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkInterestValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setInterestValue(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkNetAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setNetAmount(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setStatus(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOfferDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setOfferDate(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkOfferAcceptedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = acceptedOfferRepository.findAll().collectList().block().size();
        // set the field null
        acceptedOffer.setOfferAcceptedDate(null);

        // Create the AcceptedOffer, which fails.
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllAcceptedOffers() {
        // Initialize the database
        acceptedOfferRepository.save(acceptedOffer).block();

        // Get all the acceptedOfferList
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
            .value(hasItem(acceptedOffer.getId().intValue()))
            .jsonPath("$.[*].offerId")
            .value(hasItem(DEFAULT_OFFER_ID))
            .jsonPath("$.[*].acceptedOfferRefNo")
            .value(hasItem(DEFAULT_ACCEPTED_OFFER_REF_NO))
            .jsonPath("$.[*].reqOffId")
            .value(hasItem(DEFAULT_REQ_OFF_ID.intValue()))
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
            .jsonPath("$.[*].offerAcceptedDate")
            .value(hasItem(DEFAULT_OFFER_ACCEPTED_DATE.toString()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAcceptedOffersWithEagerRelationshipsIsEnabled() {
        when(acceptedOfferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(acceptedOfferServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAcceptedOffersWithEagerRelationshipsIsNotEnabled() {
        when(acceptedOfferServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(acceptedOfferRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getAcceptedOffer() {
        // Initialize the database
        acceptedOfferRepository.save(acceptedOffer).block();

        // Get the acceptedOffer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, acceptedOffer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(acceptedOffer.getId().intValue()))
            .jsonPath("$.offerId")
            .value(is(DEFAULT_OFFER_ID))
            .jsonPath("$.acceptedOfferRefNo")
            .value(is(DEFAULT_ACCEPTED_OFFER_REF_NO))
            .jsonPath("$.reqOffId")
            .value(is(DEFAULT_REQ_OFF_ID.intValue()))
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
            .jsonPath("$.offerAcceptedDate")
            .value(is(DEFAULT_OFFER_ACCEPTED_DATE.toString()));
    }

    @Test
    void getNonExistingAcceptedOffer() {
        // Get the acceptedOffer
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAcceptedOffer() throws Exception {
        // Initialize the database
        acceptedOfferRepository.save(acceptedOffer).block();

        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();

        // Update the acceptedOffer
        AcceptedOffer updatedAcceptedOffer = acceptedOfferRepository.findById(acceptedOffer.getId()).block();
        updatedAcceptedOffer
            .offerId(UPDATED_OFFER_ID)
            .acceptedOfferRefNo(UPDATED_ACCEPTED_OFFER_REF_NO)
            .reqOffId(UPDATED_REQ_OFF_ID)
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
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE);
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(updatedAcceptedOffer);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, acceptedOfferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
        AcceptedOffer testAcceptedOffer = acceptedOfferList.get(acceptedOfferList.size() - 1);
        assertThat(testAcceptedOffer.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testAcceptedOffer.getAcceptedOfferRefNo()).isEqualTo(UPDATED_ACCEPTED_OFFER_REF_NO);
        assertThat(testAcceptedOffer.getReqOffId()).isEqualTo(UPDATED_REQ_OFF_ID);
        assertThat(testAcceptedOffer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testAcceptedOffer.getReqAmount()).isEqualTo(UPDATED_REQ_AMOUNT);
        assertThat(testAcceptedOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testAcceptedOffer.getMarginValue()).isEqualTo(UPDATED_MARGIN_VALUE);
        assertThat(testAcceptedOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testAcceptedOffer.getInterestPtg()).isEqualTo(UPDATED_INTEREST_PTG);
        assertThat(testAcceptedOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testAcceptedOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testAcceptedOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testAcceptedOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAcceptedOffer.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testAcceptedOffer.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
    }

    @Test
    void putNonExistingAcceptedOffer() throws Exception {
        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();
        acceptedOffer.setId(longCount.incrementAndGet());

        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, acceptedOfferDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAcceptedOffer() throws Exception {
        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();
        acceptedOffer.setId(longCount.incrementAndGet());

        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAcceptedOffer() throws Exception {
        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();
        acceptedOffer.setId(longCount.incrementAndGet());

        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAcceptedOfferWithPatch() throws Exception {
        // Initialize the database
        acceptedOfferRepository.save(acceptedOffer).block();

        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();

        // Update the acceptedOffer using partial update
        AcceptedOffer partialUpdatedAcceptedOffer = new AcceptedOffer();
        partialUpdatedAcceptedOffer.setId(acceptedOffer.getId());

        partialUpdatedAcceptedOffer
            .offerId(UPDATED_OFFER_ID)
            .acceptedOfferRefNo(UPDATED_ACCEPTED_OFFER_REF_NO)
            .reqOffId(UPDATED_REQ_OFF_ID)
            .value(UPDATED_VALUE)
            .reqAmount(UPDATED_REQ_AMOUNT)
            .marginPtg(UPDATED_MARGIN_PTG)
            .amountAftMargin(UPDATED_AMOUNT_AFT_MARGIN)
            .term(UPDATED_TERM);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAcceptedOffer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAcceptedOffer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
        AcceptedOffer testAcceptedOffer = acceptedOfferList.get(acceptedOfferList.size() - 1);
        assertThat(testAcceptedOffer.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testAcceptedOffer.getAcceptedOfferRefNo()).isEqualTo(UPDATED_ACCEPTED_OFFER_REF_NO);
        assertThat(testAcceptedOffer.getReqOffId()).isEqualTo(UPDATED_REQ_OFF_ID);
        assertThat(testAcceptedOffer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testAcceptedOffer.getReqAmount()).isEqualTo(UPDATED_REQ_AMOUNT);
        assertThat(testAcceptedOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testAcceptedOffer.getMarginValue()).isEqualTo(DEFAULT_MARGIN_VALUE);
        assertThat(testAcceptedOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testAcceptedOffer.getInterestPtg()).isEqualTo(DEFAULT_INTEREST_PTG);
        assertThat(testAcceptedOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testAcceptedOffer.getInterestValue()).isEqualTo(DEFAULT_INTEREST_VALUE);
        assertThat(testAcceptedOffer.getNetAmount()).isEqualTo(DEFAULT_NET_AMOUNT);
        assertThat(testAcceptedOffer.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAcceptedOffer.getOfferDate()).isEqualTo(DEFAULT_OFFER_DATE);
        assertThat(testAcceptedOffer.getOfferAcceptedDate()).isEqualTo(DEFAULT_OFFER_ACCEPTED_DATE);
    }

    @Test
    void fullUpdateAcceptedOfferWithPatch() throws Exception {
        // Initialize the database
        acceptedOfferRepository.save(acceptedOffer).block();

        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();

        // Update the acceptedOffer using partial update
        AcceptedOffer partialUpdatedAcceptedOffer = new AcceptedOffer();
        partialUpdatedAcceptedOffer.setId(acceptedOffer.getId());

        partialUpdatedAcceptedOffer
            .offerId(UPDATED_OFFER_ID)
            .acceptedOfferRefNo(UPDATED_ACCEPTED_OFFER_REF_NO)
            .reqOffId(UPDATED_REQ_OFF_ID)
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
            .offerAcceptedDate(UPDATED_OFFER_ACCEPTED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAcceptedOffer.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAcceptedOffer))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
        AcceptedOffer testAcceptedOffer = acceptedOfferList.get(acceptedOfferList.size() - 1);
        assertThat(testAcceptedOffer.getOfferId()).isEqualTo(UPDATED_OFFER_ID);
        assertThat(testAcceptedOffer.getAcceptedOfferRefNo()).isEqualTo(UPDATED_ACCEPTED_OFFER_REF_NO);
        assertThat(testAcceptedOffer.getReqOffId()).isEqualTo(UPDATED_REQ_OFF_ID);
        assertThat(testAcceptedOffer.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testAcceptedOffer.getReqAmount()).isEqualTo(UPDATED_REQ_AMOUNT);
        assertThat(testAcceptedOffer.getMarginPtg()).isEqualTo(UPDATED_MARGIN_PTG);
        assertThat(testAcceptedOffer.getMarginValue()).isEqualTo(UPDATED_MARGIN_VALUE);
        assertThat(testAcceptedOffer.getAmountAftMargin()).isEqualTo(UPDATED_AMOUNT_AFT_MARGIN);
        assertThat(testAcceptedOffer.getInterestPtg()).isEqualTo(UPDATED_INTEREST_PTG);
        assertThat(testAcceptedOffer.getTerm()).isEqualTo(UPDATED_TERM);
        assertThat(testAcceptedOffer.getInterestValue()).isEqualTo(UPDATED_INTEREST_VALUE);
        assertThat(testAcceptedOffer.getNetAmount()).isEqualTo(UPDATED_NET_AMOUNT);
        assertThat(testAcceptedOffer.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAcceptedOffer.getOfferDate()).isEqualTo(UPDATED_OFFER_DATE);
        assertThat(testAcceptedOffer.getOfferAcceptedDate()).isEqualTo(UPDATED_OFFER_ACCEPTED_DATE);
    }

    @Test
    void patchNonExistingAcceptedOffer() throws Exception {
        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();
        acceptedOffer.setId(longCount.incrementAndGet());

        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, acceptedOfferDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAcceptedOffer() throws Exception {
        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();
        acceptedOffer.setId(longCount.incrementAndGet());

        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAcceptedOffer() throws Exception {
        int databaseSizeBeforeUpdate = acceptedOfferRepository.findAll().collectList().block().size();
        acceptedOffer.setId(longCount.incrementAndGet());

        // Create the AcceptedOffer
        AcceptedOfferDTO acceptedOfferDTO = acceptedOfferMapper.toDto(acceptedOffer);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(acceptedOfferDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AcceptedOffer in the database
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAcceptedOffer() {
        // Initialize the database
        acceptedOfferRepository.save(acceptedOffer).block();

        int databaseSizeBeforeDelete = acceptedOfferRepository.findAll().collectList().block().size();

        // Delete the acceptedOffer
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, acceptedOffer.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AcceptedOffer> acceptedOfferList = acceptedOfferRepository.findAll().collectList().block();
        assertThat(acceptedOfferList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
