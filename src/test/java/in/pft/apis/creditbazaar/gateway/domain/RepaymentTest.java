package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.CreditAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RepaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Repayment.class);
        Repayment repayment1 = getRepaymentSample1();
        Repayment repayment2 = new Repayment();
        assertThat(repayment1).isNotEqualTo(repayment2);

        repayment2.setId(repayment1.getId());
        assertThat(repayment1).isEqualTo(repayment2);

        repayment2 = getRepaymentSample2();
        assertThat(repayment1).isNotEqualTo(repayment2);
    }

    @Test
    void creditAccountDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        CreditAccountDetails creditAccountDetailsBack = getCreditAccountDetailsRandomSampleGenerator();

        repayment.addCreditAccountDetails(creditAccountDetailsBack);
        assertThat(repayment.getCreditAccountDetails()).containsOnly(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeCreditAccountDetails(creditAccountDetailsBack);
        assertThat(repayment.getCreditAccountDetails()).doesNotContain(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getRepayment()).isNull();

        repayment.creditAccountDetails(new HashSet<>(Set.of(creditAccountDetailsBack)));
        assertThat(repayment.getCreditAccountDetails()).containsOnly(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setCreditAccountDetails(new HashSet<>());
        assertThat(repayment.getCreditAccountDetails()).doesNotContain(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getRepayment()).isNull();
    }

    @Test
    void fundsTransferTransactionDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        FundsTransferTransactionDetails fundsTransferTransactionDetailsBack = getFundsTransferTransactionDetailsRandomSampleGenerator();

        repayment.addFundsTransferTransactionDetails(fundsTransferTransactionDetailsBack);
        assertThat(repayment.getFundsTransferTransactionDetails()).containsOnly(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeFundsTransferTransactionDetails(fundsTransferTransactionDetailsBack);
        assertThat(repayment.getFundsTransferTransactionDetails()).doesNotContain(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getRepayment()).isNull();

        repayment.fundsTransferTransactionDetails(new HashSet<>(Set.of(fundsTransferTransactionDetailsBack)));
        assertThat(repayment.getFundsTransferTransactionDetails()).containsOnly(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setFundsTransferTransactionDetails(new HashSet<>());
        assertThat(repayment.getFundsTransferTransactionDetails()).doesNotContain(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getRepayment()).isNull();
    }

    @Test
    void escrowTransactionDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        EscrowTransactionDetails escrowTransactionDetailsBack = getEscrowTransactionDetailsRandomSampleGenerator();

        repayment.addEscrowTransactionDetails(escrowTransactionDetailsBack);
        assertThat(repayment.getEscrowTransactionDetails()).containsOnly(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeEscrowTransactionDetails(escrowTransactionDetailsBack);
        assertThat(repayment.getEscrowTransactionDetails()).doesNotContain(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getRepayment()).isNull();

        repayment.escrowTransactionDetails(new HashSet<>(Set.of(escrowTransactionDetailsBack)));
        assertThat(repayment.getEscrowTransactionDetails()).containsOnly(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setEscrowTransactionDetails(new HashSet<>());
        assertThat(repayment.getEscrowTransactionDetails()).doesNotContain(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getRepayment()).isNull();
    }

    @Test
    void financerequestTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        repayment.setFinancerequest(financeRequestBack);
        assertThat(repayment.getFinancerequest()).isEqualTo(financeRequestBack);

        repayment.financerequest(null);
        assertThat(repayment.getFinancerequest()).isNull();
    }
}
