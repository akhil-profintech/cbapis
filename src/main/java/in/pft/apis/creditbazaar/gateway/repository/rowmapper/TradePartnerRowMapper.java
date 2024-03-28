package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.TradePartner;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TradePartner}, with proper type conversions.
 */
@Service
public class TradePartnerRowMapper implements BiFunction<Row, String, TradePartner> {

    private final ColumnConverter converter;

    public TradePartnerRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TradePartner} stored in the database.
     */
    @Override
    public TradePartner apply(Row row, String prefix) {
        TradePartner entity = new TradePartner();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTpId(converter.fromRow(row, prefix + "_tp_id", Long.class));
        entity.setTpUlidId(converter.fromRow(row, prefix + "_tp_ulid_id", String.class));
        entity.setOrgId(converter.fromRow(row, prefix + "_org_id", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        entity.setCustomerName(converter.fromRow(row, prefix + "_customer_name", String.class));
        entity.setOrgName(converter.fromRow(row, prefix + "_org_name", String.class));
        entity.setGstId(converter.fromRow(row, prefix + "_gst_id", String.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", Long.class));
        entity.setTradePartnerName(converter.fromRow(row, prefix + "_trade_partner_name", String.class));
        entity.setLocation(converter.fromRow(row, prefix + "_location", String.class));
        entity.setTradePartnerGST(converter.fromRow(row, prefix + "_trade_partner_gst", String.class));
        entity.setTradePartnerSector(converter.fromRow(row, prefix + "_trade_partner_sector", String.class));
        entity.setAcceptanceFromTradePartner(converter.fromRow(row, prefix + "_acceptance_from_trade_partner", String.class));
        entity.setTosDocument(converter.fromRow(row, prefix + "_tos_document", String.class));
        return entity;
    }
}
