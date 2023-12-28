package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Disbursement;
import in.pft.apis.creditbazaar.domain.FinancePartner;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.service.dto.FinancePartnerDTO;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Disbursement} and its DTO {@link DisbursementDTO}.
 */
@Mapper(componentModel = "spring")
public interface DisbursementMapper extends EntityMapper<DisbursementDTO, Disbursement> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    @Mapping(target = "financepartner", source = "financepartner", qualifiedByName = "financePartnerFpId")
    DisbursementDTO toDto(Disbursement s);

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
