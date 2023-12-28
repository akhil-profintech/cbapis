package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class AcceptedOfferTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static AcceptedOffer getAcceptedOfferSample1() {
        return new AcceptedOffer()
            .id(1L)
            .offerId("offerId1")
            .acceptedOfferRefNo("acceptedOfferRefNo1")
            .reqOffId(1L)
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
            .offerId("offerId2")
            .acceptedOfferRefNo("acceptedOfferRefNo2")
            .reqOffId(2L)
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
            .offerId(UUID.randomUUID().toString())
            .acceptedOfferRefNo(UUID.randomUUID().toString())
            .reqOffId(longCount.incrementAndGet())
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
