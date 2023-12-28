package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.InstaAlert;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link InstaAlert}, with proper type conversions.
 */
@Service
public class InstaAlertRowMapper implements BiFunction<Row, String, InstaAlert> {

    private final ColumnConverter converter;

    public InstaAlertRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link InstaAlert} stored in the database.
     */
    @Override
    public InstaAlert apply(Row row, String prefix) {
        InstaAlert entity = new InstaAlert();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setInstaAlertId(converter.fromRow(row, prefix + "_insta_alert_id", Long.class));
        entity.setFinReqId(converter.fromRow(row, prefix + "_fin_req_id", String.class));
        entity.setSubGrpId(converter.fromRow(row, prefix + "_sub_grp_id", String.class));
        entity.setCustomerCode(converter.fromRow(row, prefix + "_customer_code", String.class));
        entity.setCustomerName(converter.fromRow(row, prefix + "_customer_name", String.class));
        entity.setProductCode(converter.fromRow(row, prefix + "_product_code", String.class));
        entity.setPoolingAccountNumber(converter.fromRow(row, prefix + "_pooling_account_number", String.class));
        entity.setTransactionType(converter.fromRow(row, prefix + "_transaction_type", String.class));
        entity.setBatchAmt(converter.fromRow(row, prefix + "_batch_amt", Long.class));
        entity.setBatchAmtCcd(converter.fromRow(row, prefix + "_batch_amt_ccd", String.class));
        entity.setCreditDate(converter.fromRow(row, prefix + "_credit_date", String.class));
        entity.setVaNumber(converter.fromRow(row, prefix + "_va_number", String.class));
        entity.setUtrNo(converter.fromRow(row, prefix + "_utr_no", String.class));
        entity.setCreditGenerationTime(converter.fromRow(row, prefix + "_credit_generation_time", String.class));
        entity.setRemitterName(converter.fromRow(row, prefix + "_remitter_name", String.class));
        entity.setRemitterAccountNumber(converter.fromRow(row, prefix + "_remitter_account_number", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setLastupdatedDateTime(converter.fromRow(row, prefix + "_lastupdated_date_time", String.class));
        entity.setLastUpdatedBy(converter.fromRow(row, prefix + "_last_updated_by", String.class));
        entity.setTradeEntityId(converter.fromRow(row, prefix + "_trade_entity_id", Long.class));
        return entity;
    }
}
