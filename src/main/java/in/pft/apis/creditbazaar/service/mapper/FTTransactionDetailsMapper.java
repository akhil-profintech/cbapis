package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Disbursement;
import in.pft.apis.creditbazaar.domain.FTTransactionDetails;
import in.pft.apis.creditbazaar.domain.ParticipantSettlement;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.service.dto.FTTransactionDetailsDTO;
import in.pft.apis.creditbazaar.service.dto.ParticipantSettlementDTO;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FTTransactionDetails} and its DTO {@link FTTransactionDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface FTTransactionDetailsMapper extends EntityMapper<FTTransactionDetailsDTO, FTTransactionDetails> {
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementFtTrnxDetailsId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentFtTrnxDetailsId")
    @Mapping(
        target = "participantsettlement",
        source = "participantsettlement",
        qualifiedByName = "participantSettlementParticipantSettlementId"
    )
    FTTransactionDetailsDTO toDto(FTTransactionDetails s);

    @Named("disbursementFtTrnxDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "ftTrnxDetailsId", source = "ftTrnxDetailsId")
    DisbursementDTO toDtoDisbursementFtTrnxDetailsId(Disbursement disbursement);

    @Named("repaymentFtTrnxDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "ftTrnxDetailsId", source = "ftTrnxDetailsId")
    RepaymentDTO toDtoRepaymentFtTrnxDetailsId(Repayment repayment);

    @Named("participantSettlementParticipantSettlementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "participantSettlementId", source = "participantSettlementId")
    ParticipantSettlementDTO toDtoParticipantSettlementParticipantSettlementId(ParticipantSettlement participantSettlement);
}
