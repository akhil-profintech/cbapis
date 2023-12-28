package in.pft.apis.creditbazaar.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class InstaAlertSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("insta_alert_id", table, columnPrefix + "_insta_alert_id"));
        columns.add(Column.aliased("fin_req_id", table, columnPrefix + "_fin_req_id"));
        columns.add(Column.aliased("sub_grp_id", table, columnPrefix + "_sub_grp_id"));
        columns.add(Column.aliased("customer_code", table, columnPrefix + "_customer_code"));
        columns.add(Column.aliased("customer_name", table, columnPrefix + "_customer_name"));
        columns.add(Column.aliased("product_code", table, columnPrefix + "_product_code"));
        columns.add(Column.aliased("pooling_account_number", table, columnPrefix + "_pooling_account_number"));
        columns.add(Column.aliased("transaction_type", table, columnPrefix + "_transaction_type"));
        columns.add(Column.aliased("batch_amt", table, columnPrefix + "_batch_amt"));
        columns.add(Column.aliased("batch_amt_ccd", table, columnPrefix + "_batch_amt_ccd"));
        columns.add(Column.aliased("credit_date", table, columnPrefix + "_credit_date"));
        columns.add(Column.aliased("va_number", table, columnPrefix + "_va_number"));
        columns.add(Column.aliased("utr_no", table, columnPrefix + "_utr_no"));
        columns.add(Column.aliased("credit_generation_time", table, columnPrefix + "_credit_generation_time"));
        columns.add(Column.aliased("remitter_name", table, columnPrefix + "_remitter_name"));
        columns.add(Column.aliased("remitter_account_number", table, columnPrefix + "_remitter_account_number"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("lastupdated_date_time", table, columnPrefix + "_lastupdated_date_time"));
        columns.add(Column.aliased("last_updated_by", table, columnPrefix + "_last_updated_by"));

        columns.add(Column.aliased("trade_entity_id", table, columnPrefix + "_trade_entity_id"));
        return columns;
    }
}
