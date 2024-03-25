package in.pft.apis.creditbazaar.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class AnchorTraderSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("at_id", table, columnPrefix + "_at_id"));
        columns.add(Column.aliased("at_ulid_id", table, columnPrefix + "_at_ulid_id"));
        columns.add(Column.aliased("org_id", table, columnPrefix + "_org_id"));
        columns.add(Column.aliased("tenant_id", table, columnPrefix + "_tenant_id"));
        columns.add(Column.aliased("customer_name", table, columnPrefix + "_customer_name"));
        columns.add(Column.aliased("org_name", table, columnPrefix + "_org_name"));
        columns.add(Column.aliased("gst_id", table, columnPrefix + "_gst_id"));
        columns.add(Column.aliased("phone_number", table, columnPrefix + "_phone_number"));
        columns.add(Column.aliased("anchor_trader_name", table, columnPrefix + "_anchor_trader_name"));
        columns.add(Column.aliased("location", table, columnPrefix + "_location"));
        columns.add(Column.aliased("anchor_trader_gst", table, columnPrefix + "_anchor_trader_gst"));
        columns.add(Column.aliased("anchor_trader_sector", table, columnPrefix + "_anchor_trader_sector"));
        columns.add(Column.aliased("anchor_trader_pan", table, columnPrefix + "_anchor_trader_pan"));
        columns.add(Column.aliased("kyc_completed", table, columnPrefix + "_kyc_completed"));
        columns.add(Column.aliased("bank_details", table, columnPrefix + "_bank_details"));
        columns.add(Column.aliased("email_id", table, columnPrefix + "_email_id"));
        columns.add(Column.aliased("personal_email_id", table, columnPrefix + "_personal_email_id"));
        columns.add(Column.aliased("account_number", table, columnPrefix + "_account_number"));
        columns.add(Column.aliased("ifsc_code", table, columnPrefix + "_ifsc_code"));
        columns.add(Column.aliased("bank_name", table, columnPrefix + "_bank_name"));
        columns.add(Column.aliased("branch_name", table, columnPrefix + "_branch_name"));
        columns.add(Column.aliased("erp_access", table, columnPrefix + "_erp_access"));
        columns.add(Column.aliased("type_of_firm", table, columnPrefix + "_type_of_firm"));
        columns.add(Column.aliased("pan_status", table, columnPrefix + "_pan_status"));
        columns.add(Column.aliased("tos_document", table, columnPrefix + "_tos_document"));
        columns.add(Column.aliased("accept_terms", table, columnPrefix + "_accept_terms"));
        columns.add(Column.aliased("accept_declaration", table, columnPrefix + "_accept_declaration"));
        columns.add(
            Column.aliased(
                "gst_registration_certificate_upload_status",
                table,
                columnPrefix + "_gst_registration_certificate_upload_status"
            )
        );
        columns.add(
            Column.aliased(
                "gst_registration_certificate_verification_status",
                table,
                columnPrefix + "_gst_registration_certificate_verification_status"
            )
        );
        columns.add(
            Column.aliased(
                "udhyam_registrationcertificate_upload_status",
                table,
                columnPrefix + "_udhyam_registrationcertificate_upload_status"
            )
        );
        columns.add(
            Column.aliased(
                "udhyam_registrationcertificate_verification_status",
                table,
                columnPrefix + "_udhyam_registrationcertificate_verification_status"
            )
        );
        columns.add(Column.aliased("kyc_declaration", table, columnPrefix + "_kyc_declaration"));

        return columns;
    }
}
