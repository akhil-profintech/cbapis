package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.DocDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link DocDetails}, with proper type conversions.
 */
@Service
public class DocDetailsRowMapper implements BiFunction<Row, String, DocDetails> {

    private final ColumnConverter converter;

    public DocDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DocDetails} stored in the database.
     */
    @Override
    public DocDetails apply(Row row, String prefix) {
        DocDetails entity = new DocDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setDocDetailsId(converter.fromRow(row, prefix + "_doc_details_id", Long.class));
        entity.setDocRecordId(converter.fromRow(row, prefix + "_doc_record_id", Long.class));
        entity.setLink(converter.fromRow(row, prefix + "_link", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setDocType(converter.fromRow(row, prefix + "_doc_type", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setDisbursementId(converter.fromRow(row, prefix + "_disbursement_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", Long.class));
        entity.setParticipantsettlementId(converter.fromRow(row, prefix + "_participantsettlement_id", Long.class));
        return entity;
    }
}
