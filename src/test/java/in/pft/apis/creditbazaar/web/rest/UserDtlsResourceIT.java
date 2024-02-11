package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.UserDtls;
import in.pft.apis.creditbazaar.domain.enumeration.Persona;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.UserDtlsRepository;
import in.pft.apis.creditbazaar.service.UserDtlsService;
import in.pft.apis.creditbazaar.service.dto.UserDtlsDTO;
import in.pft.apis.creditbazaar.service.mapper.UserDtlsMapper;
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
 * Integration tests for the {@link UserDtlsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class UserDtlsResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ANCHOR_TRADER_ENABLED = false;
    private static final Boolean UPDATED_IS_ANCHOR_TRADER_ENABLED = true;

    private static final String DEFAULT_ANCHOR_TRADER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_TRADE_PARTNER_ENABLED = false;
    private static final Boolean UPDATED_IS_TRADE_PARTNER_ENABLED = true;

    private static final String DEFAULT_TRADE_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_ID = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_FINANCE_PARTNER_ENABLED = false;
    private static final Boolean UPDATED_IS_FINANCE_PARTNER_ENABLED = true;

    private static final String DEFAULT_FINANCE_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_PARTNER_ID = "BBBBBBBBBB";

    private static final Persona DEFAULT_DEFAULT_PERSONA = Persona.AnchorTrader;
    private static final Persona UPDATED_DEFAULT_PERSONA = Persona.TradePartner;

    private static final String ENTITY_API_URL = "/api/user-dtls";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UserDtlsRepository userDtlsRepository;

    @Mock
    private UserDtlsRepository userDtlsRepositoryMock;

    @Autowired
    private UserDtlsMapper userDtlsMapper;

    @Mock
    private UserDtlsService userDtlsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private UserDtls userDtls;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserDtls createEntity(EntityManager em) {
        UserDtls userDtls = new UserDtls()
            .userId(DEFAULT_USER_ID)
            .userName(DEFAULT_USER_NAME)
            .tenantId(DEFAULT_TENANT_ID)
            .isAnchorTraderEnabled(DEFAULT_IS_ANCHOR_TRADER_ENABLED)
            .anchorTraderId(DEFAULT_ANCHOR_TRADER_ID)
            .isTradePartnerEnabled(DEFAULT_IS_TRADE_PARTNER_ENABLED)
            .tradePartnerId(DEFAULT_TRADE_PARTNER_ID)
            .isFinancePartnerEnabled(DEFAULT_IS_FINANCE_PARTNER_ENABLED)
            .financePartnerId(DEFAULT_FINANCE_PARTNER_ID)
            .defaultPersona(DEFAULT_DEFAULT_PERSONA);
        return userDtls;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserDtls createUpdatedEntity(EntityManager em) {
        UserDtls userDtls = new UserDtls()
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .tenantId(UPDATED_TENANT_ID)
            .isAnchorTraderEnabled(UPDATED_IS_ANCHOR_TRADER_ENABLED)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .isTradePartnerEnabled(UPDATED_IS_TRADE_PARTNER_ENABLED)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .isFinancePartnerEnabled(UPDATED_IS_FINANCE_PARTNER_ENABLED)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .defaultPersona(UPDATED_DEFAULT_PERSONA);
        return userDtls;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(UserDtls.class).block();
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
        userDtls = createEntity(em);
    }

    @Test
    void createUserDtls() throws Exception {
        int databaseSizeBeforeCreate = userDtlsRepository.findAll().collectList().block().size();
        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeCreate + 1);
        UserDtls testUserDtls = userDtlsList.get(userDtlsList.size() - 1);
        assertThat(testUserDtls.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserDtls.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserDtls.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testUserDtls.getIsAnchorTraderEnabled()).isEqualTo(DEFAULT_IS_ANCHOR_TRADER_ENABLED);
        assertThat(testUserDtls.getAnchorTraderId()).isEqualTo(DEFAULT_ANCHOR_TRADER_ID);
        assertThat(testUserDtls.getIsTradePartnerEnabled()).isEqualTo(DEFAULT_IS_TRADE_PARTNER_ENABLED);
        assertThat(testUserDtls.getTradePartnerId()).isEqualTo(DEFAULT_TRADE_PARTNER_ID);
        assertThat(testUserDtls.getIsFinancePartnerEnabled()).isEqualTo(DEFAULT_IS_FINANCE_PARTNER_ENABLED);
        assertThat(testUserDtls.getFinancePartnerId()).isEqualTo(DEFAULT_FINANCE_PARTNER_ID);
        assertThat(testUserDtls.getDefaultPersona()).isEqualTo(DEFAULT_DEFAULT_PERSONA);
    }

    @Test
    void createUserDtlsWithExistingId() throws Exception {
        // Create the UserDtls with an existing ID
        userDtls.setId(1L);
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        int databaseSizeBeforeCreate = userDtlsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllUserDtls() {
        // Initialize the database
        userDtlsRepository.save(userDtls).block();

        // Get all the userDtlsList
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
            .value(hasItem(userDtls.getId().intValue()))
            .jsonPath("$.[*].userId")
            .value(hasItem(DEFAULT_USER_ID))
            .jsonPath("$.[*].userName")
            .value(hasItem(DEFAULT_USER_NAME))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID))
            .jsonPath("$.[*].isAnchorTraderEnabled")
            .value(hasItem(DEFAULT_IS_ANCHOR_TRADER_ENABLED.booleanValue()))
            .jsonPath("$.[*].anchorTraderId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.[*].isTradePartnerEnabled")
            .value(hasItem(DEFAULT_IS_TRADE_PARTNER_ENABLED.booleanValue()))
            .jsonPath("$.[*].tradePartnerId")
            .value(hasItem(DEFAULT_TRADE_PARTNER_ID))
            .jsonPath("$.[*].isFinancePartnerEnabled")
            .value(hasItem(DEFAULT_IS_FINANCE_PARTNER_ENABLED.booleanValue()))
            .jsonPath("$.[*].financePartnerId")
            .value(hasItem(DEFAULT_FINANCE_PARTNER_ID))
            .jsonPath("$.[*].defaultPersona")
            .value(hasItem(DEFAULT_DEFAULT_PERSONA.toString()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUserDtlsWithEagerRelationshipsIsEnabled() {
        when(userDtlsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(userDtlsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUserDtlsWithEagerRelationshipsIsNotEnabled() {
        when(userDtlsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(userDtlsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getUserDtls() {
        // Initialize the database
        userDtlsRepository.save(userDtls).block();

        // Get the userDtls
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, userDtls.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(userDtls.getId().intValue()))
            .jsonPath("$.userId")
            .value(is(DEFAULT_USER_ID))
            .jsonPath("$.userName")
            .value(is(DEFAULT_USER_NAME))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID))
            .jsonPath("$.isAnchorTraderEnabled")
            .value(is(DEFAULT_IS_ANCHOR_TRADER_ENABLED.booleanValue()))
            .jsonPath("$.anchorTraderId")
            .value(is(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.isTradePartnerEnabled")
            .value(is(DEFAULT_IS_TRADE_PARTNER_ENABLED.booleanValue()))
            .jsonPath("$.tradePartnerId")
            .value(is(DEFAULT_TRADE_PARTNER_ID))
            .jsonPath("$.isFinancePartnerEnabled")
            .value(is(DEFAULT_IS_FINANCE_PARTNER_ENABLED.booleanValue()))
            .jsonPath("$.financePartnerId")
            .value(is(DEFAULT_FINANCE_PARTNER_ID))
            .jsonPath("$.defaultPersona")
            .value(is(DEFAULT_DEFAULT_PERSONA.toString()));
    }

    @Test
    void getNonExistingUserDtls() {
        // Get the userDtls
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingUserDtls() throws Exception {
        // Initialize the database
        userDtlsRepository.save(userDtls).block();

        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();

        // Update the userDtls
        UserDtls updatedUserDtls = userDtlsRepository.findById(userDtls.getId()).block();
        updatedUserDtls
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .tenantId(UPDATED_TENANT_ID)
            .isAnchorTraderEnabled(UPDATED_IS_ANCHOR_TRADER_ENABLED)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .isTradePartnerEnabled(UPDATED_IS_TRADE_PARTNER_ENABLED)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .isFinancePartnerEnabled(UPDATED_IS_FINANCE_PARTNER_ENABLED)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .defaultPersona(UPDATED_DEFAULT_PERSONA);
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(updatedUserDtls);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, userDtlsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
        UserDtls testUserDtls = userDtlsList.get(userDtlsList.size() - 1);
        assertThat(testUserDtls.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserDtls.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserDtls.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testUserDtls.getIsAnchorTraderEnabled()).isEqualTo(UPDATED_IS_ANCHOR_TRADER_ENABLED);
        assertThat(testUserDtls.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testUserDtls.getIsTradePartnerEnabled()).isEqualTo(UPDATED_IS_TRADE_PARTNER_ENABLED);
        assertThat(testUserDtls.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testUserDtls.getIsFinancePartnerEnabled()).isEqualTo(UPDATED_IS_FINANCE_PARTNER_ENABLED);
        assertThat(testUserDtls.getFinancePartnerId()).isEqualTo(UPDATED_FINANCE_PARTNER_ID);
        assertThat(testUserDtls.getDefaultPersona()).isEqualTo(UPDATED_DEFAULT_PERSONA);
    }

    @Test
    void putNonExistingUserDtls() throws Exception {
        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();
        userDtls.setId(longCount.incrementAndGet());

        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, userDtlsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchUserDtls() throws Exception {
        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();
        userDtls.setId(longCount.incrementAndGet());

        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamUserDtls() throws Exception {
        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();
        userDtls.setId(longCount.incrementAndGet());

        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateUserDtlsWithPatch() throws Exception {
        // Initialize the database
        userDtlsRepository.save(userDtls).block();

        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();

        // Update the userDtls using partial update
        UserDtls partialUpdatedUserDtls = new UserDtls();
        partialUpdatedUserDtls.setId(userDtls.getId());

        partialUpdatedUserDtls
            .tenantId(UPDATED_TENANT_ID)
            .isTradePartnerEnabled(UPDATED_IS_TRADE_PARTNER_ENABLED)
            .defaultPersona(UPDATED_DEFAULT_PERSONA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedUserDtls.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedUserDtls))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
        UserDtls testUserDtls = userDtlsList.get(userDtlsList.size() - 1);
        assertThat(testUserDtls.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testUserDtls.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUserDtls.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testUserDtls.getIsAnchorTraderEnabled()).isEqualTo(DEFAULT_IS_ANCHOR_TRADER_ENABLED);
        assertThat(testUserDtls.getAnchorTraderId()).isEqualTo(DEFAULT_ANCHOR_TRADER_ID);
        assertThat(testUserDtls.getIsTradePartnerEnabled()).isEqualTo(UPDATED_IS_TRADE_PARTNER_ENABLED);
        assertThat(testUserDtls.getTradePartnerId()).isEqualTo(DEFAULT_TRADE_PARTNER_ID);
        assertThat(testUserDtls.getIsFinancePartnerEnabled()).isEqualTo(DEFAULT_IS_FINANCE_PARTNER_ENABLED);
        assertThat(testUserDtls.getFinancePartnerId()).isEqualTo(DEFAULT_FINANCE_PARTNER_ID);
        assertThat(testUserDtls.getDefaultPersona()).isEqualTo(UPDATED_DEFAULT_PERSONA);
    }

    @Test
    void fullUpdateUserDtlsWithPatch() throws Exception {
        // Initialize the database
        userDtlsRepository.save(userDtls).block();

        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();

        // Update the userDtls using partial update
        UserDtls partialUpdatedUserDtls = new UserDtls();
        partialUpdatedUserDtls.setId(userDtls.getId());

        partialUpdatedUserDtls
            .userId(UPDATED_USER_ID)
            .userName(UPDATED_USER_NAME)
            .tenantId(UPDATED_TENANT_ID)
            .isAnchorTraderEnabled(UPDATED_IS_ANCHOR_TRADER_ENABLED)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .isTradePartnerEnabled(UPDATED_IS_TRADE_PARTNER_ENABLED)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .isFinancePartnerEnabled(UPDATED_IS_FINANCE_PARTNER_ENABLED)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .defaultPersona(UPDATED_DEFAULT_PERSONA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedUserDtls.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedUserDtls))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
        UserDtls testUserDtls = userDtlsList.get(userDtlsList.size() - 1);
        assertThat(testUserDtls.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testUserDtls.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUserDtls.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testUserDtls.getIsAnchorTraderEnabled()).isEqualTo(UPDATED_IS_ANCHOR_TRADER_ENABLED);
        assertThat(testUserDtls.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testUserDtls.getIsTradePartnerEnabled()).isEqualTo(UPDATED_IS_TRADE_PARTNER_ENABLED);
        assertThat(testUserDtls.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testUserDtls.getIsFinancePartnerEnabled()).isEqualTo(UPDATED_IS_FINANCE_PARTNER_ENABLED);
        assertThat(testUserDtls.getFinancePartnerId()).isEqualTo(UPDATED_FINANCE_PARTNER_ID);
        assertThat(testUserDtls.getDefaultPersona()).isEqualTo(UPDATED_DEFAULT_PERSONA);
    }

    @Test
    void patchNonExistingUserDtls() throws Exception {
        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();
        userDtls.setId(longCount.incrementAndGet());

        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, userDtlsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchUserDtls() throws Exception {
        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();
        userDtls.setId(longCount.incrementAndGet());

        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamUserDtls() throws Exception {
        int databaseSizeBeforeUpdate = userDtlsRepository.findAll().collectList().block().size();
        userDtls.setId(longCount.incrementAndGet());

        // Create the UserDtls
        UserDtlsDTO userDtlsDTO = userDtlsMapper.toDto(userDtls);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(userDtlsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the UserDtls in the database
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteUserDtls() {
        // Initialize the database
        userDtlsRepository.save(userDtls).block();

        int databaseSizeBeforeDelete = userDtlsRepository.findAll().collectList().block().size();

        // Delete the userDtls
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, userDtls.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<UserDtls> userDtlsList = userDtlsRepository.findAll().collectList().block();
        assertThat(userDtlsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
