package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Context;
import in.pft.apis.creditbazaar.gateway.repository.ContextRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.dto.ContextDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.ContextMapper;
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
 * Integration tests for the {@link ContextResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ContextResourceIT {

    private static final String DEFAULT_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_DATE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION = "BBBBBBBBBB";

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MSGPAYLOAD = "AAAAAAAAAA";
    private static final String UPDATED_MSGPAYLOAD = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contexts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ContextRepository contextRepository;

    @Autowired
    private ContextMapper contextMapper;

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
            .action(DEFAULT_ACTION)
            .userId(DEFAULT_USER_ID)
            .tenantId(DEFAULT_TENANT_ID)
            .cpCode(DEFAULT_CP_CODE)
            .msgpayload(DEFAULT_MSGPAYLOAD);
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
            .action(UPDATED_ACTION)
            .userId(UPDATED_USER_ID)
            .tenantId(UPDATED_TENANT_ID)
            .cpCode(UPDATED_CP_CODE)
            .msgpayload(UPDATED_MSGPAYLOAD);
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
        assertThat(testContext.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testContext.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testContext.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(DEFAULT_CP_CODE);
        assertThat(testContext.getMsgpayload()).isEqualTo(DEFAULT_MSGPAYLOAD);
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
    void checkActionIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setAction(null);

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
    void checkUserIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setUserId(null);

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
    void checkTenantIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setTenantId(null);

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
    void checkMsgpayloadIsRequired() throws Exception {
        int databaseSizeBeforeTest = contextRepository.findAll().collectList().block().size();
        // set the field null
        context.setMsgpayload(null);

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
            .value(hasItem(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.[*].transactionDate")
            .value(hasItem(DEFAULT_TRANSACTION_DATE))
            .jsonPath("$.[*].action")
            .value(hasItem(DEFAULT_ACTION))
            .jsonPath("$.[*].userId")
            .value(hasItem(DEFAULT_USER_ID))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID))
            .jsonPath("$.[*].cpCode")
            .value(hasItem(DEFAULT_CP_CODE))
            .jsonPath("$.[*].msgpayload")
            .value(hasItem(DEFAULT_MSGPAYLOAD));
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
            .value(is(DEFAULT_TRANSACTION_ID))
            .jsonPath("$.transactionDate")
            .value(is(DEFAULT_TRANSACTION_DATE))
            .jsonPath("$.action")
            .value(is(DEFAULT_ACTION))
            .jsonPath("$.userId")
            .value(is(DEFAULT_USER_ID))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID))
            .jsonPath("$.cpCode")
            .value(is(DEFAULT_CP_CODE))
            .jsonPath("$.msgpayload")
            .value(is(DEFAULT_MSGPAYLOAD));
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
            .action(UPDATED_ACTION)
            .userId(UPDATED_USER_ID)
            .tenantId(UPDATED_TENANT_ID)
            .cpCode(UPDATED_CP_CODE)
            .msgpayload(UPDATED_MSGPAYLOAD);
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
        assertThat(testContext.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testContext.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testContext.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(UPDATED_CP_CODE);
        assertThat(testContext.getMsgpayload()).isEqualTo(UPDATED_MSGPAYLOAD);
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

        partialUpdatedContext.userId(UPDATED_USER_ID).cpCode(UPDATED_CP_CODE);

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
        assertThat(testContext.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testContext.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testContext.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(UPDATED_CP_CODE);
        assertThat(testContext.getMsgpayload()).isEqualTo(DEFAULT_MSGPAYLOAD);
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
            .action(UPDATED_ACTION)
            .userId(UPDATED_USER_ID)
            .tenantId(UPDATED_TENANT_ID)
            .cpCode(UPDATED_CP_CODE)
            .msgpayload(UPDATED_MSGPAYLOAD);

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
        assertThat(testContext.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testContext.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testContext.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
        assertThat(testContext.getCpCode()).isEqualTo(UPDATED_CP_CODE);
        assertThat(testContext.getMsgpayload()).isEqualTo(UPDATED_MSGPAYLOAD);
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
