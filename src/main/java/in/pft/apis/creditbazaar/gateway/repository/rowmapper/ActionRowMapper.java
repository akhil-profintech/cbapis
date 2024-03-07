package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.Action;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Action}, with proper type conversions.
 */
@Service
public class ActionRowMapper implements BiFunction<Row, String, Action> {

    private final ColumnConverter converter;

    public ActionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Action} stored in the database.
     */
    @Override
    public Action apply(Row row, String prefix) {
        Action entity = new Action();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setActionId(converter.fromRow(row, prefix + "_action_id", String.class));
        entity.setActionCode(converter.fromRow(row, prefix + "_action_code", String.class));
        entity.setActionDescription(converter.fromRow(row, prefix + "_action_description", String.class));
        entity.setActionVal(converter.fromRow(row, prefix + "_action_val", String.class));
        entity.setCpCode(converter.fromRow(row, prefix + "_cp_code", String.class));
        return entity;
    }
}
