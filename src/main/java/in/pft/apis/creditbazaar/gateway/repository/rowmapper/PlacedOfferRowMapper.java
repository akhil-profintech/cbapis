package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.PlacedOffer;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link PlacedOffer}, with proper type conversions.
 */
@Service
public class PlacedOfferRowMapper implements BiFunction<Row, String, PlacedOffer> {

    private final ColumnConverter converter;

    public PlacedOfferRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PlacedOffer} stored in the database.
     */
    @Override
    public PlacedOffer apply(Row row, String prefix) {
        PlacedOffer entity = new PlacedOffer();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setPlacedOfferUlidId(converter.fromRow(row, prefix + "_placed_offer_ulid_id", String.class));
        entity.setPlacedOfferRefNo(converter.fromRow(row, prefix + "_placed_offer_ref_no", String.class));
        entity.setReqOffUlidId(converter.fromRow(row, prefix + "_req_off_ulid_id", String.class));
        entity.setRequestOfferRefNo(converter.fromRow(row, prefix + "_request_offer_ref_no", String.class));
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
        entity.setPlacedOfferDate(converter.fromRow(row, prefix + "_placed_offer_date", LocalDate.class));
        entity.setAnchorTrader(converter.fromRow(row, prefix + "_anchor_trader", String.class));
        entity.setTradePartner(converter.fromRow(row, prefix + "_trade_partner", String.class));
        entity.setDisbursalAmount(converter.fromRow(row, prefix + "_disbursal_amount", String.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        entity.setFinancepartnerId(converter.fromRow(row, prefix + "_financepartner_id", Long.class));
        return entity;
    }
}
