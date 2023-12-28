package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.TradeEntity;
import in.pft.apis.creditbazaar.domain.UpdateVA;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import in.pft.apis.creditbazaar.service.dto.UpdateVADTO;
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
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
