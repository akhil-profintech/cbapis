package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FinancePartnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FinancePartner getFinancePartnerSample1() {
        return new FinancePartner()
            .id(1L)
            .tenantId("tenantId1")
            .fpId("fpId1")
            .orgId("orgId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L);
    }

    public static FinancePartner getFinancePartnerSample2() {
        return new FinancePartner()
            .id(2L)
            .tenantId("tenantId2")
            .fpId("fpId2")
            .orgId("orgId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L);
    }

    public static FinancePartner getFinancePartnerRandomSampleGenerator() {
        return new FinancePartner()
            .id(longCount.incrementAndGet())
            .tenantId(UUID.randomUUID().toString())
            .fpId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet());
    }
}
