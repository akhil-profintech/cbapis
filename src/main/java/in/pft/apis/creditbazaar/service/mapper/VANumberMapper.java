package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.TradeEntity;
import in.pft.apis.creditbazaar.domain.VANumber;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import in.pft.apis.creditbazaar.service.dto.VANumberDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link VANumber} and its DTO {@link VANumberDTO}.
 */
@Mapper(componentModel = "spring")
public interface VANumberMapper extends EntityMapper<VANumberDTO, VANumber> {
    @Mapping(target = "tradeEntity", source = "tradeEntity", qualifiedByName = "tradeEntityEntityId")
    VANumberDTO toDto(VANumber s);

    @Named("tradeEntityEntityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "entityId", source = "entityId")
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
