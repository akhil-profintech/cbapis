package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FinancePartnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FinancePartner getFinancePartnerSample1() {
        return new FinancePartner()
            .id(1L)
            .fpId(1L)
            .fpUlidId("fpUlidId1")
            .tenantId("tenantId1")
            .orgId("orgId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L)
            .tosDocument("tosDocument1");
    }

    public static FinancePartner getFinancePartnerSample2() {
        return new FinancePartner()
            .id(2L)
            .fpId(2L)
            .fpUlidId("fpUlidId2")
            .tenantId("tenantId2")
            .orgId("orgId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L)
            .tosDocument("tosDocument2");
    }

    public static FinancePartner getFinancePartnerRandomSampleGenerator() {
        return new FinancePartner()
            .id(longCount.incrementAndGet())
            .fpId(longCount.incrementAndGet())
            .fpUlidId(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet())
            .tosDocument(UUID.randomUUID().toString());
    }
}
