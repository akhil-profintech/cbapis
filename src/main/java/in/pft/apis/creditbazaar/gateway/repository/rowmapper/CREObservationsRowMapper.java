package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.CREObservations;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link CREObservations}, with proper type conversions.
 */
@Service
public class CREObservationsRowMapper implements BiFunction<Row, String, CREObservations> {

    private final ColumnConverter converter;

    public CREObservationsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CREObservations} stored in the database.
     */
    @Override
    public CREObservations apply(Row row, String prefix) {
        CREObservations entity = new CREObservations();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCreObservationsId(converter.fromRow(row, prefix + "_cre_observations_id", Long.class));
        entity.setCreObservationsUlidId(converter.fromRow(row, prefix + "_cre_observations_ulid_id", String.class));
        entity.setCreRequestId(converter.fromRow(row, prefix + "_cre_request_id", String.class));
        entity.setObservations(converter.fromRow(row, prefix + "_observations", String.class));
        entity.setAssessmentId(converter.fromRow(row, prefix + "_assessment_id", Long.class));
        entity.setIndividualassessmentId(converter.fromRow(row, prefix + "_individualassessment_id", Long.class));
        return entity;
    }
}
