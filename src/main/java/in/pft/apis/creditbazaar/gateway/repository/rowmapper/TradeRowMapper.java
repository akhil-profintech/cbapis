package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.Trade;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link Trade}, with proper type conversions.
 */
@Service
public class TradeRowMapper implements BiFunction<Row, String, Trade> {

    private final ColumnConverter converter;

    public TradeRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Trade} stored in the database.
     */
    @Override
    public Trade apply(Row row, String prefix) {
        Trade entity = new Trade();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTradeUlidId(converter.fromRow(row, prefix + "_trade_ulid_id", String.class));
        entity.setTradeRefNo(converter.fromRow(row, prefix + "_trade_ref_no", String.class));
        entity.setSellerGstId(converter.fromRow(row, prefix + "_seller_gst_id", String.class));
        entity.setBuyerGstId(converter.fromRow(row, prefix + "_buyer_gst_id", String.class));
        entity.setTradeAmount(converter.fromRow(row, prefix + "_trade_amount", String.class));
        entity.setInvoiceDate(converter.fromRow(row, prefix + "_invoice_date", LocalDate.class));
        entity.setInvoiceNumber(converter.fromRow(row, prefix + "_invoice_number", String.class));
        entity.setTradeDocType(converter.fromRow(row, prefix + "_trade_doc_type", String.class));
        entity.setTradeDocSource(converter.fromRow(row, prefix + "_trade_doc_source", String.class));
        entity.setTradeDocCredibility(converter.fromRow(row, prefix + "_trade_doc_credibility", String.class));
        entity.setTradeMilestoneStatus(converter.fromRow(row, prefix + "_trade_milestone_status", String.class));
        entity.setTradeAdvancePayment(converter.fromRow(row, prefix + "_trade_advance_payment", String.class));
        entity.setAnchorTraderName(converter.fromRow(row, prefix + "_anchor_trader_name", String.class));
        entity.setTradePartnerName(converter.fromRow(row, prefix + "_trade_partner_name", String.class));
        entity.setInvoiceTerm(converter.fromRow(row, prefix + "_invoice_term", Long.class));
        entity.setAcceptanceFromTradePartner(converter.fromRow(row, prefix + "_acceptance_from_trade_partner", String.class));
        entity.setReasonForFinance(converter.fromRow(row, prefix + "_reason_for_finance", String.class));
        entity.setTradePartnerSector(converter.fromRow(row, prefix + "_trade_partner_sector", String.class));
        entity.setTradePartnerLocation(converter.fromRow(row, prefix + "_trade_partner_location", String.class));
        entity.setTradePartnerGstComplianceScore(converter.fromRow(row, prefix + "_trade_partner_gst_compliance_score", String.class));
        entity.setFinancerequestId(converter.fromRow(row, prefix + "_financerequest_id", Long.class));
        entity.setAnchortraderId(converter.fromRow(row, prefix + "_anchortrader_id", Long.class));
        entity.setTradepartnerId(converter.fromRow(row, prefix + "_tradepartner_id", Long.class));
        return entity;
    }
}
