package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.FTTransactionDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FTTransactionDetailsDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long trnxDetailsId;

    @NotNull(message = "must not be null")
    private Long transactionID;

    @NotNull(message = "must not be null")
    private String debitAccountNumber;

    @NotNull(message = "must not be null")
    private String creditAccountNumber;

    @NotNull(message = "must not be null")
    private String remitterName;

    @NotNull(message = "must not be null")
    private Long amount;

    @NotNull(message = "must not be null")
    private String currency;

    @NotNull(message = "must not be null")
    private String transactionType;

    @NotNull(message = "must not be null")
    private String paymentDescription;

    @NotNull(message = "must not be null")
    private String beneficiaryIFSC;

    @NotNull(message = "must not be null")
    private String beneficiaryName;

    @NotNull(message = "must not be null")
    private String beneficiaryAddress;

    @NotNull(message = "must not be null")
    private String emailId;

    @NotNull(message = "must not be null")
    private Long mobileNo;

    @NotNull(message = "must not be null")
    private String transactionRefNo;

    @NotNull(message = "must not be null")
    private String trnxResourceDataStatus;

    @NotNull(message = "must not be null")
    private String trnxMetaDataStatus;

    @NotNull(message = "must not be null")
    private String transactionDateTime;

    private DisbursementDTO disbursement;

    private RepaymentDTO repayment;

    private ParticipantSettlementDTO participantsettlement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrnxDetailsId() {
        return trnxDetailsId;
    }

    public void setTrnxDetailsId(Long trnxDetailsId) {
        this.trnxDetailsId = trnxDetailsId;
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public String getDebitAccountNumber() {
        return debitAccountNumber;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return creditAccountNumber;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getRemitterName() {
        return remitterName;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getBeneficiaryIFSC() {
        return beneficiaryIFSC;
    }

    public void setBeneficiaryIFSC(String beneficiaryIFSC) {
        this.beneficiaryIFSC = beneficiaryIFSC;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAddress() {
        return beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getTrnxResourceDataStatus() {
        return trnxResourceDataStatus;
    }

    public void setTrnxResourceDataStatus(String trnxResourceDataStatus) {
        this.trnxResourceDataStatus = trnxResourceDataStatus;
    }

    public String getTrnxMetaDataStatus() {
        return trnxMetaDataStatus;
    }

    public void setTrnxMetaDataStatus(String trnxMetaDataStatus) {
        this.trnxMetaDataStatus = trnxMetaDataStatus;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public DisbursementDTO getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(DisbursementDTO disbursement) {
        this.disbursement = disbursement;
    }

    public RepaymentDTO getRepayment() {
        return repayment;
    }

    public void setRepayment(RepaymentDTO repayment) {
        this.repayment = repayment;
    }

    public ParticipantSettlementDTO getParticipantsettlement() {
        return participantsettlement;
    }

    public void setParticipantsettlement(ParticipantSettlementDTO participantsettlement) {
        this.participantsettlement = participantsettlement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FTTransactionDetailsDTO)) {
            return false;
        }

        FTTransactionDetailsDTO fTTransactionDetailsDTO = (FTTransactionDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fTTransactionDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FTTransactionDetailsDTO{" +
            "id=" + getId() +
            ", trnxDetailsId=" + getTrnxDetailsId() +
            ", transactionID=" + getTransactionID() +
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
            ", disbursement=" + getDisbursement() +
            ", repayment=" + getRepayment() +
            ", participantsettlement=" + getParticipantsettlement() +
            "}";
    }
}
