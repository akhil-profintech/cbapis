package in.pft.apis.creditbazaar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class OrganizationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("org_id", table, columnPrefix + "_org_id"));
        columns.add(Column.aliased("org_name", table, columnPrefix + "_org_name"));
        columns.add(Column.aliased("org_address", table, columnPrefix + "_org_address"));
        columns.add(Column.aliased("industry_type", table, columnPrefix + "_industry_type"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));

        return columns;
    }
}
