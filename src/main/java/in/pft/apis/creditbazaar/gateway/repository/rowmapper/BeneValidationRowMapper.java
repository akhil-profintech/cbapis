package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.BeneValidation;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link BeneValidation}, with proper type conversions.
 */
@Service
public class BeneValidationRowMapper implements BiFunction<Row, String, BeneValidation> {

    private final ColumnConverter converter;

    public BeneValidationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link BeneValidation} stored in the database.
     */
    @Override
    public BeneValidation apply(Row row, String prefix) {
        BeneValidation entity = new BeneValidation();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setBenevalidationId(converter.fromRow(row, prefix + "_benevalidation_id", String.class));
        entity.setFinReqId(converter.fromRow(row, prefix + "_fin_req_id", String.class));
        entity.setSubGrpId(converter.fromRow(row, prefix + "_sub_grp_id", String.class));
        entity.setRemitterName(converter.fromRow(row, prefix + "_remitter_name", String.class));
        entity.setRemitterMobileNumber(converter.fromRow(row, prefix + "_remitter_mobile_number", String.class));
        entity.setDebtorAccountId(converter.fromRow(row, prefix + "_debtor_account_id", String.class));
        entity.setAccountType(converter.fromRow(row, prefix + "_account_type", String.class));
        entity.setCreditorAccountId(converter.fromRow(row, prefix + "_creditor_account_id", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setPaymentDescription(converter.fromRow(row, prefix + "_payment_description", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setMerchantCode(converter.fromRow(row, prefix + "_merchant_code", String.class));
        entity.setIdentifier(converter.fromRow(row, prefix + "_identifier", String.class));
        entity.setRequestDateTime(converter.fromRow(row, prefix + "_request_date_time", String.class));
        entity.setMetaDataStatus(converter.fromRow(row, prefix + "_meta_data_status", String.class));
        entity.setMetaDataMessage(converter.fromRow(row, prefix + "_meta_data_message", String.class));
        entity.setMetaDataVersion(converter.fromRow(row, prefix + "_meta_data_version", String.class));
        entity.setMetaDataTime(converter.fromRow(row, prefix + "_meta_data_time", String.class));
        entity.setResourceDataCreditorAccountId(converter.fromRow(row, prefix + "_resource_data_creditor_account_id", String.class));
        entity.setResourceDataCreditorName(converter.fromRow(row, prefix + "_resource_data_creditor_name", String.class));
        entity.setResourceDataTransactionReferenceNumber(
            converter.fromRow(row, prefix + "_resource_data_transaction_reference_number", String.class)
        );
        entity.setResourceDataTransactionId(converter.fromRow(row, prefix + "_resource_data_transaction_id", String.class));
        entity.setResourceDataResponseCode(converter.fromRow(row, prefix + "_resource_data_response_code", String.class));
        entity.setResourceDataTransactionTime(converter.fromRow(row, prefix + "_resource_data_transaction_time", String.class));
        entity.setResourceDataIdentifier(converter.fromRow(row, prefix + "_resource_data_identifier", String.class));
        entity.setResponseDateTime(converter.fromRow(row, prefix + "_response_date_time", String.class));
        entity.setLastupdatedDateTime(converter.fromRow(row, prefix + "_lastupdated_date_time", String.class));
        entity.setLastUpdatedBy(converter.fromRow(row, prefix + "_last_updated_by", String.class));
        entity.setTradeEntityId(converter.fromRow(row, prefix + "_trade_entity_id", Long.class));
        return entity;
    }
}
