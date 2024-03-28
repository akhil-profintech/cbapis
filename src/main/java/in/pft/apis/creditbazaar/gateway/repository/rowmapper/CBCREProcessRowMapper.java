package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.CBCREProcess;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link CBCREProcess}, with proper type conversions.
 */
@Service
public class CBCREProcessRowMapper implements BiFunction<Row, String, CBCREProcess> {

    private final ColumnConverter converter;

    public CBCREProcessRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CBCREProcess} stored in the database.
     */
    @Override
    public CBCREProcess apply(Row row, String prefix) {
        CBCREProcess entity = new CBCREProcess();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCbProcessId(converter.fromRow(row, prefix + "_cb_process_id", Long.class));
        entity.setCbProcessUlidId(converter.fromRow(row, prefix + "_cb_process_ulid_id", String.class));
        entity.setCbProcessRefNo(converter.fromRow(row, prefix + "_cb_process_ref_no", String.class));
        entity.setAnchorTraderId(converter.fromRow(row, prefix + "_anchor_trader_id", String.class));
        entity.setAnchorTraderGst(converter.fromRow(row, prefix + "_anchor_trader_gst", String.class));
        entity.setFinanceAmount(converter.fromRow(row, prefix + "_finance_amount", String.class));
        entity.setProcessDateTime(converter.fromRow(row, prefix + "_process_date_time", String.class));
        entity.setCreProcessStatus(converter.fromRow(row, prefix + "_cre_process_status", String.class));
        entity.setResponseDateTime(converter.fromRow(row, prefix + "_response_date_time", String.class));
        entity.setCreRequestId(converter.fromRow(row, prefix + "_cre_request_id", String.class));
        entity.setCumilativeTradeScore(converter.fromRow(row, prefix + "_cumilative_trade_score", Float.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", String.class));
        entity.setTotalAmountRequested(converter.fromRow(row, prefix + "_total_amount_requested", Long.class));
        entity.setTotalInvoiceAmount(converter.fromRow(row, prefix + "_total_invoice_amount", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setFinanceRequestId(converter.fromRow(row, prefix + "_finance_request_id", Long.class));
        return entity;
    }
}
