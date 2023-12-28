package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class TradeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Trade getTradeSample1() {
        return new Trade()
            .id(1L)
            .tradeId(1L)
            .tradeRefNumber("tradeRefNumber1")
            .sellerGstId("sellerGstId1")
            .buyerGstId("buyerGstId1")
            .tradeAmount("tradeAmount1")
            .invoiceNumber("invoiceNumber1")
            .tradeDocType("tradeDocType1")
            .tradeDocSource("tradeDocSource1")
            .tradeDocCredibility("tradeDocCredibility1")
            .tradeMilestoneStatus("tradeMilestoneStatus1")
            .tradeAdvancePayment("tradeAdvancePayment1")
            .anchorTraderName("anchorTraderName1")
            .tradePartnerName("tradePartnerName1")
            .invoiceTerm(1L)
            .acceptanceFromTradePartner("acceptanceFromTradePartner1")
            .reasonForFinance("reasonForFinance1")
            .tradePartnerSector("tradePartnerSector1")
            .tradePartnerLocation("tradePartnerLocation1")
            .tradePartnerGstComplianceScore("tradePartnerGstComplianceScore1");
    }

    public static Trade getTradeSample2() {
        return new Trade()
            .id(2L)
            .tradeId(2L)
            .tradeRefNumber("tradeRefNumber2")
            .sellerGstId("sellerGstId2")
            .buyerGstId("buyerGstId2")
            .tradeAmount("tradeAmount2")
            .invoiceNumber("invoiceNumber2")
            .tradeDocType("tradeDocType2")
            .tradeDocSource("tradeDocSource2")
            .tradeDocCredibility("tradeDocCredibility2")
            .tradeMilestoneStatus("tradeMilestoneStatus2")
            .tradeAdvancePayment("tradeAdvancePayment2")
            .anchorTraderName("anchorTraderName2")
            .tradePartnerName("tradePartnerName2")
            .invoiceTerm(2L)
            .acceptanceFromTradePartner("acceptanceFromTradePartner2")
            .reasonForFinance("reasonForFinance2")
            .tradePartnerSector("tradePartnerSector2")
            .tradePartnerLocation("tradePartnerLocation2")
            .tradePartnerGstComplianceScore("tradePartnerGstComplianceScore2");
    }

    public static Trade getTradeRandomSampleGenerator() {
        return new Trade()
            .id(longCount.incrementAndGet())
            .tradeId(longCount.incrementAndGet())
            .tradeRefNumber(UUID.randomUUID().toString())
            .sellerGstId(UUID.randomUUID().toString())
            .buyerGstId(UUID.randomUUID().toString())
            .tradeAmount(UUID.randomUUID().toString())
            .invoiceNumber(UUID.randomUUID().toString())
            .tradeDocType(UUID.randomUUID().toString())
            .tradeDocSource(UUID.randomUUID().toString())
            .tradeDocCredibility(UUID.randomUUID().toString())
            .tradeMilestoneStatus(UUID.randomUUID().toString())
            .tradeAdvancePayment(UUID.randomUUID().toString())
            .anchorTraderName(UUID.randomUUID().toString())
            .tradePartnerName(UUID.randomUUID().toString())
            .invoiceTerm(longCount.incrementAndGet())
            .acceptanceFromTradePartner(UUID.randomUUID().toString())
            .reasonForFinance(UUID.randomUUID().toString())
            .tradePartnerSector(UUID.randomUUID().toString())
            .tradePartnerLocation(UUID.randomUUID().toString())
            .tradePartnerGstComplianceScore(UUID.randomUUID().toString());
    }
}
