package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.TenantDetailsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenantDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenantDetails.class);
        TenantDetails tenantDetails1 = getTenantDetailsSample1();
        TenantDetails tenantDetails2 = new TenantDetails();
        assertThat(tenantDetails1).isNotEqualTo(tenantDetails2);

        tenantDetails2.setId(tenantDetails1.getId());
        assertThat(tenantDetails1).isEqualTo(tenantDetails2);

        tenantDetails2 = getTenantDetailsSample2();
        assertThat(tenantDetails1).isNotEqualTo(tenantDetails2);
    }
}
