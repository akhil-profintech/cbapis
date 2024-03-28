package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class ProspectRequestSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("prospect_request_id", table, columnPrefix + "_prospect_request_id"));
        columns.add(Column.aliased("prospect_request_ulid_id", table, columnPrefix + "_prospect_request_ulid_id"));
        columns.add(Column.aliased("anchor_trader_id", table, columnPrefix + "_anchor_trader_id"));
        columns.add(Column.aliased("request_amount", table, columnPrefix + "_request_amount"));
        columns.add(Column.aliased("prospect_request_date", table, columnPrefix + "_prospect_request_date"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));

        return columns;
    }
}
