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
 * A RequestOffer.
 */
@Table("request_offer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequestOffer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("req_off_id")
    private String reqOffId;

    @Column("request_offer_ref_no")
    private String requestOfferRefNo;

    @NotNull(message = "must not be null")
    @Column("offer_value")
    private Long offerValue;

    @NotNull(message = "must not be null")
    @Column("request_amt")
    private Long requestAmt;

    @NotNull(message = "must not be null")
    @Column("trade_value")
    private Long tradeValue;

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
    private Float interestValue;

    @NotNull(message = "must not be null")
    @Column("net_amount")
    private Long netAmount;

    @NotNull(message = "must not be null")
    @Column("status")
    private String status;

    @NotNull(message = "must not be null")
    @Column("finance_request_date")
    private LocalDate financeRequestDate;

    @NotNull(message = "must not be null")
    @Column("anchor_trader_name")
    private String anchorTraderName;

    @NotNull(message = "must not be null")
    @Column("trade_partner_name")
    private String tradePartnerName;

    @Column("anchor_trader_gst")
    private String anchorTraderGst;

    @Column("trade_partner_gst")
    private String tradePartnerGst;

    @Column("seller_name")
    private String sellerName;

    @Column("buyer_name")
    private String buyerName;

    @Column("anchor_trader_gst_compliance_score")
    private String anchorTraderGstComplianceScore;

    @Column("anchor_trader_erp_peer_score")
    private String anchorTraderErpPeerScore;

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
    @JsonIgnoreProperties(value = { "cREHighlights", "cREObservations", "requestOffers", "individualAssessments" }, allowSetters = true)
    private CBCREProcess cbcreprocess;

    @Column("financerequest_id")
    private Long financerequestId;

    @Column("cbcreprocess_id")
    private Long cbcreprocessId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public RequestOffer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqOffId() {
        return this.reqOffId;
    }

    public RequestOffer reqOffId(String reqOffId) {
        this.setReqOffId(reqOffId);
        return this;
    }

    public void setReqOffId(String reqOffId) {
        this.reqOffId = reqOffId;
    }

    public String getRequestOfferRefNo() {
        return this.requestOfferRefNo;
    }

    public RequestOffer requestOfferRefNo(String requestOfferRefNo) {
        this.setRequestOfferRefNo(requestOfferRefNo);
        return this;
    }

    public void setRequestOfferRefNo(String requestOfferRefNo) {
        this.requestOfferRefNo = requestOfferRefNo;
    }

    public Long getOfferValue() {
        return this.offerValue;
    }

    public RequestOffer offerValue(Long offerValue) {
        this.setOfferValue(offerValue);
        return this;
    }

    public void setOfferValue(Long offerValue) {
        this.offerValue = offerValue;
    }

    public Long getRequestAmt() {
        return this.requestAmt;
    }

    public RequestOffer requestAmt(Long requestAmt) {
        this.setRequestAmt(requestAmt);
        return this;
    }

    public void setRequestAmt(Long requestAmt) {
        this.requestAmt = requestAmt;
    }

    public Long getTradeValue() {
        return this.tradeValue;
    }

    public RequestOffer tradeValue(Long tradeValue) {
        this.setTradeValue(tradeValue);
        return this;
    }

    public void setTradeValue(Long tradeValue) {
        this.tradeValue = tradeValue;
    }

    public Float getMarginPtg() {
        return this.marginPtg;
    }

    public RequestOffer marginPtg(Float marginPtg) {
        this.setMarginPtg(marginPtg);
        return this;
    }

    public void setMarginPtg(Float marginPtg) {
        this.marginPtg = marginPtg;
    }

    public Long getMarginValue() {
        return this.marginValue;
    }

    public RequestOffer marginValue(Long marginValue) {
        this.setMarginValue(marginValue);
        return this;
    }

    public void setMarginValue(Long marginValue) {
        this.marginValue = marginValue;
    }

    public Long getAmountAftMargin() {
        return this.amountAftMargin;
    }

    public RequestOffer amountAftMargin(Long amountAftMargin) {
        this.setAmountAftMargin(amountAftMargin);
        return this;
    }

    public void setAmountAftMargin(Long amountAftMargin) {
        this.amountAftMargin = amountAftMargin;
    }

    public Float getInterestPtg() {
        return this.interestPtg;
    }

    public RequestOffer interestPtg(Float interestPtg) {
        this.setInterestPtg(interestPtg);
        return this;
    }

    public void setInterestPtg(Float interestPtg) {
        this.interestPtg = interestPtg;
    }

    public Long getTerm() {
        return this.term;
    }

    public RequestOffer term(Long term) {
        this.setTerm(term);
        return this;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public Float getInterestValue() {
        return this.interestValue;
    }

    public RequestOffer interestValue(Float interestValue) {
        this.setInterestValue(interestValue);
        return this;
    }

    public void setInterestValue(Float interestValue) {
        this.interestValue = interestValue;
    }

    public Long getNetAmount() {
        return this.netAmount;
    }

    public RequestOffer netAmount(Long netAmount) {
        this.setNetAmount(netAmount);
        return this;
    }

    public void setNetAmount(Long netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return this.status;
    }

    public RequestOffer status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFinanceRequestDate() {
        return this.financeRequestDate;
    }

    public RequestOffer financeRequestDate(LocalDate financeRequestDate) {
        this.setFinanceRequestDate(financeRequestDate);
        return this;
    }

    public void setFinanceRequestDate(LocalDate financeRequestDate) {
        this.financeRequestDate = financeRequestDate;
    }

    public String getAnchorTraderName() {
        return this.anchorTraderName;
    }

    public RequestOffer anchorTraderName(String anchorTraderName) {
        this.setAnchorTraderName(anchorTraderName);
        return this;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getTradePartnerName() {
        return this.tradePartnerName;
    }

    public RequestOffer tradePartnerName(String tradePartnerName) {
        this.setTradePartnerName(tradePartnerName);
        return this;
    }

    public void setTradePartnerName(String tradePartnerName) {
        this.tradePartnerName = tradePartnerName;
    }

    public String getAnchorTraderGst() {
        return this.anchorTraderGst;
    }

    public RequestOffer anchorTraderGst(String anchorTraderGst) {
        this.setAnchorTraderGst(anchorTraderGst);
        return this;
    }

    public void setAnchorTraderGst(String anchorTraderGst) {
        this.anchorTraderGst = anchorTraderGst;
    }

    public String getTradePartnerGst() {
        return this.tradePartnerGst;
    }

    public RequestOffer tradePartnerGst(String tradePartnerGst) {
        this.setTradePartnerGst(tradePartnerGst);
        return this;
    }

    public void setTradePartnerGst(String tradePartnerGst) {
        this.tradePartnerGst = tradePartnerGst;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public RequestOffer sellerName(String sellerName) {
        this.setSellerName(sellerName);
        return this;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyerName() {
        return this.buyerName;
    }

    public RequestOffer buyerName(String buyerName) {
        this.setBuyerName(buyerName);
        return this;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getAnchorTraderGstComplianceScore() {
        return this.anchorTraderGstComplianceScore;
    }

    public RequestOffer anchorTraderGstComplianceScore(String anchorTraderGstComplianceScore) {
        this.setAnchorTraderGstComplianceScore(anchorTraderGstComplianceScore);
        return this;
    }

    public void setAnchorTraderGstComplianceScore(String anchorTraderGstComplianceScore) {
        this.anchorTraderGstComplianceScore = anchorTraderGstComplianceScore;
    }

    public String getAnchorTraderErpPeerScore() {
        return this.anchorTraderErpPeerScore;
    }

    public RequestOffer anchorTraderErpPeerScore(String anchorTraderErpPeerScore) {
        this.setAnchorTraderErpPeerScore(anchorTraderErpPeerScore);
        return this;
    }

    public void setAnchorTraderErpPeerScore(String anchorTraderErpPeerScore) {
        this.anchorTraderErpPeerScore = anchorTraderErpPeerScore;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public RequestOffer financerequest(FinanceRequest financeRequest) {
        this.setFinancerequest(financeRequest);
        return this;
    }

    public CBCREProcess getCbcreprocess() {
        return this.cbcreprocess;
    }

    public void setCbcreprocess(CBCREProcess cBCREProcess) {
        this.cbcreprocess = cBCREProcess;
        this.cbcreprocessId = cBCREProcess != null ? cBCREProcess.getId() : null;
    }

    public RequestOffer cbcreprocess(CBCREProcess cBCREProcess) {
        this.setCbcreprocess(cBCREProcess);
        return this;
    }

    public Long getFinancerequestId() {
        return this.financerequestId;
    }

    public void setFinancerequestId(Long financeRequest) {
        this.financerequestId = financeRequest;
    }

    public Long getCbcreprocessId() {
        return this.cbcreprocessId;
    }

    public void setCbcreprocessId(Long cBCREProcess) {
        this.cbcreprocessId = cBCREProcess;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RequestOffer)) {
            return false;
        }
        return getId() != null && getId().equals(((RequestOffer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestOffer{" +
            "id=" + getId() +
            ", reqOffId='" + getReqOffId() + "'" +
            ", requestOfferRefNo='" + getRequestOfferRefNo() + "'" +
            ", offerValue=" + getOfferValue() +
            ", requestAmt=" + getRequestAmt() +
            ", tradeValue=" + getTradeValue() +
            ", marginPtg=" + getMarginPtg() +
            ", marginValue=" + getMarginValue() +
            ", amountAftMargin=" + getAmountAftMargin() +
            ", interestPtg=" + getInterestPtg() +
            ", term=" + getTerm() +
            ", interestValue=" + getInterestValue() +
            ", netAmount=" + getNetAmount() +
            ", status='" + getStatus() + "'" +
            ", financeRequestDate='" + getFinanceRequestDate() + "'" +
            ", anchorTraderName='" + getAnchorTraderName() + "'" +
            ", tradePartnerName='" + getTradePartnerName() + "'" +
            ", anchorTraderGst='" + getAnchorTraderGst() + "'" +
            ", tradePartnerGst='" + getTradePartnerGst() + "'" +
            ", sellerName='" + getSellerName() + "'" +
            ", buyerName='" + getBuyerName() + "'" +
            ", anchorTraderGstComplianceScore='" + getAnchorTraderGstComplianceScore() + "'" +
            ", anchorTraderErpPeerScore='" + getAnchorTraderErpPeerScore() + "'" +
            "}";
    }
}
