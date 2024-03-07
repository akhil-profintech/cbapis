package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.FundsTransfer} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FundsTransferDTO implements Serializable {

    private Long id;

    private String fundsTransferId;

    private String fundsTransferRefNo;

    private String finReqId;

    private String subGrpId;

    @NotNull(message = "must not be null")
    private Long transactionId;

    private String debitAccountNumber;

    private String creditAccountNumber;

    private String remitterName;

    private Long amount;

    private String currency;

    private String transactionType;

    private String paymentDescription;

    private String beneficiaryIFSC;

    private String beneficiaryName;

    private String beneficiaryAddress;

    private String emailId;

    private Long mobileNo;

    private String messageType;

    private String transactionDateTime;

    private String transactionRefNo;

    private String trnxMetaDataStatus;

    private String trnxMetaDataMessage;

    private String trnxMetaDataVersion;

    private String trnxMetaDataTime;

    private String trnxResourceDataBeneficiaryName;

    private String trnxResourceDataTransactionId;

    private String trnxResourceDataStatus;

    private String fundsTransferStatus;

    @NotNull(message = "must not be null")
    private String lastupdatedDateTime;

    @NotNull(message = "must not be null")
    private String lastUpdatedBy;

    private TradeEntityDTO tradeEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundsTransferId() {
        return fundsTransferId;
    }

    public void setFundsTransferId(String fundsTransferId) {
        this.fundsTransferId = fundsTransferId;
    }

    public String getFundsTransferRefNo() {
        return fundsTransferRefNo;
    }

    public void setFundsTransferRefNo(String fundsTransferRefNo) {
        this.fundsTransferRefNo = fundsTransferRefNo;
    }

    public String getFinReqId() {
        return finReqId;
    }

    public void setFinReqId(String finReqId) {
        this.finReqId = finReqId;
    }

    public String getSubGrpId() {
        return subGrpId;
    }

    public void setSubGrpId(String subGrpId) {
        this.subGrpId = subGrpId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(String transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public String getTransactionRefNo() {
        return transactionRefNo;
    }

    public void setTransactionRefNo(String transactionRefNo) {
        this.transactionRefNo = transactionRefNo;
    }

    public String getTrnxMetaDataStatus() {
        return trnxMetaDataStatus;
    }

    public void setTrnxMetaDataStatus(String trnxMetaDataStatus) {
        this.trnxMetaDataStatus = trnxMetaDataStatus;
    }

    public String getTrnxMetaDataMessage() {
        return trnxMetaDataMessage;
    }

    public void setTrnxMetaDataMessage(String trnxMetaDataMessage) {
        this.trnxMetaDataMessage = trnxMetaDataMessage;
    }

    public String getTrnxMetaDataVersion() {
        return trnxMetaDataVersion;
    }

    public void setTrnxMetaDataVersion(String trnxMetaDataVersion) {
        this.trnxMetaDataVersion = trnxMetaDataVersion;
    }

    public String getTrnxMetaDataTime() {
        return trnxMetaDataTime;
    }

    public void setTrnxMetaDataTime(String trnxMetaDataTime) {
        this.trnxMetaDataTime = trnxMetaDataTime;
    }

    public String getTrnxResourceDataBeneficiaryName() {
        return trnxResourceDataBeneficiaryName;
    }

    public void setTrnxResourceDataBeneficiaryName(String trnxResourceDataBeneficiaryName) {
        this.trnxResourceDataBeneficiaryName = trnxResourceDataBeneficiaryName;
    }

    public String getTrnxResourceDataTransactionId() {
        return trnxResourceDataTransactionId;
    }

    public void setTrnxResourceDataTransactionId(String trnxResourceDataTransactionId) {
        this.trnxResourceDataTransactionId = trnxResourceDataTransactionId;
    }

    public String getTrnxResourceDataStatus() {
        return trnxResourceDataStatus;
    }

    public void setTrnxResourceDataStatus(String trnxResourceDataStatus) {
        this.trnxResourceDataStatus = trnxResourceDataStatus;
    }

    public String getFundsTransferStatus() {
        return fundsTransferStatus;
    }

    public void setFundsTransferStatus(String fundsTransferStatus) {
        this.fundsTransferStatus = fundsTransferStatus;
    }

    public String getLastupdatedDateTime() {
        return lastupdatedDateTime;
    }

    public void setLastupdatedDateTime(String lastupdatedDateTime) {
        this.lastupdatedDateTime = lastupdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public TradeEntityDTO getTradeEntity() {
        return tradeEntity;
    }

    public void setTradeEntity(TradeEntityDTO tradeEntity) {
        this.tradeEntity = tradeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundsTransferDTO)) {
            return false;
        }

        FundsTransferDTO fundsTransferDTO = (FundsTransferDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, fundsTransferDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundsTransferDTO{" +
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
            ", tradeEntity=" + getTradeEntity() +
            "}";
    }
}
