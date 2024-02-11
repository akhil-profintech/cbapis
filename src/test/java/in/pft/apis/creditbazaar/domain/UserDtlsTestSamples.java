package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UserDtlsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static UserDtls getUserDtlsSample1() {
        return new UserDtls()
            .id(1L)
            .userId("userId1")
            .userName("userName1")
            .tenantId("tenantId1")
            .anchorTraderId("anchorTraderId1")
            .tradePartnerId("tradePartnerId1")
            .financePartnerId("financePartnerId1");
    }

    public static UserDtls getUserDtlsSample2() {
        return new UserDtls()
            .id(2L)
            .userId("userId2")
            .userName("userName2")
            .tenantId("tenantId2")
            .anchorTraderId("anchorTraderId2")
            .tradePartnerId("tradePartnerId2")
            .financePartnerId("financePartnerId2");
    }

    public static UserDtls getUserDtlsRandomSampleGenerator() {
        return new UserDtls()
            .id(longCount.incrementAndGet())
            .userId(UUID.randomUUID().toString())
            .userName(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString())
            .anchorTraderId(UUID.randomUUID().toString())
            .tradePartnerId(UUID.randomUUID().toString())
            .financePartnerId(UUID.randomUUID().toString());
    }
}
