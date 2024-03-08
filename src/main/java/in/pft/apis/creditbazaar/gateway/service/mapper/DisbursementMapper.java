package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.Disbursement;
import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Disbursement} and its DTO {@link DisbursementDTO}.
 */
@Mapper(componentModel = "spring")
public interface DisbursementMapper extends EntityMapper<DisbursementDTO, Disbursement> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestFinanceRequestId")
    @Mapping(target = "financepartner", source = "financepartner", qualifiedByName = "financePartnerFpId")
    DisbursementDTO toDto(Disbursement s);

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

    @Named("financePartnerFpId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fpId", source = "fpId")
    @Mapping(target = "fpUlidId", source = "fpUlidId")
    @Mapping(target = "tenantId", source = "tenantId")
    @Mapping(target = "orgId", source = "orgId")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "orgName", source = "orgName")
    @Mapping(target = "gstId", source = "gstId")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "tosDocument", source = "tosDocument")
    FinancePartnerDTO toDtoFinancePartnerFpId(FinancePartner financePartner);
}
