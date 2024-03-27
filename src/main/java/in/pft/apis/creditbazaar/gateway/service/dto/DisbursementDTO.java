package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.Disbursement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DisbursementDTO implements Serializable {

    private Long id;

    private Long dbmtId;

    private String disbursementUlidId;

    private String disbursementRefNo;

    @NotNull(message = "must not be null")
    private String acceptedOfferUlidId;

    @NotNull(message = "must not be null")
    private String placedOfferUlidId;

    @NotNull(message = "must not be null")
    private String reqOffUlidId;

    @NotNull(message = "must not be null")
    private LocalDate offerAcceptedDate;

    @NotNull(message = "must not be null")
    private String dbmtRequestId;

    @NotNull(message = "must not be null")
    private Long dbmtReqAmount;

    @NotNull(message = "must not be null")
    private String currency;

    @NotNull(message = "must not be null")
    private LocalDate dbmtRequestDate;

    @NotNull(message = "must not be null")
    private String dbmtStatus;

    private String offerStatus;

    private Long docId;

    private String dbmtDateTime;

    private Long dbmtAmount;

    private Long financeRequestId;

    private String amountToBeDisbursed;

    private String destinationAccountName;

    private String destinationAccountNumber;

    private String recordStatus;

    private String actionByDate;

    private FinanceRequestDTO financerequest;

    private FinancePartnerDTO financepartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDbmtId() {
        return dbmtId;
    }

    public void setDbmtId(Long dbmtId) {
        this.dbmtId = dbmtId;
    }

    public String getDisbursementUlidId() {
        return disbursementUlidId;
    }

    public void setDisbursementUlidId(String disbursementUlidId) {
        this.disbursementUlidId = disbursementUlidId;
    }

    public String getDisbursementRefNo() {
        return disbursementRefNo;
    }

    public void setDisbursementRefNo(String disbursementRefNo) {
        this.disbursementRefNo = disbursementRefNo;
    }

    public String getAcceptedOfferUlidId() {
        return acceptedOfferUlidId;
    }

    public void setAcceptedOfferUlidId(String acceptedOfferUlidId) {
        this.acceptedOfferUlidId = acceptedOfferUlidId;
    }

    public String getPlacedOfferUlidId() {
        return placedOfferUlidId;
    }

    public void setPlacedOfferUlidId(String placedOfferUlidId) {
        this.placedOfferUlidId = placedOfferUlidId;
    }

    public String getReqOffUlidId() {
        return reqOffUlidId;
    }

    public void setReqOffUlidId(String reqOffUlidId) {
        this.reqOffUlidId = reqOffUlidId;
    }

    public LocalDate getOfferAcceptedDate() {
        return offerAcceptedDate;
    }

    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }

    public String getDbmtRequestId() {
        return dbmtRequestId;
    }

    public void setDbmtRequestId(String dbmtRequestId) {
        this.dbmtRequestId = dbmtRequestId;
    }

    public Long getDbmtReqAmount() {
        return dbmtReqAmount;
    }

    public void setDbmtReqAmount(Long dbmtReqAmount) {
        this.dbmtReqAmount = dbmtReqAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDbmtRequestDate() {
        return dbmtRequestDate;
    }

    public void setDbmtRequestDate(LocalDate dbmtRequestDate) {
        this.dbmtRequestDate = dbmtRequestDate;
    }

    public String getDbmtStatus() {
        return dbmtStatus;
    }

    public void setDbmtStatus(String dbmtStatus) {
        this.dbmtStatus = dbmtStatus;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDbmtDateTime() {
        return dbmtDateTime;
    }

    public void setDbmtDateTime(String dbmtDateTime) {
        this.dbmtDateTime = dbmtDateTime;
    }

    public Long getDbmtAmount() {
        return dbmtAmount;
    }

    public void setDbmtAmount(Long dbmtAmount) {
        this.dbmtAmount = dbmtAmount;
    }

    public Long getFinanceRequestId() {
        return financeRequestId;
    }

    public void setFinanceRequestId(Long financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public String getAmountToBeDisbursed() {
        return amountToBeDisbursed;
    }

    public void setAmountToBeDisbursed(String amountToBeDisbursed) {
        this.amountToBeDisbursed = amountToBeDisbursed;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getActionByDate() {
        return actionByDate;
    }

    public void setActionByDate(String actionByDate) {
        this.actionByDate = actionByDate;
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
        if (!(o instanceof DisbursementDTO)) {
            return false;
        }

        DisbursementDTO disbursementDTO = (DisbursementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, disbursementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DisbursementDTO{" +
            "id=" + getId() +
            ", dbmtId=" + getDbmtId() +
            ", disbursementUlidId='" + getDisbursementUlidId() + "'" +
            ", disbursementRefNo='" + getDisbursementRefNo() + "'" +
            ", acceptedOfferUlidId='" + getAcceptedOfferUlidId() + "'" +
            ", placedOfferUlidId='" + getPlacedOfferUlidId() + "'" +
            ", reqOffUlidId='" + getReqOffUlidId() + "'" +
            ", offerAcceptedDate='" + getOfferAcceptedDate() + "'" +
            ", dbmtRequestId='" + getDbmtRequestId() + "'" +
            ", dbmtReqAmount=" + getDbmtReqAmount() +
            ", currency='" + getCurrency() + "'" +
            ", dbmtRequestDate='" + getDbmtRequestDate() + "'" +
            ", dbmtStatus='" + getDbmtStatus() + "'" +
            ", offerStatus='" + getOfferStatus() + "'" +
            ", docId=" + getDocId() +
            ", dbmtDateTime='" + getDbmtDateTime() + "'" +
            ", dbmtAmount=" + getDbmtAmount() +
            ", financeRequestId=" + getFinanceRequestId() +
            ", amountToBeDisbursed='" + getAmountToBeDisbursed() + "'" +
            ", destinationAccountName='" + getDestinationAccountName() + "'" +
            ", destinationAccountNumber='" + getDestinationAccountNumber() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", actionByDate='" + getActionByDate() + "'" +
            ", financerequest=" + getFinancerequest() +
            ", financepartner=" + getFinancepartner() +
            "}";
    }
}
