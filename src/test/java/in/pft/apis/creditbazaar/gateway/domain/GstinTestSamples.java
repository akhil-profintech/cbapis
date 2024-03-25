package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class GstinTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Gstin getGstinSample1() {
        return new Gstin().id(1L).companyName("companyName1").gstId("gstId1");
    }

    public static Gstin getGstinSample2() {
        return new Gstin().id(2L).companyName("companyName2").gstId("gstId2");
    }

    public static Gstin getGstinRandomSampleGenerator() {
        return new Gstin().id(longCount.incrementAndGet()).companyName(UUID.randomUUID().toString()).gstId(UUID.randomUUID().toString());
    }
}
