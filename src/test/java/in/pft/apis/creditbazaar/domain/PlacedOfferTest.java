package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.domain.PlacedOfferTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlacedOfferTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlacedOffer.class);
        PlacedOffer placedOffer1 = getPlacedOfferSample1();
        PlacedOffer placedOffer2 = new PlacedOffer();
        assertThat(placedOffer1).isNotEqualTo(placedOffer2);

        placedOffer2.setId(placedOffer1.getId());
        assertThat(placedOffer1).isEqualTo(placedOffer2);

        placedOffer2 = getPlacedOfferSample2();
        assertThat(placedOffer1).isNotEqualTo(placedOffer2);
    }

    @Test
    void financerequestTest() throws Exception {
        PlacedOffer placedOffer = getPlacedOfferRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        placedOffer.setFinancerequest(financeRequestBack);
        assertThat(placedOffer.getFinancerequest()).isEqualTo(financeRequestBack);

        placedOffer.financerequest(null);
        assertThat(placedOffer.getFinancerequest()).isNull();
    }

    @Test
    void financepartnerTest() throws Exception {
        PlacedOffer placedOffer = getPlacedOfferRandomSampleGenerator();
        FinancePartner financePartnerBack = getFinancePartnerRandomSampleGenerator();

        placedOffer.setFinancepartner(financePartnerBack);
        assertThat(placedOffer.getFinancepartner()).isEqualTo(financePartnerBack);

        placedOffer.financepartner(null);
        assertThat(placedOffer.getFinancepartner()).isNull();
    }
}
