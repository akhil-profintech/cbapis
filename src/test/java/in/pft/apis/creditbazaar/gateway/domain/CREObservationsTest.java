package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.CREObservationsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.IndividualAssessmentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CREObservationsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CREObservations.class);
        CREObservations cREObservations1 = getCREObservationsSample1();
        CREObservations cREObservations2 = new CREObservations();
        assertThat(cREObservations1).isNotEqualTo(cREObservations2);

        cREObservations2.setId(cREObservations1.getId());
        assertThat(cREObservations1).isEqualTo(cREObservations2);

        cREObservations2 = getCREObservationsSample2();
        assertThat(cREObservations1).isNotEqualTo(cREObservations2);
    }

    @Test
    void individualassessmentTest() throws Exception {
        CREObservations cREObservations = getCREObservationsRandomSampleGenerator();
        IndividualAssessment individualAssessmentBack = getIndividualAssessmentRandomSampleGenerator();

        cREObservations.setIndividualassessment(individualAssessmentBack);
        assertThat(cREObservations.getIndividualassessment()).isEqualTo(individualAssessmentBack);

        cREObservations.individualassessment(null);
        assertThat(cREObservations.getIndividualassessment()).isNull();
    }
}