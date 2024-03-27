package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DisbursementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Disbursement getDisbursementSample1() {
        return new Disbursement()
            .id(1L)
            .dbmtId(1L)
            .disbursementUlidId("disbursementUlidId1")
            .disbursementRefNo("disbursementRefNo1")
            .acceptedOfferUlidId("acceptedOfferUlidId1")
            .placedOfferUlidId("placedOfferUlidId1")
            .reqOffUlidId("reqOffUlidId1")
            .dbmtRequestId("dbmtRequestId1")
            .dbmtReqAmount(1L)
            .currency("currency1")
            .dbmtStatus("dbmtStatus1")
            .offerStatus("offerStatus1")
            .docId(1L)
            .dbmtDateTime("dbmtDateTime1")
            .dbmtAmount(1L)
            .financeRequestId(1L)
            .amountToBeDisbursed("amountToBeDisbursed1")
            .destinationAccountName("destinationAccountName1")
            .destinationAccountNumber("destinationAccountNumber1")
            .recordStatus("recordStatus1")
            .actionByDate("actionByDate1");
    }

    public static Disbursement getDisbursementSample2() {
        return new Disbursement()
            .id(2L)
            .dbmtId(2L)
            .disbursementUlidId("disbursementUlidId2")
            .disbursementRefNo("disbursementRefNo2")
            .acceptedOfferUlidId("acceptedOfferUlidId2")
            .placedOfferUlidId("placedOfferUlidId2")
            .reqOffUlidId("reqOffUlidId2")
            .dbmtRequestId("dbmtRequestId2")
            .dbmtReqAmount(2L)
            .currency("currency2")
            .dbmtStatus("dbmtStatus2")
            .offerStatus("offerStatus2")
            .docId(2L)
            .dbmtDateTime("dbmtDateTime2")
            .dbmtAmount(2L)
            .financeRequestId(2L)
            .amountToBeDisbursed("amountToBeDisbursed2")
            .destinationAccountName("destinationAccountName2")
            .destinationAccountNumber("destinationAccountNumber2")
            .recordStatus("recordStatus2")
            .actionByDate("actionByDate2");
    }

    public static Disbursement getDisbursementRandomSampleGenerator() {
        return new Disbursement()
            .id(longCount.incrementAndGet())
            .dbmtId(longCount.incrementAndGet())
            .disbursementUlidId(UUID.randomUUID().toString())
            .disbursementRefNo(UUID.randomUUID().toString())
            .acceptedOfferUlidId(UUID.randomUUID().toString())
            .placedOfferUlidId(UUID.randomUUID().toString())
            .reqOffUlidId(UUID.randomUUID().toString())
            .dbmtRequestId(UUID.randomUUID().toString())
            .dbmtReqAmount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString())
            .dbmtStatus(UUID.randomUUID().toString())
            .offerStatus(UUID.randomUUID().toString())
            .docId(longCount.incrementAndGet())
            .dbmtDateTime(UUID.randomUUID().toString())
            .dbmtAmount(longCount.incrementAndGet())
            .financeRequestId(longCount.incrementAndGet())
            .amountToBeDisbursed(UUID.randomUUID().toString())
            .destinationAccountName(UUID.randomUUID().toString())
            .destinationAccountNumber(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString())
            .actionByDate(UUID.randomUUID().toString());
    }
}
