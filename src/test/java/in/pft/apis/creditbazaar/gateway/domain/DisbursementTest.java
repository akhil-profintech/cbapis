package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.CreditAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetailsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DisbursementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Disbursement.class);
        Disbursement disbursement1 = getDisbursementSample1();
        Disbursement disbursement2 = new Disbursement();
        assertThat(disbursement1).isNotEqualTo(disbursement2);

        disbursement2.setId(disbursement1.getId());
        assertThat(disbursement1).isEqualTo(disbursement2);

        disbursement2 = getDisbursementSample2();
        assertThat(disbursement1).isNotEqualTo(disbursement2);
    }

    @Test
    void creditAccountDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        CreditAccountDetails creditAccountDetailsBack = getCreditAccountDetailsRandomSampleGenerator();

        disbursement.addCreditAccountDetails(creditAccountDetailsBack);
        assertThat(disbursement.getCreditAccountDetails()).containsOnly(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeCreditAccountDetails(creditAccountDetailsBack);
        assertThat(disbursement.getCreditAccountDetails()).doesNotContain(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getDisbursement()).isNull();

        disbursement.creditAccountDetails(new HashSet<>(Set.of(creditAccountDetailsBack)));
        assertThat(disbursement.getCreditAccountDetails()).containsOnly(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setCreditAccountDetails(new HashSet<>());
        assertThat(disbursement.getCreditAccountDetails()).doesNotContain(creditAccountDetailsBack);
        assertThat(creditAccountDetailsBack.getDisbursement()).isNull();
    }

    @Test
    void fundsTransferTransactionDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        FundsTransferTransactionDetails fundsTransferTransactionDetailsBack = getFundsTransferTransactionDetailsRandomSampleGenerator();

        disbursement.addFundsTransferTransactionDetails(fundsTransferTransactionDetailsBack);
        assertThat(disbursement.getFundsTransferTransactionDetails()).containsOnly(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeFundsTransferTransactionDetails(fundsTransferTransactionDetailsBack);
        assertThat(disbursement.getFundsTransferTransactionDetails()).doesNotContain(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getDisbursement()).isNull();

        disbursement.fundsTransferTransactionDetails(new HashSet<>(Set.of(fundsTransferTransactionDetailsBack)));
        assertThat(disbursement.getFundsTransferTransactionDetails()).containsOnly(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setFundsTransferTransactionDetails(new HashSet<>());
        assertThat(disbursement.getFundsTransferTransactionDetails()).doesNotContain(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getDisbursement()).isNull();
    }

    @Test
    void escrowTransactionDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        EscrowTransactionDetails escrowTransactionDetailsBack = getEscrowTransactionDetailsRandomSampleGenerator();

        disbursement.addEscrowTransactionDetails(escrowTransactionDetailsBack);
        assertThat(disbursement.getEscrowTransactionDetails()).containsOnly(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeEscrowTransactionDetails(escrowTransactionDetailsBack);
        assertThat(disbursement.getEscrowTransactionDetails()).doesNotContain(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getDisbursement()).isNull();

        disbursement.escrowTransactionDetails(new HashSet<>(Set.of(escrowTransactionDetailsBack)));
        assertThat(disbursement.getEscrowTransactionDetails()).containsOnly(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setEscrowTransactionDetails(new HashSet<>());
        assertThat(disbursement.getEscrowTransactionDetails()).doesNotContain(escrowTransactionDetailsBack);
        assertThat(escrowTransactionDetailsBack.getDisbursement()).isNull();
    }

    @Test
    void financerequestTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        disbursement.setFinancerequest(financeRequestBack);
        assertThat(disbursement.getFinancerequest()).isEqualTo(financeRequestBack);

        disbursement.financerequest(null);
        assertThat(disbursement.getFinancerequest()).isNull();
    }

    @Test
    void financepartnerTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        FinancePartner financePartnerBack = getFinancePartnerRandomSampleGenerator();

        disbursement.setFinancepartner(financePartnerBack);
        assertThat(disbursement.getFinancepartner()).isEqualTo(financePartnerBack);

        disbursement.financepartner(null);
        assertThat(disbursement.getFinancepartner()).isNull();
    }
}
