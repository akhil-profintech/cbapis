package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.EscrowAccountDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

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
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", Long.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        entity.setAccName(converter.fromRow(row, prefix + "_acc_name", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setVirtualAccNumber(converter.fromRow(row, prefix + "_virtual_acc_number", String.class));
        entity.setPoolingAccNumber(converter.fromRow(row, prefix + "_pooling_acc_number", Long.class));
        entity.setDisbursementId(converter.fromRow(row, prefix + "_disbursement_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", Long.class));
        return entity;
    }
}
