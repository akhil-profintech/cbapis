package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.FinanceRequestMappingTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FinanceRequestMappingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FinanceRequestMapping.class);
        FinanceRequestMapping financeRequestMapping1 = getFinanceRequestMappingSample1();
        FinanceRequestMapping financeRequestMapping2 = new FinanceRequestMapping();
        assertThat(financeRequestMapping1).isNotEqualTo(financeRequestMapping2);

        financeRequestMapping2.setId(financeRequestMapping1.getId());
        assertThat(financeRequestMapping1).isEqualTo(financeRequestMapping2);

        financeRequestMapping2 = getFinanceRequestMappingSample2();
        assertThat(financeRequestMapping1).isNotEqualTo(financeRequestMapping2);
    }
}
