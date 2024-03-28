package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.FinanceRequestMapping;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link FinanceRequestMapping}, with proper type conversions.
 */
@Service
public class FinanceRequestMappingRowMapper implements BiFunction<Row, String, FinanceRequestMapping> {

    private final ColumnConverter converter;

    public FinanceRequestMappingRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FinanceRequestMapping} stored in the database.
     */
    @Override
    public FinanceRequestMapping apply(Row row, String prefix) {
        FinanceRequestMapping entity = new FinanceRequestMapping();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setFinanceRequestId(converter.fromRow(row, prefix + "_finance_request_id", Long.class));
        entity.setFinanceRequestMappingUlidId(converter.fromRow(row, prefix + "_finance_request_mapping_ulid_id", String.class));
        entity.setAnchorTraderId(converter.fromRow(row, prefix + "_anchor_trader_id", String.class));
        entity.setFinancePartnerId(converter.fromRow(row, prefix + "_finance_partner_id", String.class));
        entity.setAnchorTraderTenantId(converter.fromRow(row, prefix + "_anchor_trader_tenant_id", String.class));
        entity.setFinancePartnerTenantId(converter.fromRow(row, prefix + "_finance_partner_tenant_id", String.class));
        return entity;
    }
}
