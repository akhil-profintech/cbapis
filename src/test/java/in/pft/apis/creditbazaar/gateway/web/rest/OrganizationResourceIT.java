package in.pft.apis.creditbazaar.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import in.pft.apis.creditbazaar.gateway.IntegrationTest;
import in.pft.apis.creditbazaar.gateway.domain.Organization;
import in.pft.apis.creditbazaar.gateway.repository.EntityManager;
import in.pft.apis.creditbazaar.gateway.repository.OrganizationRepository;
import in.pft.apis.creditbazaar.gateway.service.dto.OrganizationDTO;
import in.pft.apis.creditbazaar.gateway.service.mapper.OrganizationMapper;
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
 * Integration tests for the {@link OrganizationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class OrganizationResourceIT {

    private static final Long DEFAULT_ORG_ID = 1L;
    private static final Long UPDATED_ORG_ID = 2L;

    private static final String DEFAULT_ORG_ULID_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ULID_ID = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ORG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ORG_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_INDUSTRY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TENANT_ID = "AAAAAAAAAA";
    private static final String UPDATED_TENANT_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/organizations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private Organization organization;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createEntity(EntityManager em) {
        Organization organization = new Organization()
            .orgId(DEFAULT_ORG_ID)
            .orgUlidId(DEFAULT_ORG_ULID_ID)
            .orgName(DEFAULT_ORG_NAME)
            .orgAddress(DEFAULT_ORG_ADDRESS)
            .industryType(DEFAULT_INDUSTRY_TYPE)
            .tenantId(DEFAULT_TENANT_ID);
        return organization;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Organization createUpdatedEntity(EntityManager em) {
        Organization organization = new Organization()
            .orgId(UPDATED_ORG_ID)
            .orgUlidId(UPDATED_ORG_ULID_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgAddress(UPDATED_ORG_ADDRESS)
            .industryType(UPDATED_INDUSTRY_TYPE)
            .tenantId(UPDATED_TENANT_ID);
        return organization;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(Organization.class).block();
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
        organization = createEntity(em);
    }

    @Test
    void createOrganization() throws Exception {
        int databaseSizeBeforeCreate = organizationRepository.findAll().collectList().block().size();
        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate + 1);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testOrganization.getOrgUlidId()).isEqualTo(DEFAULT_ORG_ULID_ID);
        assertThat(testOrganization.getOrgName()).isEqualTo(DEFAULT_ORG_NAME);
        assertThat(testOrganization.getOrgAddress()).isEqualTo(DEFAULT_ORG_ADDRESS);
        assertThat(testOrganization.getIndustryType()).isEqualTo(DEFAULT_INDUSTRY_TYPE);
        assertThat(testOrganization.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    void createOrganizationWithExistingId() throws Exception {
        // Create the Organization with an existing ID
        organization.setId(1L);
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        int databaseSizeBeforeCreate = organizationRepository.findAll().collectList().block().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    void getAllOrganizations() {
        // Initialize the database
        organizationRepository.save(organization).block();

        // Get all the organizationList
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
            .value(hasItem(organization.getId().intValue()))
            .jsonPath("$.[*].orgId")
            .value(hasItem(DEFAULT_ORG_ID.intValue()))
            .jsonPath("$.[*].orgUlidId")
            .value(hasItem(DEFAULT_ORG_ULID_ID))
            .jsonPath("$.[*].orgName")
            .value(hasItem(DEFAULT_ORG_NAME))
            .jsonPath("$.[*].orgAddress")
            .value(hasItem(DEFAULT_ORG_ADDRESS))
            .jsonPath("$.[*].industryType")
            .value(hasItem(DEFAULT_INDUSTRY_TYPE))
            .jsonPath("$.[*].tenantId")
            .value(hasItem(DEFAULT_TENANT_ID));
    }

    @Test
    void getOrganization() {
        // Initialize the database
        organizationRepository.save(organization).block();

        // Get the organization
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, organization.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(organization.getId().intValue()))
            .jsonPath("$.orgId")
            .value(is(DEFAULT_ORG_ID.intValue()))
            .jsonPath("$.orgUlidId")
            .value(is(DEFAULT_ORG_ULID_ID))
            .jsonPath("$.orgName")
            .value(is(DEFAULT_ORG_NAME))
            .jsonPath("$.orgAddress")
            .value(is(DEFAULT_ORG_ADDRESS))
            .jsonPath("$.industryType")
            .value(is(DEFAULT_INDUSTRY_TYPE))
            .jsonPath("$.tenantId")
            .value(is(DEFAULT_TENANT_ID));
    }

    @Test
    void getNonExistingOrganization() {
        // Get the organization
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingOrganization() throws Exception {
        // Initialize the database
        organizationRepository.save(organization).block();

        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();

        // Update the organization
        Organization updatedOrganization = organizationRepository.findById(organization.getId()).block();
        updatedOrganization
            .orgId(UPDATED_ORG_ID)
            .orgUlidId(UPDATED_ORG_ULID_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgAddress(UPDATED_ORG_ADDRESS)
            .industryType(UPDATED_INDUSTRY_TYPE)
            .tenantId(UPDATED_TENANT_ID);
        OrganizationDTO organizationDTO = organizationMapper.toDto(updatedOrganization);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, organizationDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testOrganization.getOrgUlidId()).isEqualTo(UPDATED_ORG_ULID_ID);
        assertThat(testOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testOrganization.getOrgAddress()).isEqualTo(UPDATED_ORG_ADDRESS);
        assertThat(testOrganization.getIndustryType()).isEqualTo(UPDATED_INDUSTRY_TYPE);
        assertThat(testOrganization.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    void putNonExistingOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();
        organization.setId(longCount.incrementAndGet());

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, organizationDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();
        organization.setId(longCount.incrementAndGet());

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();
        organization.setId(longCount.incrementAndGet());

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateOrganizationWithPatch() throws Exception {
        // Initialize the database
        organizationRepository.save(organization).block();

        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();

        // Update the organization using partial update
        Organization partialUpdatedOrganization = new Organization();
        partialUpdatedOrganization.setId(organization.getId());

        partialUpdatedOrganization.orgUlidId(UPDATED_ORG_ULID_ID).orgName(UPDATED_ORG_NAME);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedOrganization.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedOrganization))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getOrgId()).isEqualTo(DEFAULT_ORG_ID);
        assertThat(testOrganization.getOrgUlidId()).isEqualTo(UPDATED_ORG_ULID_ID);
        assertThat(testOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testOrganization.getOrgAddress()).isEqualTo(DEFAULT_ORG_ADDRESS);
        assertThat(testOrganization.getIndustryType()).isEqualTo(DEFAULT_INDUSTRY_TYPE);
        assertThat(testOrganization.getTenantId()).isEqualTo(DEFAULT_TENANT_ID);
    }

    @Test
    void fullUpdateOrganizationWithPatch() throws Exception {
        // Initialize the database
        organizationRepository.save(organization).block();

        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();

        // Update the organization using partial update
        Organization partialUpdatedOrganization = new Organization();
        partialUpdatedOrganization.setId(organization.getId());

        partialUpdatedOrganization
            .orgId(UPDATED_ORG_ID)
            .orgUlidId(UPDATED_ORG_ULID_ID)
            .orgName(UPDATED_ORG_NAME)
            .orgAddress(UPDATED_ORG_ADDRESS)
            .industryType(UPDATED_INDUSTRY_TYPE)
            .tenantId(UPDATED_TENANT_ID);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedOrganization.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedOrganization))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
        Organization testOrganization = organizationList.get(organizationList.size() - 1);
        assertThat(testOrganization.getOrgId()).isEqualTo(UPDATED_ORG_ID);
        assertThat(testOrganization.getOrgUlidId()).isEqualTo(UPDATED_ORG_ULID_ID);
        assertThat(testOrganization.getOrgName()).isEqualTo(UPDATED_ORG_NAME);
        assertThat(testOrganization.getOrgAddress()).isEqualTo(UPDATED_ORG_ADDRESS);
        assertThat(testOrganization.getIndustryType()).isEqualTo(UPDATED_INDUSTRY_TYPE);
        assertThat(testOrganization.getTenantId()).isEqualTo(UPDATED_TENANT_ID);
    }

    @Test
    void patchNonExistingOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();
        organization.setId(longCount.incrementAndGet());

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, organizationDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();
        organization.setId(longCount.incrementAndGet());

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, longCount.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamOrganization() throws Exception {
        int databaseSizeBeforeUpdate = organizationRepository.findAll().collectList().block().size();
        organization.setId(longCount.incrementAndGet());

        // Create the Organization
        OrganizationDTO organizationDTO = organizationMapper.toDto(organization);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(organizationDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Organization in the database
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteOrganization() {
        // Initialize the database
        organizationRepository.save(organization).block();

        int databaseSizeBeforeDelete = organizationRepository.findAll().collectList().block().size();

        // Delete the organization
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, organization.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<Organization> organizationList = organizationRepository.findAll().collectList().block();
        assertThat(organizationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
