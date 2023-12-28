package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Disbursement;
import in.pft.apis.creditbazaar.domain.EscrowAccountDetails;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.service.dto.EscrowAccountDetailsDTO;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EscrowAccountDetails} and its DTO {@link EscrowAccountDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface EscrowAccountDetailsMapper extends EntityMapper<EscrowAccountDetailsDTO, EscrowAccountDetails> {
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementDbmtRequestId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentRepaymentId")
    EscrowAccountDetailsDTO toDto(EscrowAccountDetails s);

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
