package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CREHighlightsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CREHighlights getCREHighlightsSample1() {
        return new CREHighlights().id(1L).creHighlightsId(1L).creRequestId("creRequestId1").highlights("highlights1");
    }

    public static CREHighlights getCREHighlightsSample2() {
        return new CREHighlights().id(2L).creHighlightsId(2L).creRequestId("creRequestId2").highlights("highlights2");
    }

    public static CREHighlights getCREHighlightsRandomSampleGenerator() {
        return new CREHighlights()
            .id(longCount.incrementAndGet())
            .creHighlightsId(longCount.incrementAndGet())
            .creRequestId(UUID.randomUUID().toString())
            .highlights(UUID.randomUUID().toString());
    }
}
