package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class IndividualAssessmentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static IndividualAssessment getIndividualAssessmentSample1() {
        return new IndividualAssessment()
            .id(1L)
            .assessmentId("assessmentId1")
            .finalverdict("finalverdict1")
            .creRequestId("creRequestId1")
            .timestamp("timestamp1")
            .tradepartnerGST("tradepartnerGST1")
            .tradepartnerId("tradepartnerId1")
            .invoiceAmount(1L)
            .invoiceId("invoiceId1");
    }

    public static IndividualAssessment getIndividualAssessmentSample2() {
        return new IndividualAssessment()
            .id(2L)
            .assessmentId("assessmentId2")
            .finalverdict("finalverdict2")
            .creRequestId("creRequestId2")
            .timestamp("timestamp2")
            .tradepartnerGST("tradepartnerGST2")
            .tradepartnerId("tradepartnerId2")
            .invoiceAmount(2L)
            .invoiceId("invoiceId2");
    }

    public static IndividualAssessment getIndividualAssessmentRandomSampleGenerator() {
        return new IndividualAssessment()
            .id(longCount.incrementAndGet())
            .assessmentId(UUID.randomUUID().toString())
            .finalverdict(UUID.randomUUID().toString())
            .creRequestId(UUID.randomUUID().toString())
            .timestamp(UUID.randomUUID().toString())
            .tradepartnerGST(UUID.randomUUID().toString())
            .tradepartnerId(UUID.randomUUID().toString())
            .invoiceAmount(longCount.incrementAndGet())
            .invoiceId(UUID.randomUUID().toString());
    }
}
