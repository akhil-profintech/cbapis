package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.UserDtls;
import in.pft.apis.creditbazaar.gateway.domain.enumeration.Persona;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link UserDtls}, with proper type conversions.
 */
@Service
public class UserDtlsRowMapper implements BiFunction<Row, String, UserDtls> {

    private final ColumnConverter converter;

    public UserDtlsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link UserDtls} stored in the database.
     */
    @Override
    public UserDtls apply(Row row, String prefix) {
        UserDtls entity = new UserDtls();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setUserUlidId(converter.fromRow(row, prefix + "_user_ulid_id", String.class));
        entity.setUserName(converter.fromRow(row, prefix + "_user_name", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        entity.setIsAnchorTraderEnabled(converter.fromRow(row, prefix + "_is_anchor_trader_enabled", Boolean.class));
        entity.setAnchorTraderId(converter.fromRow(row, prefix + "_anchor_trader_id", String.class));
        entity.setIsTradePartnerEnabled(converter.fromRow(row, prefix + "_is_trade_partner_enabled", Boolean.class));
        entity.setTradePartnerId(converter.fromRow(row, prefix + "_trade_partner_id", String.class));
        entity.setIsFinancePartnerEnabled(converter.fromRow(row, prefix + "_is_finance_partner_enabled", Boolean.class));
        entity.setFinancePartnerId(converter.fromRow(row, prefix + "_finance_partner_id", String.class));
        entity.setDefaultPersona(converter.fromRow(row, prefix + "_default_persona", Persona.class));
        return entity;
    }
}
