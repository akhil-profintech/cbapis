package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Settlement.
 */
@Table("settlement")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Settlement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("settlement_id")
    private String settlementId;

    @Column("settlement_ref_no")
    private String settlementRefNo;

    @NotNull(message = "must not be null")
    @Column("offer_id")
    private Long offerId;

    @NotNull(message = "must not be null")
    @Column("dbmt_request_id")
    private Long dbmtRequestId;

    @NotNull(message = "must not be null")
    @Column("dbmt_id")
    private Long dbmtId;

    @NotNull(message = "must not be null")
    @Column("dbmt_date")
    private String dbmtDate;

    @NotNull(message = "must not be null")
    @Column("dbmt_status")
    private String dbmtStatus;

    @NotNull(message = "must not be null")
    @Column("dbmt_amount")
    private Long dbmtAmount;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @Transient
    @JsonIgnoreProperties(value = { "fTTransactionDetails", "docDetails", "settlement" }, allowSetters = true)
    private Set<ParticipantSettlement> participantSettlements = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(
        value = {
            "repayments",
            "requestOffers",
            "disbursements",
            "prospectRequests",
            "trades",
            "placedOffers",
            "acceptedOffers",
            "settlements",
            "anchortrader",
        },
        allowSetters = true
    )
    private FinanceRequest financerequest;

    @Column("financerequest_id")
    private Long financerequestId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Settlement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettlementId() {
        return this.settlementId;
    }

    public Settlement settlementId(String settlementId) {
        this.setSettlementId(settlementId);
        return this;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String getSettlementRefNo() {
        return this.settlementRefNo;
    }

    public Settlement settlementRefNo(String settlementRefNo) {
        this.setSettlementRefNo(settlementRefNo);
        return this;
    }

    public void setSettlementRefNo(String settlementRefNo) {
        this.settlementRefNo = settlementRefNo;
    }

    public Long getOfferId() {
        return this.offerId;
    }

    public Settlement offerId(Long offerId) {
        this.setOfferId(offerId);
        return this;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getDbmtRequestId() {
        return this.dbmtRequestId;
    }

    public Settlement dbmtRequestId(Long dbmtRequestId) {
        this.setDbmtRequestId(dbmtRequestId);
        return this;
    }

    public void setDbmtRequestId(Long dbmtRequestId) {
        this.dbmtRequestId = dbmtRequestId;
    }

    public Long getDbmtId() {
        return this.dbmtId;
    }

    public Settlement dbmtId(Long dbmtId) {
        this.setDbmtId(dbmtId);
        return this;
    }

    public void setDbmtId(Long dbmtId) {
        this.dbmtId = dbmtId;
    }

    public String getDbmtDate() {
        return this.dbmtDate;
    }

    public Settlement dbmtDate(String dbmtDate) {
        this.setDbmtDate(dbmtDate);
        return this;
    }

    public void setDbmtDate(String dbmtDate) {
        this.dbmtDate = dbmtDate;
    }

    public String getDbmtStatus() {
        return this.dbmtStatus;
    }

    public Settlement dbmtStatus(String dbmtStatus) {
        this.setDbmtStatus(dbmtStatus);
        return this;
    }

    public void setDbmtStatus(String dbmtStatus) {
        this.dbmtStatus = dbmtStatus;
    }

    public Long getDbmtAmount() {
        return this.dbmtAmount;
    }

    public Settlement dbmtAmount(Long dbmtAmount) {
        this.setDbmtAmount(dbmtAmount);
        return this;
    }

    public void setDbmtAmount(Long dbmtAmount) {
        this.dbmtAmount = dbmtAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Settlement currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Set<ParticipantSettlement> getParticipantSettlements() {
        return this.participantSettlements;
    }

    public void setParticipantSettlements(Set<ParticipantSettlement> participantSettlements) {
        if (this.participantSettlements != null) {
            this.participantSettlements.forEach(i -> i.setSettlement(null));
        }
        if (participantSettlements != null) {
            participantSettlements.forEach(i -> i.setSettlement(this));
        }
        this.participantSettlements = participantSettlements;
    }

    public Settlement participantSettlements(Set<ParticipantSettlement> participantSettlements) {
        this.setParticipantSettlements(participantSettlements);
        return this;
    }

    public Settlement addParticipantSettlement(ParticipantSettlement participantSettlement) {
        this.participantSettlements.add(participantSettlement);
        participantSettlement.setSettlement(this);
        return this;
    }

    public Settlement removeParticipantSettlement(ParticipantSettlement participantSettlement) {
        this.participantSettlements.remove(participantSettlement);
        participantSettlement.setSettlement(null);
        return this;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public Settlement financerequest(FinanceRequest financeRequest) {
        this.setFinancerequest(financeRequest);
        return this;
    }

    public Long getFinancerequestId() {
        return this.financerequestId;
    }

    public void setFinancerequestId(Long financeRequest) {
        this.financerequestId = financeRequest;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Settlement)) {
            return false;
        }
        return getId() != null && getId().equals(((Settlement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Settlement{" +
            "id=" + getId() +
            ", settlementId='" + getSettlementId() + "'" +
            ", settlementRefNo='" + getSettlementRefNo() + "'" +
            ", offerId=" + getOfferId() +
            ", dbmtRequestId=" + getDbmtRequestId() +
            ", dbmtId=" + getDbmtId() +
            ", dbmtDate='" + getDbmtDate() + "'" +
            ", dbmtStatus='" + getDbmtStatus() + "'" +
            ", dbmtAmount=" + getDbmtAmount() +
            ", currency='" + getCurrency() + "'" +
            "}";
    }
}
