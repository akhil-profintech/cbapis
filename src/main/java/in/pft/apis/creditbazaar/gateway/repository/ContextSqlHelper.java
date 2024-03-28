package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class ContextSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("transaction_id", table, columnPrefix + "_transaction_id"));
        columns.add(Column.aliased("transaction_date", table, columnPrefix + "_transaction_date"));
        columns.add(Column.aliased("action", table, columnPrefix + "_action"));
        columns.add(Column.aliased("user_id", table, columnPrefix + "_user_id"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("cp_code", table, columnPrefix + "_cp_code"));
        columns.add(Column.aliased("msgpayload", table, columnPrefix + "_msgpayload"));

        return columns;
    }
}
