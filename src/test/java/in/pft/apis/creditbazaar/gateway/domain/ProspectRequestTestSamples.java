package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ProspectRequestTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ProspectRequest getProspectRequestSample1() {
        return new ProspectRequest()
            .id(1L)
            .prospectRequestId(1L)
            .prospectRequestUlidId("prospectRequestUlidId1")
            .anchorTraderId(1L)
            .requestAmount("requestAmount1")
            .currency("currency1");
    }

    public static ProspectRequest getProspectRequestSample2() {
        return new ProspectRequest()
            .id(2L)
            .prospectRequestId(2L)
            .prospectRequestUlidId("prospectRequestUlidId2")
            .anchorTraderId(2L)
            .requestAmount("requestAmount2")
            .currency("currency2");
    }

    public static ProspectRequest getProspectRequestRandomSampleGenerator() {
        return new ProspectRequest()
            .id(longCount.incrementAndGet())
            .prospectRequestId(longCount.incrementAndGet())
            .prospectRequestUlidId(UUID.randomUUID().toString())
            .anchorTraderId(longCount.incrementAndGet())
            .requestAmount(UUID.randomUUID().toString())
            .currency(UUID.randomUUID().toString());
    }
}
