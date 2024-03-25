package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TenantDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static TenantDetails getTenantDetailsSample1() {
        return new TenantDetails().id(1L).tenantUlidId("tenantUlidId1").tenantSchema("tenantSchema1");
    }

    public static TenantDetails getTenantDetailsSample2() {
        return new TenantDetails().id(2L).tenantUlidId("tenantUlidId2").tenantSchema("tenantSchema2");
    }

    public static TenantDetails getTenantDetailsRandomSampleGenerator() {
        return new TenantDetails()
            .id(longCount.incrementAndGet())
            .tenantUlidId(UUID.randomUUID().toString())
            .tenantSchema(UUID.randomUUID().toString());
    }
}
