package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A FundsTransferTransactionDetails.
 */
@Table("funds_transfer_transaction_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FundsTransferTransactionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("ft_trnx_details_id")
    private Long ftTrnxDetailsId;

    @Column("ft_trnx_details_ulid")
    private String ftTrnxDetailsUlid;

    @NotNull(message = "must not be null")
    @Column("transaction_id")
    private Long transactionId;

    @NotNull(message = "must not be null")
    @Column("debit_account_number")
    private String debitAccountNumber;

    @NotNull(message = "must not be null")
    @Column("credit_account_number")
    private String creditAccountNumber;

    @NotNull(message = "must not be null")
    @Column("remitter_name")
    private String remitterName;

    @NotNull(message = "must not be null")
    @Column("amount")
    private Long amount;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @NotNull(message = "must not be null")
    @Column("transaction_type")
    private String transactionType;

    @NotNull(message = "must not be null")
    @Column("payment_description")
    private String paymentDescription;

    @NotNull(message = "must not be null")
    @Column("beneficiary_ifsc")
    private String beneficiaryIFSC;

    @NotNull(message = "must not be null")
    @Column("beneficiary_name")
    private String beneficiaryName;

    @NotNull(message = "must not be null")
    @Column("beneficiary_address")
    private String beneficiaryAddress;

    @NotNull(message = "must not be null")
    @Column("email_id")
    private String emailId;

    @NotNull(message = "must not be null")
    @Column("mobile_no")
    private Long mobileNo;

    @NotNull(message = "must not be null")
    @Column("transaction_ref_no")
    private String transactionRefNo;

    @NotNull(message = "must not be null")
    @Column("trnx_resource_data_status")
    private String trnxResourceDataStatus;

    @NotNull(message = "must not be null")
    @Column("trnx_meta_data_status")
    private String trnxMetaDataStatus;

    @NotNull(message = "must not be null")
    @Column("transaction_date_time")
    private String transactionDateTime;

    @Transient
    @JsonIgnoreProperties(value = { "fundsTransferTransactionDetails", "settlement" }, allowSetters = true)
    private ParticipantSettlement participantsettlement;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "creditAccountDetails", "fundsTransferTransactionDetails", "escrowTransactionDetails", "financerequest", "financepartner",
        },
        allowSetters = true
    )
    private Disbursement disbursement;

    @Transient
    @JsonIgnoreProperties(
        value = { "creditAccountDetails", "fundsTransferTransactionDetails", "escrowTransactionDetails", "financerequest" },
        allowSetters = true
    )
    private Repayment repayment;

    @Column("participantsettlement_id")
    private Long participantsettlementId;

    @Column("disbursement_id")
    private Long disbursementId;

    @Column("repayment_id")
    private Long repaymentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FundsTransferTransactionDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFtTrnxDetailsId() {
        return this.ftTrnxDetailsId;
    }

    public FundsTransferTransactionDetails ftTrnxDetailsId(Long ftTrnxDetailsId) {
        this.setFtTrnxDetailsId(ftTrnxDetailsId);
        return this;
    }

    public void setFtTrnxDetailsId(Long ftTrnxDetailsId) {
        this.ftTrnxDetailsId = ftTrnxDetailsId;
    }

    public String getFtTrnxDetailsUlid() {
        return this.ftTrnxDetailsUlid;
    }

    public FundsTransferTransactionDetails ftTrnxDetailsUlid(String ftTrnxDetailsUlid) {
        this.setFtTrnxDetailsUlid(ftTrnxDetailsUlid);
        return this;
    }

    public void setFtTrnxDetailsUlid(String ftTrnxDetailsUlid) {
        this.ftTrnxDetailsUlid = ftTrnxDetailsUlid;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public FundsTransferTransactionDetails transactionId(Long transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDebitAccountNumber() {
        return this.debitAccountNumber;
    }

    public FundsTransferTransactionDetails debitAccountNumber(String debitAccountNumber) {
        this.setDebitAccountNumber(debitAccountNumber);
        return this;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return this.creditAccountNumber;
    }

    public FundsTransferTransactionDetails creditAccountNumber(String creditAccountNumber) {
        this.setCreditAccountNumber(creditAccountNumber);
        return this;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getRemitterName() {
        return this.remitterName;
    }

    public FundsTransferTransactionDetails remitterName(String remitterName) {
        this.setRemitterName(remitterName);
        return this;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public Long getAmount() {
        return this.amount;
    }

    public FundsTransferTransactionDetails amount(Long amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public FundsTransferTransactionDetails currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public FundsTransferTransactionDetails transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentDescription() {
        return this.paymentDescription;
    }

    public FundsTransferTransactionDetails paymentDescription(String paymentDescription) {
        this.setPaymentDescription(paymentDescription);
        return this;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getBeneficiaryIFSC() {
        return this.beneficiaryIFSC;
    }

    public FundsTransferTransactionDetails beneficiaryIFSC(String beneficiaryIFSC) {
        this.setBeneficiaryIFSC(beneficiaryIFSC);
        return this;
    }

    public void setBeneficiaryIFSC(String beneficiaryIFSC) {
        this.beneficiaryIFSC = beneficiaryIFSC;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public FundsTransferTransactionDetails beneficiaryName(String beneficiaryName) {
        this.setBeneficiaryName(beneficiaryName);
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAddress() {
        return this.beneficiaryAddress;
    }

    public FundsTransferTransactionDetails beneficiaryAddress(String beneficiaryAddress) {
        this.setBeneficiaryAddress(beneficiaryAddress);
        return this;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public FundsTransferTransactionDetails emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getMobileNo() {
        return this.mobileNo;
    }

    public FundsTransferTransactionDetails mobileNo(Long mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTransactionRefNo() {
        return this.transactionRefNo;
    }

    public FundsTransferTransactionDetails transactionRefNo(String transactionRefNo) {
        this.setTransactionRefNo(transactionRefNo);
        return this;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getTrnxResourceDataStatus() {
        return this.trnxResourceDataStatus;
    }

    public FundsTransferTransactionDetails trnxResourceDataStatus(String trnxResourceDataStatus) {
        this.setTrnxResourceDataStatus(trnxResourceDataStatus);
        return this;
    }

    public void setTrnxResourceDataStatus(String trnxResourceDataStatus) {
        this.trnxResourceDataStatus = trnxResourceDataStatus;
    }

    public String getTrnxMetaDataStatus() {
        return this.trnxMetaDataStatus;
    }

    public FundsTransferTransactionDetails trnxMetaDataStatus(String trnxMetaDataStatus) {
        this.setTrnxMetaDataStatus(trnxMetaDataStatus);
        return this;
    }

    public void setTrnxMetaDataStatus(String trnxMetaDataStatus) {
        this.trnxMetaDataStatus = trnxMetaDataStatus;
    }

    public String getTransactionDateTime() {
        return this.transactionDateTime;
    }

    public FundsTransferTransactionDetails transactionDateTime(String transactionDateTime) {
        this.setTransactionDateTime(transactionDateTime);
        return this;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public ParticipantSettlement getParticipantsettlement() {
        return this.participantsettlement;
    }

    public void setParticipantsettlement(ParticipantSettlement participantSettlement) {
        this.participantsettlement = participantSettlement;
        this.participantsettlementId = participantSettlement != null ? participantSettlement.getId() : null;
    }

    public FundsTransferTransactionDetails participantsettlement(ParticipantSettlement participantSettlement) {
        this.setParticipantsettlement(participantSettlement);
        return this;
    }

    public Disbursement getDisbursement() {
        return this.disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
        this.disbursementId = disbursement != null ? disbursement.getId() : null;
    }

    public FundsTransferTransactionDetails disbursement(Disbursement disbursement) {
        this.setDisbursement(disbursement);
        return this;
    }

    public Repayment getRepayment() {
        return this.repayment;
    }

    public void setRepayment(Repayment repayment) {
        this.repayment = repayment;
        this.repaymentId = repayment != null ? repayment.getId() : null;
    }

    public FundsTransferTransactionDetails repayment(Repayment repayment) {
        this.setRepayment(repayment);
        return this;
    }

    public Long getParticipantsettlementId() {
        return this.participantsettlementId;
    }

    public void setParticipantsettlementId(Long participantSettlement) {
        this.participantsettlementId = participantSettlement;
    }

    public Long getDisbursementId() {
        return this.disbursementId;
    }

    public void setDisbursementId(Long disbursement) {
        this.disbursementId = disbursement;
    }

    public Long getRepaymentId() {
        return this.repaymentId;
    }

    public void setRepaymentId(Long repayment) {
        this.repaymentId = repayment;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundsTransferTransactionDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((FundsTransferTransactionDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundsTransferTransactionDetails{" +
            "id=" + getId() +
            ", ftTrnxDetailsId=" + getFtTrnxDetailsId() +
            ", ftTrnxDetailsUlid='" + getFtTrnxDetailsUlid() + "'" +
            ", transactionId=" + getTransactionId() +
            ", debitAccountNumber='" + getDebitAccountNumber() + "'" +
            ", creditAccountNumber='" + getCreditAccountNumber() + "'" +
            ", remitterName='" + getRemitterName() + "'" +
            ", amount=" + getAmount() +
            ", currency='" + getCurrency() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", paymentDescription='" + getPaymentDescription() + "'" +
            ", beneficiaryIFSC='" + getBeneficiaryIFSC() + "'" +
            ", beneficiaryName='" + getBeneficiaryName() + "'" +
            ", beneficiaryAddress='" + getBeneficiaryAddress() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", mobileNo=" + getMobileNo() +
            ", transactionRefNo='" + getTransactionRefNo() + "'" +
            ", trnxResourceDataStatus='" + getTrnxResourceDataStatus() + "'" +
            ", trnxMetaDataStatus='" + getTrnxMetaDataStatus() + "'" +
            ", transactionDateTime='" + getTransactionDateTime() + "'" +
            "}";
    }
}
