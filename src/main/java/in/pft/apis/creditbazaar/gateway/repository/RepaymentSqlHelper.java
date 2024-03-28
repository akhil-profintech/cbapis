package in.pft.apis.creditbazaar.gateway.repository;

import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

import java.util.ArrayList;
import java.util.List;

public class RepaymentSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("repayment_id", table, columnPrefix + "_repayment_id"));
        columns.add(Column.aliased("repayment_ulid_id", table, columnPrefix + "_repayment_ulid_id"));
        columns.add(Column.aliased("repayment_ref_no", table, columnPrefix + "_repayment_ref_no"));
        columns.add(Column.aliased("accepted_offer_ulid_id", table, columnPrefix + "_accepted_offer_ulid_id"));
        columns.add(Column.aliased("placed_offer_ulid_id", table, columnPrefix + "_placed_offer_ulid_id"));
        columns.add(Column.aliased("req_off_ulid_id", table, columnPrefix + "_req_off_ulid_id"));
        columns.add(Column.aliased("offer_accepted_date", table, columnPrefix + "_offer_accepted_date"));
        columns.add(Column.aliased("dbmt_request_id", table, columnPrefix + "_dbmt_request_id"));
        columns.add(Column.aliased("dbmt_status", table, columnPrefix + "_dbmt_status"));
        columns.add(Column.aliased("dbmt_date_time", table, columnPrefix + "_dbmt_date_time"));
        columns.add(Column.aliased("dbmt_id", table, columnPrefix + "_dbmt_id"));
        columns.add(Column.aliased("dbmt_amount", table, columnPrefix + "_dbmt_amount"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));
        columns.add(Column.aliased("repayment_status", table, columnPrefix + "_repayment_status"));
        columns.add(Column.aliased("repayment_date", table, columnPrefix + "_repayment_date"));
        columns.add(Column.aliased("repayment_amount", table, columnPrefix + "_repayment_amount"));
        columns.add(Column.aliased("finance_request_id", table, columnPrefix + "_finance_request_id"));
        columns.add(Column.aliased("repayment_due_date", table, columnPrefix + "_repayment_due_date"));
        columns.add(Column.aliased("total_repayment_amount", table, columnPrefix + "_total_repayment_amount"));
        columns.add(Column.aliased("amount_repayed", table, columnPrefix + "_amount_repayed"));
        columns.add(Column.aliased("amount_to_be_paid", table, columnPrefix + "_amount_to_be_paid"));
        columns.add(Column.aliased("source_account_name", table, columnPrefix + "_source_account_name"));
        columns.add(Column.aliased("source_account_number", table, columnPrefix + "_source_account_number"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("record_status", table, columnPrefix + "_record_status"));
        columns.add(Column.aliased("reference_number", table, columnPrefix + "_reference_number"));

        columns.add(Column.aliased("financerequest_id", table, columnPrefix + "_financerequest_id"));
        return columns;
    }
}
