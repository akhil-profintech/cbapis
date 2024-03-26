package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.CREObservations;
import in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment;
import in.pft.apis.creditbazaar.gateway.service.dto.CREObservationsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.IndividualAssessmentDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for the entity {@link CREObservations} and its DTO {@link CREObservationsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CREObservationsMapper extends EntityMapper<CREObservationsDTO, CREObservations> {
    @Mapping(target = "individualassessment", source = "individualassessment", qualifiedByName = "individualAssessmentAssessmentId")
    CREObservationsDTO toDto(CREObservations s);

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
    @Mapping(target = "baseScore", source = "baseScore")
    @Mapping(target = "ctin", source = "ctin")
    @Mapping(target = "invDate", source = "invDate")
    @Mapping(target = "cbProcessId", source = "cbProcessId")
    @Mapping(target = "grnPresent", source = "grnPresent")
    @Mapping(target = "einvoicePresent", source = "einvoicePresent")
    @Mapping(target = "ewayBillPresent", source = "ewayBillPresent")
    @Mapping(target = "tradePartnerConfirmation", source = "tradePartnerConfirmation")
    @Mapping(target = "cbcreprocess", source = "cbcreprocess")
    IndividualAssessmentDTO toDtoIndividualAssessmentAssessmentId(IndividualAssessment individualAssessment);
}
