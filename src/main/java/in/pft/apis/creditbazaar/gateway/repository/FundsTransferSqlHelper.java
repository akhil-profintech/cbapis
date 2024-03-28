package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class FundsTransferSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("funds_transfer_id", table, columnPrefix + "_funds_transfer_id"));
        columns.add(Column.aliased("funds_transfer_ref_no", table, columnPrefix + "_funds_transfer_ref_no"));
        columns.add(Column.aliased("fin_req_id", table, columnPrefix + "_fin_req_id"));
        columns.add(Column.aliased("sub_grp_id", table, columnPrefix + "_sub_grp_id"));
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
        columns.add(Column.aliased("message_type", table, columnPrefix + "_message_type"));
        columns.add(Column.aliased("transaction_date_time", table, columnPrefix + "_transaction_date_time"));
        columns.add(Column.aliased("transaction_ref_no", table, columnPrefix + "_transaction_ref_no"));
        columns.add(Column.aliased("trnx_meta_data_status", table, columnPrefix + "_trnx_meta_data_status"));
        columns.add(Column.aliased("trnx_meta_data_message", table, columnPrefix + "_trnx_meta_data_message"));
        columns.add(Column.aliased("trnx_meta_data_version", table, columnPrefix + "_trnx_meta_data_version"));
        columns.add(Column.aliased("trnx_meta_data_time", table, columnPrefix + "_trnx_meta_data_time"));
        columns.add(Column.aliased("trnx_resource_data_beneficiary_name", table, columnPrefix + "_trnx_resource_data_beneficiary_name"));
        columns.add(Column.aliased("trnx_resource_data_transaction_id", table, columnPrefix + "_trnx_resource_data_transaction_id"));
        columns.add(Column.aliased("trnx_resource_data_status", table, columnPrefix + "_trnx_resource_data_status"));
        columns.add(Column.aliased("funds_transfer_status", table, columnPrefix + "_funds_transfer_status"));
        columns.add(Column.aliased("lastupdated_date_time", table, columnPrefix + "_lastupdated_date_time"));
        columns.add(Column.aliased("last_updated_by", table, columnPrefix + "_last_updated_by"));

        columns.add(Column.aliased("trade_entity_id", table, columnPrefix + "_trade_entity_id"));
        return columns;
    }
}
