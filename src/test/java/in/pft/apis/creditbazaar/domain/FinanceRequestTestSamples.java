package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FinanceRequestTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FinanceRequest getFinanceRequestSample1() {
        return new FinanceRequest()
            .id(1L)
            .requestId("requestId1")
            .financeRequestRefNo("financeRequestRefNo1")
            .requestAmount("requestAmount1")
            .currency("currency1")
            .requestStatus("requestStatus1");
    }

    public static FinanceRequest getFinanceRequestSample2() {
        return new FinanceRequest()
            .id(2L)
            .requestId("requestId2")
            .financeRequestRefNo("financeRequestRefNo2")
            .requestAmount("requestAmount2")
            .currency("currency2")
            .requestStatus("requestStatus2");
    }

    public static FinanceRequest getFinanceRequestRandomSampleGenerator() {
        return new FinanceRequest()
            .id(longCount.incrementAndGet())
            .requestId(UUID.randomUUID().toString())
            .financeRequestRefNo(UUID.randomUUID().toString())
            .requestAmount(UUID.randomUUID().toString())
            .currency(UUID.randomUUID().toString())
            .requestStatus(UUID.randomUUID().toString());
    }
}
