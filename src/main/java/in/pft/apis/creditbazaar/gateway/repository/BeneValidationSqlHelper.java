package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class BeneValidationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("benevalidation_id", table, columnPrefix + "_benevalidation_id"));
        columns.add(Column.aliased("fin_req_id", table, columnPrefix + "_fin_req_id"));
        columns.add(Column.aliased("sub_grp_id", table, columnPrefix + "_sub_grp_id"));
        columns.add(Column.aliased("remitter_name", table, columnPrefix + "_remitter_name"));
        columns.add(Column.aliased("remitter_mobile_number", table, columnPrefix + "_remitter_mobile_number"));
        columns.add(Column.aliased("debtor_account_id", table, columnPrefix + "_debtor_account_id"));
        columns.add(Column.aliased("account_type", table, columnPrefix + "_account_type"));
        columns.add(Column.aliased("creditor_account_id", table, columnPrefix + "_creditor_account_id"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("payment_description", table, columnPrefix + "_payment_description"));
        columns.add(Column.aliased("transaction_reference_number", table, columnPrefix + "_transaction_reference_number"));
        columns.add(Column.aliased("merchant_code", table, columnPrefix + "_merchant_code"));
        columns.add(Column.aliased("identifier", table, columnPrefix + "_identifier"));
        columns.add(Column.aliased("request_date_time", table, columnPrefix + "_request_date_time"));
        columns.add(Column.aliased("meta_data_status", table, columnPrefix + "_meta_data_status"));
        columns.add(Column.aliased("meta_data_message", table, columnPrefix + "_meta_data_message"));
        columns.add(Column.aliased("meta_data_version", table, columnPrefix + "_meta_data_version"));
        columns.add(Column.aliased("meta_data_time", table, columnPrefix + "_meta_data_time"));
        columns.add(Column.aliased("resource_data_creditor_account_id", table, columnPrefix + "_resource_data_creditor_account_id"));
        columns.add(Column.aliased("resource_data_creditor_name", table, columnPrefix + "_resource_data_creditor_name"));
        columns.add(
            Column.aliased(
                "resource_data_transaction_reference_number",
                table,
                columnPrefix + "_resource_data_transaction_reference_number"
            )
        );
        columns.add(Column.aliased("resource_data_transaction_id", table, columnPrefix + "_resource_data_transaction_id"));
        columns.add(Column.aliased("resource_data_response_code", table, columnPrefix + "_resource_data_response_code"));
        columns.add(Column.aliased("resource_data_transaction_time", table, columnPrefix + "_resource_data_transaction_time"));
        columns.add(Column.aliased("resource_data_identifier", table, columnPrefix + "_resource_data_identifier"));
        columns.add(Column.aliased("response_date_time", table, columnPrefix + "_response_date_time"));
        columns.add(Column.aliased("lastupdated_date_time", table, columnPrefix + "_lastupdated_date_time"));
        columns.add(Column.aliased("last_updated_by", table, columnPrefix + "_last_updated_by"));

        columns.add(Column.aliased("trade_entity_id", table, columnPrefix + "_trade_entity_id"));
        return columns;
    }
}
