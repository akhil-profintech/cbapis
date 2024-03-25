package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CreditBazaarIntegratorTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CreditBazaarIntegrator getCreditBazaarIntegratorSample1() {
        return new CreditBazaarIntegrator()
            .id(1L)
            .integratorUlidId("integratorUlidId1")
            .tenantId("tenantId1")
            .orgId("orgId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L);
    }

    public static CreditBazaarIntegrator getCreditBazaarIntegratorSample2() {
        return new CreditBazaarIntegrator()
            .id(2L)
            .integratorUlidId("integratorUlidId2")
            .tenantId("tenantId2")
            .orgId("orgId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L);
    }

    public static CreditBazaarIntegrator getCreditBazaarIntegratorRandomSampleGenerator() {
        return new CreditBazaarIntegrator()
            .id(longCount.incrementAndGet())
            .integratorUlidId(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet());
    }
}
