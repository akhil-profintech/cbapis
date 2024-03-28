package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class TradeSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("trade_ulid_id", table, columnPrefix + "_trade_ulid_id"));
        columns.add(Column.aliased("trade_ref_no", table, columnPrefix + "_trade_ref_no"));
        columns.add(Column.aliased("seller_gst_id", table, columnPrefix + "_seller_gst_id"));
        columns.add(Column.aliased("buyer_gst_id", table, columnPrefix + "_buyer_gst_id"));
        columns.add(Column.aliased("trade_amount", table, columnPrefix + "_trade_amount"));
        columns.add(Column.aliased("invoice_date", table, columnPrefix + "_invoice_date"));
        columns.add(Column.aliased("invoice_number", table, columnPrefix + "_invoice_number"));
        columns.add(Column.aliased("trade_doc_type", table, columnPrefix + "_trade_doc_type"));
        columns.add(Column.aliased("trade_doc_source", table, columnPrefix + "_trade_doc_source"));
        columns.add(Column.aliased("trade_doc_credibility", table, columnPrefix + "_trade_doc_credibility"));
        columns.add(Column.aliased("trade_milestone_status", table, columnPrefix + "_trade_milestone_status"));
        columns.add(Column.aliased("trade_advance_payment", table, columnPrefix + "_trade_advance_payment"));
        columns.add(Column.aliased("anchor_trader_name", table, columnPrefix + "_anchor_trader_name"));
        columns.add(Column.aliased("trade_partner_name", table, columnPrefix + "_trade_partner_name"));
        columns.add(Column.aliased("invoice_term", table, columnPrefix + "_invoice_term"));
        columns.add(Column.aliased("acceptance_from_trade_partner", table, columnPrefix + "_acceptance_from_trade_partner"));
        columns.add(Column.aliased("reason_for_finance", table, columnPrefix + "_reason_for_finance"));
        columns.add(Column.aliased("trade_partner_sector", table, columnPrefix + "_trade_partner_sector"));
        columns.add(Column.aliased("trade_partner_location", table, columnPrefix + "_trade_partner_location"));
        columns.add(Column.aliased("trade_partner_gst_compliance_score", table, columnPrefix + "_trade_partner_gst_compliance_score"));

        columns.add(Column.aliased("financerequest_id", table, columnPrefix + "_financerequest_id"));
        columns.add(Column.aliased("anchortrader_id", table, columnPrefix + "_anchortrader_id"));
        columns.add(Column.aliased("tradepartner_id", table, columnPrefix + "_tradepartner_id"));
        return columns;
    }
}
