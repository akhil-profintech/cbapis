package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FinanceRequestMappingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinanceRequestMappingDTO.class);
        FinanceRequestMappingDTO financeRequestMappingDTO1 = new FinanceRequestMappingDTO();
        financeRequestMappingDTO1.setId(1L);
        FinanceRequestMappingDTO financeRequestMappingDTO2 = new FinanceRequestMappingDTO();
        assertThat(financeRequestMappingDTO1).isNotEqualTo(financeRequestMappingDTO2);
        financeRequestMappingDTO2.setId(financeRequestMappingDTO1.getId());
        assertThat(financeRequestMappingDTO1).isEqualTo(financeRequestMappingDTO2);
        financeRequestMappingDTO2.setId(2L);
        assertThat(financeRequestMappingDTO1).isNotEqualTo(financeRequestMappingDTO2);
        financeRequestMappingDTO1.setId(null);
        assertThat(financeRequestMappingDTO1).isNotEqualTo(financeRequestMappingDTO2);
    }
}
