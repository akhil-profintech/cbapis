package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.Disbursement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DisbursementDTO implements Serializable {

    private Long id;

    private String dbmtId;

    private String disbursementRefNo;

    @NotNull(message = "must not be null")
    private Long acceptedOfferId;

    @NotNull(message = "must not be null")
    private Long offerId;

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
    private String dbmtstatus;

    private String offerStatus;

    private String ftTrnxDetailsId;

    private String collectionTrnxDetailsId;

    private Long docId;

    private String dbmtDateTime;

    private Long dbmtAmount;

    private String financeRequestId;

    private String amountToBeDisbursed;

    private String destinationAccountName;

    private String destinationAccountNumber;

    private String ifscCode;

    private String status;

    private String actionByDate;

    private FinanceRequestDTO financerequest;

    private FinancePartnerDTO financepartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbmtId() {
        return dbmtId;
    }

    public void setDbmtId(String dbmtId) {
        this.dbmtId = dbmtId;
    }

    public String getDisbursementRefNo() {
        return disbursementRefNo;
    }

    public void setDisbursementRefNo(String disbursementRefNo) {
        this.disbursementRefNo = disbursementRefNo;
    }

    public Long getAcceptedOfferId() {
        return acceptedOfferId;
    }

    public void setAcceptedOfferId(Long acceptedOfferId) {
        this.acceptedOfferId = acceptedOfferId;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
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

    public String getDbmtstatus() {
        return dbmtstatus;
    }

    public void setDbmtstatus(String dbmtstatus) {
        this.dbmtstatus = dbmtstatus;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String getFtTrnxDetailsId() {
        return ftTrnxDetailsId;
    }

    public void setFtTrnxDetailsId(String ftTrnxDetailsId) {
        this.ftTrnxDetailsId = ftTrnxDetailsId;
    }

    public String getCollectionTrnxDetailsId() {
        return collectionTrnxDetailsId;
    }

    public void setCollectionTrnxDetailsId(String collectionTrnxDetailsId) {
        this.collectionTrnxDetailsId = collectionTrnxDetailsId;
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

    public String getFinanceRequestId() {
        return financeRequestId;
    }

    public void setFinanceRequestId(String financeRequestId) {
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

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
            ", dbmtId='" + getDbmtId() + "'" +
            ", disbursementRefNo='" + getDisbursementRefNo() + "'" +
            ", acceptedOfferId=" + getAcceptedOfferId() +
            ", offerId=" + getOfferId() +
            ", offerAcceptedDate='" + getOfferAcceptedDate() + "'" +
            ", dbmtRequestId='" + getDbmtRequestId() + "'" +
            ", dbmtReqAmount=" + getDbmtReqAmount() +
            ", currency='" + getCurrency() + "'" +
            ", dbmtRequestDate='" + getDbmtRequestDate() + "'" +
            ", dbmtstatus='" + getDbmtstatus() + "'" +
            ", offerStatus='" + getOfferStatus() + "'" +
            ", ftTrnxDetailsId='" + getFtTrnxDetailsId() + "'" +
            ", collectionTrnxDetailsId='" + getCollectionTrnxDetailsId() + "'" +
            ", docId=" + getDocId() +
            ", dbmtDateTime='" + getDbmtDateTime() + "'" +
            ", dbmtAmount=" + getDbmtAmount() +
            ", financeRequestId='" + getFinanceRequestId() + "'" +
            ", amountToBeDisbursed='" + getAmountToBeDisbursed() + "'" +
            ", destinationAccountName='" + getDestinationAccountName() + "'" +
            ", destinationAccountNumber='" + getDestinationAccountNumber() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", actionByDate='" + getActionByDate() + "'" +
            ", financerequest=" + getFinancerequest() +
            ", financepartner=" + getFinancepartner() +
            "}";
    }
}
