package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CollectionTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CreditAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.DocDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.EscrowAccountDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FTTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FinanceRequestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
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
    void escrowAccountDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        EscrowAccountDetails escrowAccountDetailsBack = getEscrowAccountDetailsRandomSampleGenerator();

        disbursement.addEscrowAccountDetails(escrowAccountDetailsBack);
        assertThat(disbursement.getEscrowAccountDetails()).containsOnly(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeEscrowAccountDetails(escrowAccountDetailsBack);
        assertThat(disbursement.getEscrowAccountDetails()).doesNotContain(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getDisbursement()).isNull();

        disbursement.escrowAccountDetails(new HashSet<>(Set.of(escrowAccountDetailsBack)));
        assertThat(disbursement.getEscrowAccountDetails()).containsOnly(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setEscrowAccountDetails(new HashSet<>());
        assertThat(disbursement.getEscrowAccountDetails()).doesNotContain(escrowAccountDetailsBack);
        assertThat(escrowAccountDetailsBack.getDisbursement()).isNull();
    }

    @Test
    void docDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        DocDetails docDetailsBack = getDocDetailsRandomSampleGenerator();

        disbursement.addDocDetails(docDetailsBack);
        assertThat(disbursement.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeDocDetails(docDetailsBack);
        assertThat(disbursement.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getDisbursement()).isNull();

        disbursement.docDetails(new HashSet<>(Set.of(docDetailsBack)));
        assertThat(disbursement.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setDocDetails(new HashSet<>());
        assertThat(disbursement.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getDisbursement()).isNull();
    }

    @Test
    void fTTransactionDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        FTTransactionDetails fTTransactionDetailsBack = getFTTransactionDetailsRandomSampleGenerator();

        disbursement.addFTTransactionDetails(fTTransactionDetailsBack);
        assertThat(disbursement.getFTTransactionDetails()).containsOnly(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeFTTransactionDetails(fTTransactionDetailsBack);
        assertThat(disbursement.getFTTransactionDetails()).doesNotContain(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getDisbursement()).isNull();

        disbursement.fTTransactionDetails(new HashSet<>(Set.of(fTTransactionDetailsBack)));
        assertThat(disbursement.getFTTransactionDetails()).containsOnly(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setFTTransactionDetails(new HashSet<>());
        assertThat(disbursement.getFTTransactionDetails()).doesNotContain(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getDisbursement()).isNull();
    }

    @Test
    void collectionTransactionDetailsTest() throws Exception {
        Disbursement disbursement = getDisbursementRandomSampleGenerator();
        CollectionTransactionDetails collectionTransactionDetailsBack = getCollectionTransactionDetailsRandomSampleGenerator();

        disbursement.addCollectionTransactionDetails(collectionTransactionDetailsBack);
        assertThat(disbursement.getCollectionTransactionDetails()).containsOnly(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.removeCollectionTransactionDetails(collectionTransactionDetailsBack);
        assertThat(disbursement.getCollectionTransactionDetails()).doesNotContain(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getDisbursement()).isNull();

        disbursement.collectionTransactionDetails(new HashSet<>(Set.of(collectionTransactionDetailsBack)));
        assertThat(disbursement.getCollectionTransactionDetails()).containsOnly(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getDisbursement()).isEqualTo(disbursement);

        disbursement.setCollectionTransactionDetails(new HashSet<>());
        assertThat(disbursement.getCollectionTransactionDetails()).doesNotContain(collectionTransactionDetailsBack);
        assertThat(collectionTransactionDetailsBack.getDisbursement()).isNull();
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
