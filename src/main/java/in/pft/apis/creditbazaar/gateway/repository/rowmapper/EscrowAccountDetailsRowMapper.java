package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.EscrowAccountDetails;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link EscrowAccountDetails}, with proper type conversions.
 */
@Service
public class EscrowAccountDetailsRowMapper implements BiFunction<Row, String, EscrowAccountDetails> {

    private final ColumnConverter converter;

    public EscrowAccountDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EscrowAccountDetails} stored in the database.
     */
    @Override
    public EscrowAccountDetails apply(Row row, String prefix) {
        EscrowAccountDetails entity = new EscrowAccountDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEscrowDetailsId(converter.fromRow(row, prefix + "_escrow_details_id", Long.class));
        entity.setEscrowDetailsUlidId(converter.fromRow(row, prefix + "_escrow_details_ulid_id", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", Long.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        entity.setAccName(converter.fromRow(row, prefix + "_acc_name", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setVirtualAccNumber(converter.fromRow(row, prefix + "_virtual_acc_number", String.class));
        entity.setPoolingAccNumber(converter.fromRow(row, prefix + "_pooling_acc_number", Long.class));
        return entity;
    }
}
