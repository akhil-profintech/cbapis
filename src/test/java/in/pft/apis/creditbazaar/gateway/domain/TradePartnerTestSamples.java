package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TradePartnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TradePartner getTradePartnerSample1() {
        return new TradePartner()
            .id(1L)
            .tpId(1L)
            .tpUlidId("tpUlidId1")
            .orgId("orgId1")
            .tenantId("tenantId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L)
            .tradePartnerName("tradePartnerName1")
            .location("location1")
            .tradePartnerGST("tradePartnerGST1")
            .tradePartnerSector("tradePartnerSector1")
            .acceptanceFromTradePartner("acceptanceFromTradePartner1")
            .tosDocument("tosDocument1");
    }

    public static TradePartner getTradePartnerSample2() {
        return new TradePartner()
            .id(2L)
            .tpId(2L)
            .tpUlidId("tpUlidId2")
            .orgId("orgId2")
            .tenantId("tenantId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L)
            .tradePartnerName("tradePartnerName2")
            .location("location2")
            .tradePartnerGST("tradePartnerGST2")
            .tradePartnerSector("tradePartnerSector2")
            .acceptanceFromTradePartner("acceptanceFromTradePartner2")
            .tosDocument("tosDocument2");
    }

    public static TradePartner getTradePartnerRandomSampleGenerator() {
        return new TradePartner()
            .id(longCount.incrementAndGet())
            .tpId(longCount.incrementAndGet())
            .tpUlidId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet())
            .tradePartnerName(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .tradePartnerGST(UUID.randomUUID().toString())
            .tradePartnerSector(UUID.randomUUID().toString())
            .acceptanceFromTradePartner(UUID.randomUUID().toString())
            .tosDocument(UUID.randomUUID().toString());
    }
}
