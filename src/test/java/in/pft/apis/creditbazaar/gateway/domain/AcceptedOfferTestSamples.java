package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AcceptedOfferTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AcceptedOffer getAcceptedOfferSample1() {
        return new AcceptedOffer()
            .id(1L)
            .acceptedOfferUlidId("acceptedOfferUlidId1")
            .acceptedOfferRefNo("acceptedOfferRefNo1")
            .reqOffUlidId("reqOffUlidId1")
            .value(1L)
            .reqAmount(1L)
            .marginValue(1L)
            .amountAftMargin(1L)
            .term(1L)
            .interestValue(1L)
            .netAmount(1L)
            .status("status1");
    }

    public static AcceptedOffer getAcceptedOfferSample2() {
        return new AcceptedOffer()
            .id(2L)
            .acceptedOfferUlidId("acceptedOfferUlidId2")
            .acceptedOfferRefNo("acceptedOfferRefNo2")
            .reqOffUlidId("reqOffUlidId2")
            .value(2L)
            .reqAmount(2L)
            .marginValue(2L)
            .amountAftMargin(2L)
            .term(2L)
            .interestValue(2L)
            .netAmount(2L)
            .status("status2");
    }

    public static AcceptedOffer getAcceptedOfferRandomSampleGenerator() {
        return new AcceptedOffer()
            .id(longCount.incrementAndGet())
            .acceptedOfferUlidId(UUID.randomUUID().toString())
            .acceptedOfferRefNo(UUID.randomUUID().toString())
            .reqOffUlidId(UUID.randomUUID().toString())
            .value(longCount.incrementAndGet())
            .reqAmount(longCount.incrementAndGet())
            .marginValue(longCount.incrementAndGet())
            .amountAftMargin(longCount.incrementAndGet())
            .term(longCount.incrementAndGet())
            .interestValue(longCount.incrementAndGet())
            .netAmount(longCount.incrementAndGet())
            .status(UUID.randomUUID().toString());
    }
}
