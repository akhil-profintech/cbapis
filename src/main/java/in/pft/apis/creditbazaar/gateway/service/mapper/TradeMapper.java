package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTrader;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.domain.Trade;
import in.pft.apis.creditbazaar.gateway.domain.TradePartner;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.TradePartnerDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link Trade} and its DTO {@link TradeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeMapper extends EntityMapper<TradeDTO, Trade> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestFinanceRequestId")
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    @Mapping(target = "tradepartner", source = "tradepartner", qualifiedByName = "tradePartnerTpId")
    TradeDTO toDto(Trade s);

    @Named("financeRequestFinanceRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "financeRequestId", source = "financeRequestId")
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
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);

    @Named("tradePartnerTpId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "tpId", source = "tpId")
    @Mapping(target = "tpUlidId", source = "tpUlidId")
    @Mapping(target = "orgId", source = "orgId")
    @Mapping(target = "tenantId", source = "tenantId")
    @Mapping(target = "customerName", source = "customerName")
    @Mapping(target = "orgName", source = "orgName")
    @Mapping(target = "gstId", source = "gstId")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    @Mapping(target = "tradePartnerName", source = "tradePartnerName")
    @Mapping(target = "location", source = "location")
    @Mapping(target = "tradePartnerGST", source = "tradePartnerGST")
    @Mapping(target = "tradePartnerSector", source = "tradePartnerSector")
    @Mapping(target = "acceptanceFromTradePartner", source = "acceptanceFromTradePartner")
    @Mapping(target = "tosDocument", source = "tosDocument")
    TradePartnerDTO toDtoTradePartnerTpId(TradePartner tradePartner);
}
