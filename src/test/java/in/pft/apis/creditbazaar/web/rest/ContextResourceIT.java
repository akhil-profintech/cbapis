package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.Context;
import in.pft.apis.creditbazaar.repository.ContextRepository;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.service.ContextService;
import in.pft.apis.creditbazaar.service.dto.ContextDTO;
import in.pft.apis.creditbazaar.service.mapper.ContextMapper;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link ContextResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ContextResourceIT {

    private static final Long DEFAULT_TRANSACTION_ID = 1L;
    private static final Long UPDATED_TRANSACTION_ID = 2L;

    private static final Instant DEFAULT_TRANSACTION_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TRANSACTION_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CLIENT_ID = 1L;
    private static final Long UPDATED_CLIENT_ID = 2L;

    private static final String DEFAULT_CP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CP_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contexts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContextRepository contextRepository;

    @Mock
    private ContextRepository contextRepositoryMock;

    @Autowired
    private ContextMapper contextMapper;

    @Mock
    private ContextService contextServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Context context;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Context createEntity(EntityManager em) {
        Context context = new Context()
            .transactionId(DEFAULT_TRANSACTION_ID)
            .transactionDate(DEFAULT_TRANSACTION_DATE)
            .clientId(DEFAULT_CLIENT_ID)
            .cpCode(DEFAULT_CP_CODE);
        return context;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Context createUpdatedEntity(EntityManager em) {
        Context context = new Context()
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .clientId(UPDATED_CLIENT_ID)
            .cpCode(UPDATED_CP_CODE);
        return context;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Context.class).block();
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
        context = createEntity(em);
    }

    @Test
    void createContext() throws Exception {
        int databaseSizeBeforeCreate = contextRepository.findAll().collectList().block().size();
        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeCreate + 1);
        Context testContext = contextList.get(contextList.size() - 1);
        assertThat(testContext.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testContext.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testContext.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(DEFAULT_CP_CODE);
    }

    @Test
    void createContextWithExistingId() throws Exception {
        // Create the Context with an existing ID
        context.setId(1L);
        ContextDTO contextDTO = contextMapper.toDto(context);

        int databaseSizeBeforeCreate = contextRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkTransactionIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setTransactionId(null);

        // Create the Context, which fails.
        ContextDTO contextDTO = contextMapper.toDto(context);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkTransactionDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setTransactionDate(null);

        // Create the Context, which fails.
        ContextDTO contextDTO = contextMapper.toDto(context);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkClientIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setClientId(null);

        // Create the Context, which fails.
        ContextDTO contextDTO = contextMapper.toDto(context);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkCpCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setCpCode(null);

        // Create the Context, which fails.
        ContextDTO contextDTO = contextMapper.toDto(context);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllContexts() {
        // Initialize the database
        contextRepository.save(context).block();

        // Get all the contextList
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
            .value(hasItem(context.getId().intValue()))
            .jsonPath("$.[*].transactionId")
            .value(hasItem(DEFAULT_TRANSACTION_ID.intValue()))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(DEFAULT_TRANSACTION_DATE.toString()))
            .jsonPath("$.[*].clientId")
            .value(hasItem(DEFAULT_CLIENT_ID.intValue()))
            .jsonPath("$.[*].cpCode")
            .value(hasItem(DEFAULT_CP_CODE));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllContextsWithEagerRelationshipsIsEnabled() {
        when(contextServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(contextServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllContextsWithEagerRelationshipsIsNotEnabled() {
        when(contextServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(contextRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getContext() {
        // Initialize the database
        contextRepository.save(context).block();

        // Get the context
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, context.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(context.getId().intValue()))
            .jsonPath("$.transactionId")
            .value(is(DEFAULT_TRANSACTION_ID.intValue()))
            .jsonPath("$.transactionDate")
            .value(is(DEFAULT_TRANSACTION_DATE.toString()))
            .jsonPath("$.clientId")
            .value(is(DEFAULT_CLIENT_ID.intValue()))
            .jsonPath("$.cpCode")
            .value(is(DEFAULT_CP_CODE));
    }

    @Test
    void getNonExistingContext() {
        // Get the context
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingContext() throws Exception {
        // Initialize the database
        contextRepository.save(context).block();

        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();

        // Update the context
        Context updatedContext = contextRepository.findById(context.getId()).block();
        updatedContext
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .clientId(UPDATED_CLIENT_ID)
            .cpCode(UPDATED_CP_CODE);
        ContextDTO contextDTO = contextMapper.toDto(updatedContext);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, contextDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
        Context testContext = contextList.get(contextList.size() - 1);
        assertThat(testContext.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testContext.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testContext.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(UPDATED_CP_CODE);
    }

    @Test
    void putNonExistingContext() throws Exception {
        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();
        context.setId(longCount.incrementAndGet());

        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, contextDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchContext() throws Exception {
        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();
        context.setId(longCount.incrementAndGet());

        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamContext() throws Exception {
        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();
        context.setId(longCount.incrementAndGet());

        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateContextWithPatch() throws Exception {
        // Initialize the database
        contextRepository.save(context).block();

        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();

        // Update the context using partial update
        Context partialUpdatedContext = new Context();
        partialUpdatedContext.setId(context.getId());

        partialUpdatedContext.cpCode(UPDATED_CP_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedContext.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedContext))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
        Context testContext = contextList.get(contextList.size() - 1);
        assertThat(testContext.getTransactionId()).isEqualTo(DEFAULT_TRANSACTION_ID);
        assertThat(testContext.getTransactionDate()).isEqualTo(DEFAULT_TRANSACTION_DATE);
        assertThat(testContext.getClientId()).isEqualTo(DEFAULT_CLIENT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(UPDATED_CP_CODE);
    }

    @Test
    void fullUpdateContextWithPatch() throws Exception {
        // Initialize the database
        contextRepository.save(context).block();

        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();

        // Update the context using partial update
        Context partialUpdatedContext = new Context();
        partialUpdatedContext.setId(context.getId());

        partialUpdatedContext
            .transactionId(UPDATED_TRANSACTION_ID)
            .transactionDate(UPDATED_TRANSACTION_DATE)
            .clientId(UPDATED_CLIENT_ID)
            .cpCode(UPDATED_CP_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedContext.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedContext))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
        Context testContext = contextList.get(contextList.size() - 1);
        assertThat(testContext.getTransactionId()).isEqualTo(UPDATED_TRANSACTION_ID);
        assertThat(testContext.getTransactionDate()).isEqualTo(UPDATED_TRANSACTION_DATE);
        assertThat(testContext.getClientId()).isEqualTo(UPDATED_CLIENT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(UPDATED_CP_CODE);
    }

    @Test
    void patchNonExistingContext() throws Exception {
        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();
        context.setId(longCount.incrementAndGet());

        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, contextDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchContext() throws Exception {
        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();
        context.setId(longCount.incrementAndGet());

        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamContext() throws Exception {
        int databaseSizeBeforeUpdate = contextRepository.findAll().collectList().block().size();
        context.setId(longCount.incrementAndGet());

        // Create the Context
        ContextDTO contextDTO = contextMapper.toDto(context);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(contextDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Context in the database
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteContext() {
        // Initialize the database
        contextRepository.save(context).block();

        int databaseSizeBeforeDelete = contextRepository.findAll().collectList().block().size();

        // Delete the context
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, context.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Context> contextList = contextRepository.findAll().collectList().block();
        assertThat(contextList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
