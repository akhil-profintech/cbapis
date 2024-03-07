package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CREHighlightsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("cre_highlights_id", table, columnPrefix + "_cre_highlights_id"));
        columns.add(Column.aliased("cre_highlights_ulid_id", table, columnPrefix + "_cre_highlights_ulid_id"));
        columns.add(Column.aliased("cre_request_id", table, columnPrefix + "_cre_request_id"));
        columns.add(Column.aliased("highlights", table, columnPrefix + "_highlights"));

        columns.add(Column.aliased("individualassessment_id", table, columnPrefix + "_individualassessment_id"));
        return columns;
    }
}
