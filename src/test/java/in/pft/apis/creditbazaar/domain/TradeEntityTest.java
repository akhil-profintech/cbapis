package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.BeneValidationTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FundsTransferTestSamples.*;
import static in.pft.apis.creditbazaar.domain.InstaAlertTestSamples.*;
import static in.pft.apis.creditbazaar.domain.TradeEntityTestSamples.*;
import static in.pft.apis.creditbazaar.domain.UpdateVATestSamples.*;
import static in.pft.apis.creditbazaar.domain.VANumberTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TradeEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TradeEntity.class);
        TradeEntity tradeEntity1 = getTradeEntitySample1();
        TradeEntity tradeEntity2 = new TradeEntity();
        assertThat(tradeEntity1).isNotEqualTo(tradeEntity2);

        tradeEntity2.setId(tradeEntity1.getId());
        assertThat(tradeEntity1).isEqualTo(tradeEntity2);

        tradeEntity2 = getTradeEntitySample2();
        assertThat(tradeEntity1).isNotEqualTo(tradeEntity2);
    }

    @Test
    void beneValidationTest() throws Exception {
        TradeEntity tradeEntity = getTradeEntityRandomSampleGenerator();
        BeneValidation beneValidationBack = getBeneValidationRandomSampleGenerator();

        tradeEntity.addBeneValidation(beneValidationBack);
        assertThat(tradeEntity.getBeneValidations()).containsOnly(beneValidationBack);
        assertThat(beneValidationBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.removeBeneValidation(beneValidationBack);
        assertThat(tradeEntity.getBeneValidations()).doesNotContain(beneValidationBack);
        assertThat(beneValidationBack.getTradeEntity()).isNull();

        tradeEntity.beneValidations(new HashSet<>(Set.of(beneValidationBack)));
        assertThat(tradeEntity.getBeneValidations()).containsOnly(beneValidationBack);
        assertThat(beneValidationBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.setBeneValidations(new HashSet<>());
        assertThat(tradeEntity.getBeneValidations()).doesNotContain(beneValidationBack);
        assertThat(beneValidationBack.getTradeEntity()).isNull();
    }

    @Test
    void instaAlertTest() throws Exception {
        TradeEntity tradeEntity = getTradeEntityRandomSampleGenerator();
        InstaAlert instaAlertBack = getInstaAlertRandomSampleGenerator();

        tradeEntity.addInstaAlert(instaAlertBack);
        assertThat(tradeEntity.getInstaAlerts()).containsOnly(instaAlertBack);
        assertThat(instaAlertBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.removeInstaAlert(instaAlertBack);
        assertThat(tradeEntity.getInstaAlerts()).doesNotContain(instaAlertBack);
        assertThat(instaAlertBack.getTradeEntity()).isNull();

        tradeEntity.instaAlerts(new HashSet<>(Set.of(instaAlertBack)));
        assertThat(tradeEntity.getInstaAlerts()).containsOnly(instaAlertBack);
        assertThat(instaAlertBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.setInstaAlerts(new HashSet<>());
        assertThat(tradeEntity.getInstaAlerts()).doesNotContain(instaAlertBack);
        assertThat(instaAlertBack.getTradeEntity()).isNull();
    }

    @Test
    void fundsTransferTest() throws Exception {
        TradeEntity tradeEntity = getTradeEntityRandomSampleGenerator();
        FundsTransfer fundsTransferBack = getFundsTransferRandomSampleGenerator();

        tradeEntity.addFundsTransfer(fundsTransferBack);
        assertThat(tradeEntity.getFundsTransfers()).containsOnly(fundsTransferBack);
        assertThat(fundsTransferBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.removeFundsTransfer(fundsTransferBack);
        assertThat(tradeEntity.getFundsTransfers()).doesNotContain(fundsTransferBack);
        assertThat(fundsTransferBack.getTradeEntity()).isNull();

        tradeEntity.fundsTransfers(new HashSet<>(Set.of(fundsTransferBack)));
        assertThat(tradeEntity.getFundsTransfers()).containsOnly(fundsTransferBack);
        assertThat(fundsTransferBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.setFundsTransfers(new HashSet<>());
        assertThat(tradeEntity.getFundsTransfers()).doesNotContain(fundsTransferBack);
        assertThat(fundsTransferBack.getTradeEntity()).isNull();
    }

    @Test
    void updateVATest() throws Exception {
        TradeEntity tradeEntity = getTradeEntityRandomSampleGenerator();
        UpdateVA updateVABack = getUpdateVARandomSampleGenerator();

        tradeEntity.addUpdateVA(updateVABack);
        assertThat(tradeEntity.getUpdateVAS()).containsOnly(updateVABack);
        assertThat(updateVABack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.removeUpdateVA(updateVABack);
        assertThat(tradeEntity.getUpdateVAS()).doesNotContain(updateVABack);
        assertThat(updateVABack.getTradeEntity()).isNull();

        tradeEntity.updateVAS(new HashSet<>(Set.of(updateVABack)));
        assertThat(tradeEntity.getUpdateVAS()).containsOnly(updateVABack);
        assertThat(updateVABack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.setUpdateVAS(new HashSet<>());
        assertThat(tradeEntity.getUpdateVAS()).doesNotContain(updateVABack);
        assertThat(updateVABack.getTradeEntity()).isNull();
    }

    @Test
    void vANumberTest() throws Exception {
        TradeEntity tradeEntity = getTradeEntityRandomSampleGenerator();
        VANumber vANumberBack = getVANumberRandomSampleGenerator();

        tradeEntity.addVANumber(vANumberBack);
        assertThat(tradeEntity.getVANumbers()).containsOnly(vANumberBack);
        assertThat(vANumberBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.removeVANumber(vANumberBack);
        assertThat(tradeEntity.getVANumbers()).doesNotContain(vANumberBack);
        assertThat(vANumberBack.getTradeEntity()).isNull();

        tradeEntity.vANumbers(new HashSet<>(Set.of(vANumberBack)));
        assertThat(tradeEntity.getVANumbers()).containsOnly(vANumberBack);
        assertThat(vANumberBack.getTradeEntity()).isEqualTo(tradeEntity);

        tradeEntity.setVANumbers(new HashSet<>());
        assertThat(tradeEntity.getVANumbers()).doesNotContain(vANumberBack);
        assertThat(vANumberBack.getTradeEntity()).isNull();
    }
}
