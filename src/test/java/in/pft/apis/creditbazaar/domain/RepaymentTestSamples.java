package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RepaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Repayment getRepaymentSample1() {
        return new Repayment()
            .id(1L)
            .repaymentId("repaymentId1")
            .repaymentRefNo("repaymentRefNo1")
            .acceptedOfferId(1L)
            .offerId(1L)
            .dbmtRequestId("dbmtRequestId1")
            .dbmtstatus("dbmtstatus1")
            .dbmtDateTime("dbmtDateTime1")
            .dbmtId("dbmtId1")
            .dbmtAmount(1L)
            .currency("currency1")
            .repaymentStatus("repaymentStatus1")
            .repaymentAmount(1L)
            .ftTrnxDetailsId("ftTrnxDetailsId1")
            .collectionTrnxDetailsId("collectionTrnxDetailsId1")
            .docId(1L)
            .financeRequestId("financeRequestId1")
            .totalRepaymentAmount("totalRepaymentAmount1")
            .amountRepayed("amountRepayed1")
            .amountToBePaid("amountToBePaid1")
            .sourceAccountName("sourceAccountName1")
            .sourceAccountNumber("sourceAccountNumber1")
            .ifscCode("ifscCode1")
            .status("status1")
            .referenceNumber("referenceNumber1");
    }

    public static Repayment getRepaymentSample2() {
        return new Repayment()
            .id(2L)
            .repaymentId("repaymentId2")
            .repaymentRefNo("repaymentRefNo2")
            .acceptedOfferId(2L)
            .offerId(2L)
            .dbmtRequestId("dbmtRequestId2")
            .dbmtstatus("dbmtstatus2")
            .dbmtDateTime("dbmtDateTime2")
            .dbmtId("dbmtId2")
            .dbmtAmount(2L)
            .currency("currency2")
            .repaymentStatus("repaymentStatus2")
            .repaymentAmount(2L)
            .ftTrnxDetailsId("ftTrnxDetailsId2")
            .collectionTrnxDetailsId("collectionTrnxDetailsId2")
            .docId(2L)
            .financeRequestId("financeRequestId2")
            .totalRepaymentAmount("totalRepaymentAmount2")
            .amountRepayed("amountRepayed2")
            .amountToBePaid("amountToBePaid2")
            .sourceAccountName("sourceAccountName2")
            .sourceAccountNumber("sourceAccountNumber2")
            .ifscCode("ifscCode2")
            .status("status2")
            .referenceNumber("referenceNumber2");
    }

    public static Repayment getRepaymentRandomSampleGenerator() {
        return new Repayment()
            .id(longCount.incrementAndGet())
            .repaymentId(UUID.randomUUID().toString())
            .repaymentRefNo(UUID.randomUUID().toString())
            .acceptedOfferId(longCount.incrementAndGet())
            .offerId(longCount.incrementAndGet())
            .dbmtRequestId(UUID.randomUUID().toString())
            .dbmtstatus(UUID.randomUUID().toString())
            .dbmtDateTime(UUID.randomUUID().toString())
            .dbmtId(UUID.randomUUID().toString())
            .dbmtAmount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString())
            .repaymentStatus(UUID.randomUUID().toString())
            .repaymentAmount(longCount.incrementAndGet())
            .ftTrnxDetailsId(UUID.randomUUID().toString())
            .collectionTrnxDetailsId(UUID.randomUUID().toString())
            .docId(longCount.incrementAndGet())
            .financeRequestId(UUID.randomUUID().toString())
            .totalRepaymentAmount(UUID.randomUUID().toString())
            .amountRepayed(UUID.randomUUID().toString())
            .amountToBePaid(UUID.randomUUID().toString())
            .sourceAccountName(UUID.randomUUID().toString())
            .sourceAccountNumber(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .referenceNumber(UUID.randomUUID().toString());
    }
}
