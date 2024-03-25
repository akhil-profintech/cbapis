package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CREHighlightsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CREHighlights getCREHighlightsSample1() {
        return new CREHighlights()
            .id(1L)
            .creHighlightsId(1L)
            .creHighlightsUlidId("creHighlightsUlidId1")
            .creRequestId("creRequestId1")
            .highlights("highlights1")
            .assessmentId(1L);
    }

    public static CREHighlights getCREHighlightsSample2() {
        return new CREHighlights()
            .id(2L)
            .creHighlightsId(2L)
            .creHighlightsUlidId("creHighlightsUlidId2")
            .creRequestId("creRequestId2")
            .highlights("highlights2")
            .assessmentId(2L);
    }

    public static CREHighlights getCREHighlightsRandomSampleGenerator() {
        return new CREHighlights()
            .id(longCount.incrementAndGet())
            .creHighlightsId(longCount.incrementAndGet())
            .creHighlightsUlidId(UUID.randomUUID().toString())
            .creRequestId(UUID.randomUUID().toString())
            .highlights(UUID.randomUUID().toString())
            .assessmentId(longCount.incrementAndGet());
    }
}
