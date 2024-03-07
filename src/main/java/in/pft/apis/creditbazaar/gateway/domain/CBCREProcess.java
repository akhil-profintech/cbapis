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
 * A CBCREProcess.
 */
@Table("cbcre_process")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CBCREProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("cb_process_id")
    private Long cbProcessId;

    @Column("cb_process_ulid_id")
    private String cbProcessUlidId;

    @Column("cb_process_ref_no")
    private String cbProcessRefNo;

    @Column("anchor_trader_id")
    private String anchorTraderId;

    @Column("anchor_trader_gst")
    private String anchorTraderGst;

    @Column("finance_amount")
    private String financeAmount;

    @Column("process_date_time")
    private String processDateTime;

    @Column("cre_process_status")
    private String creProcessStatus;

    @Column("response_date_time")
    private String responseDateTime;

    @Column("cre_request_id")
    private String creRequestId;

    @Column("cumilative_trade_score")
    private Float cumilativeTradeScore;

    @Column("timestamp")
    private String timestamp;

    @Column("total_amount_requested")
    private Long totalAmountRequested;

    @Column("total_invoice_amount")
    private Long totalInvoiceAmount;

    @Transient
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "cbcreprocess" }, allowSetters = true)
    private Set<IndividualAssessment> individualAssessments = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(
        value = {
            "requestOffers",
            "trades",
            "placedOffers",
            "acceptedOffers",
            "disbursements",
            "docDetails",
            "repayments",
            "settlements",
            "cBCREProcesses",
            "anchortrader",
        },
        allowSetters = true
    )
    private FinanceRequest financeRequest;

    @Column("finance_request_id")
    private Long financeRequestId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CBCREProcess id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCbProcessId() {
        return this.cbProcessId;
    }

    public CBCREProcess cbProcessId(Long cbProcessId) {
        this.setCbProcessId(cbProcessId);
        return this;
    }

    public void setCbProcessId(Long cbProcessId) {
        this.cbProcessId = cbProcessId;
    }

    public String getCbProcessUlidId() {
        return this.cbProcessUlidId;
    }

    public CBCREProcess cbProcessUlidId(String cbProcessUlidId) {
        this.setCbProcessUlidId(cbProcessUlidId);
        return this;
    }

    public void setCbProcessUlidId(String cbProcessUlidId) {
        this.cbProcessUlidId = cbProcessUlidId;
    }

    public String getCbProcessRefNo() {
        return this.cbProcessRefNo;
    }

    public CBCREProcess cbProcessRefNo(String cbProcessRefNo) {
        this.setCbProcessRefNo(cbProcessRefNo);
        return this;
    }

    public void setCbProcessRefNo(String cbProcessRefNo) {
        this.cbProcessRefNo = cbProcessRefNo;
    }

    public String getAnchorTraderId() {
        return this.anchorTraderId;
    }

    public CBCREProcess anchorTraderId(String anchorTraderId) {
        this.setAnchorTraderId(anchorTraderId);
        return this;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getAnchorTraderGst() {
        return this.anchorTraderGst;
    }

    public CBCREProcess anchorTraderGst(String anchorTraderGst) {
        this.setAnchorTraderGst(anchorTraderGst);
        return this;
    }

    public void setAnchorTraderGst(String anchorTraderGst) {
        this.anchorTraderGst = anchorTraderGst;
    }

    public String getFinanceAmount() {
        return this.financeAmount;
    }

    public CBCREProcess financeAmount(String financeAmount) {
        this.setFinanceAmount(financeAmount);
        return this;
    }

    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getProcessDateTime() {
        return this.processDateTime;
    }

    public CBCREProcess processDateTime(String processDateTime) {
        this.setProcessDateTime(processDateTime);
        return this;
    }

    public void setProcessDateTime(String processDateTime) {
        this.processDateTime = processDateTime;
    }

    public String getCreProcessStatus() {
        return this.creProcessStatus;
    }

    public CBCREProcess creProcessStatus(String creProcessStatus) {
        this.setCreProcessStatus(creProcessStatus);
        return this;
    }

    public void setCreProcessStatus(String creProcessStatus) {
        this.creProcessStatus = creProcessStatus;
    }

    public String getResponseDateTime() {
        return this.responseDateTime;
    }

    public CBCREProcess responseDateTime(String responseDateTime) {
        this.setResponseDateTime(responseDateTime);
        return this;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getCreRequestId() {
        return this.creRequestId;
    }

    public CBCREProcess creRequestId(String creRequestId) {
        this.setCreRequestId(creRequestId);
        return this;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public Float getCumilativeTradeScore() {
        return this.cumilativeTradeScore;
    }

    public CBCREProcess cumilativeTradeScore(Float cumilativeTradeScore) {
        this.setCumilativeTradeScore(cumilativeTradeScore);
        return this;
    }

    public void setCumilativeTradeScore(Float cumilativeTradeScore) {
        this.cumilativeTradeScore = cumilativeTradeScore;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public CBCREProcess timestamp(String timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTotalAmountRequested() {
        return this.totalAmountRequested;
    }

    public CBCREProcess totalAmountRequested(Long totalAmountRequested) {
        this.setTotalAmountRequested(totalAmountRequested);
        return this;
    }

    public void setTotalAmountRequested(Long totalAmountRequested) {
        this.totalAmountRequested = totalAmountRequested;
    }

    public Long getTotalInvoiceAmount() {
        return this.totalInvoiceAmount;
    }

    public CBCREProcess totalInvoiceAmount(Long totalInvoiceAmount) {
        this.setTotalInvoiceAmount(totalInvoiceAmount);
        return this;
    }

    public void setTotalInvoiceAmount(Long totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public Set<IndividualAssessment> getIndividualAssessments() {
        return this.individualAssessments;
    }

    public void setIndividualAssessments(Set<IndividualAssessment> individualAssessments) {
        if (this.individualAssessments != null) {
            this.individualAssessments.forEach(i -> i.setCbcreprocess(null));
        }
        if (individualAssessments != null) {
            individualAssessments.forEach(i -> i.setCbcreprocess(this));
        }
        this.individualAssessments = individualAssessments;
    }

    public CBCREProcess individualAssessments(Set<IndividualAssessment> individualAssessments) {
        this.setIndividualAssessments(individualAssessments);
        return this;
    }

    public CBCREProcess addIndividualAssessment(IndividualAssessment individualAssessment) {
        this.individualAssessments.add(individualAssessment);
        individualAssessment.setCbcreprocess(this);
        return this;
    }

    public CBCREProcess removeIndividualAssessment(IndividualAssessment individualAssessment) {
        this.individualAssessments.remove(individualAssessment);
        individualAssessment.setCbcreprocess(null);
        return this;
    }

    public FinanceRequest getFinanceRequest() {
        return this.financeRequest;
    }

    public void setFinanceRequest(FinanceRequest financeRequest) {
        this.financeRequest = financeRequest;
        this.financeRequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public CBCREProcess financeRequest(FinanceRequest financeRequest) {
        this.setFinanceRequest(financeRequest);
        return this;
    }

    public Long getFinanceRequestId() {
        return this.financeRequestId;
    }

    public void setFinanceRequestId(Long financeRequest) {
        this.financeRequestId = financeRequest;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CBCREProcess)) {
            return false;
        }
        return getId() != null && getId().equals(((CBCREProcess) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CBCREProcess{" +
            "id=" + getId() +
            ", cbProcessId=" + getCbProcessId() +
            ", cbProcessUlidId='" + getCbProcessUlidId() + "'" +
            ", cbProcessRefNo='" + getCbProcessRefNo() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", anchorTraderGst='" + getAnchorTraderGst() + "'" +
            ", financeAmount='" + getFinanceAmount() + "'" +
            ", processDateTime='" + getProcessDateTime() + "'" +
            ", creProcessStatus='" + getCreProcessStatus() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", cumilativeTradeScore=" + getCumilativeTradeScore() +
            ", timestamp='" + getTimestamp() + "'" +
            ", totalAmountRequested=" + getTotalAmountRequested() +
            ", totalInvoiceAmount=" + getTotalInvoiceAmount() +
            "}";
    }
}
