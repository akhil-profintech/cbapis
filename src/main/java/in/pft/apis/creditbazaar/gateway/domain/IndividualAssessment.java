package in.pft.apis.creditbazaar.gateway.domain;

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
    private Long assessmentId;

    @Column("assessment_ulid_id")
    private String assessmentUlidId;

    @Column("credit_score")
    private Double creditScore;

    @Column("final_verdict")
    private String finalVerdict;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("timestamp")
    private String timestamp;

    @Column("trade_partner_gst")
    private String tradePartnerGST;

    @Column("trade_partner_id")
    private String tradePartnerId;

    @Column("invoice_amount")
    private Long invoiceAmount;

    @Column("invoice_id")
    private String invoiceId;

    @Transient
    @JsonIgnoreProperties(value = { "individualassessment" }, allowSetters = true)
    private Set<CREHighlights> cREHighlights = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "individualassessment" }, allowSetters = true)
    private Set<CREObservations> cREObservations = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "individualAssessments", "financeRequest" }, allowSetters = true)
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

    public Long getAssessmentId() {
        return this.assessmentId;
    }

    public IndividualAssessment assessmentId(Long assessmentId) {
        this.setAssessmentId(assessmentId);
        return this;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentUlidId() {
        return this.assessmentUlidId;
    }

    public IndividualAssessment assessmentUlidId(String assessmentUlidId) {
        this.setAssessmentUlidId(assessmentUlidId);
        return this;
    }

    public void setAssessmentUlidId(String assessmentUlidId) {
        this.assessmentUlidId = assessmentUlidId;
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

    public String getFinalVerdict() {
        return this.finalVerdict;
    }

    public IndividualAssessment finalVerdict(String finalVerdict) {
        this.setFinalVerdict(finalVerdict);
        return this;
    }

    public void setFinalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
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

    public String getTradePartnerGST() {
        return this.tradePartnerGST;
    }

    public IndividualAssessment tradePartnerGST(String tradePartnerGST) {
        this.setTradePartnerGST(tradePartnerGST);
        return this;
    }

    public void setTradePartnerGST(String tradePartnerGST) {
        this.tradePartnerGST = tradePartnerGST;
    }

    public String getTradePartnerId() {
        return this.tradePartnerId;
    }

    public IndividualAssessment tradePartnerId(String tradePartnerId) {
        this.setTradePartnerId(tradePartnerId);
        return this;
    }

    public void setTradePartnerId(String tradePartnerId) {
        this.tradePartnerId = tradePartnerId;
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
            ", assessmentId=" + getAssessmentId() +
            ", assessmentUlidId='" + getAssessmentUlidId() + "'" +
            ", creditScore=" + getCreditScore() +
            ", finalVerdict='" + getFinalVerdict() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", tradePartnerGST='" + getTradePartnerGST() + "'" +
            ", tradePartnerId='" + getTradePartnerId() + "'" +
            ", invoiceAmount=" + getInvoiceAmount() +
            ", invoiceId='" + getInvoiceId() + "'" +
            "}";
    }
}
