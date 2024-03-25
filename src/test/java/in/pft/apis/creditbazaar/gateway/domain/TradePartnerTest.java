package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.TradePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TradePartnerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradePartner.class);
        TradePartner tradePartner1 = getTradePartnerSample1();
        TradePartner tradePartner2 = new TradePartner();
        assertThat(tradePartner1).isNotEqualTo(tradePartner2);

        tradePartner2.setId(tradePartner1.getId());
        assertThat(tradePartner1).isEqualTo(tradePartner2);

        tradePartner2 = getTradePartnerSample2();
        assertThat(tradePartner1).isNotEqualTo(tradePartner2);
    }

    @Test
    void tradeTest() throws Exception {
        TradePartner tradePartner = getTradePartnerRandomSampleGenerator();
        Trade tradeBack = getTradeRandomSampleGenerator();

        tradePartner.addTrade(tradeBack);
        assertThat(tradePartner.getTrades()).containsOnly(tradeBack);
        assertThat(tradeBack.getTradepartner()).isEqualTo(tradePartner);

        tradePartner.removeTrade(tradeBack);
        assertThat(tradePartner.getTrades()).doesNotContain(tradeBack);
        assertThat(tradeBack.getTradepartner()).isNull();

        tradePartner.trades(new HashSet<>(Set.of(tradeBack)));
        assertThat(tradePartner.getTrades()).containsOnly(tradeBack);
        assertThat(tradeBack.getTradepartner()).isEqualTo(tradePartner);

        tradePartner.setTrades(new HashSet<>());
        assertThat(tradePartner.getTrades()).doesNotContain(tradeBack);
        assertThat(tradeBack.getTradepartner()).isNull();
    }
}
