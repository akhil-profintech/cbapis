package in.pft.apis.creditbazaar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CreditBazaarIntegratorSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("integrator_id", table, columnPrefix + "_integrator_id"));
        columns.add(Column.aliased("org_id", table, columnPrefix + "_org_id"));
        columns.add(Column.aliased("customer_name", table, columnPrefix + "_customer_name"));
        columns.add(Column.aliased("org_name", table, columnPrefix + "_org_name"));
        columns.add(Column.aliased("gst_id", table, columnPrefix + "_gst_id"));
        columns.add(Column.aliased("phone_number", table, columnPrefix + "_phone_number"));

        return columns;
    }
}
