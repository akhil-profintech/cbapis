package in.pft.apis.creditbazaar.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CBCREProcessTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CBCREProcess getCBCREProcessSample1() {
        return new CBCREProcess()
            .id(1L)
            .cbProcessId("cbProcessId1")
            .financeRequestId("financeRequestId1")
            .anchorTraderId("anchorTraderId1")
            .anchortTraderGst("anchortTraderGst1")
            .financeAmount("financeAmount1")
            .processDateTime("processDateTime1")
            .creProcessStatus("creProcessStatus1")
            .responseDateTime("responseDateTime1")
            .creRequestId("creRequestId1")
            .timestamp("timestamp1")
            .totalAmountRequested(1L)
            .totalInvoiceAmount(1L);
    }

    public static CBCREProcess getCBCREProcessSample2() {
        return new CBCREProcess()
            .id(2L)
            .cbProcessId("cbProcessId2")
            .financeRequestId("financeRequestId2")
            .anchorTraderId("anchorTraderId2")
            .anchortTraderGst("anchortTraderGst2")
            .financeAmount("financeAmount2")
            .processDateTime("processDateTime2")
            .creProcessStatus("creProcessStatus2")
            .responseDateTime("responseDateTime2")
            .creRequestId("creRequestId2")
            .timestamp("timestamp2")
            .totalAmountRequested(2L)
            .totalInvoiceAmount(2L);
    }

    public static CBCREProcess getCBCREProcessRandomSampleGenerator() {
        return new CBCREProcess()
            .id(longCount.incrementAndGet())
            .cbProcessId(UUID.randomUUID().toString())
            .financeRequestId(UUID.randomUUID().toString())
            .anchorTraderId(UUID.randomUUID().toString())
            .anchortTraderGst(UUID.randomUUID().toString())
            .financeAmount(UUID.randomUUID().toString())
            .processDateTime(UUID.randomUUID().toString())
            .creProcessStatus(UUID.randomUUID().toString())
            .responseDateTime(UUID.randomUUID().toString())
            .creRequestId(UUID.randomUUID().toString())
            .timestamp(UUID.randomUUID().toString())
            .totalAmountRequested(longCount.incrementAndGet())
            .totalInvoiceAmount(longCount.incrementAndGet());
    }
}
