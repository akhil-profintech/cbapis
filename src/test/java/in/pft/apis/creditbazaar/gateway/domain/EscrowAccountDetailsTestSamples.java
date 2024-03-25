package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class EscrowAccountDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EscrowAccountDetails getEscrowAccountDetailsSample1() {
        return new EscrowAccountDetails()
            .id(1L)
            .escrowDetailsId(1L)
            .escrowDetailsUlidId("escrowDetailsUlidId1")
            .tenantId(1L)
            .customerId(1L)
            .accName("accName1")
            .ifscCode("ifscCode1")
            .virtualAccNumber("virtualAccNumber1")
            .poolingAccNumber(1L);
    }

    public static EscrowAccountDetails getEscrowAccountDetailsSample2() {
        return new EscrowAccountDetails()
            .id(2L)
            .escrowDetailsId(2L)
            .escrowDetailsUlidId("escrowDetailsUlidId2")
            .tenantId(2L)
            .customerId(2L)
            .accName("accName2")
            .ifscCode("ifscCode2")
            .virtualAccNumber("virtualAccNumber2")
            .poolingAccNumber(2L);
    }

    public static EscrowAccountDetails getEscrowAccountDetailsRandomSampleGenerator() {
        return new EscrowAccountDetails()
            .id(longCount.incrementAndGet())
            .escrowDetailsId(longCount.incrementAndGet())
            .escrowDetailsUlidId(UUID.randomUUID().toString())
            .tenantId(longCount.incrementAndGet())
            .customerId(longCount.incrementAndGet())
            .accName(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .virtualAccNumber(UUID.randomUUID().toString())
            .poolingAccNumber(longCount.incrementAndGet());
    }
}
