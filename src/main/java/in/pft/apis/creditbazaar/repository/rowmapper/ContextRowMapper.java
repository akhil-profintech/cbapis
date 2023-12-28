package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.Context;
import io.r2dbc.spi.Row;
import java.time.Instant;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link Context}, with proper type conversions.
 */
@Service
public class ContextRowMapper implements BiFunction<Row, String, Context> {

    private final ColumnConverter converter;

    public ContextRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Context} stored in the database.
     */
    @Override
    public Context apply(Row row, String prefix) {
        Context entity = new Context();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", Long.class));
        entity.setTransactionDate(converter.fromRow(row, prefix + "_transaction_date", Instant.class));
        entity.setClientId(converter.fromRow(row, prefix + "_client_id", Long.class));
        entity.setCpCode(converter.fromRow(row, prefix + "_cp_code", String.class));
        entity.setActionId(converter.fromRow(row, prefix + "_action_id", Long.class));
        return entity;
    }
}
