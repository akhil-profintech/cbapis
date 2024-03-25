package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SettlementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SettlementDTO.class);
        SettlementDTO settlementDTO1 = new SettlementDTO();
        settlementDTO1.setId(1L);
        SettlementDTO settlementDTO2 = new SettlementDTO();
        assertThat(settlementDTO1).isNotEqualTo(settlementDTO2);
        settlementDTO2.setId(settlementDTO1.getId());
        assertThat(settlementDTO1).isEqualTo(settlementDTO2);
        settlementDTO2.setId(2L);
        assertThat(settlementDTO1).isNotEqualTo(settlementDTO2);
        settlementDTO1.setId(null);
        assertThat(settlementDTO1).isNotEqualTo(settlementDTO2);
    }
}
