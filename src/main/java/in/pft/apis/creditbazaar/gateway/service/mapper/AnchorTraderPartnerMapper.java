package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTrader;
import in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderPartnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AnchorTraderPartner} and its DTO {@link AnchorTraderPartnerDTO}.
 */
@Mapper(componentModel = "spring")
public interface AnchorTraderPartnerMapper extends EntityMapper<AnchorTraderPartnerDTO, AnchorTraderPartner> {
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    AnchorTraderPartnerDTO toDto(AnchorTraderPartner s);

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atId", source = "atId")
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);
}
