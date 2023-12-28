package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.DisbursementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.DocDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.ParticipantSettlementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RepaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocDetails.class);
        DocDetails docDetails1 = getDocDetailsSample1();
        DocDetails docDetails2 = new DocDetails();
        assertThat(docDetails1).isNotEqualTo(docDetails2);

        docDetails2.setId(docDetails1.getId());
        assertThat(docDetails1).isEqualTo(docDetails2);

        docDetails2 = getDocDetailsSample2();
        assertThat(docDetails1).isNotEqualTo(docDetails2);
    }

    @Test
    void disbursementTest() throws Exception {
        DocDetails docDetails = getDocDetailsRandomSampleGenerator();
        Disbursement disbursementBack = getDisbursementRandomSampleGenerator();

        docDetails.setDisbursement(disbursementBack);
        assertThat(docDetails.getDisbursement()).isEqualTo(disbursementBack);

        docDetails.disbursement(null);
        assertThat(docDetails.getDisbursement()).isNull();
    }

    @Test
    void repaymentTest() throws Exception {
        DocDetails docDetails = getDocDetailsRandomSampleGenerator();
        Repayment repaymentBack = getRepaymentRandomSampleGenerator();

        docDetails.setRepayment(repaymentBack);
        assertThat(docDetails.getRepayment()).isEqualTo(repaymentBack);

        docDetails.repayment(null);
        assertThat(docDetails.getRepayment()).isNull();
    }

    @Test
    void participantsettlementTest() throws Exception {
        DocDetails docDetails = getDocDetailsRandomSampleGenerator();
        ParticipantSettlement participantSettlementBack = getParticipantSettlementRandomSampleGenerator();

        docDetails.setParticipantsettlement(participantSettlementBack);
        assertThat(docDetails.getParticipantsettlement()).isEqualTo(participantSettlementBack);

        docDetails.participantsettlement(null);
        assertThat(docDetails.getParticipantsettlement()).isNull();
    }
}
