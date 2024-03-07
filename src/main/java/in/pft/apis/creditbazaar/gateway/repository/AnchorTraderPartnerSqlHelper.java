package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class AnchorTraderPartnerSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("atpartner_id", table, columnPrefix + "_atpartner_id"));
        columns.add(Column.aliased("atpartner_ulid_id", table, columnPrefix + "_atpartner_ulid_id"));
        columns.add(Column.aliased("pan", table, columnPrefix + "_pan"));
        columns.add(Column.aliased("pan_status", table, columnPrefix + "_pan_status"));
        columns.add(Column.aliased("name", table, columnPrefix + "_name"));
        columns.add(Column.aliased("aadhar", table, columnPrefix + "_aadhar"));
        columns.add(Column.aliased("aadhar_otp", table, columnPrefix + "_aadhar_otp"));
        columns.add(Column.aliased("aadhar_status", table, columnPrefix + "_aadhar_status"));
        columns.add(Column.aliased("aadhar_name", table, columnPrefix + "_aadhar_name"));
        columns.add(Column.aliased("aadhar_address", table, columnPrefix + "_aadhar_address"));

        columns.add(Column.aliased("anchortrader_id", table, columnPrefix + "_anchortrader_id"));
        return columns;
    }
}
