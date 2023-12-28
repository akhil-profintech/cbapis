package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.EscrowAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EscrowAccountDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EscrowAccountDetails.class);
        EscrowAccountDetails escrowAccountDetails1 = getEscrowAccountDetailsSample1();
        EscrowAccountDetails escrowAccountDetails2 = new EscrowAccountDetails();
        assertThat(escrowAccountDetails1).isNotEqualTo(escrowAccountDetails2);

        escrowAccountDetails2.setId(escrowAccountDetails1.getId());
        assertThat(escrowAccountDetails1).isEqualTo(escrowAccountDetails2);

        escrowAccountDetails2 = getEscrowAccountDetailsSample2();
        assertThat(escrowAccountDetails1).isNotEqualTo(escrowAccountDetails2);
    }

    @Test
    void disbursementTest() throws Exception {
        EscrowAccountDetails escrowAccountDetails = getEscrowAccountDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        escrowAccountDetails.setDisbursement(disbursementBack);
        assertThat(escrowAccountDetails.getDisbursement()).isEqualTo(disbursementBack);

        escrowAccountDetails.disbursement(null);
        assertThat(escrowAccountDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        EscrowAccountDetails escrowAccountDetails = getEscrowAccountDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        escrowAccountDetails.setRepayment(repaymentBack);
        assertThat(escrowAccountDetails.getRepayment()).isEqualTo(repaymentBack);

        escrowAccountDetails.repayment(null);
        assertThat(escrowAccountDetails.getRepayment()).isNull();
    }
}
