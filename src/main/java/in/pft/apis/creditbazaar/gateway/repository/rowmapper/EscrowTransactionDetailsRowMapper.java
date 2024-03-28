package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link EscrowTransactionDetails}, with proper type conversions.
 */
@Service
public class EscrowTransactionDetailsRowMapper implements BiFunction<Row, String, EscrowTransactionDetails> {

    private final ColumnConverter converter;

    public EscrowTransactionDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link EscrowTransactionDetails} stored in the database.
     */
    @Override
    public EscrowTransactionDetails apply(Row row, String prefix) {
        EscrowTransactionDetails entity = new EscrowTransactionDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEscrowTrnxDetailsId(converter.fromRow(row, prefix + "_escrow_trnx_details_id", Long.class));
        entity.setEscrowTrnxDetailsUlidId(converter.fromRow(row, prefix + "_escrow_trnx_details_ulid_id", String.class));
        entity.setCustomerCode(converter.fromRow(row, prefix + "_customer_code", String.class));
        entity.setCustomerName(converter.fromRow(row, prefix + "_customer_name", String.class));
        entity.setProductCode(converter.fromRow(row, prefix + "_product_code", String.class));
        entity.setTransactionType(converter.fromRow(row, prefix + "_transaction_type", String.class));
        entity.setBatchAmt(converter.fromRow(row, prefix + "_batch_amt", String.class));
        entity.setBatchAmtCcd(converter.fromRow(row, prefix + "_batch_amt_ccd", String.class));
        entity.setCreditDate(converter.fromRow(row, prefix + "_credit_date", String.class));
        entity.setVaNumber(converter.fromRow(row, prefix + "_va_number", String.class));
        entity.setUtrNo(converter.fromRow(row, prefix + "_utr_no", String.class));
        entity.setCreditGenerationTime(converter.fromRow(row, prefix + "_credit_generation_time", String.class));
        entity.setRemitterName(converter.fromRow(row, prefix + "_remitter_name", String.class));
        entity.setRemitterAccountNumber(converter.fromRow(row, prefix + "_remitter_account_number", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setDisbursementId(converter.fromRow(row, prefix + "_disbursement_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", Long.class));
        return entity;
    }
}
