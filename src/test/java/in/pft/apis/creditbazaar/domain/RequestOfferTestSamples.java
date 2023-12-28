package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class RequestOfferTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static RequestOffer getRequestOfferSample1() {
        return new RequestOffer()
            .id(1L)
            .reqOffId("reqOffId1")
            .requestOfferRefNo("requestOfferRefNo1")
            .offerValue(1L)
            .requestAmt(1L)
            .tradeValue(1L)
            .marginValue(1L)
            .amountAftMargin(1L)
            .term(1L)
            .netAmount(1L)
            .status("status1")
            .anchorTraderName("anchorTraderName1")
            .tradePartnerName("tradePartnerName1")
            .anchorTraderGst("anchorTraderGst1")
            .tradePartnerGst("tradePartnerGst1")
            .sellerName("sellerName1")
            .buyerName("buyerName1")
            .anchorTraderGstComplianceScore("anchorTraderGstComplianceScore1")
            .anchorTraderErpPeerScore("anchorTraderErpPeerScore1");
    }

    public static RequestOffer getRequestOfferSample2() {
        return new RequestOffer()
            .id(2L)
            .reqOffId("reqOffId2")
            .requestOfferRefNo("requestOfferRefNo2")
            .offerValue(2L)
            .requestAmt(2L)
            .tradeValue(2L)
            .marginValue(2L)
            .amountAftMargin(2L)
            .term(2L)
            .netAmount(2L)
            .status("status2")
            .anchorTraderName("anchorTraderName2")
            .tradePartnerName("tradePartnerName2")
            .anchorTraderGst("anchorTraderGst2")
            .tradePartnerGst("tradePartnerGst2")
            .sellerName("sellerName2")
            .buyerName("buyerName2")
            .anchorTraderGstComplianceScore("anchorTraderGstComplianceScore2")
            .anchorTraderErpPeerScore("anchorTraderErpPeerScore2");
    }

    public static RequestOffer getRequestOfferRandomSampleGenerator() {
        return new RequestOffer()
            .id(longCount.incrementAndGet())
            .reqOffId(UUID.randomUUID().toString())
            .requestOfferRefNo(UUID.randomUUID().toString())
            .offerValue(longCount.incrementAndGet())
            .requestAmt(longCount.incrementAndGet())
            .tradeValue(longCount.incrementAndGet())
            .marginValue(longCount.incrementAndGet())
            .amountAftMargin(longCount.incrementAndGet())
            .term(longCount.incrementAndGet())
            .netAmount(longCount.incrementAndGet())
            .status(UUID.randomUUID().toString())
            .anchorTraderName(UUID.randomUUID().toString())
            .tradePartnerName(UUID.randomUUID().toString())
            .anchorTraderGst(UUID.randomUUID().toString())
            .tradePartnerGst(UUID.randomUUID().toString())
            .sellerName(UUID.randomUUID().toString())
            .buyerName(UUID.randomUUID().toString())
            .anchorTraderGstComplianceScore(UUID.randomUUID().toString())
            .anchorTraderErpPeerScore(UUID.randomUUID().toString());
    }
}
