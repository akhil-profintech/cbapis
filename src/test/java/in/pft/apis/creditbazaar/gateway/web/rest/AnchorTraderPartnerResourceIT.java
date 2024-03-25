package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner;
import in.pft.apis.creditbazaar.gateway.repository.AnchorTraderPartnerRepository;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.service.AnchorTraderPartnerService;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderPartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.AnchorTraderPartnerMapper;
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
 * Integration tests for the {@link AnchorTraderPartnerResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AnchorTraderPartnerResourceIT {

    private static final Long DEFAULT_ATPARTNER_ID = 1L;
    private static final Long UPDATED_ATPARTNER_ID = 2L;

    private static final String DEFAULT_ATPARTNER_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_ATPARTNER_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAN = "AAAAAAAAAA";
    private static final String UPDATED_PAN = "BBBBBBBBBB";

    private static final String DEFAULT_PAN_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_PAN_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR_OTP = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_OTP = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_AADHAR_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_AADHAR_ADDRESS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/anchor-trader-partners";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AnchorTraderPartnerRepository anchorTraderPartnerRepository;

    @Mock
    private AnchorTraderPartnerRepository anchorTraderPartnerRepositoryMock;

    @Autowired
    private AnchorTraderPartnerMapper anchorTraderPartnerMapper;

    @Mock
    private AnchorTraderPartnerService anchorTraderPartnerServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AnchorTraderPartner anchorTraderPartner;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnchorTraderPartner createEntity(EntityManager em) {
        AnchorTraderPartner anchorTraderPartner = new AnchorTraderPartner()
            .atpartnerId(DEFAULT_ATPARTNER_ID)
            .atpartnerUlidId(DEFAULT_ATPARTNER_ULID_ID)
            .pan(DEFAULT_PAN)
            .panStatus(DEFAULT_PAN_STATUS)
            .name(DEFAULT_NAME)
            .aadhar(DEFAULT_AADHAR)
            .aadharOtp(DEFAULT_AADHAR_OTP)
            .aadharStatus(DEFAULT_AADHAR_STATUS)
            .aadharName(DEFAULT_AADHAR_NAME)
            .aadharAddress(DEFAULT_AADHAR_ADDRESS);
        return anchorTraderPartner;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnchorTraderPartner createUpdatedEntity(EntityManager em) {
        AnchorTraderPartner anchorTraderPartner = new AnchorTraderPartner()
            .atpartnerId(UPDATED_ATPARTNER_ID)
            .atpartnerUlidId(UPDATED_ATPARTNER_ULID_ID)
            .pan(UPDATED_PAN)
            .panStatus(UPDATED_PAN_STATUS)
            .name(UPDATED_NAME)
            .aadhar(UPDATED_AADHAR)
            .aadharOtp(UPDATED_AADHAR_OTP)
            .aadharStatus(UPDATED_AADHAR_STATUS)
            .aadharName(UPDATED_AADHAR_NAME)
            .aadharAddress(UPDATED_AADHAR_ADDRESS);
        return anchorTraderPartner;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AnchorTraderPartner.class).block();
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
        anchorTraderPartner = createEntity(em);
    }

    @Test
    void createAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeCreate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeCreate + 1);
        AnchorTraderPartner testAnchorTraderPartner = anchorTraderPartnerList.get(anchorTraderPartnerList.size() - 1);
        assertThat(testAnchorTraderPartner.getAtpartnerId()).isEqualTo(DEFAULT_ATPARTNER_ID);
        assertThat(testAnchorTraderPartner.getAtpartnerUlidId()).isEqualTo(DEFAULT_ATPARTNER_ULID_ID);
        assertThat(testAnchorTraderPartner.getPan()).isEqualTo(DEFAULT_PAN);
        assertThat(testAnchorTraderPartner.getPanStatus()).isEqualTo(DEFAULT_PAN_STATUS);
        assertThat(testAnchorTraderPartner.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAnchorTraderPartner.getAadhar()).isEqualTo(DEFAULT_AADHAR);
        assertThat(testAnchorTraderPartner.getAadharOtp()).isEqualTo(DEFAULT_AADHAR_OTP);
        assertThat(testAnchorTraderPartner.getAadharStatus()).isEqualTo(DEFAULT_AADHAR_STATUS);
        assertThat(testAnchorTraderPartner.getAadharName()).isEqualTo(DEFAULT_AADHAR_NAME);
        assertThat(testAnchorTraderPartner.getAadharAddress()).isEqualTo(DEFAULT_AADHAR_ADDRESS);
    }

    @Test
    void createAnchorTraderPartnerWithExistingId() throws Exception {
        // Create the AnchorTraderPartner with an existing ID
        anchorTraderPartner.setId(1L);
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        int databaseSizeBeforeCreate = anchorTraderPartnerRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllAnchorTraderPartners() {
        // Initialize the database
        anchorTraderPartnerRepository.save(anchorTraderPartner).block();

        // Get all the anchorTraderPartnerList
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
            .value(hasItem(anchorTraderPartner.getId().intValue()))
            .jsonPath("$.[*].atpartnerId")
            .value(hasItem(DEFAULT_ATPARTNER_ID.intValue()))
            .jsonPath("$.[*].atpartnerUlidId")
            .value(hasItem(DEFAULT_ATPARTNER_ULID_ID))
            .jsonPath("$.[*].pan")
            .value(hasItem(DEFAULT_PAN))
            .jsonPath("$.[*].panStatus")
            .value(hasItem(DEFAULT_PAN_STATUS))
            .jsonPath("$.[*].name")
            .value(hasItem(DEFAULT_NAME))
            .jsonPath("$.[*].aadhar")
            .value(hasItem(DEFAULT_AADHAR))
            .jsonPath("$.[*].aadharOtp")
            .value(hasItem(DEFAULT_AADHAR_OTP))
            .jsonPath("$.[*].aadharStatus")
            .value(hasItem(DEFAULT_AADHAR_STATUS))
            .jsonPath("$.[*].aadharName")
            .value(hasItem(DEFAULT_AADHAR_NAME))
            .jsonPath("$.[*].aadharAddress")
            .value(hasItem(DEFAULT_AADHAR_ADDRESS));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAnchorTraderPartnersWithEagerRelationshipsIsEnabled() {
        when(anchorTraderPartnerServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(anchorTraderPartnerServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllAnchorTraderPartnersWithEagerRelationshipsIsNotEnabled() {
        when(anchorTraderPartnerServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(anchorTraderPartnerRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getAnchorTraderPartner() {
        // Initialize the database
        anchorTraderPartnerRepository.save(anchorTraderPartner).block();

        // Get the anchorTraderPartner
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, anchorTraderPartner.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(anchorTraderPartner.getId().intValue()))
            .jsonPath("$.atpartnerId")
            .value(is(DEFAULT_ATPARTNER_ID.intValue()))
            .jsonPath("$.atpartnerUlidId")
            .value(is(DEFAULT_ATPARTNER_ULID_ID))
            .jsonPath("$.pan")
            .value(is(DEFAULT_PAN))
            .jsonPath("$.panStatus")
            .value(is(DEFAULT_PAN_STATUS))
            .jsonPath("$.name")
            .value(is(DEFAULT_NAME))
            .jsonPath("$.aadhar")
            .value(is(DEFAULT_AADHAR))
            .jsonPath("$.aadharOtp")
            .value(is(DEFAULT_AADHAR_OTP))
            .jsonPath("$.aadharStatus")
            .value(is(DEFAULT_AADHAR_STATUS))
            .jsonPath("$.aadharName")
            .value(is(DEFAULT_AADHAR_NAME))
            .jsonPath("$.aadharAddress")
            .value(is(DEFAULT_AADHAR_ADDRESS));
    }

    @Test
    void getNonExistingAnchorTraderPartner() {
        // Get the anchorTraderPartner
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAnchorTraderPartner() throws Exception {
        // Initialize the database
        anchorTraderPartnerRepository.save(anchorTraderPartner).block();

        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();

        // Update the anchorTraderPartner
        AnchorTraderPartner updatedAnchorTraderPartner = anchorTraderPartnerRepository.findById(anchorTraderPartner.getId()).block();
        updatedAnchorTraderPartner
            .atpartnerId(UPDATED_ATPARTNER_ID)
            .atpartnerUlidId(UPDATED_ATPARTNER_ULID_ID)
            .pan(UPDATED_PAN)
            .panStatus(UPDATED_PAN_STATUS)
            .name(UPDATED_NAME)
            .aadhar(UPDATED_AADHAR)
            .aadharOtp(UPDATED_AADHAR_OTP)
            .aadharStatus(UPDATED_AADHAR_STATUS)
            .aadharName(UPDATED_AADHAR_NAME)
            .aadharAddress(UPDATED_AADHAR_ADDRESS);
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(updatedAnchorTraderPartner);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, anchorTraderPartnerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
        AnchorTraderPartner testAnchorTraderPartner = anchorTraderPartnerList.get(anchorTraderPartnerList.size() - 1);
        assertThat(testAnchorTraderPartner.getAtpartnerId()).isEqualTo(UPDATED_ATPARTNER_ID);
        assertThat(testAnchorTraderPartner.getAtpartnerUlidId()).isEqualTo(UPDATED_ATPARTNER_ULID_ID);
        assertThat(testAnchorTraderPartner.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testAnchorTraderPartner.getPanStatus()).isEqualTo(UPDATED_PAN_STATUS);
        assertThat(testAnchorTraderPartner.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAnchorTraderPartner.getAadhar()).isEqualTo(UPDATED_AADHAR);
        assertThat(testAnchorTraderPartner.getAadharOtp()).isEqualTo(UPDATED_AADHAR_OTP);
        assertThat(testAnchorTraderPartner.getAadharStatus()).isEqualTo(UPDATED_AADHAR_STATUS);
        assertThat(testAnchorTraderPartner.getAadharName()).isEqualTo(UPDATED_AADHAR_NAME);
        assertThat(testAnchorTraderPartner.getAadharAddress()).isEqualTo(UPDATED_AADHAR_ADDRESS);
    }

    @Test
    void putNonExistingAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        anchorTraderPartner.setId(longCount.incrementAndGet());

        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, anchorTraderPartnerDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        anchorTraderPartner.setId(longCount.incrementAndGet());

        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        anchorTraderPartner.setId(longCount.incrementAndGet());

        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateAnchorTraderPartnerWithPatch() throws Exception {
        // Initialize the database
        anchorTraderPartnerRepository.save(anchorTraderPartner).block();

        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();

        // Update the anchorTraderPartner using partial update
        AnchorTraderPartner partialUpdatedAnchorTraderPartner = new AnchorTraderPartner();
        partialUpdatedAnchorTraderPartner.setId(anchorTraderPartner.getId());

        partialUpdatedAnchorTraderPartner
            .atpartnerId(UPDATED_ATPARTNER_ID)
            .pan(UPDATED_PAN)
            .aadharOtp(UPDATED_AADHAR_OTP)
            .aadharStatus(UPDATED_AADHAR_STATUS)
            .aadharAddress(UPDATED_AADHAR_ADDRESS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAnchorTraderPartner.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAnchorTraderPartner))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
        AnchorTraderPartner testAnchorTraderPartner = anchorTraderPartnerList.get(anchorTraderPartnerList.size() - 1);
        assertThat(testAnchorTraderPartner.getAtpartnerId()).isEqualTo(UPDATED_ATPARTNER_ID);
        assertThat(testAnchorTraderPartner.getAtpartnerUlidId()).isEqualTo(DEFAULT_ATPARTNER_ULID_ID);
        assertThat(testAnchorTraderPartner.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testAnchorTraderPartner.getPanStatus()).isEqualTo(DEFAULT_PAN_STATUS);
        assertThat(testAnchorTraderPartner.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAnchorTraderPartner.getAadhar()).isEqualTo(DEFAULT_AADHAR);
        assertThat(testAnchorTraderPartner.getAadharOtp()).isEqualTo(UPDATED_AADHAR_OTP);
        assertThat(testAnchorTraderPartner.getAadharStatus()).isEqualTo(UPDATED_AADHAR_STATUS);
        assertThat(testAnchorTraderPartner.getAadharName()).isEqualTo(DEFAULT_AADHAR_NAME);
        assertThat(testAnchorTraderPartner.getAadharAddress()).isEqualTo(UPDATED_AADHAR_ADDRESS);
    }

    @Test
    void fullUpdateAnchorTraderPartnerWithPatch() throws Exception {
        // Initialize the database
        anchorTraderPartnerRepository.save(anchorTraderPartner).block();

        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();

        // Update the anchorTraderPartner using partial update
        AnchorTraderPartner partialUpdatedAnchorTraderPartner = new AnchorTraderPartner();
        partialUpdatedAnchorTraderPartner.setId(anchorTraderPartner.getId());

        partialUpdatedAnchorTraderPartner
            .atpartnerId(UPDATED_ATPARTNER_ID)
            .atpartnerUlidId(UPDATED_ATPARTNER_ULID_ID)
            .pan(UPDATED_PAN)
            .panStatus(UPDATED_PAN_STATUS)
            .name(UPDATED_NAME)
            .aadhar(UPDATED_AADHAR)
            .aadharOtp(UPDATED_AADHAR_OTP)
            .aadharStatus(UPDATED_AADHAR_STATUS)
            .aadharName(UPDATED_AADHAR_NAME)
            .aadharAddress(UPDATED_AADHAR_ADDRESS);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAnchorTraderPartner.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAnchorTraderPartner))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
        AnchorTraderPartner testAnchorTraderPartner = anchorTraderPartnerList.get(anchorTraderPartnerList.size() - 1);
        assertThat(testAnchorTraderPartner.getAtpartnerId()).isEqualTo(UPDATED_ATPARTNER_ID);
        assertThat(testAnchorTraderPartner.getAtpartnerUlidId()).isEqualTo(UPDATED_ATPARTNER_ULID_ID);
        assertThat(testAnchorTraderPartner.getPan()).isEqualTo(UPDATED_PAN);
        assertThat(testAnchorTraderPartner.getPanStatus()).isEqualTo(UPDATED_PAN_STATUS);
        assertThat(testAnchorTraderPartner.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAnchorTraderPartner.getAadhar()).isEqualTo(UPDATED_AADHAR);
        assertThat(testAnchorTraderPartner.getAadharOtp()).isEqualTo(UPDATED_AADHAR_OTP);
        assertThat(testAnchorTraderPartner.getAadharStatus()).isEqualTo(UPDATED_AADHAR_STATUS);
        assertThat(testAnchorTraderPartner.getAadharName()).isEqualTo(UPDATED_AADHAR_NAME);
        assertThat(testAnchorTraderPartner.getAadharAddress()).isEqualTo(UPDATED_AADHAR_ADDRESS);
    }

    @Test
    void patchNonExistingAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        anchorTraderPartner.setId(longCount.incrementAndGet());

        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, anchorTraderPartnerDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        anchorTraderPartner.setId(longCount.incrementAndGet());

        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamAnchorTraderPartner() throws Exception {
        int databaseSizeBeforeUpdate = anchorTraderPartnerRepository.findAll().collectList().block().size();
        anchorTraderPartner.setId(longCount.incrementAndGet());

        // Create the AnchorTraderPartner
        AnchorTraderPartnerDTO anchorTraderPartnerDTO = anchorTraderPartnerMapper.toDto(anchorTraderPartner);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(anchorTraderPartnerDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AnchorTraderPartner in the database
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteAnchorTraderPartner() {
        // Initialize the database
        anchorTraderPartnerRepository.save(anchorTraderPartner).block();

        int databaseSizeBeforeDelete = anchorTraderPartnerRepository.findAll().collectList().block().size();

        // Delete the anchorTraderPartner
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, anchorTraderPartner.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AnchorTraderPartner> anchorTraderPartnerList = anchorTraderPartnerRepository.findAll().collectList().block();
        assertThat(anchorTraderPartnerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
