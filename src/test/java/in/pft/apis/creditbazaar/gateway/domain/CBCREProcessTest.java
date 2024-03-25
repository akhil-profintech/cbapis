package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.CBCREProcessTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.IndividualAssessmentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class CBCREProcessTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CBCREProcess.class);
        CBCREProcess cBCREProcess1 = getCBCREProcessSample1();
        CBCREProcess cBCREProcess2 = new CBCREProcess();
        assertThat(cBCREProcess1).isNotEqualTo(cBCREProcess2);

        cBCREProcess2.setId(cBCREProcess1.getId());
        assertThat(cBCREProcess1).isEqualTo(cBCREProcess2);

        cBCREProcess2 = getCBCREProcessSample2();
        assertThat(cBCREProcess1).isNotEqualTo(cBCREProcess2);
    }

    @Test
    void individualAssessmentTest() throws Exception {
        CBCREProcess cBCREProcess = getCBCREProcessRandomSampleGenerator();
        IndividualAssessment individualAssessmentBack = getIndividualAssessmentRandomSampleGenerator();

        cBCREProcess.addIndividualAssessment(individualAssessmentBack);
        assertThat(cBCREProcess.getIndividualAssessments()).containsOnly(individualAssessmentBack);
        assertThat(individualAssessmentBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.removeIndividualAssessment(individualAssessmentBack);
        assertThat(cBCREProcess.getIndividualAssessments()).doesNotContain(individualAssessmentBack);
        assertThat(individualAssessmentBack.getCbcreprocess()).isNull();

        cBCREProcess.individualAssessments(new HashSet<>(Set.of(individualAssessmentBack)));
        assertThat(cBCREProcess.getIndividualAssessments()).containsOnly(individualAssessmentBack);
        assertThat(individualAssessmentBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.setIndividualAssessments(new HashSet<>());
        assertThat(cBCREProcess.getIndividualAssessments()).doesNotContain(individualAssessmentBack);
        assertThat(individualAssessmentBack.getCbcreprocess()).isNull();
    }

    @Test
    void financeRequestTest() throws Exception {
        CBCREProcess cBCREProcess = getCBCREProcessRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        cBCREProcess.setFinanceRequest(financeRequestBack);
        assertThat(cBCREProcess.getFinanceRequest()).isEqualTo(financeRequestBack);

        cBCREProcess.financeRequest(null);
        assertThat(cBCREProcess.getFinanceRequest()).isNull();
    }
}
