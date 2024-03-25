package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TenantDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TenantDetailsDTO.class);
        TenantDetailsDTO tenantDetailsDTO1 = new TenantDetailsDTO();
        tenantDetailsDTO1.setId(1L);
        TenantDetailsDTO tenantDetailsDTO2 = new TenantDetailsDTO();
        assertThat(tenantDetailsDTO1).isNotEqualTo(tenantDetailsDTO2);
        tenantDetailsDTO2.setId(tenantDetailsDTO1.getId());
        assertThat(tenantDetailsDTO1).isEqualTo(tenantDetailsDTO2);
        tenantDetailsDTO2.setId(2L);
        assertThat(tenantDetailsDTO1).isNotEqualTo(tenantDetailsDTO2);
        tenantDetailsDTO1.setId(null);
        assertThat(tenantDetailsDTO1).isNotEqualTo(tenantDetailsDTO2);
    }
}
