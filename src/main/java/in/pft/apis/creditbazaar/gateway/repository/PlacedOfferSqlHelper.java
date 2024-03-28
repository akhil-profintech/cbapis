package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class PlacedOfferSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("placed_offer_ulid_id", table, columnPrefix + "_placed_offer_ulid_id"));
        columns.add(Column.aliased("placed_offer_ref_no", table, columnPrefix + "_placed_offer_ref_no"));
        columns.add(Column.aliased("req_off_ulid_id", table, columnPrefix + "_req_off_ulid_id"));
        columns.add(Column.aliased("request_offer_ref_no", table, columnPrefix + "_request_offer_ref_no"));
        columns.add(Column.aliased("value", table, columnPrefix + "_value"));
        columns.add(Column.aliased("req_amount", table, columnPrefix + "_req_amount"));
        columns.add(Column.aliased("margin_ptg", table, columnPrefix + "_margin_ptg"));
        columns.add(Column.aliased("margin_value", table, columnPrefix + "_margin_value"));
        columns.add(Column.aliased("amount_aft_margin", table, columnPrefix + "_amount_aft_margin"));
        columns.add(Column.aliased("interest_ptg", table, columnPrefix + "_interest_ptg"));
        columns.add(Column.aliased("term", table, columnPrefix + "_term"));
        columns.add(Column.aliased("interest_value", table, columnPrefix + "_interest_value"));
        columns.add(Column.aliased("net_amount", table, columnPrefix + "_net_amount"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("offer_date", table, columnPrefix + "_offer_date"));
        columns.add(Column.aliased("placed_offer_date", table, columnPrefix + "_placed_offer_date"));
        columns.add(Column.aliased("anchor_trader", table, columnPrefix + "_anchor_trader"));
        columns.add(Column.aliased("trade_partner", table, columnPrefix + "_trade_partner"));
        columns.add(Column.aliased("disbursal_amount", table, columnPrefix + "_disbursal_amount"));

        columns.add(Column.aliased("financerequest_id", table, columnPrefix + "_financerequest_id"));
        columns.add(Column.aliased("financepartner_id", table, columnPrefix + "_financepartner_id"));
        return columns;
    }
}
