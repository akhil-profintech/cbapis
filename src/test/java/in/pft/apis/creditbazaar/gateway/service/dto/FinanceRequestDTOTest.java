package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FinanceRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinanceRequestDTO.class);
        FinanceRequestDTO financeRequestDTO1 = new FinanceRequestDTO();
        financeRequestDTO1.setId(1L);
        FinanceRequestDTO financeRequestDTO2 = new FinanceRequestDTO();
        assertThat(financeRequestDTO1).isNotEqualTo(financeRequestDTO2);
        financeRequestDTO2.setId(financeRequestDTO1.getId());
        assertThat(financeRequestDTO1).isEqualTo(financeRequestDTO2);
        financeRequestDTO2.setId(2L);
        assertThat(financeRequestDTO1).isNotEqualTo(financeRequestDTO2);
        financeRequestDTO1.setId(null);
        assertThat(financeRequestDTO1).isNotEqualTo(financeRequestDTO2);
    }
}
