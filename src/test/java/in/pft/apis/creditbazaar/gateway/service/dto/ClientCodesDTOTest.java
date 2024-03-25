package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientCodesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientCodesDTO.class);
        ClientCodesDTO clientCodesDTO1 = new ClientCodesDTO();
        clientCodesDTO1.setId(1L);
        ClientCodesDTO clientCodesDTO2 = new ClientCodesDTO();
        assertThat(clientCodesDTO1).isNotEqualTo(clientCodesDTO2);
        clientCodesDTO2.setId(clientCodesDTO1.getId());
        assertThat(clientCodesDTO1).isEqualTo(clientCodesDTO2);
        clientCodesDTO2.setId(2L);
        assertThat(clientCodesDTO1).isNotEqualTo(clientCodesDTO2);
        clientCodesDTO1.setId(null);
        assertThat(clientCodesDTO1).isNotEqualTo(clientCodesDTO2);
    }
}
