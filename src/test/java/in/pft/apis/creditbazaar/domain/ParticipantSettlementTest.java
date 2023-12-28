package in.pft.apis.creditbazaar.domain;

import static in.pft.apis.creditbazaar.domain.DocDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.FTTransactionDetailsTestSamples.*;
import static in.pft.apis.creditbazaar.domain.ParticipantSettlementTestSamples.*;
import static in.pft.apis.creditbazaar.domain.SettlementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
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
    void fTTransactionDetailsTest() throws Exception {
        ParticipantSettlement participantSettlement = getParticipantSettlementRandomSampleGenerator();
        FTTransactionDetails fTTransactionDetailsBack = getFTTransactionDetailsRandomSampleGenerator();

        participantSettlement.addFTTransactionDetails(fTTransactionDetailsBack);
        assertThat(participantSettlement.getFTTransactionDetails()).containsOnly(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getParticipantsettlement()).isEqualTo(participantSettlement);

        participantSettlement.removeFTTransactionDetails(fTTransactionDetailsBack);
        assertThat(participantSettlement.getFTTransactionDetails()).doesNotContain(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getParticipantsettlement()).isNull();

        participantSettlement.fTTransactionDetails(new HashSet<>(Set.of(fTTransactionDetailsBack)));
        assertThat(participantSettlement.getFTTransactionDetails()).containsOnly(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getParticipantsettlement()).isEqualTo(participantSettlement);

        participantSettlement.setFTTransactionDetails(new HashSet<>());
        assertThat(participantSettlement.getFTTransactionDetails()).doesNotContain(fTTransactionDetailsBack);
        assertThat(fTTransactionDetailsBack.getParticipantsettlement()).isNull();
    }

    @Test
    void docDetailsTest() throws Exception {
        ParticipantSettlement participantSettlement = getParticipantSettlementRandomSampleGenerator();
        DocDetails docDetailsBack = getDocDetailsRandomSampleGenerator();

        participantSettlement.addDocDetails(docDetailsBack);
        assertThat(participantSettlement.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getParticipantsettlement()).isEqualTo(participantSettlement);

        participantSettlement.removeDocDetails(docDetailsBack);
        assertThat(participantSettlement.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getParticipantsettlement()).isNull();

        participantSettlement.docDetails(new HashSet<>(Set.of(docDetailsBack)));
        assertThat(participantSettlement.getDocDetails()).containsOnly(docDetailsBack);
        assertThat(docDetailsBack.getParticipantsettlement()).isEqualTo(participantSettlement);

        participantSettlement.setDocDetails(new HashSet<>());
        assertThat(participantSettlement.getDocDetails()).doesNotContain(docDetailsBack);
        assertThat(docDetailsBack.getParticipantsettlement()).isNull();
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
