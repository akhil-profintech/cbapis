package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradePartnerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradePartnerDTO.class);
        TradePartnerDTO tradePartnerDTO1 = new TradePartnerDTO();
        tradePartnerDTO1.setId(1L);
        TradePartnerDTO tradePartnerDTO2 = new TradePartnerDTO();
        assertThat(tradePartnerDTO1).isNotEqualTo(tradePartnerDTO2);
        tradePartnerDTO2.setId(tradePartnerDTO1.getId());
        assertThat(tradePartnerDTO1).isEqualTo(tradePartnerDTO2);
        tradePartnerDTO2.setId(2L);
        assertThat(tradePartnerDTO1).isNotEqualTo(tradePartnerDTO2);
        tradePartnerDTO1.setId(null);
        assertThat(tradePartnerDTO1).isNotEqualTo(tradePartnerDTO2);
    }
}
