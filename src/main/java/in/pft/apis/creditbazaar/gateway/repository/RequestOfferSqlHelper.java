package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class RequestOfferSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("req_off_ulid_id", table, columnPrefix + "_req_off_ulid_id"));
        columns.add(Column.aliased("req_offer_ref_no", table, columnPrefix + "_req_offer_ref_no"));
        columns.add(Column.aliased("offer_value", table, columnPrefix + "_offer_value"));
        columns.add(Column.aliased("request_amt", table, columnPrefix + "_request_amt"));
        columns.add(Column.aliased("trade_value", table, columnPrefix + "_trade_value"));
        columns.add(Column.aliased("margin_ptg", table, columnPrefix + "_margin_ptg"));
        columns.add(Column.aliased("margin_value", table, columnPrefix + "_margin_value"));
        columns.add(Column.aliased("amount_aft_margin", table, columnPrefix + "_amount_aft_margin"));
        columns.add(Column.aliased("interest_ptg", table, columnPrefix + "_interest_ptg"));
        columns.add(Column.aliased("term", table, columnPrefix + "_term"));
        columns.add(Column.aliased("interest_value", table, columnPrefix + "_interest_value"));
        columns.add(Column.aliased("net_amount", table, columnPrefix + "_net_amount"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("finance_request_date", table, columnPrefix + "_finance_request_date"));
        columns.add(Column.aliased("anchor_trader_name", table, columnPrefix + "_anchor_trader_name"));
        columns.add(Column.aliased("trade_partner_name", table, columnPrefix + "_trade_partner_name"));
        columns.add(Column.aliased("anchor_trader_gst", table, columnPrefix + "_anchor_trader_gst"));
        columns.add(Column.aliased("trade_partner_gst", table, columnPrefix + "_trade_partner_gst"));
        columns.add(Column.aliased("anchor_trader_gst_compliance_score", table, columnPrefix + "_anchor_trader_gst_compliance_score"));
        columns.add(Column.aliased("anchor_trader_gsterp_peer_score", table, columnPrefix + "_anchor_trader_gsterp_peer_score"));
        columns.add(Column.aliased("seller_trade_performance_index", table, columnPrefix + "_seller_trade_performance_index"));

        columns.add(Column.aliased("financerequest_id", table, columnPrefix + "_financerequest_id"));
        columns.add(Column.aliased("financepartner_id", table, columnPrefix + "_financepartner_id"));
        return columns;
    }
}
