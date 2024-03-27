package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ParticipantSettlementSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("participant_settlement_id", table, columnPrefix + "_participant_settlement_id"));
        columns.add(Column.aliased("participant_settlement_ulid_id", table, columnPrefix + "_participant_settlement_ulid_id"));
        columns.add(Column.aliased("participant_id", table, columnPrefix + "_participant_id"));
        columns.add(Column.aliased("participant_name", table, columnPrefix + "_participant_name"));
        columns.add(Column.aliased("gst_id", table, columnPrefix + "_gst_id"));
        columns.add(Column.aliased("settlement_type", table, columnPrefix + "_settlement_type"));
        columns.add(Column.aliased("charge_type", table, columnPrefix + "_charge_type"));
        columns.add(Column.aliased("settlement_amount", table, columnPrefix + "_settlement_amount"));
        columns.add(Column.aliased("settlement_date", table, columnPrefix + "_settlement_date"));
        columns.add(Column.aliased("settlement_due_date", table, columnPrefix + "_settlement_due_date"));
        columns.add(Column.aliased("settlement_contact_info", table, columnPrefix + "_settlement_contact_info"));
        columns.add(Column.aliased("patron_of_payment", table, columnPrefix + "_patron_of_payment"));
        columns.add(Column.aliased("recipient_of_payment", table, columnPrefix + "_recipient_of_payment"));
        columns.add(Column.aliased("settlement_method", table, columnPrefix + "_settlement_method"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("acc_name", table, columnPrefix + "_acc_name"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("acc_number", table, columnPrefix + "_acc_number"));
        columns.add(Column.aliased("doc_id", table, columnPrefix + "_doc_id"));
        columns.add(Column.aliased("record_status", table, columnPrefix + "_record_status"));

        columns.add(Column.aliased("settlement_id", table, columnPrefix + "_settlement_id"));
        return columns;
    }
}
