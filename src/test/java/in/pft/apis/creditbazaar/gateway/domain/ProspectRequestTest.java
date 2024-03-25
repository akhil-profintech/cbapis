package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.ProspectRequestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProspectRequestTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProspectRequest.class);
        ProspectRequest prospectRequest1 = getProspectRequestSample1();
        ProspectRequest prospectRequest2 = new ProspectRequest();
        assertThat(prospectRequest1).isNotEqualTo(prospectRequest2);

        prospectRequest2.setId(prospectRequest1.getId());
        assertThat(prospectRequest1).isEqualTo(prospectRequest2);

        prospectRequest2 = getProspectRequestSample2();
        assertThat(prospectRequest1).isNotEqualTo(prospectRequest2);
    }
}
