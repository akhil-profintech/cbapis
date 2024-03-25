package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Gstin;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.GstinRepository;
import in.pft.apis.creditbazaar.gateway.service.GstinService;
import in.pft.apis.creditbazaar.gateway.service.dto.GstinDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.GstinMapper;
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
 * Integration tests for the {@link GstinResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class GstinResourceIT {

    private static final String DEFAULT_COMPANY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COMPANY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_GST_ID = "AAAAAAAAAA";
    private static final String UPDATED_GST_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/gstins";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private GstinRepository gstinRepository;

    @Mock
    private GstinRepository gstinRepositoryMock;

    @Autowired
    private GstinMapper gstinMapper;

    @Mock
    private GstinService gstinServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Gstin gstin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gstin createEntity(EntityManager em) {
        Gstin gstin = new Gstin().companyName(DEFAULT_COMPANY_NAME).gstId(DEFAULT_GST_ID);
        return gstin;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Gstin createUpdatedEntity(EntityManager em) {
        Gstin gstin = new Gstin().companyName(UPDATED_COMPANY_NAME).gstId(UPDATED_GST_ID);
        return gstin;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Gstin.class).block();
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
        gstin = createEntity(em);
    }

    @Test
    void createGstin() throws Exception {
        int databaseSizeBeforeCreate = gstinRepository.findAll().collectList().block().size();
        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeCreate + 1);
        Gstin testGstin = gstinList.get(gstinList.size() - 1);
        assertThat(testGstin.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testGstin.getGstId()).isEqualTo(DEFAULT_GST_ID);
    }

    @Test
    void createGstinWithExistingId() throws Exception {
        // Create the Gstin with an existing ID
        gstin.setId(1L);
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        int databaseSizeBeforeCreate = gstinRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkCompanyNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = gstinRepository.findAll().collectList().block().size();
        // set the field null
        gstin.setCompanyName(null);

        // Create the Gstin, which fails.
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkGstIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = gstinRepository.findAll().collectList().block().size();
        // set the field null
        gstin.setGstId(null);

        // Create the Gstin, which fails.
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllGstins() {
        // Initialize the database
        gstinRepository.save(gstin).block();

        // Get all the gstinList
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
            .value(hasItem(gstin.getId().intValue()))
            .jsonPath("$.[*].companyName")
            .value(hasItem(DEFAULT_COMPANY_NAME))
            .jsonPath("$.[*].gstId")
            .value(hasItem(DEFAULT_GST_ID));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllGstinsWithEagerRelationshipsIsEnabled() {
        when(gstinServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(gstinServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllGstinsWithEagerRelationshipsIsNotEnabled() {
        when(gstinServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(gstinRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getGstin() {
        // Initialize the database
        gstinRepository.save(gstin).block();

        // Get the gstin
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, gstin.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(gstin.getId().intValue()))
            .jsonPath("$.companyName")
            .value(is(DEFAULT_COMPANY_NAME))
            .jsonPath("$.gstId")
            .value(is(DEFAULT_GST_ID));
    }

    @Test
    void getNonExistingGstin() {
        // Get the gstin
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingGstin() throws Exception {
        // Initialize the database
        gstinRepository.save(gstin).block();

        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();

        // Update the gstin
        Gstin updatedGstin = gstinRepository.findById(gstin.getId()).block();
        updatedGstin.companyName(UPDATED_COMPANY_NAME).gstId(UPDATED_GST_ID);
        GstinDTO gstinDTO = gstinMapper.toDto(updatedGstin);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, gstinDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
        Gstin testGstin = gstinList.get(gstinList.size() - 1);
        assertThat(testGstin.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testGstin.getGstId()).isEqualTo(UPDATED_GST_ID);
    }

    @Test
    void putNonExistingGstin() throws Exception {
        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();
        gstin.setId(longCount.incrementAndGet());

        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, gstinDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchGstin() throws Exception {
        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();
        gstin.setId(longCount.incrementAndGet());

        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamGstin() throws Exception {
        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();
        gstin.setId(longCount.incrementAndGet());

        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateGstinWithPatch() throws Exception {
        // Initialize the database
        gstinRepository.save(gstin).block();

        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();

        // Update the gstin using partial update
        Gstin partialUpdatedGstin = new Gstin();
        partialUpdatedGstin.setId(gstin.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedGstin.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedGstin))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
        Gstin testGstin = gstinList.get(gstinList.size() - 1);
        assertThat(testGstin.getCompanyName()).isEqualTo(DEFAULT_COMPANY_NAME);
        assertThat(testGstin.getGstId()).isEqualTo(DEFAULT_GST_ID);
    }

    @Test
    void fullUpdateGstinWithPatch() throws Exception {
        // Initialize the database
        gstinRepository.save(gstin).block();

        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();

        // Update the gstin using partial update
        Gstin partialUpdatedGstin = new Gstin();
        partialUpdatedGstin.setId(gstin.getId());

        partialUpdatedGstin.companyName(UPDATED_COMPANY_NAME).gstId(UPDATED_GST_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedGstin.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedGstin))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
        Gstin testGstin = gstinList.get(gstinList.size() - 1);
        assertThat(testGstin.getCompanyName()).isEqualTo(UPDATED_COMPANY_NAME);
        assertThat(testGstin.getGstId()).isEqualTo(UPDATED_GST_ID);
    }

    @Test
    void patchNonExistingGstin() throws Exception {
        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();
        gstin.setId(longCount.incrementAndGet());

        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, gstinDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchGstin() throws Exception {
        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();
        gstin.setId(longCount.incrementAndGet());

        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamGstin() throws Exception {
        int databaseSizeBeforeUpdate = gstinRepository.findAll().collectList().block().size();
        gstin.setId(longCount.incrementAndGet());

        // Create the Gstin
        GstinDTO gstinDTO = gstinMapper.toDto(gstin);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(gstinDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Gstin in the database
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteGstin() {
        // Initialize the database
        gstinRepository.save(gstin).block();

        int databaseSizeBeforeDelete = gstinRepository.findAll().collectList().block().size();

        // Delete the gstin
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, gstin.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Gstin> gstinList = gstinRepository.findAll().collectList().block();
        assertThat(gstinList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
