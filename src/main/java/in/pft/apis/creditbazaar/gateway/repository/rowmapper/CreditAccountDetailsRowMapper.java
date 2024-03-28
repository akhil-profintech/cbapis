package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.CreditAccountDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CreditAccountDetails}, with proper type conversions.
 */
@Service
public class CreditAccountDetailsRowMapper implements BiFunction<Row, String, CreditAccountDetails> {

    private final ColumnConverter converter;

    public CreditAccountDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CreditAccountDetails} stored in the database.
     */
    @Override
    public CreditAccountDetails apply(Row row, String prefix) {
        CreditAccountDetails entity = new CreditAccountDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setCreditAccDetailsId(converter.fromRow(row, prefix + "_credit_acc_details_id", Long.class));
        entity.setCreditAccountDetailsUlidId(converter.fromRow(row, prefix + "_credit_account_details_ulid_id", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", Long.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        entity.setAccName(converter.fromRow(row, prefix + "_acc_name", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setAccNumber(converter.fromRow(row, prefix + "_acc_number", Long.class));
        entity.setDestinationAccountName(converter.fromRow(row, prefix + "_destination_account_name", String.class));
        entity.setDestinationAccountNumber(converter.fromRow(row, prefix + "_destination_account_number", String.class));
        entity.setDisbursementId(converter.fromRow(row, prefix + "_disbursement_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", Long.class));
        return entity;
    }
}
