package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CREObservationsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CREObservationsDTO.class);
        CREObservationsDTO cREObservationsDTO1 = new CREObservationsDTO();
        cREObservationsDTO1.setId(1L);
        CREObservationsDTO cREObservationsDTO2 = new CREObservationsDTO();
        assertThat(cREObservationsDTO1).isNotEqualTo(cREObservationsDTO2);
        cREObservationsDTO2.setId(cREObservationsDTO1.getId());
        assertThat(cREObservationsDTO1).isEqualTo(cREObservationsDTO2);
        cREObservationsDTO2.setId(2L);
        assertThat(cREObservationsDTO1).isNotEqualTo(cREObservationsDTO2);
        cREObservationsDTO1.setId(null);
        assertThat(cREObservationsDTO1).isNotEqualTo(cREObservationsDTO2);
    }
}
