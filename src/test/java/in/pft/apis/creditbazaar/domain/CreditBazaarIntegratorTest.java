package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.CreditBazaarIntegratorTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditBazaarIntegratorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditBazaarIntegrator.class);
        CreditBazaarIntegrator creditBazaarIntegrator1 = getCreditBazaarIntegratorSample1();
        CreditBazaarIntegrator creditBazaarIntegrator2 = new CreditBazaarIntegrator();
        assertThat(creditBazaarIntegrator1).isNotEqualTo(creditBazaarIntegrator2);

        creditBazaarIntegrator2.setId(creditBazaarIntegrator1.getId());
        assertThat(creditBazaarIntegrator1).isEqualTo(creditBazaarIntegrator2);

        creditBazaarIntegrator2 = getCreditBazaarIntegratorSample2();
        assertThat(creditBazaarIntegrator1).isNotEqualTo(creditBazaarIntegrator2);
    }
}
