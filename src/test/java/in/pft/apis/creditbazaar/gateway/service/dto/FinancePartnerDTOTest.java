package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FinancePartnerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancePartnerDTO.class);
        FinancePartnerDTO financePartnerDTO1 = new FinancePartnerDTO();
        financePartnerDTO1.setId(1L);
        FinancePartnerDTO financePartnerDTO2 = new FinancePartnerDTO();
        assertThat(financePartnerDTO1).isNotEqualTo(financePartnerDTO2);
        financePartnerDTO2.setId(financePartnerDTO1.getId());
        assertThat(financePartnerDTO1).isEqualTo(financePartnerDTO2);
        financePartnerDTO2.setId(2L);
        assertThat(financePartnerDTO1).isNotEqualTo(financePartnerDTO2);
        financePartnerDTO1.setId(null);
        assertThat(financePartnerDTO1).isNotEqualTo(financePartnerDTO2);
    }
}
