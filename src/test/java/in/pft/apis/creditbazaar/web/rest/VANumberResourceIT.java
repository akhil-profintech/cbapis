package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.VANumber;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.VANumberRepository;
import in.pft.apis.creditbazaar.service.VANumberService;
import in.pft.apis.creditbazaar.service.dto.VANumberDTO;
import in.pft.apis.creditbazaar.service.mapper.VANumberMapper;
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
 * Integration tests for the {@link VANumberResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class VANumberResourceIT {

    private static final Long DEFAULT_VA_ID = 1L;
    private static final Long UPDATED_VA_ID = 2L;

    private static final String DEFAULT_POOLING_ACC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_POOLING_ACC_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_VIRTUAL_ACC_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VIRTUAL_ACC_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_VA_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_VA_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FINANCE_REQUEST_ID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCE_REQUEST_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_GROUP_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GROUP_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/va-numbers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private VANumberRepository vANumberRepository;

    @Mock
    private VANumberRepository vANumberRepositoryMock;

    @Autowired
    private VANumberMapper vANumberMapper;

    @Mock
    private VANumberService vANumberServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private VANumber vANumber;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VANumber createEntity(EntityManager em) {
        VANumber vANumber = new VANumber()
            .vaId(DEFAULT_VA_ID)
            .poolingAccNumber(DEFAULT_POOLING_ACC_NUMBER)
            .virtualAccNumber(DEFAULT_VIRTUAL_ACC_NUMBER)
            .vaStatus(DEFAULT_VA_STATUS)
            .financeRequestId(DEFAULT_FINANCE_REQUEST_ID)
            .subGroupId(DEFAULT_SUB_GROUP_ID);
        return vANumber;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VANumber createUpdatedEntity(EntityManager em) {
        VANumber vANumber = new VANumber()
            .vaId(UPDATED_VA_ID)
            .poolingAccNumber(UPDATED_POOLING_ACC_NUMBER)
            .virtualAccNumber(UPDATED_VIRTUAL_ACC_NUMBER)
            .vaStatus(UPDATED_VA_STATUS)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .subGroupId(UPDATED_SUB_GROUP_ID);
        return vANumber;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(VANumber.class).block();
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
        vANumber = createEntity(em);
    }

    @Test
    void createVANumber() throws Exception {
        int databaseSizeBeforeCreate = vANumberRepository.findAll().collectList().block().size();
        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeCreate + 1);
        VANumber testVANumber = vANumberList.get(vANumberList.size() - 1);
        assertThat(testVANumber.getVaId()).isEqualTo(DEFAULT_VA_ID);
        assertThat(testVANumber.getPoolingAccNumber()).isEqualTo(DEFAULT_POOLING_ACC_NUMBER);
        assertThat(testVANumber.getVirtualAccNumber()).isEqualTo(DEFAULT_VIRTUAL_ACC_NUMBER);
        assertThat(testVANumber.getVaStatus()).isEqualTo(DEFAULT_VA_STATUS);
        assertThat(testVANumber.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testVANumber.getSubGroupId()).isEqualTo(DEFAULT_SUB_GROUP_ID);
    }

    @Test
    void createVANumberWithExistingId() throws Exception {
        // Create the VANumber with an existing ID
        vANumber.setId(1L);
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        int databaseSizeBeforeCreate = vANumberRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkVaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = vANumberRepository.findAll().collectList().block().size();
        // set the field null
        vANumber.setVaId(null);

        // Create the VANumber, which fails.
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkPoolingAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = vANumberRepository.findAll().collectList().block().size();
        // set the field null
        vANumber.setPoolingAccNumber(null);

        // Create the VANumber, which fails.
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVirtualAccNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = vANumberRepository.findAll().collectList().block().size();
        // set the field null
        vANumber.setVirtualAccNumber(null);

        // Create the VANumber, which fails.
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkVaStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = vANumberRepository.findAll().collectList().block().size();
        // set the field null
        vANumber.setVaStatus(null);

        // Create the VANumber, which fails.
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllVANumbers() {
        // Initialize the database
        vANumberRepository.save(vANumber).block();

        // Get all the vANumberList
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
            .value(hasItem(vANumber.getId().intValue()))
            .jsonPath("$.[*].vaId")
            .value(hasItem(DEFAULT_VA_ID.intValue()))
            .jsonPath("$.[*].poolingAccNumber")
            .value(hasItem(DEFAULT_POOLING_ACC_NUMBER))
            .jsonPath("$.[*].virtualAccNumber")
            .value(hasItem(DEFAULT_VIRTUAL_ACC_NUMBER))
            .jsonPath("$.[*].vaStatus")
            .value(hasItem(DEFAULT_VA_STATUS))
            .jsonPath("$.[*].financeRequestId")
            .value(hasItem(DEFAULT_FINANCE_REQUEST_ID))
            .jsonPath("$.[*].subGroupId")
            .value(hasItem(DEFAULT_SUB_GROUP_ID));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllVANumbersWithEagerRelationshipsIsEnabled() {
        when(vANumberServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(vANumberServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllVANumbersWithEagerRelationshipsIsNotEnabled() {
        when(vANumberServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(vANumberRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getVANumber() {
        // Initialize the database
        vANumberRepository.save(vANumber).block();

        // Get the vANumber
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, vANumber.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(vANumber.getId().intValue()))
            .jsonPath("$.vaId")
            .value(is(DEFAULT_VA_ID.intValue()))
            .jsonPath("$.poolingAccNumber")
            .value(is(DEFAULT_POOLING_ACC_NUMBER))
            .jsonPath("$.virtualAccNumber")
            .value(is(DEFAULT_VIRTUAL_ACC_NUMBER))
            .jsonPath("$.vaStatus")
            .value(is(DEFAULT_VA_STATUS))
            .jsonPath("$.financeRequestId")
            .value(is(DEFAULT_FINANCE_REQUEST_ID))
            .jsonPath("$.subGroupId")
            .value(is(DEFAULT_SUB_GROUP_ID));
    }

    @Test
    void getNonExistingVANumber() {
        // Get the vANumber
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingVANumber() throws Exception {
        // Initialize the database
        vANumberRepository.save(vANumber).block();

        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();

        // Update the vANumber
        VANumber updatedVANumber = vANumberRepository.findById(vANumber.getId()).block();
        updatedVANumber
            .vaId(UPDATED_VA_ID)
            .poolingAccNumber(UPDATED_POOLING_ACC_NUMBER)
            .virtualAccNumber(UPDATED_VIRTUAL_ACC_NUMBER)
            .vaStatus(UPDATED_VA_STATUS)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .subGroupId(UPDATED_SUB_GROUP_ID);
        VANumberDTO vANumberDTO = vANumberMapper.toDto(updatedVANumber);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, vANumberDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
        VANumber testVANumber = vANumberList.get(vANumberList.size() - 1);
        assertThat(testVANumber.getVaId()).isEqualTo(UPDATED_VA_ID);
        assertThat(testVANumber.getPoolingAccNumber()).isEqualTo(UPDATED_POOLING_ACC_NUMBER);
        assertThat(testVANumber.getVirtualAccNumber()).isEqualTo(UPDATED_VIRTUAL_ACC_NUMBER);
        assertThat(testVANumber.getVaStatus()).isEqualTo(UPDATED_VA_STATUS);
        assertThat(testVANumber.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testVANumber.getSubGroupId()).isEqualTo(UPDATED_SUB_GROUP_ID);
    }

    @Test
    void putNonExistingVANumber() throws Exception {
        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();
        vANumber.setId(longCount.incrementAndGet());

        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, vANumberDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchVANumber() throws Exception {
        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();
        vANumber.setId(longCount.incrementAndGet());

        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamVANumber() throws Exception {
        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();
        vANumber.setId(longCount.incrementAndGet());

        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateVANumberWithPatch() throws Exception {
        // Initialize the database
        vANumberRepository.save(vANumber).block();

        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();

        // Update the vANumber using partial update
        VANumber partialUpdatedVANumber = new VANumber();
        partialUpdatedVANumber.setId(vANumber.getId());

        partialUpdatedVANumber.vaId(UPDATED_VA_ID).vaStatus(UPDATED_VA_STATUS).subGroupId(UPDATED_SUB_GROUP_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVANumber.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVANumber))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
        VANumber testVANumber = vANumberList.get(vANumberList.size() - 1);
        assertThat(testVANumber.getVaId()).isEqualTo(UPDATED_VA_ID);
        assertThat(testVANumber.getPoolingAccNumber()).isEqualTo(DEFAULT_POOLING_ACC_NUMBER);
        assertThat(testVANumber.getVirtualAccNumber()).isEqualTo(DEFAULT_VIRTUAL_ACC_NUMBER);
        assertThat(testVANumber.getVaStatus()).isEqualTo(UPDATED_VA_STATUS);
        assertThat(testVANumber.getFinanceRequestId()).isEqualTo(DEFAULT_FINANCE_REQUEST_ID);
        assertThat(testVANumber.getSubGroupId()).isEqualTo(UPDATED_SUB_GROUP_ID);
    }

    @Test
    void fullUpdateVANumberWithPatch() throws Exception {
        // Initialize the database
        vANumberRepository.save(vANumber).block();

        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();

        // Update the vANumber using partial update
        VANumber partialUpdatedVANumber = new VANumber();
        partialUpdatedVANumber.setId(vANumber.getId());

        partialUpdatedVANumber
            .vaId(UPDATED_VA_ID)
            .poolingAccNumber(UPDATED_POOLING_ACC_NUMBER)
            .virtualAccNumber(UPDATED_VIRTUAL_ACC_NUMBER)
            .vaStatus(UPDATED_VA_STATUS)
            .financeRequestId(UPDATED_FINANCE_REQUEST_ID)
            .subGroupId(UPDATED_SUB_GROUP_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedVANumber.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedVANumber))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
        VANumber testVANumber = vANumberList.get(vANumberList.size() - 1);
        assertThat(testVANumber.getVaId()).isEqualTo(UPDATED_VA_ID);
        assertThat(testVANumber.getPoolingAccNumber()).isEqualTo(UPDATED_POOLING_ACC_NUMBER);
        assertThat(testVANumber.getVirtualAccNumber()).isEqualTo(UPDATED_VIRTUAL_ACC_NUMBER);
        assertThat(testVANumber.getVaStatus()).isEqualTo(UPDATED_VA_STATUS);
        assertThat(testVANumber.getFinanceRequestId()).isEqualTo(UPDATED_FINANCE_REQUEST_ID);
        assertThat(testVANumber.getSubGroupId()).isEqualTo(UPDATED_SUB_GROUP_ID);
    }

    @Test
    void patchNonExistingVANumber() throws Exception {
        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();
        vANumber.setId(longCount.incrementAndGet());

        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, vANumberDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchVANumber() throws Exception {
        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();
        vANumber.setId(longCount.incrementAndGet());

        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamVANumber() throws Exception {
        int databaseSizeBeforeUpdate = vANumberRepository.findAll().collectList().block().size();
        vANumber.setId(longCount.incrementAndGet());

        // Create the VANumber
        VANumberDTO vANumberDTO = vANumberMapper.toDto(vANumber);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(vANumberDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the VANumber in the database
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteVANumber() {
        // Initialize the database
        vANumberRepository.save(vANumber).block();

        int databaseSizeBeforeDelete = vANumberRepository.findAll().collectList().block().size();

        // Delete the vANumber
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, vANumber.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<VANumber> vANumberList = vANumberRepository.findAll().collectList().block();
        assertThat(vANumberList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
