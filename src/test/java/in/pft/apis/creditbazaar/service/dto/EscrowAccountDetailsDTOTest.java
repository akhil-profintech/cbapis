package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EscrowAccountDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EscrowAccountDetailsDTO.class);
        EscrowAccountDetailsDTO escrowAccountDetailsDTO1 = new EscrowAccountDetailsDTO();
        escrowAccountDetailsDTO1.setId(1L);
        EscrowAccountDetailsDTO escrowAccountDetailsDTO2 = new EscrowAccountDetailsDTO();
        assertThat(escrowAccountDetailsDTO1).isNotEqualTo(escrowAccountDetailsDTO2);
        escrowAccountDetailsDTO2.setId(escrowAccountDetailsDTO1.getId());
        assertThat(escrowAccountDetailsDTO1).isEqualTo(escrowAccountDetailsDTO2);
        escrowAccountDetailsDTO2.setId(2L);
        assertThat(escrowAccountDetailsDTO1).isNotEqualTo(escrowAccountDetailsDTO2);
        escrowAccountDetailsDTO1.setId(null);
        assertThat(escrowAccountDetailsDTO1).isNotEqualTo(escrowAccountDetailsDTO2);
    }
}
