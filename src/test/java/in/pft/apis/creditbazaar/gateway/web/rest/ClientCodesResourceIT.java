package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.ClientCodes;
import in.pft.apis.creditbazaar.gateway.repository.ClientCodesRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.dto.ClientCodesDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.ClientCodesMapper;
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
 * Integration tests for the {@link ClientCodesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class ClientCodesResourceIT {

    private static final Long DEFAULT_CLIENT_CODE = 1L;
    private static final Long UPDATED_CLIENT_CODE = 2L;

    private static final String DEFAULT_CLIENT_CHARS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_CHARS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/client-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ClientCodesRepository clientCodesRepository;

    @Autowired
    private ClientCodesMapper clientCodesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private ClientCodes clientCodes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientCodes createEntity(EntityManager em) {
        ClientCodes clientCodes = new ClientCodes()
            .clientCode(DEFAULT_CLIENT_CODE)
            .clientCharsCode(DEFAULT_CLIENT_CHARS_CODE)
            .clientName(DEFAULT_CLIENT_NAME);
        return clientCodes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ClientCodes createUpdatedEntity(EntityManager em) {
        ClientCodes clientCodes = new ClientCodes()
            .clientCode(UPDATED_CLIENT_CODE)
            .clientCharsCode(UPDATED_CLIENT_CHARS_CODE)
            .clientName(UPDATED_CLIENT_NAME);
        return clientCodes;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(ClientCodes.class).block();
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
        clientCodes = createEntity(em);
    }

    @Test
    void createClientCodes() throws Exception {
        int databaseSizeBeforeCreate = clientCodesRepository.findAll().collectList().block().size();
        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeCreate + 1);
        ClientCodes testClientCodes = clientCodesList.get(clientCodesList.size() - 1);
        assertThat(testClientCodes.getClientCode()).isEqualTo(DEFAULT_CLIENT_CODE);
        assertThat(testClientCodes.getClientCharsCode()).isEqualTo(DEFAULT_CLIENT_CHARS_CODE);
        assertThat(testClientCodes.getClientName()).isEqualTo(DEFAULT_CLIENT_NAME);
    }

    @Test
    void createClientCodesWithExistingId() throws Exception {
        // Create the ClientCodes with an existing ID
        clientCodes.setId(1L);
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        int databaseSizeBeforeCreate = clientCodesRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllClientCodes() {
        // Initialize the database
        clientCodesRepository.save(clientCodes).block();

        // Get all the clientCodesList
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
            .value(hasItem(clientCodes.getId().intValue()))
            .jsonPath("$.[*].clientCode")
            .value(hasItem(DEFAULT_CLIENT_CODE.intValue()))
            .jsonPath("$.[*].clientCharsCode")
            .value(hasItem(DEFAULT_CLIENT_CHARS_CODE))
            .jsonPath("$.[*].clientName")
            .value(hasItem(DEFAULT_CLIENT_NAME));
    }

    @Test
    void getClientCodes() {
        // Initialize the database
        clientCodesRepository.save(clientCodes).block();

        // Get the clientCodes
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, clientCodes.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(clientCodes.getId().intValue()))
            .jsonPath("$.clientCode")
            .value(is(DEFAULT_CLIENT_CODE.intValue()))
            .jsonPath("$.clientCharsCode")
            .value(is(DEFAULT_CLIENT_CHARS_CODE))
            .jsonPath("$.clientName")
            .value(is(DEFAULT_CLIENT_NAME));
    }

    @Test
    void getNonExistingClientCodes() {
        // Get the clientCodes
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingClientCodes() throws Exception {
        // Initialize the database
        clientCodesRepository.save(clientCodes).block();

        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();

        // Update the clientCodes
        ClientCodes updatedClientCodes = clientCodesRepository.findById(clientCodes.getId()).block();
        updatedClientCodes.clientCode(UPDATED_CLIENT_CODE).clientCharsCode(UPDATED_CLIENT_CHARS_CODE).clientName(UPDATED_CLIENT_NAME);
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(updatedClientCodes);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, clientCodesDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
        ClientCodes testClientCodes = clientCodesList.get(clientCodesList.size() - 1);
        assertThat(testClientCodes.getClientCode()).isEqualTo(UPDATED_CLIENT_CODE);
        assertThat(testClientCodes.getClientCharsCode()).isEqualTo(UPDATED_CLIENT_CHARS_CODE);
        assertThat(testClientCodes.getClientName()).isEqualTo(UPDATED_CLIENT_NAME);
    }

    @Test
    void putNonExistingClientCodes() throws Exception {
        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();
        clientCodes.setId(longCount.incrementAndGet());

        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, clientCodesDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchClientCodes() throws Exception {
        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();
        clientCodes.setId(longCount.incrementAndGet());

        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamClientCodes() throws Exception {
        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();
        clientCodes.setId(longCount.incrementAndGet());

        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateClientCodesWithPatch() throws Exception {
        // Initialize the database
        clientCodesRepository.save(clientCodes).block();

        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();

        // Update the clientCodes using partial update
        ClientCodes partialUpdatedClientCodes = new ClientCodes();
        partialUpdatedClientCodes.setId(clientCodes.getId());

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClientCodes.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClientCodes))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
        ClientCodes testClientCodes = clientCodesList.get(clientCodesList.size() - 1);
        assertThat(testClientCodes.getClientCode()).isEqualTo(DEFAULT_CLIENT_CODE);
        assertThat(testClientCodes.getClientCharsCode()).isEqualTo(DEFAULT_CLIENT_CHARS_CODE);
        assertThat(testClientCodes.getClientName()).isEqualTo(DEFAULT_CLIENT_NAME);
    }

    @Test
    void fullUpdateClientCodesWithPatch() throws Exception {
        // Initialize the database
        clientCodesRepository.save(clientCodes).block();

        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();

        // Update the clientCodes using partial update
        ClientCodes partialUpdatedClientCodes = new ClientCodes();
        partialUpdatedClientCodes.setId(clientCodes.getId());

        partialUpdatedClientCodes
            .clientCode(UPDATED_CLIENT_CODE)
            .clientCharsCode(UPDATED_CLIENT_CHARS_CODE)
            .clientName(UPDATED_CLIENT_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedClientCodes.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedClientCodes))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
        ClientCodes testClientCodes = clientCodesList.get(clientCodesList.size() - 1);
        assertThat(testClientCodes.getClientCode()).isEqualTo(UPDATED_CLIENT_CODE);
        assertThat(testClientCodes.getClientCharsCode()).isEqualTo(UPDATED_CLIENT_CHARS_CODE);
        assertThat(testClientCodes.getClientName()).isEqualTo(UPDATED_CLIENT_NAME);
    }

    @Test
    void patchNonExistingClientCodes() throws Exception {
        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();
        clientCodes.setId(longCount.incrementAndGet());

        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, clientCodesDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchClientCodes() throws Exception {
        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();
        clientCodes.setId(longCount.incrementAndGet());

        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamClientCodes() throws Exception {
        int databaseSizeBeforeUpdate = clientCodesRepository.findAll().collectList().block().size();
        clientCodes.setId(longCount.incrementAndGet());

        // Create the ClientCodes
        ClientCodesDTO clientCodesDTO = clientCodesMapper.toDto(clientCodes);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(clientCodesDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the ClientCodes in the database
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteClientCodes() {
        // Initialize the database
        clientCodesRepository.save(clientCodes).block();

        int databaseSizeBeforeDelete = clientCodesRepository.findAll().collectList().block().size();

        // Delete the clientCodes
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, clientCodes.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<ClientCodes> clientCodesList = clientCodesRepository.findAll().collectList().block();
        assertThat(clientCodesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
