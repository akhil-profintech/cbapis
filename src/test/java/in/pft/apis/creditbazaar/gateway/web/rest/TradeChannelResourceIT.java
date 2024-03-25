package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.TradeChannel;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.TradeChannelRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeChannelDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.TradeChannelMapper;
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
 * Integration tests for the {@link TradeChannelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TradeChannelResourceIT {

    private static final String DEFAULT_TRADE_CHANNEL_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_CHANNEL_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_PARTNER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRADE_PARTNER_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRADE_PARTNER_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_PARTNER_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_PARTNER_TENANT_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/trade-channels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TradeChannelRepository tradeChannelRepository;

    @Autowired
    private TradeChannelMapper tradeChannelMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TradeChannel tradeChannel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeChannel createEntity(EntityManager em) {
        TradeChannel tradeChannel = new TradeChannel()
            .tradeChannelUlidId(DEFAULT_TRADE_CHANNEL_ULID_ID)
            .anchorTraderId(DEFAULT_ANCHOR_TRADER_ID)
            .tradePartnerId(DEFAULT_TRADE_PARTNER_ID)
            .financePartnerId(DEFAULT_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(DEFAULT_ANCHOR_TRADER_TENANT_ID)
            .tradePartnerTenantId(DEFAULT_TRADE_PARTNER_TENANT_ID)
            .financePartnerTenantId(DEFAULT_FINANCE_PARTNER_TENANT_ID);
        return tradeChannel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeChannel createUpdatedEntity(EntityManager em) {
        TradeChannel tradeChannel = new TradeChannel()
            .tradeChannelUlidId(UPDATED_TRADE_CHANNEL_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .tradePartnerTenantId(UPDATED_TRADE_PARTNER_TENANT_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);
        return tradeChannel;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TradeChannel.class).block();
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
        tradeChannel = createEntity(em);
    }

    @Test
    void createTradeChannel() throws Exception {
        int databaseSizeBeforeCreate = tradeChannelRepository.findAll().collectList().block().size();
        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeCreate + 1);
        TradeChannel testTradeChannel = tradeChannelList.get(tradeChannelList.size() - 1);
        assertThat(testTradeChannel.getTradeChannelUlidId()).isEqualTo(DEFAULT_TRADE_CHANNEL_ULID_ID);
        assertThat(testTradeChannel.getAnchorTraderId()).isEqualTo(DEFAULT_ANCHOR_TRADER_ID);
        assertThat(testTradeChannel.getTradePartnerId()).isEqualTo(DEFAULT_TRADE_PARTNER_ID);
        assertThat(testTradeChannel.getFinancePartnerId()).isEqualTo(DEFAULT_FINANCE_PARTNER_ID);
        assertThat(testTradeChannel.getAnchorTraderTenantId()).isEqualTo(DEFAULT_ANCHOR_TRADER_TENANT_ID);
        assertThat(testTradeChannel.getTradePartnerTenantId()).isEqualTo(DEFAULT_TRADE_PARTNER_TENANT_ID);
        assertThat(testTradeChannel.getFinancePartnerTenantId()).isEqualTo(DEFAULT_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void createTradeChannelWithExistingId() throws Exception {
        // Create the TradeChannel with an existing ID
        tradeChannel.setId(1L);
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        int databaseSizeBeforeCreate = tradeChannelRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllTradeChannels() {
        // Initialize the database
        tradeChannelRepository.save(tradeChannel).block();

        // Get all the tradeChannelList
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
            .value(hasItem(tradeChannel.getId().intValue()))
            .jsonPath("$.[*].tradeChannelUlidId")
            .value(hasItem(DEFAULT_TRADE_CHANNEL_ULID_ID))
            .jsonPath("$.[*].anchorTraderId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.[*].tradePartnerId")
            .value(hasItem(DEFAULT_TRADE_PARTNER_ID))
            .jsonPath("$.[*].financePartnerId")
            .value(hasItem(DEFAULT_FINANCE_PARTNER_ID))
            .jsonPath("$.[*].anchorTraderTenantId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_TENANT_ID))
            .jsonPath("$.[*].tradePartnerTenantId")
            .value(hasItem(DEFAULT_TRADE_PARTNER_TENANT_ID))
            .jsonPath("$.[*].financePartnerTenantId")
            .value(hasItem(DEFAULT_FINANCE_PARTNER_TENANT_ID));
    }

    @Test
    void getTradeChannel() {
        // Initialize the database
        tradeChannelRepository.save(tradeChannel).block();

        // Get the tradeChannel
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tradeChannel.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tradeChannel.getId().intValue()))
            .jsonPath("$.tradeChannelUlidId")
            .value(is(DEFAULT_TRADE_CHANNEL_ULID_ID))
            .jsonPath("$.anchorTraderId")
            .value(is(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.tradePartnerId")
            .value(is(DEFAULT_TRADE_PARTNER_ID))
            .jsonPath("$.financePartnerId")
            .value(is(DEFAULT_FINANCE_PARTNER_ID))
            .jsonPath("$.anchorTraderTenantId")
            .value(is(DEFAULT_ANCHOR_TRADER_TENANT_ID))
            .jsonPath("$.tradePartnerTenantId")
            .value(is(DEFAULT_TRADE_PARTNER_TENANT_ID))
            .jsonPath("$.financePartnerTenantId")
            .value(is(DEFAULT_FINANCE_PARTNER_TENANT_ID));
    }

    @Test
    void getNonExistingTradeChannel() {
        // Get the tradeChannel
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingTradeChannel() throws Exception {
        // Initialize the database
        tradeChannelRepository.save(tradeChannel).block();

        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();

        // Update the tradeChannel
        TradeChannel updatedTradeChannel = tradeChannelRepository.findById(tradeChannel.getId()).block();
        updatedTradeChannel
            .tradeChannelUlidId(UPDATED_TRADE_CHANNEL_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .tradePartnerTenantId(UPDATED_TRADE_PARTNER_TENANT_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(updatedTradeChannel);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradeChannelDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
        TradeChannel testTradeChannel = tradeChannelList.get(tradeChannelList.size() - 1);
        assertThat(testTradeChannel.getTradeChannelUlidId()).isEqualTo(UPDATED_TRADE_CHANNEL_ULID_ID);
        assertThat(testTradeChannel.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testTradeChannel.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testTradeChannel.getFinancePartnerId()).isEqualTo(UPDATED_FINANCE_PARTNER_ID);
        assertThat(testTradeChannel.getAnchorTraderTenantId()).isEqualTo(UPDATED_ANCHOR_TRADER_TENANT_ID);
        assertThat(testTradeChannel.getTradePartnerTenantId()).isEqualTo(UPDATED_TRADE_PARTNER_TENANT_ID);
        assertThat(testTradeChannel.getFinancePartnerTenantId()).isEqualTo(UPDATED_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void putNonExistingTradeChannel() throws Exception {
        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();
        tradeChannel.setId(longCount.incrementAndGet());

        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradeChannelDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTradeChannel() throws Exception {
        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();
        tradeChannel.setId(longCount.incrementAndGet());

        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTradeChannel() throws Exception {
        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();
        tradeChannel.setId(longCount.incrementAndGet());

        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTradeChannelWithPatch() throws Exception {
        // Initialize the database
        tradeChannelRepository.save(tradeChannel).block();

        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();

        // Update the tradeChannel using partial update
        TradeChannel partialUpdatedTradeChannel = new TradeChannel();
        partialUpdatedTradeChannel.setId(tradeChannel.getId());

        partialUpdatedTradeChannel
            .tradeChannelUlidId(UPDATED_TRADE_CHANNEL_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .tradePartnerTenantId(UPDATED_TRADE_PARTNER_TENANT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTradeChannel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeChannel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
        TradeChannel testTradeChannel = tradeChannelList.get(tradeChannelList.size() - 1);
        assertThat(testTradeChannel.getTradeChannelUlidId()).isEqualTo(UPDATED_TRADE_CHANNEL_ULID_ID);
        assertThat(testTradeChannel.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testTradeChannel.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testTradeChannel.getFinancePartnerId()).isEqualTo(DEFAULT_FINANCE_PARTNER_ID);
        assertThat(testTradeChannel.getAnchorTraderTenantId()).isEqualTo(UPDATED_ANCHOR_TRADER_TENANT_ID);
        assertThat(testTradeChannel.getTradePartnerTenantId()).isEqualTo(UPDATED_TRADE_PARTNER_TENANT_ID);
        assertThat(testTradeChannel.getFinancePartnerTenantId()).isEqualTo(DEFAULT_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void fullUpdateTradeChannelWithPatch() throws Exception {
        // Initialize the database
        tradeChannelRepository.save(tradeChannel).block();

        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();

        // Update the tradeChannel using partial update
        TradeChannel partialUpdatedTradeChannel = new TradeChannel();
        partialUpdatedTradeChannel.setId(tradeChannel.getId());

        partialUpdatedTradeChannel
            .tradeChannelUlidId(UPDATED_TRADE_CHANNEL_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .tradePartnerId(UPDATED_TRADE_PARTNER_ID)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .tradePartnerTenantId(UPDATED_TRADE_PARTNER_TENANT_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTradeChannel.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeChannel))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
        TradeChannel testTradeChannel = tradeChannelList.get(tradeChannelList.size() - 1);
        assertThat(testTradeChannel.getTradeChannelUlidId()).isEqualTo(UPDATED_TRADE_CHANNEL_ULID_ID);
        assertThat(testTradeChannel.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testTradeChannel.getTradePartnerId()).isEqualTo(UPDATED_TRADE_PARTNER_ID);
        assertThat(testTradeChannel.getFinancePartnerId()).isEqualTo(UPDATED_FINANCE_PARTNER_ID);
        assertThat(testTradeChannel.getAnchorTraderTenantId()).isEqualTo(UPDATED_ANCHOR_TRADER_TENANT_ID);
        assertThat(testTradeChannel.getTradePartnerTenantId()).isEqualTo(UPDATED_TRADE_PARTNER_TENANT_ID);
        assertThat(testTradeChannel.getFinancePartnerTenantId()).isEqualTo(UPDATED_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void patchNonExistingTradeChannel() throws Exception {
        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();
        tradeChannel.setId(longCount.incrementAndGet());

        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, tradeChannelDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTradeChannel() throws Exception {
        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();
        tradeChannel.setId(longCount.incrementAndGet());

        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTradeChannel() throws Exception {
        int databaseSizeBeforeUpdate = tradeChannelRepository.findAll().collectList().block().size();
        tradeChannel.setId(longCount.incrementAndGet());

        // Create the TradeChannel
        TradeChannelDTO tradeChannelDTO = tradeChannelMapper.toDto(tradeChannel);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeChannelDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TradeChannel in the database
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTradeChannel() {
        // Initialize the database
        tradeChannelRepository.save(tradeChannel).block();

        int databaseSizeBeforeDelete = tradeChannelRepository.findAll().collectList().block().size();

        // Delete the tradeChannel
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, tradeChannel.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<TradeChannel> tradeChannelList = tradeChannelRepository.findAll().collectList().block();
        assertThat(tradeChannelList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
