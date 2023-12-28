package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.TradePartner;
import in.pft.apis.creditbazaar.service.dto.TradePartnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TradePartner} and its DTO {@link TradePartnerDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradePartnerMapper extends EntityMapper<TradePartnerDTO, TradePartner> {}
