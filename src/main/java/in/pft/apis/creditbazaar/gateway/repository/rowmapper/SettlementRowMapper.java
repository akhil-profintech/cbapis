package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.Settlement;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Settlement}, with proper type conversions.
 */
@Service
public class SettlementRowMapper implements BiFunction<Row, String, Settlement> {

    private final ColumnConverter converter;

    public SettlementRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Settlement} stored in the database.
     */
    @Override
    public Settlement apply(Row row, String prefix) {
        Settlement entity = new Settlement();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setSettlementId(converter.fromRow(row, prefix + "_settlement_id", Long.class));
        entity.setSettlementUlidId(converter.fromRow(row, prefix + "_settlement_ulid_id", String.class));
        entity.setSettlementRefNo(converter.fromRow(row, prefix + "_settlement_ref_no", String.class));
        entity.setAcceptedOfferUlidId(converter.fromRow(row, prefix + "_accepted_offer_ulid_id", String.class));
        entity.setPlacedOfferUlidId(converter.fromRow(row, prefix + "_placed_offer_ulid_id", String.class));
        entity.setReqOffUlidId(converter.fromRow(row, prefix + "_req_off_ulid_id", String.class));
        entity.setDbmtRequestId(converter.fromRow(row, prefix + "_dbmt_request_id", Long.class));
        entity.setDbmtId(converter.fromRow(row, prefix + "_dbmt_id", Long.class));
        entity.setDbmtDate(converter.fromRow(row, prefix + "_dbmt_date", String.class));
        entity.setDbmtStatus(converter.fromRow(row, prefix + "_dbmt_status", String.class));
        entity.setDbmtAmount(converter.fromRow(row, prefix + "_dbmt_amount", Long.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setRecordStatus(converter.fromRow(row, prefix + "_record_status", String.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        return entity;
    }
}
