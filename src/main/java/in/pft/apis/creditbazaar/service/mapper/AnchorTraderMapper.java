package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.AnchorTrader;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnchorTrader} and its DTO {@link AnchorTraderDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnchorTraderMapper extends EntityMapper<AnchorTraderDTO, AnchorTrader> {}
