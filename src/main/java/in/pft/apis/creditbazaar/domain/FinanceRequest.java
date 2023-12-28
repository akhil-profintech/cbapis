package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A FinanceRequest.
 */
@Table("finance_request")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinanceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("request_id")
    private String requestId;

    @Column("finance_request_ref_no")
    private String financeRequestRefNo;

    @NotNull(message = "must not be null")
    @Column("request_amount")
    private String requestAmount;

    @NotNull(message = "must not be null")
    @Column("request_date")
    private LocalDate requestDate;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @NotNull(message = "must not be null")
    @Column("request_status")
    private String requestStatus;

    @NotNull(message = "must not be null")
    @Column("due_date")
    private LocalDate dueDate;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "creditAccountDetails",
            "escrowAccountDetails",
            "docDetails",
            "fTTransactionDetails",
            "collectionTransactionDetails",
            "financerequest",
        },
        allowSetters = true
    )
    private Set<Repayment> repayments = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "cbcreprocess" }, allowSetters = true)
    private Set<RequestOffer> requestOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(
        value = {
            "creditAccountDetails",
            "escrowAccountDetails",
            "docDetails",
            "fTTransactionDetails",
            "collectionTransactionDetails",
            "financerequest",
            "financepartner",
        },
        allowSetters = true
    )
    private Set<Disbursement> disbursements = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest" }, allowSetters = true)
    private Set<ProspectRequest> prospectRequests = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "tradepartner", "anchortrader" }, allowSetters = true)
    private Set<Trade> trades = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "financepartner" }, allowSetters = true)
    private Set<PlacedOffer> placedOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "financepartner", "anchortrader" }, allowSetters = true)
    private Set<AcceptedOffer> acceptedOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "participantSettlements", "financerequest" }, allowSetters = true)
    private Set<Settlement> settlements = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financeRequests", "acceptedOffers", "trades" }, allowSetters = true)
    private AnchorTrader anchortrader;

    @Column("anchortrader_id")
    private Long anchortraderId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FinanceRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public FinanceRequest requestId(String requestId) {
        this.setRequestId(requestId);
        return this;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getFinanceRequestRefNo() {
        return this.financeRequestRefNo;
    }

    public FinanceRequest financeRequestRefNo(String financeRequestRefNo) {
        this.setFinanceRequestRefNo(financeRequestRefNo);
        return this;
    }

    public void setFinanceRequestRefNo(String financeRequestRefNo) {
        this.financeRequestRefNo = financeRequestRefNo;
    }

    public String getRequestAmount() {
        return this.requestAmount;
    }

    public FinanceRequest requestAmount(String requestAmount) {
        this.setRequestAmount(requestAmount);
        return this;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public LocalDate getRequestDate() {
        return this.requestDate;
    }

    public FinanceRequest requestDate(LocalDate requestDate) {
        this.setRequestDate(requestDate);
        return this;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public FinanceRequest currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public FinanceRequest requestStatus(String requestStatus) {
        this.setRequestStatus(requestStatus);
        return this;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public FinanceRequest dueDate(LocalDate dueDate) {
        this.setDueDate(dueDate);
        return this;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Set<Repayment> getRepayments() {
        return this.repayments;
    }

    public void setRepayments(Set<Repayment> repayments) {
        if (this.repayments != null) {
            this.repayments.forEach(i -> i.setFinancerequest(null));
        }
        if (repayments != null) {
            repayments.forEach(i -> i.setFinancerequest(this));
        }
        this.repayments = repayments;
    }

    public FinanceRequest repayments(Set<Repayment> repayments) {
        this.setRepayments(repayments);
        return this;
    }

    public FinanceRequest addRepayment(Repayment repayment) {
        this.repayments.add(repayment);
        repayment.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeRepayment(Repayment repayment) {
        this.repayments.remove(repayment);
        repayment.setFinancerequest(null);
        return this;
    }

    public Set<RequestOffer> getRequestOffers() {
        return this.requestOffers;
    }

    public void setRequestOffers(Set<RequestOffer> requestOffers) {
        if (this.requestOffers != null) {
            this.requestOffers.forEach(i -> i.setFinancerequest(null));
        }
        if (requestOffers != null) {
            requestOffers.forEach(i -> i.setFinancerequest(this));
        }
        this.requestOffers = requestOffers;
    }

    public FinanceRequest requestOffers(Set<RequestOffer> requestOffers) {
        this.setRequestOffers(requestOffers);
        return this;
    }

    public FinanceRequest addRequestOffer(RequestOffer requestOffer) {
        this.requestOffers.add(requestOffer);
        requestOffer.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeRequestOffer(RequestOffer requestOffer) {
        this.requestOffers.remove(requestOffer);
        requestOffer.setFinancerequest(null);
        return this;
    }

    public Set<Disbursement> getDisbursements() {
        return this.disbursements;
    }

    public void setDisbursements(Set<Disbursement> disbursements) {
        if (this.disbursements != null) {
            this.disbursements.forEach(i -> i.setFinancerequest(null));
        }
        if (disbursements != null) {
            disbursements.forEach(i -> i.setFinancerequest(this));
        }
        this.disbursements = disbursements;
    }

    public FinanceRequest disbursements(Set<Disbursement> disbursements) {
        this.setDisbursements(disbursements);
        return this;
    }

    public FinanceRequest addDisbursement(Disbursement disbursement) {
        this.disbursements.add(disbursement);
        disbursement.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeDisbursement(Disbursement disbursement) {
        this.disbursements.remove(disbursement);
        disbursement.setFinancerequest(null);
        return this;
    }

    public Set<ProspectRequest> getProspectRequests() {
        return this.prospectRequests;
    }

    public void setProspectRequests(Set<ProspectRequest> prospectRequests) {
        if (this.prospectRequests != null) {
            this.prospectRequests.forEach(i -> i.setFinancerequest(null));
        }
        if (prospectRequests != null) {
            prospectRequests.forEach(i -> i.setFinancerequest(this));
        }
        this.prospectRequests = prospectRequests;
    }

    public FinanceRequest prospectRequests(Set<ProspectRequest> prospectRequests) {
        this.setProspectRequests(prospectRequests);
        return this;
    }

    public FinanceRequest addProspectRequest(ProspectRequest prospectRequest) {
        this.prospectRequests.add(prospectRequest);
        prospectRequest.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeProspectRequest(ProspectRequest prospectRequest) {
        this.prospectRequests.remove(prospectRequest);
        prospectRequest.setFinancerequest(null);
        return this;
    }

    public Set<Trade> getTrades() {
        return this.trades;
    }

    public void setTrades(Set<Trade> trades) {
        if (this.trades != null) {
            this.trades.forEach(i -> i.setFinancerequest(null));
        }
        if (trades != null) {
            trades.forEach(i -> i.setFinancerequest(this));
        }
        this.trades = trades;
    }

    public FinanceRequest trades(Set<Trade> trades) {
        this.setTrades(trades);
        return this;
    }

    public FinanceRequest addTrade(Trade trade) {
        this.trades.add(trade);
        trade.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeTrade(Trade trade) {
        this.trades.remove(trade);
        trade.setFinancerequest(null);
        return this;
    }

    public Set<PlacedOffer> getPlacedOffers() {
        return this.placedOffers;
    }

    public void setPlacedOffers(Set<PlacedOffer> placedOffers) {
        if (this.placedOffers != null) {
            this.placedOffers.forEach(i -> i.setFinancerequest(null));
        }
        if (placedOffers != null) {
            placedOffers.forEach(i -> i.setFinancerequest(this));
        }
        this.placedOffers = placedOffers;
    }

    public FinanceRequest placedOffers(Set<PlacedOffer> placedOffers) {
        this.setPlacedOffers(placedOffers);
        return this;
    }

    public FinanceRequest addPlacedOffer(PlacedOffer placedOffer) {
        this.placedOffers.add(placedOffer);
        placedOffer.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removePlacedOffer(PlacedOffer placedOffer) {
        this.placedOffers.remove(placedOffer);
        placedOffer.setFinancerequest(null);
        return this;
    }

    public Set<AcceptedOffer> getAcceptedOffers() {
        return this.acceptedOffers;
    }

    public void setAcceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        if (this.acceptedOffers != null) {
            this.acceptedOffers.forEach(i -> i.setFinancerequest(null));
        }
        if (acceptedOffers != null) {
            acceptedOffers.forEach(i -> i.setFinancerequest(this));
        }
        this.acceptedOffers = acceptedOffers;
    }

    public FinanceRequest acceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        this.setAcceptedOffers(acceptedOffers);
        return this;
    }

    public FinanceRequest addAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.add(acceptedOffer);
        acceptedOffer.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.remove(acceptedOffer);
        acceptedOffer.setFinancerequest(null);
        return this;
    }

    public Set<Settlement> getSettlements() {
        return this.settlements;
    }

    public void setSettlements(Set<Settlement> settlements) {
        if (this.settlements != null) {
            this.settlements.forEach(i -> i.setFinancerequest(null));
        }
        if (settlements != null) {
            settlements.forEach(i -> i.setFinancerequest(this));
        }
        this.settlements = settlements;
    }

    public FinanceRequest settlements(Set<Settlement> settlements) {
        this.setSettlements(settlements);
        return this;
    }

    public FinanceRequest addSettlement(Settlement settlement) {
        this.settlements.add(settlement);
        settlement.setFinancerequest(this);
        return this;
    }

    public FinanceRequest removeSettlement(Settlement settlement) {
        this.settlements.remove(settlement);
        settlement.setFinancerequest(null);
        return this;
    }

    public AnchorTrader getAnchortrader() {
        return this.anchortrader;
    }

    public void setAnchortrader(AnchorTrader anchorTrader) {
        this.anchortrader = anchorTrader;
        this.anchortraderId = anchorTrader != null ? anchorTrader.getId() : null;
    }

    public FinanceRequest anchortrader(AnchorTrader anchorTrader) {
        this.setAnchortrader(anchorTrader);
        return this;
    }

    public Long getAnchortraderId() {
        return this.anchortraderId;
    }

    public void setAnchortraderId(Long anchorTrader) {
        this.anchortraderId = anchorTrader;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinanceRequest)) {
            return false;
        }
        return getId() != null && getId().equals(((FinanceRequest) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinanceRequest{" +
            "id=" + getId() +
            ", requestId='" + getRequestId() + "'" +
            ", financeRequestRefNo='" + getFinanceRequestRefNo() + "'" +
            ", requestAmount='" + getRequestAmount() + "'" +
            ", requestDate='" + getRequestDate() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            "}";
    }
}
