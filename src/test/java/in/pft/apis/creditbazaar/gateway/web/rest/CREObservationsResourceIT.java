package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.CREObservations;
import in.pft.apis.creditbazaar.gateway.repository.CREObservationsRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.CREObservationsService;
import in.pft.apis.creditbazaar.gateway.service.dto.CREObservationsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CREObservationsMapper;
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
 * Integration tests for the {@link CREObservationsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CREObservationsResourceIT {

    private static final Long DEFAULT_CRE_OBSERVATIONS_ID = 1L;
    private static final Long UPDATED_CRE_OBSERVATIONS_ID = 2L;

    private static final String DEFAULT_CRE_OBSERVATIONS_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_OBSERVATIONS_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CRE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATIONS = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS = "BBBBBBBBBB";

    private static final Long DEFAULT_ASSESSMENT_ID = 1L;
    private static final Long UPDATED_ASSESSMENT_ID = 2L;

    private static final String ENTITY_API_URL = "/api/cre-observations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CREObservationsRepository cREObservationsRepository;

    @Mock
    private CREObservationsRepository cREObservationsRepositoryMock;

    @Autowired
    private CREObservationsMapper cREObservationsMapper;

    @Mock
    private CREObservationsService cREObservationsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CREObservations cREObservations;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CREObservations createEntity(EntityManager em) {
        CREObservations cREObservations = new CREObservations()
            .creObservationsId(DEFAULT_CRE_OBSERVATIONS_ID)
            .creObservationsUlidId(DEFAULT_CRE_OBSERVATIONS_ULID_ID)
            .creRequestId(DEFAULT_CRE_REQUEST_ID)
            .observations(DEFAULT_OBSERVATIONS)
            .assessmentId(DEFAULT_ASSESSMENT_ID);
        return cREObservations;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CREObservations createUpdatedEntity(EntityManager em) {
        CREObservations cREObservations = new CREObservations()
            .creObservationsId(UPDATED_CRE_OBSERVATIONS_ID)
            .creObservationsUlidId(UPDATED_CRE_OBSERVATIONS_ULID_ID)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .observations(UPDATED_OBSERVATIONS)
            .assessmentId(UPDATED_ASSESSMENT_ID);
        return cREObservations;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CREObservations.class).block();
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
        cREObservations = createEntity(em);
    }

    @Test
    void createCREObservations() throws Exception {
        int databaseSizeBeforeCreate = cREObservationsRepository.findAll().collectList().block().size();
        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeCreate + 1);
        CREObservations testCREObservations = cREObservationsList.get(cREObservationsList.size() - 1);
        assertThat(testCREObservations.getCreObservationsId()).isEqualTo(DEFAULT_CRE_OBSERVATIONS_ID);
        assertThat(testCREObservations.getCreObservationsUlidId()).isEqualTo(DEFAULT_CRE_OBSERVATIONS_ULID_ID);
        assertThat(testCREObservations.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testCREObservations.getObservations()).isEqualTo(DEFAULT_OBSERVATIONS);
        assertThat(testCREObservations.getAssessmentId()).isEqualTo(DEFAULT_ASSESSMENT_ID);
    }

    @Test
    void createCREObservationsWithExistingId() throws Exception {
        // Create the CREObservations with an existing ID
        cREObservations.setId(1L);
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        int databaseSizeBeforeCreate = cREObservationsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCREObservations() {
        // Initialize the database
        cREObservationsRepository.save(cREObservations).block();

        // Get all the cREObservationsList
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
            .value(hasItem(cREObservations.getId().intValue()))
            .jsonPath("$.[*].creObservationsId")
            .value(hasItem(DEFAULT_CRE_OBSERVATIONS_ID.intValue()))
            .jsonPath("$.[*].creObservationsUlidId")
            .value(hasItem(DEFAULT_CRE_OBSERVATIONS_ULID_ID))
            .jsonPath("$.[*].creRequestId")
            .value(hasItem(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.[*].observations")
            .value(hasItem(DEFAULT_OBSERVATIONS))
            .jsonPath("$.[*].assessmentId")
            .value(hasItem(DEFAULT_ASSESSMENT_ID.intValue()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCREObservationsWithEagerRelationshipsIsEnabled() {
        when(cREObservationsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(cREObservationsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCREObservationsWithEagerRelationshipsIsNotEnabled() {
        when(cREObservationsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(cREObservationsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getCREObservations() {
        // Initialize the database
        cREObservationsRepository.save(cREObservations).block();

        // Get the cREObservations
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, cREObservations.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(cREObservations.getId().intValue()))
            .jsonPath("$.creObservationsId")
            .value(is(DEFAULT_CRE_OBSERVATIONS_ID.intValue()))
            .jsonPath("$.creObservationsUlidId")
            .value(is(DEFAULT_CRE_OBSERVATIONS_ULID_ID))
            .jsonPath("$.creRequestId")
            .value(is(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.observations")
            .value(is(DEFAULT_OBSERVATIONS))
            .jsonPath("$.assessmentId")
            .value(is(DEFAULT_ASSESSMENT_ID.intValue()));
    }

    @Test
    void getNonExistingCREObservations() {
        // Get the cREObservations
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCREObservations() throws Exception {
        // Initialize the database
        cREObservationsRepository.save(cREObservations).block();

        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();

        // Update the cREObservations
        CREObservations updatedCREObservations = cREObservationsRepository.findById(cREObservations.getId()).block();
        updatedCREObservations
            .creObservationsId(UPDATED_CRE_OBSERVATIONS_ID)
            .creObservationsUlidId(UPDATED_CRE_OBSERVATIONS_ULID_ID)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .observations(UPDATED_OBSERVATIONS)
            .assessmentId(UPDATED_ASSESSMENT_ID);
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(updatedCREObservations);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cREObservationsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
        CREObservations testCREObservations = cREObservationsList.get(cREObservationsList.size() - 1);
        assertThat(testCREObservations.getCreObservationsId()).isEqualTo(UPDATED_CRE_OBSERVATIONS_ID);
        assertThat(testCREObservations.getCreObservationsUlidId()).isEqualTo(UPDATED_CRE_OBSERVATIONS_ULID_ID);
        assertThat(testCREObservations.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCREObservations.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testCREObservations.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
    }

    @Test
    void putNonExistingCREObservations() throws Exception {
        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();
        cREObservations.setId(longCount.incrementAndGet());

        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cREObservationsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCREObservations() throws Exception {
        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();
        cREObservations.setId(longCount.incrementAndGet());

        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCREObservations() throws Exception {
        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();
        cREObservations.setId(longCount.incrementAndGet());

        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCREObservationsWithPatch() throws Exception {
        // Initialize the database
        cREObservationsRepository.save(cREObservations).block();

        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();

        // Update the cREObservations using partial update
        CREObservations partialUpdatedCREObservations = new CREObservations();
        partialUpdatedCREObservations.setId(cREObservations.getId());

        partialUpdatedCREObservations
            .creObservationsId(UPDATED_CRE_OBSERVATIONS_ID)
            .creObservationsUlidId(UPDATED_CRE_OBSERVATIONS_ULID_ID)
            .assessmentId(UPDATED_ASSESSMENT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCREObservations.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCREObservations))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
        CREObservations testCREObservations = cREObservationsList.get(cREObservationsList.size() - 1);
        assertThat(testCREObservations.getCreObservationsId()).isEqualTo(UPDATED_CRE_OBSERVATIONS_ID);
        assertThat(testCREObservations.getCreObservationsUlidId()).isEqualTo(UPDATED_CRE_OBSERVATIONS_ULID_ID);
        assertThat(testCREObservations.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testCREObservations.getObservations()).isEqualTo(DEFAULT_OBSERVATIONS);
        assertThat(testCREObservations.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
    }

    @Test
    void fullUpdateCREObservationsWithPatch() throws Exception {
        // Initialize the database
        cREObservationsRepository.save(cREObservations).block();

        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();

        // Update the cREObservations using partial update
        CREObservations partialUpdatedCREObservations = new CREObservations();
        partialUpdatedCREObservations.setId(cREObservations.getId());

        partialUpdatedCREObservations
            .creObservationsId(UPDATED_CRE_OBSERVATIONS_ID)
            .creObservationsUlidId(UPDATED_CRE_OBSERVATIONS_ULID_ID)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .observations(UPDATED_OBSERVATIONS)
            .assessmentId(UPDATED_ASSESSMENT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCREObservations.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCREObservations))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
        CREObservations testCREObservations = cREObservationsList.get(cREObservationsList.size() - 1);
        assertThat(testCREObservations.getCreObservationsId()).isEqualTo(UPDATED_CRE_OBSERVATIONS_ID);
        assertThat(testCREObservations.getCreObservationsUlidId()).isEqualTo(UPDATED_CRE_OBSERVATIONS_ULID_ID);
        assertThat(testCREObservations.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCREObservations.getObservations()).isEqualTo(UPDATED_OBSERVATIONS);
        assertThat(testCREObservations.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
    }

    @Test
    void patchNonExistingCREObservations() throws Exception {
        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();
        cREObservations.setId(longCount.incrementAndGet());

        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, cREObservationsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCREObservations() throws Exception {
        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();
        cREObservations.setId(longCount.incrementAndGet());

        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCREObservations() throws Exception {
        int databaseSizeBeforeUpdate = cREObservationsRepository.findAll().collectList().block().size();
        cREObservations.setId(longCount.incrementAndGet());

        // Create the CREObservations
        CREObservationsDTO cREObservationsDTO = cREObservationsMapper.toDto(cREObservations);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREObservationsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CREObservations in the database
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCREObservations() {
        // Initialize the database
        cREObservationsRepository.save(cREObservations).block();

        int databaseSizeBeforeDelete = cREObservationsRepository.findAll().collectList().block().size();

        // Delete the cREObservations
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, cREObservations.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CREObservations> cREObservationsList = cREObservationsRepository.findAll().collectList().block();
        assertThat(cREObservationsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
