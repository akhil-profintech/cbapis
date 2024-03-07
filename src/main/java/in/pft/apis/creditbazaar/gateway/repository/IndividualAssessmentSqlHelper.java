package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class IndividualAssessmentSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("assessment_id", table, columnPrefix + "_assessment_id"));
        columns.add(Column.aliased("assessment_ulid_id", table, columnPrefix + "_assessment_ulid_id"));
        columns.add(Column.aliased("credit_score", table, columnPrefix + "_credit_score"));
        columns.add(Column.aliased("final_verdict", table, columnPrefix + "_final_verdict"));
        columns.add(Column.aliased("cre_request_id", table, columnPrefix + "_cre_request_id"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("trade_partner_gst", table, columnPrefix + "_trade_partner_gst"));
        columns.add(Column.aliased("trade_partner_id", table, columnPrefix + "_trade_partner_id"));
        columns.add(Column.aliased("invoice_amount", table, columnPrefix + "_invoice_amount"));
        columns.add(Column.aliased("invoice_id", table, columnPrefix + "_invoice_id"));

        columns.add(Column.aliased("cbcreprocess_id", table, columnPrefix + "_cbcreprocess_id"));
        return columns;
    }
}
