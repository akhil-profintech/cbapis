package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RequestOfferTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestOfferTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestOffer.class);
        RequestOffer requestOffer1 = getRequestOfferSample1();
        RequestOffer requestOffer2 = new RequestOffer();
        assertThat(requestOffer1).isNotEqualTo(requestOffer2);

        requestOffer2.setId(requestOffer1.getId());
        assertThat(requestOffer1).isEqualTo(requestOffer2);

        requestOffer2 = getRequestOfferSample2();
        assertThat(requestOffer1).isNotEqualTo(requestOffer2);
    }

    @Test
    void financerequestTest() throws Exception {
        RequestOffer requestOffer = getRequestOfferRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        requestOffer.setFinancerequest(financeRequestBack);
        assertThat(requestOffer.getFinancerequest()).isEqualTo(financeRequestBack);

        requestOffer.financerequest(null);
        assertThat(requestOffer.getFinancerequest()).isNull();
    }

    @Test
    void financepartnerTest() throws Exception {
        RequestOffer requestOffer = getRequestOfferRandomSampleGenerator();
        FinancePartner financePartnerBack = getFinancePartnerRandomSampleGenerator();

        requestOffer.setFinancepartner(financePartnerBack);
        assertThat(requestOffer.getFinancepartner()).isEqualTo(financePartnerBack);

        requestOffer.financepartner(null);
        assertThat(requestOffer.getFinancepartner()).isNull();
    }
}
