package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CREHighlights.
 */
@Table("cre_highlights")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CREHighlights implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("cre_highlights_id")
    private Long creHighlightsId;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("highlights")
    private String highlights;

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

    public CREHighlights id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreHighlightsId() {
        return this.creHighlightsId;
    }

    public CREHighlights creHighlightsId(Long creHighlightsId) {
        this.setCreHighlightsId(creHighlightsId);
        return this;
    }

    public void setCreHighlightsId(Long creHighlightsId) {
        this.creHighlightsId = creHighlightsId;
    }

    public String getCreRequestId() {
        return this.creRequestId;
    }

    public CREHighlights creRequestId(String creRequestId) {
        this.setCreRequestId(creRequestId);
        return this;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public String getHighlights() {
        return this.highlights;
    }

    public CREHighlights highlights(String highlights) {
        this.setHighlights(highlights);
        return this;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public CBCREProcess getCbcreprocess() {
        return this.cbcreprocess;
    }

    public void setCbcreprocess(CBCREProcess cBCREProcess) {
        this.cbcreprocess = cBCREProcess;
        this.cbcreprocessId = cBCREProcess != null ? cBCREProcess.getId() : null;
    }

    public CREHighlights cbcreprocess(CBCREProcess cBCREProcess) {
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

    public CREHighlights individualassessment(IndividualAssessment individualAssessment) {
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
        if (!(o instanceof CREHighlights)) {
            return false;
        }
        return getId() != null && getId().equals(((CREHighlights) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CREHighlights{" +
            "id=" + getId() +
            ", creHighlightsId=" + getCreHighlightsId() +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", highlights='" + getHighlights() + "'" +
            "}";
    }
}
