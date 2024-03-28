package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class CreditAccountDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("credit_acc_details_id", table, columnPrefix + "_credit_acc_details_id"));
        columns.add(Column.aliased("credit_account_details_ulid_id", table, columnPrefix + "_credit_account_details_ulid_id"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("customer_id", table, columnPrefix + "_customer_id"));
        columns.add(Column.aliased("acc_name", table, columnPrefix + "_acc_name"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("acc_number", table, columnPrefix + "_acc_number"));
        columns.add(Column.aliased("destination_account_name", table, columnPrefix + "_destination_account_name"));
        columns.add(Column.aliased("destination_account_number", table, columnPrefix + "_destination_account_number"));

        columns.add(Column.aliased("disbursement_id", table, columnPrefix + "_disbursement_id"));
        columns.add(Column.aliased("repayment_id", table, columnPrefix + "_repayment_id"));
        return columns;
    }
}
