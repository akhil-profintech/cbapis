package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.TradeEntityTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.UpdateVATestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UpdateVATest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UpdateVA.class);
        UpdateVA updateVA1 = getUpdateVASample1();
        UpdateVA updateVA2 = new UpdateVA();
        assertThat(updateVA1).isNotEqualTo(updateVA2);

        updateVA2.setId(updateVA1.getId());
        assertThat(updateVA1).isEqualTo(updateVA2);

        updateVA2 = getUpdateVASample2();
        assertThat(updateVA1).isNotEqualTo(updateVA2);
    }

    @Test
    void tradeEntityTest() throws Exception {
        UpdateVA updateVA = getUpdateVARandomSampleGenerator();
        TradeEntity tradeEntityBack = getTradeEntityRandomSampleGenerator();

        updateVA.setTradeEntity(tradeEntityBack);
        assertThat(updateVA.getTradeEntity()).isEqualTo(tradeEntityBack);

        updateVA.tradeEntity(null);
        assertThat(updateVA.getTradeEntity()).isNull();
    }
}
