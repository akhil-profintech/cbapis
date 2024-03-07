package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.CREObservations} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CREObservationsDTO implements Serializable {

    private Long id;

    private Long creObservationsId;

    private String creObservationsUlidId;

    private String creRequestId;

    private String observations;

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

    public String getCreObservationsUlidId() {
        return creObservationsUlidId;
    }

    public void setCreObservationsUlidId(String creObservationsUlidId) {
        this.creObservationsUlidId = creObservationsUlidId;
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
            ", creObservationsUlidId='" + getCreObservationsUlidId() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", observations='" + getObservations() + "'" +
            ", individualassessment=" + getIndividualassessment() +
            "}";
    }
}
