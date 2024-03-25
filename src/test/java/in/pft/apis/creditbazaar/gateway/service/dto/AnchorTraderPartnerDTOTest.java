package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnchorTraderPartnerDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnchorTraderPartnerDTO.class);
        AnchorTraderPartnerDTO anchorTraderPartnerDTO1 = new AnchorTraderPartnerDTO();
        anchorTraderPartnerDTO1.setId(1L);
        AnchorTraderPartnerDTO anchorTraderPartnerDTO2 = new AnchorTraderPartnerDTO();
        assertThat(anchorTraderPartnerDTO1).isNotEqualTo(anchorTraderPartnerDTO2);
        anchorTraderPartnerDTO2.setId(anchorTraderPartnerDTO1.getId());
        assertThat(anchorTraderPartnerDTO1).isEqualTo(anchorTraderPartnerDTO2);
        anchorTraderPartnerDTO2.setId(2L);
        assertThat(anchorTraderPartnerDTO1).isNotEqualTo(anchorTraderPartnerDTO2);
        anchorTraderPartnerDTO1.setId(null);
        assertThat(anchorTraderPartnerDTO1).isNotEqualTo(anchorTraderPartnerDTO2);
    }
}
