package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.AcceptedOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.PlacedOfferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RequestOfferTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FinancePartnerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinancePartner.class);
        FinancePartner financePartner1 = getFinancePartnerSample1();
        FinancePartner financePartner2 = new FinancePartner();
        assertThat(financePartner1).isNotEqualTo(financePartner2);

        financePartner2.setId(financePartner1.getId());
        assertThat(financePartner1).isEqualTo(financePartner2);

        financePartner2 = getFinancePartnerSample2();
        assertThat(financePartner1).isNotEqualTo(financePartner2);
    }

    @Test
    void requestOfferTest() throws Exception {
        FinancePartner financePartner = getFinancePartnerRandomSampleGenerator();
        RequestOffer requestOfferBack = getRequestOfferRandomSampleGenerator();

        financePartner.addRequestOffer(requestOfferBack);
        assertThat(financePartner.getRequestOffers()).containsOnly(requestOfferBack);
        assertThat(requestOfferBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.removeRequestOffer(requestOfferBack);
        assertThat(financePartner.getRequestOffers()).doesNotContain(requestOfferBack);
        assertThat(requestOfferBack.getFinancepartner()).isNull();

        financePartner.requestOffers(new HashSet<>(Set.of(requestOfferBack)));
        assertThat(financePartner.getRequestOffers()).containsOnly(requestOfferBack);
        assertThat(requestOfferBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.setRequestOffers(new HashSet<>());
        assertThat(financePartner.getRequestOffers()).doesNotContain(requestOfferBack);
        assertThat(requestOfferBack.getFinancepartner()).isNull();
    }

    @Test
    void placedOfferTest() throws Exception {
        FinancePartner financePartner = getFinancePartnerRandomSampleGenerator();
        PlacedOffer placedOfferBack = getPlacedOfferRandomSampleGenerator();

        financePartner.addPlacedOffer(placedOfferBack);
        assertThat(financePartner.getPlacedOffers()).containsOnly(placedOfferBack);
        assertThat(placedOfferBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.removePlacedOffer(placedOfferBack);
        assertThat(financePartner.getPlacedOffers()).doesNotContain(placedOfferBack);
        assertThat(placedOfferBack.getFinancepartner()).isNull();

        financePartner.placedOffers(new HashSet<>(Set.of(placedOfferBack)));
        assertThat(financePartner.getPlacedOffers()).containsOnly(placedOfferBack);
        assertThat(placedOfferBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.setPlacedOffers(new HashSet<>());
        assertThat(financePartner.getPlacedOffers()).doesNotContain(placedOfferBack);
        assertThat(placedOfferBack.getFinancepartner()).isNull();
    }

    @Test
    void acceptedOfferTest() throws Exception {
        FinancePartner financePartner = getFinancePartnerRandomSampleGenerator();
        AcceptedOffer acceptedOfferBack = getAcceptedOfferRandomSampleGenerator();

        financePartner.addAcceptedOffer(acceptedOfferBack);
        assertThat(financePartner.getAcceptedOffers()).containsOnly(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.removeAcceptedOffer(acceptedOfferBack);
        assertThat(financePartner.getAcceptedOffers()).doesNotContain(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancepartner()).isNull();

        financePartner.acceptedOffers(new HashSet<>(Set.of(acceptedOfferBack)));
        assertThat(financePartner.getAcceptedOffers()).containsOnly(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.setAcceptedOffers(new HashSet<>());
        assertThat(financePartner.getAcceptedOffers()).doesNotContain(acceptedOfferBack);
        assertThat(acceptedOfferBack.getFinancepartner()).isNull();
    }

    @Test
    void disbursementTest() throws Exception {
        FinancePartner financePartner = getFinancePartnerRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        financePartner.addDisbursement(disbursementBack);
        assertThat(financePartner.getDisbursements()).containsOnly(disbursementBack);
        assertThat(disbursementBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.removeDisbursement(disbursementBack);
        assertThat(financePartner.getDisbursements()).doesNotContain(disbursementBack);
        assertThat(disbursementBack.getFinancepartner()).isNull();

        financePartner.disbursements(new HashSet<>(Set.of(disbursementBack)));
        assertThat(financePartner.getDisbursements()).containsOnly(disbursementBack);
        assertThat(disbursementBack.getFinancepartner()).isEqualTo(financePartner);

        financePartner.setDisbursements(new HashSet<>());
        assertThat(financePartner.getDisbursements()).doesNotContain(disbursementBack);
        assertThat(disbursementBack.getFinancepartner()).isNull();
    }
}
