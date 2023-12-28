package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AnchorTraderTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AnchorTrader getAnchorTraderSample1() {
        return new AnchorTrader()
            .id(1L)
            .tenantId("tenantId1")
            .atId("atId1")
            .orgId("orgId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L)
            .anchorTraderName("anchorTraderName1")
            .location("location1")
            .anchorTraderGST("anchorTraderGST1")
            .anchorTraderSector("anchorTraderSector1")
            .anchorTraderPAN("anchorTraderPAN1")
            .kycCompleted("kycCompleted1")
            .bankDetails("bankDetails1");
    }

    public static AnchorTrader getAnchorTraderSample2() {
        return new AnchorTrader()
            .id(2L)
            .tenantId("tenantId2")
            .atId("atId2")
            .orgId("orgId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L)
            .anchorTraderName("anchorTraderName2")
            .location("location2")
            .anchorTraderGST("anchorTraderGST2")
            .anchorTraderSector("anchorTraderSector2")
            .anchorTraderPAN("anchorTraderPAN2")
            .kycCompleted("kycCompleted2")
            .bankDetails("bankDetails2");
    }

    public static AnchorTrader getAnchorTraderRandomSampleGenerator() {
        return new AnchorTrader()
            .id(longCount.incrementAndGet())
            .tenantId(UUID.randomUUID().toString())
            .atId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet())
            .anchorTraderName(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .anchorTraderGST(UUID.randomUUID().toString())
            .anchorTraderSector(UUID.randomUUID().toString())
            .anchorTraderPAN(UUID.randomUUID().toString())
            .kycCompleted(UUID.randomUUID().toString())
            .bankDetails(UUID.randomUUID().toString());
    }
}
