package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class OrganizationTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Organization getOrganizationSample1() {
        return new Organization()
            .id(1L)
            .orgId("orgId1")
            .orgName("orgName1")
            .orgAddress("orgAddress1")
            .industryType("industryType1")
            .tenantId("tenantId1");
    }

    public static Organization getOrganizationSample2() {
        return new Organization()
            .id(2L)
            .orgId("orgId2")
            .orgName("orgName2")
            .orgAddress("orgAddress2")
            .industryType("industryType2")
            .tenantId("tenantId2");
    }

    public static Organization getOrganizationRandomSampleGenerator() {
        return new Organization()
            .id(longCount.incrementAndGet())
            .orgId(UUID.randomUUID().toString())
            .orgName(UUID.randomUUID().toString())
            .orgAddress(UUID.randomUUID().toString())
            .industryType(UUID.randomUUID().toString())
            .tenantId(UUID.randomUUID().toString());
    }
}
