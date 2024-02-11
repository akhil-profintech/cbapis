package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.TradeChannel;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TradeChannel}, with proper type conversions.
 */
@Service
public class TradeChannelRowMapper implements BiFunction<Row, String, TradeChannel> {

    private final ColumnConverter converter;

    public TradeChannelRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TradeChannel} stored in the database.
     */
    @Override
    public TradeChannel apply(Row row, String prefix) {
        TradeChannel entity = new TradeChannel();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTradeChannelId(converter.fromRow(row, prefix + "_trade_channel_id", String.class));
        entity.setAnchorTraderId(converter.fromRow(row, prefix + "_anchor_trader_id", String.class));
        entity.setTradePartnerId(converter.fromRow(row, prefix + "_trade_partner_id", String.class));
        entity.setFinancePartnerId(converter.fromRow(row, prefix + "_finance_partner_id", String.class));
        entity.setAnchorTraderTenantId(converter.fromRow(row, prefix + "_anchor_trader_tenant_id", String.class));
        entity.setTradePartnerTenantId(converter.fromRow(row, prefix + "_trade_partner_tenant_id", String.class));
        entity.setFinancePartnerTenantId(converter.fromRow(row, prefix + "_finance_partner_tenant_id", String.class));
        return entity;
    }
}
