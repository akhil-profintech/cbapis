package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.FinanceRequestTestSamples.*;
import static in.pft.apis.creditbazaar.domain.ParticipantSettlementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.SettlementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SettlementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Settlement.class);
        Settlement settlement1 = getSettlementSample1();
        Settlement settlement2 = new Settlement();
        assertThat(settlement1).isNotEqualTo(settlement2);

        settlement2.setId(settlement1.getId());
        assertThat(settlement1).isEqualTo(settlement2);

        settlement2 = getSettlementSample2();
        assertThat(settlement1).isNotEqualTo(settlement2);
    }

    @Test
    void participantSettlementTest() throws Exception {
        Settlement settlement = getSettlementRandomSampleGenerator();
        ParticipantSettlement participantSettlementBack = getParticipantSettlementRandomSampleGenerator();

        settlement.addParticipantSettlement(participantSettlementBack);
        assertThat(settlement.getParticipantSettlements()).containsOnly(participantSettlementBack);
        assertThat(participantSettlementBack.getSettlement()).isEqualTo(settlement);

        settlement.removeParticipantSettlement(participantSettlementBack);
        assertThat(settlement.getParticipantSettlements()).doesNotContain(participantSettlementBack);
        assertThat(participantSettlementBack.getSettlement()).isNull();

        settlement.participantSettlements(new HashSet<>(Set.of(participantSettlementBack)));
        assertThat(settlement.getParticipantSettlements()).containsOnly(participantSettlementBack);
        assertThat(participantSettlementBack.getSettlement()).isEqualTo(settlement);

        settlement.setParticipantSettlements(new HashSet<>());
        assertThat(settlement.getParticipantSettlements()).doesNotContain(participantSettlementBack);
        assertThat(participantSettlementBack.getSettlement()).isNull();
    }

    @Test
    void financerequestTest() throws Exception {
        Settlement settlement = getSettlementRandomSampleGenerator();
        FinanceRequest financeRequestBack = getFinanceRequestRandomSampleGenerator();

        settlement.setFinancerequest(financeRequestBack);
        assertThat(settlement.getFinancerequest()).isEqualTo(financeRequestBack);

        settlement.financerequest(null);
        assertThat(settlement.getFinancerequest()).isNull();
    }
}
