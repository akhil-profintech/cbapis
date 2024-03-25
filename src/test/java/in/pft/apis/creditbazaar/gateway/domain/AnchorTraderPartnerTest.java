package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.AnchorTraderTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnchorTraderPartnerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnchorTraderPartner.class);
        AnchorTraderPartner anchorTraderPartner1 = getAnchorTraderPartnerSample1();
        AnchorTraderPartner anchorTraderPartner2 = new AnchorTraderPartner();
        assertThat(anchorTraderPartner1).isNotEqualTo(anchorTraderPartner2);

        anchorTraderPartner2.setId(anchorTraderPartner1.getId());
        assertThat(anchorTraderPartner1).isEqualTo(anchorTraderPartner2);

        anchorTraderPartner2 = getAnchorTraderPartnerSample2();
        assertThat(anchorTraderPartner1).isNotEqualTo(anchorTraderPartner2);
    }

    @Test
    void anchortraderTest() throws Exception {
        AnchorTraderPartner anchorTraderPartner = getAnchorTraderPartnerRandomSampleGenerator();
        AnchorTrader anchorTraderBack = getAnchorTraderRandomSampleGenerator();

        anchorTraderPartner.setAnchortrader(anchorTraderBack);
        assertThat(anchorTraderPartner.getAnchortrader()).isEqualTo(anchorTraderBack);

        anchorTraderPartner.anchortrader(null);
        assertThat(anchorTraderPartner.getAnchortrader()).isNull();
    }
}
