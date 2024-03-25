package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.AcceptedOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class AnchorTraderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnchorTrader.class);
        AnchorTrader anchorTrader1 = getAnchorTraderSample1();
        AnchorTrader anchorTrader2 = new AnchorTrader();
        assertThat(anchorTrader1).isNotEqualTo(anchorTrader2);

        anchorTrader2.setId(anchorTrader1.getId());
        assertThat(anchorTrader1).isEqualTo(anchorTrader2);

        anchorTrader2 = getAnchorTraderSample2();
        assertThat(anchorTrader1).isNotEqualTo(anchorTrader2);
    }

    @Test
    void financeRequestTest() throws Exception {
        AnchorTrader anchorTrader = getAnchorTraderRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        anchorTrader.addFinanceRequest(financeRequestBack);
        assertThat(anchorTrader.getFinanceRequests()).containsOnly(financeRequestBack);
        assertThat(financeRequestBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.removeFinanceRequest(financeRequestBack);
        assertThat(anchorTrader.getFinanceRequests()).doesNotContain(financeRequestBack);
        assertThat(financeRequestBack.getAnchortrader()).isNull();

        anchorTrader.financeRequests(new HashSet<>(Set.of(financeRequestBack)));
        assertThat(anchorTrader.getFinanceRequests()).containsOnly(financeRequestBack);
        assertThat(financeRequestBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.setFinanceRequests(new HashSet<>());
        assertThat(anchorTrader.getFinanceRequests()).doesNotContain(financeRequestBack);
        assertThat(financeRequestBack.getAnchortrader()).isNull();
    }

    @Test
    void anchorTraderPartnerTest() throws Exception {
        AnchorTrader anchorTrader = getAnchorTraderRandomSampleGenerator();
        AnchorTraderPartner anchorTraderPartnerBack = getAnchorTraderPartnerRandomSampleGenerator();

        anchorTrader.addAnchorTraderPartner(anchorTraderPartnerBack);
        assertThat(anchorTrader.getAnchorTraderPartners()).containsOnly(anchorTraderPartnerBack);
        assertThat(anchorTraderPartnerBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.removeAnchorTraderPartner(anchorTraderPartnerBack);
        assertThat(anchorTrader.getAnchorTraderPartners()).doesNotContain(anchorTraderPartnerBack);
        assertThat(anchorTraderPartnerBack.getAnchortrader()).isNull();

        anchorTrader.anchorTraderPartners(new HashSet<>(Set.of(anchorTraderPartnerBack)));
        assertThat(anchorTrader.getAnchorTraderPartners()).containsOnly(anchorTraderPartnerBack);
        assertThat(anchorTraderPartnerBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.setAnchorTraderPartners(new HashSet<>());
        assertThat(anchorTrader.getAnchorTraderPartners()).doesNotContain(anchorTraderPartnerBack);
        assertThat(anchorTraderPartnerBack.getAnchortrader()).isNull();
    }

    @Test
    void acceptedOfferTest() throws Exception {
        AnchorTrader anchorTrader = getAnchorTraderRandomSampleGenerator();
        AcceptedOffer acceptedOfferBack = getAcceptedOfferRandomSampleGenerator();

        anchorTrader.addAcceptedOffer(acceptedOfferBack);
        assertThat(anchorTrader.getAcceptedOffers()).containsOnly(acceptedOfferBack);
        assertThat(acceptedOfferBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.removeAcceptedOffer(acceptedOfferBack);
        assertThat(anchorTrader.getAcceptedOffers()).doesNotContain(acceptedOfferBack);
        assertThat(acceptedOfferBack.getAnchortrader()).isNull();

        anchorTrader.acceptedOffers(new HashSet<>(Set.of(acceptedOfferBack)));
        assertThat(anchorTrader.getAcceptedOffers()).containsOnly(acceptedOfferBack);
        assertThat(acceptedOfferBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.setAcceptedOffers(new HashSet<>());
        assertThat(anchorTrader.getAcceptedOffers()).doesNotContain(acceptedOfferBack);
        assertThat(acceptedOfferBack.getAnchortrader()).isNull();
    }

    @Test
    void tradeTest() throws Exception {
        AnchorTrader anchorTrader = getAnchorTraderRandomSampleGenerator();
        Trade tradeBack = getTradeRandomSampleGenerator();

        anchorTrader.addTrade(tradeBack);
        assertThat(anchorTrader.getTrades()).containsOnly(tradeBack);
        assertThat(tradeBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.removeTrade(tradeBack);
        assertThat(anchorTrader.getTrades()).doesNotContain(tradeBack);
        assertThat(tradeBack.getAnchortrader()).isNull();

        anchorTrader.trades(new HashSet<>(Set.of(tradeBack)));
        assertThat(anchorTrader.getTrades()).containsOnly(tradeBack);
        assertThat(tradeBack.getAnchortrader()).isEqualTo(anchorTrader);

        anchorTrader.setTrades(new HashSet<>());
        assertThat(anchorTrader.getTrades()).doesNotContain(tradeBack);
        assertThat(tradeBack.getAnchortrader()).isNull();
    }
}
