package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Column("cre_highlights_id")
    private Long creHighlightsId;

    @Column("cre_highlights_ulid_id")
    private String creHighlightsUlidId;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("highlights")
    private String highlights;

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

    public String getCreHighlightsUlidId() {
        return this.creHighlightsUlidId;
    }

    public CREHighlights creHighlightsUlidId(String creHighlightsUlidId) {
        this.setCreHighlightsUlidId(creHighlightsUlidId);
        return this;
    }

    public void setCreHighlightsUlidId(String creHighlightsUlidId) {
        this.creHighlightsUlidId = creHighlightsUlidId;
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

    public Long getAssessmentId() {
        return this.assessmentId;
    }

    public CREHighlights assessmentId(Long assessmentId) {
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

    public CREHighlights individualassessment(IndividualAssessment individualAssessment) {
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
            ", creHighlightsUlidId='" + getCreHighlightsUlidId() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", highlights='" + getHighlights() + "'" +
            ", assessmentId=" + getAssessmentId() +
            "}";
    }
}
