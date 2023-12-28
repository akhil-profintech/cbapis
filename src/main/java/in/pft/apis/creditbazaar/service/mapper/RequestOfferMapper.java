package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CBCREProcess;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.domain.RequestOffer;
import in.pft.apis.creditbazaar.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.dto.RequestOfferDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RequestOffer} and its DTO {@link RequestOfferDTO}.
 */
@Mapper(componentModel = "spring")
public interface RequestOfferMapper extends EntityMapper<RequestOfferDTO, RequestOffer> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    @Mapping(target = "cbcreprocess", source = "cbcreprocess", qualifiedByName = "cBCREProcessCbProcessId")
    RequestOfferDTO toDto(RequestOffer s);

    @Named("financeRequestRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    FinanceRequestDTO toDtoFinanceRequestRequestId(FinanceRequest financeRequest);

    @Named("cBCREProcessCbProcessId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cbProcessId", source = "cbProcessId")
    CBCREProcessDTO toDtoCBCREProcessCbProcessId(CBCREProcess cBCREProcess);
}
