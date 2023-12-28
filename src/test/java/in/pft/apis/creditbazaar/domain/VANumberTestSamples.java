package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class VANumberTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static VANumber getVANumberSample1() {
        return new VANumber()
            .id(1L)
            .vaId(1L)
            .poolingAccNumber("poolingAccNumber1")
            .virtualAccNumber("virtualAccNumber1")
            .vaStatus("vaStatus1")
            .financeRequestId("financeRequestId1")
            .subGroupId("subGroupId1");
    }

    public static VANumber getVANumberSample2() {
        return new VANumber()
            .id(2L)
            .vaId(2L)
            .poolingAccNumber("poolingAccNumber2")
            .virtualAccNumber("virtualAccNumber2")
            .vaStatus("vaStatus2")
            .financeRequestId("financeRequestId2")
            .subGroupId("subGroupId2");
    }

    public static VANumber getVANumberRandomSampleGenerator() {
        return new VANumber()
            .id(longCount.incrementAndGet())
            .vaId(longCount.incrementAndGet())
            .poolingAccNumber(UUID.randomUUID().toString())
            .virtualAccNumber(UUID.randomUUID().toString())
            .vaStatus(UUID.randomUUID().toString())
            .financeRequestId(UUID.randomUUID().toString())
            .subGroupId(UUID.randomUUID().toString());
    }
}
