package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BeneValidationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BeneValidationDTO.class);
        BeneValidationDTO beneValidationDTO1 = new BeneValidationDTO();
        beneValidationDTO1.setId(1L);
        BeneValidationDTO beneValidationDTO2 = new BeneValidationDTO();
        assertThat(beneValidationDTO1).isNotEqualTo(beneValidationDTO2);
        beneValidationDTO2.setId(beneValidationDTO1.getId());
        assertThat(beneValidationDTO1).isEqualTo(beneValidationDTO2);
        beneValidationDTO2.setId(2L);
        assertThat(beneValidationDTO1).isNotEqualTo(beneValidationDTO2);
        beneValidationDTO1.setId(null);
        assertThat(beneValidationDTO1).isNotEqualTo(beneValidationDTO2);
    }
}
