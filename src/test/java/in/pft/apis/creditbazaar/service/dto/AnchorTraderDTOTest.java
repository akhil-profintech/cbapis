package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AnchorTraderDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AnchorTraderDTO.class);
        AnchorTraderDTO anchorTraderDTO1 = new AnchorTraderDTO();
        anchorTraderDTO1.setId(1L);
        AnchorTraderDTO anchorTraderDTO2 = new AnchorTraderDTO();
        assertThat(anchorTraderDTO1).isNotEqualTo(anchorTraderDTO2);
        anchorTraderDTO2.setId(anchorTraderDTO1.getId());
        assertThat(anchorTraderDTO1).isEqualTo(anchorTraderDTO2);
        anchorTraderDTO2.setId(2L);
        assertThat(anchorTraderDTO1).isNotEqualTo(anchorTraderDTO2);
        anchorTraderDTO1.setId(null);
        assertThat(anchorTraderDTO1).isNotEqualTo(anchorTraderDTO2);
    }
}
