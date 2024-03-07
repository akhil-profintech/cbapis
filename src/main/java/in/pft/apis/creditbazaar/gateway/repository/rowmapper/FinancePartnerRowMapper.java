package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FinancePartner}, with proper type conversions.
 */
@Service
public class FinancePartnerRowMapper implements BiFunction<Row, String, FinancePartner> {

    private final ColumnConverter converter;

    public FinancePartnerRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FinancePartner} stored in the database.
     */
    @Override
    public FinancePartner apply(Row row, String prefix) {
        FinancePartner entity = new FinancePartner();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setFpId(converter.fromRow(row, prefix + "_fp_id", Long.class));
        entity.setFpUlidId(converter.fromRow(row, prefix + "_fp_ulid_id", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        entity.setOrgId(converter.fromRow(row, prefix + "_org_id", String.class));
        entity.setCustomerName(converter.fromRow(row, prefix + "_customer_name", String.class));
        entity.setOrgName(converter.fromRow(row, prefix + "_org_name", String.class));
        entity.setGstId(converter.fromRow(row, prefix + "_gst_id", String.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", Long.class));
        entity.setTosDocument(converter.fromRow(row, prefix + "_tos_document", String.class));
        return entity;
    }
}
