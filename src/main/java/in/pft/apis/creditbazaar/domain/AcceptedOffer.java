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
 * A AcceptedOffer.
 */
@Table("accepted_offer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AcceptedOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("offer_id")
    private String offerId;

    @Column("accepted_offer_ref_no")
    private String acceptedOfferRefNo;

    @NotNull(message = "must not be null")
    @Column("req_off_id")
    private Long reqOffId;

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

    @NotNull(message = "must not be null")
    @Column("status")
    private String status;

    @NotNull(message = "must not be null")
    @Column("offer_date")
    private LocalDate offerDate;

    @NotNull(message = "must not be null")
    @Column("offer_accepted_date")
    private LocalDate offerAcceptedDate;

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

    @Transient
    @JsonIgnoreProperties(value = { "financeRequests", "acceptedOffers", "trades" }, allowSetters = true)
    private AnchorTrader anchortrader;

    @Column("financerequest_id")
    private Long financerequestId;

    @Column("financepartner_id")
    private Long financepartnerId;

    @Column("anchortrader_id")
    private Long anchortraderId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AcceptedOffer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfferId() {
        return this.offerId;
    }

    public AcceptedOffer offerId(String offerId) {
        this.setOfferId(offerId);
        return this;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getAcceptedOfferRefNo() {
        return this.acceptedOfferRefNo;
    }

    public AcceptedOffer acceptedOfferRefNo(String acceptedOfferRefNo) {
        this.setAcceptedOfferRefNo(acceptedOfferRefNo);
        return this;
    }

    public void setAcceptedOfferRefNo(String acceptedOfferRefNo) {
        this.acceptedOfferRefNo = acceptedOfferRefNo;
    }

    public Long getReqOffId() {
        return this.reqOffId;
    }

    public AcceptedOffer reqOffId(Long reqOffId) {
        this.setReqOffId(reqOffId);
        return this;
    }

    public void setReqOffId(Long reqOffId) {
        this.reqOffId = reqOffId;
    }

    public Long getValue() {
        return this.value;
    }

    public AcceptedOffer value(Long value) {
        this.setValue(value);
        return this;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getReqAmount() {
        return this.reqAmount;
    }

    public AcceptedOffer reqAmount(Long reqAmount) {
        this.setReqAmount(reqAmount);
        return this;
    }

    public void setReqAmount(Long reqAmount) {
        this.reqAmount = reqAmount;
    }

    public Float getMarginPtg() {
        return this.marginPtg;
    }

    public AcceptedOffer marginPtg(Float marginPtg) {
        this.setMarginPtg(marginPtg);
        return this;
    }

    public void setMarginPtg(Float marginPtg) {
        this.marginPtg = marginPtg;
    }

    public Long getMarginValue() {
        return this.marginValue;
    }

    public AcceptedOffer marginValue(Long marginValue) {
        this.setMarginValue(marginValue);
        return this;
    }

    public void setMarginValue(Long marginValue) {
        this.marginValue = marginValue;
    }

    public Long getAmountAftMargin() {
        return this.amountAftMargin;
    }

    public AcceptedOffer amountAftMargin(Long amountAftMargin) {
        this.setAmountAftMargin(amountAftMargin);
        return this;
    }

    public void setAmountAftMargin(Long amountAftMargin) {
        this.amountAftMargin = amountAftMargin;
    }

    public Float getInterestPtg() {
        return this.interestPtg;
    }

    public AcceptedOffer interestPtg(Float interestPtg) {
        this.setInterestPtg(interestPtg);
        return this;
    }

    public void setInterestPtg(Float interestPtg) {
        this.interestPtg = interestPtg;
    }

    public Long getTerm() {
        return this.term;
    }

    public AcceptedOffer term(Long term) {
        this.setTerm(term);
        return this;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public Long getInterestValue() {
        return this.interestValue;
    }

    public AcceptedOffer interestValue(Long interestValue) {
        this.setInterestValue(interestValue);
        return this;
    }

    public void setInterestValue(Long interestValue) {
        this.interestValue = interestValue;
    }

    public Long getNetAmount() {
        return this.netAmount;
    }

    public AcceptedOffer netAmount(Long netAmount) {
        this.setNetAmount(netAmount);
        return this;
    }

    public void setNetAmount(Long netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public AcceptedOffer status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOfferDate() {
        return this.offerDate;
    }

    public AcceptedOffer offerDate(LocalDate offerDate) {
        this.setOfferDate(offerDate);
        return this;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getOfferAcceptedDate() {
        return this.offerAcceptedDate;
    }

    public AcceptedOffer offerAcceptedDate(LocalDate offerAcceptedDate) {
        this.setOfferAcceptedDate(offerAcceptedDate);
        return this;
    }

    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public AcceptedOffer financerequest(FinanceRequest financeRequest) {
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

    public AcceptedOffer financepartner(FinancePartner financePartner) {
        this.setFinancepartner(financePartner);
        return this;
    }

    public AnchorTrader getAnchortrader() {
        return this.anchortrader;
    }

    public void setAnchortrader(AnchorTrader anchorTrader) {
        this.anchortrader = anchorTrader;
        this.anchortraderId = anchorTrader != null ? anchorTrader.getId() : null;
    }

    public AcceptedOffer anchortrader(AnchorTrader anchorTrader) {
        this.setAnchortrader(anchorTrader);
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
        if (!(o instanceof AcceptedOffer)) {
            return false;
        }
        return getId() != null && getId().equals(((AcceptedOffer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AcceptedOffer{" +
            "id=" + getId() +
            ", offerId='" + getOfferId() + "'" +
            ", acceptedOfferRefNo='" + getAcceptedOfferRefNo() + "'" +
            ", reqOffId=" + getReqOffId() +
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
            ", offerAcceptedDate='" + getOfferAcceptedDate() + "'" +
            "}";
    }
}
