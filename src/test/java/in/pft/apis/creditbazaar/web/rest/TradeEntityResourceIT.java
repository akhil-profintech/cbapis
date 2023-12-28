package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.TradeEntity;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.TradeEntityRepository;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import in.pft.apis.creditbazaar.service.mapper.TradeEntityMapper;
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
 * Integration tests for the {@link TradeEntityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class TradeEntityResourceIT {

    private static final Long DEFAULT_ENTITY_ID = 1L;
    private static final Long UPDATED_ENTITY_ID = 2L;

    private static final String DEFAULT_ENTITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITY_DESC = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITY_GST = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_GST = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/trade-entities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TradeEntityRepository tradeEntityRepository;

    @Autowired
    private TradeEntityMapper tradeEntityMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private TradeEntity tradeEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeEntity createEntity(EntityManager em) {
        TradeEntity tradeEntity = new TradeEntity()
            .entityId(DEFAULT_ENTITY_ID)
            .entityName(DEFAULT_ENTITY_NAME)
            .entityDesc(DEFAULT_ENTITY_DESC)
            .entityGST(DEFAULT_ENTITY_GST);
        return tradeEntity;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TradeEntity createUpdatedEntity(EntityManager em) {
        TradeEntity tradeEntity = new TradeEntity()
            .entityId(UPDATED_ENTITY_ID)
            .entityName(UPDATED_ENTITY_NAME)
            .entityDesc(UPDATED_ENTITY_DESC)
            .entityGST(UPDATED_ENTITY_GST);
        return tradeEntity;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(TradeEntity.class).block();
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
        tradeEntity = createEntity(em);
    }

    @Test
    void createTradeEntity() throws Exception {
        int databaseSizeBeforeCreate = tradeEntityRepository.findAll().collectList().block().size();
        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeCreate + 1);
        TradeEntity testTradeEntity = tradeEntityList.get(tradeEntityList.size() - 1);
        assertThat(testTradeEntity.getEntityId()).isEqualTo(DEFAULT_ENTITY_ID);
        assertThat(testTradeEntity.getEntityName()).isEqualTo(DEFAULT_ENTITY_NAME);
        assertThat(testTradeEntity.getEntityDesc()).isEqualTo(DEFAULT_ENTITY_DESC);
        assertThat(testTradeEntity.getEntityGST()).isEqualTo(DEFAULT_ENTITY_GST);
    }

    @Test
    void createTradeEntityWithExistingId() throws Exception {
        // Create the TradeEntity with an existing ID
        tradeEntity.setId(1L);
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        int databaseSizeBeforeCreate = tradeEntityRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllTradeEntities() {
        // Initialize the database
        tradeEntityRepository.save(tradeEntity).block();

        // Get all the tradeEntityList
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
            .value(hasItem(tradeEntity.getId().intValue()))
            .jsonPath("$.[*].entityId")
            .value(hasItem(DEFAULT_ENTITY_ID.intValue()))
            .jsonPath("$.[*].entityName")
            .value(hasItem(DEFAULT_ENTITY_NAME))
            .jsonPath("$.[*].entityDesc")
            .value(hasItem(DEFAULT_ENTITY_DESC))
            .jsonPath("$.[*].entityGST")
            .value(hasItem(DEFAULT_ENTITY_GST));
    }

    @Test
    void getTradeEntity() {
        // Initialize the database
        tradeEntityRepository.save(tradeEntity).block();

        // Get the tradeEntity
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, tradeEntity.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(tradeEntity.getId().intValue()))
            .jsonPath("$.entityId")
            .value(is(DEFAULT_ENTITY_ID.intValue()))
            .jsonPath("$.entityName")
            .value(is(DEFAULT_ENTITY_NAME))
            .jsonPath("$.entityDesc")
            .value(is(DEFAULT_ENTITY_DESC))
            .jsonPath("$.entityGST")
            .value(is(DEFAULT_ENTITY_GST));
    }

    @Test
    void getNonExistingTradeEntity() {
        // Get the tradeEntity
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingTradeEntity() throws Exception {
        // Initialize the database
        tradeEntityRepository.save(tradeEntity).block();

        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();

        // Update the tradeEntity
        TradeEntity updatedTradeEntity = tradeEntityRepository.findById(tradeEntity.getId()).block();
        updatedTradeEntity
            .entityId(UPDATED_ENTITY_ID)
            .entityName(UPDATED_ENTITY_NAME)
            .entityDesc(UPDATED_ENTITY_DESC)
            .entityGST(UPDATED_ENTITY_GST);
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(updatedTradeEntity);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradeEntityDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
        TradeEntity testTradeEntity = tradeEntityList.get(tradeEntityList.size() - 1);
        assertThat(testTradeEntity.getEntityId()).isEqualTo(UPDATED_ENTITY_ID);
        assertThat(testTradeEntity.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
        assertThat(testTradeEntity.getEntityDesc()).isEqualTo(UPDATED_ENTITY_DESC);
        assertThat(testTradeEntity.getEntityGST()).isEqualTo(UPDATED_ENTITY_GST);
    }

    @Test
    void putNonExistingTradeEntity() throws Exception {
        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();
        tradeEntity.setId(longCount.incrementAndGet());

        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, tradeEntityDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchTradeEntity() throws Exception {
        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();
        tradeEntity.setId(longCount.incrementAndGet());

        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamTradeEntity() throws Exception {
        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();
        tradeEntity.setId(longCount.incrementAndGet());

        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateTradeEntityWithPatch() throws Exception {
        // Initialize the database
        tradeEntityRepository.save(tradeEntity).block();

        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();

        // Update the tradeEntity using partial update
        TradeEntity partialUpdatedTradeEntity = new TradeEntity();
        partialUpdatedTradeEntity.setId(tradeEntity.getId());

        partialUpdatedTradeEntity.entityId(UPDATED_ENTITY_ID).entityName(UPDATED_ENTITY_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTradeEntity.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeEntity))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
        TradeEntity testTradeEntity = tradeEntityList.get(tradeEntityList.size() - 1);
        assertThat(testTradeEntity.getEntityId()).isEqualTo(UPDATED_ENTITY_ID);
        assertThat(testTradeEntity.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
        assertThat(testTradeEntity.getEntityDesc()).isEqualTo(DEFAULT_ENTITY_DESC);
        assertThat(testTradeEntity.getEntityGST()).isEqualTo(DEFAULT_ENTITY_GST);
    }

    @Test
    void fullUpdateTradeEntityWithPatch() throws Exception {
        // Initialize the database
        tradeEntityRepository.save(tradeEntity).block();

        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();

        // Update the tradeEntity using partial update
        TradeEntity partialUpdatedTradeEntity = new TradeEntity();
        partialUpdatedTradeEntity.setId(tradeEntity.getId());

        partialUpdatedTradeEntity
            .entityId(UPDATED_ENTITY_ID)
            .entityName(UPDATED_ENTITY_NAME)
            .entityDesc(UPDATED_ENTITY_DESC)
            .entityGST(UPDATED_ENTITY_GST);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedTradeEntity.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedTradeEntity))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
        TradeEntity testTradeEntity = tradeEntityList.get(tradeEntityList.size() - 1);
        assertThat(testTradeEntity.getEntityId()).isEqualTo(UPDATED_ENTITY_ID);
        assertThat(testTradeEntity.getEntityName()).isEqualTo(UPDATED_ENTITY_NAME);
        assertThat(testTradeEntity.getEntityDesc()).isEqualTo(UPDATED_ENTITY_DESC);
        assertThat(testTradeEntity.getEntityGST()).isEqualTo(UPDATED_ENTITY_GST);
    }

    @Test
    void patchNonExistingTradeEntity() throws Exception {
        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();
        tradeEntity.setId(longCount.incrementAndGet());

        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, tradeEntityDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchTradeEntity() throws Exception {
        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();
        tradeEntity.setId(longCount.incrementAndGet());

        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamTradeEntity() throws Exception {
        int databaseSizeBeforeUpdate = tradeEntityRepository.findAll().collectList().block().size();
        tradeEntity.setId(longCount.incrementAndGet());

        // Create the TradeEntity
        TradeEntityDTO tradeEntityDTO = tradeEntityMapper.toDto(tradeEntity);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(tradeEntityDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the TradeEntity in the database
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteTradeEntity() {
        // Initialize the database
        tradeEntityRepository.save(tradeEntity).block();

        int databaseSizeBeforeDelete = tradeEntityRepository.findAll().collectList().block().size();

        // Delete the tradeEntity
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, tradeEntity.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<TradeEntity> tradeEntityList = tradeEntityRepository.findAll().collectList().block();
        assertThat(tradeEntityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
