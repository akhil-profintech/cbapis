package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsTransferTransactionDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundsTransferTransactionDetailsDTO.class);
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO1 = new FundsTransferTransactionDetailsDTO();
        fundsTransferTransactionDetailsDTO1.setId(1L);
        FundsTransferTransactionDetailsDTO fundsTransferTransactionDetailsDTO2 = new FundsTransferTransactionDetailsDTO();
        assertThat(fundsTransferTransactionDetailsDTO1).isNotEqualTo(fundsTransferTransactionDetailsDTO2);
        fundsTransferTransactionDetailsDTO2.setId(fundsTransferTransactionDetailsDTO1.getId());
        assertThat(fundsTransferTransactionDetailsDTO1).isEqualTo(fundsTransferTransactionDetailsDTO2);
        fundsTransferTransactionDetailsDTO2.setId(2L);
        assertThat(fundsTransferTransactionDetailsDTO1).isNotEqualTo(fundsTransferTransactionDetailsDTO2);
        fundsTransferTransactionDetailsDTO1.setId(null);
        assertThat(fundsTransferTransactionDetailsDTO1).isNotEqualTo(fundsTransferTransactionDetailsDTO2);
    }
}
