package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProspectRequestDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProspectRequestDTO.class);
        ProspectRequestDTO prospectRequestDTO1 = new ProspectRequestDTO();
        prospectRequestDTO1.setId(1L);
        ProspectRequestDTO prospectRequestDTO2 = new ProspectRequestDTO();
        assertThat(prospectRequestDTO1).isNotEqualTo(prospectRequestDTO2);
        prospectRequestDTO2.setId(prospectRequestDTO1.getId());
        assertThat(prospectRequestDTO1).isEqualTo(prospectRequestDTO2);
        prospectRequestDTO2.setId(2L);
        assertThat(prospectRequestDTO1).isNotEqualTo(prospectRequestDTO2);
        prospectRequestDTO1.setId(null);
        assertThat(prospectRequestDTO1).isNotEqualTo(prospectRequestDTO2);
    }
}
