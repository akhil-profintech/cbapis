package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.AnchorTraderTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FinancePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.domain.OrganizationTestSamples.*;
import static in.pft.apis.creditbazaar.domain.TradePartnerTestSamples.*;
import static in.pft.apis.creditbazaar.domain.UserDtlsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OrganizationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Organization.class);
        Organization organization1 = getOrganizationSample1();
        Organization organization2 = new Organization();
        assertThat(organization1).isNotEqualTo(organization2);

        organization2.setId(organization1.getId());
        assertThat(organization1).isEqualTo(organization2);

        organization2 = getOrganizationSample2();
        assertThat(organization1).isNotEqualTo(organization2);
    }

    @Test
    void userDtlsTest() throws Exception {
        Organization organization = getOrganizationRandomSampleGenerator();
        UserDtls userDtlsBack = getUserDtlsRandomSampleGenerator();

        organization.addUserDtls(userDtlsBack);
        assertThat(organization.getUserDtls()).containsOnly(userDtlsBack);
        assertThat(userDtlsBack.getOrganization()).isEqualTo(organization);

        organization.removeUserDtls(userDtlsBack);
        assertThat(organization.getUserDtls()).doesNotContain(userDtlsBack);
        assertThat(userDtlsBack.getOrganization()).isNull();

        organization.userDtls(new HashSet<>(Set.of(userDtlsBack)));
        assertThat(organization.getUserDtls()).containsOnly(userDtlsBack);
        assertThat(userDtlsBack.getOrganization()).isEqualTo(organization);

        organization.setUserDtls(new HashSet<>());
        assertThat(organization.getUserDtls()).doesNotContain(userDtlsBack);
        assertThat(userDtlsBack.getOrganization()).isNull();
    }

    @Test
    void tradePartnerTest() throws Exception {
        Organization organization = getOrganizationRandomSampleGenerator();
        TradePartner tradePartnerBack = getTradePartnerRandomSampleGenerator();

        organization.addTradePartner(tradePartnerBack);
        assertThat(organization.getTradePartners()).containsOnly(tradePartnerBack);
        assertThat(tradePartnerBack.getOrganization()).isEqualTo(organization);

        organization.removeTradePartner(tradePartnerBack);
        assertThat(organization.getTradePartners()).doesNotContain(tradePartnerBack);
        assertThat(tradePartnerBack.getOrganization()).isNull();

        organization.tradePartners(new HashSet<>(Set.of(tradePartnerBack)));
        assertThat(organization.getTradePartners()).containsOnly(tradePartnerBack);
        assertThat(tradePartnerBack.getOrganization()).isEqualTo(organization);

        organization.setTradePartners(new HashSet<>());
        assertThat(organization.getTradePartners()).doesNotContain(tradePartnerBack);
        assertThat(tradePartnerBack.getOrganization()).isNull();
    }

    @Test
    void anchorTraderTest() throws Exception {
        Organization organization = getOrganizationRandomSampleGenerator();
        AnchorTrader anchorTraderBack = getAnchorTraderRandomSampleGenerator();

        organization.addAnchorTrader(anchorTraderBack);
        assertThat(organization.getAnchorTraders()).containsOnly(anchorTraderBack);
        assertThat(anchorTraderBack.getOrganization()).isEqualTo(organization);

        organization.removeAnchorTrader(anchorTraderBack);
        assertThat(organization.getAnchorTraders()).doesNotContain(anchorTraderBack);
        assertThat(anchorTraderBack.getOrganization()).isNull();

        organization.anchorTraders(new HashSet<>(Set.of(anchorTraderBack)));
        assertThat(organization.getAnchorTraders()).containsOnly(anchorTraderBack);
        assertThat(anchorTraderBack.getOrganization()).isEqualTo(organization);

        organization.setAnchorTraders(new HashSet<>());
        assertThat(organization.getAnchorTraders()).doesNotContain(anchorTraderBack);
        assertThat(anchorTraderBack.getOrganization()).isNull();
    }

    @Test
    void financePartnerTest() throws Exception {
        Organization organization = getOrganizationRandomSampleGenerator();
        FinancePartner financePartnerBack = getFinancePartnerRandomSampleGenerator();

        organization.addFinancePartner(financePartnerBack);
        assertThat(organization.getFinancePartners()).containsOnly(financePartnerBack);
        assertThat(financePartnerBack.getOrganization()).isEqualTo(organization);

        organization.removeFinancePartner(financePartnerBack);
        assertThat(organization.getFinancePartners()).doesNotContain(financePartnerBack);
        assertThat(financePartnerBack.getOrganization()).isNull();

        organization.financePartners(new HashSet<>(Set.of(financePartnerBack)));
        assertThat(organization.getFinancePartners()).containsOnly(financePartnerBack);
        assertThat(financePartnerBack.getOrganization()).isEqualTo(organization);

        organization.setFinancePartners(new HashSet<>());
        assertThat(organization.getFinancePartners()).doesNotContain(financePartnerBack);
        assertThat(financePartnerBack.getOrganization()).isNull();
    }
}
