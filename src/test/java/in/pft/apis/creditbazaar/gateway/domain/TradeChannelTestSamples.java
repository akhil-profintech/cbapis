package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TradeChannelTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TradeChannel getTradeChannelSample1() {
        return new TradeChannel()
            .id(1L)
            .tradeChannelUlidId("tradeChannelUlidId1")
            .anchorTraderId("anchorTraderId1")
            .tradePartnerId("tradePartnerId1")
            .financePartnerId("financePartnerId1")
            .anchorTraderTenantId("anchorTraderTenantId1")
            .tradePartnerTenantId("tradePartnerTenantId1")
            .financePartnerTenantId("financePartnerTenantId1");
    }

    public static TradeChannel getTradeChannelSample2() {
        return new TradeChannel()
            .id(2L)
            .tradeChannelUlidId("tradeChannelUlidId2")
            .anchorTraderId("anchorTraderId2")
            .tradePartnerId("tradePartnerId2")
            .financePartnerId("financePartnerId2")
            .anchorTraderTenantId("anchorTraderTenantId2")
            .tradePartnerTenantId("tradePartnerTenantId2")
            .financePartnerTenantId("financePartnerTenantId2");
    }

    public static TradeChannel getTradeChannelRandomSampleGenerator() {
        return new TradeChannel()
            .id(longCount.incrementAndGet())
            .tradeChannelUlidId(UUID.randomUUID().toString())
            .anchorTraderId(UUID.randomUUID().toString())
            .tradePartnerId(UUID.randomUUID().toString())
            .financePartnerId(UUID.randomUUID().toString())
            .anchorTraderTenantId(UUID.randomUUID().toString())
            .tradePartnerTenantId(UUID.randomUUID().toString())
            .financePartnerTenantId(UUID.randomUUID().toString());
    }
}
