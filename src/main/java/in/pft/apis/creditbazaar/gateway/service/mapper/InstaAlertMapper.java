package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.InstaAlert;
import in.pft.apis.creditbazaar.gateway.domain.TradeEntity;
import in.pft.apis.creditbazaar.gateway.service.dto.InstaAlertDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeEntityDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link InstaAlert} and its DTO {@link InstaAlertDTO}.
 */
@Mapper(componentModel = "spring")
public interface InstaAlertMapper extends EntityMapper<InstaAlertDTO, InstaAlert> {
    @Mapping(target = "tradeEntity", source = "tradeEntity", qualifiedByName = "tradeEntityEntityId")
    InstaAlertDTO toDto(InstaAlert s);

    @Named("tradeEntityEntityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "entityId", source = "entityId")
    @Mapping(target = "entityUlidId", source = "entityUlidId")
    @Mapping(target = "entityName", source = "entityName")
    @Mapping(target = "entityDesc", source = "entityDesc")
    @Mapping(target = "entityGST", source = "entityGST")
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
