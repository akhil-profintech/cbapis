package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.UpdateVA;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link UpdateVA}, with proper type conversions.
 */
@Service
public class UpdateVARowMapper implements BiFunction<Row, String, UpdateVA> {

    private final ColumnConverter converter;

    public UpdateVARowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link UpdateVA} stored in the database.
     */
    @Override
    public UpdateVA apply(Row row, String prefix) {
        UpdateVA entity = new UpdateVA();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUpdateVirAccId(converter.fromRow(row, prefix + "_update_vir_acc_id", Long.class));
        entity.setFinReqId(converter.fromRow(row, prefix + "_fin_req_id", String.class));
        entity.setSubGrpId(converter.fromRow(row, prefix + "_sub_grp_id", String.class));
        entity.setMsgId(converter.fromRow(row, prefix + "_msg_id", String.class));
        entity.setCnvId(converter.fromRow(row, prefix + "_cnv_id", String.class));
        entity.setExtRefId(converter.fromRow(row, prefix + "_ext_ref_id", String.class));
        entity.setBizObjId(converter.fromRow(row, prefix + "_biz_obj_id", String.class));
        entity.setAppId(converter.fromRow(row, prefix + "_app_id", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", String.class));
        entity.setVaNum(converter.fromRow(row, prefix + "_va_num", String.class));
        entity.setFinPar(converter.fromRow(row, prefix + "_fin_par", String.class));
        entity.setClientCode(converter.fromRow(row, prefix + "_client_code", String.class));
        entity.setRequestDateTime(converter.fromRow(row, prefix + "_request_date_time", String.class));
        entity.setReslt(converter.fromRow(row, prefix + "_reslt", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setStatusDesc(converter.fromRow(row, prefix + "_status_desc", String.class));
        entity.setErrorCode(converter.fromRow(row, prefix + "_error_code", String.class));
        entity.setResponseDateTime(converter.fromRow(row, prefix + "_response_date_time", String.class));
        entity.setLastupdatedDateTime(converter.fromRow(row, prefix + "_lastupdated_date_time", String.class));
        entity.setLastUpdatedBy(converter.fromRow(row, prefix + "_last_updated_by", String.class));
        entity.setTradeEntityId(converter.fromRow(row, prefix + "_trade_entity_id", Long.class));
        return entity;
    }
}
