package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.ClientCodesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ClientCodesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ClientCodes.class);
        ClientCodes clientCodes1 = getClientCodesSample1();
        ClientCodes clientCodes2 = new ClientCodes();
        assertThat(clientCodes1).isNotEqualTo(clientCodes2);

        clientCodes2.setId(clientCodes1.getId());
        assertThat(clientCodes1).isEqualTo(clientCodes2);

        clientCodes2 = getClientCodesSample2();
        assertThat(clientCodes1).isNotEqualTo(clientCodes2);
    }
}
