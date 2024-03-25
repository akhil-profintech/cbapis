package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequestMapping;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.FinanceRequestMappingRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestMappingDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.FinanceRequestMappingMapper;
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
 * Integration tests for the {@link FinanceRequestMappingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class FinanceRequestMappingResourceIT {

    private static final Long DEFAULT_FINANCE_REQUEST_ID = 1L;
    private static final Long UPDATED_FINANCE_REQUEST_ID = 2L;

    private static final String DEFAULT_FINANCE_REQUEST_MAPPING_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_PARTNER_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_PARTNER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ANCHOR_TRADER_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_ANCHOR_TRADER_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_PARTNER_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_PARTNER_TENANT_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/finance-request-mappings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private FinanceRequestMappingRepository financeRequestMappingRepository;

    @Autowired
    private FinanceRequestMappingMapper financeRequestMappingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private FinanceRequestMapping financeRequestMapping;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinanceRequestMapping createEntity(EntityManager em) {
        FinanceRequestMapping financeRequestMapping = new FinanceRequestMapping()
            .financeRequestId(DEFAULT_FINANCE_REQUEST_ID)
            .financeRequestMappingUlidId(DEFAULT_FINANCE_REQUEST_MAPPING_ULID_ID)
            .anchorTraderId(DEFAULT_ANCHOR_TRADER_ID)
            .financePartnerId(DEFAULT_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(DEFAULT_ANCHOR_TRADER_TENANT_ID)
            .financePartnerTenantId(DEFAULT_FINANCE_PARTNER_TENANT_ID);
        return financeRequestMapping;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FinanceRequestMapping createUpdatedEntity(EntityManager em) {
        FinanceRequestMapping financeRequestMapping = new FinanceRequestMapping()
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .financeRequestMappingUlidId(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);
        return financeRequestMapping;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(FinanceRequestMapping.class).block();
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
        financeRequestMapping = createEntity(em);
    }

    @Test
    void createFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeCreate = financeRequestMappingRepository.findAll().collectList().block().size();
        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeCreate + 1);
        FinanceRequestMapping testFinanceRequestMapping = financeRequestMappingList.get(financeRequestMappingList.size() - 1);
        assertThat(testFinanceRequestMapping.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testFinanceRequestMapping.getFinanceRequestMappingUlidId()).isEqualTo(DEFAULT_FINANCE_REQUEST_MAPPING_ULID_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderId()).isEqualTo(DEFAULT_ANCHOR_TRADER_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerId()).isEqualTo(DEFAULT_FINANCE_PARTNER_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderTenantId()).isEqualTo(DEFAULT_ANCHOR_TRADER_TENANT_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerTenantId()).isEqualTo(DEFAULT_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void createFinanceRequestMappingWithExistingId() throws Exception {
        // Create the FinanceRequestMapping with an existing ID
        financeRequestMapping.setId(1L);
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        int databaseSizeBeforeCreate = financeRequestMappingRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllFinanceRequestMappings() {
        // Initialize the database
        financeRequestMappingRepository.save(financeRequestMapping).block();

        // Get all the financeRequestMappingList
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
            .value(hasItem(financeRequestMapping.getId().intValue()))
            .jsonPath("$.[*].financeRequestId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_ID.intValue()))
            .jsonPath("$.[*].financeRequestMappingUlidId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_MAPPING_ULID_ID))
            .jsonPath("$.[*].anchorTraderId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.[*].financePartnerId")
            .value(hasItem(DEFAULT_FINANCE_PARTNER_ID))
            .jsonPath("$.[*].anchorTraderTenantId")
            .value(hasItem(DEFAULT_ANCHOR_TRADER_TENANT_ID))
            .jsonPath("$.[*].financePartnerTenantId")
            .value(hasItem(DEFAULT_FINANCE_PARTNER_TENANT_ID));
    }

    @Test
    void getFinanceRequestMapping() {
        // Initialize the database
        financeRequestMappingRepository.save(financeRequestMapping).block();

        // Get the financeRequestMapping
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, financeRequestMapping.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(financeRequestMapping.getId().intValue()))
            .jsonPath("$.financeRequestId")
            .value(is(DEFAULT_FINANCE_REQUEST_ID.intValue()))
            .jsonPath("$.financeRequestMappingUlidId")
            .value(is(DEFAULT_FINANCE_REQUEST_MAPPING_ULID_ID))
            .jsonPath("$.anchorTraderId")
            .value(is(DEFAULT_ANCHOR_TRADER_ID))
            .jsonPath("$.financePartnerId")
            .value(is(DEFAULT_FINANCE_PARTNER_ID))
            .jsonPath("$.anchorTraderTenantId")
            .value(is(DEFAULT_ANCHOR_TRADER_TENANT_ID))
            .jsonPath("$.financePartnerTenantId")
            .value(is(DEFAULT_FINANCE_PARTNER_TENANT_ID));
    }

    @Test
    void getNonExistingFinanceRequestMapping() {
        // Get the financeRequestMapping
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingFinanceRequestMapping() throws Exception {
        // Initialize the database
        financeRequestMappingRepository.save(financeRequestMapping).block();

        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();

        // Update the financeRequestMapping
        FinanceRequestMapping updatedFinanceRequestMapping = financeRequestMappingRepository
            .findById(financeRequestMapping.getId())
            .block();
        updatedFinanceRequestMapping
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .financeRequestMappingUlidId(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(updatedFinanceRequestMapping);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, financeRequestMappingDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
        FinanceRequestMapping testFinanceRequestMapping = financeRequestMappingList.get(financeRequestMappingList.size() - 1);
        assertThat(testFinanceRequestMapping.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testFinanceRequestMapping.getFinanceRequestMappingUlidId()).isEqualTo(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerId()).isEqualTo(UPDATED_FINANCE_PARTNER_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderTenantId()).isEqualTo(UPDATED_ANCHOR_TRADER_TENANT_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerTenantId()).isEqualTo(UPDATED_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void putNonExistingFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();
        financeRequestMapping.setId(longCount.incrementAndGet());

        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, financeRequestMappingDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();
        financeRequestMapping.setId(longCount.incrementAndGet());

        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();
        financeRequestMapping.setId(longCount.incrementAndGet());

        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateFinanceRequestMappingWithPatch() throws Exception {
        // Initialize the database
        financeRequestMappingRepository.save(financeRequestMapping).block();

        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();

        // Update the financeRequestMapping using partial update
        FinanceRequestMapping partialUpdatedFinanceRequestMapping = new FinanceRequestMapping();
        partialUpdatedFinanceRequestMapping.setId(financeRequestMapping.getId());

        partialUpdatedFinanceRequestMapping
            .financeRequestMappingUlidId(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFinanceRequestMapping.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFinanceRequestMapping))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
        FinanceRequestMapping testFinanceRequestMapping = financeRequestMappingList.get(financeRequestMappingList.size() - 1);
        assertThat(testFinanceRequestMapping.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testFinanceRequestMapping.getFinanceRequestMappingUlidId()).isEqualTo(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerId()).isEqualTo(DEFAULT_FINANCE_PARTNER_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderTenantId()).isEqualTo(DEFAULT_ANCHOR_TRADER_TENANT_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerTenantId()).isEqualTo(UPDATED_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void fullUpdateFinanceRequestMappingWithPatch() throws Exception {
        // Initialize the database
        financeRequestMappingRepository.save(financeRequestMapping).block();

        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();

        // Update the financeRequestMapping using partial update
        FinanceRequestMapping partialUpdatedFinanceRequestMapping = new FinanceRequestMapping();
        partialUpdatedFinanceRequestMapping.setId(financeRequestMapping.getId());

        partialUpdatedFinanceRequestMapping
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .financeRequestMappingUlidId(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID)
            .anchorTraderId(UPDATED_ANCHOR_TRADER_ID)
            .financePartnerId(UPDATED_FINANCE_PARTNER_ID)
            .anchorTraderTenantId(UPDATED_ANCHOR_TRADER_TENANT_ID)
            .financePartnerTenantId(UPDATED_FINANCE_PARTNER_TENANT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedFinanceRequestMapping.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedFinanceRequestMapping))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
        FinanceRequestMapping testFinanceRequestMapping = financeRequestMappingList.get(financeRequestMappingList.size() - 1);
        assertThat(testFinanceRequestMapping.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testFinanceRequestMapping.getFinanceRequestMappingUlidId()).isEqualTo(UPDATED_FINANCE_REQUEST_MAPPING_ULID_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderId()).isEqualTo(UPDATED_ANCHOR_TRADER_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerId()).isEqualTo(UPDATED_FINANCE_PARTNER_ID);
        assertThat(testFinanceRequestMapping.getAnchorTraderTenantId()).isEqualTo(UPDATED_ANCHOR_TRADER_TENANT_ID);
        assertThat(testFinanceRequestMapping.getFinancePartnerTenantId()).isEqualTo(UPDATED_FINANCE_PARTNER_TENANT_ID);
    }

    @Test
    void patchNonExistingFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();
        financeRequestMapping.setId(longCount.incrementAndGet());

        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, financeRequestMappingDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();
        financeRequestMapping.setId(longCount.incrementAndGet());

        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamFinanceRequestMapping() throws Exception {
        int databaseSizeBeforeUpdate = financeRequestMappingRepository.findAll().collectList().block().size();
        financeRequestMapping.setId(longCount.incrementAndGet());

        // Create the FinanceRequestMapping
        FinanceRequestMappingDTO financeRequestMappingDTO = financeRequestMappingMapper.toDto(financeRequestMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(financeRequestMappingDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the FinanceRequestMapping in the database
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteFinanceRequestMapping() {
        // Initialize the database
        financeRequestMappingRepository.save(financeRequestMapping).block();

        int databaseSizeBeforeDelete = financeRequestMappingRepository.findAll().collectList().block().size();

        // Delete the financeRequestMapping
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, financeRequestMapping.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<FinanceRequestMapping> financeRequestMappingList = financeRequestMappingRepository.findAll().collectList().block();
        assertThat(financeRequestMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
