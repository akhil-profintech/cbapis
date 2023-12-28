package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UpdateVADTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UpdateVADTO.class);
        UpdateVADTO updateVADTO1 = new UpdateVADTO();
        updateVADTO1.setId(1L);
        UpdateVADTO updateVADTO2 = new UpdateVADTO();
        assertThat(updateVADTO1).isNotEqualTo(updateVADTO2);
        updateVADTO2.setId(updateVADTO1.getId());
        assertThat(updateVADTO1).isEqualTo(updateVADTO2);
        updateVADTO2.setId(2L);
        assertThat(updateVADTO1).isNotEqualTo(updateVADTO2);
        updateVADTO1.setId(null);
        assertThat(updateVADTO1).isNotEqualTo(updateVADTO2);
    }
}
