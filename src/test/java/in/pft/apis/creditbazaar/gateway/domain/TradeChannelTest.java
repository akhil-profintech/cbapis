package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.TradeChannelTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeChannelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeChannel.class);
        TradeChannel tradeChannel1 = getTradeChannelSample1();
        TradeChannel tradeChannel2 = new TradeChannel();
        assertThat(tradeChannel1).isNotEqualTo(tradeChannel2);

        tradeChannel2.setId(tradeChannel1.getId());
        assertThat(tradeChannel1).isEqualTo(tradeChannel2);

        tradeChannel2 = getTradeChannelSample2();
        assertThat(tradeChannel1).isNotEqualTo(tradeChannel2);
    }
}
