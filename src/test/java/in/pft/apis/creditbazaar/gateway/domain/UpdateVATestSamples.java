package in.pft.apis.creditbazaar.gateway.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class UpdateVATestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static UpdateVA getUpdateVASample1() {
        return new UpdateVA()
            .id(1L)
            .updateVirAccId(1L)
            .finReqId("finReqId1")
            .subGrpId("subGrpId1")
            .msgId("msgId1")
            .cnvId("cnvId1")
            .extRefId("extRefId1")
            .bizObjId("bizObjId1")
            .appId("appId1")
            .timestamp("timestamp1")
            .vaNum("vaNum1")
            .finPar("finPar1")
            .clientCode("clientCode1")
            .requestDateTime("requestDateTime1")
            .reslt("reslt1")
            .status("status1")
            .statusDesc("statusDesc1")
            .errorCode("errorCode1")
            .responseDateTime("responseDateTime1")
            .lastupdatedDateTime("lastupdatedDateTime1")
            .lastUpdatedBy("lastUpdatedBy1");
    }

    public static UpdateVA getUpdateVASample2() {
        return new UpdateVA()
            .id(2L)
            .updateVirAccId(2L)
            .finReqId("finReqId2")
            .subGrpId("subGrpId2")
            .msgId("msgId2")
            .cnvId("cnvId2")
            .extRefId("extRefId2")
            .bizObjId("bizObjId2")
            .appId("appId2")
            .timestamp("timestamp2")
            .vaNum("vaNum2")
            .finPar("finPar2")
            .clientCode("clientCode2")
            .requestDateTime("requestDateTime2")
            .reslt("reslt2")
            .status("status2")
            .statusDesc("statusDesc2")
            .errorCode("errorCode2")
            .responseDateTime("responseDateTime2")
            .lastupdatedDateTime("lastupdatedDateTime2")
            .lastUpdatedBy("lastUpdatedBy2");
    }

    public static UpdateVA getUpdateVARandomSampleGenerator() {
        return new UpdateVA()
            .id(longCount.incrementAndGet())
            .updateVirAccId(longCount.incrementAndGet())
            .finReqId(UUID.randomUUID().toString())
            .subGrpId(UUID.randomUUID().toString())
            .msgId(UUID.randomUUID().toString())
            .cnvId(UUID.randomUUID().toString())
            .extRefId(UUID.randomUUID().toString())
            .bizObjId(UUID.randomUUID().toString())
            .appId(UUID.randomUUID().toString())
            .timestamp(UUID.randomUUID().toString())
            .vaNum(UUID.randomUUID().toString())
            .finPar(UUID.randomUUID().toString())
            .clientCode(UUID.randomUUID().toString())
            .requestDateTime(UUID.randomUUID().toString())
            .reslt(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString())
            .statusDesc(UUID.randomUUID().toString())
            .errorCode(UUID.randomUUID().toString())
            .responseDateTime(UUID.randomUUID().toString())
            .lastupdatedDateTime(UUID.randomUUID().toString())
            .lastUpdatedBy(UUID.randomUUID().toString());
    }
}
