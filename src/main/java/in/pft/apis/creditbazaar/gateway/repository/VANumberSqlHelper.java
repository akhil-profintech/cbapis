package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class VANumberSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("va_id", table, columnPrefix + "_va_id"));
        columns.add(Column.aliased("pooling_acc_number", table, columnPrefix + "_pooling_acc_number"));
        columns.add(Column.aliased("virtual_acc_number", table, columnPrefix + "_virtual_acc_number"));
        columns.add(Column.aliased("va_status", table, columnPrefix + "_va_status"));
        columns.add(Column.aliased("finance_request_id", table, columnPrefix + "_finance_request_id"));
        columns.add(Column.aliased("sub_group_id", table, columnPrefix + "_sub_group_id"));

        columns.add(Column.aliased("trade_entity_id", table, columnPrefix + "_trade_entity_id"));
        return columns;
    }
}
