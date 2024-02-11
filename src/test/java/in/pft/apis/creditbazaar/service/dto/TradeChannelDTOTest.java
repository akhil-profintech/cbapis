package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeChannelDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeChannelDTO.class);
        TradeChannelDTO tradeChannelDTO1 = new TradeChannelDTO();
        tradeChannelDTO1.setId(1L);
        TradeChannelDTO tradeChannelDTO2 = new TradeChannelDTO();
        assertThat(tradeChannelDTO1).isNotEqualTo(tradeChannelDTO2);
        tradeChannelDTO2.setId(tradeChannelDTO1.getId());
        assertThat(tradeChannelDTO1).isEqualTo(tradeChannelDTO2);
        tradeChannelDTO2.setId(2L);
        assertThat(tradeChannelDTO1).isNotEqualTo(tradeChannelDTO2);
        tradeChannelDTO1.setId(null);
        assertThat(tradeChannelDTO1).isNotEqualTo(tradeChannelDTO2);
    }
}
