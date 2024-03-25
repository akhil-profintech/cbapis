package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ActionTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Action getActionSample1() {
        return new Action()
            .id(1L)
            .actionId("actionId1")
            .actionCode("actionCode1")
            .actionDescription("actionDescription1")
            .actionVal("actionVal1")
            .cpCode("cpCode1");
    }

    public static Action getActionSample2() {
        return new Action()
            .id(2L)
            .actionId("actionId2")
            .actionCode("actionCode2")
            .actionDescription("actionDescription2")
            .actionVal("actionVal2")
            .cpCode("cpCode2");
    }

    public static Action getActionRandomSampleGenerator() {
        return new Action()
            .id(longCount.incrementAndGet())
            .actionId(UUID.randomUUID().toString())
            .actionCode(UUID.randomUUID().toString())
            .actionDescription(UUID.randomUUID().toString())
            .actionVal(UUID.randomUUID().toString())
            .cpCode(UUID.randomUUID().toString());
    }
}
