package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class ClientCodesSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("client_code", table, columnPrefix + "_client_code"));
        columns.add(Column.aliased("client_chars_code", table, columnPrefix + "_client_chars_code"));
        columns.add(Column.aliased("client_name", table, columnPrefix + "_client_name"));

        return columns;
    }
}
