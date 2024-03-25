package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EscrowTransactionDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EscrowTransactionDetailsDTO.class);
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO1 = new EscrowTransactionDetailsDTO();
        escrowTransactionDetailsDTO1.setId(1L);
        EscrowTransactionDetailsDTO escrowTransactionDetailsDTO2 = new EscrowTransactionDetailsDTO();
        assertThat(escrowTransactionDetailsDTO1).isNotEqualTo(escrowTransactionDetailsDTO2);
        escrowTransactionDetailsDTO2.setId(escrowTransactionDetailsDTO1.getId());
        assertThat(escrowTransactionDetailsDTO1).isEqualTo(escrowTransactionDetailsDTO2);
        escrowTransactionDetailsDTO2.setId(2L);
        assertThat(escrowTransactionDetailsDTO1).isNotEqualTo(escrowTransactionDetailsDTO2);
        escrowTransactionDetailsDTO1.setId(null);
        assertThat(escrowTransactionDetailsDTO1).isNotEqualTo(escrowTransactionDetailsDTO2);
    }
}
