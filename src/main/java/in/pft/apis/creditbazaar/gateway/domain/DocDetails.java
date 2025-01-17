package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A DocDetails.
 */
@Table("doc_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("doc_details_id")
    private Long docDetailsId;

    @Column("doc_details_ulid_id")
    private String docDetailsUlidId;

    @NotNull(message = "must not be null")
    @Column("doc_record_id")
    private Long docRecordId;

    @NotNull(message = "must not be null")
    @Column("link")
    private String link;

    @NotNull(message = "must not be null")
    @Column("description")
    private String description;

    @NotNull(message = "must not be null")
    @Column("doc_type")
    private String docType;

    @NotNull(message = "must not be null")
    @Column("status")
    private String status;

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

    public DocDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocDetailsId() {
        return this.docDetailsId;
    }

    public DocDetails docDetailsId(Long docDetailsId) {
        this.setDocDetailsId(docDetailsId);
        return this;
    }

    public void setDocDetailsId(Long docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

    public String getDocDetailsUlidId() {
        return this.docDetailsUlidId;
    }

    public DocDetails docDetailsUlidId(String docDetailsUlidId) {
        this.setDocDetailsUlidId(docDetailsUlidId);
        return this;
    }

    public void setDocDetailsUlidId(String docDetailsUlidId) {
        this.docDetailsUlidId = docDetailsUlidId;
    }

    public Long getDocRecordId() {
        return this.docRecordId;
    }

    public DocDetails docRecordId(Long docRecordId) {
        this.setDocRecordId(docRecordId);
        return this;
    }

    public void setDocRecordId(Long docRecordId) {
        this.docRecordId = docRecordId;
    }

    public String getLink() {
        return this.link;
    }

    public DocDetails link(String link) {
        this.setLink(link);
        return this;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return this.description;
    }

    public DocDetails description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocType() {
        return this.docType;
    }

    public DocDetails docType(String docType) {
        this.setDocType(docType);
        return this;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getStatus() {
        return this.status;
    }

    public DocDetails status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FinanceRequest getFinanceRequest() {
        return this.financeRequest;
    }

    public void setFinanceRequest(FinanceRequest financeRequest) {
        this.financeRequest = financeRequest;
        this.financeRequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public DocDetails financeRequest(FinanceRequest financeRequest) {
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
        if (!(o instanceof DocDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((DocDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocDetails{" +
            "id=" + getId() +
            ", docDetailsId=" + getDocDetailsId() +
            ", docDetailsUlidId='" + getDocDetailsUlidId() + "'" +
            ", docRecordId=" + getDocRecordId() +
            ", link='" + getLink() + "'" +
            ", description='" + getDescription() + "'" +
            ", docType='" + getDocType() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
