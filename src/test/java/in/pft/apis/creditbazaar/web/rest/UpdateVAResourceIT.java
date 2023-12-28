package in.pft.apis.creditbazaar.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

import in.pft.apis.creditbazaar.IntegrationTest;
import in.pft.apis.creditbazaar.domain.UpdateVA;
import in.pft.apis.creditbazaar.repository.EntityManager;
import in.pft.apis.creditbazaar.repository.UpdateVARepository;
import in.pft.apis.creditbazaar.service.UpdateVAService;
import in.pft.apis.creditbazaar.service.dto.UpdateVADTO;
import in.pft.apis.creditbazaar.service.mapper.UpdateVAMapper;
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
 * Integration tests for the {@link UpdateVAResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class UpdateVAResourceIT {

    private static final Long DEFAULT_UPDATE_VIR_ACC_ID = 1L;
    private static final Long UPDATED_UPDATE_VIR_ACC_ID = 2L;

    private static final String DEFAULT_FIN_REQ_ID = "AAAAAAAAAA";
    private static final String UPDATED_FIN_REQ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_GRP_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_GRP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_MSG_ID = "AAAAAAAAAA";
    private static final String UPDATED_MSG_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CNV_ID = "AAAAAAAAAA";
    private static final String UPDATED_CNV_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXT_REF_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXT_REF_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BIZ_OBJ_ID = "AAAAAAAAAA";
    private static final String UPDATED_BIZ_OBJ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_APP_ID = "AAAAAAAAAA";
    private static final String UPDATED_APP_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TIMESTAMP = "AAAAAAAAAA";
    private static final String UPDATED_TIMESTAMP = "BBBBBBBBBB";

    private static final String DEFAULT_VA_NUM = "AAAAAAAAAA";
    private static final String UPDATED_VA_NUM = "BBBBBBBBBB";

    private static final String DEFAULT_FIN_PAR = "AAAAAAAAAA";
    private static final String UPDATED_FIN_PAR = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CLIENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_REQUEST_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_REQUEST_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_RESLT = "AAAAAAAAAA";
    private static final String UPDATED_RESLT = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS_DESC = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LASTUPDATED_DATE_TIME = "AAAAAAAAAA";
    private static final String UPDATED_LASTUPDATED_DATE_TIME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/update-vas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private UpdateVARepository updateVARepository;

    @Mock
    private UpdateVARepository updateVARepositoryMock;

    @Autowired
    private UpdateVAMapper updateVAMapper;

    @Mock
    private UpdateVAService updateVAServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private UpdateVA updateVA;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UpdateVA createEntity(EntityManager em) {
        UpdateVA updateVA = new UpdateVA()
            .updateVirAccId(DEFAULT_UPDATE_VIR_ACC_ID)
            .finReqId(DEFAULT_FIN_REQ_ID)
            .subGrpId(DEFAULT_SUB_GRP_ID)
            .msgId(DEFAULT_MSG_ID)
            .cnvId(DEFAULT_CNV_ID)
            .extRefId(DEFAULT_EXT_REF_ID)
            .bizObjId(DEFAULT_BIZ_OBJ_ID)
            .appId(DEFAULT_APP_ID)
            .timestamp(DEFAULT_TIMESTAMP)
            .vaNum(DEFAULT_VA_NUM)
            .finPar(DEFAULT_FIN_PAR)
            .clientCode(DEFAULT_CLIENT_CODE)
            .requestDateTime(DEFAULT_REQUEST_DATE_TIME)
            .reslt(DEFAULT_RESLT)
            .status(DEFAULT_STATUS)
            .statusDesc(DEFAULT_STATUS_DESC)
            .errorCode(DEFAULT_ERROR_CODE)
            .responseDateTime(DEFAULT_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(DEFAULT_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
        return updateVA;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UpdateVA createUpdatedEntity(EntityManager em) {
        UpdateVA updateVA = new UpdateVA()
            .updateVirAccId(UPDATED_UPDATE_VIR_ACC_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .msgId(UPDATED_MSG_ID)
            .cnvId(UPDATED_CNV_ID)
            .extRefId(UPDATED_EXT_REF_ID)
            .bizObjId(UPDATED_BIZ_OBJ_ID)
            .appId(UPDATED_APP_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .vaNum(UPDATED_VA_NUM)
            .finPar(UPDATED_FIN_PAR)
            .clientCode(UPDATED_CLIENT_CODE)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .reslt(UPDATED_RESLT)
            .status(UPDATED_STATUS)
            .statusDesc(UPDATED_STATUS_DESC)
            .errorCode(UPDATED_ERROR_CODE)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        return updateVA;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(UpdateVA.class).block();
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
        updateVA = createEntity(em);
    }

    @Test
    void createUpdateVA() throws Exception {
        int databaseSizeBeforeCreate = updateVARepository.findAll().collectList().block().size();
        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeCreate + 1);
        UpdateVA testUpdateVA = updateVAList.get(updateVAList.size() - 1);
        assertThat(testUpdateVA.getUpdateVirAccId()).isEqualTo(DEFAULT_UPDATE_VIR_ACC_ID);
        assertThat(testUpdateVA.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testUpdateVA.getSubGrpId()).isEqualTo(DEFAULT_SUB_GRP_ID);
        assertThat(testUpdateVA.getMsgId()).isEqualTo(DEFAULT_MSG_ID);
        assertThat(testUpdateVA.getCnvId()).isEqualTo(DEFAULT_CNV_ID);
        assertThat(testUpdateVA.getExtRefId()).isEqualTo(DEFAULT_EXT_REF_ID);
        assertThat(testUpdateVA.getBizObjId()).isEqualTo(DEFAULT_BIZ_OBJ_ID);
        assertThat(testUpdateVA.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testUpdateVA.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testUpdateVA.getVaNum()).isEqualTo(DEFAULT_VA_NUM);
        assertThat(testUpdateVA.getFinPar()).isEqualTo(DEFAULT_FIN_PAR);
        assertThat(testUpdateVA.getClientCode()).isEqualTo(DEFAULT_CLIENT_CODE);
        assertThat(testUpdateVA.getRequestDateTime()).isEqualTo(DEFAULT_REQUEST_DATE_TIME);
        assertThat(testUpdateVA.getReslt()).isEqualTo(DEFAULT_RESLT);
        assertThat(testUpdateVA.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUpdateVA.getStatusDesc()).isEqualTo(DEFAULT_STATUS_DESC);
        assertThat(testUpdateVA.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testUpdateVA.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testUpdateVA.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testUpdateVA.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    }

    @Test
    void createUpdateVAWithExistingId() throws Exception {
        // Create the UpdateVA with an existing ID
        updateVA.setId(1L);
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        int databaseSizeBeforeCreate = updateVARepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void checkUpdateVirAccIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = updateVARepository.findAll().collectList().block().size();
        // set the field null
        updateVA.setUpdateVirAccId(null);

        // Create the UpdateVA, which fails.
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkRequestDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = updateVARepository.findAll().collectList().block().size();
        // set the field null
        updateVA.setRequestDateTime(null);

        // Create the UpdateVA, which fails.
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkResponseDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = updateVARepository.findAll().collectList().block().size();
        // set the field null
        updateVA.setResponseDateTime(null);

        // Create the UpdateVA, which fails.
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastupdatedDateTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = updateVARepository.findAll().collectList().block().size();
        // set the field null
        updateVA.setLastupdatedDateTime(null);

        // Create the UpdateVA, which fails.
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void checkLastUpdatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = updateVARepository.findAll().collectList().block().size();
        // set the field null
        updateVA.setLastUpdatedBy(null);

        // Create the UpdateVA, which fails.
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    void getAllUpdateVAS() {
        // Initialize the database
        updateVARepository.save(updateVA).block();

        // Get all the updateVAList
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
            .value(hasItem(updateVA.getId().intValue()))
            .jsonPath("$.[*].updateVirAccId")
            .value(hasItem(DEFAULT_UPDATE_VIR_ACC_ID.intValue()))
            .jsonPath("$.[*].finReqId")
            .value(hasItem(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.[*].subGrpId")
            .value(hasItem(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.[*].msgId")
            .value(hasItem(DEFAULT_MSG_ID))
            .jsonPath("$.[*].cnvId")
            .value(hasItem(DEFAULT_CNV_ID))
            .jsonPath("$.[*].extRefId")
            .value(hasItem(DEFAULT_EXT_REF_ID))
            .jsonPath("$.[*].bizObjId")
            .value(hasItem(DEFAULT_BIZ_OBJ_ID))
            .jsonPath("$.[*].appId")
            .value(hasItem(DEFAULT_APP_ID))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(DEFAULT_TIMESTAMP))
            .jsonPath("$.[*].vaNum")
            .value(hasItem(DEFAULT_VA_NUM))
            .jsonPath("$.[*].finPar")
            .value(hasItem(DEFAULT_FIN_PAR))
            .jsonPath("$.[*].clientCode")
            .value(hasItem(DEFAULT_CLIENT_CODE))
            .jsonPath("$.[*].requestDateTime")
            .value(hasItem(DEFAULT_REQUEST_DATE_TIME))
            .jsonPath("$.[*].reslt")
            .value(hasItem(DEFAULT_RESLT))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].statusDesc")
            .value(hasItem(DEFAULT_STATUS_DESC))
            .jsonPath("$.[*].errorCode")
            .value(hasItem(DEFAULT_ERROR_CODE))
            .jsonPath("$.[*].responseDateTime")
            .value(hasItem(DEFAULT_RESPONSE_DATE_TIME))
            .jsonPath("$.[*].lastupdatedDateTime")
            .value(hasItem(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.[*].lastUpdatedBy")
            .value(hasItem(DEFAULT_LAST_UPDATED_BY));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUpdateVASWithEagerRelationshipsIsEnabled() {
        when(updateVAServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=true").exchange().expectStatus().isOk();

        verify(updateVAServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllUpdateVASWithEagerRelationshipsIsNotEnabled() {
        when(updateVAServiceMock.findAllWithEagerRelationships(any())).thenReturn(Flux.empty());

        webTestClient.get().uri(ENTITY_API_URL + "?eagerload=false").exchange().expectStatus().isOk();
        verify(updateVARepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    void getUpdateVA() {
        // Initialize the database
        updateVARepository.save(updateVA).block();

        // Get the updateVA
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, updateVA.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(updateVA.getId().intValue()))
            .jsonPath("$.updateVirAccId")
            .value(is(DEFAULT_UPDATE_VIR_ACC_ID.intValue()))
            .jsonPath("$.finReqId")
            .value(is(DEFAULT_FIN_REQ_ID))
            .jsonPath("$.subGrpId")
            .value(is(DEFAULT_SUB_GRP_ID))
            .jsonPath("$.msgId")
            .value(is(DEFAULT_MSG_ID))
            .jsonPath("$.cnvId")
            .value(is(DEFAULT_CNV_ID))
            .jsonPath("$.extRefId")
            .value(is(DEFAULT_EXT_REF_ID))
            .jsonPath("$.bizObjId")
            .value(is(DEFAULT_BIZ_OBJ_ID))
            .jsonPath("$.appId")
            .value(is(DEFAULT_APP_ID))
            .jsonPath("$.timestamp")
            .value(is(DEFAULT_TIMESTAMP))
            .jsonPath("$.vaNum")
            .value(is(DEFAULT_VA_NUM))
            .jsonPath("$.finPar")
            .value(is(DEFAULT_FIN_PAR))
            .jsonPath("$.clientCode")
            .value(is(DEFAULT_CLIENT_CODE))
            .jsonPath("$.requestDateTime")
            .value(is(DEFAULT_REQUEST_DATE_TIME))
            .jsonPath("$.reslt")
            .value(is(DEFAULT_RESLT))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.statusDesc")
            .value(is(DEFAULT_STATUS_DESC))
            .jsonPath("$.errorCode")
            .value(is(DEFAULT_ERROR_CODE))
            .jsonPath("$.responseDateTime")
            .value(is(DEFAULT_RESPONSE_DATE_TIME))
            .jsonPath("$.lastupdatedDateTime")
            .value(is(DEFAULT_LASTUPDATED_DATE_TIME))
            .jsonPath("$.lastUpdatedBy")
            .value(is(DEFAULT_LAST_UPDATED_BY));
    }

    @Test
    void getNonExistingUpdateVA() {
        // Get the updateVA
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingUpdateVA() throws Exception {
        // Initialize the database
        updateVARepository.save(updateVA).block();

        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();

        // Update the updateVA
        UpdateVA updatedUpdateVA = updateVARepository.findById(updateVA.getId()).block();
        updatedUpdateVA
            .updateVirAccId(UPDATED_UPDATE_VIR_ACC_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .msgId(UPDATED_MSG_ID)
            .cnvId(UPDATED_CNV_ID)
            .extRefId(UPDATED_EXT_REF_ID)
            .bizObjId(UPDATED_BIZ_OBJ_ID)
            .appId(UPDATED_APP_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .vaNum(UPDATED_VA_NUM)
            .finPar(UPDATED_FIN_PAR)
            .clientCode(UPDATED_CLIENT_CODE)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .reslt(UPDATED_RESLT)
            .status(UPDATED_STATUS)
            .statusDesc(UPDATED_STATUS_DESC)
            .errorCode(UPDATED_ERROR_CODE)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updatedUpdateVA);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updateVADTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
        UpdateVA testUpdateVA = updateVAList.get(updateVAList.size() - 1);
        assertThat(testUpdateVA.getUpdateVirAccId()).isEqualTo(UPDATED_UPDATE_VIR_ACC_ID);
        assertThat(testUpdateVA.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testUpdateVA.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testUpdateVA.getMsgId()).isEqualTo(UPDATED_MSG_ID);
        assertThat(testUpdateVA.getCnvId()).isEqualTo(UPDATED_CNV_ID);
        assertThat(testUpdateVA.getExtRefId()).isEqualTo(UPDATED_EXT_REF_ID);
        assertThat(testUpdateVA.getBizObjId()).isEqualTo(UPDATED_BIZ_OBJ_ID);
        assertThat(testUpdateVA.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testUpdateVA.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testUpdateVA.getVaNum()).isEqualTo(UPDATED_VA_NUM);
        assertThat(testUpdateVA.getFinPar()).isEqualTo(UPDATED_FIN_PAR);
        assertThat(testUpdateVA.getClientCode()).isEqualTo(UPDATED_CLIENT_CODE);
        assertThat(testUpdateVA.getRequestDateTime()).isEqualTo(UPDATED_REQUEST_DATE_TIME);
        assertThat(testUpdateVA.getReslt()).isEqualTo(UPDATED_RESLT);
        assertThat(testUpdateVA.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUpdateVA.getStatusDesc()).isEqualTo(UPDATED_STATUS_DESC);
        assertThat(testUpdateVA.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testUpdateVA.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testUpdateVA.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testUpdateVA.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void putNonExistingUpdateVA() throws Exception {
        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();
        updateVA.setId(longCount.incrementAndGet());

        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updateVADTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchUpdateVA() throws Exception {
        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();
        updateVA.setId(longCount.incrementAndGet());

        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamUpdateVA() throws Exception {
        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();
        updateVA.setId(longCount.incrementAndGet());

        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateUpdateVAWithPatch() throws Exception {
        // Initialize the database
        updateVARepository.save(updateVA).block();

        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();

        // Update the updateVA using partial update
        UpdateVA partialUpdatedUpdateVA = new UpdateVA();
        partialUpdatedUpdateVA.setId(updateVA.getId());

        partialUpdatedUpdateVA
            .updateVirAccId(UPDATED_UPDATE_VIR_ACC_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .extRefId(UPDATED_EXT_REF_ID)
            .appId(UPDATED_APP_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .finPar(UPDATED_FIN_PAR)
            .clientCode(UPDATED_CLIENT_CODE)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .reslt(UPDATED_RESLT)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedUpdateVA.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedUpdateVA))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
        UpdateVA testUpdateVA = updateVAList.get(updateVAList.size() - 1);
        assertThat(testUpdateVA.getUpdateVirAccId()).isEqualTo(UPDATED_UPDATE_VIR_ACC_ID);
        assertThat(testUpdateVA.getFinReqId()).isEqualTo(DEFAULT_FIN_REQ_ID);
        assertThat(testUpdateVA.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testUpdateVA.getMsgId()).isEqualTo(DEFAULT_MSG_ID);
        assertThat(testUpdateVA.getCnvId()).isEqualTo(DEFAULT_CNV_ID);
        assertThat(testUpdateVA.getExtRefId()).isEqualTo(UPDATED_EXT_REF_ID);
        assertThat(testUpdateVA.getBizObjId()).isEqualTo(DEFAULT_BIZ_OBJ_ID);
        assertThat(testUpdateVA.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testUpdateVA.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testUpdateVA.getVaNum()).isEqualTo(DEFAULT_VA_NUM);
        assertThat(testUpdateVA.getFinPar()).isEqualTo(UPDATED_FIN_PAR);
        assertThat(testUpdateVA.getClientCode()).isEqualTo(UPDATED_CLIENT_CODE);
        assertThat(testUpdateVA.getRequestDateTime()).isEqualTo(UPDATED_REQUEST_DATE_TIME);
        assertThat(testUpdateVA.getReslt()).isEqualTo(UPDATED_RESLT);
        assertThat(testUpdateVA.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testUpdateVA.getStatusDesc()).isEqualTo(DEFAULT_STATUS_DESC);
        assertThat(testUpdateVA.getErrorCode()).isEqualTo(DEFAULT_ERROR_CODE);
        assertThat(testUpdateVA.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testUpdateVA.getLastupdatedDateTime()).isEqualTo(DEFAULT_LASTUPDATED_DATE_TIME);
        assertThat(testUpdateVA.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void fullUpdateUpdateVAWithPatch() throws Exception {
        // Initialize the database
        updateVARepository.save(updateVA).block();

        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();

        // Update the updateVA using partial update
        UpdateVA partialUpdatedUpdateVA = new UpdateVA();
        partialUpdatedUpdateVA.setId(updateVA.getId());

        partialUpdatedUpdateVA
            .updateVirAccId(UPDATED_UPDATE_VIR_ACC_ID)
            .finReqId(UPDATED_FIN_REQ_ID)
            .subGrpId(UPDATED_SUB_GRP_ID)
            .msgId(UPDATED_MSG_ID)
            .cnvId(UPDATED_CNV_ID)
            .extRefId(UPDATED_EXT_REF_ID)
            .bizObjId(UPDATED_BIZ_OBJ_ID)
            .appId(UPDATED_APP_ID)
            .timestamp(UPDATED_TIMESTAMP)
            .vaNum(UPDATED_VA_NUM)
            .finPar(UPDATED_FIN_PAR)
            .clientCode(UPDATED_CLIENT_CODE)
            .requestDateTime(UPDATED_REQUEST_DATE_TIME)
            .reslt(UPDATED_RESLT)
            .status(UPDATED_STATUS)
            .statusDesc(UPDATED_STATUS_DESC)
            .errorCode(UPDATED_ERROR_CODE)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .lastupdatedDateTime(UPDATED_LASTUPDATED_DATE_TIME)
            .lastUpdatedBy(UPDATED_LAST_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedUpdateVA.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedUpdateVA))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
        UpdateVA testUpdateVA = updateVAList.get(updateVAList.size() - 1);
        assertThat(testUpdateVA.getUpdateVirAccId()).isEqualTo(UPDATED_UPDATE_VIR_ACC_ID);
        assertThat(testUpdateVA.getFinReqId()).isEqualTo(UPDATED_FIN_REQ_ID);
        assertThat(testUpdateVA.getSubGrpId()).isEqualTo(UPDATED_SUB_GRP_ID);
        assertThat(testUpdateVA.getMsgId()).isEqualTo(UPDATED_MSG_ID);
        assertThat(testUpdateVA.getCnvId()).isEqualTo(UPDATED_CNV_ID);
        assertThat(testUpdateVA.getExtRefId()).isEqualTo(UPDATED_EXT_REF_ID);
        assertThat(testUpdateVA.getBizObjId()).isEqualTo(UPDATED_BIZ_OBJ_ID);
        assertThat(testUpdateVA.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testUpdateVA.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testUpdateVA.getVaNum()).isEqualTo(UPDATED_VA_NUM);
        assertThat(testUpdateVA.getFinPar()).isEqualTo(UPDATED_FIN_PAR);
        assertThat(testUpdateVA.getClientCode()).isEqualTo(UPDATED_CLIENT_CODE);
        assertThat(testUpdateVA.getRequestDateTime()).isEqualTo(UPDATED_REQUEST_DATE_TIME);
        assertThat(testUpdateVA.getReslt()).isEqualTo(UPDATED_RESLT);
        assertThat(testUpdateVA.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testUpdateVA.getStatusDesc()).isEqualTo(UPDATED_STATUS_DESC);
        assertThat(testUpdateVA.getErrorCode()).isEqualTo(UPDATED_ERROR_CODE);
        assertThat(testUpdateVA.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testUpdateVA.getLastupdatedDateTime()).isEqualTo(UPDATED_LASTUPDATED_DATE_TIME);
        assertThat(testUpdateVA.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    }

    @Test
    void patchNonExistingUpdateVA() throws Exception {
        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();
        updateVA.setId(longCount.incrementAndGet());

        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, updateVADTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchUpdateVA() throws Exception {
        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();
        updateVA.setId(longCount.incrementAndGet());

        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamUpdateVA() throws Exception {
        int databaseSizeBeforeUpdate = updateVARepository.findAll().collectList().block().size();
        updateVA.setId(longCount.incrementAndGet());

        // Create the UpdateVA
        UpdateVADTO updateVADTO = updateVAMapper.toDto(updateVA);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(updateVADTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the UpdateVA in the database
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteUpdateVA() {
        // Initialize the database
        updateVARepository.save(updateVA).block();

        int databaseSizeBeforeDelete = updateVARepository.findAll().collectList().block().size();

        // Delete the updateVA
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, updateVA.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<UpdateVA> updateVAList = updateVARepository.findAll().collectList().block();
        assertThat(updateVAList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
