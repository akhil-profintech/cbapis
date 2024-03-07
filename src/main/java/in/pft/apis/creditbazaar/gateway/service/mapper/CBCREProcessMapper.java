package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.CBCREProcess;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CBCREProcess} and its DTO {@link CBCREProcessDTO}.
 */
@Mapper(componentModel = "spring")
public interface CBCREProcessMapper extends EntityMapper<CBCREProcessDTO, CBCREProcess> {
    @Mapping(target = "financeRequest", source = "financeRequest", qualifiedByName = "financeRequestFinanceRequestId")
    CBCREProcessDTO toDto(CBCREProcess s);

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
