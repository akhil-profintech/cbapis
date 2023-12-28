package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CollectionTransactionDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CollectionTransactionDetails getCollectionTransactionDetailsSample1() {
        return new CollectionTransactionDetails()
            .id(1L)
            .trnxDetailsid(1L)
            .customerCode("customerCode1")
            .customerName("customerName1")
            .productCode("productCode1")
            .transactionType("transactionType1")
            .batchAmt("batchAmt1")
            .batchAmtCcd("batchAmtCcd1")
            .creditDate("creditDate1")
            .vaNumber("vaNumber1")
            .utrNo("utrNo1")
            .creditGenerationTime("creditGenerationTime1")
            .remitterName("remitterName1")
            .remitterAccountNumber("remitterAccountNumber1")
            .ifscCode("ifscCode1");
    }

    public static CollectionTransactionDetails getCollectionTransactionDetailsSample2() {
        return new CollectionTransactionDetails()
            .id(2L)
            .trnxDetailsid(2L)
            .customerCode("customerCode2")
            .customerName("customerName2")
            .productCode("productCode2")
            .transactionType("transactionType2")
            .batchAmt("batchAmt2")
            .batchAmtCcd("batchAmtCcd2")
            .creditDate("creditDate2")
            .vaNumber("vaNumber2")
            .utrNo("utrNo2")
            .creditGenerationTime("creditGenerationTime2")
            .remitterName("remitterName2")
            .remitterAccountNumber("remitterAccountNumber2")
            .ifscCode("ifscCode2");
    }

    public static CollectionTransactionDetails getCollectionTransactionDetailsRandomSampleGenerator() {
        return new CollectionTransactionDetails()
            .id(longCount.incrementAndGet())
            .trnxDetailsid(longCount.incrementAndGet())
            .customerCode(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .productCode(UUID.randomUUID().toString())
            .transactionType(UUID.randomUUID().toString())
            .batchAmt(UUID.randomUUID().toString())
            .batchAmtCcd(UUID.randomUUID().toString())
            .creditDate(UUID.randomUUID().toString())
            .vaNumber(UUID.randomUUID().toString())
            .utrNo(UUID.randomUUID().toString())
            .creditGenerationTime(UUID.randomUUID().toString())
            .remitterName(UUID.randomUUID().toString())
            .remitterAccountNumber(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString());
    }
}
