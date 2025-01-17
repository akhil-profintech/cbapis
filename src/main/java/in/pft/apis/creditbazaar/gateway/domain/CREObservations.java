package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CREObservations.
 */
@Table("cre_observations")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CREObservations implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("cre_observations_id")
    private Long creObservationsId;

    @Column("cre_observations_ulid_id")
    private String creObservationsUlidId;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("observations")
    private String observations;

    @Column("assessment_id")
    private Long assessmentId;

    @Transient
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "cbcreprocess" }, allowSetters = true)
    private IndividualAssessment individualassessment;

    @Column("individualassessment_id")
    private Long individualassessmentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CREObservations id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreObservationsId() {
        return this.creObservationsId;
    }

    public CREObservations creObservationsId(Long creObservationsId) {
        this.setCreObservationsId(creObservationsId);
        return this;
    }

    public void setCreObservationsId(Long creObservationsId) {
        this.creObservationsId = creObservationsId;
    }

    public String getCreObservationsUlidId() {
        return this.creObservationsUlidId;
    }

    public CREObservations creObservationsUlidId(String creObservationsUlidId) {
        this.setCreObservationsUlidId(creObservationsUlidId);
        return this;
    }

    public void setCreObservationsUlidId(String creObservationsUlidId) {
        this.creObservationsUlidId = creObservationsUlidId;
    }

    public String getCreRequestId() {
        return this.creRequestId;
    }

    public CREObservations creRequestId(String creRequestId) {
        this.setCreRequestId(creRequestId);
        return this;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public String getObservations() {
        return this.observations;
    }

    public CREObservations observations(String observations) {
        this.setObservations(observations);
        return this;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Long getAssessmentId() {
        return this.assessmentId;
    }

    public CREObservations assessmentId(Long assessmentId) {
        this.setAssessmentId(assessmentId);
        return this;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public IndividualAssessment getIndividualassessment() {
        return this.individualassessment;
    }

    public void setIndividualassessment(IndividualAssessment individualAssessment) {
        this.individualassessment = individualAssessment;
        this.individualassessmentId = individualAssessment != null ? individualAssessment.getId() : null;
    }

    public CREObservations individualassessment(IndividualAssessment individualAssessment) {
        this.setIndividualassessment(individualAssessment);
        return this;
    }

    public Long getIndividualassessmentId() {
        return this.individualassessmentId;
    }

    public void setIndividualassessmentId(Long individualAssessment) {
        this.individualassessmentId = individualAssessment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CREObservations)) {
            return false;
        }
        return getId() != null && getId().equals(((CREObservations) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CREObservations{" +
            "id=" + getId() +
            ", creObservationsId=" + getCreObservationsId() +
            ", creObservationsUlidId='" + getCreObservationsUlidId() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", observations='" + getObservations() + "'" +
            ", assessmentId=" + getAssessmentId() +
            "}";
    }
}
