package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class UpdateVASqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("update_vir_acc_id", table, columnPrefix + "_update_vir_acc_id"));
        columns.add(Column.aliased("fin_req_id", table, columnPrefix + "_fin_req_id"));
        columns.add(Column.aliased("sub_grp_id", table, columnPrefix + "_sub_grp_id"));
        columns.add(Column.aliased("msg_id", table, columnPrefix + "_msg_id"));
        columns.add(Column.aliased("cnv_id", table, columnPrefix + "_cnv_id"));
        columns.add(Column.aliased("ext_ref_id", table, columnPrefix + "_ext_ref_id"));
        columns.add(Column.aliased("biz_obj_id", table, columnPrefix + "_biz_obj_id"));
        columns.add(Column.aliased("app_id", table, columnPrefix + "_app_id"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("va_num", table, columnPrefix + "_va_num"));
        columns.add(Column.aliased("fin_par", table, columnPrefix + "_fin_par"));
        columns.add(Column.aliased("client_code", table, columnPrefix + "_client_code"));
        columns.add(Column.aliased("request_date_time", table, columnPrefix + "_request_date_time"));
        columns.add(Column.aliased("reslt", table, columnPrefix + "_reslt"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("status_desc", table, columnPrefix + "_status_desc"));
        columns.add(Column.aliased("error_code", table, columnPrefix + "_error_code"));
        columns.add(Column.aliased("response_date_time", table, columnPrefix + "_response_date_time"));
        columns.add(Column.aliased("lastupdated_date_time", table, columnPrefix + "_lastupdated_date_time"));
        columns.add(Column.aliased("last_updated_by", table, columnPrefix + "_last_updated_by"));

        columns.add(Column.aliased("trade_entity_id", table, columnPrefix + "_trade_entity_id"));
        return columns;
    }
}
