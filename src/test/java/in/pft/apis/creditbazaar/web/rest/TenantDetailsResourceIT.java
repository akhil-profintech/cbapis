package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.TenantDetails;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.TenantDetailsRepository;
import in.pft.apis.creditbazaar.service.dto.TenantDetailsDTO;
import in.pft.apis.creditbazaar.service.mapper.TenantDetailsMapper;
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
 * Integration tests for the {@link TenantDetailsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TenantDetailsResourceIT {

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_SCHEMA = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_SCHEMA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tenant-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TenantDetailsRepository tenantDetailsRepository;

    @Autowired
    private TenantDetailsMapper tenantDetailsMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TenantDetails tenantDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenantDetails createEntity(EntityManager em) {
        TenantDetails tenantDetails = new TenantDetails().tenantId(DEFAULT_TENANT_ID).tenantSchema(DEFAULT_TENANT_SCHEMA);
        return tenantDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TenantDetails createUpdatedEntity(EntityManager em) {
        TenantDetails tenantDetails = new TenantDetails().tenantId(UPDATED_TENANT_ID).tenantSchema(UPDATED_TENANT_SCHEMA);
        return tenantDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TenantDetails.class).block();
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
        tenantDetails = createEntity(em);
    }

    @Test
    void createTenantDetails() throws Exception {
        int databaseSizeBeforeCreate = tenantDetailsRepository.findAll().collectList().block().size();
        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        TenantDetails testTenantDetails = tenantDetailsList.get(tenantDetailsList.size() - 1);
        assertThat(testTenantDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTenantDetails.getTenantSchema()).isEqualTo(DEFAULT_TENANT_SCHEMA);
    }

    @Test
    void createTenantDetailsWithExistingId() throws Exception {
        // Create the TenantDetails with an existing ID
        tenantDetails.setId(1L);
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        int databaseSizeBeforeCreate = tenantDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllTenantDetails() {
        // Initialize the database
        tenantDetailsRepository.save(tenantDetails).block();

        // Get all the tenantDetailsList
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
            .value(hasItem(tenantDetails.getId().intValue()))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID))
            .jsonPath("$.[*].tenantSchema")
            .value(hasItem(DEFAULT_TENANT_SCHEMA));
    }

    @Test
    void getTenantDetails() {
        // Initialize the database
        tenantDetailsRepository.save(tenantDetails).block();

        // Get the tenantDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tenantDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tenantDetails.getId().intValue()))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID))
            .jsonPath("$.tenantSchema")
            .value(is(DEFAULT_TENANT_SCHEMA));
    }

    @Test
    void getNonExistingTenantDetails() {
        // Get the tenantDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingTenantDetails() throws Exception {
        // Initialize the database
        tenantDetailsRepository.save(tenantDetails).block();

        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();

        // Update the tenantDetails
        TenantDetails updatedTenantDetails = tenantDetailsRepository.findById(tenantDetails.getId()).block();
        updatedTenantDetails.tenantId(UPDATED_TENANT_ID).tenantSchema(UPDATED_TENANT_SCHEMA);
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(updatedTenantDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tenantDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
        TenantDetails testTenantDetails = tenantDetailsList.get(tenantDetailsList.size() - 1);
        assertThat(testTenantDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTenantDetails.getTenantSchema()).isEqualTo(UPDATED_TENANT_SCHEMA);
    }

    @Test
    void putNonExistingTenantDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();
        tenantDetails.setId(longCount.incrementAndGet());

        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tenantDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTenantDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();
        tenantDetails.setId(longCount.incrementAndGet());

        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTenantDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();
        tenantDetails.setId(longCount.incrementAndGet());

        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTenantDetailsWithPatch() throws Exception {
        // Initialize the database
        tenantDetailsRepository.save(tenantDetails).block();

        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();

        // Update the tenantDetails using partial update
        TenantDetails partialUpdatedTenantDetails = new TenantDetails();
        partialUpdatedTenantDetails.setId(tenantDetails.getId());

        partialUpdatedTenantDetails.tenantSchema(UPDATED_TENANT_SCHEMA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTenantDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTenantDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
        TenantDetails testTenantDetails = tenantDetailsList.get(tenantDetailsList.size() - 1);
        assertThat(testTenantDetails.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testTenantDetails.getTenantSchema()).isEqualTo(UPDATED_TENANT_SCHEMA);
    }

    @Test
    void fullUpdateTenantDetailsWithPatch() throws Exception {
        // Initialize the database
        tenantDetailsRepository.save(tenantDetails).block();

        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();

        // Update the tenantDetails using partial update
        TenantDetails partialUpdatedTenantDetails = new TenantDetails();
        partialUpdatedTenantDetails.setId(tenantDetails.getId());

        partialUpdatedTenantDetails.tenantId(UPDATED_TENANT_ID).tenantSchema(UPDATED_TENANT_SCHEMA);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTenantDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTenantDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
        TenantDetails testTenantDetails = tenantDetailsList.get(tenantDetailsList.size() - 1);
        assertThat(testTenantDetails.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testTenantDetails.getTenantSchema()).isEqualTo(UPDATED_TENANT_SCHEMA);
    }

    @Test
    void patchNonExistingTenantDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();
        tenantDetails.setId(longCount.incrementAndGet());

        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, tenantDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTenantDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();
        tenantDetails.setId(longCount.incrementAndGet());

        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTenantDetails() throws Exception {
        int databaseSizeBeforeUpdate = tenantDetailsRepository.findAll().collectList().block().size();
        tenantDetails.setId(longCount.incrementAndGet());

        // Create the TenantDetails
        TenantDetailsDTO tenantDetailsDTO = tenantDetailsMapper.toDto(tenantDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tenantDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TenantDetails in the database
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTenantDetails() {
        // Initialize the database
        tenantDetailsRepository.save(tenantDetails).block();

        int databaseSizeBeforeDelete = tenantDetailsRepository.findAll().collectList().block().size();

        // Delete the tenantDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, tenantDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<TenantDetails> tenantDetailsList = tenantDetailsRepository.findAll().collectList().block();
        assertThat(tenantDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
