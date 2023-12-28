package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InstaAlertTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static InstaAlert getInstaAlertSample1() {
        return new InstaAlert()
            .id(1L)
            .instaAlertId(1L)
            .finReqId("finReqId1")
            .subGrpId("subGrpId1")
            .customerCode("customerCode1")
            .customerName("customerName1")
            .productCode("productCode1")
            .poolingAccountNumber("poolingAccountNumber1")
            .transactionType("transactionType1")
            .batchAmt(1L)
            .batchAmtCcd("batchAmtCcd1")
            .creditDate("creditDate1")
            .vaNumber("vaNumber1")
            .utrNo("utrNo1")
            .creditGenerationTime("creditGenerationTime1")
            .remitterName("remitterName1")
            .remitterAccountNumber("remitterAccountNumber1")
            .ifscCode("ifscCode1")
            .lastupdatedDateTime("lastupdatedDateTime1")
            .lastUpdatedBy("lastUpdatedBy1");
    }

    public static InstaAlert getInstaAlertSample2() {
        return new InstaAlert()
            .id(2L)
            .instaAlertId(2L)
            .finReqId("finReqId2")
            .subGrpId("subGrpId2")
            .customerCode("customerCode2")
            .customerName("customerName2")
            .productCode("productCode2")
            .poolingAccountNumber("poolingAccountNumber2")
            .transactionType("transactionType2")
            .batchAmt(2L)
            .batchAmtCcd("batchAmtCcd2")
            .creditDate("creditDate2")
            .vaNumber("vaNumber2")
            .utrNo("utrNo2")
            .creditGenerationTime("creditGenerationTime2")
            .remitterName("remitterName2")
            .remitterAccountNumber("remitterAccountNumber2")
            .ifscCode("ifscCode2")
            .lastupdatedDateTime("lastupdatedDateTime2")
            .lastUpdatedBy("lastUpdatedBy2");
    }

    public static InstaAlert getInstaAlertRandomSampleGenerator() {
        return new InstaAlert()
            .id(longCount.incrementAndGet())
            .instaAlertId(longCount.incrementAndGet())
            .finReqId(UUID.randomUUID().toString())
            .subGrpId(UUID.randomUUID().toString())
            .customerCode(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .productCode(UUID.randomUUID().toString())
            .poolingAccountNumber(UUID.randomUUID().toString())
            .transactionType(UUID.randomUUID().toString())
            .batchAmt(longCount.incrementAndGet())
            .batchAmtCcd(UUID.randomUUID().toString())
            .creditDate(UUID.randomUUID().toString())
            .vaNumber(UUID.randomUUID().toString())
            .utrNo(UUID.randomUUID().toString())
            .creditGenerationTime(UUID.randomUUID().toString())
            .remitterName(UUID.randomUUID().toString())
            .remitterAccountNumber(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .lastupdatedDateTime(UUID.randomUUID().toString())
            .lastUpdatedBy(UUID.randomUUID().toString());
    }
}
