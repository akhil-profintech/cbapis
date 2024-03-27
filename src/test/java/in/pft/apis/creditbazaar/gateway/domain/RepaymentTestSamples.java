package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RepaymentTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Repayment getRepaymentSample1() {
        return new Repayment()
            .id(1L)
            .repaymentId(1L)
            .repaymentUlidId("repaymentUlidId1")
            .repaymentRefNo("repaymentRefNo1")
            .acceptedOfferUlidId("acceptedOfferUlidId1")
            .placedOfferUlidId("placedOfferUlidId1")
            .reqOffUlidId("reqOffUlidId1")
            .dbmtRequestId("dbmtRequestId1")
            .dbmtStatus("dbmtStatus1")
            .dbmtDateTime("dbmtDateTime1")
            .dbmtId(1L)
            .dbmtAmount(1L)
            .currency("currency1")
            .repaymentStatus("repaymentStatus1")
            .repaymentAmount(1L)
            .financeRequestId(1L)
            .totalRepaymentAmount("totalRepaymentAmount1")
            .amountRepayed("amountRepayed1")
            .amountToBePaid("amountToBePaid1")
            .sourceAccountName("sourceAccountName1")
            .sourceAccountNumber("sourceAccountNumber1")
            .ifscCode("ifscCode1")
            .recordStatus("recordStatus1")
            .referenceNumber("referenceNumber1");
    }

    public static Repayment getRepaymentSample2() {
        return new Repayment()
            .id(2L)
            .repaymentId(2L)
            .repaymentUlidId("repaymentUlidId2")
            .repaymentRefNo("repaymentRefNo2")
            .acceptedOfferUlidId("acceptedOfferUlidId2")
            .placedOfferUlidId("placedOfferUlidId2")
            .reqOffUlidId("reqOffUlidId2")
            .dbmtRequestId("dbmtRequestId2")
            .dbmtStatus("dbmtStatus2")
            .dbmtDateTime("dbmtDateTime2")
            .dbmtId(2L)
            .dbmtAmount(2L)
            .currency("currency2")
            .repaymentStatus("repaymentStatus2")
            .repaymentAmount(2L)
            .financeRequestId(2L)
            .totalRepaymentAmount("totalRepaymentAmount2")
            .amountRepayed("amountRepayed2")
            .amountToBePaid("amountToBePaid2")
            .sourceAccountName("sourceAccountName2")
            .sourceAccountNumber("sourceAccountNumber2")
            .ifscCode("ifscCode2")
            .recordStatus("recordStatus2")
            .referenceNumber("referenceNumber2");
    }

    public static Repayment getRepaymentRandomSampleGenerator() {
        return new Repayment()
            .id(longCount.incrementAndGet())
            .repaymentId(longCount.incrementAndGet())
            .repaymentUlidId(UUID.randomUUID().toString())
            .repaymentRefNo(UUID.randomUUID().toString())
            .acceptedOfferUlidId(UUID.randomUUID().toString())
            .placedOfferUlidId(UUID.randomUUID().toString())
            .reqOffUlidId(UUID.randomUUID().toString())
            .dbmtRequestId(UUID.randomUUID().toString())
            .dbmtStatus(UUID.randomUUID().toString())
            .dbmtDateTime(UUID.randomUUID().toString())
            .dbmtId(longCount.incrementAndGet())
            .dbmtAmount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString())
            .repaymentStatus(UUID.randomUUID().toString())
            .repaymentAmount(longCount.incrementAndGet())
            .financeRequestId(longCount.incrementAndGet())
            .totalRepaymentAmount(UUID.randomUUID().toString())
            .amountRepayed(UUID.randomUUID().toString())
            .amountToBePaid(UUID.randomUUID().toString())
            .sourceAccountName(UUID.randomUUID().toString())
            .sourceAccountNumber(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .recordStatus(UUID.randomUUID().toString())
            .referenceNumber(UUID.randomUUID().toString());
    }
}
