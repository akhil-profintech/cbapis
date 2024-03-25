package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.EscrowAccountDetailsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EscrowAccountDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EscrowAccountDetails.class);
        EscrowAccountDetails escrowAccountDetails1 = getEscrowAccountDetailsSample1();
        EscrowAccountDetails escrowAccountDetails2 = new EscrowAccountDetails();
        assertThat(escrowAccountDetails1).isNotEqualTo(escrowAccountDetails2);

        escrowAccountDetails2.setId(escrowAccountDetails1.getId());
        assertThat(escrowAccountDetails1).isEqualTo(escrowAccountDetails2);

        escrowAccountDetails2 = getEscrowAccountDetailsSample2();
        assertThat(escrowAccountDetails1).isNotEqualTo(escrowAccountDetails2);
    }
}
