package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.CBCREProcess;
import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.gateway.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.IndividualAssessmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link IndividualAssessment} and its DTO {@link IndividualAssessmentDTO}.
 */
@Mapper(componentModel = "spring")
public interface IndividualAssessmentMapper extends EntityMapper<IndividualAssessmentDTO, IndividualAssessment> {
    @Mapping(target = "cbcreprocess", source = "cbcreprocess", qualifiedByName = "cBCREProcessCbProcessId")
    IndividualAssessmentDTO toDto(IndividualAssessment s);

    @Named("cBCREProcessCbProcessId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cbProcessId", source = "cbProcessId")
    @Mapping(target = "cbProcessUlidId", source = "cbProcessUlidId")
    @Mapping(target = "cbProcessRefNo", source = "cbProcessRefNo")
    @Mapping(target = "anchorTraderId", source = "anchorTraderId")
    @Mapping(target = "anchorTraderGst", source = "anchorTraderGst")
    @Mapping(target = "financeAmount", source = "financeAmount")
    @Mapping(target = "processDateTime", source = "processDateTime")
    @Mapping(target = "creProcessStatus", source = "creProcessStatus")
    @Mapping(target = "responseDateTime", source = "responseDateTime")
    @Mapping(target = "creRequestId", source = "creRequestId")
    @Mapping(target = "cumilativeTradeScore", source = "cumilativeTradeScore")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "totalAmountRequested", source = "totalAmountRequested")
    @Mapping(target = "totalInvoiceAmount", source = "totalInvoiceAmount")
    @Mapping(target = "financeRequest", source = "financeRequest")
    CBCREProcessDTO toDtoCBCREProcessCbProcessId(CBCREProcess cBCREProcess);
}
