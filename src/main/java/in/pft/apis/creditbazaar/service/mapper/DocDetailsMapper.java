package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Disbursement;
import in.pft.apis.creditbazaar.domain.DocDetails;
import in.pft.apis.creditbazaar.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.service.dto.DocDetailsDTO;
import in.pft.apis.creditbazaar.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocDetails} and its DTO {@link DocDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface DocDetailsMapper extends EntityMapper<DocDetailsDTO, DocDetails> {
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementDbmtRequestId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentRepaymentId")
    @Mapping(
        target = "participantsettlement",
        source = "participantsettlement",
        qualifiedByName = "participantSettlementParticipantSettlementId"
    )
    DocDetailsDTO toDto(DocDetails s);

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

    @Named("participantSettlementParticipantSettlementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "participantSettlementId", source = "participantSettlementId")
    ParticipantSettlementDTO toDtoParticipantSettlementParticipantSettlementId(ParticipantSettlement participantSettlement);
}
