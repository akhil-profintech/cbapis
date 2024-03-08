package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.gateway.domain.Settlement;
import in.pft.apis.creditbazaar.gateway.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.SettlementDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link ParticipantSettlement} and its DTO {@link ParticipantSettlementDTO}.
 */
@Mapper(componentModel = "spring")
public interface ParticipantSettlementMapper extends EntityMapper<ParticipantSettlementDTO, ParticipantSettlement> {
    @Mapping(target = "settlement", source = "settlement", qualifiedByName = "settlementSettlementId")
    ParticipantSettlementDTO toDto(ParticipantSettlement s);

    @Named("settlementSettlementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "settlementId", source = "settlementId")
    @Mapping(target = "settlementUlidId", source = "settlementUlidId")
    @Mapping(target = "settlementRefNo", source = "settlementRefNo")
    @Mapping(target = "acceptedOfferUlidId", source = "acceptedOfferUlidId")
    @Mapping(target = "placedOfferUlidId", source = "placedOfferUlidId")
    @Mapping(target = "reqOffUlidId", source = "reqOffUlidId")
    @Mapping(target = "dbmtRequestId", source = "dbmtRequestId")
    @Mapping(target = "dbmtId", source = "dbmtId")
    @Mapping(target = "dbmtDate", source = "dbmtDate")
    @Mapping(target = "dbmtStatus", source = "dbmtStatus")
    @Mapping(target = "dbmtAmount", source = "dbmtAmount")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "financerequest", source = "financerequest")
    SettlementDTO toDtoSettlementSettlementId(Settlement settlement);
}
