package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TradePartnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TradePartner getTradePartnerSample1() {
        return new TradePartner()
            .id(1L)
            .tenantId("tenantId1")
            .tpId("tpId1")
            .orgId("orgId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L)
            .tradePartnerName("tradePartnerName1")
            .location("location1")
            .tradepartnerGST("tradepartnerGST1")
            .tradePartnerSector("tradePartnerSector1")
            .acceptanceFromTradePartner("acceptanceFromTradePartner1");
    }

    public static TradePartner getTradePartnerSample2() {
        return new TradePartner()
            .id(2L)
            .tenantId("tenantId2")
            .tpId("tpId2")
            .orgId("orgId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L)
            .tradePartnerName("tradePartnerName2")
            .location("location2")
            .tradepartnerGST("tradepartnerGST2")
            .tradePartnerSector("tradePartnerSector2")
            .acceptanceFromTradePartner("acceptanceFromTradePartner2");
    }

    public static TradePartner getTradePartnerRandomSampleGenerator() {
        return new TradePartner()
            .id(longCount.incrementAndGet())
            .tenantId(UUID.randomUUID().toString())
            .tpId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet())
            .tradePartnerName(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .tradepartnerGST(UUID.randomUUID().toString())
            .tradePartnerSector(UUID.randomUUID().toString())
            .acceptanceFromTradePartner(UUID.randomUUID().toString());
    }
}
