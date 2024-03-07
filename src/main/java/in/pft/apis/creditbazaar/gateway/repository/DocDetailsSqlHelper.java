package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DocDetailsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("doc_details_id", table, columnPrefix + "_doc_details_id"));
        columns.add(Column.aliased("doc_details_ulid_id", table, columnPrefix + "_doc_details_ulid_id"));
        columns.add(Column.aliased("doc_record_id", table, columnPrefix + "_doc_record_id"));
        columns.add(Column.aliased("link", table, columnPrefix + "_link"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("doc_type", table, columnPrefix + "_doc_type"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));

        columns.add(Column.aliased("finance_request_id", table, columnPrefix + "_finance_request_id"));
        return columns;
    }
}
