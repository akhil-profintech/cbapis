package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditBazaarIntegratorDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditBazaarIntegratorDTO.class);
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO1 = new CreditBazaarIntegratorDTO();
        creditBazaarIntegratorDTO1.setId(1L);
        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO2 = new CreditBazaarIntegratorDTO();
        assertThat(creditBazaarIntegratorDTO1).isNotEqualTo(creditBazaarIntegratorDTO2);
        creditBazaarIntegratorDTO2.setId(creditBazaarIntegratorDTO1.getId());
        assertThat(creditBazaarIntegratorDTO1).isEqualTo(creditBazaarIntegratorDTO2);
        creditBazaarIntegratorDTO2.setId(2L);
        assertThat(creditBazaarIntegratorDTO1).isNotEqualTo(creditBazaarIntegratorDTO2);
        creditBazaarIntegratorDTO1.setId(null);
        assertThat(creditBazaarIntegratorDTO1).isNotEqualTo(creditBazaarIntegratorDTO2);
    }
}
