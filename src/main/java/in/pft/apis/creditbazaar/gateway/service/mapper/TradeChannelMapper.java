package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.TradeChannel;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeChannelDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TradeChannel} and its DTO {@link TradeChannelDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeChannelMapper extends EntityMapper<TradeChannelDTO, TradeChannel> {}
