package in.pft.apis.creditbazaar.gateway.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocDetailsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocDetailsDTO.class);
        DocDetailsDTO docDetailsDTO1 = new DocDetailsDTO();
        docDetailsDTO1.setId(1L);
        DocDetailsDTO docDetailsDTO2 = new DocDetailsDTO();
        assertThat(docDetailsDTO1).isNotEqualTo(docDetailsDTO2);
        docDetailsDTO2.setId(docDetailsDTO1.getId());
        assertThat(docDetailsDTO1).isEqualTo(docDetailsDTO2);
        docDetailsDTO2.setId(2L);
        assertThat(docDetailsDTO1).isNotEqualTo(docDetailsDTO2);
        docDetailsDTO1.setId(null);
        assertThat(docDetailsDTO1).isNotEqualTo(docDetailsDTO2);
    }
}
