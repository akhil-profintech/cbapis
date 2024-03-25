package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.DocDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.FinanceRequestTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DocDetailsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocDetails.class);
        DocDetails docDetails1 = getDocDetailsSample1();
        DocDetails docDetails2 = new DocDetails();
        assertThat(docDetails1).isNotEqualTo(docDetails2);

        docDetails2.setId(docDetails1.getId());
        assertThat(docDetails1).isEqualTo(docDetails2);

        docDetails2 = getDocDetailsSample2();
        assertThat(docDetails1).isNotEqualTo(docDetails2);
    }

    @Test
    void financeRequestTest() throws Exception {
        DocDetails docDetails = getDocDetailsRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        docDetails.setFinanceRequest(financeRequestBack);
        assertThat(docDetails.getFinanceRequest()).isEqualTo(financeRequestBack);

        docDetails.financeRequest(null);
        assertThat(docDetails.getFinanceRequest()).isNull();
    }
}
