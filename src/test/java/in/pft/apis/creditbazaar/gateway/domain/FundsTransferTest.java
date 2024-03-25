package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.FundsTransferTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.TradeEntityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsTransferTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundsTransfer.class);
        FundsTransfer fundsTransfer1 = getFundsTransferSample1();
        FundsTransfer fundsTransfer2 = new FundsTransfer();
        assertThat(fundsTransfer1).isNotEqualTo(fundsTransfer2);

        fundsTransfer2.setId(fundsTransfer1.getId());
        assertThat(fundsTransfer1).isEqualTo(fundsTransfer2);

        fundsTransfer2 = getFundsTransferSample2();
        assertThat(fundsTransfer1).isNotEqualTo(fundsTransfer2);
    }

    @Test
    void tradeEntityTest() throws Exception {
        FundsTransfer fundsTransfer = getFundsTransferRandomSampleGenerator();
        TradeEntity tradeEntityBack = getTradeEntityRandomSampleGenerator();

        fundsTransfer.setTradeEntity(tradeEntityBack);
        assertThat(fundsTransfer.getTradeEntity()).isEqualTo(tradeEntityBack);

        fundsTransfer.tradeEntity(null);
        assertThat(fundsTransfer.getTradeEntity()).isNull();
    }
}
