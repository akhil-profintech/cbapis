package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.CREHighlights;
import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.gateway.service.dto.CREHighlightsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.IndividualAssessmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link CREHighlights} and its DTO {@link CREHighlightsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CREHighlightsMapper extends EntityMapper<CREHighlightsDTO, CREHighlights> {
    @Mapping(target = "individualassessment", source = "individualassessment", qualifiedByName = "individualAssessmentAssessmentId")
    CREHighlightsDTO toDto(CREHighlights s);

    @Named("individualAssessmentAssessmentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "assessmentId", source = "assessmentId")
    @Mapping(target = "assessmentUlidId", source = "assessmentUlidId")
    @Mapping(target = "creditScore", source = "creditScore")
    @Mapping(target = "finalVerdict", source = "finalVerdict")
    @Mapping(target = "creRequestId", source = "creRequestId")
    @Mapping(target = "timestamp", source = "timestamp")
    @Mapping(target = "tradePartnerGST", source = "tradePartnerGST")
    @Mapping(target = "tradePartnerId", source = "tradePartnerId")
    @Mapping(target = "invoiceAmount", source = "invoiceAmount")
    @Mapping(target = "invoiceId", source = "invoiceId")
    @Mapping(target = "cbcreprocess", source = "cbcreprocess")
    IndividualAssessmentDTO toDtoIndividualAssessmentAssessmentId(IndividualAssessment individualAssessment);
}
