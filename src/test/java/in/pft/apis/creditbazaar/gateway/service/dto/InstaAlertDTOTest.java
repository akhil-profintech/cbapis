package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InstaAlertDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InstaAlertDTO.class);
        InstaAlertDTO instaAlertDTO1 = new InstaAlertDTO();
        instaAlertDTO1.setId(1L);
        InstaAlertDTO instaAlertDTO2 = new InstaAlertDTO();
        assertThat(instaAlertDTO1).isNotEqualTo(instaAlertDTO2);
        instaAlertDTO2.setId(instaAlertDTO1.getId());
        assertThat(instaAlertDTO1).isEqualTo(instaAlertDTO2);
        instaAlertDTO2.setId(2L);
        assertThat(instaAlertDTO1).isNotEqualTo(instaAlertDTO2);
        instaAlertDTO1.setId(null);
        assertThat(instaAlertDTO1).isNotEqualTo(instaAlertDTO2);
    }
}
