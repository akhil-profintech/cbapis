package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.ActionTestSamples.*;
import static in.pft.apis.creditbazaar.domain.ContextTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ActionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Action.class);
        Action action1 = getActionSample1();
        Action action2 = new Action();
        assertThat(action1).isNotEqualTo(action2);

        action2.setId(action1.getId());
        assertThat(action1).isEqualTo(action2);

        action2 = getActionSample2();
        assertThat(action1).isNotEqualTo(action2);
    }

    @Test
    void contextTest() throws Exception {
        Action action = getActionRandomSampleGenerator();
        Context contextBack = getContextRandomSampleGenerator();

        action.addContext(contextBack);
        assertThat(action.getContexts()).containsOnly(contextBack);
        assertThat(contextBack.getAction()).isEqualTo(action);

        action.removeContext(contextBack);
        assertThat(action.getContexts()).doesNotContain(contextBack);
        assertThat(contextBack.getAction()).isNull();

        action.contexts(new HashSet<>(Set.of(contextBack)));
        assertThat(action.getContexts()).containsOnly(contextBack);
        assertThat(contextBack.getAction()).isEqualTo(action);

        action.setContexts(new HashSet<>());
        assertThat(action.getContexts()).doesNotContain(contextBack);
        assertThat(contextBack.getAction()).isNull();
    }
}
