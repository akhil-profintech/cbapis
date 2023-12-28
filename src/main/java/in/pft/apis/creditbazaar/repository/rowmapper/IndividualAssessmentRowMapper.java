package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.IndividualAssessment;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

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
        entity.setAssessmentId(converter.fromRow(row, prefix + "_assessment_id", String.class));
        entity.setCreditScore(converter.fromRow(row, prefix + "_credit_score", Double.class));
        entity.setFinalverdict(converter.fromRow(row, prefix + "_finalverdict", String.class));
        entity.setCreRequestId(converter.fromRow(row, prefix + "_cre_request_id", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", String.class));
        entity.setTradepartnerGST(converter.fromRow(row, prefix + "_tradepartner_gst", String.class));
        entity.setTradepartnerId(converter.fromRow(row, prefix + "_tradepartner_id", String.class));
        entity.setInvoiceAmount(converter.fromRow(row, prefix + "_invoice_amount", Long.class));
        entity.setInvoiceId(converter.fromRow(row, prefix + "_invoice_id", String.class));
        entity.setCbcreprocessId(converter.fromRow(row, prefix + "_cbcreprocess_id", Long.class));
        return entity;
    }
}
