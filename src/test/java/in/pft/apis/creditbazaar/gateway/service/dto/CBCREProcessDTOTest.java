package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CBCREProcessDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CBCREProcessDTO.class);
        CBCREProcessDTO cBCREProcessDTO1 = new CBCREProcessDTO();
        cBCREProcessDTO1.setId(1L);
        CBCREProcessDTO cBCREProcessDTO2 = new CBCREProcessDTO();
        assertThat(cBCREProcessDTO1).isNotEqualTo(cBCREProcessDTO2);
        cBCREProcessDTO2.setId(cBCREProcessDTO1.getId());
        assertThat(cBCREProcessDTO1).isEqualTo(cBCREProcessDTO2);
        cBCREProcessDTO2.setId(2L);
        assertThat(cBCREProcessDTO1).isNotEqualTo(cBCREProcessDTO2);
        cBCREProcessDTO1.setId(null);
        assertThat(cBCREProcessDTO1).isNotEqualTo(cBCREProcessDTO2);
    }
}
