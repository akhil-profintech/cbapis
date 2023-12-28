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
 * A Disbursement.
 */
@Table("disbursement")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Disbursement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("dbmt_id")
    private String dbmtId;

    @Column("disbursement_ref_no")
    private String disbursementRefNo;

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
    @Column("dbmt_req_amount")
    private Long dbmtReqAmount;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @NotNull(message = "must not be null")
    @Column("dbmt_request_date")
    private LocalDate dbmtRequestDate;

    @NotNull(message = "must not be null")
    @Column("dbmtstatus")
    private String dbmtstatus;

    @Column("offer_status")
    private String offerStatus;

    @Column("ft_trnx_details_id")
    private String ftTrnxDetailsId;

    @Column("collection_trnx_details_id")
    private String collectionTrnxDetailsId;

    @Column("doc_id")
    private Long docId;

    @Column("dbmt_date_time")
    private String dbmtDateTime;

    @Column("dbmt_amount")
    private Long dbmtAmount;

    @Column("finance_request_id")
    private String financeRequestId;

    @Column("amount_to_be_disbursed")
    private String amountToBeDisbursed;

    @Column("destination_account_name")
    private String destinationAccountName;

    @Column("destination_account_number")
    private String destinationAccountNumber;

    @Column("ifsc_code")
    private String ifscCode;

    @Column("status")
    private String status;

    @Column("action_by_date")
    private String actionByDate;

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

    @Transient
    @JsonIgnoreProperties(value = { "disbursements", "placedOffers", "acceptedOffers" }, allowSetters = true)
    private FinancePartner financepartner;

    @Column("financerequest_id")
    private Long financerequestId;

    @Column("financepartner_id")
    private Long financepartnerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Disbursement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDbmtId() {
        return this.dbmtId;
    }

    public Disbursement dbmtId(String dbmtId) {
        this.setDbmtId(dbmtId);
        return this;
    }

    public void setDbmtId(String dbmtId) {
        this.dbmtId = dbmtId;
    }

    public String getDisbursementRefNo() {
        return this.disbursementRefNo;
    }

    public Disbursement disbursementRefNo(String disbursementRefNo) {
        this.setDisbursementRefNo(disbursementRefNo);
        return this;
    }

    public void setDisbursementRefNo(String disbursementRefNo) {
        this.disbursementRefNo = disbursementRefNo;
    }

    public Long getAcceptedOfferId() {
        return this.acceptedOfferId;
    }

    public Disbursement acceptedOfferId(Long acceptedOfferId) {
        this.setAcceptedOfferId(acceptedOfferId);
        return this;
    }

    public void setAcceptedOfferId(Long acceptedOfferId) {
        this.acceptedOfferId = acceptedOfferId;
    }

    public Long getOfferId() {
        return this.offerId;
    }

    public Disbursement offerId(Long offerId) {
        this.setOfferId(offerId);
        return this;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public LocalDate getOfferAcceptedDate() {
        return this.offerAcceptedDate;
    }

    public Disbursement offerAcceptedDate(LocalDate offerAcceptedDate) {
        this.setOfferAcceptedDate(offerAcceptedDate);
        return this;
    }

    public void setOfferAcceptedDate(LocalDate offerAcceptedDate) {
        this.offerAcceptedDate = offerAcceptedDate;
    }

    public String getDbmtRequestId() {
        return this.dbmtRequestId;
    }

    public Disbursement dbmtRequestId(String dbmtRequestId) {
        this.setDbmtRequestId(dbmtRequestId);
        return this;
    }

    public void setDbmtRequestId(String dbmtRequestId) {
        this.dbmtRequestId = dbmtRequestId;
    }

    public Long getDbmtReqAmount() {
        return this.dbmtReqAmount;
    }

    public Disbursement dbmtReqAmount(Long dbmtReqAmount) {
        this.setDbmtReqAmount(dbmtReqAmount);
        return this;
    }

    public void setDbmtReqAmount(Long dbmtReqAmount) {
        this.dbmtReqAmount = dbmtReqAmount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Disbursement currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDate getDbmtRequestDate() {
        return this.dbmtRequestDate;
    }

    public Disbursement dbmtRequestDate(LocalDate dbmtRequestDate) {
        this.setDbmtRequestDate(dbmtRequestDate);
        return this;
    }

    public void setDbmtRequestDate(LocalDate dbmtRequestDate) {
        this.dbmtRequestDate = dbmtRequestDate;
    }

    public String getDbmtstatus() {
        return this.dbmtstatus;
    }

    public Disbursement dbmtstatus(String dbmtstatus) {
        this.setDbmtstatus(dbmtstatus);
        return this;
    }

    public void setDbmtstatus(String dbmtstatus) {
        this.dbmtstatus = dbmtstatus;
    }

    public String getOfferStatus() {
        return this.offerStatus;
    }

    public Disbursement offerStatus(String offerStatus) {
        this.setOfferStatus(offerStatus);
        return this;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String getFtTrnxDetailsId() {
        return this.ftTrnxDetailsId;
    }

    public Disbursement ftTrnxDetailsId(String ftTrnxDetailsId) {
        this.setFtTrnxDetailsId(ftTrnxDetailsId);
        return this;
    }

    public void setFtTrnxDetailsId(String ftTrnxDetailsId) {
        this.ftTrnxDetailsId = ftTrnxDetailsId;
    }

    public String getCollectionTrnxDetailsId() {
        return this.collectionTrnxDetailsId;
    }

    public Disbursement collectionTrnxDetailsId(String collectionTrnxDetailsId) {
        this.setCollectionTrnxDetailsId(collectionTrnxDetailsId);
        return this;
    }

    public void setCollectionTrnxDetailsId(String collectionTrnxDetailsId) {
        this.collectionTrnxDetailsId = collectionTrnxDetailsId;
    }

    public Long getDocId() {
        return this.docId;
    }

    public Disbursement docId(Long docId) {
        this.setDocId(docId);
        return this;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDbmtDateTime() {
        return this.dbmtDateTime;
    }

    public Disbursement dbmtDateTime(String dbmtDateTime) {
        this.setDbmtDateTime(dbmtDateTime);
        return this;
    }

    public void setDbmtDateTime(String dbmtDateTime) {
        this.dbmtDateTime = dbmtDateTime;
    }

    public Long getDbmtAmount() {
        return this.dbmtAmount;
    }

    public Disbursement dbmtAmount(Long dbmtAmount) {
        this.setDbmtAmount(dbmtAmount);
        return this;
    }

    public void setDbmtAmount(Long dbmtAmount) {
        this.dbmtAmount = dbmtAmount;
    }

    public String getFinanceRequestId() {
        return this.financeRequestId;
    }

    public Disbursement financeRequestId(String financeRequestId) {
        this.setFinanceRequestId(financeRequestId);
        return this;
    }

    public void setFinanceRequestId(String financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public String getAmountToBeDisbursed() {
        return this.amountToBeDisbursed;
    }

    public Disbursement amountToBeDisbursed(String amountToBeDisbursed) {
        this.setAmountToBeDisbursed(amountToBeDisbursed);
        return this;
    }

    public void setAmountToBeDisbursed(String amountToBeDisbursed) {
        this.amountToBeDisbursed = amountToBeDisbursed;
    }

    public String getDestinationAccountName() {
        return this.destinationAccountName;
    }

    public Disbursement destinationAccountName(String destinationAccountName) {
        this.setDestinationAccountName(destinationAccountName);
        return this;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    public Disbursement destinationAccountNumber(String destinationAccountNumber) {
        this.setDestinationAccountNumber(destinationAccountNumber);
        return this;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public Disbursement ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getStatus() {
        return this.status;
    }

    public Disbursement status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionByDate() {
        return this.actionByDate;
    }

    public Disbursement actionByDate(String actionByDate) {
        this.setActionByDate(actionByDate);
        return this;
    }

    public void setActionByDate(String actionByDate) {
        this.actionByDate = actionByDate;
    }

    public Set<CreditAccountDetails> getCreditAccountDetails() {
        return this.creditAccountDetails;
    }

    public void setCreditAccountDetails(Set<CreditAccountDetails> creditAccountDetails) {
        if (this.creditAccountDetails != null) {
            this.creditAccountDetails.forEach(i -> i.setDisbursement(null));
        }
        if (creditAccountDetails != null) {
            creditAccountDetails.forEach(i -> i.setDisbursement(this));
        }
        this.creditAccountDetails = creditAccountDetails;
    }

    public Disbursement creditAccountDetails(Set<CreditAccountDetails> creditAccountDetails) {
        this.setCreditAccountDetails(creditAccountDetails);
        return this;
    }

    public Disbursement addCreditAccountDetails(CreditAccountDetails creditAccountDetails) {
        this.creditAccountDetails.add(creditAccountDetails);
        creditAccountDetails.setDisbursement(this);
        return this;
    }

    public Disbursement removeCreditAccountDetails(CreditAccountDetails creditAccountDetails) {
        this.creditAccountDetails.remove(creditAccountDetails);
        creditAccountDetails.setDisbursement(null);
        return this;
    }

    public Set<EscrowAccountDetails> getEscrowAccountDetails() {
        return this.escrowAccountDetails;
    }

    public void setEscrowAccountDetails(Set<EscrowAccountDetails> escrowAccountDetails) {
        if (this.escrowAccountDetails != null) {
            this.escrowAccountDetails.forEach(i -> i.setDisbursement(null));
        }
        if (escrowAccountDetails != null) {
            escrowAccountDetails.forEach(i -> i.setDisbursement(this));
        }
        this.escrowAccountDetails = escrowAccountDetails;
    }

    public Disbursement escrowAccountDetails(Set<EscrowAccountDetails> escrowAccountDetails) {
        this.setEscrowAccountDetails(escrowAccountDetails);
        return this;
    }

    public Disbursement addEscrowAccountDetails(EscrowAccountDetails escrowAccountDetails) {
        this.escrowAccountDetails.add(escrowAccountDetails);
        escrowAccountDetails.setDisbursement(this);
        return this;
    }

    public Disbursement removeEscrowAccountDetails(EscrowAccountDetails escrowAccountDetails) {
        this.escrowAccountDetails.remove(escrowAccountDetails);
        escrowAccountDetails.setDisbursement(null);
        return this;
    }

    public Set<DocDetails> getDocDetails() {
        return this.docDetails;
    }

    public void setDocDetails(Set<DocDetails> docDetails) {
        if (this.docDetails != null) {
            this.docDetails.forEach(i -> i.setDisbursement(null));
        }
        if (docDetails != null) {
            docDetails.forEach(i -> i.setDisbursement(this));
        }
        this.docDetails = docDetails;
    }

    public Disbursement docDetails(Set<DocDetails> docDetails) {
        this.setDocDetails(docDetails);
        return this;
    }

    public Disbursement addDocDetails(DocDetails docDetails) {
        this.docDetails.add(docDetails);
        docDetails.setDisbursement(this);
        return this;
    }

    public Disbursement removeDocDetails(DocDetails docDetails) {
        this.docDetails.remove(docDetails);
        docDetails.setDisbursement(null);
        return this;
    }

    public Set<FTTransactionDetails> getFTTransactionDetails() {
        return this.fTTransactionDetails;
    }

    public void setFTTransactionDetails(Set<FTTransactionDetails> fTTransactionDetails) {
        if (this.fTTransactionDetails != null) {
            this.fTTransactionDetails.forEach(i -> i.setDisbursement(null));
        }
        if (fTTransactionDetails != null) {
            fTTransactionDetails.forEach(i -> i.setDisbursement(this));
        }
        this.fTTransactionDetails = fTTransactionDetails;
    }

    public Disbursement fTTransactionDetails(Set<FTTransactionDetails> fTTransactionDetails) {
        this.setFTTransactionDetails(fTTransactionDetails);
        return this;
    }

    public Disbursement addFTTransactionDetails(FTTransactionDetails fTTransactionDetails) {
        this.fTTransactionDetails.add(fTTransactionDetails);
        fTTransactionDetails.setDisbursement(this);
        return this;
    }

    public Disbursement removeFTTransactionDetails(FTTransactionDetails fTTransactionDetails) {
        this.fTTransactionDetails.remove(fTTransactionDetails);
        fTTransactionDetails.setDisbursement(null);
        return this;
    }

    public Set<CollectionTransactionDetails> getCollectionTransactionDetails() {
        return this.collectionTransactionDetails;
    }

    public void setCollectionTransactionDetails(Set<CollectionTransactionDetails> collectionTransactionDetails) {
        if (this.collectionTransactionDetails != null) {
            this.collectionTransactionDetails.forEach(i -> i.setDisbursement(null));
        }
        if (collectionTransactionDetails != null) {
            collectionTransactionDetails.forEach(i -> i.setDisbursement(this));
        }
        this.collectionTransactionDetails = collectionTransactionDetails;
    }

    public Disbursement collectionTransactionDetails(Set<CollectionTransactionDetails> collectionTransactionDetails) {
        this.setCollectionTransactionDetails(collectionTransactionDetails);
        return this;
    }

    public Disbursement addCollectionTransactionDetails(CollectionTransactionDetails collectionTransactionDetails) {
        this.collectionTransactionDetails.add(collectionTransactionDetails);
        collectionTransactionDetails.setDisbursement(this);
        return this;
    }

    public Disbursement removeCollectionTransactionDetails(CollectionTransactionDetails collectionTransactionDetails) {
        this.collectionTransactionDetails.remove(collectionTransactionDetails);
        collectionTransactionDetails.setDisbursement(null);
        return this;
    }

    public FinanceRequest getFinancerequest() {
        return this.financerequest;
    }

    public void setFinancerequest(FinanceRequest financeRequest) {
        this.financerequest = financeRequest;
        this.financerequestId = financeRequest != null ? financeRequest.getId() : null;
    }

    public Disbursement financerequest(FinanceRequest financeRequest) {
        this.setFinancerequest(financeRequest);
        return this;
    }

    public FinancePartner getFinancepartner() {
        return this.financepartner;
    }

    public void setFinancepartner(FinancePartner financePartner) {
        this.financepartner = financePartner;
        this.financepartnerId = financePartner != null ? financePartner.getId() : null;
    }

    public Disbursement financepartner(FinancePartner financePartner) {
        this.setFinancepartner(financePartner);
        return this;
    }

    public Long getFinancerequestId() {
        return this.financerequestId;
    }

    public void setFinancerequestId(Long financeRequest) {
        this.financerequestId = financeRequest;
    }

    public Long getFinancepartnerId() {
        return this.financepartnerId;
    }

    public void setFinancepartnerId(Long financePartner) {
        this.financepartnerId = financePartner;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Disbursement)) {
            return false;
        }
        return getId() != null && getId().equals(((Disbursement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Disbursement{" +
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
            "}";
    }
}
