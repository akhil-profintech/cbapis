package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.RequestOffer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequestOfferDTO implements Serializable {

    private Long id;

    private String reqOffUlidId;

    private String reqOfferRefNo;

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

    private String anchorTraderGSTComplianceScore;

    private String anchorTraderGSTERPPeerScore;

    private String sellerTradePerformanceIndex;

    private FinanceRequestDTO financerequest;

    private FinancePartnerDTO financepartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqOffUlidId() {
        return reqOffUlidId;
    }

    public void setReqOffUlidId(String reqOffUlidId) {
        this.reqOffUlidId = reqOffUlidId;
    }

    public String getReqOfferRefNo() {
        return reqOfferRefNo;
    }

    public void setReqOfferRefNo(String reqOfferRefNo) {
        this.reqOfferRefNo = reqOfferRefNo;
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

    public String getAnchorTraderGSTComplianceScore() {
        return anchorTraderGSTComplianceScore;
    }

    public void setAnchorTraderGSTComplianceScore(String anchorTraderGSTComplianceScore) {
        this.anchorTraderGSTComplianceScore = anchorTraderGSTComplianceScore;
    }

    public String getAnchorTraderGSTERPPeerScore() {
        return anchorTraderGSTERPPeerScore;
    }

    public void setAnchorTraderGSTERPPeerScore(String anchorTraderGSTERPPeerScore) {
        this.anchorTraderGSTERPPeerScore = anchorTraderGSTERPPeerScore;
    }

    public String getSellerTradePerformanceIndex() {
        return sellerTradePerformanceIndex;
    }

    public void setSellerTradePerformanceIndex(String sellerTradePerformanceIndex) {
        this.sellerTradePerformanceIndex = sellerTradePerformanceIndex;
    }

    public FinanceRequestDTO getFinancerequest() {
        return financerequest;
    }

    public void setFinancerequest(FinanceRequestDTO financerequest) {
        this.financerequest = financerequest;
    }

    public FinancePartnerDTO getFinancepartner() {
        return financepartner;
    }

    public void setFinancepartner(FinancePartnerDTO financepartner) {
        this.financepartner = financepartner;
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
            ", reqOffUlidId='" + getReqOffUlidId() + "'" +
            ", reqOfferRefNo='" + getReqOfferRefNo() + "'" +
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
            ", anchorTraderGSTComplianceScore='" + getAnchorTraderGSTComplianceScore() + "'" +
            ", anchorTraderGSTERPPeerScore='" + getAnchorTraderGSTERPPeerScore() + "'" +
            ", sellerTradePerformanceIndex='" + getSellerTradePerformanceIndex() + "'" +
            ", financerequest=" + getFinancerequest() +
            ", financepartner=" + getFinancepartner() +
            "}";
    }
}
