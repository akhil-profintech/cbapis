package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class DocDetailsTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static DocDetails getDocDetailsSample1() {
        return new DocDetails()
            .id(1L)
            .docDetailsId(1L)
            .docDetailsUlidId("docDetailsUlidId1")
            .docRecordId(1L)
            .link("link1")
            .description("description1")
            .docType("docType1")
            .status("status1");
    }

    public static DocDetails getDocDetailsSample2() {
        return new DocDetails()
            .id(2L)
            .docDetailsId(2L)
            .docDetailsUlidId("docDetailsUlidId2")
            .docRecordId(2L)
            .link("link2")
            .description("description2")
            .docType("docType2")
            .status("status2");
    }

    public static DocDetails getDocDetailsRandomSampleGenerator() {
        return new DocDetails()
            .id(longCount.incrementAndGet())
            .docDetailsId(longCount.incrementAndGet())
            .docDetailsUlidId(UUID.randomUUID().toString())
            .docRecordId(longCount.incrementAndGet())
            .link(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString())
            .docType(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
