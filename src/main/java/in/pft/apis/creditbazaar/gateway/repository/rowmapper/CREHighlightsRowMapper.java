package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.CREHighlights;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CREHighlights}, with proper type conversions.
 */
@Service
public class CREHighlightsRowMapper implements BiFunction<Row, String, CREHighlights> {

    private final ColumnConverter converter;

    public CREHighlightsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CREHighlights} stored in the database.
     */
    @Override
    public CREHighlights apply(Row row, String prefix) {
        CREHighlights entity = new CREHighlights();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCreHighlightsId(converter.fromRow(row, prefix + "_cre_highlights_id", Long.class));
        entity.setCreHighlightsUlidId(converter.fromRow(row, prefix + "_cre_highlights_ulid_id", String.class));
        entity.setCreRequestId(converter.fromRow(row, prefix + "_cre_request_id", String.class));
        entity.setHighlights(converter.fromRow(row, prefix + "_highlights", String.class));
        entity.setIndividualassessmentId(converter.fromRow(row, prefix + "_individualassessment_id", Long.class));
        return entity;
    }
}
