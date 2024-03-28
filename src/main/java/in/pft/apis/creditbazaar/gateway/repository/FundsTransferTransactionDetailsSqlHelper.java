package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class FundsTransferTransactionDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("ft_trnx_details_id", table, columnPrefix + "_ft_trnx_details_id"));
        columns.add(Column.aliased("ft_trnx_details_ulid", table, columnPrefix + "_ft_trnx_details_ulid"));
        columns.add(Column.aliased("transaction_id", table, columnPrefix + "_transaction_id"));
        columns.add(Column.aliased("debit_account_number", table, columnPrefix + "_debit_account_number"));
        columns.add(Column.aliased("credit_account_number", table, columnPrefix + "_credit_account_number"));
        columns.add(Column.aliased("remitter_name", table, columnPrefix + "_remitter_name"));
        columns.add(Column.aliased("amount", table, columnPrefix + "_amount"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));
        columns.add(Column.aliased("transaction_type", table, columnPrefix + "_transaction_type"));
        columns.add(Column.aliased("payment_description", table, columnPrefix + "_payment_description"));
        columns.add(Column.aliased("beneficiary_ifsc", table, columnPrefix + "_beneficiary_ifsc"));
        columns.add(Column.aliased("beneficiary_name", table, columnPrefix + "_beneficiary_name"));
        columns.add(Column.aliased("beneficiary_address", table, columnPrefix + "_beneficiary_address"));
        columns.add(Column.aliased("email_id", table, columnPrefix + "_email_id"));
        columns.add(Column.aliased("mobile_no", table, columnPrefix + "_mobile_no"));
        columns.add(Column.aliased("transaction_ref_no", table, columnPrefix + "_transaction_ref_no"));
        columns.add(Column.aliased("trnx_resource_data_status", table, columnPrefix + "_trnx_resource_data_status"));
        columns.add(Column.aliased("trnx_meta_data_status", table, columnPrefix + "_trnx_meta_data_status"));
        columns.add(Column.aliased("transaction_date_time", table, columnPrefix + "_transaction_date_time"));

        columns.add(Column.aliased("participantsettlement_id", table, columnPrefix + "_participantsettlement_id"));
        columns.add(Column.aliased("disbursement_id", table, columnPrefix + "_disbursement_id"));
        columns.add(Column.aliased("repayment_id", table, columnPrefix + "_repayment_id"));
        return columns;
    }
}
