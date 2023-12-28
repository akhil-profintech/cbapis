package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.RequestOffer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequestOfferDTO implements Serializable {

    private Long id;

    private String reqOffId;

    private String requestOfferRefNo;

    @NotNull(message = "must not be null")
    private Long offerValue;

    @NotNull(message = "must not be null")
    private Long requestAmt;

    @NotNull(message = "must not be null")
    private Long tradeValue;

    @NotNull(message = "must not be null")
    private Float marginPtg;

    @NotNull(message = "must not be null")
    private Long marginValue;

    @NotNull(message = "must not be null")
    private Long amountAftMargin;

    @NotNull(message = "must not be null")
    private Float interestPtg;

    @NotNull(message = "must not be null")
    private Long term;

    @NotNull(message = "must not be null")
    private Float interestValue;

    @NotNull(message = "must not be null")
    private Long netAmount;

    @NotNull(message = "must not be null")
    private String status;

    @NotNull(message = "must not be null")
    private LocalDate financeRequestDate;

    @NotNull(message = "must not be null")
    private String anchorTraderName;

    @NotNull(message = "must not be null")
    private String tradePartnerName;

    private String anchorTraderGst;

    private String tradePartnerGst;

    private String sellerName;

    private String buyerName;

    private String anchorTraderGstComplianceScore;

    private String anchorTraderErpPeerScore;

    private FinanceRequestDTO financerequest;

    private CBCREProcessDTO cbcreprocess;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqOffId() {
        return reqOffId;
    }

    public void setReqOffId(String reqOffId) {
        this.reqOffId = reqOffId;
    }

    public String getRequestOfferRefNo() {
        return requestOfferRefNo;
    }

    public void setRequestOfferRefNo(String requestOfferRefNo) {
        this.requestOfferRefNo = requestOfferRefNo;
    }

    public Long getOfferValue() {
        return offerValue;
    }

    public void setOfferValue(Long offerValue) {
        this.offerValue = offerValue;
    }

    public Long getRequestAmt() {
        return requestAmt;
    }

    public void setRequestAmt(Long requestAmt) {
        this.requestAmt = requestAmt;
    }

    public Long getTradeValue() {
        return tradeValue;
    }

    public void setTradeValue(Long tradeValue) {
        this.tradeValue = tradeValue;
    }

    public Float getMarginPtg() {
        return marginPtg;
    }

    public void setMarginPtg(Float marginPtg) {
        this.marginPtg = marginPtg;
    }

    public Long getMarginValue() {
        return marginValue;
    }

    public void setMarginValue(Long marginValue) {
        this.marginValue = marginValue;
    }

    public Long getAmountAftMargin() {
        return amountAftMargin;
    }

    public void setAmountAftMargin(Long amountAftMargin) {
        this.amountAftMargin = amountAftMargin;
    }

    public Float getInterestPtg() {
        return interestPtg;
    }

    public void setInterestPtg(Float interestPtg) {
        this.interestPtg = interestPtg;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    public Float getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(Float interestValue) {
        this.interestValue = interestValue;
    }

    public Long getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(Long netAmount) {
        this.netAmount = netAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getFinanceRequestDate() {
        return financeRequestDate;
    }

    public void setFinanceRequestDate(LocalDate financeRequestDate) {
        this.financeRequestDate = financeRequestDate;
    }

    public String getAnchorTraderName() {
        return anchorTraderName;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getTradePartnerName() {
        return tradePartnerName;
    }

    public void setTradePartnerName(String tradePartnerName) {
        this.tradePartnerName = tradePartnerName;
    }

    public String getAnchorTraderGst() {
        return anchorTraderGst;
    }

    public void setAnchorTraderGst(String anchorTraderGst) {
        this.anchorTraderGst = anchorTraderGst;
    }

    public String getTradePartnerGst() {
        return tradePartnerGst;
    }

    public void setTradePartnerGst(String tradePartnerGst) {
        this.tradePartnerGst = tradePartnerGst;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getAnchorTraderGstComplianceScore() {
        return anchorTraderGstComplianceScore;
    }

    public void setAnchorTraderGstComplianceScore(String anchorTraderGstComplianceScore) {
        this.anchorTraderGstComplianceScore = anchorTraderGstComplianceScore;
    }

    public String getAnchorTraderErpPeerScore() {
        return anchorTraderErpPeerScore;
    }

    public void setAnchorTraderErpPeerScore(String anchorTraderErpPeerScore) {
        this.anchorTraderErpPeerScore = anchorTraderErpPeerScore;
    }

    public FinanceRequestDTO getFinancerequest() {
        return financerequest;
    }

    public void setFinancerequest(FinanceRequestDTO financerequest) {
        this.financerequest = financerequest;
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
        if (!(o instanceof RequestOfferDTO)) {
            return false;
        }

        RequestOfferDTO requestOfferDTO = (RequestOfferDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, requestOfferDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestOfferDTO{" +
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
            ", financerequest=" + getFinancerequest() +
            ", cbcreprocess=" + getCbcreprocess() +
            "}";
    }
}
