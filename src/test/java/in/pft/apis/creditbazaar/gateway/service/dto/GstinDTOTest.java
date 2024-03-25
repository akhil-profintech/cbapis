package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class GstinDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GstinDTO.class);
        GstinDTO gstinDTO1 = new GstinDTO();
        gstinDTO1.setId(1L);
        GstinDTO gstinDTO2 = new GstinDTO();
        assertThat(gstinDTO1).isNotEqualTo(gstinDTO2);
        gstinDTO2.setId(gstinDTO1.getId());
        assertThat(gstinDTO1).isEqualTo(gstinDTO2);
        gstinDTO2.setId(2L);
        assertThat(gstinDTO1).isNotEqualTo(gstinDTO2);
        gstinDTO1.setId(null);
        assertThat(gstinDTO1).isNotEqualTo(gstinDTO2);
    }
}
