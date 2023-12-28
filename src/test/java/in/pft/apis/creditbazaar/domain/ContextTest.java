package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.ActionTestSamples.*;
import static in.pft.apis.creditbazaar.domain.ContextTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
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

    @Test
    void actionTest() throws Exception {
        Context context = getContextRandomSampleGenerator();
        Action actionBack = getActionRandomSampleGenerator();

        context.setAction(actionBack);
        assertThat(context.getAction()).isEqualTo(actionBack);

        context.action(null);
        assertThat(context.getAction()).isNull();
    }
}
