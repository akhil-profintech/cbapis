package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.ActionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
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
}
