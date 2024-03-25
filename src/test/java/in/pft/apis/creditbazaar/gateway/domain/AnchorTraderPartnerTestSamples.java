package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AnchorTraderPartnerTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AnchorTraderPartner getAnchorTraderPartnerSample1() {
        return new AnchorTraderPartner()
            .id(1L)
            .atpartnerId(1L)
            .atpartnerUlidId("atpartnerUlidId1")
            .pan("pan1")
            .panStatus("panStatus1")
            .name("name1")
            .aadhar("aadhar1")
            .aadharOtp("aadharOtp1")
            .aadharStatus("aadharStatus1")
            .aadharName("aadharName1")
            .aadharAddress("aadharAddress1");
    }

    public static AnchorTraderPartner getAnchorTraderPartnerSample2() {
        return new AnchorTraderPartner()
            .id(2L)
            .atpartnerId(2L)
            .atpartnerUlidId("atpartnerUlidId2")
            .pan("pan2")
            .panStatus("panStatus2")
            .name("name2")
            .aadhar("aadhar2")
            .aadharOtp("aadharOtp2")
            .aadharStatus("aadharStatus2")
            .aadharName("aadharName2")
            .aadharAddress("aadharAddress2");
    }

    public static AnchorTraderPartner getAnchorTraderPartnerRandomSampleGenerator() {
        return new AnchorTraderPartner()
            .id(longCount.incrementAndGet())
            .atpartnerId(longCount.incrementAndGet())
            .atpartnerUlidId(UUID.randomUUID().toString())
            .pan(UUID.randomUUID().toString())
            .panStatus(UUID.randomUUID().toString())
            .name(UUID.randomUUID().toString())
            .aadhar(UUID.randomUUID().toString())
            .aadharOtp(UUID.randomUUID().toString())
            .aadharStatus(UUID.randomUUID().toString())
            .aadharName(UUID.randomUUID().toString())
            .aadharAddress(UUID.randomUUID().toString());
    }
}
