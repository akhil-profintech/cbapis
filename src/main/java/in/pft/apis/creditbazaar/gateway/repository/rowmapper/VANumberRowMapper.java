package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.VANumber;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

/**
 * Converter between {@link Row} to {@link VANumber}, with proper type conversions.
 */
@Service
public class VANumberRowMapper implements BiFunction<Row, String, VANumber> {

    private final ColumnConverter converter;

    public VANumberRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link VANumber} stored in the database.
     */
    @Override
    public VANumber apply(Row row, String prefix) {
        VANumber entity = new VANumber();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setVaId(converter.fromRow(row, prefix + "_va_id", Long.class));
        entity.setPoolingAccNumber(converter.fromRow(row, prefix + "_pooling_acc_number", String.class));
        entity.setVirtualAccNumber(converter.fromRow(row, prefix + "_virtual_acc_number", String.class));
        entity.setVaStatus(converter.fromRow(row, prefix + "_va_status", String.class));
        entity.setFinanceRequestId(converter.fromRow(row, prefix + "_finance_request_id", Long.class));
        entity.setSubGroupId(converter.fromRow(row, prefix + "_sub_group_id", String.class));
        entity.setTradeEntityId(converter.fromRow(row, prefix + "_trade_entity_id", Long.class));
        return entity;
    }
}
