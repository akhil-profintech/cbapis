package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.Disbursement;
import in.pft.apis.creditbazaar.gateway.domain.FundsTransferTransactionDetails;
import in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.gateway.domain.Repayment;
import in.pft.apis.creditbazaar.gateway.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundsTransferTransactionDetails} and its DTO {@link FundsTransferTransactionDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface FundsTransferTransactionDetailsMapper
    extends EntityMapper<FundsTransferTransactionDetailsDTO, FundsTransferTransactionDetails> {
    @Mapping(
        target = "participantsettlement",
        source = "participantsettlement",
        qualifiedByName = "participantSettlementParticipantSettlementId"
    )
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementDbmtId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentRepaymentId")
    FundsTransferTransactionDetailsDTO toDto(FundsTransferTransactionDetails s);

    @Named("participantSettlementParticipantSettlementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "participantSettlementId", source = "participantSettlementId")
    ParticipantSettlementDTO toDtoParticipantSettlementParticipantSettlementId(ParticipantSettlement participantSettlement);

    @Named("disbursementDbmtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dbmtId", source = "dbmtId")
    DisbursementDTO toDtoDisbursementDbmtId(Disbursement disbursement);

    @Named("repaymentRepaymentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "repaymentId", source = "repaymentId")
    RepaymentDTO toDtoRepaymentRepaymentId(Repayment repayment);
}
