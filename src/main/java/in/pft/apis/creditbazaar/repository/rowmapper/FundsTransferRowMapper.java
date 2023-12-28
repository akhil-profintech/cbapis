package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.FundsTransfer;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link FundsTransfer}, with proper type conversions.
 */
@Service
public class FundsTransferRowMapper implements BiFunction<Row, String, FundsTransfer> {

    private final ColumnConverter converter;

    public FundsTransferRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link FundsTransfer} stored in the database.
     */
    @Override
    public FundsTransfer apply(Row row, String prefix) {
        FundsTransfer entity = new FundsTransfer();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setFundsTransferId(converter.fromRow(row, prefix + "_funds_transfer_id", String.class));
        entity.setFundsTransferRefNo(converter.fromRow(row, prefix + "_funds_transfer_ref_no", String.class));
        entity.setFinReqId(converter.fromRow(row, prefix + "_fin_req_id", String.class));
        entity.setSubGrpId(converter.fromRow(row, prefix + "_sub_grp_id", String.class));
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
        entity.setMessageType(converter.fromRow(row, prefix + "_message_type", String.class));
        entity.setTransactionDateTime(converter.fromRow(row, prefix + "_transaction_date_time", String.class));
        entity.setTransactionRefNo(converter.fromRow(row, prefix + "_transaction_ref_no", String.class));
        entity.setTrnxMetaDataStatus(converter.fromRow(row, prefix + "_trnx_meta_data_status", String.class));
        entity.setTrnxMetaDataMessage(converter.fromRow(row, prefix + "_trnx_meta_data_message", String.class));
        entity.setTrnxMetaDataVersion(converter.fromRow(row, prefix + "_trnx_meta_data_version", String.class));
        entity.setTrnxMetaDataTime(converter.fromRow(row, prefix + "_trnx_meta_data_time", String.class));
        entity.setTrnxResourceDataBeneficiaryName(converter.fromRow(row, prefix + "_trnx_resource_data_beneficiary_name", String.class));
        entity.setTrnxResourceDataTransactionId(converter.fromRow(row, prefix + "_trnx_resource_data_transaction_id", String.class));
        entity.setTrnxResourceDataStatus(converter.fromRow(row, prefix + "_trnx_resource_data_status", String.class));
        entity.setFundsTransferStatus(converter.fromRow(row, prefix + "_funds_transfer_status", String.class));
        entity.setLastupdatedDateTime(converter.fromRow(row, prefix + "_lastupdated_date_time", String.class));
        entity.setLastUpdatedBy(converter.fromRow(row, prefix + "_last_updated_by", String.class));
        entity.setTradeEntityId(converter.fromRow(row, prefix + "_trade_entity_id", Long.class));
        return entity;
    }
}
