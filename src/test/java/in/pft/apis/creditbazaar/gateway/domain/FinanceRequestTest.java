package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.AcceptedOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.CBCREProcessTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.DocDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.PlacedOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RepaymentTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RequestOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.SettlementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FinanceRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinanceRequest.class);
        FinanceRequest financeRequest1 = getFinanceRequestSample1();
        FinanceRequest financeRequest2 = new FinanceRequest();
        assertThat(financeRequest1).isNotEqualTo(financeRequest2);

        financeRequest2.setId(financeRequest1.getId());
        assertThat(financeRequest1).isEqualTo(financeRequest2);

        financeRequest2 = getFinanceRequestSample2();
        assertThat(financeRequest1).isNotEqualTo(financeRequest2);
    }

    @Test
    void requestOfferTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        RequestOffer requestOfferBack = getRequestOfferRandomSampleGenerator();

        financeRequest.addRequestOffer(requestOfferBack);
        assertThat(financeRequest.getRequestOffers()).containsOnly(requestOfferBack);
        assertThat(requestOfferBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removeRequestOffer(requestOfferBack);
        assertThat(financeRequest.getRequestOffers()).doesNotContain(requestOfferBack);
        assertThat(requestOfferBack.getFinancerequest()).isNull();

        financeRequest.requestOffers(new HashSet<>(Set.of(requestOfferBack)));
        assertThat(financeRequest.getRequestOffers()).containsOnly(requestOfferBack);
        assertThat(requestOfferBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setRequestOffers(new HashSet<>());
        assertThat(financeRequest.getRequestOffers()).doesNotContain(requestOfferBack);
        assertThat(requestOfferBack.getFinancerequest()).isNull();
    }

    @Test
    void tradeTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        Trade tradeBack = getTradeRandomSampleGenerator();

        financeRequest.addTrade(tradeBack);
        assertThat(financeRequest.getTrades()).containsOnly(tradeBack);
        assertThat(tradeBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removeTrade(tradeBack);
        assertThat(financeRequest.getTrades()).doesNotContain(tradeBack);
        assertThat(tradeBack.getFinancerequest()).isNull();

        financeRequest.trades(new HashSet<>(Set.of(tradeBack)));
        assertThat(financeRequest.getTrades()).containsOnly(tradeBack);
        assertThat(tradeBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setTrades(new HashSet<>());
        assertThat(financeRequest.getTrades()).doesNotContain(tradeBack);
        assertThat(tradeBack.getFinancerequest()).isNull();
    }

    @Test
    void placedOfferTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        PlacedOffer placedOfferBack = getPlacedOfferRandomSampleGenerator();

        financeRequest.addPlacedOffer(placedOfferBack);
        assertThat(financeRequest.getPlacedOffers()).containsOnly(placedOfferBack);
        assertThat(placedOfferBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removePlacedOffer(placedOfferBack);
        assertThat(financeRequest.getPlacedOffers()).doesNotContain(placedOfferBack);
        assertThat(placedOfferBack.getFinancerequest()).isNull();

        financeRequest.placedOffers(new HashSet<>(Set.of(placedOfferBack)));
        assertThat(financeRequest.getPlacedOffers()).containsOnly(placedOfferBack);
        assertThat(placedOfferBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setPlacedOffers(new HashSet<>());
        assertThat(financeRequest.getPlacedOffers()).doesNotContain(placedOfferBack);
        assertThat(placedOfferBack.getFinancerequest()).isNull();
    }

    @Test
    void acceptedOfferTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        AcceptedOffer acceptedOfferBack = getAcceptedOfferRandomSampleGenerator();

        financeRequest.addAcceptedOffer(acceptedOfferBack);
        assertThat(financeRequest.getAcceptedOffers()).containsOnly(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removeAcceptedOffer(acceptedOfferBack);
        assertThat(financeRequest.getAcceptedOffers()).doesNotContain(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancerequest()).isNull();

        financeRequest.acceptedOffers(new HashSet<>(Set.of(acceptedOfferBack)));
        assertThat(financeRequest.getAcceptedOffers()).containsOnly(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setAcceptedOffers(new HashSet<>());
        assertThat(financeRequest.getAcceptedOffers()).doesNotContain(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancerequest()).isNull();
    }

    @Test
    void disbursementTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        financeRequest.addDisbursement(disbursementBack);
        assertThat(financeRequest.getDisbursements()).containsOnly(disbursementBack);
        assertThat(disbursementBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removeDisbursement(disbursementBack);
        assertThat(financeRequest.getDisbursements()).doesNotContain(disbursementBack);
        assertThat(disbursementBack.getFinancerequest()).isNull();

        financeRequest.disbursements(new HashSet<>(Set.of(disbursementBack)));
        assertThat(financeRequest.getDisbursements()).containsOnly(disbursementBack);
        assertThat(disbursementBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setDisbursements(new HashSet<>());
        assertThat(financeRequest.getDisbursements()).doesNotContain(disbursementBack);
        assertThat(disbursementBack.getFinancerequest()).isNull();
    }

    @Test
    void docDetailsTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        DocDetails docDetailsBack = getDocDetailsRandomSampleGenerator();

        financeRequest.addDocDetails(docDetailsBack);
        assertThat(financeRequest.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getFinanceRequest()).isEqualTo(financeRequest);

        financeRequest.removeDocDetails(docDetailsBack);
        assertThat(financeRequest.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getFinanceRequest()).isNull();

        financeRequest.docDetails(new HashSet<>(Set.of(docDetailsBack)));
        assertThat(financeRequest.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getFinanceRequest()).isEqualTo(financeRequest);

        financeRequest.setDocDetails(new HashSet<>());
        assertThat(financeRequest.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getFinanceRequest()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        financeRequest.addRepayment(repaymentBack);
        assertThat(financeRequest.getRepayments()).containsOnly(repaymentBack);
        assertThat(repaymentBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removeRepayment(repaymentBack);
        assertThat(financeRequest.getRepayments()).doesNotContain(repaymentBack);
        assertThat(repaymentBack.getFinancerequest()).isNull();

        financeRequest.repayments(new HashSet<>(Set.of(repaymentBack)));
        assertThat(financeRequest.getRepayments()).containsOnly(repaymentBack);
        assertThat(repaymentBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setRepayments(new HashSet<>());
        assertThat(financeRequest.getRepayments()).doesNotContain(repaymentBack);
        assertThat(repaymentBack.getFinancerequest()).isNull();
    }

    @Test
    void settlementTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        Settlement settlementBack = getSettlementRandomSampleGenerator();

        financeRequest.addSettlement(settlementBack);
        assertThat(financeRequest.getSettlements()).containsOnly(settlementBack);
        assertThat(settlementBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.removeSettlement(settlementBack);
        assertThat(financeRequest.getSettlements()).doesNotContain(settlementBack);
        assertThat(settlementBack.getFinancerequest()).isNull();

        financeRequest.settlements(new HashSet<>(Set.of(settlementBack)));
        assertThat(financeRequest.getSettlements()).containsOnly(settlementBack);
        assertThat(settlementBack.getFinancerequest()).isEqualTo(financeRequest);

        financeRequest.setSettlements(new HashSet<>());
        assertThat(financeRequest.getSettlements()).doesNotContain(settlementBack);
        assertThat(settlementBack.getFinancerequest()).isNull();
    }

    @Test
    void cBCREProcessTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        CBCREProcess cBCREProcessBack = getCBCREProcessRandomSampleGenerator();

        financeRequest.addCBCREProcess(cBCREProcessBack);
        assertThat(financeRequest.getCBCREProcesses()).containsOnly(cBCREProcessBack);
        assertThat(cBCREProcessBack.getFinanceRequest()).isEqualTo(financeRequest);

        financeRequest.removeCBCREProcess(cBCREProcessBack);
        assertThat(financeRequest.getCBCREProcesses()).doesNotContain(cBCREProcessBack);
        assertThat(cBCREProcessBack.getFinanceRequest()).isNull();

        financeRequest.cBCREProcesses(new HashSet<>(Set.of(cBCREProcessBack)));
        assertThat(financeRequest.getCBCREProcesses()).containsOnly(cBCREProcessBack);
        assertThat(cBCREProcessBack.getFinanceRequest()).isEqualTo(financeRequest);

        financeRequest.setCBCREProcesses(new HashSet<>());
        assertThat(financeRequest.getCBCREProcesses()).doesNotContain(cBCREProcessBack);
        assertThat(cBCREProcessBack.getFinanceRequest()).isNull();
    }

    @Test
    void anchortraderTest() throws Exception {
        FinanceRequest financeRequest = getFinanceRequestRandomSampleGenerator();
        AnchorTrader anchorTraderBack = getAnchorTraderRandomSampleGenerator();

        financeRequest.setAnchortrader(anchorTraderBack);
        assertThat(financeRequest.getAnchortrader()).isEqualTo(anchorTraderBack);

        financeRequest.anchortrader(null);
        assertThat(financeRequest.getAnchortrader()).isNull();
    }
}
