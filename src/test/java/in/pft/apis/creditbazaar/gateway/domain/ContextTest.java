package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.ContextTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContextTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Context.class);
        Context context1 = getContextSample1();
        Context context2 = new Context();
        assertThat(context1).isNotEqualTo(context2);

        context2.setId(context1.getId());
        assertThat(context1).isEqualTo(context2);

        context2 = getContextSample2();
        assertThat(context1).isNotEqualTo(context2);
    }
}
