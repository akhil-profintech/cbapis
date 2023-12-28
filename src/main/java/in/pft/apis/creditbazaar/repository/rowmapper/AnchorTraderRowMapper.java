package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.AnchorTrader;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnchorTrader}, with proper type conversions.
 */
@Service
public class AnchorTraderRowMapper implements BiFunction<Row, String, AnchorTrader> {

    private final ColumnConverter converter;

    public AnchorTraderRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnchorTrader} stored in the database.
     */
    @Override
    public AnchorTrader apply(Row row, String prefix) {
        AnchorTrader entity = new AnchorTrader();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", String.class));
        entity.setAtId(converter.fromRow(row, prefix + "_at_id", String.class));
        entity.setOrgId(converter.fromRow(row, prefix + "_org_id", String.class));
        entity.setCustomerName(converter.fromRow(row, prefix + "_customer_name", String.class));
        entity.setOrgName(converter.fromRow(row, prefix + "_org_name", String.class));
        entity.setGstId(converter.fromRow(row, prefix + "_gst_id", String.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", Long.class));
        entity.setAnchorTraderName(converter.fromRow(row, prefix + "_anchor_trader_name", String.class));
        entity.setLocation(converter.fromRow(row, prefix + "_location", String.class));
        entity.setAnchorTraderGST(converter.fromRow(row, prefix + "_anchor_trader_gst", String.class));
        entity.setAnchorTraderSector(converter.fromRow(row, prefix + "_anchor_trader_sector", String.class));
        entity.setAnchorTraderPAN(converter.fromRow(row, prefix + "_anchor_trader_pan", String.class));
        entity.setKycCompleted(converter.fromRow(row, prefix + "_kyc_completed", String.class));
        entity.setBankDetails(converter.fromRow(row, prefix + "_bank_details", String.class));
        return entity;
    }
}
