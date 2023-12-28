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
    private String cbProcessId;

    @Column("finance_request_id")
    private String financeRequestId;

    @Column("anchor_trader_id")
    private String anchorTraderId;

    @Column("anchort_trader_gst")
    private String anchortTraderGst;

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

    @Column("cumilativetradescore")
    private Float cumilativetradescore;

    @Column("timestamp")
    private String timestamp;

    @Column("total_amount_requested")
    private Long totalAmountRequested;

    @Column("total_invoice_amount")
    private Long totalInvoiceAmount;

    @Transient
    @JsonIgnoreProperties(value = { "cbcreprocess", "individualassessment" }, allowSetters = true)
    private Set<CREHighlights> cREHighlights = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "cbcreprocess", "individualassessment" }, allowSetters = true)
    private Set<CREObservations> cREObservations = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "cbcreprocess" }, allowSetters = true)
    private Set<RequestOffer> requestOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "cbcreprocess" }, allowSetters = true)
    private Set<IndividualAssessment> individualAssessments = new HashSet<>();

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

    public String getCbProcessId() {
        return this.cbProcessId;
    }

    public CBCREProcess cbProcessId(String cbProcessId) {
        this.setCbProcessId(cbProcessId);
        return this;
    }

    public void setCbProcessId(String cbProcessId) {
        this.cbProcessId = cbProcessId;
    }

    public String getFinanceRequestId() {
        return this.financeRequestId;
    }

    public CBCREProcess financeRequestId(String financeRequestId) {
        this.setFinanceRequestId(financeRequestId);
        return this;
    }

    public void setFinanceRequestId(String financeRequestId) {
        this.financeRequestId = financeRequestId;
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

    public String getAnchortTraderGst() {
        return this.anchortTraderGst;
    }

    public CBCREProcess anchortTraderGst(String anchortTraderGst) {
        this.setAnchortTraderGst(anchortTraderGst);
        return this;
    }

    public void setAnchortTraderGst(String anchortTraderGst) {
        this.anchortTraderGst = anchortTraderGst;
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

    public Float getCumilativetradescore() {
        return this.cumilativetradescore;
    }

    public CBCREProcess cumilativetradescore(Float cumilativetradescore) {
        this.setCumilativetradescore(cumilativetradescore);
        return this;
    }

    public void setCumilativetradescore(Float cumilativetradescore) {
        this.cumilativetradescore = cumilativetradescore;
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

    public Set<CREHighlights> getCREHighlights() {
        return this.cREHighlights;
    }

    public void setCREHighlights(Set<CREHighlights> cREHighlights) {
        if (this.cREHighlights != null) {
            this.cREHighlights.forEach(i -> i.setCbcreprocess(null));
        }
        if (cREHighlights != null) {
            cREHighlights.forEach(i -> i.setCbcreprocess(this));
        }
        this.cREHighlights = cREHighlights;
    }

    public CBCREProcess cREHighlights(Set<CREHighlights> cREHighlights) {
        this.setCREHighlights(cREHighlights);
        return this;
    }

    public CBCREProcess addCREHighlights(CREHighlights cREHighlights) {
        this.cREHighlights.add(cREHighlights);
        cREHighlights.setCbcreprocess(this);
        return this;
    }

    public CBCREProcess removeCREHighlights(CREHighlights cREHighlights) {
        this.cREHighlights.remove(cREHighlights);
        cREHighlights.setCbcreprocess(null);
        return this;
    }

    public Set<CREObservations> getCREObservations() {
        return this.cREObservations;
    }

    public void setCREObservations(Set<CREObservations> cREObservations) {
        if (this.cREObservations != null) {
            this.cREObservations.forEach(i -> i.setCbcreprocess(null));
        }
        if (cREObservations != null) {
            cREObservations.forEach(i -> i.setCbcreprocess(this));
        }
        this.cREObservations = cREObservations;
    }

    public CBCREProcess cREObservations(Set<CREObservations> cREObservations) {
        this.setCREObservations(cREObservations);
        return this;
    }

    public CBCREProcess addCREObservations(CREObservations cREObservations) {
        this.cREObservations.add(cREObservations);
        cREObservations.setCbcreprocess(this);
        return this;
    }

    public CBCREProcess removeCREObservations(CREObservations cREObservations) {
        this.cREObservations.remove(cREObservations);
        cREObservations.setCbcreprocess(null);
        return this;
    }

    public Set<RequestOffer> getRequestOffers() {
        return this.requestOffers;
    }

    public void setRequestOffers(Set<RequestOffer> requestOffers) {
        if (this.requestOffers != null) {
            this.requestOffers.forEach(i -> i.setCbcreprocess(null));
        }
        if (requestOffers != null) {
            requestOffers.forEach(i -> i.setCbcreprocess(this));
        }
        this.requestOffers = requestOffers;
    }

    public CBCREProcess requestOffers(Set<RequestOffer> requestOffers) {
        this.setRequestOffers(requestOffers);
        return this;
    }

    public CBCREProcess addRequestOffer(RequestOffer requestOffer) {
        this.requestOffers.add(requestOffer);
        requestOffer.setCbcreprocess(this);
        return this;
    }

    public CBCREProcess removeRequestOffer(RequestOffer requestOffer) {
        this.requestOffers.remove(requestOffer);
        requestOffer.setCbcreprocess(null);
        return this;
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
            ", cbProcessId='" + getCbProcessId() + "'" +
            ", financeRequestId='" + getFinanceRequestId() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", anchortTraderGst='" + getAnchortTraderGst() + "'" +
            ", financeAmount='" + getFinanceAmount() + "'" +
            ", processDateTime='" + getProcessDateTime() + "'" +
            ", creProcessStatus='" + getCreProcessStatus() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", cumilativetradescore=" + getCumilativetradescore() +
            ", timestamp='" + getTimestamp() + "'" +
            ", totalAmountRequested=" + getTotalAmountRequested() +
            ", totalInvoiceAmount=" + getTotalInvoiceAmount() +
            "}";
    }
}
