package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.ClientCodes;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ClientCodes}, with proper type conversions.
 */
@Service
public class ClientCodesRowMapper implements BiFunction<Row, String, ClientCodes> {

    private final ColumnConverter converter;

    public ClientCodesRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ClientCodes} stored in the database.
     */
    @Override
    public ClientCodes apply(Row row, String prefix) {
        ClientCodes entity = new ClientCodes();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setClientCode(converter.fromRow(row, prefix + "_client_code", Long.class));
        entity.setClientCharsCode(converter.fromRow(row, prefix + "_client_chars_code", String.class));
        entity.setClientName(converter.fromRow(row, prefix + "_client_name", String.class));
        return entity;
    }
}
