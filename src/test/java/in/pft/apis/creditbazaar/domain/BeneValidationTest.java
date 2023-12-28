package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.BeneValidationTestSamples.*;
import static in.pft.apis.creditbazaar.domain.TradeEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BeneValidationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeneValidation.class);
        BeneValidation beneValidation1 = getBeneValidationSample1();
        BeneValidation beneValidation2 = new BeneValidation();
        assertThat(beneValidation1).isNotEqualTo(beneValidation2);

        beneValidation2.setId(beneValidation1.getId());
        assertThat(beneValidation1).isEqualTo(beneValidation2);

        beneValidation2 = getBeneValidationSample2();
        assertThat(beneValidation1).isNotEqualTo(beneValidation2);
    }

    @Test
    void tradeEntityTest() throws Exception {
        BeneValidation beneValidation = getBeneValidationRandomSampleGenerator();
        TradeEntity tradeEntityBack = getTradeEntityRandomSampleGenerator();

        beneValidation.setTradeEntity(tradeEntityBack);
        assertThat(beneValidation.getTradeEntity()).isEqualTo(tradeEntityBack);

        beneValidation.tradeEntity(null);
        assertThat(beneValidation.getTradeEntity()).isNull();
    }
}
