package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ContextTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Context getContextSample1() {
        return new Context().id(1L).transactionId(1L).clientId(1L).cpCode("cpCode1");
    }

    public static Context getContextSample2() {
        return new Context().id(2L).transactionId(2L).clientId(2L).cpCode("cpCode2");
    }

    public static Context getContextRandomSampleGenerator() {
        return new Context()
            .id(longCount.incrementAndGet())
            .transactionId(longCount.incrementAndGet())
            .clientId(longCount.incrementAndGet())
            .cpCode(UUID.randomUUID().toString());
    }
}
