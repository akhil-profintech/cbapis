package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A FundsTransfer.
 */
@Table("funds_transfer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FundsTransfer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("funds_transfer_id")
    private String fundsTransferId;

    @Column("funds_transfer_ref_no")
    private String fundsTransferRefNo;

    @Column("fin_req_id")
    private String finReqId;

    @Column("sub_grp_id")
    private String subGrpId;

    @NotNull(message = "must not be null")
    @Column("transaction_id")
    private Long transactionId;

    @Column("debit_account_number")
    private String debitAccountNumber;

    @Column("credit_account_number")
    private String creditAccountNumber;

    @Column("remitter_name")
    private String remitterName;

    @Column("amount")
    private Long amount;

    @Column("currency")
    private String currency;

    @Column("transaction_type")
    private String transactionType;

    @Column("payment_description")
    private String paymentDescription;

    @Column("beneficiary_ifsc")
    private String beneficiaryIFSC;

    @Column("beneficiary_name")
    private String beneficiaryName;

    @Column("beneficiary_address")
    private String beneficiaryAddress;

    @Column("email_id")
    private String emailId;

    @Column("mobile_no")
    private Long mobileNo;

    @Column("message_type")
    private String messageType;

    @Column("transaction_date_time")
    private String transactionDateTime;

    @Column("transaction_ref_no")
    private String transactionRefNo;

    @Column("trnx_meta_data_status")
    private String trnxMetaDataStatus;

    @Column("trnx_meta_data_message")
    private String trnxMetaDataMessage;

    @Column("trnx_meta_data_version")
    private String trnxMetaDataVersion;

    @Column("trnx_meta_data_time")
    private String trnxMetaDataTime;

    @Column("trnx_resource_data_beneficiary_name")
    private String trnxResourceDataBeneficiaryName;

    @Column("trnx_resource_data_transaction_id")
    private String trnxResourceDataTransactionId;

    @Column("trnx_resource_data_status")
    private String trnxResourceDataStatus;

    @Column("funds_transfer_status")
    private String fundsTransferStatus;

    @NotNull(message = "must not be null")
    @Column("lastupdated_date_time")
    private String lastupdatedDateTime;

    @NotNull(message = "must not be null")
    @Column("last_updated_by")
    private String lastUpdatedBy;

    @Transient
    @JsonIgnoreProperties(value = { "beneValidations", "instaAlerts", "fundsTransfers", "updateVAS", "vANumbers" }, allowSetters = true)
    private TradeEntity tradeEntity;

    @Column("trade_entity_id")
    private Long tradeEntityId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FundsTransfer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundsTransferId() {
        return this.fundsTransferId;
    }

    public FundsTransfer fundsTransferId(String fundsTransferId) {
        this.setFundsTransferId(fundsTransferId);
        return this;
    }

    public void setFundsTransferId(String fundsTransferId) {
        this.fundsTransferId = fundsTransferId;
    }

    public String getFundsTransferRefNo() {
        return this.fundsTransferRefNo;
    }

    public FundsTransfer fundsTransferRefNo(String fundsTransferRefNo) {
        this.setFundsTransferRefNo(fundsTransferRefNo);
        return this;
    }

    public void setFundsTransferRefNo(String fundsTransferRefNo) {
        this.fundsTransferRefNo = fundsTransferRefNo;
    }

    public String getFinReqId() {
        return this.finReqId;
    }

    public FundsTransfer finReqId(String finReqId) {
        this.setFinReqId(finReqId);
        return this;
    }

    public void setFinReqId(String finReqId) {
        this.finReqId = finReqId;
    }

    public String getSubGrpId() {
        return this.subGrpId;
    }

    public FundsTransfer subGrpId(String subGrpId) {
        this.setSubGrpId(subGrpId);
        return this;
    }

    public void setSubGrpId(String subGrpId) {
        this.subGrpId = subGrpId;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public FundsTransfer transactionId(Long transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getDebitAccountNumber() {
        return this.debitAccountNumber;
    }

    public FundsTransfer debitAccountNumber(String debitAccountNumber) {
        this.setDebitAccountNumber(debitAccountNumber);
        return this;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return this.creditAccountNumber;
    }

    public FundsTransfer creditAccountNumber(String creditAccountNumber) {
        this.setCreditAccountNumber(creditAccountNumber);
        return this;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public String getRemitterName() {
        return this.remitterName;
    }

    public FundsTransfer remitterName(String remitterName) {
        this.setRemitterName(remitterName);
        return this;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public Long getAmount() {
        return this.amount;
    }

    public FundsTransfer amount(Long amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public FundsTransfer currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public FundsTransfer transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentDescription() {
        return this.paymentDescription;
    }

    public FundsTransfer paymentDescription(String paymentDescription) {
        this.setPaymentDescription(paymentDescription);
        return this;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getBeneficiaryIFSC() {
        return this.beneficiaryIFSC;
    }

    public FundsTransfer beneficiaryIFSC(String beneficiaryIFSC) {
        this.setBeneficiaryIFSC(beneficiaryIFSC);
        return this;
    }

    public void setBeneficiaryIFSC(String beneficiaryIFSC) {
        this.beneficiaryIFSC = beneficiaryIFSC;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public FundsTransfer beneficiaryName(String beneficiaryName) {
        this.setBeneficiaryName(beneficiaryName);
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAddress() {
        return this.beneficiaryAddress;
    }

    public FundsTransfer beneficiaryAddress(String beneficiaryAddress) {
        this.setBeneficiaryAddress(beneficiaryAddress);
        return this;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public FundsTransfer emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getMobileNo() {
        return this.mobileNo;
    }

    public FundsTransfer mobileNo(Long mobileNo) {
        this.setMobileNo(mobileNo);
        return this;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMessageType() {
        return this.messageType;
    }

    public FundsTransfer messageType(String messageType) {
        this.setMessageType(messageType);
        return this;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTransactionDateTime() {
        return this.transactionDateTime;
    }

    public FundsTransfer transactionDateTime(String transactionDateTime) {
        this.setTransactionDateTime(transactionDateTime);
        return this;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getTransactionRefNo() {
        return this.transactionRefNo;
    }

    public FundsTransfer transactionRefNo(String transactionRefNo) {
        this.setTransactionRefNo(transactionRefNo);
        return this;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getTrnxMetaDataStatus() {
        return this.trnxMetaDataStatus;
    }

    public FundsTransfer trnxMetaDataStatus(String trnxMetaDataStatus) {
        this.setTrnxMetaDataStatus(trnxMetaDataStatus);
        return this;
    }

    public void setTrnxMetaDataStatus(String trnxMetaDataStatus) {
        this.trnxMetaDataStatus = trnxMetaDataStatus;
    }

    public String getTrnxMetaDataMessage() {
        return this.trnxMetaDataMessage;
    }

    public FundsTransfer trnxMetaDataMessage(String trnxMetaDataMessage) {
        this.setTrnxMetaDataMessage(trnxMetaDataMessage);
        return this;
    }

    public void setTrnxMetaDataMessage(String trnxMetaDataMessage) {
        this.trnxMetaDataMessage = trnxMetaDataMessage;
    }

    public String getTrnxMetaDataVersion() {
        return this.trnxMetaDataVersion;
    }

    public FundsTransfer trnxMetaDataVersion(String trnxMetaDataVersion) {
        this.setTrnxMetaDataVersion(trnxMetaDataVersion);
        return this;
    }

    public void setTrnxMetaDataVersion(String trnxMetaDataVersion) {
        this.trnxMetaDataVersion = trnxMetaDataVersion;
    }

    public String getTrnxMetaDataTime() {
        return this.trnxMetaDataTime;
    }

    public FundsTransfer trnxMetaDataTime(String trnxMetaDataTime) {
        this.setTrnxMetaDataTime(trnxMetaDataTime);
        return this;
    }

    public void setTrnxMetaDataTime(String trnxMetaDataTime) {
        this.trnxMetaDataTime = trnxMetaDataTime;
    }

    public String getTrnxResourceDataBeneficiaryName() {
        return this.trnxResourceDataBeneficiaryName;
    }

    public FundsTransfer trnxResourceDataBeneficiaryName(String trnxResourceDataBeneficiaryName) {
        this.setTrnxResourceDataBeneficiaryName(trnxResourceDataBeneficiaryName);
        return this;
    }

    public void setTrnxResourceDataBeneficiaryName(String trnxResourceDataBeneficiaryName) {
        this.trnxResourceDataBeneficiaryName = trnxResourceDataBeneficiaryName;
    }

    public String getTrnxResourceDataTransactionId() {
        return this.trnxResourceDataTransactionId;
    }

    public FundsTransfer trnxResourceDataTransactionId(String trnxResourceDataTransactionId) {
        this.setTrnxResourceDataTransactionId(trnxResourceDataTransactionId);
        return this;
    }

    public void setTrnxResourceDataTransactionId(String trnxResourceDataTransactionId) {
        this.trnxResourceDataTransactionId = trnxResourceDataTransactionId;
    }

    public String getTrnxResourceDataStatus() {
        return this.trnxResourceDataStatus;
    }

    public FundsTransfer trnxResourceDataStatus(String trnxResourceDataStatus) {
        this.setTrnxResourceDataStatus(trnxResourceDataStatus);
        return this;
    }

    public void setTrnxResourceDataStatus(String trnxResourceDataStatus) {
        this.trnxResourceDataStatus = trnxResourceDataStatus;
    }

    public String getFundsTransferStatus() {
        return this.fundsTransferStatus;
    }

    public FundsTransfer fundsTransferStatus(String fundsTransferStatus) {
        this.setFundsTransferStatus(fundsTransferStatus);
        return this;
    }

    public void setFundsTransferStatus(String fundsTransferStatus) {
        this.fundsTransferStatus = fundsTransferStatus;
    }

    public String getLastupdatedDateTime() {
        return this.lastupdatedDateTime;
    }

    public FundsTransfer lastupdatedDateTime(String lastupdatedDateTime) {
        this.setLastupdatedDateTime(lastupdatedDateTime);
        return this;
    }

    public void setLastupdatedDateTime(String lastupdatedDateTime) {
        this.lastupdatedDateTime = lastupdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public FundsTransfer lastUpdatedBy(String lastUpdatedBy) {
        this.setLastUpdatedBy(lastUpdatedBy);
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public TradeEntity getTradeEntity() {
        return this.tradeEntity;
    }

    public void setTradeEntity(TradeEntity tradeEntity) {
        this.tradeEntity = tradeEntity;
        this.tradeEntityId = tradeEntity != null ? tradeEntity.getId() : null;
    }

    public FundsTransfer tradeEntity(TradeEntity tradeEntity) {
        this.setTradeEntity(tradeEntity);
        return this;
    }

    public Long getTradeEntityId() {
        return this.tradeEntityId;
    }

    public void setTradeEntityId(Long tradeEntity) {
        this.tradeEntityId = tradeEntity;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundsTransfer)) {
            return false;
        }
        return getId() != null && getId().equals(((FundsTransfer) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundsTransfer{" +
            "id=" + getId() +
            ", fundsTransferId='" + getFundsTransferId() + "'" +
            ", fundsTransferRefNo='" + getFundsTransferRefNo() + "'" +
            ", finReqId='" + getFinReqId() + "'" +
            ", subGrpId='" + getSubGrpId() + "'" +
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
            ", messageType='" + getMessageType() + "'" +
            ", transactionDateTime='" + getTransactionDateTime() + "'" +
            ", transactionRefNo='" + getTransactionRefNo() + "'" +
            ", trnxMetaDataStatus='" + getTrnxMetaDataStatus() + "'" +
            ", trnxMetaDataMessage='" + getTrnxMetaDataMessage() + "'" +
            ", trnxMetaDataVersion='" + getTrnxMetaDataVersion() + "'" +
            ", trnxMetaDataTime='" + getTrnxMetaDataTime() + "'" +
            ", trnxResourceDataBeneficiaryName='" + getTrnxResourceDataBeneficiaryName() + "'" +
            ", trnxResourceDataTransactionId='" + getTrnxResourceDataTransactionId() + "'" +
            ", trnxResourceDataStatus='" + getTrnxResourceDataStatus() + "'" +
            ", fundsTransferStatus='" + getFundsTransferStatus() + "'" +
            ", lastupdatedDateTime='" + getLastupdatedDateTime() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
