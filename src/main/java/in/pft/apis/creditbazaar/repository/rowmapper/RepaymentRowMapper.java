package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.Repayment;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Repayment}, with proper type conversions.
 */
@Service
public class RepaymentRowMapper implements BiFunction<Row, String, Repayment> {

    private final ColumnConverter converter;

    public RepaymentRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Repayment} stored in the database.
     */
    @Override
    public Repayment apply(Row row, String prefix) {
        Repayment entity = new Repayment();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setRepaymentId(converter.fromRow(row, prefix + "_repayment_id", String.class));
        entity.setRepaymentRefNo(converter.fromRow(row, prefix + "_repayment_ref_no", String.class));
        entity.setAcceptedOfferId(converter.fromRow(row, prefix + "_accepted_offer_id", Long.class));
        entity.setOfferId(converter.fromRow(row, prefix + "_offer_id", Long.class));
        entity.setOfferAcceptedDate(converter.fromRow(row, prefix + "_offer_accepted_date", LocalDate.class));
        entity.setDbmtRequestId(converter.fromRow(row, prefix + "_dbmt_request_id", String.class));
        entity.setDbmtstatus(converter.fromRow(row, prefix + "_dbmtstatus", String.class));
        entity.setDbmtDateTime(converter.fromRow(row, prefix + "_dbmt_date_time", String.class));
        entity.setDbmtId(converter.fromRow(row, prefix + "_dbmt_id", String.class));
        entity.setDbmtAmount(converter.fromRow(row, prefix + "_dbmt_amount", Long.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setRepaymentStatus(converter.fromRow(row, prefix + "_repayment_status", String.class));
        entity.setRepaymentDate(converter.fromRow(row, prefix + "_repayment_date", LocalDate.class));
        entity.setRepaymentAmount(converter.fromRow(row, prefix + "_repayment_amount", Long.class));
        entity.setFtTrnxDetailsId(converter.fromRow(row, prefix + "_ft_trnx_details_id", String.class));
        entity.setCollectionTrnxDetailsId(converter.fromRow(row, prefix + "_collection_trnx_details_id", String.class));
        entity.setDocId(converter.fromRow(row, prefix + "_doc_id", Long.class));
        entity.setFinanceRequestId(converter.fromRow(row, prefix + "_finance_request_id", String.class));
        entity.setRepaymentDueDate(converter.fromRow(row, prefix + "_repayment_due_date", LocalDate.class));
        entity.setTotalRepaymentAmount(converter.fromRow(row, prefix + "_total_repayment_amount", String.class));
        entity.setAmountRepayed(converter.fromRow(row, prefix + "_amount_repayed", String.class));
        entity.setAmountToBePaid(converter.fromRow(row, prefix + "_amount_to_be_paid", String.class));
        entity.setSourceAccountName(converter.fromRow(row, prefix + "_source_account_name", String.class));
        entity.setSourceAccountNumber(converter.fromRow(row, prefix + "_source_account_number", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setReferenceNumber(converter.fromRow(row, prefix + "_reference_number", String.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        return entity;
    }
}
