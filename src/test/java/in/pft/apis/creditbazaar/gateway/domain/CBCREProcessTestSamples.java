package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class CBCREProcessTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static CBCREProcess getCBCREProcessSample1() {
        return new CBCREProcess()
            .id(1L)
            .cbProcessId(1L)
            .cbProcessUlidId("cbProcessUlidId1")
            .cbProcessRefNo("cbProcessRefNo1")
            .anchorTraderId("anchorTraderId1")
            .anchorTraderGst("anchorTraderGst1")
            .financeAmount("financeAmount1")
            .processDateTime("processDateTime1")
            .creProcessStatus("creProcessStatus1")
            .responseDateTime("responseDateTime1")
            .creRequestId("creRequestId1")
            .timestamp("timestamp1")
            .totalAmountRequested(1L)
            .totalInvoiceAmount(1L)
            .status("status1");
    }

    public static CBCREProcess getCBCREProcessSample2() {
        return new CBCREProcess()
            .id(2L)
            .cbProcessId(2L)
            .cbProcessUlidId("cbProcessUlidId2")
            .cbProcessRefNo("cbProcessRefNo2")
            .anchorTraderId("anchorTraderId2")
            .anchorTraderGst("anchorTraderGst2")
            .financeAmount("financeAmount2")
            .processDateTime("processDateTime2")
            .creProcessStatus("creProcessStatus2")
            .responseDateTime("responseDateTime2")
            .creRequestId("creRequestId2")
            .timestamp("timestamp2")
            .totalAmountRequested(2L)
            .totalInvoiceAmount(2L)
            .status("status2");
    }

    public static CBCREProcess getCBCREProcessRandomSampleGenerator() {
        return new CBCREProcess()
            .id(longCount.incrementAndGet())
            .cbProcessId(longCount.incrementAndGet())
            .cbProcessUlidId(UUID.randomUUID().toString())
            .cbProcessRefNo(UUID.randomUUID().toString())
            .anchorTraderId(UUID.randomUUID().toString())
            .anchorTraderGst(UUID.randomUUID().toString())
            .financeAmount(UUID.randomUUID().toString())
            .processDateTime(UUID.randomUUID().toString())
            .creProcessStatus(UUID.randomUUID().toString())
            .responseDateTime(UUID.randomUUID().toString())
            .creRequestId(UUID.randomUUID().toString())
            .timestamp(UUID.randomUUID().toString())
            .totalAmountRequested(longCount.incrementAndGet())
            .totalInvoiceAmount(longCount.incrementAndGet())
            .status(UUID.randomUUID().toString());
    }
}
