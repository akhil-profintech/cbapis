package in.pft.apis.creditbazaar.gateway.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ProspectRequest.
 */
@Table("prospect_request")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProspectRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("prospect_request_id")
    private Long prospectRequestId;

    @Column("prospect_request_ulid_id")
    private String prospectRequestUlidId;

    @NotNull(message = "must not be null")
    @Column("anchor_trader_id")
    private Long anchorTraderId;

    @NotNull(message = "must not be null")
    @Column("request_amount")
    private String requestAmount;

    @NotNull(message = "must not be null")
    @Column("prospect_request_date")
    private LocalDate prospectRequestDate;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProspectRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProspectRequestId() {
        return this.prospectRequestId;
    }

    public ProspectRequest prospectRequestId(Long prospectRequestId) {
        this.setProspectRequestId(prospectRequestId);
        return this;
    }

    public void setProspectRequestId(Long prospectRequestId) {
        this.prospectRequestId = prospectRequestId;
    }

    public String getProspectRequestUlidId() {
        return this.prospectRequestUlidId;
    }

    public ProspectRequest prospectRequestUlidId(String prospectRequestUlidId) {
        this.setProspectRequestUlidId(prospectRequestUlidId);
        return this;
    }

    public void setProspectRequestUlidId(String prospectRequestUlidId) {
        this.prospectRequestUlidId = prospectRequestUlidId;
    }

    public Long getAnchorTraderId() {
        return this.anchorTraderId;
    }

    public ProspectRequest anchorTraderId(Long anchorTraderId) {
        this.setAnchorTraderId(anchorTraderId);
        return this;
    }

    public void setAnchorTraderId(Long anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getRequestAmount() {
        return this.requestAmount;
    }

    public ProspectRequest requestAmount(String requestAmount) {
        this.setRequestAmount(requestAmount);
        return this;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public LocalDate getProspectRequestDate() {
        return this.prospectRequestDate;
    }

    public ProspectRequest prospectRequestDate(LocalDate prospectRequestDate) {
        this.setProspectRequestDate(prospectRequestDate);
        return this;
    }

    public void setProspectRequestDate(LocalDate prospectRequestDate) {
        this.prospectRequestDate = prospectRequestDate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public ProspectRequest currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProspectRequest)) {
            return false;
        }
        return getId() != null && getId().equals(((ProspectRequest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProspectRequest{" +
            "id=" + getId() +
            ", prospectRequestId=" + getProspectRequestId() +
            ", prospectRequestUlidId='" + getProspectRequestUlidId() + "'" +
            ", anchorTraderId=" + getAnchorTraderId() +
            ", requestAmount='" + getRequestAmount() + "'" +
            ", prospectRequestDate='" + getProspectRequestDate() + "'" +
            ", currency='" + getCurrency() + "'" +
            "}";
    }
}
