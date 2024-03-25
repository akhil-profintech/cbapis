package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ContextTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Context getContextSample1() {
        return new Context()
            .id(1L)
            .transactionId("transactionId1")
            .transactionDate("transactionDate1")
            .action("action1")
            .userId("userId1")
            .tenantId("tenantId1")
            .cpCode("cpCode1")
            .msgpayload("msgpayload1");
    }

    public static Context getContextSample2() {
        return new Context()
            .id(2L)
            .transactionId("transactionId2")
            .transactionDate("transactionDate2")
            .action("action2")
            .userId("userId2")
            .tenantId("tenantId2")
            .cpCode("cpCode2")
            .msgpayload("msgpayload2");
    }

    public static Context getContextRandomSampleGenerator() {
        return new Context()
            .id(longCount.incrementAndGet())
            .transactionId(UUID.randomUUID().toString())
            .transactionDate(UUID.randomUUID().toString())
            .action(UUID.randomUUID().toString())
            .userId(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString())
            .cpCode(UUID.randomUUID().toString())
            .msgpayload(UUID.randomUUID().toString());
    }
}
