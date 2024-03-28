package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DisbursementSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("dbmt_id", table, columnPrefix + "_dbmt_id"));
        columns.add(Column.aliased("disbursement_ulid_id", table, columnPrefix + "_disbursement_ulid_id"));
        columns.add(Column.aliased("disbursement_ref_no", table, columnPrefix + "_disbursement_ref_no"));
        columns.add(Column.aliased("accepted_offer_ulid_id", table, columnPrefix + "_accepted_offer_ulid_id"));
        columns.add(Column.aliased("placed_offer_ulid_id", table, columnPrefix + "_placed_offer_ulid_id"));
        columns.add(Column.aliased("req_off_ulid_id", table, columnPrefix + "_req_off_ulid_id"));
        columns.add(Column.aliased("offer_accepted_date", table, columnPrefix + "_offer_accepted_date"));
        columns.add(Column.aliased("dbmt_request_id", table, columnPrefix + "_dbmt_request_id"));
        columns.add(Column.aliased("dbmt_req_amount", table, columnPrefix + "_dbmt_req_amount"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));
        columns.add(Column.aliased("dbmt_request_date", table, columnPrefix + "_dbmt_request_date"));
        columns.add(Column.aliased("dbmt_status", table, columnPrefix + "_dbmt_status"));
        columns.add(Column.aliased("offer_status", table, columnPrefix + "_offer_status"));
        columns.add(Column.aliased("doc_id", table, columnPrefix + "_doc_id"));
        columns.add(Column.aliased("dbmt_date_time", table, columnPrefix + "_dbmt_date_time"));
        columns.add(Column.aliased("dbmt_amount", table, columnPrefix + "_dbmt_amount"));
        columns.add(Column.aliased("finance_request_id", table, columnPrefix + "_finance_request_id"));
        columns.add(Column.aliased("amount_to_be_disbursed", table, columnPrefix + "_amount_to_be_disbursed"));
        columns.add(Column.aliased("destination_account_name", table, columnPrefix + "_destination_account_name"));
        columns.add(Column.aliased("destination_account_number", table, columnPrefix + "_destination_account_number"));
        columns.add(Column.aliased("record_status", table, columnPrefix + "_record_status"));
        columns.add(Column.aliased("action_by_date", table, columnPrefix + "_action_by_date"));

        columns.add(Column.aliased("financerequest_id", table, columnPrefix + "_financerequest_id"));
        columns.add(Column.aliased("financepartner_id", table, columnPrefix + "_financepartner_id"));
        return columns;
    }
}
