package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.AcceptedOffer;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AcceptedOffer}, with proper type conversions.
 */
@Service
public class AcceptedOfferRowMapper implements BiFunction<Row, String, AcceptedOffer> {

    private final ColumnConverter converter;

    public AcceptedOfferRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AcceptedOffer} stored in the database.
     */
    @Override
    public AcceptedOffer apply(Row row, String prefix) {
        AcceptedOffer entity = new AcceptedOffer();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAcceptedOfferUlidId(converter.fromRow(row, prefix + "_accepted_offer_ulid_id", String.class));
        entity.setAcceptedOfferRefNo(converter.fromRow(row, prefix + "_accepted_offer_ref_no", String.class));
        entity.setReqOffUlidId(converter.fromRow(row, prefix + "_req_off_ulid_id", String.class));
        entity.setValue(converter.fromRow(row, prefix + "_value", Long.class));
        entity.setReqAmount(converter.fromRow(row, prefix + "_req_amount", Long.class));
        entity.setMarginPtg(converter.fromRow(row, prefix + "_margin_ptg", Float.class));
        entity.setMarginValue(converter.fromRow(row, prefix + "_margin_value", Long.class));
        entity.setAmountAftMargin(converter.fromRow(row, prefix + "_amount_aft_margin", Long.class));
        entity.setInterestPtg(converter.fromRow(row, prefix + "_interest_ptg", Float.class));
        entity.setTerm(converter.fromRow(row, prefix + "_term", Long.class));
        entity.setInterestValue(converter.fromRow(row, prefix + "_interest_value", Long.class));
        entity.setNetAmount(converter.fromRow(row, prefix + "_net_amount", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setOfferDate(converter.fromRow(row, prefix + "_offer_date", LocalDate.class));
        entity.setOfferAcceptedDate(converter.fromRow(row, prefix + "_offer_accepted_date", LocalDate.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        entity.setAnchortraderId(converter.fromRow(row, prefix + "_anchortrader_id", Long.class));
        entity.setFinancepartnerId(converter.fromRow(row, prefix + "_financepartner_id", Long.class));
        return entity;
    }
}
