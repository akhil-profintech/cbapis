package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.DocDetails;
import in.pft.apis.creditbazaar.gateway.repository.DocDetailsRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.DocDetailsService;
import in.pft.apis.creditbazaar.gateway.service.dto.DocDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.DocDetailsMapper;
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
 * Integration tests for the {@link DocDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DocDetailsResourceIT {

    private static final Long DEFAULT_DOC_DETAILS_ID = 1L;
    private static final Long UPDATED_DOC_DETAILS_ID = 2L;

    private static final String DEFAULT_DOC_DETAILS_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_DOC_DETAILS_ULID_ID = "BBBBBBBBBB";

    private static final Long DEFAULT_DOC_RECORD_ID = 1L;
    private static final Long UPDATED_DOC_RECORD_ID = 2L;

    private static final String DEFAULT_LINK = "AAAAAAAAAA";
    private static final String UPDATED_LINK = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DOC_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/doc-details";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DocDetailsRepository docDetailsRepository;

    @Mock
    private DocDetailsRepository docDetailsRepositoryMock;

    @Autowired
    private DocDetailsMapper docDetailsMapper;

    @Mock
    private DocDetailsService docDetailsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private DocDetails docDetails;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocDetails createEntity(EntityManager em) {
        DocDetails docDetails = new DocDetails()
            .docDetailsId(DEFAULT_DOC_DETAILS_ID)
            .docDetailsUlidId(DEFAULT_DOC_DETAILS_ULID_ID)
            .docRecordId(DEFAULT_DOC_RECORD_ID)
            .link(DEFAULT_LINK)
            .description(DEFAULT_DESCRIPTION)
            .docType(DEFAULT_DOC_TYPE)
            .status(DEFAULT_STATUS);
        return docDetails;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DocDetails createUpdatedEntity(EntityManager em) {
        DocDetails docDetails = new DocDetails()
            .docDetailsId(UPDATED_DOC_DETAILS_ID)
            .docDetailsUlidId(UPDATED_DOC_DETAILS_ULID_ID)
            .docRecordId(UPDATED_DOC_RECORD_ID)
            .link(UPDATED_LINK)
            .description(UPDATED_DESCRIPTION)
            .docType(UPDATED_DOC_TYPE)
            .status(UPDATED_STATUS);
        return docDetails;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(DocDetails.class).block();
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
        docDetails = createEntity(em);
    }

    @Test
    void createDocDetails() throws Exception {
        int databaseSizeBeforeCreate = docDetailsRepository.findAll().collectList().block().size();
        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        DocDetails testDocDetails = docDetailsList.get(docDetailsList.size() - 1);
        assertThat(testDocDetails.getDocDetailsId()).isEqualTo(DEFAULT_DOC_DETAILS_ID);
        assertThat(testDocDetails.getDocDetailsUlidId()).isEqualTo(DEFAULT_DOC_DETAILS_ULID_ID);
        assertThat(testDocDetails.getDocRecordId()).isEqualTo(DEFAULT_DOC_RECORD_ID);
        assertThat(testDocDetails.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testDocDetails.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDocDetails.getDocType()).isEqualTo(DEFAULT_DOC_TYPE);
        assertThat(testDocDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    void createDocDetailsWithExistingId() throws Exception {
        // Create the DocDetails with an existing ID
        docDetails.setId(1L);
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        int databaseSizeBeforeCreate = docDetailsRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkDocRecordIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = docDetailsRepository.findAll().collectList().block().size();
        // set the field null
        docDetails.setDocRecordId(null);

        // Create the DocDetails, which fails.
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLinkIsRequired() throws Exception {
        int databaseSizeBeforeTest = docDetailsRepository.findAll().collectList().block().size();
        // set the field null
        docDetails.setLink(null);

        // Create the DocDetails, which fails.
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = docDetailsRepository.findAll().collectList().block().size();
        // set the field null
        docDetails.setDescription(null);

        // Create the DocDetails, which fails.
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkDocTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = docDetailsRepository.findAll().collectList().block().size();
        // set the field null
        docDetails.setDocType(null);

        // Create the DocDetails, which fails.
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = docDetailsRepository.findAll().collectList().block().size();
        // set the field null
        docDetails.setStatus(null);

        // Create the DocDetails, which fails.
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllDocDetails() {
        // Initialize the database
        docDetailsRepository.save(docDetails).block();

        // Get all the docDetailsList
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
            .value(hasItem(docDetails.getId().intValue()))
            .jsonPath("$.[*].docDetailsId")
            .value(hasItem(DEFAULT_DOC_DETAILS_ID.intValue()))
            .jsonPath("$.[*].docDetailsUlidId")
            .value(hasItem(DEFAULT_DOC_DETAILS_ULID_ID))
            .jsonPath("$.[*].docRecordId")
            .value(hasItem(DEFAULT_DOC_RECORD_ID.intValue()))
            .jsonPath("$.[*].link")
            .value(hasItem(DEFAULT_LINK))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION))
            .jsonPath("$.[*].docType")
            .value(hasItem(DEFAULT_DOC_TYPE))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDocDetailsWithEagerRelationshipsIsEnabled() {
        when(docDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(docDetailsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllDocDetailsWithEagerRelationshipsIsNotEnabled() {
        when(docDetailsServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(docDetailsRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getDocDetails() {
        // Initialize the database
        docDetailsRepository.save(docDetails).block();

        // Get the docDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, docDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(docDetails.getId().intValue()))
            .jsonPath("$.docDetailsId")
            .value(is(DEFAULT_DOC_DETAILS_ID.intValue()))
            .jsonPath("$.docDetailsUlidId")
            .value(is(DEFAULT_DOC_DETAILS_ULID_ID))
            .jsonPath("$.docRecordId")
            .value(is(DEFAULT_DOC_RECORD_ID.intValue()))
            .jsonPath("$.link")
            .value(is(DEFAULT_LINK))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION))
            .jsonPath("$.docType")
            .value(is(DEFAULT_DOC_TYPE))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS));
    }

    @Test
    void getNonExistingDocDetails() {
        // Get the docDetails
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDocDetails() throws Exception {
        // Initialize the database
        docDetailsRepository.save(docDetails).block();

        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();

        // Update the docDetails
        DocDetails updatedDocDetails = docDetailsRepository.findById(docDetails.getId()).block();
        updatedDocDetails
            .docDetailsId(UPDATED_DOC_DETAILS_ID)
            .docDetailsUlidId(UPDATED_DOC_DETAILS_ULID_ID)
            .docRecordId(UPDATED_DOC_RECORD_ID)
            .link(UPDATED_LINK)
            .description(UPDATED_DESCRIPTION)
            .docType(UPDATED_DOC_TYPE)
            .status(UPDATED_STATUS);
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(updatedDocDetails);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, docDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
        DocDetails testDocDetails = docDetailsList.get(docDetailsList.size() - 1);
        assertThat(testDocDetails.getDocDetailsId()).isEqualTo(UPDATED_DOC_DETAILS_ID);
        assertThat(testDocDetails.getDocDetailsUlidId()).isEqualTo(UPDATED_DOC_DETAILS_ULID_ID);
        assertThat(testDocDetails.getDocRecordId()).isEqualTo(UPDATED_DOC_RECORD_ID);
        assertThat(testDocDetails.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testDocDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDocDetails.getDocType()).isEqualTo(UPDATED_DOC_TYPE);
        assertThat(testDocDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void putNonExistingDocDetails() throws Exception {
        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();
        docDetails.setId(longCount.incrementAndGet());

        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, docDetailsDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDocDetails() throws Exception {
        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();
        docDetails.setId(longCount.incrementAndGet());

        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDocDetails() throws Exception {
        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();
        docDetails.setId(longCount.incrementAndGet());

        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDocDetailsWithPatch() throws Exception {
        // Initialize the database
        docDetailsRepository.save(docDetails).block();

        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();

        // Update the docDetails using partial update
        DocDetails partialUpdatedDocDetails = new DocDetails();
        partialUpdatedDocDetails.setId(docDetails.getId());

        partialUpdatedDocDetails
            .docDetailsUlidId(UPDATED_DOC_DETAILS_ULID_ID)
            .description(UPDATED_DESCRIPTION)
            .docType(UPDATED_DOC_TYPE)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDocDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDocDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
        DocDetails testDocDetails = docDetailsList.get(docDetailsList.size() - 1);
        assertThat(testDocDetails.getDocDetailsId()).isEqualTo(DEFAULT_DOC_DETAILS_ID);
        assertThat(testDocDetails.getDocDetailsUlidId()).isEqualTo(UPDATED_DOC_DETAILS_ULID_ID);
        assertThat(testDocDetails.getDocRecordId()).isEqualTo(DEFAULT_DOC_RECORD_ID);
        assertThat(testDocDetails.getLink()).isEqualTo(DEFAULT_LINK);
        assertThat(testDocDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDocDetails.getDocType()).isEqualTo(UPDATED_DOC_TYPE);
        assertThat(testDocDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void fullUpdateDocDetailsWithPatch() throws Exception {
        // Initialize the database
        docDetailsRepository.save(docDetails).block();

        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();

        // Update the docDetails using partial update
        DocDetails partialUpdatedDocDetails = new DocDetails();
        partialUpdatedDocDetails.setId(docDetails.getId());

        partialUpdatedDocDetails
            .docDetailsId(UPDATED_DOC_DETAILS_ID)
            .docDetailsUlidId(UPDATED_DOC_DETAILS_ULID_ID)
            .docRecordId(UPDATED_DOC_RECORD_ID)
            .link(UPDATED_LINK)
            .description(UPDATED_DESCRIPTION)
            .docType(UPDATED_DOC_TYPE)
            .status(UPDATED_STATUS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDocDetails.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedDocDetails))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
        DocDetails testDocDetails = docDetailsList.get(docDetailsList.size() - 1);
        assertThat(testDocDetails.getDocDetailsId()).isEqualTo(UPDATED_DOC_DETAILS_ID);
        assertThat(testDocDetails.getDocDetailsUlidId()).isEqualTo(UPDATED_DOC_DETAILS_ULID_ID);
        assertThat(testDocDetails.getDocRecordId()).isEqualTo(UPDATED_DOC_RECORD_ID);
        assertThat(testDocDetails.getLink()).isEqualTo(UPDATED_LINK);
        assertThat(testDocDetails.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDocDetails.getDocType()).isEqualTo(UPDATED_DOC_TYPE);
        assertThat(testDocDetails.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    void patchNonExistingDocDetails() throws Exception {
        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();
        docDetails.setId(longCount.incrementAndGet());

        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, docDetailsDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDocDetails() throws Exception {
        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();
        docDetails.setId(longCount.incrementAndGet());

        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDocDetails() throws Exception {
        int databaseSizeBeforeUpdate = docDetailsRepository.findAll().collectList().block().size();
        docDetails.setId(longCount.incrementAndGet());

        // Create the DocDetails
        DocDetailsDTO docDetailsDTO = docDetailsMapper.toDto(docDetails);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(docDetailsDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DocDetails in the database
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDocDetails() {
        // Initialize the database
        docDetailsRepository.save(docDetails).block();

        int databaseSizeBeforeDelete = docDetailsRepository.findAll().collectList().block().size();

        // Delete the docDetails
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, docDetails.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<DocDetails> docDetailsList = docDetailsRepository.findAll().collectList().block();
        assertThat(docDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
