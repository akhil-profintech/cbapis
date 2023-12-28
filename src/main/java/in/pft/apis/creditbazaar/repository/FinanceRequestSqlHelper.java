package in.pft.apis.creditbazaar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class FinanceRequestSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("request_id", table, columnPrefix + "_request_id"));
        columns.add(Column.aliased("finance_request_ref_no", table, columnPrefix + "_finance_request_ref_no"));
        columns.add(Column.aliased("request_amount", table, columnPrefix + "_request_amount"));
        columns.add(Column.aliased("request_date", table, columnPrefix + "_request_date"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));
        columns.add(Column.aliased("request_status", table, columnPrefix + "_request_status"));
        columns.add(Column.aliased("due_date", table, columnPrefix + "_due_date"));

        columns.add(Column.aliased("anchortrader_id", table, columnPrefix + "_anchortrader_id"));
        return columns;
    }
}
