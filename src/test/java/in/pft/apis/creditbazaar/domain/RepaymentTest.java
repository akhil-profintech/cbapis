package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CollectionTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CreditAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.DocDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.EscrowAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FTTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
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
    void escrowAccountDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        EscrowAccountDetails escrowAccountDetailsBack = getEscrowAccountDetailsRandomSampleGenerator();

        repayment.addEscrowAccountDetails(escrowAccountDetailsBack);
        assertThat(repayment.getEscrowAccountDetails()).containsOnly(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeEscrowAccountDetails(escrowAccountDetailsBack);
        assertThat(repayment.getEscrowAccountDetails()).doesNotContain(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getRepayment()).isNull();

        repayment.escrowAccountDetails(new HashSet<>(Set.of(escrowAccountDetailsBack)));
        assertThat(repayment.getEscrowAccountDetails()).containsOnly(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setEscrowAccountDetails(new HashSet<>());
        assertThat(repayment.getEscrowAccountDetails()).doesNotContain(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getRepayment()).isNull();
    }

    @Test
    void docDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        DocDetails docDetailsBack = getDocDetailsRandomSampleGenerator();

        repayment.addDocDetails(docDetailsBack);
        assertThat(repayment.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeDocDetails(docDetailsBack);
        assertThat(repayment.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getRepayment()).isNull();

        repayment.docDetails(new HashSet<>(Set.of(docDetailsBack)));
        assertThat(repayment.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setDocDetails(new HashSet<>());
        assertThat(repayment.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getRepayment()).isNull();
    }

    @Test
    void fTTransactionDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        FTTransactionDetails fTTransactionDetailsBack = getFTTransactionDetailsRandomSampleGenerator();

        repayment.addFTTransactionDetails(fTTransactionDetailsBack);
        assertThat(repayment.getFTTransactionDetails()).containsOnly(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeFTTransactionDetails(fTTransactionDetailsBack);
        assertThat(repayment.getFTTransactionDetails()).doesNotContain(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getRepayment()).isNull();

        repayment.fTTransactionDetails(new HashSet<>(Set.of(fTTransactionDetailsBack)));
        assertThat(repayment.getFTTransactionDetails()).containsOnly(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setFTTransactionDetails(new HashSet<>());
        assertThat(repayment.getFTTransactionDetails()).doesNotContain(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getRepayment()).isNull();
    }

    @Test
    void collectionTransactionDetailsTest() throws Exception {
        Repayment repayment = getRepaymentRandomSampleGenerator();
        CollectionTransactionDetails collectionTransactionDetailsBack = getCollectionTransactionDetailsRandomSampleGenerator();

        repayment.addCollectionTransactionDetails(collectionTransactionDetailsBack);
        assertThat(repayment.getCollectionTransactionDetails()).containsOnly(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.removeCollectionTransactionDetails(collectionTransactionDetailsBack);
        assertThat(repayment.getCollectionTransactionDetails()).doesNotContain(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getRepayment()).isNull();

        repayment.collectionTransactionDetails(new HashSet<>(Set.of(collectionTransactionDetailsBack)));
        assertThat(repayment.getCollectionTransactionDetails()).containsOnly(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getRepayment()).isEqualTo(repayment);

        repayment.setCollectionTransactionDetails(new HashSet<>());
        assertThat(repayment.getCollectionTransactionDetails()).doesNotContain(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getRepayment()).isNull();
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
