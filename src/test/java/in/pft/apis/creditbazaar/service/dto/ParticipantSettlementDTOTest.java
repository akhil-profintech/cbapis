package in.pft.apis.creditbazaar.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import in.pft.apis.creditbazaar.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ParticipantSettlementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParticipantSettlementDTO.class);
        ParticipantSettlementDTO participantSettlementDTO1 = new ParticipantSettlementDTO();
        participantSettlementDTO1.setId(1L);
        ParticipantSettlementDTO participantSettlementDTO2 = new ParticipantSettlementDTO();
        assertThat(participantSettlementDTO1).isNotEqualTo(participantSettlementDTO2);
        participantSettlementDTO2.setId(participantSettlementDTO1.getId());
        assertThat(participantSettlementDTO1).isEqualTo(participantSettlementDTO2);
        participantSettlementDTO2.setId(2L);
        assertThat(participantSettlementDTO1).isNotEqualTo(participantSettlementDTO2);
        participantSettlementDTO1.setId(null);
        assertThat(participantSettlementDTO1).isNotEqualTo(participantSettlementDTO2);
    }
}
