package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserDtlsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserDtlsDTO.class);
        UserDtlsDTO userDtlsDTO1 = new UserDtlsDTO();
        userDtlsDTO1.setId(1L);
        UserDtlsDTO userDtlsDTO2 = new UserDtlsDTO();
        assertThat(userDtlsDTO1).isNotEqualTo(userDtlsDTO2);
        userDtlsDTO2.setId(userDtlsDTO1.getId());
        assertThat(userDtlsDTO1).isEqualTo(userDtlsDTO2);
        userDtlsDTO2.setId(2L);
        assertThat(userDtlsDTO1).isNotEqualTo(userDtlsDTO2);
        userDtlsDTO1.setId(null);
        assertThat(userDtlsDTO1).isNotEqualTo(userDtlsDTO2);
    }
}
