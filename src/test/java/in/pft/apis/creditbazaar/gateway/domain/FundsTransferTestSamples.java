package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class FundsTransferTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static FundsTransfer getFundsTransferSample1() {
        return new FundsTransfer()
            .id(1L)
            .fundsTransferId("fundsTransferId1")
            .fundsTransferRefNo("fundsTransferRefNo1")
            .finReqId("finReqId1")
            .subGrpId("subGrpId1")
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
            .messageType("messageType1")
            .transactionDateTime("transactionDateTime1")
            .transactionRefNo("transactionRefNo1")
            .trnxMetaDataStatus("trnxMetaDataStatus1")
            .trnxMetaDataMessage("trnxMetaDataMessage1")
            .trnxMetaDataVersion("trnxMetaDataVersion1")
            .trnxMetaDataTime("trnxMetaDataTime1")
            .trnxResourceDataBeneficiaryName("trnxResourceDataBeneficiaryName1")
            .trnxResourceDataTransactionId("trnxResourceDataTransactionId1")
            .trnxResourceDataStatus("trnxResourceDataStatus1")
            .fundsTransferStatus("fundsTransferStatus1")
            .lastupdatedDateTime("lastupdatedDateTime1")
            .lastUpdatedBy("lastUpdatedBy1");
    }

    public static FundsTransfer getFundsTransferSample2() {
        return new FundsTransfer()
            .id(2L)
            .fundsTransferId("fundsTransferId2")
            .fundsTransferRefNo("fundsTransferRefNo2")
            .finReqId("finReqId2")
            .subGrpId("subGrpId2")
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
            .messageType("messageType2")
            .transactionDateTime("transactionDateTime2")
            .transactionRefNo("transactionRefNo2")
            .trnxMetaDataStatus("trnxMetaDataStatus2")
            .trnxMetaDataMessage("trnxMetaDataMessage2")
            .trnxMetaDataVersion("trnxMetaDataVersion2")
            .trnxMetaDataTime("trnxMetaDataTime2")
            .trnxResourceDataBeneficiaryName("trnxResourceDataBeneficiaryName2")
            .trnxResourceDataTransactionId("trnxResourceDataTransactionId2")
            .trnxResourceDataStatus("trnxResourceDataStatus2")
            .fundsTransferStatus("fundsTransferStatus2")
            .lastupdatedDateTime("lastupdatedDateTime2")
            .lastUpdatedBy("lastUpdatedBy2");
    }

    public static FundsTransfer getFundsTransferRandomSampleGenerator() {
        return new FundsTransfer()
            .id(longCount.incrementAndGet())
            .fundsTransferId(UUID.randomUUID().toString())
            .fundsTransferRefNo(UUID.randomUUID().toString())
            .finReqId(UUID.randomUUID().toString())
            .subGrpId(UUID.randomUUID().toString())
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
            .messageType(UUID.randomUUID().toString())
            .transactionDateTime(UUID.randomUUID().toString())
            .transactionRefNo(UUID.randomUUID().toString())
            .trnxMetaDataStatus(UUID.randomUUID().toString())
            .trnxMetaDataMessage(UUID.randomUUID().toString())
            .trnxMetaDataVersion(UUID.randomUUID().toString())
            .trnxMetaDataTime(UUID.randomUUID().toString())
            .trnxResourceDataBeneficiaryName(UUID.randomUUID().toString())
            .trnxResourceDataTransactionId(UUID.randomUUID().toString())
            .trnxResourceDataStatus(UUID.randomUUID().toString())
            .fundsTransferStatus(UUID.randomUUID().toString())
            .lastupdatedDateTime(UUID.randomUUID().toString())
            .lastUpdatedBy(UUID.randomUUID().toString());
    }
}
