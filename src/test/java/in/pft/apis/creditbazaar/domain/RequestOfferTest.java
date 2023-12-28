package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CBCREProcessTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RequestOfferTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
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
    void cbcreprocessTest() throws Exception {
        RequestOffer requestOffer = getRequestOfferRandomSampleGenerator();
        CBCREProcess cBCREProcessBack = getCBCREProcessRandomSampleGenerator();

        requestOffer.setCbcreprocess(cBCREProcessBack);
        assertThat(requestOffer.getCbcreprocess()).isEqualTo(cBCREProcessBack);

        requestOffer.cbcreprocess(null);
        assertThat(requestOffer.getCbcreprocess()).isNull();
    }
}
