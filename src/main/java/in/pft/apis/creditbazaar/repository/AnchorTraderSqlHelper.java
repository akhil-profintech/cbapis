package in.pft.apis.creditbazaar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class AnchorTraderSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("at_id", table, columnPrefix + "_at_id"));
        columns.add(Column.aliased("org_id", table, columnPrefix + "_org_id"));
        columns.add(Column.aliased("customer_name", table, columnPrefix + "_customer_name"));
        columns.add(Column.aliased("org_name", table, columnPrefix + "_org_name"));
        columns.add(Column.aliased("gst_id", table, columnPrefix + "_gst_id"));
        columns.add(Column.aliased("phone_number", table, columnPrefix + "_phone_number"));
        columns.add(Column.aliased("anchor_trader_name", table, columnPrefix + "_anchor_trader_name"));
        columns.add(Column.aliased("location", table, columnPrefix + "_location"));
        columns.add(Column.aliased("anchor_trader_gst", table, columnPrefix + "_anchor_trader_gst"));
        columns.add(Column.aliased("anchor_trader_sector", table, columnPrefix + "_anchor_trader_sector"));
        columns.add(Column.aliased("anchor_trader_pan", table, columnPrefix + "_anchor_trader_pan"));
        columns.add(Column.aliased("kyc_completed", table, columnPrefix + "_kyc_completed"));
        columns.add(Column.aliased("bank_details", table, columnPrefix + "_bank_details"));

        return columns;
    }
}
