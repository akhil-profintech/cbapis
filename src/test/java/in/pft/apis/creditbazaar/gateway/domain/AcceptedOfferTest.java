package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.AcceptedOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AcceptedOfferTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcceptedOffer.class);
        AcceptedOffer acceptedOffer1 = getAcceptedOfferSample1();
        AcceptedOffer acceptedOffer2 = new AcceptedOffer();
        assertThat(acceptedOffer1).isNotEqualTo(acceptedOffer2);

        acceptedOffer2.setId(acceptedOffer1.getId());
        assertThat(acceptedOffer1).isEqualTo(acceptedOffer2);

        acceptedOffer2 = getAcceptedOfferSample2();
        assertThat(acceptedOffer1).isNotEqualTo(acceptedOffer2);
    }

    @Test
    void financerequestTest() throws Exception {
        AcceptedOffer acceptedOffer = getAcceptedOfferRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        acceptedOffer.setFinancerequest(financeRequestBack);
        assertThat(acceptedOffer.getFinancerequest()).isEqualTo(financeRequestBack);

        acceptedOffer.financerequest(null);
        assertThat(acceptedOffer.getFinancerequest()).isNull();
    }

    @Test
    void anchortraderTest() throws Exception {
        AcceptedOffer acceptedOffer = getAcceptedOfferRandomSampleGenerator();
        AnchorTrader anchorTraderBack = getAnchorTraderRandomSampleGenerator();

        acceptedOffer.setAnchortrader(anchorTraderBack);
        assertThat(acceptedOffer.getAnchortrader()).isEqualTo(anchorTraderBack);

        acceptedOffer.anchortrader(null);
        assertThat(acceptedOffer.getAnchortrader()).isNull();
    }

    @Test
    void financepartnerTest() throws Exception {
        AcceptedOffer acceptedOffer = getAcceptedOfferRandomSampleGenerator();
        FinancePartner financePartnerBack = getFinancePartnerRandomSampleGenerator();

        acceptedOffer.setFinancepartner(financePartnerBack);
        assertThat(acceptedOffer.getFinancepartner()).isEqualTo(financePartnerBack);

        acceptedOffer.financepartner(null);
        assertThat(acceptedOffer.getFinancepartner()).isNull();
    }
}
