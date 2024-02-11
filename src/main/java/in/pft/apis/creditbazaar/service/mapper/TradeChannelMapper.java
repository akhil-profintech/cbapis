package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.TradeChannel;
import in.pft.apis.creditbazaar.service.dto.TradeChannelDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TradeChannel} and its DTO {@link TradeChannelDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeChannelMapper extends EntityMapper<TradeChannelDTO, TradeChannel> {}
