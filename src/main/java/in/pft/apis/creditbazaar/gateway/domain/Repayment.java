package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Repayment.
 */
@Table("repayment")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Repayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("repayment_id")
    private Long repaymentId;

    @Column("repayment_ulid_id")
    private String repaymentUlidId;

    @Column("repayment_ref_no")
    private String repaymentRefNo;

    @NotNull(message = "must not be null")
    @Column("accepted_offer_ulid_id")
    private String acceptedOfferUlidId;

    @NotNull(message = "must not be null")
    @Column("placed_offer_ulid_id")
    private String placedOfferUlidId;

    @NotNull(message = "must not be null")
    @Column("req_off_ulid_id")
    private String reqOffUlidId;

    @NotNull(message = "must not be null")
    @Column("offer_accepted_date")
    private LocalDate offerAcceptedDate;

    @NotNull(message = "must not be null")
    @Column("dbmt_request_id")
    private String dbmtRequestId;

    @NotNull(message = "must not be null")
    @Column("dbmt_status")
    private String dbmtStatus;

    @NotNull(message = "must not be null")
    @Column("dbmt_date_time")
    private String dbmtDateTime;

    @NotNull(message = "must not be null")
    @Column("dbmt_id")
    private Long dbmtId;

    @NotNull(message = "must not be null")
    @Column("dbmt_amount")
    private Long dbmtAmount;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @NotNull(message = "must not be null")
    @Column("repayment_status")
    private String repaymentStatus;

    @Column("repayment_date")
    private LocalDate repaymentDate;

    @Column("repayment_amount")
    private Long repaymentAmount;

    @Column("finance_request_id")
    private Long financeRequestId;

    @Column("repayment_due_date")
    private LocalDate repaymentDueDate;

    @Column("total_repayment_amount")
    private String totalRepaymentAmount;

    @Column("amount_repayed")
    private String amountRepayed;

    @Column("amount_to_be_paid")
    private String amountToBePaid;

    @Column("source_account_name")
    private String sourceAccountName;

    @Column("source_account_number")
    private String sourceAccountNumber;

    @Column("ifsc_code")
    private String ifscCode;

    @Column("record_status")
    private String recordStatus;

    @Column("reference_number")
    private String referenceNumber;

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment" }, allowSetters = true)
    private Set<CreditAccountDetails> creditAccountDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "participantsettlement", "disbursement", "repayment" }, allowSetters = true)
    private Set<FundsTransferTransactionDetails> fundsTransferTransactionDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment" }, allowSetters = true)
    private Set<EscrowTransactionDetails> escrowTransactionDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(
        value = {
            "requestOffers",
            "trades",
            "placedOffers",
            "acceptedOffers",
            "disbursements",
            "docDetails",
            "repayments",
            "settlements",
            "cBCREProcesses",
            "anchortrader",
        },
        allowSetters = true
    )
    private FinanceRequest financerequest;

    @Column("financerequest_id")
    private Long financerequestId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Repayment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRepaymentId() {
        return this.repaymentId;
    }

    public Repayment repaymentId(Long repaymentId) {
        this.setRepaymentId(repaymentId);
        return this;
    }

    public void setRepaymentId(Long repaymentId) {
        this.repaymentId = repaymentId;
    }

    public String getRepaymentUlidId() {
        return this.repaymentUlidId;
    }

    public Repayment repaymentUlidId(String repaymentUlidId) {
        this.setRepaymentUlidId(repaymentUlidId);
        return this;
    }

    public void setRepaymentUlidId(String repaymentUlidId) {
        this.repaymentUlidId = repaymentUlidId;
    }

    public String getRepaymentRefNo() {
        return this.repaymentRefNo;
    }

    public Repayment repaymentRefNo(String repaymentRefNo) {
        this.setRepaymentRefNo(repaymentRefNo);
        return this;
    }

    public void setRepaymentRefNo(String repaymentRefNo) {
        this.repaymentRefNo = repaymentRefNo;
    }

    public String getAcceptedOfferUlidId() {
        return this.acceptedOfferUlidId;
    }

    public Repayment acceptedOfferUlidId(String acceptedOfferUlidId) {
        this.setAcceptedOfferUlidId(acceptedOfferUlidId);
        return this;
    }

    public void setAcceptedOfferUlidId(String acceptedOfferUlidId) {
        this.acceptedOfferUlidId = acceptedOfferUlidId;
    }

    public String getPlacedOfferUlidId() {
        return this.placedOfferUlidId;
    }

    public Repayment placedOfferUlidId(String placedOfferUlidId) {
        this.setPlacedOfferUlidId(placedOfferUlidId);
        return this;
    }

    public void setPlacedOfferUlidId(String placedOfferUlidId) {
        this.placedOfferUlidId = placedOfferUlidId;
    }

    public String getReqOffUlidId() {
        return this.reqOffUlidId;
    }

    public Repayment reqOffUlidId(String reqOffUlidId) {
        this.setReqOffUlidId(reqOffUlidId);
        return this;
    }

    public void setReqOffUlidId(String reqOffUlidId) {
        this.reqOffUlidId = reqOffUlidId;
    }

    public LocalDate getOfferAcceptedDate() {
        return this.offerAcceptedDate;
    }

    public Repayment offerAcceptedDate(LocalDate offerAcceptedDate) {
        this.setOfferAcceptedDate(offerAcceptedDate);
        return this;
    }

    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }

    public String getDbmtRequestId() {
        return this.dbmtRequestId;
    }

    public Repayment dbmtRequestId(String dbmtRequestId) {
        this.setDbmtRequestId(dbmtRequestId);
        return this;
    }

    public void setDbmtRequestId(String dbmtRequestId) {
        this.dbmtRequestId = dbmtRequestId;
    }

    public String getDbmtStatus() {
        return this.dbmtStatus;
    }

    public Repayment dbmtStatus(String dbmtStatus) {
        this.setDbmtStatus(dbmtStatus);
        return this;
    }

    public void setDbmtStatus(String dbmtStatus) {
        this.dbmtStatus = dbmtStatus;
    }

    public String getDbmtDateTime() {
        return this.dbmtDateTime;
    }

    public Repayment dbmtDateTime(String dbmtDateTime) {
        this.setDbmtDateTime(dbmtDateTime);
        return this;
    }

    public void setDbmtDateTime(String dbmtDateTime) {
        this.dbmtDateTime = dbmtDateTime;
    }

    public Long getDbmtId() {
        return this.dbmtId;
    }

    public Repayment dbmtId(Long dbmtId) {
        this.setDbmtId(dbmtId);
        return this;
    }

    public void setDbmtId(Long dbmtId) {
        this.dbmtId = dbmtId;
    }

    public Long getDbmtAmount() {
        return this.dbmtAmount;
    }

    public Repayment dbmtAmount(Long dbmtAmount) {
        this.setDbmtAmount(dbmtAmount);
        return this;
    }

    public void setDbmtAmount(Long dbmtAmount) {
        this.dbmtAmount = dbmtAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Repayment currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRepaymentStatus() {
        return this.repaymentStatus;
    }

    public Repayment repaymentStatus(String repaymentStatus) {
        this.setRepaymentStatus(repaymentStatus);
        return this;
    }

    public void setRepaymentStatus(String repaymentStatus) {
        this.repaymentStatus = repaymentStatus;
    }

    public LocalDate getRepaymentDate() {
        return this.repaymentDate;
    }

    public Repayment repaymentDate(LocalDate repaymentDate) {
        this.setRepaymentDate(repaymentDate);
        return this;
    }

    public void setRepaymentDate(LocalDate repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

    public Long getRepaymentAmount() {
        return this.repaymentAmount;
    }

    public Repayment repaymentAmount(Long repaymentAmount) {
        this.setRepaymentAmount(repaymentAmount);
        return this;
    }

    public void setRepaymentAmount(Long repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public Long getFinanceRequestId() {
        return this.financeRequestId;
    }

    public Repayment financeRequestId(Long financeRequestId) {
        this.setFinanceRequestId(financeRequestId);
        return this;
    }

    public void setFinanceRequestId(Long financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public LocalDate getRepaymentDueDate() {
        return this.repaymentDueDate;
    }

    public Repayment repaymentDueDate(LocalDate repaymentDueDate) {
        this.setRepaymentDueDate(repaymentDueDate);
        return this;
    }

    public void setRepaymentDueDate(LocalDate repaymentDueDate) {
        this.repaymentDueDate = repaymentDueDate;
    }

    public String getTotalRepaymentAmount() {
        return this.totalRepaymentAmount;
    }

    public Repayment totalRepaymentAmount(String totalRepaymentAmount) {
        this.setTotalRepaymentAmount(totalRepaymentAmount);
        return this;
    }

    public void setTotalRepaymentAmount(String totalRepaymentAmount) {
        this.totalRepaymentAmount = totalRepaymentAmount;
    }

    public String getAmountRepayed() {
        return this.amountRepayed;
    }

    public Repayment amountRepayed(String amountRepayed) {
        this.setAmountRepayed(amountRepayed);
        return this;
    }

    public void setAmountRepayed(String amountRepayed) {
        this.amountRepayed = amountRepayed;
    }

    public String getAmountToBePaid() {
        return this.amountToBePaid;
    }

    public Repayment amountToBePaid(String amountToBePaid) {
        this.setAmountToBePaid(amountToBePaid);
        return this;
    }

    public void setAmountToBePaid(String amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public String getSourceAccountName() {
        return this.sourceAccountName;
    }

    public Repayment sourceAccountName(String sourceAccountName) {
        this.setSourceAccountName(sourceAccountName);
        return this;
    }

    public void setSourceAccountName(String sourceAccountName) {
        this.sourceAccountName = sourceAccountName;
    }

    public String getSourceAccountNumber() {
        return this.sourceAccountNumber;
    }

    public Repayment sourceAccountNumber(String sourceAccountNumber) {
        this.setSourceAccountNumber(sourceAccountNumber);
        return this;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public Repayment ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public Repayment recordStatus(String recordStatus) {
        this.setRecordStatus(recordStatus);
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getReferenceNumber() {
        return this.referenceNumber;
    }

    public Repayment referenceNumber(String referenceNumber) {
        this.setReferenceNumber(referenceNumber);
        return this;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public Set<CreditAccountDetails> getCreditAccountDetails() {
        return this.creditAccountDetails;
    }

    public void setCreditAccountDetails(Set<CreditAccountDetails> creditAccountDetails) {
        if (this.creditAccountDetails != null) {
            this.creditAccountDetails.forEach(i -> i.setRepayment(null));
        }
        if (creditAccountDetails != null) {
            creditAccountDetails.forEach(i -> i.setRepayment(this));
        }
        this.creditAccountDetails = creditAccountDetails;
    }

    public Repayment creditAccountDetails(Set<CreditAccountDetails> creditAccountDetails) {
        this.setCreditAccountDetails(creditAccountDetails);
        return this;
    }

    public Repayment addCreditAccountDetails(CreditAccountDetails creditAccountDetails) {
        this.creditAccountDetails.add(creditAccountDetails);
        creditAccountDetails.setRepayment(this);
        return this;
    }

    public Repayment removeCreditAccountDetails(CreditAccountDetails creditAccountDetails) {
        this.creditAccountDetails.remove(creditAccountDetails);
        creditAccountDetails.setRepayment(null);
        return this;
    }

    public Set<FundsTransferTransactionDetails> getFundsTransferTransactionDetails() {
        return this.fundsTransferTransactionDetails;
    }

    public void setFundsTransferTransactionDetails(Set<FundsTransferTransactionDetails> fundsTransferTransactionDetails) {
        if (this.fundsTransferTransactionDetails != null) {
            this.fundsTransferTransactionDetails.forEach(i -> i.setRepayment(null));
        }
        if (fundsTransferTransactionDetails != null) {
            fundsTransferTransactionDetails.forEach(i -> i.setRepayment(this));
        }
        this.fundsTransferTransactionDetails = fundsTransferTransactionDetails;
    }

    public Repayment fundsTransferTransactionDetails(Set<FundsTransferTransactionDetails> fundsTransferTransactionDetails) {
        this.setFundsTransferTransactionDetails(fundsTransferTransactionDetails);
        return this;
    }

    public Repayment addFundsTransferTransactionDetails(FundsTransferTransactionDetails fundsTransferTransactionDetails) {
        this.fundsTransferTransactionDetails.add(fundsTransferTransactionDetails);
        fundsTransferTransactionDetails.setRepayment(this);
        return this;
    }

    public Repayment removeFundsTransferTransactionDetails(FundsTransferTransactionDetails fundsTransferTransactionDetails) {
        this.fundsTransferTransactionDetails.remove(fundsTransferTransactionDetails);
        fundsTransferTransactionDetails.setRepayment(null);
        return this;
    }

    public Set<EscrowTransactionDetails> getEscrowTransactionDetails() {
        return this.escrowTransactionDetails;
    }

    public void setEscrowTransactionDetails(Set<EscrowTransactionDetails> escrowTransactionDetails) {
        if (this.escrowTransactionDetails != null) {
            this.escrowTransactionDetails.forEach(i -> i.setRepayment(null));
        }
        if (escrowTransactionDetails != null) {
            escrowTransactionDetails.forEach(i -> i.setRepayment(this));
        }
        this.escrowTransactionDetails = escrowTransactionDetails;
    }

    public Repayment escrowTransactionDetails(Set<EscrowTransactionDetails> escrowTransactionDetails) {
        this.setEscrowTransactionDetails(escrowTransactionDetails);
        return this;
    }

    public Repayment addEscrowTransactionDetails(EscrowTransactionDetails escrowTransactionDetails) {
        this.escrowTransactionDetails.add(escrowTransactionDetails);
        escrowTransactionDetails.setRepayment(this);
        return this;
    }

    public Repayment removeEscrowTransactionDetails(EscrowTransactionDetails escrowTransactionDetails) {
        this.escrowTransactionDetails.remove(escrowTransactionDetails);
        escrowTransactionDetails.setRepayment(null);
        return this;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public Repayment financerequest(FinanceRequest financeRequest) {
        this.setFinancerequest(financeRequest);
        return this;
    }

    public Long getFinancerequestId() {
        return this.financerequestId;
    }

    public void setFinancerequestId(Long financeRequest) {
        this.financerequestId = financeRequest;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Repayment)) {
            return false;
        }
        return getId() != null && getId().equals(((Repayment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Repayment{" +
            "id=" + getId() +
            ", repaymentId=" + getRepaymentId() +
            ", repaymentUlidId='" + getRepaymentUlidId() + "'" +
            ", repaymentRefNo='" + getRepaymentRefNo() + "'" +
            ", acceptedOfferUlidId='" + getAcceptedOfferUlidId() + "'" +
            ", placedOfferUlidId='" + getPlacedOfferUlidId() + "'" +
            ", reqOffUlidId='" + getReqOffUlidId() + "'" +
            ", offerAcceptedDate='" + getOfferAcceptedDate() + "'" +
            ", dbmtRequestId='" + getDbmtRequestId() + "'" +
            ", dbmtStatus='" + getDbmtStatus() + "'" +
            ", dbmtDateTime='" + getDbmtDateTime() + "'" +
            ", dbmtId=" + getDbmtId() +
            ", dbmtAmount=" + getDbmtAmount() +
            ", currency='" + getCurrency() + "'" +
            ", repaymentStatus='" + getRepaymentStatus() + "'" +
            ", repaymentDate='" + getRepaymentDate() + "'" +
            ", repaymentAmount=" + getRepaymentAmount() +
            ", financeRequestId=" + getFinanceRequestId() +
            ", repaymentDueDate='" + getRepaymentDueDate() + "'" +
            ", totalRepaymentAmount='" + getTotalRepaymentAmount() + "'" +
            ", amountRepayed='" + getAmountRepayed() + "'" +
            ", amountToBePaid='" + getAmountToBePaid() + "'" +
            ", sourceAccountName='" + getSourceAccountName() + "'" +
            ", sourceAccountNumber='" + getSourceAccountNumber() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", referenceNumber='" + getReferenceNumber() + "'" +
            "}";
    }
}
