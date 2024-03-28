package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class GstinSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("company_name", table, columnPrefix + "_company_name"));
        columns.add(Column.aliased("gst_id", table, columnPrefix + "_gst_id"));

        columns.add(Column.aliased("organization_id", table, columnPrefix + "_organization_id"));
        return columns;
    }
}
