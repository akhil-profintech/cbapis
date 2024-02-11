package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FinanceRequestMappingTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FinanceRequestMapping getFinanceRequestMappingSample1() {
        return new FinanceRequestMapping()
            .id(1L)
            .financeRequestId("financeRequestId1")
            .anchorTraderId("anchorTraderId1")
            .financePartnerId("financePartnerId1")
            .anchorTraderTenantId("anchorTraderTenantId1")
            .financePartnerTenantId("financePartnerTenantId1");
    }

    public static FinanceRequestMapping getFinanceRequestMappingSample2() {
        return new FinanceRequestMapping()
            .id(2L)
            .financeRequestId("financeRequestId2")
            .anchorTraderId("anchorTraderId2")
            .financePartnerId("financePartnerId2")
            .anchorTraderTenantId("anchorTraderTenantId2")
            .financePartnerTenantId("financePartnerTenantId2");
    }

    public static FinanceRequestMapping getFinanceRequestMappingRandomSampleGenerator() {
        return new FinanceRequestMapping()
            .id(longCount.incrementAndGet())
            .financeRequestId(UUID.randomUUID().toString())
            .anchorTraderId(UUID.randomUUID().toString())
            .financePartnerId(UUID.randomUUID().toString())
            .anchorTraderTenantId(UUID.randomUUID().toString())
            .financePartnerTenantId(UUID.randomUUID().toString());
    }
}
