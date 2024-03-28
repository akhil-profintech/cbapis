package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class SettlementSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("settlement_id", table, columnPrefix + "_settlement_id"));
        columns.add(Column.aliased("settlement_ulid_id", table, columnPrefix + "_settlement_ulid_id"));
        columns.add(Column.aliased("settlement_ref_no", table, columnPrefix + "_settlement_ref_no"));
        columns.add(Column.aliased("accepted_offer_ulid_id", table, columnPrefix + "_accepted_offer_ulid_id"));
        columns.add(Column.aliased("placed_offer_ulid_id", table, columnPrefix + "_placed_offer_ulid_id"));
        columns.add(Column.aliased("req_off_ulid_id", table, columnPrefix + "_req_off_ulid_id"));
        columns.add(Column.aliased("dbmt_request_id", table, columnPrefix + "_dbmt_request_id"));
        columns.add(Column.aliased("dbmt_id", table, columnPrefix + "_dbmt_id"));
        columns.add(Column.aliased("dbmt_date", table, columnPrefix + "_dbmt_date"));
        columns.add(Column.aliased("dbmt_status", table, columnPrefix + "_dbmt_status"));
        columns.add(Column.aliased("dbmt_amount", table, columnPrefix + "_dbmt_amount"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));
        columns.add(Column.aliased("record_status", table, columnPrefix + "_record_status"));

        columns.add(Column.aliased("financerequest_id", table, columnPrefix + "_financerequest_id"));
        return columns;
    }
}
