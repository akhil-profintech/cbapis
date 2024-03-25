package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AnchorTraderTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AnchorTrader getAnchorTraderSample1() {
        return new AnchorTrader()
            .id(1L)
            .atId(1L)
            .atUlidId("atUlidId1")
            .orgId("orgId1")
            .tenantId("tenantId1")
            .customerName("customerName1")
            .orgName("orgName1")
            .gstId("gstId1")
            .phoneNumber(1L)
            .anchorTraderName("anchorTraderName1")
            .location("location1")
            .anchorTraderGST("anchorTraderGST1")
            .anchorTraderSector("anchorTraderSector1")
            .anchorTraderPAN("anchorTraderPAN1")
            .kycCompleted("kycCompleted1")
            .bankDetails("bankDetails1")
            .emailId("emailId1")
            .personalEmailId("personalEmailId1")
            .accountNumber("accountNumber1")
            .ifscCode("ifscCode1")
            .bankName("bankName1")
            .branchName("branchName1")
            .typeOfFirm("typeOfFirm1")
            .panStatus("panStatus1")
            .tosDocument("tosDocument1");
    }

    public static AnchorTrader getAnchorTraderSample2() {
        return new AnchorTrader()
            .id(2L)
            .atId(2L)
            .atUlidId("atUlidId2")
            .orgId("orgId2")
            .tenantId("tenantId2")
            .customerName("customerName2")
            .orgName("orgName2")
            .gstId("gstId2")
            .phoneNumber(2L)
            .anchorTraderName("anchorTraderName2")
            .location("location2")
            .anchorTraderGST("anchorTraderGST2")
            .anchorTraderSector("anchorTraderSector2")
            .anchorTraderPAN("anchorTraderPAN2")
            .kycCompleted("kycCompleted2")
            .bankDetails("bankDetails2")
            .emailId("emailId2")
            .personalEmailId("personalEmailId2")
            .accountNumber("accountNumber2")
            .ifscCode("ifscCode2")
            .bankName("bankName2")
            .branchName("branchName2")
            .typeOfFirm("typeOfFirm2")
            .panStatus("panStatus2")
            .tosDocument("tosDocument2");
    }

    public static AnchorTrader getAnchorTraderRandomSampleGenerator() {
        return new AnchorTrader()
            .id(longCount.incrementAndGet())
            .atId(longCount.incrementAndGet())
            .atUlidId(UUID.randomUUID().toString())
            .orgId(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString())
            .customerName(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .gstId(UUID.randomUUID().toString())
            .phoneNumber(longCount.incrementAndGet())
            .anchorTraderName(UUID.randomUUID().toString())
            .location(UUID.randomUUID().toString())
            .anchorTraderGST(UUID.randomUUID().toString())
            .anchorTraderSector(UUID.randomUUID().toString())
            .anchorTraderPAN(UUID.randomUUID().toString())
            .kycCompleted(UUID.randomUUID().toString())
            .bankDetails(UUID.randomUUID().toString())
            .emailId(UUID.randomUUID().toString())
            .personalEmailId(UUID.randomUUID().toString())
            .accountNumber(UUID.randomUUID().toString())
            .ifscCode(UUID.randomUUID().toString())
            .bankName(UUID.randomUUID().toString())
            .branchName(UUID.randomUUID().toString())
            .typeOfFirm(UUID.randomUUID().toString())
            .panStatus(UUID.randomUUID().toString())
            .tosDocument(UUID.randomUUID().toString());
    }
}
