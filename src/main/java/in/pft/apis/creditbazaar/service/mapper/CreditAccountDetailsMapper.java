package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CreditAccountDetails;
import in.pft.apis.creditbazaar.domain.Disbursement;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.service.dto.CreditAccountDetailsDTO;
import in.pft.apis.creditbazaar.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreditAccountDetails} and its DTO {@link CreditAccountDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreditAccountDetailsMapper extends EntityMapper<CreditAccountDetailsDTO, CreditAccountDetails> {
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementDbmtRequestId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentRepaymentId")
    CreditAccountDetailsDTO toDto(CreditAccountDetails s);

    @Named("disbursementDbmtRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dbmtRequestId", source = "dbmtRequestId")
    DisbursementDTO toDtoDisbursementDbmtRequestId(Disbursement disbursement);

    @Named("repaymentRepaymentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "repaymentId", source = "repaymentId")
    RepaymentDTO toDtoRepaymentRepaymentId(Repayment repayment);
}
