package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.Disbursement;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Disbursement}, with proper type conversions.
 */
@Service
public class DisbursementRowMapper implements BiFunction<Row, String, Disbursement> {

    private final ColumnConverter converter;

    public DisbursementRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Disbursement} stored in the database.
     */
    @Override
    public Disbursement apply(Row row, String prefix) {
        Disbursement entity = new Disbursement();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setDbmtId(converter.fromRow(row, prefix + "_dbmt_id", String.class));
        entity.setDisbursementRefNo(converter.fromRow(row, prefix + "_disbursement_ref_no", String.class));
        entity.setAcceptedOfferId(converter.fromRow(row, prefix + "_accepted_offer_id", Long.class));
        entity.setOfferId(converter.fromRow(row, prefix + "_offer_id", Long.class));
        entity.setOfferAcceptedDate(converter.fromRow(row, prefix + "_offer_accepted_date", LocalDate.class));
        entity.setDbmtRequestId(converter.fromRow(row, prefix + "_dbmt_request_id", String.class));
        entity.setDbmtReqAmount(converter.fromRow(row, prefix + "_dbmt_req_amount", Long.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setDbmtRequestDate(converter.fromRow(row, prefix + "_dbmt_request_date", LocalDate.class));
        entity.setDbmtstatus(converter.fromRow(row, prefix + "_dbmtstatus", String.class));
        entity.setOfferStatus(converter.fromRow(row, prefix + "_offer_status", String.class));
        entity.setFtTrnxDetailsId(converter.fromRow(row, prefix + "_ft_trnx_details_id", String.class));
        entity.setCollectionTrnxDetailsId(converter.fromRow(row, prefix + "_collection_trnx_details_id", String.class));
        entity.setDocId(converter.fromRow(row, prefix + "_doc_id", Long.class));
        entity.setDbmtDateTime(converter.fromRow(row, prefix + "_dbmt_date_time", String.class));
        entity.setDbmtAmount(converter.fromRow(row, prefix + "_dbmt_amount", Long.class));
        entity.setFinanceRequestId(converter.fromRow(row, prefix + "_finance_request_id", String.class));
        entity.setAmountToBeDisbursed(converter.fromRow(row, prefix + "_amount_to_be_disbursed", String.class));
        entity.setDestinationAccountName(converter.fromRow(row, prefix + "_destination_account_name", String.class));
        entity.setDestinationAccountNumber(converter.fromRow(row, prefix + "_destination_account_number", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setActionByDate(converter.fromRow(row, prefix + "_action_by_date", String.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        entity.setFinancepartnerId(converter.fromRow(row, prefix + "_financepartner_id", Long.class));
        return entity;
    }
}
