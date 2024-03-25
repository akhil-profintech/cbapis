package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlacedOfferDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlacedOfferDTO.class);
        PlacedOfferDTO placedOfferDTO1 = new PlacedOfferDTO();
        placedOfferDTO1.setId(1L);
        PlacedOfferDTO placedOfferDTO2 = new PlacedOfferDTO();
        assertThat(placedOfferDTO1).isNotEqualTo(placedOfferDTO2);
        placedOfferDTO2.setId(placedOfferDTO1.getId());
        assertThat(placedOfferDTO1).isEqualTo(placedOfferDTO2);
        placedOfferDTO2.setId(2L);
        assertThat(placedOfferDTO1).isNotEqualTo(placedOfferDTO2);
        placedOfferDTO1.setId(null);
        assertThat(placedOfferDTO1).isNotEqualTo(placedOfferDTO2);
    }
}
