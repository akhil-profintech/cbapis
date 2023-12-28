package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DisbursementTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Disbursement getDisbursementSample1() {
        return new Disbursement()
            .id(1L)
            .dbmtId("dbmtId1")
            .disbursementRefNo("disbursementRefNo1")
            .acceptedOfferId(1L)
            .offerId(1L)
            .dbmtRequestId("dbmtRequestId1")
            .dbmtReqAmount(1L)
            .currency("currency1")
            .dbmtstatus("dbmtstatus1")
            .offerStatus("offerStatus1")
            .ftTrnxDetailsId("ftTrnxDetailsId1")
            .collectionTrnxDetailsId("collectionTrnxDetailsId1")
            .docId(1L)
            .dbmtDateTime("dbmtDateTime1")
            .dbmtAmount(1L)
            .financeRequestId("financeRequestId1")
            .amountToBeDisbursed("amountToBeDisbursed1")
            .destinationAccountName("destinationAccountName1")
            .destinationAccountNumber("destinationAccountNumber1")
            .ifscCode("ifscCode1")
            .status("status1")
            .actionByDate("actionByDate1");
    }

    public static Disbursement getDisbursementSample2() {
        return new Disbursement()
            .id(2L)
            .dbmtId("dbmtId2")
            .disbursementRefNo("disbursementRefNo2")
            .acceptedOfferId(2L)
            .offerId(2L)
            .dbmtRequestId("dbmtRequestId2")
            .dbmtReqAmount(2L)
            .currency("currency2")
            .dbmtstatus("dbmtstatus2")
            .offerStatus("offerStatus2")
            .ftTrnxDetailsId("ftTrnxDetailsId2")
            .collectionTrnxDetailsId("collectionTrnxDetailsId2")
            .docId(2L)
            .dbmtDateTime("dbmtDateTime2")
            .dbmtAmount(2L)
            .financeRequestId("financeRequestId2")
            .amountToBeDisbursed("amountToBeDisbursed2")
            .destinationAccountName("destinationAccountName2")
            .destinationAccountNumber("destinationAccountNumber2")
            .ifscCode("ifscCode2")
            .status("status2")
            .actionByDate("actionByDate2");
    }

    public static Disbursement getDisbursementRandomSampleGenerator() {
        return new Disbursement()
            .id(longCount.incrementAndGet())
            .dbmtId(UUID.randomUUID().toString())
            .disbursementRefNo(UUID.randomUUID().toString())
            .acceptedOfferId(longCount.incrementAndGet())
            .offerId(longCount.incrementAndGet())
            .dbmtRequestId(UUID.randomUUID().toString())
            .dbmtReqAmount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString())
            .dbmtstatus(UUID.randomUUID().toString())
            .offerStatus(UUID.randomUUID().toString())
            .ftTrnxDetailsId(UUID.randomUUID().toString())
            .collectionTrnxDetailsId(UUID.randomUUID().toString())
            .docId(longCount.incrementAndGet())
            .dbmtDateTime(UUID.randomUUID().toString())
            .dbmtAmount(longCount.incrementAndGet())
            .financeRequestId(UUID.randomUUID().toString())
            .amountToBeDisbursed(UUID.randomUUID().toString())
            .destinationAccountName(UUID.randomUUID().toString())
            .destinationAccountNumber(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .actionByDate(UUID.randomUUID().toString());
    }
}
