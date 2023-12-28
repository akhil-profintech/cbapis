package in.pft.apis.creditbazaar.repository.rowmapper;

import in.pft.apis.creditbazaar.domain.TradeEntity;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link TradeEntity}, with proper type conversions.
 */
@Service
public class TradeEntityRowMapper implements BiFunction<Row, String, TradeEntity> {

    private final ColumnConverter converter;

    public TradeEntityRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link TradeEntity} stored in the database.
     */
    @Override
    public TradeEntity apply(Row row, String prefix) {
        TradeEntity entity = new TradeEntity();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setEntityId(converter.fromRow(row, prefix + "_entity_id", Long.class));
        entity.setEntityName(converter.fromRow(row, prefix + "_entity_name", String.class));
        entity.setEntityDesc(converter.fromRow(row, prefix + "_entity_desc", String.class));
        entity.setEntityGST(converter.fromRow(row, prefix + "_entity_gst", String.class));
        return entity;
    }
}
