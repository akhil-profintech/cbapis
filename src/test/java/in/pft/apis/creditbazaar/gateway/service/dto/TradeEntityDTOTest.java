package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeEntityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeEntityDTO.class);
        TradeEntityDTO tradeEntityDTO1 = new TradeEntityDTO();
        tradeEntityDTO1.setId(1L);
        TradeEntityDTO tradeEntityDTO2 = new TradeEntityDTO();
        assertThat(tradeEntityDTO1).isNotEqualTo(tradeEntityDTO2);
        tradeEntityDTO2.setId(tradeEntityDTO1.getId());
        assertThat(tradeEntityDTO1).isEqualTo(tradeEntityDTO2);
        tradeEntityDTO2.setId(2L);
        assertThat(tradeEntityDTO1).isNotEqualTo(tradeEntityDTO2);
        tradeEntityDTO1.setId(null);
        assertThat(tradeEntityDTO1).isNotEqualTo(tradeEntityDTO2);
    }
}
