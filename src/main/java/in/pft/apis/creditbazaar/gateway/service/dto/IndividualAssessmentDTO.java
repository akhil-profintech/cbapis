package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.IndividualAssessment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IndividualAssessmentDTO implements Serializable {

    private Long id;

    private Long assessmentId;

    private String assessmentUlidId;

    private Double creditScore;

    private String finalVerdict;

    private String creRequestId;

    private String timestamp;

    private String tradePartnerGST;

    private String tradePartnerId;

    private Long invoiceAmount;

    private String invoiceId;

    private CBCREProcessDTO cbcreprocess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getAssessmentUlidId() {
        return assessmentUlidId;
    }

    public void setAssessmentUlidId(String assessmentUlidId) {
        this.assessmentUlidId = assessmentUlidId;
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    public String getFinalVerdict() {
        return finalVerdict;
    }

    public void setFinalVerdict(String finalVerdict) {
        this.finalVerdict = finalVerdict;
    }

    public String getCreRequestId() {
        return creRequestId;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTradePartnerGST() {
        return tradePartnerGST;
    }

    public void setTradePartnerGST(String tradePartnerGST) {
        this.tradePartnerGST = tradePartnerGST;
    }

    public String getTradePartnerId() {
        return tradePartnerId;
    }

    public void setTradePartnerId(String tradePartnerId) {
        this.tradePartnerId = tradePartnerId;
    }

    public Long getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(Long invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public CBCREProcessDTO getCbcreprocess() {
        return cbcreprocess;
    }

    public void setCbcreprocess(CBCREProcessDTO cbcreprocess) {
        this.cbcreprocess = cbcreprocess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IndividualAssessmentDTO)) {
            return false;
        }

        IndividualAssessmentDTO individualAssessmentDTO = (IndividualAssessmentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, individualAssessmentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IndividualAssessmentDTO{" +
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
            ", cbcreprocess=" + getCbcreprocess() +
            "}";
    }
}
