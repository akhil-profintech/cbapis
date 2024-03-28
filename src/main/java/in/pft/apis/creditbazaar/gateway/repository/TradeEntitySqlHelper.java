package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class TradeEntitySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("entity_id", table, columnPrefix + "_entity_id"));
        columns.add(Column.aliased("entity_ulid_id", table, columnPrefix + "_entity_ulid_id"));
        columns.add(Column.aliased("entity_name", table, columnPrefix + "_entity_name"));
        columns.add(Column.aliased("entity_desc", table, columnPrefix + "_entity_desc"));
        columns.add(Column.aliased("entity_gst", table, columnPrefix + "_entity_gst"));

        return columns;
    }
}
