package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.UserDtlsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class UserDtlsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserDtls.class);
        UserDtls userDtls1 = getUserDtlsSample1();
        UserDtls userDtls2 = new UserDtls();
        assertThat(userDtls1).isNotEqualTo(userDtls2);

        userDtls2.setId(userDtls1.getId());
        assertThat(userDtls1).isEqualTo(userDtls2);

        userDtls2 = getUserDtlsSample2();
        assertThat(userDtls1).isNotEqualTo(userDtls2);
    }
}
