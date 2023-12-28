package in.pft.apis.creditbazaar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.IndividualAssessment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IndividualAssessmentDTO implements Serializable {

    private Long id;

    private String assessmentId;

    private Double creditScore;

    private String finalverdict;

    private String creRequestId;

    private String timestamp;

    private String tradepartnerGST;

    private String tradepartnerId;

    private Long invoiceAmount;

    private String invoiceId;

    private CBCREProcessDTO cbcreprocess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }

    public String getFinalverdict() {
        return finalverdict;
    }

    public void setFinalverdict(String finalverdict) {
        this.finalverdict = finalverdict;
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

    public String getTradepartnerGST() {
        return tradepartnerGST;
    }

    public void setTradepartnerGST(String tradepartnerGST) {
        this.tradepartnerGST = tradepartnerGST;
    }

    public String getTradepartnerId() {
        return tradepartnerId;
    }

    public void setTradepartnerId(String tradepartnerId) {
        this.tradepartnerId = tradepartnerId;
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
            ", assessmentId='" + getAssessmentId() + "'" +
            ", creditScore=" + getCreditScore() +
            ", finalverdict='" + getFinalverdict() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", tradepartnerGST='" + getTradepartnerGST() + "'" +
            ", tradepartnerId='" + getTradepartnerId() + "'" +
            ", invoiceAmount=" + getInvoiceAmount() +
            ", invoiceId='" + getInvoiceId() + "'" +
            ", cbcreprocess=" + getCbcreprocess() +
            "}";
    }
}
