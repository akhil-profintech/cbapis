package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.InstaAlert;
import in.pft.apis.creditbazaar.domain.TradeEntity;
import in.pft.apis.creditbazaar.service.dto.InstaAlertDTO;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import org.mapstruct.*;

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
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
