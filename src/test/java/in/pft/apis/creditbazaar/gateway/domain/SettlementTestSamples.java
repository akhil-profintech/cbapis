package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class SettlementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Settlement getSettlementSample1() {
        return new Settlement()
            .id(1L)
            .settlementId(1L)
            .settlementUlidId("settlementUlidId1")
            .settlementRefNo("settlementRefNo1")
            .acceptedOfferUlidId("acceptedOfferUlidId1")
            .placedOfferUlidId("placedOfferUlidId1")
            .reqOffUlidId("reqOffUlidId1")
            .dbmtRequestId(1L)
            .dbmtId(1L)
            .dbmtDate("dbmtDate1")
            .dbmtStatus("dbmtStatus1")
            .dbmtAmount(1L)
            .currency("currency1")
            .recordStatus("recordStatus1");
    }

    public static Settlement getSettlementSample2() {
        return new Settlement()
            .id(2L)
            .settlementId(2L)
            .settlementUlidId("settlementUlidId2")
            .settlementRefNo("settlementRefNo2")
            .acceptedOfferUlidId("acceptedOfferUlidId2")
            .placedOfferUlidId("placedOfferUlidId2")
            .reqOffUlidId("reqOffUlidId2")
            .dbmtRequestId(2L)
            .dbmtId(2L)
            .dbmtDate("dbmtDate2")
            .dbmtStatus("dbmtStatus2")
            .dbmtAmount(2L)
            .currency("currency2")
            .recordStatus("recordStatus2");
    }

    public static Settlement getSettlementRandomSampleGenerator() {
        return new Settlement()
            .id(longCount.incrementAndGet())
            .settlementId(longCount.incrementAndGet())
            .settlementUlidId(UUID.randomUUID().toString())
            .settlementRefNo(UUID.randomUUID().toString())
            .acceptedOfferUlidId(UUID.randomUUID().toString())
            .placedOfferUlidId(UUID.randomUUID().toString())
            .reqOffUlidId(UUID.randomUUID().toString())
            .dbmtRequestId(longCount.incrementAndGet())
            .dbmtId(longCount.incrementAndGet())
            .dbmtDate(UUID.randomUUID().toString())
            .dbmtStatus(UUID.randomUUID().toString())
            .dbmtAmount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString());
    }
}
