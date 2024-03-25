package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class IndividualAssessmentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static IndividualAssessment getIndividualAssessmentSample1() {
        return new IndividualAssessment()
            .id(1L)
            .assessmentId(1L)
            .assessmentUlidId("assessmentUlidId1")
            .finalVerdict("finalVerdict1")
            .creRequestId("creRequestId1")
            .timestamp("timestamp1")
            .tradePartnerGST("tradePartnerGST1")
            .tradePartnerId("tradePartnerId1")
            .invoiceAmount(1L)
            .invoiceId("invoiceId1")
            .baseScore("baseScore1")
            .ctin("ctin1")
            .invDate("invDate1")
            .cbProcessId(1L);
    }

    public static IndividualAssessment getIndividualAssessmentSample2() {
        return new IndividualAssessment()
            .id(2L)
            .assessmentId(2L)
            .assessmentUlidId("assessmentUlidId2")
            .finalVerdict("finalVerdict2")
            .creRequestId("creRequestId2")
            .timestamp("timestamp2")
            .tradePartnerGST("tradePartnerGST2")
            .tradePartnerId("tradePartnerId2")
            .invoiceAmount(2L)
            .invoiceId("invoiceId2")
            .baseScore("baseScore2")
            .ctin("ctin2")
            .invDate("invDate2")
            .cbProcessId(2L);
    }

    public static IndividualAssessment getIndividualAssessmentRandomSampleGenerator() {
        return new IndividualAssessment()
            .id(longCount.incrementAndGet())
            .assessmentId(longCount.incrementAndGet())
            .assessmentUlidId(UUID.randomUUID().toString())
            .finalVerdict(UUID.randomUUID().toString())
            .creRequestId(UUID.randomUUID().toString())
            .timestamp(UUID.randomUUID().toString())
            .tradePartnerGST(UUID.randomUUID().toString())
            .tradePartnerId(UUID.randomUUID().toString())
            .invoiceAmount(longCount.incrementAndGet())
            .invoiceId(UUID.randomUUID().toString())
            .baseScore(UUID.randomUUID().toString())
            .ctin(UUID.randomUUID().toString())
            .invDate(UUID.randomUUID().toString())
            .cbProcessId(longCount.incrementAndGet());
    }
}
