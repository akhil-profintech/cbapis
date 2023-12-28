package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.Repayment} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RepaymentDTO implements Serializable {

    private Long id;

    private String repaymentId;

    private String repaymentRefNo;

    @NotNull(message = "must not be null")
    private Long acceptedOfferId;

    @NotNull(message = "must not be null")
    private Long offerId;

    @NotNull(message = "must not be null")
    private LocalDate offerAcceptedDate;

    @NotNull(message = "must not be null")
    private String dbmtRequestId;

    @NotNull(message = "must not be null")
    private String dbmtstatus;

    @NotNull(message = "must not be null")
    private String dbmtDateTime;

    @NotNull(message = "must not be null")
    private String dbmtId;

    @NotNull(message = "must not be null")
    private Long dbmtAmount;

    @NotNull(message = "must not be null")
    private String currency;

    @NotNull(message = "must not be null")
    private String repaymentStatus;

    private LocalDate repaymentDate;

    private Long repaymentAmount;

    private String ftTrnxDetailsId;

    private String collectionTrnxDetailsId;

    private Long docId;

    private String financeRequestId;

    private LocalDate repaymentDueDate;

    private String totalRepaymentAmount;

    private String amountRepayed;

    private String amountToBePaid;

    private String sourceAccountName;

    private String sourceAccountNumber;

    private String ifscCode;

    private String status;

    private String referenceNumber;

    private FinanceRequestDTO financerequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRepaymentId() {
        return repaymentId;
    }

    public void setRepaymentId(String repaymentId) {
        this.repaymentId = repaymentId;
    }

    public String getRepaymentRefNo() {
        return repaymentRefNo;
    }

    public void setRepaymentRefNo(String repaymentRefNo) {
        this.repaymentRefNo = repaymentRefNo;
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

    public String getDbmtstatus() {
        return dbmtstatus;
    }

    public void setDbmtstatus(String dbmtstatus) {
        this.dbmtstatus = dbmtstatus;
    }

    public String getDbmtDateTime() {
        return dbmtDateTime;
    }

    public void setDbmtDateTime(String dbmtDateTime) {
        this.dbmtDateTime = dbmtDateTime;
    }

    public String getDbmtId() {
        return dbmtId;
    }

    public void setDbmtId(String dbmtId) {
        this.dbmtId = dbmtId;
    }

    public Long getDbmtAmount() {
        return dbmtAmount;
    }

    public void setDbmtAmount(Long dbmtAmount) {
        this.dbmtAmount = dbmtAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRepaymentStatus() {
        return repaymentStatus;
    }

    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public LocalDate getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(LocalDate repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Long getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Long repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
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

    public String getFinanceRequestId() {
        return financeRequestId;
    }

    public void setFinanceRequestId(String financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public LocalDate getRepaymentDueDate() {
        return repaymentDueDate;
    }

    public void setRepaymentDueDate(LocalDate repaymentDueDate) {
        this.repaymentDueDate = repaymentDueDate;
    }

    public String getTotalRepaymentAmount() {
        return totalRepaymentAmount;
    }

    public void setTotalRepaymentAmount(String totalRepaymentAmount) {
        this.totalRepaymentAmount = totalRepaymentAmount;
    }

    public String getAmountRepayed() {
        return amountRepayed;
    }

    public void setAmountRepayed(String amountRepayed) {
        this.amountRepayed = amountRepayed;
    }

    public String getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(String amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public String getSourceAccountName() {
        return sourceAccountName;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
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

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public FinanceRequestDTO getFinancerequest() {
        return financerequest;
    }

    public void setFinancerequest(FinanceRequestDTO financerequest) {
        this.financerequest = financerequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RepaymentDTO)) {
            return false;
        }

        RepaymentDTO repaymentDTO = (RepaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, repaymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RepaymentDTO{" +
            "id=" + getId() +
            ", repaymentId='" + getRepaymentId() + "'" +
            ", repaymentRefNo='" + getRepaymentRefNo() + "'" +
            ", acceptedOfferId=" + getAcceptedOfferId() +
            ", offerId=" + getOfferId() +
            ", offerAcceptedDate='" + getOfferAcceptedDate() + "'" +
            ", dbmtRequestId='" + getDbmtRequestId() + "'" +
            ", dbmtstatus='" + getDbmtstatus() + "'" +
            ", dbmtDateTime='" + getDbmtDateTime() + "'" +
            ", dbmtId='" + getDbmtId() + "'" +
            ", dbmtAmount=" + getDbmtAmount() +
            ", currency='" + getCurrency() + "'" +
            ", repaymentStatus='" + getRepaymentStatus() + "'" +
            ", repaymentDate='" + getRepaymentDate() + "'" +
            ", repaymentAmount=" + getRepaymentAmount() +
            ", ftTrnxDetailsId='" + getFtTrnxDetailsId() + "'" +
            ", collectionTrnxDetailsId='" + getCollectionTrnxDetailsId() + "'" +
            ", docId=" + getDocId() +
            ", financeRequestId='" + getFinanceRequestId() + "'" +
            ", repaymentDueDate='" + getRepaymentDueDate() + "'" +
            ", totalRepaymentAmount='" + getTotalRepaymentAmount() + "'" +
            ", amountRepayed='" + getAmountRepayed() + "'" +
            ", amountToBePaid='" + getAmountToBePaid() + "'" +
            ", sourceAccountName='" + getSourceAccountName() + "'" +
            ", sourceAccountNumber='" + getSourceAccountNumber() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            ", financerequest=" + getFinancerequest() +
            "}";
    }
}
