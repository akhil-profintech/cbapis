package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.CreditBazaarIntegrator;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link CreditBazaarIntegrator}, with proper type conversions.
 */
@Service
public class CreditBazaarIntegratorRowMapper implements BiFunction<Row, String, CreditBazaarIntegrator> {

    private final ColumnConverter converter;

    public CreditBazaarIntegratorRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreditBazaarIntegrator} stored in the database.
     */
    @Override
    public CreditBazaarIntegrator apply(Row row, String prefix) {
        CreditBazaarIntegrator entity = new CreditBazaarIntegrator();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setIntegratorUlidId(converter.fromRow(row, prefix + "_integrator_ulid_id", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        entity.setOrgId(converter.fromRow(row, prefix + "_org_id", String.class));
        entity.setCustomerName(converter.fromRow(row, prefix + "_customer_name", String.class));
        entity.setOrgName(converter.fromRow(row, prefix + "_org_name", String.class));
        entity.setGstId(converter.fromRow(row, prefix + "_gst_id", String.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", Long.class));
        return entity;
    }
}
