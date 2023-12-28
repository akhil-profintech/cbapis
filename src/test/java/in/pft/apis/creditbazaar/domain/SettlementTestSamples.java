package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SettlementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Settlement getSettlementSample1() {
        return new Settlement()
            .id(1L)
            .settlementId("settlementId1")
            .settlementRefNo("settlementRefNo1")
            .offerId(1L)
            .dbmtRequestId(1L)
            .dbmtId(1L)
            .dbmtDate("dbmtDate1")
            .dbmtStatus("dbmtStatus1")
            .dbmtAmount(1L)
            .currency("currency1");
    }

    public static Settlement getSettlementSample2() {
        return new Settlement()
            .id(2L)
            .settlementId("settlementId2")
            .settlementRefNo("settlementRefNo2")
            .offerId(2L)
            .dbmtRequestId(2L)
            .dbmtId(2L)
            .dbmtDate("dbmtDate2")
            .dbmtStatus("dbmtStatus2")
            .dbmtAmount(2L)
            .currency("currency2");
    }

    public static Settlement getSettlementRandomSampleGenerator() {
        return new Settlement()
            .id(longCount.incrementAndGet())
            .settlementId(UUID.randomUUID().toString())
            .settlementRefNo(UUID.randomUUID().toString())
            .offerId(longCount.incrementAndGet())
            .dbmtRequestId(longCount.incrementAndGet())
            .dbmtId(longCount.incrementAndGet())
            .dbmtDate(UUID.randomUUID().toString())
            .dbmtStatus(UUID.randomUUID().toString())
            .dbmtAmount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString());
    }
}
