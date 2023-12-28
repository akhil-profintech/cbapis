package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
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

    @NotNull(message = "must not be null")
    @Column("cre_observations_id")
    private Long creObservationsId;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("observations")
    private String observations;

    @Transient
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "requestOffers", "individualAssessments" }, allowSetters = true)
    private CBCREProcess cbcreprocess;

    @Transient
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "cbcreprocess" }, allowSetters = true)
    private IndividualAssessment individualassessment;

    @Column("cbcreprocess_id")
    private Long cbcreprocessId;

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

    public CBCREProcess getCbcreprocess() {
        return this.cbcreprocess;
    }

    public void setCbcreprocess(CBCREProcess cBCREProcess) {
        this.cbcreprocess = cBCREProcess;
        this.cbcreprocessId = cBCREProcess != null ? cBCREProcess.getId() : null;
    }

    public CREObservations cbcreprocess(CBCREProcess cBCREProcess) {
        this.setCbcreprocess(cBCREProcess);
        return this;
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

    public Long getCbcreprocessId() {
        return this.cbcreprocessId;
    }

    public void setCbcreprocessId(Long cBCREProcess) {
        this.cbcreprocessId = cBCREProcess;
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
            ", creRequestId='" + getCreRequestId() + "'" +
            ", observations='" + getObservations() + "'" +
            "}";
    }
}
