package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTrader;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnchorTrader} and its DTO {@link AnchorTraderDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnchorTraderMapper extends EntityMapper<AnchorTraderDTO, AnchorTrader> {}
