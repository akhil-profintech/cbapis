package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.BeneValidation;
import in.pft.apis.creditbazaar.domain.TradeEntity;
import in.pft.apis.creditbazaar.service.dto.BeneValidationDTO;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BeneValidation} and its DTO {@link BeneValidationDTO}.
 */
@Mapper(componentModel = "spring")
public interface BeneValidationMapper extends EntityMapper<BeneValidationDTO, BeneValidation> {
    @Mapping(target = "tradeEntity", source = "tradeEntity", qualifiedByName = "tradeEntityEntityId")
    BeneValidationDTO toDto(BeneValidation s);

    @Named("tradeEntityEntityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "entityId", source = "entityId")
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
