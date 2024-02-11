package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.Organization;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Organization}, with proper type conversions.
 */
@Service
public class OrganizationRowMapper implements BiFunction<Row, String, Organization> {

    private final ColumnConverter converter;

    public OrganizationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Organization} stored in the database.
     */
    @Override
    public Organization apply(Row row, String prefix) {
        Organization entity = new Organization();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setOrgId(converter.fromRow(row, prefix + "_org_id", String.class));
        entity.setOrgName(converter.fromRow(row, prefix + "_org_name", String.class));
        entity.setOrgAddress(converter.fromRow(row, prefix + "_org_address", String.class));
        entity.setIndustryType(converter.fromRow(row, prefix + "_industry_type", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        return entity;
    }
}
