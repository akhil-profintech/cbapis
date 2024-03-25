package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CREObservationsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CREObservations getCREObservationsSample1() {
        return new CREObservations()
            .id(1L)
            .creObservationsId(1L)
            .creObservationsUlidId("creObservationsUlidId1")
            .creRequestId("creRequestId1")
            .observations("observations1")
            .assessmentId(1L);
    }

    public static CREObservations getCREObservationsSample2() {
        return new CREObservations()
            .id(2L)
            .creObservationsId(2L)
            .creObservationsUlidId("creObservationsUlidId2")
            .creRequestId("creRequestId2")
            .observations("observations2")
            .assessmentId(2L);
    }

    public static CREObservations getCREObservationsRandomSampleGenerator() {
        return new CREObservations()
            .id(longCount.incrementAndGet())
            .creObservationsId(longCount.incrementAndGet())
            .creObservationsUlidId(UUID.randomUUID().toString())
            .creRequestId(UUID.randomUUID().toString())
            .observations(UUID.randomUUID().toString())
            .assessmentId(longCount.incrementAndGet());
    }
}
