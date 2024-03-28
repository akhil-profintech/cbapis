package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link IndividualAssessment}, with proper type conversions.
 */
@Service
public class IndividualAssessmentRowMapper implements BiFunction<Row, String, IndividualAssessment> {

    private final ColumnConverter converter;

    public IndividualAssessmentRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link IndividualAssessment} stored in the database.
     */
    @Override
    public IndividualAssessment apply(Row row, String prefix) {
        IndividualAssessment entity = new IndividualAssessment();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAssessmentId(converter.fromRow(row, prefix + "_assessment_id", Long.class));
        entity.setAssessmentUlidId(converter.fromRow(row, prefix + "_assessment_ulid_id", String.class));
        entity.setCreditScore(converter.fromRow(row, prefix + "_credit_score", Double.class));
        entity.setFinalVerdict(converter.fromRow(row, prefix + "_final_verdict", String.class));
        entity.setCreRequestId(converter.fromRow(row, prefix + "_cre_request_id", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", String.class));
        entity.setTradePartnerGST(converter.fromRow(row, prefix + "_trade_partner_gst", String.class));
        entity.setTradePartnerId(converter.fromRow(row, prefix + "_trade_partner_id", String.class));
        entity.setInvoiceAmount(converter.fromRow(row, prefix + "_invoice_amount", Long.class));
        entity.setInvoiceId(converter.fromRow(row, prefix + "_invoice_id", String.class));
        entity.setBaseScore(converter.fromRow(row, prefix + "_base_score", String.class));
        entity.setCtin(converter.fromRow(row, prefix + "_ctin", String.class));
        entity.setInvDate(converter.fromRow(row, prefix + "_inv_date", String.class));
        entity.setCbProcessId(converter.fromRow(row, prefix + "_cb_process_id", Long.class));
        entity.setGrnPresent(converter.fromRow(row, prefix + "_grn_present", Boolean.class));
        entity.setEinvoicePresent(converter.fromRow(row, prefix + "_einvoice_present", Boolean.class));
        entity.setEwayBillPresent(converter.fromRow(row, prefix + "_eway_bill_present", Boolean.class));
        entity.setTradePartnerConfirmation(converter.fromRow(row, prefix + "_trade_partner_confirmation", Boolean.class));
        entity.setCbcreprocessId(converter.fromRow(row, prefix + "_cbcreprocess_id", Long.class));
        return entity;
    }
}
