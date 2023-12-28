package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RequestOfferDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RequestOfferDTO.class);
        RequestOfferDTO requestOfferDTO1 = new RequestOfferDTO();
        requestOfferDTO1.setId(1L);
        RequestOfferDTO requestOfferDTO2 = new RequestOfferDTO();
        assertThat(requestOfferDTO1).isNotEqualTo(requestOfferDTO2);
        requestOfferDTO2.setId(requestOfferDTO1.getId());
        assertThat(requestOfferDTO1).isEqualTo(requestOfferDTO2);
        requestOfferDTO2.setId(2L);
        assertThat(requestOfferDTO1).isNotEqualTo(requestOfferDTO2);
        requestOfferDTO1.setId(null);
        assertThat(requestOfferDTO1).isNotEqualTo(requestOfferDTO2);
    }
}
