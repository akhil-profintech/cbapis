package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CBCREProcessTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CREHighlightsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CREObservationsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.IndividualAssessmentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class IndividualAssessmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndividualAssessment.class);
        IndividualAssessment individualAssessment1 = getIndividualAssessmentSample1();
        IndividualAssessment individualAssessment2 = new IndividualAssessment();
        assertThat(individualAssessment1).isNotEqualTo(individualAssessment2);

        individualAssessment2.setId(individualAssessment1.getId());
        assertThat(individualAssessment1).isEqualTo(individualAssessment2);

        individualAssessment2 = getIndividualAssessmentSample2();
        assertThat(individualAssessment1).isNotEqualTo(individualAssessment2);
    }

    @Test
    void cREHighlightsTest() throws Exception {
        IndividualAssessment individualAssessment = getIndividualAssessmentRandomSampleGenerator();
        CREHighlights cREHighlightsBack = getCREHighlightsRandomSampleGenerator();

        individualAssessment.addCREHighlights(cREHighlightsBack);
        assertThat(individualAssessment.getCREHighlights()).containsOnly(cREHighlightsBack);
        assertThat(cREHighlightsBack.getIndividualassessment()).isEqualTo(individualAssessment);

        individualAssessment.removeCREHighlights(cREHighlightsBack);
        assertThat(individualAssessment.getCREHighlights()).doesNotContain(cREHighlightsBack);
        assertThat(cREHighlightsBack.getIndividualassessment()).isNull();

        individualAssessment.cREHighlights(new HashSet<>(Set.of(cREHighlightsBack)));
        assertThat(individualAssessment.getCREHighlights()).containsOnly(cREHighlightsBack);
        assertThat(cREHighlightsBack.getIndividualassessment()).isEqualTo(individualAssessment);

        individualAssessment.setCREHighlights(new HashSet<>());
        assertThat(individualAssessment.getCREHighlights()).doesNotContain(cREHighlightsBack);
        assertThat(cREHighlightsBack.getIndividualassessment()).isNull();
    }

    @Test
    void cREObservationsTest() throws Exception {
        IndividualAssessment individualAssessment = getIndividualAssessmentRandomSampleGenerator();
        CREObservations cREObservationsBack = getCREObservationsRandomSampleGenerator();

        individualAssessment.addCREObservations(cREObservationsBack);
        assertThat(individualAssessment.getCREObservations()).containsOnly(cREObservationsBack);
        assertThat(cREObservationsBack.getIndividualassessment()).isEqualTo(individualAssessment);

        individualAssessment.removeCREObservations(cREObservationsBack);
        assertThat(individualAssessment.getCREObservations()).doesNotContain(cREObservationsBack);
        assertThat(cREObservationsBack.getIndividualassessment()).isNull();

        individualAssessment.cREObservations(new HashSet<>(Set.of(cREObservationsBack)));
        assertThat(individualAssessment.getCREObservations()).containsOnly(cREObservationsBack);
        assertThat(cREObservationsBack.getIndividualassessment()).isEqualTo(individualAssessment);

        individualAssessment.setCREObservations(new HashSet<>());
        assertThat(individualAssessment.getCREObservations()).doesNotContain(cREObservationsBack);
        assertThat(cREObservationsBack.getIndividualassessment()).isNull();
    }

    @Test
    void cbcreprocessTest() throws Exception {
        IndividualAssessment individualAssessment = getIndividualAssessmentRandomSampleGenerator();
        CBCREProcess cBCREProcessBack = getCBCREProcessRandomSampleGenerator();

        individualAssessment.setCbcreprocess(cBCREProcessBack);
        assertThat(individualAssessment.getCbcreprocess()).isEqualTo(cBCREProcessBack);

        individualAssessment.cbcreprocess(null);
        assertThat(individualAssessment.getCbcreprocess()).isNull();
    }
}
