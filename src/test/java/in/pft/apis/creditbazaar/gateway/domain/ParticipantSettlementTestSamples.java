package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ParticipantSettlementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ParticipantSettlement getParticipantSettlementSample1() {
        return new ParticipantSettlement()
            .id(1L)
            .participantSettlementId(1L)
            .participantSettlementUlidId("participantSettlementUlidId1")
            .participantId(1L)
            .participantName("participantName1")
            .gstId("gstId1")
            .settlementAmount(1L)
            .settlementDate("settlementDate1")
            .settlementDueDate("settlementDueDate1")
            .settlementContactInfo("settlementContactInfo1")
            .patronOfPayment("patronOfPayment1")
            .recipientOfPayment("recipientOfPayment1")
            .settlementMethod("settlementMethod1")
            .tenantId(1L)
            .accName("accName1")
            .ifscCode("ifscCode1")
            .accNumber(1L)
            .docId("docId1");
    }

    public static ParticipantSettlement getParticipantSettlementSample2() {
        return new ParticipantSettlement()
            .id(2L)
            .participantSettlementId(2L)
            .participantSettlementUlidId("participantSettlementUlidId2")
            .participantId(2L)
            .participantName("participantName2")
            .gstId("gstId2")
            .settlementAmount(2L)
            .settlementDate("settlementDate2")
            .settlementDueDate("settlementDueDate2")
            .settlementContactInfo("settlementContactInfo2")
            .patronOfPayment("patronOfPayment2")
            .recipientOfPayment("recipientOfPayment2")
            .settlementMethod("settlementMethod2")
            .tenantId(2L)
            .accName("accName2")
            .ifscCode("ifscCode2")
            .accNumber(2L)
            .docId("docId2");
    }

    public static ParticipantSettlement getParticipantSettlementRandomSampleGenerator() {
        return new ParticipantSettlement()
            .id(longCount.incrementAndGet())
            .participantSettlementId(longCount.incrementAndGet())
            .participantSettlementUlidId(UUID.randomUUID().toString())
            .participantId(longCount.incrementAndGet())
            .participantName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .settlementAmount(longCount.incrementAndGet())
            .settlementDate(UUID.randomUUID().toString())
            .settlementDueDate(UUID.randomUUID().toString())
            .settlementContactInfo(UUID.randomUUID().toString())
            .patronOfPayment(UUID.randomUUID().toString())
            .recipientOfPayment(UUID.randomUUID().toString())
            .settlementMethod(UUID.randomUUID().toString())
            .tenantId(longCount.incrementAndGet())
            .accName(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .accNumber(longCount.incrementAndGet())
            .docId(UUID.randomUUID().toString());
    }
}
