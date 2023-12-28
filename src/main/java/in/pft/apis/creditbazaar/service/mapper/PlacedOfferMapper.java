package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.FinancePartner;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.domain.PlacedOffer;
import in.pft.apis.creditbazaar.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.dto.PlacedOfferDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PlacedOffer} and its DTO {@link PlacedOfferDTO}.
 */
@Mapper(componentModel = "spring")
public interface PlacedOfferMapper extends EntityMapper<PlacedOfferDTO, PlacedOffer> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    @Mapping(target = "financepartner", source = "financepartner", qualifiedByName = "financePartnerFpId")
    PlacedOfferDTO toDto(PlacedOffer s);

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
}
