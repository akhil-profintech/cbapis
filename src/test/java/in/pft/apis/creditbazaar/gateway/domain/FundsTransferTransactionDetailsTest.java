package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsTransferTransactionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundsTransferTransactionDetails.class);
        FundsTransferTransactionDetails fundsTransferTransactionDetails1 = getFundsTransferTransactionDetailsSample1();
        FundsTransferTransactionDetails fundsTransferTransactionDetails2 = new FundsTransferTransactionDetails();
        assertThat(fundsTransferTransactionDetails1).isNotEqualTo(fundsTransferTransactionDetails2);

        fundsTransferTransactionDetails2.setId(fundsTransferTransactionDetails1.getId());
        assertThat(fundsTransferTransactionDetails1).isEqualTo(fundsTransferTransactionDetails2);

        fundsTransferTransactionDetails2 = getFundsTransferTransactionDetailsSample2();
        assertThat(fundsTransferTransactionDetails1).isNotEqualTo(fundsTransferTransactionDetails2);
    }

    @Test
    void participantsettlementTest() throws Exception {
        FundsTransferTransactionDetails fundsTransferTransactionDetails = getFundsTransferTransactionDetailsRandomSampleGenerator();
        ParticipantSettlement participantSettlementBack = getParticipantSettlementRandomSampleGenerator();

        fundsTransferTransactionDetails.setParticipantsettlement(participantSettlementBack);
        assertThat(fundsTransferTransactionDetails.getParticipantsettlement()).isEqualTo(participantSettlementBack);

        fundsTransferTransactionDetails.participantsettlement(null);
        assertThat(fundsTransferTransactionDetails.getParticipantsettlement()).isNull();
    }

    @Test
    void disbursementTest() throws Exception {
        FundsTransferTransactionDetails fundsTransferTransactionDetails = getFundsTransferTransactionDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        fundsTransferTransactionDetails.setDisbursement(disbursementBack);
        assertThat(fundsTransferTransactionDetails.getDisbursement()).isEqualTo(disbursementBack);

        fundsTransferTransactionDetails.disbursement(null);
        assertThat(fundsTransferTransactionDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        FundsTransferTransactionDetails fundsTransferTransactionDetails = getFundsTransferTransactionDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        fundsTransferTransactionDetails.setRepayment(repaymentBack);
        assertThat(fundsTransferTransactionDetails.getRepayment()).isEqualTo(repaymentBack);

        fundsTransferTransactionDetails.repayment(null);
        assertThat(fundsTransferTransactionDetails.getRepayment()).isNull();
    }
}
