package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.ProspectRequest;
import io.r2dbc.spi.Row;
import java.time.LocalDate;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ProspectRequest}, with proper type conversions.
 */
@Service
public class ProspectRequestRowMapper implements BiFunction<Row, String, ProspectRequest> {

    private final ColumnConverter converter;

    public ProspectRequestRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ProspectRequest} stored in the database.
     */
    @Override
    public ProspectRequest apply(Row row, String prefix) {
        ProspectRequest entity = new ProspectRequest();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setProspectRequestId(converter.fromRow(row, prefix + "_prospect_request_id", Long.class));
        entity.setProspectRequestUlidId(converter.fromRow(row, prefix + "_prospect_request_ulid_id", String.class));
        entity.setAnchorTraderId(converter.fromRow(row, prefix + "_anchor_trader_id", Long.class));
        entity.setRequestAmount(converter.fromRow(row, prefix + "_request_amount", String.class));
        entity.setProspectRequestDate(converter.fromRow(row, prefix + "_prospect_request_date", LocalDate.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        return entity;
    }
}
