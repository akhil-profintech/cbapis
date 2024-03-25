package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.TradeEntityTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.VANumberTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VANumberTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(VANumber.class);
        VANumber vANumber1 = getVANumberSample1();
        VANumber vANumber2 = new VANumber();
        assertThat(vANumber1).isNotEqualTo(vANumber2);

        vANumber2.setId(vANumber1.getId());
        assertThat(vANumber1).isEqualTo(vANumber2);

        vANumber2 = getVANumberSample2();
        assertThat(vANumber1).isNotEqualTo(vANumber2);
    }

    @Test
    void tradeEntityTest() throws Exception {
        VANumber vANumber = getVANumberRandomSampleGenerator();
        TradeEntity tradeEntityBack = getTradeEntityRandomSampleGenerator();

        vANumber.setTradeEntity(tradeEntityBack);
        assertThat(vANumber.getTradeEntity()).isEqualTo(tradeEntityBack);

        vANumber.tradeEntity(null);
        assertThat(vANumber.getTradeEntity()).isNull();
    }
}
