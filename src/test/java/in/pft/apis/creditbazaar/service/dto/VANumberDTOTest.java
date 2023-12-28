package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VANumberDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(VANumberDTO.class);
        VANumberDTO vANumberDTO1 = new VANumberDTO();
        vANumberDTO1.setId(1L);
        VANumberDTO vANumberDTO2 = new VANumberDTO();
        assertThat(vANumberDTO1).isNotEqualTo(vANumberDTO2);
        vANumberDTO2.setId(vANumberDTO1.getId());
        assertThat(vANumberDTO1).isEqualTo(vANumberDTO2);
        vANumberDTO2.setId(2L);
        assertThat(vANumberDTO1).isNotEqualTo(vANumberDTO2);
        vANumberDTO1.setId(null);
        assertThat(vANumberDTO1).isNotEqualTo(vANumberDTO2);
    }
}
