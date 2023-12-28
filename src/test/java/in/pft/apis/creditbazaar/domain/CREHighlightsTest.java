package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CBCREProcessTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CREHighlightsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.IndividualAssessmentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CREHighlightsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CREHighlights.class);
        CREHighlights cREHighlights1 = getCREHighlightsSample1();
        CREHighlights cREHighlights2 = new CREHighlights();
        assertThat(cREHighlights1).isNotEqualTo(cREHighlights2);

        cREHighlights2.setId(cREHighlights1.getId());
        assertThat(cREHighlights1).isEqualTo(cREHighlights2);

        cREHighlights2 = getCREHighlightsSample2();
        assertThat(cREHighlights1).isNotEqualTo(cREHighlights2);
    }

    @Test
    void cbcreprocessTest() throws Exception {
        CREHighlights cREHighlights = getCREHighlightsRandomSampleGenerator();
        CBCREProcess cBCREProcessBack = getCBCREProcessRandomSampleGenerator();

        cREHighlights.setCbcreprocess(cBCREProcessBack);
        assertThat(cREHighlights.getCbcreprocess()).isEqualTo(cBCREProcessBack);

        cREHighlights.cbcreprocess(null);
        assertThat(cREHighlights.getCbcreprocess()).isNull();
    }

    @Test
    void individualassessmentTest() throws Exception {
        CREHighlights cREHighlights = getCREHighlightsRandomSampleGenerator();
        IndividualAssessment individualAssessmentBack = getIndividualAssessmentRandomSampleGenerator();

        cREHighlights.setIndividualassessment(individualAssessmentBack);
        assertThat(cREHighlights.getIndividualassessment()).isEqualTo(individualAssessmentBack);

        cREHighlights.individualassessment(null);
        assertThat(cREHighlights.getIndividualassessment()).isNull();
    }
}
