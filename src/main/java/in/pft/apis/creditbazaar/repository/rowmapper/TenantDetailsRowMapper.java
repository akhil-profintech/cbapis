package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.TenantDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TenantDetails}, with proper type conversions.
 */
@Service
public class TenantDetailsRowMapper implements BiFunction<Row, String, TenantDetails> {

    private final ColumnConverter converter;

    public TenantDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TenantDetails} stored in the database.
     */
    @Override
    public TenantDetails apply(Row row, String prefix) {
        TenantDetails entity = new TenantDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        entity.setTenantSchema(converter.fromRow(row, prefix + "_tenant_schema", String.class));
        return entity;
    }
}
