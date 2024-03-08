package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.domain.Settlement;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.SettlementDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Settlement} and its DTO {@link SettlementDTO}.
 */
@Mapper(componentModel = "spring")
public interface SettlementMapper extends EntityMapper<SettlementDTO, Settlement> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestFinanceRequestId")
    SettlementDTO toDto(Settlement s);

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
