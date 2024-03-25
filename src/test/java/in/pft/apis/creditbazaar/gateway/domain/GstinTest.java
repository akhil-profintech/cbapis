package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.GstinTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.OrganizationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GstinTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Gstin.class);
        Gstin gstin1 = getGstinSample1();
        Gstin gstin2 = new Gstin();
        assertThat(gstin1).isNotEqualTo(gstin2);

        gstin2.setId(gstin1.getId());
        assertThat(gstin1).isEqualTo(gstin2);

        gstin2 = getGstinSample2();
        assertThat(gstin1).isNotEqualTo(gstin2);
    }

    @Test
    void organizationTest() throws Exception {
        Gstin gstin = getGstinRandomSampleGenerator();
        Organization organizationBack = getOrganizationRandomSampleGenerator();

        gstin.setOrganization(organizationBack);
        assertThat(gstin.getOrganization()).isEqualTo(organizationBack);

        gstin.organization(null);
        assertThat(gstin.getOrganization()).isNull();
    }
}
