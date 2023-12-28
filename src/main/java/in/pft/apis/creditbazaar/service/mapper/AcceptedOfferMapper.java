package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.AcceptedOffer;
import in.pft.apis.creditbazaar.domain.AnchorTrader;
import in.pft.apis.creditbazaar.domain.FinancePartner;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.service.dto.AcceptedOfferDTO;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AcceptedOffer} and its DTO {@link AcceptedOfferDTO}.
 */
@Mapper(componentModel = "spring")
public interface AcceptedOfferMapper extends EntityMapper<AcceptedOfferDTO, AcceptedOffer> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    @Mapping(target = "financepartner", source = "financepartner", qualifiedByName = "financePartnerFpId")
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    AcceptedOfferDTO toDto(AcceptedOffer s);

    @Named("financeRequestRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    FinanceRequestDTO toDtoFinanceRequestRequestId(FinanceRequest financeRequest);

    @Named("financePartnerFpId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fpId", source = "fpId")
    FinancePartnerDTO toDtoFinancePartnerFpId(FinancePartner financePartner);

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atId", source = "atId")
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);
}
