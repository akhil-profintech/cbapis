package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FTTransactionDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FTTransactionDetailsDTO.class);
        FTTransactionDetailsDTO fTTransactionDetailsDTO1 = new FTTransactionDetailsDTO();
        fTTransactionDetailsDTO1.setId(1L);
        FTTransactionDetailsDTO fTTransactionDetailsDTO2 = new FTTransactionDetailsDTO();
        assertThat(fTTransactionDetailsDTO1).isNotEqualTo(fTTransactionDetailsDTO2);
        fTTransactionDetailsDTO2.setId(fTTransactionDetailsDTO1.getId());
        assertThat(fTTransactionDetailsDTO1).isEqualTo(fTTransactionDetailsDTO2);
        fTTransactionDetailsDTO2.setId(2L);
        assertThat(fTTransactionDetailsDTO1).isNotEqualTo(fTTransactionDetailsDTO2);
        fTTransactionDetailsDTO1.setId(null);
        assertThat(fTTransactionDetailsDTO1).isNotEqualTo(fTTransactionDetailsDTO2);
    }
}
