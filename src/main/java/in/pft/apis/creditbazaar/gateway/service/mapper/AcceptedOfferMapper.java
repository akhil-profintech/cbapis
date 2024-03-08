package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.AcceptedOffer;
import in.pft.apis.creditbazaar.gateway.domain.AnchorTrader;
import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.service.dto.AcceptedOfferDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link AcceptedOffer} and its DTO {@link AcceptedOfferDTO}.
 */
@Mapper(componentModel = "spring")
public interface AcceptedOfferMapper extends EntityMapper<AcceptedOfferDTO, AcceptedOffer> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestFinanceRequestId")
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    @Mapping(target = "financepartner", source = "financepartner", qualifiedByName = "financePartnerFpId")
    AcceptedOfferDTO toDto(AcceptedOffer s);

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

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atId", source = "atId")
    @Mapping(target = "atUlidId", source = "atUlidId")
    @Mapping(target = "orgId", source = "orgId")
    @Mapping(target = "tenantId", source = "tenantId")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "orgName", source = "orgName")
    @Mapping(target = "gstId", source = "gstId")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "anchorTraderName", source = "anchorTraderName")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "anchorTraderGST", source = "anchorTraderGST")
    @Mapping(target = "anchorTraderSector", source = "anchorTraderSector")
    @Mapping(target = "anchorTraderPAN", source = "anchorTraderPAN")
    @Mapping(target = "kycCompleted", source = "kycCompleted")
    @Mapping(target = "bankDetails", source = "bankDetails")
    @Mapping(target = "emailId", source = "emailId")
    @Mapping(target = "accountNumber", source = "accountNumber")
    @Mapping(target = "ifscCode", source = "ifscCode")
    @Mapping(target = "bankName", source = "bankName")
    @Mapping(target = "branchName", source = "branchName")
    @Mapping(target = "erpAccess", source = "erpAccess")
    @Mapping(target = "typeOfFirm", source = "typeOfFirm")
    @Mapping(target = "panStatus", source = "panStatus")
    @Mapping(target = "tosDocument", source = "tosDocument")
    @Mapping(target = "acceptTerms", source = "acceptTerms")
    @Mapping(target = "gstRegistrationCertificateUploadStatus", source = "gstRegistrationCertificateUploadStatus")
    @Mapping(target = "gstRegistrationCertificateVerificationStatus", source = "gstRegistrationCertificateVerificationStatus")
    @Mapping(target = "udhyamRegistrationcertificateUploadStatus", source = "udhyamRegistrationcertificateUploadStatus")
    @Mapping(target = "udhyamRegistrationcertificateVerificationStatus", source = "udhyamRegistrationcertificateVerificationStatus")
    @Mapping(target = "kycDeclaration", source = "kycDeclaration")
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);

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
