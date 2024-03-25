package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EscrowTransactionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EscrowTransactionDetails.class);
        EscrowTransactionDetails escrowTransactionDetails1 = getEscrowTransactionDetailsSample1();
        EscrowTransactionDetails escrowTransactionDetails2 = new EscrowTransactionDetails();
        assertThat(escrowTransactionDetails1).isNotEqualTo(escrowTransactionDetails2);

        escrowTransactionDetails2.setId(escrowTransactionDetails1.getId());
        assertThat(escrowTransactionDetails1).isEqualTo(escrowTransactionDetails2);

        escrowTransactionDetails2 = getEscrowTransactionDetailsSample2();
        assertThat(escrowTransactionDetails1).isNotEqualTo(escrowTransactionDetails2);
    }

    @Test
    void disbursementTest() throws Exception {
        EscrowTransactionDetails escrowTransactionDetails = getEscrowTransactionDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        escrowTransactionDetails.setDisbursement(disbursementBack);
        assertThat(escrowTransactionDetails.getDisbursement()).isEqualTo(disbursementBack);

        escrowTransactionDetails.disbursement(null);
        assertThat(escrowTransactionDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        EscrowTransactionDetails escrowTransactionDetails = getEscrowTransactionDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        escrowTransactionDetails.setRepayment(repaymentBack);
        assertThat(escrowTransactionDetails.getRepayment()).isEqualTo(repaymentBack);

        escrowTransactionDetails.repayment(null);
        assertThat(escrowTransactionDetails.getRepayment()).isNull();
    }
}
