package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.PlacedOffer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PlacedOfferDTO implements Serializable {

    private Long id;

    private String placedOfferUlidId;

    private String placedOfferRefNo;

    private String reqOffUlidId;

    private String requestOfferRefNo;

    @NotNull(message = "must not be null")
    private Long value;

    @NotNull(message = "must not be null")
    private Long reqAmount;

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
    private Long interestValue;

    @NotNull(message = "must not be null")
    private Long netAmount;

    @NotNull(message = "must not be null")
    private String status;

    @NotNull(message = "must not be null")
    private LocalDate offerDate;

    private LocalDate placedOfferDate;

    private String anchorTrader;

    private String tradePartner;

    @NotNull(message = "must not be null")
    private String disbursalAmount;

    private FinanceRequestDTO financerequest;

    private FinancePartnerDTO financepartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlacedOfferUlidId() {
        return placedOfferUlidId;
    }

    public void setPlacedOfferUlidId(String placedOfferUlidId) {
        this.placedOfferUlidId = placedOfferUlidId;
    }

    public String getPlacedOfferRefNo() {
        return placedOfferRefNo;
    }

    public void setPlacedOfferRefNo(String placedOfferRefNo) {
        this.placedOfferRefNo = placedOfferRefNo;
    }

    public String getReqOffUlidId() {
        return reqOffUlidId;
    }

    public void setReqOffUlidId(String reqOffUlidId) {
        this.reqOffUlidId = reqOffUlidId;
    }

    public String getRequestOfferRefNo() {
        return requestOfferRefNo;
    }

    public void setRequestOfferRefNo(String requestOfferRefNo) {
        this.requestOfferRefNo = requestOfferRefNo;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getReqAmount() {
        return reqAmount;
    }

    public void setReqAmount(Long reqAmount) {
        this.reqAmount = reqAmount;
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

    public Long getInterestValue() {
        return interestValue;
    }

    public void setInterestValue(Long interestValue) {
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

    public LocalDate getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(LocalDate offerDate) {
        this.offerDate = offerDate;
    }

    public LocalDate getPlacedOfferDate() {
        return placedOfferDate;
    }

    public void setPlacedOfferDate(LocalDate placedOfferDate) {
        this.placedOfferDate = placedOfferDate;
    }

    public String getAnchorTrader() {
        return anchorTrader;
    }

    public void setAnchorTrader(String anchorTrader) {
        this.anchorTrader = anchorTrader;
    }

    public String getTradePartner() {
        return tradePartner;
    }

    public void setTradePartner(String tradePartner) {
        this.tradePartner = tradePartner;
    }

    public String getDisbursalAmount() {
        return disbursalAmount;
    }

    public void setDisbursalAmount(String disbursalAmount) {
        this.disbursalAmount = disbursalAmount;
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
        if (!(o instanceof PlacedOfferDTO)) {
            return false;
        }

        PlacedOfferDTO placedOfferDTO = (PlacedOfferDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, placedOfferDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlacedOfferDTO{" +
            "id=" + getId() +
            ", placedOfferUlidId='" + getPlacedOfferUlidId() + "'" +
            ", placedOfferRefNo='" + getPlacedOfferRefNo() + "'" +
            ", reqOffUlidId='" + getReqOffUlidId() + "'" +
            ", requestOfferRefNo='" + getRequestOfferRefNo() + "'" +
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
            ", placedOfferDate='" + getPlacedOfferDate() + "'" +
            ", anchorTrader='" + getAnchorTrader() + "'" +
            ", tradePartner='" + getTradePartner() + "'" +
            ", disbursalAmount='" + getDisbursalAmount() + "'" +
            ", financerequest=" + getFinancerequest() +
            ", financepartner=" + getFinancepartner() +
            "}";
    }
}
