package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetails;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FundsTransferTransactionDetails}, with proper type conversions.
 */
@Service
public class FundsTransferTransactionDetailsRowMapper implements BiFunction<Row, String, FundsTransferTransactionDetails> {

    private final ColumnConverter converter;

    public FundsTransferTransactionDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FundsTransferTransactionDetails} stored in the database.
     */
    @Override
    public FundsTransferTransactionDetails apply(Row row, String prefix) {
        FundsTransferTransactionDetails entity = new FundsTransferTransactionDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setFtTrnxDetailsId(converter.fromRow(row, prefix + "_ft_trnx_details_id", Long.class));
        entity.setFtTrnxDetailsUlid(converter.fromRow(row, prefix + "_ft_trnx_details_ulid", String.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", Long.class));
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
        entity.setParticipantsettlementId(converter.fromRow(row, prefix + "_participantsettlement_id", Long.class));
        entity.setDisbursementId(converter.fromRow(row, prefix + "_disbursement_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", Long.class));
        return entity;
    }
}
