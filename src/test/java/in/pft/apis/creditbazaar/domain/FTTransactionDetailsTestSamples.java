package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FTTransactionDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FTTransactionDetails getFTTransactionDetailsSample1() {
        return new FTTransactionDetails()
            .id(1L)
            .trnxDetailsId(1L)
            .transactionID(1L)
            .debitAccountNumber("debitAccountNumber1")
            .creditAccountNumber("creditAccountNumber1")
            .remitterName("remitterName1")
            .amount(1L)
            .currency("currency1")
            .transactionType("transactionType1")
            .paymentDescription("paymentDescription1")
            .beneficiaryIFSC("beneficiaryIFSC1")
            .beneficiaryName("beneficiaryName1")
            .beneficiaryAddress("beneficiaryAddress1")
            .emailId("emailId1")
            .mobileNo(1L)
            .transactionRefNo("transactionRefNo1")
            .trnxResourceDataStatus("trnxResourceDataStatus1")
            .trnxMetaDataStatus("trnxMetaDataStatus1")
            .transactionDateTime("transactionDateTime1");
    }

    public static FTTransactionDetails getFTTransactionDetailsSample2() {
        return new FTTransactionDetails()
            .id(2L)
            .trnxDetailsId(2L)
            .transactionID(2L)
            .debitAccountNumber("debitAccountNumber2")
            .creditAccountNumber("creditAccountNumber2")
            .remitterName("remitterName2")
            .amount(2L)
            .currency("currency2")
            .transactionType("transactionType2")
            .paymentDescription("paymentDescription2")
            .beneficiaryIFSC("beneficiaryIFSC2")
            .beneficiaryName("beneficiaryName2")
            .beneficiaryAddress("beneficiaryAddress2")
            .emailId("emailId2")
            .mobileNo(2L)
            .transactionRefNo("transactionRefNo2")
            .trnxResourceDataStatus("trnxResourceDataStatus2")
            .trnxMetaDataStatus("trnxMetaDataStatus2")
            .transactionDateTime("transactionDateTime2");
    }

    public static FTTransactionDetails getFTTransactionDetailsRandomSampleGenerator() {
        return new FTTransactionDetails()
            .id(longCount.incrementAndGet())
            .trnxDetailsId(longCount.incrementAndGet())
            .transactionID(longCount.incrementAndGet())
            .debitAccountNumber(UUID.randomUUID().toString())
            .creditAccountNumber(UUID.randomUUID().toString())
            .remitterName(UUID.randomUUID().toString())
            .amount(longCount.incrementAndGet())
            .currency(UUID.randomUUID().toString())
            .transactionType(UUID.randomUUID().toString())
            .paymentDescription(UUID.randomUUID().toString())
            .beneficiaryIFSC(UUID.randomUUID().toString())
            .beneficiaryName(UUID.randomUUID().toString())
            .beneficiaryAddress(UUID.randomUUID().toString())
            .emailId(UUID.randomUUID().toString())
            .mobileNo(longCount.incrementAndGet())
            .transactionRefNo(UUID.randomUUID().toString())
            .trnxResourceDataStatus(UUID.randomUUID().toString())
            .trnxMetaDataStatus(UUID.randomUUID().toString())
            .transactionDateTime(UUID.randomUUID().toString());
    }
}
