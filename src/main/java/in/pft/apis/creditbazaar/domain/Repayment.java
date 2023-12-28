package in.pft.apis.creditbazaar.domain;

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
    private String repaymentId;

    @Column("repayment_ref_no")
    private String repaymentRefNo;

    @NotNull(message = "must not be null")
    @Column("accepted_offer_id")
    private Long acceptedOfferId;

    @NotNull(message = "must not be null")
    @Column("offer_id")
    private Long offerId;

    @NotNull(message = "must not be null")
    @Column("offer_accepted_date")
    private LocalDate offerAcceptedDate;

    @NotNull(message = "must not be null")
    @Column("dbmt_request_id")
    private String dbmtRequestId;

    @NotNull(message = "must not be null")
    @Column("dbmtstatus")
    private String dbmtstatus;

    @NotNull(message = "must not be null")
    @Column("dbmt_date_time")
    private String dbmtDateTime;

    @NotNull(message = "must not be null")
    @Column("dbmt_id")
    private String dbmtId;

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

    @Column("ft_trnx_details_id")
    private String ftTrnxDetailsId;

    @Column("collection_trnx_details_id")
    private String collectionTrnxDetailsId;

    @Column("doc_id")
    private Long docId;

    @Column("finance_request_id")
    private String financeRequestId;

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

    @Column("status")
    private String status;

    @Column("reference_number")
    private String referenceNumber;

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment" }, allowSetters = true)
    private Set<CreditAccountDetails> creditAccountDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment" }, allowSetters = true)
    private Set<EscrowAccountDetails> escrowAccountDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment", "participantsettlement" }, allowSetters = true)
    private Set<DocDetails> docDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment", "participantsettlement" }, allowSetters = true)
    private Set<FTTransactionDetails> fTTransactionDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "disbursement", "repayment" }, allowSetters = true)
    private Set<CollectionTransactionDetails> collectionTransactionDetails = new HashSet<>();

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

    public String getRepaymentId() {
        return this.repaymentId;
    }

    public Repayment repaymentId(String repaymentId) {
        this.setRepaymentId(repaymentId);
        return this;
    }

    public void setRepaymentId(String repaymentId) {
        this.repaymentId = repaymentId;
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

    public Long getAcceptedOfferId() {
        return this.acceptedOfferId;
    }

    public Repayment acceptedOfferId(Long acceptedOfferId) {
        this.setAcceptedOfferId(acceptedOfferId);
        return this;
    }

    public void setAcceptedOfferId(Long acceptedOfferId) {
        this.acceptedOfferId = acceptedOfferId;
    }

    public Long getOfferId() {
        return this.offerId;
    }

    public Repayment offerId(Long offerId) {
        this.setOfferId(offerId);
        return this;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
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

    public String getDbmtstatus() {
        return this.dbmtstatus;
    }

    public Repayment dbmtstatus(String dbmtstatus) {
        this.setDbmtstatus(dbmtstatus);
        return this;
    }

    public void setDbmtstatus(String dbmtstatus) {
        this.dbmtstatus = dbmtstatus;
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

    public String getDbmtId() {
        return this.dbmtId;
    }

    public Repayment dbmtId(String dbmtId) {
        this.setDbmtId(dbmtId);
        return this;
    }

    public void setDbmtId(String dbmtId) {
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

    public String getFtTrnxDetailsId() {
        return this.ftTrnxDetailsId;
    }

    public Repayment ftTrnxDetailsId(String ftTrnxDetailsId) {
        this.setFtTrnxDetailsId(ftTrnxDetailsId);
        return this;
    }

    public void setFtTrnxDetailsId(String ftTrnxDetailsId) {
        this.ftTrnxDetailsId = ftTrnxDetailsId;
    }

    public String getCollectionTrnxDetailsId() {
        return this.collectionTrnxDetailsId;
    }

    public Repayment collectionTrnxDetailsId(String collectionTrnxDetailsId) {
        this.setCollectionTrnxDetailsId(collectionTrnxDetailsId);
        return this;
    }

    public void setCollectionTrnxDetailsId(String collectionTrnxDetailsId) {
        this.collectionTrnxDetailsId = collectionTrnxDetailsId;
    }

    public Long getDocId() {
        return this.docId;
    }

    public Repayment docId(Long docId) {
        this.setDocId(docId);
        return this;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getFinanceRequestId() {
        return this.financeRequestId;
    }

    public Repayment financeRequestId(String financeRequestId) {
        this.setFinanceRequestId(financeRequestId);
        return this;
    }

    public void setFinanceRequestId(String financeRequestId) {
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

    public String getStatus() {
        return this.status;
    }

    public Repayment status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Set<EscrowAccountDetails> getEscrowAccountDetails() {
        return this.escrowAccountDetails;
    }

    public void setEscrowAccountDetails(Set<EscrowAccountDetails> escrowAccountDetails) {
        if (this.escrowAccountDetails != null) {
            this.escrowAccountDetails.forEach(i -> i.setRepayment(null));
        }
        if (escrowAccountDetails != null) {
            escrowAccountDetails.forEach(i -> i.setRepayment(this));
        }
        this.escrowAccountDetails = escrowAccountDetails;
    }

    public Repayment escrowAccountDetails(Set<EscrowAccountDetails> escrowAccountDetails) {
        this.setEscrowAccountDetails(escrowAccountDetails);
        return this;
    }

    public Repayment addEscrowAccountDetails(EscrowAccountDetails escrowAccountDetails) {
        this.escrowAccountDetails.add(escrowAccountDetails);
        escrowAccountDetails.setRepayment(this);
        return this;
    }

    public Repayment removeEscrowAccountDetails(EscrowAccountDetails escrowAccountDetails) {
        this.escrowAccountDetails.remove(escrowAccountDetails);
        escrowAccountDetails.setRepayment(null);
        return this;
    }

    public Set<DocDetails> getDocDetails() {
        return this.docDetails;
    }

    public void setDocDetails(Set<DocDetails> docDetails) {
        if (this.docDetails != null) {
            this.docDetails.forEach(i -> i.setRepayment(null));
        }
        if (docDetails != null) {
            docDetails.forEach(i -> i.setRepayment(this));
        }
        this.docDetails = docDetails;
    }

    public Repayment docDetails(Set<DocDetails> docDetails) {
        this.setDocDetails(docDetails);
        return this;
    }

    public Repayment addDocDetails(DocDetails docDetails) {
        this.docDetails.add(docDetails);
        docDetails.setRepayment(this);
        return this;
    }

    public Repayment removeDocDetails(DocDetails docDetails) {
        this.docDetails.remove(docDetails);
        docDetails.setRepayment(null);
        return this;
    }

    public Set<FTTransactionDetails> getFTTransactionDetails() {
        return this.fTTransactionDetails;
    }

    public void setFTTransactionDetails(Set<FTTransactionDetails> fTTransactionDetails) {
        if (this.fTTransactionDetails != null) {
            this.fTTransactionDetails.forEach(i -> i.setRepayment(null));
        }
        if (fTTransactionDetails != null) {
            fTTransactionDetails.forEach(i -> i.setRepayment(this));
        }
        this.fTTransactionDetails = fTTransactionDetails;
    }

    public Repayment fTTransactionDetails(Set<FTTransactionDetails> fTTransactionDetails) {
        this.setFTTransactionDetails(fTTransactionDetails);
        return this;
    }

    public Repayment addFTTransactionDetails(FTTransactionDetails fTTransactionDetails) {
        this.fTTransactionDetails.add(fTTransactionDetails);
        fTTransactionDetails.setRepayment(this);
        return this;
    }

    public Repayment removeFTTransactionDetails(FTTransactionDetails fTTransactionDetails) {
        this.fTTransactionDetails.remove(fTTransactionDetails);
        fTTransactionDetails.setRepayment(null);
        return this;
    }

    public Set<CollectionTransactionDetails> getCollectionTransactionDetails() {
        return this.collectionTransactionDetails;
    }

    public void setCollectionTransactionDetails(Set<CollectionTransactionDetails> collectionTransactionDetails) {
        if (this.collectionTransactionDetails != null) {
            this.collectionTransactionDetails.forEach(i -> i.setRepayment(null));
        }
        if (collectionTransactionDetails != null) {
            collectionTransactionDetails.forEach(i -> i.setRepayment(this));
        }
        this.collectionTransactionDetails = collectionTransactionDetails;
    }

    public Repayment collectionTransactionDetails(Set<CollectionTransactionDetails> collectionTransactionDetails) {
        this.setCollectionTransactionDetails(collectionTransactionDetails);
        return this;
    }

    public Repayment addCollectionTransactionDetails(CollectionTransactionDetails collectionTransactionDetails) {
        this.collectionTransactionDetails.add(collectionTransactionDetails);
        collectionTransactionDetails.setRepayment(this);
        return this;
    }

    public Repayment removeCollectionTransactionDetails(CollectionTransactionDetails collectionTransactionDetails) {
        this.collectionTransactionDetails.remove(collectionTransactionDetails);
        collectionTransactionDetails.setRepayment(null);
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
            "}";
    }
}
