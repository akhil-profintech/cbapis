package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FTTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.ParticipantSettlementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FTTransactionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FTTransactionDetails.class);
        FTTransactionDetails fTTransactionDetails1 = getFTTransactionDetailsSample1();
        FTTransactionDetails fTTransactionDetails2 = new FTTransactionDetails();
        assertThat(fTTransactionDetails1).isNotEqualTo(fTTransactionDetails2);

        fTTransactionDetails2.setId(fTTransactionDetails1.getId());
        assertThat(fTTransactionDetails1).isEqualTo(fTTransactionDetails2);

        fTTransactionDetails2 = getFTTransactionDetailsSample2();
        assertThat(fTTransactionDetails1).isNotEqualTo(fTTransactionDetails2);
    }

    @Test
    void disbursementTest() throws Exception {
        FTTransactionDetails fTTransactionDetails = getFTTransactionDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        fTTransactionDetails.setDisbursement(disbursementBack);
        assertThat(fTTransactionDetails.getDisbursement()).isEqualTo(disbursementBack);

        fTTransactionDetails.disbursement(null);
        assertThat(fTTransactionDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        FTTransactionDetails fTTransactionDetails = getFTTransactionDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        fTTransactionDetails.setRepayment(repaymentBack);
        assertThat(fTTransactionDetails.getRepayment()).isEqualTo(repaymentBack);

        fTTransactionDetails.repayment(null);
        assertThat(fTTransactionDetails.getRepayment()).isNull();
    }

    @Test
    void participantsettlementTest() throws Exception {
        FTTransactionDetails fTTransactionDetails = getFTTransactionDetailsRandomSampleGenerator();
        ParticipantSettlement participantSettlementBack = getParticipantSettlementRandomSampleGenerator();

        fTTransactionDetails.setParticipantsettlement(participantSettlementBack);
        assertThat(fTTransactionDetails.getParticipantsettlement()).isEqualTo(participantSettlementBack);

        fTTransactionDetails.participantsettlement(null);
        assertThat(fTTransactionDetails.getParticipantsettlement()).isNull();
    }
}
