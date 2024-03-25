package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RepaymentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RepaymentDTO.class);
        RepaymentDTO repaymentDTO1 = new RepaymentDTO();
        repaymentDTO1.setId(1L);
        RepaymentDTO repaymentDTO2 = new RepaymentDTO();
        assertThat(repaymentDTO1).isNotEqualTo(repaymentDTO2);
        repaymentDTO2.setId(repaymentDTO1.getId());
        assertThat(repaymentDTO1).isEqualTo(repaymentDTO2);
        repaymentDTO2.setId(2L);
        assertThat(repaymentDTO1).isNotEqualTo(repaymentDTO2);
        repaymentDTO1.setId(null);
        assertThat(repaymentDTO1).isNotEqualTo(repaymentDTO2);
    }
}
