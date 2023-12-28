package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.FTTransactionDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FTTransactionDetails}, with proper type conversions.
 */
@Service
public class FTTransactionDetailsRowMapper implements BiFunction<Row, String, FTTransactionDetails> {

    private final ColumnConverter converter;

    public FTTransactionDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FTTransactionDetails} stored in the database.
     */
    @Override
    public FTTransactionDetails apply(Row row, String prefix) {
        FTTransactionDetails entity = new FTTransactionDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTrnxDetailsId(converter.fromRow(row, prefix + "_trnx_details_id", Long.class));
        entity.setTransactionID(converter.fromRow(row, prefix + "_transaction_id", Long.class));
        entity.setDebitAccountNumber(converter.fromRow(row, prefix + "_debit_account_number", String.class));
        entity.setCreditAccountNumber(converter.fromRow(row, prefix + "_credit_account_number", String.class));
        entity.setRemitterName(converter.fromRow(row, prefix + "_remitter_name", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", Long.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setTransactionType(converter.fromRow(row, prefix + "_transaction_type", String.class));
        entity.setPaymentDescription(converter.fromRow(row, prefix + "_payment_description", String.class));
        entity.setBeneficiaryIFSC(converter.fromRow(row, prefix + "_beneficiary_ifsc", String.class));
        entity.setBeneficiaryName(converter.fromRow(row, prefix + "_beneficiary_name", String.class));
        entity.setBeneficiaryAddress(converter.fromRow(row, prefix + "_beneficiary_address", String.class));
        entity.setEmailId(converter.fromRow(row, prefix + "_email_id", String.class));
        entity.setMobileNo(converter.fromRow(row, prefix + "_mobile_no", Long.class));
        entity.setTransactionRefNo(converter.fromRow(row, prefix + "_transaction_ref_no", String.class));
        entity.setTrnxResourceDataStatus(converter.fromRow(row, prefix + "_trnx_resource_data_status", String.class));
        entity.setTrnxMetaDataStatus(converter.fromRow(row, prefix + "_trnx_meta_data_status", String.class));
        entity.setTransactionDateTime(converter.fromRow(row, prefix + "_transaction_date_time", String.class));
        entity.setDisbursementId(converter.fromRow(row, prefix + "_disbursement_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", Long.class));
        entity.setParticipantsettlementId(converter.fromRow(row, prefix + "_participantsettlement_id", Long.class));
        return entity;
    }
}
