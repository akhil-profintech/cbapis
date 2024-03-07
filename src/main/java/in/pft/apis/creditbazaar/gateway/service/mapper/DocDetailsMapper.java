package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.DocDetails;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.service.dto.DocDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocDetails} and its DTO {@link DocDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocDetailsMapper extends EntityMapper<DocDetailsDTO, DocDetails> {
    @Mapping(target = "financeRequest", source = "financeRequest", qualifiedByName = "financeRequestFinanceRequestId")
    DocDetailsDTO toDto(DocDetails s);

    @Named("financeRequestFinanceRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "financeRequestId", source = "financeRequestId")
    @Mapping(target = "financeRequestUlidId", source = "financeRequestUlidId")
    @Mapping(target = "financeRequestRefNo", source = "financeRequestRefNo")
    @Mapping(target = "tradeChannelId", source = "tradeChannelId")
    @Mapping(target = "requestAmount", source = "requestAmount")
    @Mapping(target = "requestDate", source = "requestDate")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "requestStatus", source = "requestStatus")
    @Mapping(target = "dueDate", source = "dueDate")
    @Mapping(target = "gstConsent", source = "gstConsent")
    @Mapping(target = "anchortrader", source = "anchortrader")
    FinanceRequestDTO toDtoFinanceRequestFinanceRequestId(FinanceRequest financeRequest);
}
