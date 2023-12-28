package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CollectionTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CollectionTransactionDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CollectionTransactionDetails.class);
        CollectionTransactionDetails collectionTransactionDetails1 = getCollectionTransactionDetailsSample1();
        CollectionTransactionDetails collectionTransactionDetails2 = new CollectionTransactionDetails();
        assertThat(collectionTransactionDetails1).isNotEqualTo(collectionTransactionDetails2);

        collectionTransactionDetails2.setId(collectionTransactionDetails1.getId());
        assertThat(collectionTransactionDetails1).isEqualTo(collectionTransactionDetails2);

        collectionTransactionDetails2 = getCollectionTransactionDetailsSample2();
        assertThat(collectionTransactionDetails1).isNotEqualTo(collectionTransactionDetails2);
    }

    @Test
    void disbursementTest() throws Exception {
        CollectionTransactionDetails collectionTransactionDetails = getCollectionTransactionDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        collectionTransactionDetails.setDisbursement(disbursementBack);
        assertThat(collectionTransactionDetails.getDisbursement()).isEqualTo(disbursementBack);

        collectionTransactionDetails.disbursement(null);
        assertThat(collectionTransactionDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        CollectionTransactionDetails collectionTransactionDetails = getCollectionTransactionDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        collectionTransactionDetails.setRepayment(repaymentBack);
        assertThat(collectionTransactionDetails.getRepayment()).isEqualTo(repaymentBack);

        collectionTransactionDetails.repayment(null);
        assertThat(collectionTransactionDetails.getRepayment()).isNull();
    }
}
