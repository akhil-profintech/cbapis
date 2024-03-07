package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.Gstin;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Gstin}, with proper type conversions.
 */
@Service
public class GstinRowMapper implements BiFunction<Row, String, Gstin> {

    private final ColumnConverter converter;

    public GstinRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Gstin} stored in the database.
     */
    @Override
    public Gstin apply(Row row, String prefix) {
        Gstin entity = new Gstin();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCompanyName(converter.fromRow(row, prefix + "_company_name", String.class));
        entity.setGstId(converter.fromRow(row, prefix + "_gst_id", String.class));
        entity.setOrganizationId(converter.fromRow(row, prefix + "_organization_id", Long.class));
        return entity;
    }
}
