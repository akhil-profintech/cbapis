package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FundsTransferTransactionDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FundsTransferTransactionDetails getFundsTransferTransactionDetailsSample1() {
        return new FundsTransferTransactionDetails()
            .id(1L)
            .ftTrnxDetailsId(1L)
            .ftTrnxDetailsUlid("ftTrnxDetailsUlid1")
            .transactionId(1L)
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

    public static FundsTransferTransactionDetails getFundsTransferTransactionDetailsSample2() {
        return new FundsTransferTransactionDetails()
            .id(2L)
            .ftTrnxDetailsId(2L)
            .ftTrnxDetailsUlid("ftTrnxDetailsUlid2")
            .transactionId(2L)
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

    public static FundsTransferTransactionDetails getFundsTransferTransactionDetailsRandomSampleGenerator() {
        return new FundsTransferTransactionDetails()
            .id(longCount.incrementAndGet())
            .ftTrnxDetailsId(longCount.incrementAndGet())
            .ftTrnxDetailsUlid(UUID.randomUUID().toString())
            .transactionId(longCount.incrementAndGet())
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
