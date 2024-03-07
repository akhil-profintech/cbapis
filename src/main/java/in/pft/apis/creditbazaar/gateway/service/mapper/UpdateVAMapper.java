package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.TradeEntity;
import in.pft.apis.creditbazaar.gateway.domain.UpdateVA;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeEntityDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.UpdateVADTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UpdateVA} and its DTO {@link UpdateVADTO}.
 */
@Mapper(componentModel = "spring")
public interface UpdateVAMapper extends EntityMapper<UpdateVADTO, UpdateVA> {
    @Mapping(target = "tradeEntity", source = "tradeEntity", qualifiedByName = "tradeEntityEntityId")
    UpdateVADTO toDto(UpdateVA s);

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
