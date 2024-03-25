package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class PlacedOfferTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static PlacedOffer getPlacedOfferSample1() {
        return new PlacedOffer()
            .id(1L)
            .placedOfferUlidId("placedOfferUlidId1")
            .placedOfferRefNo("placedOfferRefNo1")
            .reqOffUlidId("reqOffUlidId1")
            .requestOfferRefNo("requestOfferRefNo1")
            .value(1L)
            .reqAmount(1L)
            .marginValue(1L)
            .amountAftMargin(1L)
            .term(1L)
            .interestValue(1L)
            .netAmount(1L)
            .status("status1")
            .anchorTrader("anchorTrader1")
            .tradePartner("tradePartner1")
            .disbursalAmount("disbursalAmount1");
    }

    public static PlacedOffer getPlacedOfferSample2() {
        return new PlacedOffer()
            .id(2L)
            .placedOfferUlidId("placedOfferUlidId2")
            .placedOfferRefNo("placedOfferRefNo2")
            .reqOffUlidId("reqOffUlidId2")
            .requestOfferRefNo("requestOfferRefNo2")
            .value(2L)
            .reqAmount(2L)
            .marginValue(2L)
            .amountAftMargin(2L)
            .term(2L)
            .interestValue(2L)
            .netAmount(2L)
            .status("status2")
            .anchorTrader("anchorTrader2")
            .tradePartner("tradePartner2")
            .disbursalAmount("disbursalAmount2");
    }

    public static PlacedOffer getPlacedOfferRandomSampleGenerator() {
        return new PlacedOffer()
            .id(longCount.incrementAndGet())
            .placedOfferUlidId(UUID.randomUUID().toString())
            .placedOfferRefNo(UUID.randomUUID().toString())
            .reqOffUlidId(UUID.randomUUID().toString())
            .requestOfferRefNo(UUID.randomUUID().toString())
            .value(longCount.incrementAndGet())
            .reqAmount(longCount.incrementAndGet())
            .marginValue(longCount.incrementAndGet())
            .amountAftMargin(longCount.incrementAndGet())
            .term(longCount.incrementAndGet())
            .interestValue(longCount.incrementAndGet())
            .netAmount(longCount.incrementAndGet())
            .status(UUID.randomUUID().toString())
            .anchorTrader(UUID.randomUUID().toString())
            .tradePartner(UUID.randomUUID().toString())
            .disbursalAmount(UUID.randomUUID().toString());
    }
}
