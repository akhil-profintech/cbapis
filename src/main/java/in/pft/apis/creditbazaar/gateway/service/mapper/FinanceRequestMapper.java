package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTrader;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link FinanceRequest} and its DTO {@link FinanceRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface FinanceRequestMapper extends EntityMapper<FinanceRequestDTO, FinanceRequest> {
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    FinanceRequestDTO toDto(FinanceRequest s);

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atUlidId", source = "atId")
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
}
