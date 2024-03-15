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

    private String baseScore;

    private String ctin;

    private String invDate;

    private Long cbProcessId;

    private Boolean grnPresent;

    private Boolean einvoicePresent;

    private Boolean ewayBillPresent;

    private Boolean tradePartnerConfirmation;

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

    public String getBaseScore() {
        return baseScore;
    }

    public void setBaseScore(String baseScore) {
        this.baseScore = baseScore;
    }

    public String getCtin() {
        return ctin;
    }

    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    public Long getCbProcessId() {
        return cbProcessId;
    }

    public void setCbProcessId(Long cbProcessId) {
        this.cbProcessId = cbProcessId;
    }

    public Boolean getGrnPresent() {
        return grnPresent;
    }

    public void setGrnPresent(Boolean grnPresent) {
        this.grnPresent = grnPresent;
    }

    public Boolean getEinvoicePresent() {
        return einvoicePresent;
    }

    public void setEinvoicePresent(Boolean einvoicePresent) {
        this.einvoicePresent = einvoicePresent;
    }

    public Boolean getEwayBillPresent() {
        return ewayBillPresent;
    }

    public void setEwayBillPresent(Boolean ewayBillPresent) {
        this.ewayBillPresent = ewayBillPresent;
    }

    public Boolean getTradePartnerConfirmation() {
        return tradePartnerConfirmation;
    }

    public void setTradePartnerConfirmation(Boolean tradePartnerConfirmation) {
        this.tradePartnerConfirmation = tradePartnerConfirmation;
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
            ", baseScore='" + getBaseScore() + "'" +
            ", ctin='" + getCtin() + "'" +
            ", invDate='" + getInvDate() + "'" +
            ", cbProcessId=" + getCbProcessId() +
            ", grnPresent='" + getGrnPresent() + "'" +
            ", einvoicePresent='" + getEinvoicePresent() + "'" +
            ", ewayBillPresent='" + getEwayBillPresent() + "'" +
            ", tradePartnerConfirmation='" + getTradePartnerConfirmation() + "'" +
            ", cbcreprocess=" + getCbcreprocess() +
            "}";
    }
}
