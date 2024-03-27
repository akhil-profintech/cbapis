package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.gateway.domain.Settlement;
import in.pft.apis.creditbazaar.gateway.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.SettlementDTO;
import org.mapstruct.*;

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
    SettlementDTO toDtoSettlementSettlementId(Settlement settlement);
}
