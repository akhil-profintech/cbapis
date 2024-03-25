package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class ClientCodesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ClientCodes getClientCodesSample1() {
        return new ClientCodes().id(1L).clientCode(1L).clientCharsCode("clientCharsCode1").clientName("clientName1");
    }

    public static ClientCodes getClientCodesSample2() {
        return new ClientCodes().id(2L).clientCode(2L).clientCharsCode("clientCharsCode2").clientName("clientName2");
    }

    public static ClientCodes getClientCodesRandomSampleGenerator() {
        return new ClientCodes()
            .id(longCount.incrementAndGet())
            .clientCode(longCount.incrementAndGet())
            .clientCharsCode(UUID.randomUUID().toString())
            .clientName(UUID.randomUUID().toString());
    }
}
