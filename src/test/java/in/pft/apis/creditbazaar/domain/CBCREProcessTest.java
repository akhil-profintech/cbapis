package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CBCREProcessTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CREHighlightsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.CREObservationsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.IndividualAssessmentTestSamples.*;
import static in.pft.apis.creditbazaar.domain.RequestOfferTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
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
    void cREHighlightsTest() throws Exception {
        CBCREProcess cBCREProcess = getCBCREProcessRandomSampleGenerator();
        CREHighlights cREHighlightsBack = getCREHighlightsRandomSampleGenerator();

        cBCREProcess.addCREHighlights(cREHighlightsBack);
        assertThat(cBCREProcess.getCREHighlights()).containsOnly(cREHighlightsBack);
        assertThat(cREHighlightsBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.removeCREHighlights(cREHighlightsBack);
        assertThat(cBCREProcess.getCREHighlights()).doesNotContain(cREHighlightsBack);
        assertThat(cREHighlightsBack.getCbcreprocess()).isNull();

        cBCREProcess.cREHighlights(new HashSet<>(Set.of(cREHighlightsBack)));
        assertThat(cBCREProcess.getCREHighlights()).containsOnly(cREHighlightsBack);
        assertThat(cREHighlightsBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.setCREHighlights(new HashSet<>());
        assertThat(cBCREProcess.getCREHighlights()).doesNotContain(cREHighlightsBack);
        assertThat(cREHighlightsBack.getCbcreprocess()).isNull();
    }

    @Test
    void cREObservationsTest() throws Exception {
        CBCREProcess cBCREProcess = getCBCREProcessRandomSampleGenerator();
        CREObservations cREObservationsBack = getCREObservationsRandomSampleGenerator();

        cBCREProcess.addCREObservations(cREObservationsBack);
        assertThat(cBCREProcess.getCREObservations()).containsOnly(cREObservationsBack);
        assertThat(cREObservationsBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.removeCREObservations(cREObservationsBack);
        assertThat(cBCREProcess.getCREObservations()).doesNotContain(cREObservationsBack);
        assertThat(cREObservationsBack.getCbcreprocess()).isNull();

        cBCREProcess.cREObservations(new HashSet<>(Set.of(cREObservationsBack)));
        assertThat(cBCREProcess.getCREObservations()).containsOnly(cREObservationsBack);
        assertThat(cREObservationsBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.setCREObservations(new HashSet<>());
        assertThat(cBCREProcess.getCREObservations()).doesNotContain(cREObservationsBack);
        assertThat(cREObservationsBack.getCbcreprocess()).isNull();
    }

    @Test
    void requestOfferTest() throws Exception {
        CBCREProcess cBCREProcess = getCBCREProcessRandomSampleGenerator();
        RequestOffer requestOfferBack = getRequestOfferRandomSampleGenerator();

        cBCREProcess.addRequestOffer(requestOfferBack);
        assertThat(cBCREProcess.getRequestOffers()).containsOnly(requestOfferBack);
        assertThat(requestOfferBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.removeRequestOffer(requestOfferBack);
        assertThat(cBCREProcess.getRequestOffers()).doesNotContain(requestOfferBack);
        assertThat(requestOfferBack.getCbcreprocess()).isNull();

        cBCREProcess.requestOffers(new HashSet<>(Set.of(requestOfferBack)));
        assertThat(cBCREProcess.getRequestOffers()).containsOnly(requestOfferBack);
        assertThat(requestOfferBack.getCbcreprocess()).isEqualTo(cBCREProcess);

        cBCREProcess.setRequestOffers(new HashSet<>());
        assertThat(cBCREProcess.getRequestOffers()).doesNotContain(requestOfferBack);
        assertThat(requestOfferBack.getCbcreprocess()).isNull();
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
}
