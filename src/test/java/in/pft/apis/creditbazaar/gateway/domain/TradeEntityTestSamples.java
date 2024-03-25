package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TradeEntityTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TradeEntity getTradeEntitySample1() {
        return new TradeEntity()
            .id(1L)
            .entityId(1L)
            .entityUlidId("entityUlidId1")
            .entityName("entityName1")
            .entityDesc("entityDesc1")
            .entityGST("entityGST1");
    }

    public static TradeEntity getTradeEntitySample2() {
        return new TradeEntity()
            .id(2L)
            .entityId(2L)
            .entityUlidId("entityUlidId2")
            .entityName("entityName2")
            .entityDesc("entityDesc2")
            .entityGST("entityGST2");
    }

    public static TradeEntity getTradeEntityRandomSampleGenerator() {
        return new TradeEntity()
            .id(longCount.incrementAndGet())
            .entityId(longCount.incrementAndGet())
            .entityUlidId(UUID.randomUUID().toString())
            .entityName(UUID.randomUUID().toString())
            .entityDesc(UUID.randomUUID().toString())
            .entityGST(UUID.randomUUID().toString());
    }
}
