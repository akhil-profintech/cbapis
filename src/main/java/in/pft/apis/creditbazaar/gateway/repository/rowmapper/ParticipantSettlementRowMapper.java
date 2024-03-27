package in.pft.apis.creditbazaar.gateway.repository.rowmapper;

import in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.gateway.domain.enumeration.ChargeType;
import in.pft.apis.creditbazaar.gateway.domain.enumeration.SettlementType;
import io.r2dbc.spi.Row;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;

/**
 * Converter between {@link Row} to {@link ParticipantSettlement}, with proper type conversions.
 */
@Service
public class ParticipantSettlementRowMapper implements BiFunction<Row, String, ParticipantSettlement> {

    private final ColumnConverter converter;

    public ParticipantSettlementRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ParticipantSettlement} stored in the database.
     */
    @Override
    public ParticipantSettlement apply(Row row, String prefix) {
        ParticipantSettlement entity = new ParticipantSettlement();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setParticipantSettlementId(converter.fromRow(row, prefix + "_participant_settlement_id", Long.class));
        entity.setParticipantSettlementUlidId(converter.fromRow(row, prefix + "_participant_settlement_ulid_id", String.class));
        entity.setParticipantId(converter.fromRow(row, prefix + "_participant_id", Long.class));
        entity.setParticipantName(converter.fromRow(row, prefix + "_participant_name", String.class));
        entity.setGstId(converter.fromRow(row, prefix + "_gst_id", String.class));
        entity.setSettlementType(converter.fromRow(row, prefix + "_settlement_type", SettlementType.class));
        entity.setChargeType(converter.fromRow(row, prefix + "_charge_type", ChargeType.class));
        entity.setSettlementAmount(converter.fromRow(row, prefix + "_settlement_amount", Long.class));
        entity.setSettlementDate(converter.fromRow(row, prefix + "_settlement_date", String.class));
        entity.setSettlementDueDate(converter.fromRow(row, prefix + "_settlement_due_date", String.class));
        entity.setSettlementContactInfo(converter.fromRow(row, prefix + "_settlement_contact_info", String.class));
        entity.setPatronOfPayment(converter.fromRow(row, prefix + "_patron_of_payment", String.class));
        entity.setRecipientOfPayment(converter.fromRow(row, prefix + "_recipient_of_payment", String.class));
        entity.setSettlementMethod(converter.fromRow(row, prefix + "_settlement_method", String.class));
        entity.setTenantId(converter.fromRow(row, prefix + "_tenant_id", Long.class));
        entity.setAccName(converter.fromRow(row, prefix + "_acc_name", String.class));
        entity.setIfscCode(converter.fromRow(row, prefix + "_ifsc_code", String.class));
        entity.setAccNumber(converter.fromRow(row, prefix + "_acc_number", Long.class));
        entity.setDocId(converter.fromRow(row, prefix + "_doc_id", String.class));
        entity.setRecordStatus(converter.fromRow(row, prefix + "_record_status", String.class));
        entity.setSettlementId(converter.fromRow(row, prefix + "_settlement_id", Long.class));
        return entity;
    }
}
