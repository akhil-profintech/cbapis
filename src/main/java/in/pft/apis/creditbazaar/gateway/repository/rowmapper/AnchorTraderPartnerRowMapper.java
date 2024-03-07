package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link AnchorTraderPartner}, with proper type conversions.
 */
@Service
public class AnchorTraderPartnerRowMapper implements BiFunction<Row, String, AnchorTraderPartner> {

    private final ColumnConverter converter;

    public AnchorTraderPartnerRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AnchorTraderPartner} stored in the database.
     */
    @Override
    public AnchorTraderPartner apply(Row row, String prefix) {
        AnchorTraderPartner entity = new AnchorTraderPartner();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAtpartnerId(converter.fromRow(row, prefix + "_atpartner_id", Long.class));
        entity.setAtpartnerUlidId(converter.fromRow(row, prefix + "_atpartner_ulid_id", String.class));
        entity.setPan(converter.fromRow(row, prefix + "_pan", String.class));
        entity.setPanStatus(converter.fromRow(row, prefix + "_pan_status", String.class));
        entity.setName(converter.fromRow(row, prefix + "_name", String.class));
        entity.setAadhar(converter.fromRow(row, prefix + "_aadhar", String.class));
        entity.setAadharOtp(converter.fromRow(row, prefix + "_aadhar_otp", String.class));
        entity.setAadharStatus(converter.fromRow(row, prefix + "_aadhar_status", String.class));
        entity.setAadharName(converter.fromRow(row, prefix + "_aadhar_name", String.class));
        entity.setAadharAddress(converter.fromRow(row, prefix + "_aadhar_address", String.class));
        entity.setAnchortraderId(converter.fromRow(row, prefix + "_anchortrader_id", Long.class));
        return entity;
    }
}
