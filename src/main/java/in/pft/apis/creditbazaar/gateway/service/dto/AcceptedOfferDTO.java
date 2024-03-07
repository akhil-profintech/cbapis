package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.AcceptedOffer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AcceptedOfferDTO implements Serializable {

    private Long id;

    private String acceptedOfferUlidId;

    private String acceptedOfferRefNo;

    @NotNull(message = "must not be null")
    private String reqOffUlidId;

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

    @NotNull(message = "must not be null")
    private LocalDate offerAcceptedDate;

    private FinanceRequestDTO financerequest;

    private AnchorTraderDTO anchortrader;

    private FinancePartnerDTO financepartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcceptedOfferUlidId() {
        return acceptedOfferUlidId;
    }

    public void setAcceptedOfferUlidId(String acceptedOfferUlidId) {
        this.acceptedOfferUlidId = acceptedOfferUlidId;
    }

    public String getAcceptedOfferRefNo() {
        return acceptedOfferRefNo;
    }

    public void setAcceptedOfferRefNo(String acceptedOfferRefNo) {
        this.acceptedOfferRefNo = acceptedOfferRefNo;
    }

    public String getReqOffUlidId() {
        return reqOffUlidId;
    }

    public void setReqOffUlidId(String reqOffUlidId) {
        this.reqOffUlidId = reqOffUlidId;
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

    public LocalDate getOfferAcceptedDate() {
        return offerAcceptedDate;
    }

    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }

    public FinanceRequestDTO getFinancerequest() {
        return financerequest;
    }

    public void setFinancerequest(FinanceRequestDTO financerequest) {
        this.financerequest = financerequest;
    }

    public AnchorTraderDTO getAnchortrader() {
        return anchortrader;
    }

    public void setAnchortrader(AnchorTraderDTO anchortrader) {
        this.anchortrader = anchortrader;
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
        if (!(o instanceof AcceptedOfferDTO)) {
            return false;
        }

        AcceptedOfferDTO acceptedOfferDTO = (AcceptedOfferDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, acceptedOfferDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AcceptedOfferDTO{" +
            "id=" + getId() +
            ", acceptedOfferUlidId='" + getAcceptedOfferUlidId() + "'" +
            ", acceptedOfferRefNo='" + getAcceptedOfferRefNo() + "'" +
            ", reqOffUlidId='" + getReqOffUlidId() + "'" +
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
            ", financerequest=" + getFinancerequest() +
            ", anchortrader=" + getAnchortrader() +
            ", financepartner=" + getFinancepartner() +
            "}";
    }
}
