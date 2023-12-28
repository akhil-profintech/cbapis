package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CBCREProcess;
import in.pft.apis.creditbazaar.domain.CREObservations;
import in.pft.apis.creditbazaar.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.service.dto.CBCREProcessDTO;
import in.pft.apis.creditbazaar.service.dto.CREObservationsDTO;
import in.pft.apis.creditbazaar.service.dto.IndividualAssessmentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CREObservations} and its DTO {@link CREObservationsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CREObservationsMapper extends EntityMapper<CREObservationsDTO, CREObservations> {
    @Mapping(target = "cbcreprocess", source = "cbcreprocess", qualifiedByName = "cBCREProcessCbProcessId")
    @Mapping(target = "individualassessment", source = "individualassessment", qualifiedByName = "individualAssessmentAssessmentId")
    CREObservationsDTO toDto(CREObservations s);

    @Named("cBCREProcessCbProcessId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cbProcessId", source = "cbProcessId")
    CBCREProcessDTO toDtoCBCREProcessCbProcessId(CBCREProcess cBCREProcess);

    @Named("individualAssessmentAssessmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "assessmentId", source = "assessmentId")
    IndividualAssessmentDTO toDtoIndividualAssessmentAssessmentId(IndividualAssessment individualAssessment);
}
