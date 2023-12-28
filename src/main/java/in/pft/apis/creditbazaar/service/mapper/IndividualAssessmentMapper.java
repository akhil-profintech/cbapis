package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CBCREProcess;
import in.pft.apis.creditbazaar.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.service.dto.IndividualAssessmentDTO;
import org.mapstruct.*;

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
    CBCREProcessDTO toDtoCBCREProcessCbProcessId(CBCREProcess cBCREProcess);
}
