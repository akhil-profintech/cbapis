package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TradeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Trade.class);
        Trade trade1 = getTradeSample1();
        Trade trade2 = new Trade();
        assertThat(trade1).isNotEqualTo(trade2);

        trade2.setId(trade1.getId());
        assertThat(trade1).isEqualTo(trade2);

        trade2 = getTradeSample2();
        assertThat(trade1).isNotEqualTo(trade2);
    }

    @Test
    void financerequestTest() throws Exception {
        Trade trade = getTradeRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        trade.setFinancerequest(financeRequestBack);
        assertThat(trade.getFinancerequest()).isEqualTo(financeRequestBack);

        trade.financerequest(null);
        assertThat(trade.getFinancerequest()).isNull();
    }

    @Test
    void anchortraderTest() throws Exception {
        Trade trade = getTradeRandomSampleGenerator();
        AnchorTrader anchorTraderBack = getAnchorTraderRandomSampleGenerator();

        trade.setAnchortrader(anchorTraderBack);
        assertThat(trade.getAnchortrader()).isEqualTo(anchorTraderBack);

        trade.anchortrader(null);
        assertThat(trade.getAnchortrader()).isNull();
    }

    @Test
    void tradepartnerTest() throws Exception {
        Trade trade = getTradeRandomSampleGenerator();
        TradePartner tradePartnerBack = getTradePartnerRandomSampleGenerator();

        trade.setTradepartner(tradePartnerBack);
        assertThat(trade.getTradepartner()).isEqualTo(tradePartnerBack);

        trade.tradepartner(null);
        assertThat(trade.getTradepartner()).isNull();
    }
}
