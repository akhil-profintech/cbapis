package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CBCREProcessSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("cb_process_id", table, columnPrefix + "_cb_process_id"));
        columns.add(Column.aliased("cb_process_ulid_id", table, columnPrefix + "_cb_process_ulid_id"));
        columns.add(Column.aliased("cb_process_ref_no", table, columnPrefix + "_cb_process_ref_no"));
        columns.add(Column.aliased("anchor_trader_id", table, columnPrefix + "_anchor_trader_id"));
        columns.add(Column.aliased("anchor_trader_gst", table, columnPrefix + "_anchor_trader_gst"));
        columns.add(Column.aliased("finance_amount", table, columnPrefix + "_finance_amount"));
        columns.add(Column.aliased("process_date_time", table, columnPrefix + "_process_date_time"));
        columns.add(Column.aliased("cre_process_status", table, columnPrefix + "_cre_process_status"));
        columns.add(Column.aliased("response_date_time", table, columnPrefix + "_response_date_time"));
        columns.add(Column.aliased("cre_request_id", table, columnPrefix + "_cre_request_id"));
        columns.add(Column.aliased("cumilative_trade_score", table, columnPrefix + "_cumilative_trade_score"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("total_amount_requested", table, columnPrefix + "_total_amount_requested"));
        columns.add(Column.aliased("total_invoice_amount", table, columnPrefix + "_total_invoice_amount"));

        columns.add(Column.aliased("finance_request_id", table, columnPrefix + "_finance_request_id"));
        return columns;
    }
}
