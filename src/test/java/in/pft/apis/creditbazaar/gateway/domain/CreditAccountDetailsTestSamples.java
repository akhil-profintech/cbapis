package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CreditAccountDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CreditAccountDetails getCreditAccountDetailsSample1() {
        return new CreditAccountDetails()
            .id(1L)
            .creditAccDetailsId(1L)
            .creditAccountDetailsUlidId("creditAccountDetailsUlidId1")
            .tenantId(1L)
            .customerId(1L)
            .accName("accName1")
            .ifscCode("ifscCode1")
            .accNumber(1L)
            .destinationAccountName("destinationAccountName1")
            .destinationAccountNumber("destinationAccountNumber1");
    }

    public static CreditAccountDetails getCreditAccountDetailsSample2() {
        return new CreditAccountDetails()
            .id(2L)
            .creditAccDetailsId(2L)
            .creditAccountDetailsUlidId("creditAccountDetailsUlidId2")
            .tenantId(2L)
            .customerId(2L)
            .accName("accName2")
            .ifscCode("ifscCode2")
            .accNumber(2L)
            .destinationAccountName("destinationAccountName2")
            .destinationAccountNumber("destinationAccountNumber2");
    }

    public static CreditAccountDetails getCreditAccountDetailsRandomSampleGenerator() {
        return new CreditAccountDetails()
            .id(longCount.incrementAndGet())
            .creditAccDetailsId(longCount.incrementAndGet())
            .creditAccountDetailsUlidId(UUID.randomUUID().toString())
            .tenantId(longCount.incrementAndGet())
            .customerId(longCount.incrementAndGet())
            .accName(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .accNumber(longCount.incrementAndGet())
            .destinationAccountName(UUID.randomUUID().toString())
            .destinationAccountNumber(UUID.randomUUID().toString());
    }
}
