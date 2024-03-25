package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class BeneValidationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static BeneValidation getBeneValidationSample1() {
        return new BeneValidation()
            .id(1L)
            .benevalidationId("benevalidationId1")
            .finReqId("finReqId1")
            .subGrpId("subGrpId1")
            .remitterName("remitterName1")
            .remitterMobileNumber("remitterMobileNumber1")
            .debtorAccountId("debtorAccountId1")
            .accountType("accountType1")
            .creditorAccountId("creditorAccountId1")
            .ifscCode("ifscCode1")
            .paymentDescription("paymentDescription1")
            .transactionReferenceNumber("transactionReferenceNumber1")
            .merchantCode("merchantCode1")
            .identifier("identifier1")
            .requestDateTime("requestDateTime1")
            .metaDataStatus("metaDataStatus1")
            .metaDataMessage("metaDataMessage1")
            .metaDataVersion("metaDataVersion1")
            .metaDataTime("metaDataTime1")
            .resourceDataCreditorAccountId("resourceDataCreditorAccountId1")
            .resourceDataCreditorName("resourceDataCreditorName1")
            .resourceDataTransactionReferenceNumber("resourceDataTransactionReferenceNumber1")
            .resourceDataTransactionId("resourceDataTransactionId1")
            .resourceDataResponseCode("resourceDataResponseCode1")
            .resourceDataTransactionTime("resourceDataTransactionTime1")
            .resourceDataIdentifier("resourceDataIdentifier1")
            .responseDateTime("responseDateTime1")
            .lastupdatedDateTime("lastupdatedDateTime1")
            .lastUpdatedBy("lastUpdatedBy1");
    }

    public static BeneValidation getBeneValidationSample2() {
        return new BeneValidation()
            .id(2L)
            .benevalidationId("benevalidationId2")
            .finReqId("finReqId2")
            .subGrpId("subGrpId2")
            .remitterName("remitterName2")
            .remitterMobileNumber("remitterMobileNumber2")
            .debtorAccountId("debtorAccountId2")
            .accountType("accountType2")
            .creditorAccountId("creditorAccountId2")
            .ifscCode("ifscCode2")
            .paymentDescription("paymentDescription2")
            .transactionReferenceNumber("transactionReferenceNumber2")
            .merchantCode("merchantCode2")
            .identifier("identifier2")
            .requestDateTime("requestDateTime2")
            .metaDataStatus("metaDataStatus2")
            .metaDataMessage("metaDataMessage2")
            .metaDataVersion("metaDataVersion2")
            .metaDataTime("metaDataTime2")
            .resourceDataCreditorAccountId("resourceDataCreditorAccountId2")
            .resourceDataCreditorName("resourceDataCreditorName2")
            .resourceDataTransactionReferenceNumber("resourceDataTransactionReferenceNumber2")
            .resourceDataTransactionId("resourceDataTransactionId2")
            .resourceDataResponseCode("resourceDataResponseCode2")
            .resourceDataTransactionTime("resourceDataTransactionTime2")
            .resourceDataIdentifier("resourceDataIdentifier2")
            .responseDateTime("responseDateTime2")
            .lastupdatedDateTime("lastupdatedDateTime2")
            .lastUpdatedBy("lastUpdatedBy2");
    }

    public static BeneValidation getBeneValidationRandomSampleGenerator() {
        return new BeneValidation()
            .id(longCount.incrementAndGet())
            .benevalidationId(UUID.randomUUID().toString())
            .finReqId(UUID.randomUUID().toString())
            .subGrpId(UUID.randomUUID().toString())
            .remitterName(UUID.randomUUID().toString())
            .remitterMobileNumber(UUID.randomUUID().toString())
            .debtorAccountId(UUID.randomUUID().toString())
            .accountType(UUID.randomUUID().toString())
            .creditorAccountId(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .paymentDescription(UUID.randomUUID().toString())
            .transactionReferenceNumber(UUID.randomUUID().toString())
            .merchantCode(UUID.randomUUID().toString())
            .identifier(UUID.randomUUID().toString())
            .requestDateTime(UUID.randomUUID().toString())
            .metaDataStatus(UUID.randomUUID().toString())
            .metaDataMessage(UUID.randomUUID().toString())
            .metaDataVersion(UUID.randomUUID().toString())
            .metaDataTime(UUID.randomUUID().toString())
            .resourceDataCreditorAccountId(UUID.randomUUID().toString())
            .resourceDataCreditorName(UUID.randomUUID().toString())
            .resourceDataTransactionReferenceNumber(UUID.randomUUID().toString())
            .resourceDataTransactionId(UUID.randomUUID().toString())
            .resourceDataResponseCode(UUID.randomUUID().toString())
            .resourceDataTransactionTime(UUID.randomUUID().toString())
            .resourceDataIdentifier(UUID.randomUUID().toString())
            .responseDateTime(UUID.randomUUID().toString())
            .lastupdatedDateTime(UUID.randomUUID().toString())
            .lastUpdatedBy(UUID.randomUUID().toString());
    }
}
