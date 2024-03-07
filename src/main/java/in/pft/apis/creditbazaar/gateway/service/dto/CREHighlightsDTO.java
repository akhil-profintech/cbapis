package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.CREHighlights} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CREHighlightsDTO implements Serializable {

    private Long id;

    private Long creHighlightsId;

    private String creHighlightsUlidId;

    private String creRequestId;

    private String highlights;

    private IndividualAssessmentDTO individualassessment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreHighlightsId() {
        return creHighlightsId;
    }

    public void setCreHighlightsId(Long creHighlightsId) {
        this.creHighlightsId = creHighlightsId;
    }

    public String getCreHighlightsUlidId() {
        return creHighlightsUlidId;
    }

    public void setCreHighlightsUlidId(String creHighlightsUlidId) {
        this.creHighlightsUlidId = creHighlightsUlidId;
    }

    public String getCreRequestId() {
        return creRequestId;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
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
        if (!(o instanceof CREHighlightsDTO)) {
            return false;
        }

        CREHighlightsDTO cREHighlightsDTO = (CREHighlightsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cREHighlightsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CREHighlightsDTO{" +
            "id=" + getId() +
            ", creHighlightsId=" + getCreHighlightsId() +
            ", creHighlightsUlidId='" + getCreHighlightsUlidId() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", highlights='" + getHighlights() + "'" +
            ", individualassessment=" + getIndividualassessment() +
            "}";
    }
}
