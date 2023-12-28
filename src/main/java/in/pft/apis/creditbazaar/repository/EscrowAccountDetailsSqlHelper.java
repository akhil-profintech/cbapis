package in.pft.apis.creditbazaar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class EscrowAccountDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("escrow_details_id", table, columnPrefix + "_escrow_details_id"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("customer_id", table, columnPrefix + "_customer_id"));
        columns.add(Column.aliased("acc_name", table, columnPrefix + "_acc_name"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("virtual_acc_number", table, columnPrefix + "_virtual_acc_number"));
        columns.add(Column.aliased("pooling_acc_number", table, columnPrefix + "_pooling_acc_number"));

        columns.add(Column.aliased("disbursement_id", table, columnPrefix + "_disbursement_id"));
        columns.add(Column.aliased("repayment_id", table, columnPrefix + "_repayment_id"));
        return columns;
    }
}
