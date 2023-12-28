package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DisbursementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DisbursementDTO.class);
        DisbursementDTO disbursementDTO1 = new DisbursementDTO();
        disbursementDTO1.setId(1L);
        DisbursementDTO disbursementDTO2 = new DisbursementDTO();
        assertThat(disbursementDTO1).isNotEqualTo(disbursementDTO2);
        disbursementDTO2.setId(disbursementDTO1.getId());
        assertThat(disbursementDTO1).isEqualTo(disbursementDTO2);
        disbursementDTO2.setId(2L);
        assertThat(disbursementDTO1).isNotEqualTo(disbursementDTO2);
        disbursementDTO1.setId(null);
        assertThat(disbursementDTO1).isNotEqualTo(disbursementDTO2);
    }
}
