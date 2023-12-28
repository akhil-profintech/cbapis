package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.CREObservations} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CREObservationsDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long creObservationsId;

    private String creRequestId;

    private String observations;

    private CBCREProcessDTO cbcreprocess;

    private IndividualAssessmentDTO individualassessment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreObservationsId() {
        return creObservationsId;
    }

    public void setCreObservationsId(Long creObservationsId) {
        this.creObservationsId = creObservationsId;
    }

    public String getCreRequestId() {
        return creRequestId;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public CBCREProcessDTO getCbcreprocess() {
        return cbcreprocess;
    }

    public void setCbcreprocess(CBCREProcessDTO cbcreprocess) {
        this.cbcreprocess = cbcreprocess;
    }

    public IndividualAssessmentDTO getIndividualassessment() {
        return individualassessment;
    }

    public void setIndividualassessment(IndividualAssessmentDTO individualassessment) {
        this.individualassessment = individualassessment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CREObservationsDTO)) {
            return false;
        }

        CREObservationsDTO cREObservationsDTO = (CREObservationsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cREObservationsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CREObservationsDTO{" +
            "id=" + getId() +
            ", creObservationsId=" + getCreObservationsId() +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", observations='" + getObservations() + "'" +
            ", cbcreprocess=" + getCbcreprocess() +
            ", individualassessment=" + getIndividualassessment() +
            "}";
    }
}
