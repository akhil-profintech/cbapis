package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class FinanceRequestMappingSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("finance_request_id", table, columnPrefix + "_finance_request_id"));
        columns.add(Column.aliased("finance_request_mapping_ulid_id", table, columnPrefix + "_finance_request_mapping_ulid_id"));
        columns.add(Column.aliased("anchor_trader_id", table, columnPrefix + "_anchor_trader_id"));
        columns.add(Column.aliased("finance_partner_id", table, columnPrefix + "_finance_partner_id"));
        columns.add(Column.aliased("anchor_trader_tenant_id", table, columnPrefix + "_anchor_trader_tenant_id"));
        columns.add(Column.aliased("finance_partner_tenant_id", table, columnPrefix + "_finance_partner_tenant_id"));

        return columns;
    }
}
