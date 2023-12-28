package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AcceptedOfferDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AcceptedOfferDTO.class);
        AcceptedOfferDTO acceptedOfferDTO1 = new AcceptedOfferDTO();
        acceptedOfferDTO1.setId(1L);
        AcceptedOfferDTO acceptedOfferDTO2 = new AcceptedOfferDTO();
        assertThat(acceptedOfferDTO1).isNotEqualTo(acceptedOfferDTO2);
        acceptedOfferDTO2.setId(acceptedOfferDTO1.getId());
        assertThat(acceptedOfferDTO1).isEqualTo(acceptedOfferDTO2);
        acceptedOfferDTO2.setId(2L);
        assertThat(acceptedOfferDTO1).isNotEqualTo(acceptedOfferDTO2);
        acceptedOfferDTO1.setId(null);
        assertThat(acceptedOfferDTO1).isNotEqualTo(acceptedOfferDTO2);
    }
}
