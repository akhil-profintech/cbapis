package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FinanceRequest}, with proper type conversions.
 */
@Service
public class FinanceRequestRowMapper implements BiFunction<Row, String, FinanceRequest> {

    private final ColumnConverter converter;

    public FinanceRequestRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FinanceRequest} stored in the database.
     */
    @Override
    public FinanceRequest apply(Row row, String prefix) {
        FinanceRequest entity = new FinanceRequest();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setFinanceRequestId(converter.fromRow(row, prefix + "_finance_request_id", Long.class));
        entity.setFinanceRequestUlidId(converter.fromRow(row, prefix + "_finance_request_ulid_id", String.class));
        entity.setFinanceRequestRefNo(converter.fromRow(row, prefix + "_finance_request_ref_no", String.class));
        entity.setTradeChannelId(converter.fromRow(row, prefix + "_trade_channel_id", String.class));
        entity.setRequestAmount(converter.fromRow(row, prefix + "_request_amount", String.class));
        entity.setRequestDate(converter.fromRow(row, prefix + "_request_date", LocalDate.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setRequestStatus(converter.fromRow(row, prefix + "_request_status", String.class));
        entity.setDueDate(converter.fromRow(row, prefix + "_due_date", LocalDate.class));
        entity.setGstConsent(converter.fromRow(row, prefix + "_gst_consent", Boolean.class));
        entity.setAnchortraderId(converter.fromRow(row, prefix + "_anchortrader_id", Long.class));
        return entity;
    }
}
