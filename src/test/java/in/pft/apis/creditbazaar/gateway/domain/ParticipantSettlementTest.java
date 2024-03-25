package in.pft.apis.creditbazaar.gateway.domain;

import static in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlementTestSamples.*;
import static in.pft.apis.creditbazaar.gateway.domain.SettlementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.gateway.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ParticipantSettlementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipantSettlement.class);
        ParticipantSettlement participantSettlement1 = getParticipantSettlementSample1();
        ParticipantSettlement participantSettlement2 = new ParticipantSettlement();
        assertThat(participantSettlement1).isNotEqualTo(participantSettlement2);

        participantSettlement2.setId(participantSettlement1.getId());
        assertThat(participantSettlement1).isEqualTo(participantSettlement2);

        participantSettlement2 = getParticipantSettlementSample2();
        assertThat(participantSettlement1).isNotEqualTo(participantSettlement2);
    }

    @Test
    void fundsTransferTransactionDetailsTest() throws Exception {
        ParticipantSettlement participantSettlement = getParticipantSettlementRandomSampleGenerator();
        FundsTransferTransactionDetails fundsTransferTransactionDetailsBack = getFundsTransferTransactionDetailsRandomSampleGenerator();

        participantSettlement.addFundsTransferTransactionDetails(fundsTransferTransactionDetailsBack);
        assertThat(participantSettlement.getFundsTransferTransactionDetails()).containsOnly(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getParticipantsettlement()).isEqualTo(participantSettlement);

        participantSettlement.removeFundsTransferTransactionDetails(fundsTransferTransactionDetailsBack);
        assertThat(participantSettlement.getFundsTransferTransactionDetails()).doesNotContain(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getParticipantsettlement()).isNull();

        participantSettlement.fundsTransferTransactionDetails(new HashSet<>(Set.of(fundsTransferTransactionDetailsBack)));
        assertThat(participantSettlement.getFundsTransferTransactionDetails()).containsOnly(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getParticipantsettlement()).isEqualTo(participantSettlement);

        participantSettlement.setFundsTransferTransactionDetails(new HashSet<>());
        assertThat(participantSettlement.getFundsTransferTransactionDetails()).doesNotContain(fundsTransferTransactionDetailsBack);
        assertThat(fundsTransferTransactionDetailsBack.getParticipantsettlement()).isNull();
    }

    @Test
    void settlementTest() throws Exception {
        ParticipantSettlement participantSettlement = getParticipantSettlementRandomSampleGenerator();
        Settlement settlementBack = getSettlementRandomSampleGenerator();

        participantSettlement.setSettlement(settlementBack);
        assertThat(participantSettlement.getSettlement()).isEqualTo(settlementBack);

        participantSettlement.settlement(null);
        assertThat(participantSettlement.getSettlement()).isNull();
    }
}
