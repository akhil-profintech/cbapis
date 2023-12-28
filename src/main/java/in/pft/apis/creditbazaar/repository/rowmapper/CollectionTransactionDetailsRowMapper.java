package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.CollectionTransactionDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link CollectionTransactionDetails}, with proper type conversions.
 */
@Service
public class CollectionTransactionDetailsRowMapper implements BiFunction<Row, String, CollectionTransactionDetails> {

    private final ColumnConverter converter;

    public CollectionTransactionDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CollectionTransactionDetails} stored in the database.
     */
    @Override
    public CollectionTransactionDetails apply(Row row, String prefix) {
        CollectionTransactionDetails entity = new CollectionTransactionDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTrnxDetailsid(converter.fromRow(row, prefix + "_trnx_detailsid", Long.class));
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
