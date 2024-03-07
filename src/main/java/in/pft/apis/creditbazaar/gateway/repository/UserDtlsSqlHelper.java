package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class UserDtlsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("user_ulid_id", table, columnPrefix + "_user_ulid_id"));
        columns.add(Column.aliased("user_name", table, columnPrefix + "_user_name"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("is_anchor_trader_enabled", table, columnPrefix + "_is_anchor_trader_enabled"));
        columns.add(Column.aliased("anchor_trader_id", table, columnPrefix + "_anchor_trader_id"));
        columns.add(Column.aliased("is_trade_partner_enabled", table, columnPrefix + "_is_trade_partner_enabled"));
        columns.add(Column.aliased("trade_partner_id", table, columnPrefix + "_trade_partner_id"));
        columns.add(Column.aliased("is_finance_partner_enabled", table, columnPrefix + "_is_finance_partner_enabled"));
        columns.add(Column.aliased("finance_partner_id", table, columnPrefix + "_finance_partner_id"));
        columns.add(Column.aliased("default_persona", table, columnPrefix + "_default_persona"));

        return columns;
    }
}
