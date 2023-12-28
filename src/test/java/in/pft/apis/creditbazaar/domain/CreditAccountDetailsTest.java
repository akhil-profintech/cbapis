package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CreditAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditAccountDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditAccountDetails.class);
        CreditAccountDetails creditAccountDetails1 = getCreditAccountDetailsSample1();
        CreditAccountDetails creditAccountDetails2 = new CreditAccountDetails();
        assertThat(creditAccountDetails1).isNotEqualTo(creditAccountDetails2);

        creditAccountDetails2.setId(creditAccountDetails1.getId());
        assertThat(creditAccountDetails1).isEqualTo(creditAccountDetails2);

        creditAccountDetails2 = getCreditAccountDetailsSample2();
        assertThat(creditAccountDetails1).isNotEqualTo(creditAccountDetails2);
    }

    @Test
    void disbursementTest() throws Exception {
        CreditAccountDetails creditAccountDetails = getCreditAccountDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        creditAccountDetails.setDisbursement(disbursementBack);
        assertThat(creditAccountDetails.getDisbursement()).isEqualTo(disbursementBack);

        creditAccountDetails.disbursement(null);
        assertThat(creditAccountDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        CreditAccountDetails creditAccountDetails = getCreditAccountDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        creditAccountDetails.setRepayment(repaymentBack);
        assertThat(creditAccountDetails.getRepayment()).isEqualTo(repaymentBack);

        creditAccountDetails.repayment(null);
        assertThat(creditAccountDetails.getRepayment()).isNull();
    }
}
