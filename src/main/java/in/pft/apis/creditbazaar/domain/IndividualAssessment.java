package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A IndividualAssessment.
 */
@Table("individual_assessment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IndividualAssessment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("assessment_id")
    private String assessmentId;

    @Column("credit_score")
    private Double creditScore;

    @Column("finalverdict")
    private String finalverdict;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("timestamp")
    private String timestamp;

    @Column("tradepartner_gst")
    private String tradepartnerGST;

    @Column("tradepartner_id")
    private String tradepartnerId;

    @Column("invoice_amount")
    private Long invoiceAmount;

    @Column("invoice_id")
    private String invoiceId;

    @Transient
    @JsonIgnoreProperties(value = { "cbcreprocess", "individualassessment" }, allowSetters = true)
    private Set<CREHighlights> cREHighlights = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "cbcreprocess", "individualassessment" }, allowSetters = true)
    private Set<CREObservations> cREObservations = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "requestOffers", "individualAssessments" }, allowSetters = true)
    private CBCREProcess cbcreprocess;

    @Column("cbcreprocess_id")
    private Long cbcreprocessId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public IndividualAssessment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssessmentId() {
        return this.assessmentId;
    }

    public IndividualAssessment assessmentId(String assessmentId) {
        this.setAssessmentId(assessmentId);
        return this;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Double getCreditScore() {
        return this.creditScore;
    }

    public IndividualAssessment creditScore(Double creditScore) {
        this.setCreditScore(creditScore);
        return this;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    public String getFinalverdict() {
        return this.finalverdict;
    }

    public IndividualAssessment finalverdict(String finalverdict) {
        this.setFinalverdict(finalverdict);
        return this;
    }

    public void setFinalverdict(String finalverdict) {
        this.finalverdict = finalverdict;
    }

    public String getCreRequestId() {
        return this.creRequestId;
    }

    public IndividualAssessment creRequestId(String creRequestId) {
        this.setCreRequestId(creRequestId);
        return this;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public IndividualAssessment timestamp(String timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTradepartnerGST() {
        return this.tradepartnerGST;
    }

    public IndividualAssessment tradepartnerGST(String tradepartnerGST) {
        this.setTradepartnerGST(tradepartnerGST);
        return this;
    }

    public void setTradepartnerGST(String tradepartnerGST) {
        this.tradepartnerGST = tradepartnerGST;
    }

    public String getTradepartnerId() {
        return this.tradepartnerId;
    }

    public IndividualAssessment tradepartnerId(String tradepartnerId) {
        this.setTradepartnerId(tradepartnerId);
        return this;
    }

    public void setTradepartnerId(String tradepartnerId) {
        this.tradepartnerId = tradepartnerId;
    }

    public Long getInvoiceAmount() {
        return this.invoiceAmount;
    }

    public IndividualAssessment invoiceAmount(Long invoiceAmount) {
        this.setInvoiceAmount(invoiceAmount);
        return this;
    }

    public void setInvoiceAmount(Long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceId() {
        return this.invoiceId;
    }

    public IndividualAssessment invoiceId(String invoiceId) {
        this.setInvoiceId(invoiceId);
        return this;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Set<CREHighlights> getCREHighlights() {
        return this.cREHighlights;
    }

    public void setCREHighlights(Set<CREHighlights> cREHighlights) {
        if (this.cREHighlights != null) {
            this.cREHighlights.forEach(i -> i.setIndividualassessment(null));
        }
        if (cREHighlights != null) {
            cREHighlights.forEach(i -> i.setIndividualassessment(this));
        }
        this.cREHighlights = cREHighlights;
    }

    public IndividualAssessment cREHighlights(Set<CREHighlights> cREHighlights) {
        this.setCREHighlights(cREHighlights);
        return this;
    }

    public IndividualAssessment addCREHighlights(CREHighlights cREHighlights) {
        this.cREHighlights.add(cREHighlights);
        cREHighlights.setIndividualassessment(this);
        return this;
    }

    public IndividualAssessment removeCREHighlights(CREHighlights cREHighlights) {
        this.cREHighlights.remove(cREHighlights);
        cREHighlights.setIndividualassessment(null);
        return this;
    }

    public Set<CREObservations> getCREObservations() {
        return this.cREObservations;
    }

    public void setCREObservations(Set<CREObservations> cREObservations) {
        if (this.cREObservations != null) {
            this.cREObservations.forEach(i -> i.setIndividualassessment(null));
        }
        if (cREObservations != null) {
            cREObservations.forEach(i -> i.setIndividualassessment(this));
        }
        this.cREObservations = cREObservations;
    }

    public IndividualAssessment cREObservations(Set<CREObservations> cREObservations) {
        this.setCREObservations(cREObservations);
        return this;
    }

    public IndividualAssessment addCREObservations(CREObservations cREObservations) {
        this.cREObservations.add(cREObservations);
        cREObservations.setIndividualassessment(this);
        return this;
    }

    public IndividualAssessment removeCREObservations(CREObservations cREObservations) {
        this.cREObservations.remove(cREObservations);
        cREObservations.setIndividualassessment(null);
        return this;
    }

    public CBCREProcess getCbcreprocess() {
        return this.cbcreprocess;
    }

    public void setCbcreprocess(CBCREProcess cBCREProcess) {
        this.cbcreprocess = cBCREProcess;
        this.cbcreprocessId = cBCREProcess != null ? cBCREProcess.getId() : null;
    }

    public IndividualAssessment cbcreprocess(CBCREProcess cBCREProcess) {
        this.setCbcreprocess(cBCREProcess);
        return this;
    }

    public Long getCbcreprocessId() {
        return this.cbcreprocessId;
    }

    public void setCbcreprocessId(Long cBCREProcess) {
        this.cbcreprocessId = cBCREProcess;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IndividualAssessment)) {
            return false;
        }
        return getId() != null && getId().equals(((IndividualAssessment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndividualAssessment{" +
            "id=" + getId() +
            ", assessmentId='" + getAssessmentId() + "'" +
            ", creditScore=" + getCreditScore() +
            ", finalverdict='" + getFinalverdict() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", tradepartnerGST='" + getTradepartnerGST() + "'" +
            ", tradepartnerId='" + getTradepartnerId() + "'" +
            ", invoiceAmount=" + getInvoiceAmount() +
            ", invoiceId='" + getInvoiceId() + "'" +
            "}";
    }
}
