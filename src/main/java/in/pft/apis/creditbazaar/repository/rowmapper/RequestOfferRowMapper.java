package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.RequestOffer;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link RequestOffer}, with proper type conversions.
 */
@Service
public class RequestOfferRowMapper implements BiFunction<Row, String, RequestOffer> {

    private final ColumnConverter converter;

    public RequestOfferRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link RequestOffer} stored in the database.
     */
    @Override
    public RequestOffer apply(Row row, String prefix) {
        RequestOffer entity = new RequestOffer();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setReqOffId(converter.fromRow(row, prefix + "_req_off_id", String.class));
        entity.setRequestOfferRefNo(converter.fromRow(row, prefix + "_request_offer_ref_no", String.class));
        entity.setOfferValue(converter.fromRow(row, prefix + "_offer_value", Long.class));
        entity.setRequestAmt(converter.fromRow(row, prefix + "_request_amt", Long.class));
        entity.setTradeValue(converter.fromRow(row, prefix + "_trade_value", Long.class));
        entity.setMarginPtg(converter.fromRow(row, prefix + "_margin_ptg", Float.class));
        entity.setMarginValue(converter.fromRow(row, prefix + "_margin_value", Long.class));
        entity.setAmountAftMargin(converter.fromRow(row, prefix + "_amount_aft_margin", Long.class));
        entity.setInterestPtg(converter.fromRow(row, prefix + "_interest_ptg", Float.class));
        entity.setTerm(converter.fromRow(row, prefix + "_term", Long.class));
        entity.setInterestValue(converter.fromRow(row, prefix + "_interest_value", Float.class));
        entity.setNetAmount(converter.fromRow(row, prefix + "_net_amount", Long.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setFinanceRequestDate(converter.fromRow(row, prefix + "_finance_request_date", LocalDate.class));
        entity.setAnchorTraderName(converter.fromRow(row, prefix + "_anchor_trader_name", String.class));
        entity.setTradePartnerName(converter.fromRow(row, prefix + "_trade_partner_name", String.class));
        entity.setAnchorTraderGst(converter.fromRow(row, prefix + "_anchor_trader_gst", String.class));
        entity.setTradePartnerGst(converter.fromRow(row, prefix + "_trade_partner_gst", String.class));
        entity.setSellerName(converter.fromRow(row, prefix + "_seller_name", String.class));
        entity.setBuyerName(converter.fromRow(row, prefix + "_buyer_name", String.class));
        entity.setAnchorTraderGstComplianceScore(converter.fromRow(row, prefix + "_anchor_trader_gst_compliance_score", String.class));
        entity.setAnchorTraderErpPeerScore(converter.fromRow(row, prefix + "_anchor_trader_erp_peer_score", String.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        entity.setCbcreprocessId(converter.fromRow(row, prefix + "_cbcreprocess_id", Long.class));
        return entity;
    }
}
