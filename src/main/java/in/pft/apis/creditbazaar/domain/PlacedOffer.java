package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A PlacedOffer.
 */
@Table("placed_offer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PlacedOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("req_off_id")
    private String reqOffId;

    @Column("placed_offer_ref_no")
    private String placedOfferRefNo;

    @NotNull(message = "must not be null")
    @Column("value")
    private Long value;

    @NotNull(message = "must not be null")
    @Column("req_amount")
    private Long reqAmount;

    @NotNull(message = "must not be null")
    @Column("margin_ptg")
    private Float marginPtg;

    @NotNull(message = "must not be null")
    @Column("margin_value")
    private Long marginValue;

    @NotNull(message = "must not be null")
    @Column("amount_aft_margin")
    private Long amountAftMargin;

    @NotNull(message = "must not be null")
    @Column("interest_ptg")
    private Float interestPtg;

    @NotNull(message = "must not be null")
    @Column("term")
    private Long term;

    @NotNull(message = "must not be null")
    @Column("interest_value")
    private Long interestValue;

    @NotNull(message = "must not be null")
    @Column("net_amount")
    private Long netAmount;

    @Column("status")
    private String status;

    @NotNull(message = "must not be null")
    @Column("offer_date")
    private LocalDate offerDate;

    @Column("request_id")
    private String requestId;

    @Column("placed_offer_date")
    private LocalDate placedOfferDate;

    @Column("anchor_trader")
    private String anchorTrader;

    @Column("trade_partner")
    private String tradePartner;

    @Column("disbursal_amount")
    private String disbursalAmount;

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

    @Transient
    @JsonIgnoreProperties(value = { "disbursements", "placedOffers", "acceptedOffers" }, allowSetters = true)
    private FinancePartner financepartner;

    @Column("financerequest_id")
    private Long financerequestId;

    @Column("financepartner_id")
    private Long financepartnerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PlacedOffer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqOffId() {
        return this.reqOffId;
    }

    public PlacedOffer reqOffId(String reqOffId) {
        this.setReqOffId(reqOffId);
        return this;
    }

    public void setReqOffId(String reqOffId) {
        this.reqOffId = reqOffId;
    }

    public String getPlacedOfferRefNo() {
        return this.placedOfferRefNo;
    }

    public PlacedOffer placedOfferRefNo(String placedOfferRefNo) {
        this.setPlacedOfferRefNo(placedOfferRefNo);
        return this;
    }

    public void setPlacedOfferRefNo(String placedOfferRefNo) {
        this.placedOfferRefNo = placedOfferRefNo;
    }

    public Long getValue() {
        return this.value;
    }

    public PlacedOffer value(Long value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getReqAmount() {
        return this.reqAmount;
    }

    public PlacedOffer reqAmount(Long reqAmount) {
        this.setReqAmount(reqAmount);
        return this;
    }

    public void setReqAmount(Long reqAmount) {
        this.reqAmount = reqAmount;
    }

    public Float getMarginPtg() {
        return this.marginPtg;
    }

    public PlacedOffer marginPtg(Float marginPtg) {
        this.setMarginPtg(marginPtg);
        return this;
    }

    public void setMarginPtg(Float marginPtg) {
        this.marginPtg = marginPtg;
    }

    public Long getMarginValue() {
        return this.marginValue;
    }

    public PlacedOffer marginValue(Long marginValue) {
        this.setMarginValue(marginValue);
        return this;
    }

    public void setMarginValue(Long marginValue) {
        this.marginValue = marginValue;
    }

    public Long getAmountAftMargin() {
        return this.amountAftMargin;
    }

    public PlacedOffer amountAftMargin(Long amountAftMargin) {
        this.setAmountAftMargin(amountAftMargin);
        return this;
    }

    public void setAmountAftMargin(Long amountAftMargin) {
        this.amountAftMargin = amountAftMargin;
    }

    public Float getInterestPtg() {
        return this.interestPtg;
    }

    public PlacedOffer interestPtg(Float interestPtg) {
        this.setInterestPtg(interestPtg);
        return this;
    }

    public void setInterestPtg(Float interestPtg) {
        this.interestPtg = interestPtg;
    }

    public Long getTerm() {
        return this.term;
    }

    public PlacedOffer term(Long term) {
        this.setTerm(term);
        return this;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public Long getInterestValue() {
        return this.interestValue;
    }

    public PlacedOffer interestValue(Long interestValue) {
        this.setInterestValue(interestValue);
        return this;
    }

    public void setInterestValue(Long interestValue) {
        this.interestValue = interestValue;
    }

    public Long getNetAmount() {
        return this.netAmount;
    }

    public PlacedOffer netAmount(Long netAmount) {
        this.setNetAmount(netAmount);
        return this;
    }

    public void setNetAmount(Long netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public PlacedOffer status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOfferDate() {
        return this.offerDate;
    }

    public PlacedOffer offerDate(LocalDate offerDate) {
        this.setOfferDate(offerDate);
        return this;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public PlacedOffer requestId(String requestId) {
        this.setRequestId(requestId);
        return this;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public LocalDate getPlacedOfferDate() {
        return this.placedOfferDate;
    }

    public PlacedOffer placedOfferDate(LocalDate placedOfferDate) {
        this.setPlacedOfferDate(placedOfferDate);
        return this;
    }

    public void setPlacedOfferDate(LocalDate placedOfferDate) {
        this.placedOfferDate = placedOfferDate;
    }

    public String getAnchorTrader() {
        return this.anchorTrader;
    }

    public PlacedOffer anchorTrader(String anchorTrader) {
        this.setAnchorTrader(anchorTrader);
        return this;
    }

    public void setAnchorTrader(String anchorTrader) {
        this.anchorTrader = anchorTrader;
    }

    public String getTradePartner() {
        return this.tradePartner;
    }

    public PlacedOffer tradePartner(String tradePartner) {
        this.setTradePartner(tradePartner);
        return this;
    }

    public void setTradePartner(String tradePartner) {
        this.tradePartner = tradePartner;
    }

    public String getDisbursalAmount() {
        return this.disbursalAmount;
    }

    public PlacedOffer disbursalAmount(String disbursalAmount) {
        this.setDisbursalAmount(disbursalAmount);
        return this;
    }

    public void setDisbursalAmount(String disbursalAmount) {
        this.disbursalAmount = disbursalAmount;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public PlacedOffer financerequest(FinanceRequest financeRequest) {
        this.setFinancerequest(financeRequest);
        return this;
    }

    public FinancePartner getFinancepartner() {
        return this.financepartner;
    }

    public void setFinancepartner(FinancePartner financePartner) {
        this.financepartner = financePartner;
        this.financepartnerId = financePartner != null ? financePartner.getId() : null;
    }

    public PlacedOffer financepartner(FinancePartner financePartner) {
        this.setFinancepartner(financePartner);
        return this;
    }

    public Long getFinancerequestId() {
        return this.financerequestId;
    }

    public void setFinancerequestId(Long financeRequest) {
        this.financerequestId = financeRequest;
    }

    public Long getFinancepartnerId() {
        return this.financepartnerId;
    }

    public void setFinancepartnerId(Long financePartner) {
        this.financepartnerId = financePartner;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlacedOffer)) {
            return false;
        }
        return getId() != null && getId().equals(((PlacedOffer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlacedOffer{" +
            "id=" + getId() +
            ", reqOffId='" + getReqOffId() + "'" +
            ", placedOfferRefNo='" + getPlacedOfferRefNo() + "'" +
            ", value=" + getValue() +
            ", reqAmount=" + getReqAmount() +
            ", marginPtg=" + getMarginPtg() +
            ", marginValue=" + getMarginValue() +
            ", amountAftMargin=" + getAmountAftMargin() +
            ", interestPtg=" + getInterestPtg() +
            ", term=" + getTerm() +
            ", interestValue=" + getInterestValue() +
            ", netAmount=" + getNetAmount() +
            ", status='" + getStatus() + "'" +
            ", offerDate='" + getOfferDate() + "'" +
            ", requestId='" + getRequestId() + "'" +
            ", placedOfferDate='" + getPlacedOfferDate() + "'" +
            ", anchorTrader='" + getAnchorTrader() + "'" +
            ", tradePartner='" + getTradePartner() + "'" +
            ", disbursalAmount='" + getDisbursalAmount() + "'" +
            "}";
    }
}
