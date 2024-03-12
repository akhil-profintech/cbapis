package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.TradeEntity;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeEntityDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TradeEntity} and its DTO {@link TradeEntityDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeEntityMapper extends EntityMapper<TradeEntityDTO, TradeEntity> {}
