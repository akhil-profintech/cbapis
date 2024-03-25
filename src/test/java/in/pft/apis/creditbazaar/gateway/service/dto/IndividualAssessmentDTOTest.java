package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IndividualAssessmentDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndividualAssessmentDTO.class);
        IndividualAssessmentDTO individualAssessmentDTO1 = new IndividualAssessmentDTO();
        individualAssessmentDTO1.setId(1L);
        IndividualAssessmentDTO individualAssessmentDTO2 = new IndividualAssessmentDTO();
        assertThat(individualAssessmentDTO1).isNotEqualTo(individualAssessmentDTO2);
        individualAssessmentDTO2.setId(individualAssessmentDTO1.getId());
        assertThat(individualAssessmentDTO1).isEqualTo(individualAssessmentDTO2);
        individualAssessmentDTO2.setId(2L);
        assertThat(individualAssessmentDTO1).isNotEqualTo(individualAssessmentDTO2);
        individualAssessmentDTO1.setId(null);
        assertThat(individualAssessmentDTO1).isNotEqualTo(individualAssessmentDTO2);
    }
}
