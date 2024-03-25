package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.GstinTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.OrganizationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OrganizationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organization.class);
        Organization organization1 = getOrganizationSample1();
        Organization organization2 = new Organization();
        assertThat(organization1).isNotEqualTo(organization2);

        organization2.setId(organization1.getId());
        assertThat(organization1).isEqualTo(organization2);

        organization2 = getOrganizationSample2();
        assertThat(organization1).isNotEqualTo(organization2);
    }

    @Test
    void gstinTest() throws Exception {
        Organization organization = getOrganizationRandomSampleGenerator();
        Gstin gstinBack = getGstinRandomSampleGenerator();

        organization.addGstin(gstinBack);
        assertThat(organization.getGstins()).containsOnly(gstinBack);
        assertThat(gstinBack.getOrganization()).isEqualTo(organization);

        organization.removeGstin(gstinBack);
        assertThat(organization.getGstins()).doesNotContain(gstinBack);
        assertThat(gstinBack.getOrganization()).isNull();

        organization.gstins(new HashSet<>(Set.of(gstinBack)));
        assertThat(organization.getGstins()).containsOnly(gstinBack);
        assertThat(gstinBack.getOrganization()).isEqualTo(organization);

        organization.setGstins(new HashSet<>());
        assertThat(organization.getGstins()).doesNotContain(gstinBack);
        assertThat(gstinBack.getOrganization()).isNull();
    }
}
