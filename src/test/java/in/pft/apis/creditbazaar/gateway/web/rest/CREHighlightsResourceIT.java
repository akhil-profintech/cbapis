package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.CREHighlights;
import in.pft.apis.creditbazaar.gateway.repository.CREHighlightsRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.CREHighlightsService;
import in.pft.apis.creditbazaar.gateway.service.dto.CREHighlightsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.CREHighlightsMapper;
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
 * Integration tests for the {@link CREHighlightsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class CREHighlightsResourceIT {

    private static final Long DEFAULT_CRE_HIGHLIGHTS_ID = 1L;
    private static final Long UPDATED_CRE_HIGHLIGHTS_ID = 2L;

    private static final String DEFAULT_CRE_HIGHLIGHTS_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_HIGHLIGHTS_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CRE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_CRE_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_HIGHLIGHTS = "AAAAAAAAAA";
    private static final String UPDATED_HIGHLIGHTS = "BBBBBBBBBB";

    private static final Long DEFAULT_ASSESSMENT_ID = 1L;
    private static final Long UPDATED_ASSESSMENT_ID = 2L;

    private static final String ENTITY_API_URL = "/api/cre-highlights";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CREHighlightsRepository cREHighlightsRepository;

    @Mock
    private CREHighlightsRepository cREHighlightsRepositoryMock;

    @Autowired
    private CREHighlightsMapper cREHighlightsMapper;

    @Mock
    private CREHighlightsService cREHighlightsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private CREHighlights cREHighlights;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CREHighlights createEntity(EntityManager em) {
        CREHighlights cREHighlights = new CREHighlights()
            .creHighlightsId(DEFAULT_CRE_HIGHLIGHTS_ID)
            .creHighlightsUlidId(DEFAULT_CRE_HIGHLIGHTS_ULID_ID)
            .creRequestId(DEFAULT_CRE_REQUEST_ID)
            .highlights(DEFAULT_HIGHLIGHTS)
            .assessmentId(DEFAULT_ASSESSMENT_ID);
        return cREHighlights;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CREHighlights createUpdatedEntity(EntityManager em) {
        CREHighlights cREHighlights = new CREHighlights()
            .creHighlightsId(UPDATED_CRE_HIGHLIGHTS_ID)
            .creHighlightsUlidId(UPDATED_CRE_HIGHLIGHTS_ULID_ID)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .highlights(UPDATED_HIGHLIGHTS)
            .assessmentId(UPDATED_ASSESSMENT_ID);
        return cREHighlights;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(CREHighlights.class).block();
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
        cREHighlights = createEntity(em);
    }

    @Test
    void createCREHighlights() throws Exception {
        int databaseSizeBeforeCreate = cREHighlightsRepository.findAll().collectList().block().size();
        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeCreate + 1);
        CREHighlights testCREHighlights = cREHighlightsList.get(cREHighlightsList.size() - 1);
        assertThat(testCREHighlights.getCreHighlightsId()).isEqualTo(DEFAULT_CRE_HIGHLIGHTS_ID);
        assertThat(testCREHighlights.getCreHighlightsUlidId()).isEqualTo(DEFAULT_CRE_HIGHLIGHTS_ULID_ID);
        assertThat(testCREHighlights.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testCREHighlights.getHighlights()).isEqualTo(DEFAULT_HIGHLIGHTS);
        assertThat(testCREHighlights.getAssessmentId()).isEqualTo(DEFAULT_ASSESSMENT_ID);
    }

    @Test
    void createCREHighlightsWithExistingId() throws Exception {
        // Create the CREHighlights with an existing ID
        cREHighlights.setId(1L);
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        int databaseSizeBeforeCreate = cREHighlightsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllCREHighlights() {
        // Initialize the database
        cREHighlightsRepository.save(cREHighlights).block();

        // Get all the cREHighlightsList
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
            .value(hasItem(cREHighlights.getId().intValue()))
            .jsonPath("$.[*].creHighlightsId")
            .value(hasItem(DEFAULT_CRE_HIGHLIGHTS_ID.intValue()))
            .jsonPath("$.[*].creHighlightsUlidId")
            .value(hasItem(DEFAULT_CRE_HIGHLIGHTS_ULID_ID))
            .jsonPath("$.[*].creRequestId")
            .value(hasItem(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.[*].highlights")
            .value(hasItem(DEFAULT_HIGHLIGHTS))
            .jsonPath("$.[*].assessmentId")
            .value(hasItem(DEFAULT_ASSESSMENT_ID.intValue()));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCREHighlightsWithEagerRelationshipsIsEnabled() {
        when(cREHighlightsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(cREHighlightsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCREHighlightsWithEagerRelationshipsIsNotEnabled() {
        when(cREHighlightsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(cREHighlightsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getCREHighlights() {
        // Initialize the database
        cREHighlightsRepository.save(cREHighlights).block();

        // Get the cREHighlights
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, cREHighlights.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(cREHighlights.getId().intValue()))
            .jsonPath("$.creHighlightsId")
            .value(is(DEFAULT_CRE_HIGHLIGHTS_ID.intValue()))
            .jsonPath("$.creHighlightsUlidId")
            .value(is(DEFAULT_CRE_HIGHLIGHTS_ULID_ID))
            .jsonPath("$.creRequestId")
            .value(is(DEFAULT_CRE_REQUEST_ID))
            .jsonPath("$.highlights")
            .value(is(DEFAULT_HIGHLIGHTS))
            .jsonPath("$.assessmentId")
            .value(is(DEFAULT_ASSESSMENT_ID.intValue()));
    }

    @Test
    void getNonExistingCREHighlights() {
        // Get the cREHighlights
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingCREHighlights() throws Exception {
        // Initialize the database
        cREHighlightsRepository.save(cREHighlights).block();

        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();

        // Update the cREHighlights
        CREHighlights updatedCREHighlights = cREHighlightsRepository.findById(cREHighlights.getId()).block();
        updatedCREHighlights
            .creHighlightsId(UPDATED_CRE_HIGHLIGHTS_ID)
            .creHighlightsUlidId(UPDATED_CRE_HIGHLIGHTS_ULID_ID)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .highlights(UPDATED_HIGHLIGHTS)
            .assessmentId(UPDATED_ASSESSMENT_ID);
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(updatedCREHighlights);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cREHighlightsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
        CREHighlights testCREHighlights = cREHighlightsList.get(cREHighlightsList.size() - 1);
        assertThat(testCREHighlights.getCreHighlightsId()).isEqualTo(UPDATED_CRE_HIGHLIGHTS_ID);
        assertThat(testCREHighlights.getCreHighlightsUlidId()).isEqualTo(UPDATED_CRE_HIGHLIGHTS_ULID_ID);
        assertThat(testCREHighlights.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCREHighlights.getHighlights()).isEqualTo(UPDATED_HIGHLIGHTS);
        assertThat(testCREHighlights.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
    }

    @Test
    void putNonExistingCREHighlights() throws Exception {
        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();
        cREHighlights.setId(longCount.incrementAndGet());

        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, cREHighlightsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchCREHighlights() throws Exception {
        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();
        cREHighlights.setId(longCount.incrementAndGet());

        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamCREHighlights() throws Exception {
        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();
        cREHighlights.setId(longCount.incrementAndGet());

        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateCREHighlightsWithPatch() throws Exception {
        // Initialize the database
        cREHighlightsRepository.save(cREHighlights).block();

        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();

        // Update the cREHighlights using partial update
        CREHighlights partialUpdatedCREHighlights = new CREHighlights();
        partialUpdatedCREHighlights.setId(cREHighlights.getId());

        partialUpdatedCREHighlights.creHighlightsUlidId(UPDATED_CRE_HIGHLIGHTS_ULID_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCREHighlights.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCREHighlights))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
        CREHighlights testCREHighlights = cREHighlightsList.get(cREHighlightsList.size() - 1);
        assertThat(testCREHighlights.getCreHighlightsId()).isEqualTo(DEFAULT_CRE_HIGHLIGHTS_ID);
        assertThat(testCREHighlights.getCreHighlightsUlidId()).isEqualTo(UPDATED_CRE_HIGHLIGHTS_ULID_ID);
        assertThat(testCREHighlights.getCreRequestId()).isEqualTo(DEFAULT_CRE_REQUEST_ID);
        assertThat(testCREHighlights.getHighlights()).isEqualTo(DEFAULT_HIGHLIGHTS);
        assertThat(testCREHighlights.getAssessmentId()).isEqualTo(DEFAULT_ASSESSMENT_ID);
    }

    @Test
    void fullUpdateCREHighlightsWithPatch() throws Exception {
        // Initialize the database
        cREHighlightsRepository.save(cREHighlights).block();

        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();

        // Update the cREHighlights using partial update
        CREHighlights partialUpdatedCREHighlights = new CREHighlights();
        partialUpdatedCREHighlights.setId(cREHighlights.getId());

        partialUpdatedCREHighlights
            .creHighlightsId(UPDATED_CRE_HIGHLIGHTS_ID)
            .creHighlightsUlidId(UPDATED_CRE_HIGHLIGHTS_ULID_ID)
            .creRequestId(UPDATED_CRE_REQUEST_ID)
            .highlights(UPDATED_HIGHLIGHTS)
            .assessmentId(UPDATED_ASSESSMENT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedCREHighlights.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedCREHighlights))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
        CREHighlights testCREHighlights = cREHighlightsList.get(cREHighlightsList.size() - 1);
        assertThat(testCREHighlights.getCreHighlightsId()).isEqualTo(UPDATED_CRE_HIGHLIGHTS_ID);
        assertThat(testCREHighlights.getCreHighlightsUlidId()).isEqualTo(UPDATED_CRE_HIGHLIGHTS_ULID_ID);
        assertThat(testCREHighlights.getCreRequestId()).isEqualTo(UPDATED_CRE_REQUEST_ID);
        assertThat(testCREHighlights.getHighlights()).isEqualTo(UPDATED_HIGHLIGHTS);
        assertThat(testCREHighlights.getAssessmentId()).isEqualTo(UPDATED_ASSESSMENT_ID);
    }

    @Test
    void patchNonExistingCREHighlights() throws Exception {
        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();
        cREHighlights.setId(longCount.incrementAndGet());

        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, cREHighlightsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchCREHighlights() throws Exception {
        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();
        cREHighlights.setId(longCount.incrementAndGet());

        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamCREHighlights() throws Exception {
        int databaseSizeBeforeUpdate = cREHighlightsRepository.findAll().collectList().block().size();
        cREHighlights.setId(longCount.incrementAndGet());

        // Create the CREHighlights
        CREHighlightsDTO cREHighlightsDTO = cREHighlightsMapper.toDto(cREHighlights);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(cREHighlightsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the CREHighlights in the database
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteCREHighlights() {
        // Initialize the database
        cREHighlightsRepository.save(cREHighlights).block();

        int databaseSizeBeforeDelete = cREHighlightsRepository.findAll().collectList().block().size();

        // Delete the cREHighlights
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, cREHighlights.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<CREHighlights> cREHighlightsList = cREHighlightsRepository.findAll().collectList().block();
        assertThat(cREHighlightsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
