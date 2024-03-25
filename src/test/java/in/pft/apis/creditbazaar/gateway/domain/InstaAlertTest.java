package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.InstaAlertTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradeEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstaAlertTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstaAlert.class);
        InstaAlert instaAlert1 = getInstaAlertSample1();
        InstaAlert instaAlert2 = new InstaAlert();
        assertThat(instaAlert1).isNotEqualTo(instaAlert2);

        instaAlert2.setId(instaAlert1.getId());
        assertThat(instaAlert1).isEqualTo(instaAlert2);

        instaAlert2 = getInstaAlertSample2();
        assertThat(instaAlert1).isNotEqualTo(instaAlert2);
    }

    @Test
    void tradeEntityTest() throws Exception {
        InstaAlert instaAlert = getInstaAlertRandomSampleGenerator();
        TradeEntity tradeEntityBack = getTradeEntityRandomSampleGenerator();

        instaAlert.setTradeEntity(tradeEntityBack);
        assertThat(instaAlert.getTradeEntity()).isEqualTo(tradeEntityBack);

        instaAlert.tradeEntity(null);
        assertThat(instaAlert.getTradeEntity()).isNull();
    }
}
