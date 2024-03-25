package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Action;
import in.pft.apis.creditbazaar.gateway.repository.ActionRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.dto.ActionDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.ActionMapper;
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
 * Integration tests for the {@link ActionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ActionResourceIT {

    private static final String DEFAULT_ACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION_VAL = "AAAAAAAAAA";
    private static final String UPDATED_ACTION_VAL = "BBBBBBBBBB";

    private static final String DEFAULT_CP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CP_CODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/actions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ActionRepository actionRepository;

    @Autowired
    private ActionMapper actionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Action action;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Action createEntity(EntityManager em) {
        Action action = new Action()
            .actionId(DEFAULT_ACTION_ID)
            .actionCode(DEFAULT_ACTION_CODE)
            .actionDescription(DEFAULT_ACTION_DESCRIPTION)
            .actionVal(DEFAULT_ACTION_VAL)
            .cpCode(DEFAULT_CP_CODE);
        return action;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Action createUpdatedEntity(EntityManager em) {
        Action action = new Action()
            .actionId(UPDATED_ACTION_ID)
            .actionCode(UPDATED_ACTION_CODE)
            .actionDescription(UPDATED_ACTION_DESCRIPTION)
            .actionVal(UPDATED_ACTION_VAL)
            .cpCode(UPDATED_CP_CODE);
        return action;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Action.class).block();
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
        action = createEntity(em);
    }

    @Test
    void createAction() throws Exception {
        int databaseSizeBeforeCreate = actionRepository.findAll().collectList().block().size();
        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeCreate + 1);
        Action testAction = actionList.get(actionList.size() - 1);
        assertThat(testAction.getActionId()).isEqualTo(DEFAULT_ACTION_ID);
        assertThat(testAction.getActionCode()).isEqualTo(DEFAULT_ACTION_CODE);
        assertThat(testAction.getActionDescription()).isEqualTo(DEFAULT_ACTION_DESCRIPTION);
        assertThat(testAction.getActionVal()).isEqualTo(DEFAULT_ACTION_VAL);
        assertThat(testAction.getCpCode()).isEqualTo(DEFAULT_CP_CODE);
    }

    @Test
    void createActionWithExistingId() throws Exception {
        // Create the Action with an existing ID
        action.setId(1L);
        ActionDTO actionDTO = actionMapper.toDto(action);

        int databaseSizeBeforeCreate = actionRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllActions() {
        // Initialize the database
        actionRepository.save(action).block();

        // Get all the actionList
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
            .value(hasItem(action.getId().intValue()))
            .jsonPath("$.[*].actionId")
            .value(hasItem(DEFAULT_ACTION_ID))
            .jsonPath("$.[*].actionCode")
            .value(hasItem(DEFAULT_ACTION_CODE))
            .jsonPath("$.[*].actionDescription")
            .value(hasItem(DEFAULT_ACTION_DESCRIPTION))
            .jsonPath("$.[*].actionVal")
            .value(hasItem(DEFAULT_ACTION_VAL))
            .jsonPath("$.[*].cpCode")
            .value(hasItem(DEFAULT_CP_CODE));
    }

    @Test
    void getAction() {
        // Initialize the database
        actionRepository.save(action).block();

        // Get the action
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, action.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(action.getId().intValue()))
            .jsonPath("$.actionId")
            .value(is(DEFAULT_ACTION_ID))
            .jsonPath("$.actionCode")
            .value(is(DEFAULT_ACTION_CODE))
            .jsonPath("$.actionDescription")
            .value(is(DEFAULT_ACTION_DESCRIPTION))
            .jsonPath("$.actionVal")
            .value(is(DEFAULT_ACTION_VAL))
            .jsonPath("$.cpCode")
            .value(is(DEFAULT_CP_CODE));
    }

    @Test
    void getNonExistingAction() {
        // Get the action
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAction() throws Exception {
        // Initialize the database
        actionRepository.save(action).block();

        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();

        // Update the action
        Action updatedAction = actionRepository.findById(action.getId()).block();
        updatedAction
            .actionId(UPDATED_ACTION_ID)
            .actionCode(UPDATED_ACTION_CODE)
            .actionDescription(UPDATED_ACTION_DESCRIPTION)
            .actionVal(UPDATED_ACTION_VAL)
            .cpCode(UPDATED_CP_CODE);
        ActionDTO actionDTO = actionMapper.toDto(updatedAction);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, actionDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
        Action testAction = actionList.get(actionList.size() - 1);
        assertThat(testAction.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testAction.getActionCode()).isEqualTo(UPDATED_ACTION_CODE);
        assertThat(testAction.getActionDescription()).isEqualTo(UPDATED_ACTION_DESCRIPTION);
        assertThat(testAction.getActionVal()).isEqualTo(UPDATED_ACTION_VAL);
        assertThat(testAction.getCpCode()).isEqualTo(UPDATED_CP_CODE);
    }

    @Test
    void putNonExistingAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();
        action.setId(longCount.incrementAndGet());

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, actionDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();
        action.setId(longCount.incrementAndGet());

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();
        action.setId(longCount.incrementAndGet());

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateActionWithPatch() throws Exception {
        // Initialize the database
        actionRepository.save(action).block();

        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();

        // Update the action using partial update
        Action partialUpdatedAction = new Action();
        partialUpdatedAction.setId(action.getId());

        partialUpdatedAction
            .actionId(UPDATED_ACTION_ID)
            .actionCode(UPDATED_ACTION_CODE)
            .actionDescription(UPDATED_ACTION_DESCRIPTION)
            .actionVal(UPDATED_ACTION_VAL);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
        Action testAction = actionList.get(actionList.size() - 1);
        assertThat(testAction.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testAction.getActionCode()).isEqualTo(UPDATED_ACTION_CODE);
        assertThat(testAction.getActionDescription()).isEqualTo(UPDATED_ACTION_DESCRIPTION);
        assertThat(testAction.getActionVal()).isEqualTo(UPDATED_ACTION_VAL);
        assertThat(testAction.getCpCode()).isEqualTo(DEFAULT_CP_CODE);
    }

    @Test
    void fullUpdateActionWithPatch() throws Exception {
        // Initialize the database
        actionRepository.save(action).block();

        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();

        // Update the action using partial update
        Action partialUpdatedAction = new Action();
        partialUpdatedAction.setId(action.getId());

        partialUpdatedAction
            .actionId(UPDATED_ACTION_ID)
            .actionCode(UPDATED_ACTION_CODE)
            .actionDescription(UPDATED_ACTION_DESCRIPTION)
            .actionVal(UPDATED_ACTION_VAL)
            .cpCode(UPDATED_CP_CODE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAction.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAction))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
        Action testAction = actionList.get(actionList.size() - 1);
        assertThat(testAction.getActionId()).isEqualTo(UPDATED_ACTION_ID);
        assertThat(testAction.getActionCode()).isEqualTo(UPDATED_ACTION_CODE);
        assertThat(testAction.getActionDescription()).isEqualTo(UPDATED_ACTION_DESCRIPTION);
        assertThat(testAction.getActionVal()).isEqualTo(UPDATED_ACTION_VAL);
        assertThat(testAction.getCpCode()).isEqualTo(UPDATED_CP_CODE);
    }

    @Test
    void patchNonExistingAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();
        action.setId(longCount.incrementAndGet());

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, actionDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();
        action.setId(longCount.incrementAndGet());

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAction() throws Exception {
        int databaseSizeBeforeUpdate = actionRepository.findAll().collectList().block().size();
        action.setId(longCount.incrementAndGet());

        // Create the Action
        ActionDTO actionDTO = actionMapper.toDto(action);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(actionDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Action in the database
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAction() {
        // Initialize the database
        actionRepository.save(action).block();

        int databaseSizeBeforeDelete = actionRepository.findAll().collectList().block().size();

        // Delete the action
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, action.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Action> actionList = actionRepository.findAll().collectList().block();
        assertThat(actionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
