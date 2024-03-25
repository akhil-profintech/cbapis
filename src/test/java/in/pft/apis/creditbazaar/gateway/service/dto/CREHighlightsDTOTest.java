package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CREHighlightsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CREHighlightsDTO.class);
        CREHighlightsDTO cREHighlightsDTO1 = new CREHighlightsDTO();
        cREHighlightsDTO1.setId(1L);
        CREHighlightsDTO cREHighlightsDTO2 = new CREHighlightsDTO();
        assertThat(cREHighlightsDTO1).isNotEqualTo(cREHighlightsDTO2);
        cREHighlightsDTO2.setId(cREHighlightsDTO1.getId());
        assertThat(cREHighlightsDTO1).isEqualTo(cREHighlightsDTO2);
        cREHighlightsDTO2.setId(2L);
        assertThat(cREHighlightsDTO1).isNotEqualTo(cREHighlightsDTO2);
        cREHighlightsDTO1.setId(null);
        assertThat(cREHighlightsDTO1).isNotEqualTo(cREHighlightsDTO2);
    }
}
