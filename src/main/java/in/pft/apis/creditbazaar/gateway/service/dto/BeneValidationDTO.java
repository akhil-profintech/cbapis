package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.BeneValidation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeneValidationDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String benevalidationId;

    private String finReqId;

    private String subGrpId;

    private String remitterName;

    private String remitterMobileNumber;

    private String debtorAccountId;

    private String accountType;

    private String creditorAccountId;

    private String ifscCode;

    private String paymentDescription;

    private String transactionReferenceNumber;

    private String merchantCode;

    private String identifier;

    @NotNull(message = "must not be null")
    private String requestDateTime;

    private String metaDataStatus;

    private String metaDataMessage;

    private String metaDataVersion;

    private String metaDataTime;

    private String resourceDataCreditorAccountId;

    private String resourceDataCreditorName;

    private String resourceDataTransactionReferenceNumber;

    private String resourceDataTransactionId;

    private String resourceDataResponseCode;

    private String resourceDataTransactionTime;

    private String resourceDataIdentifier;

    @NotNull(message = "must not be null")
    private String responseDateTime;

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

    public String getBenevalidationId() {
        return benevalidationId;
    }

    public void setBenevalidationId(String benevalidationId) {
        this.benevalidationId = benevalidationId;
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

    public String getRemitterName() {
        return remitterName;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterMobileNumber() {
        return remitterMobileNumber;
    }

    public void setRemitterMobileNumber(String remitterMobileNumber) {
        this.remitterMobileNumber = remitterMobileNumber;
    }

    public String getDebtorAccountId() {
        return debtorAccountId;
    }

    public void setDebtorAccountId(String debtorAccountId) {
        this.debtorAccountId = debtorAccountId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCreditorAccountId() {
        return creditorAccountId;
    }

    public void setCreditorAccountId(String creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getTransactionReferenceNumber() {
        return transactionReferenceNumber;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getMetaDataStatus() {
        return metaDataStatus;
    }

    public void setMetaDataStatus(String metaDataStatus) {
        this.metaDataStatus = metaDataStatus;
    }

    public String getMetaDataMessage() {
        return metaDataMessage;
    }

    public void setMetaDataMessage(String metaDataMessage) {
        this.metaDataMessage = metaDataMessage;
    }

    public String getMetaDataVersion() {
        return metaDataVersion;
    }

    public void setMetaDataVersion(String metaDataVersion) {
        this.metaDataVersion = metaDataVersion;
    }

    public String getMetaDataTime() {
        return metaDataTime;
    }

    public void setMetaDataTime(String metaDataTime) {
        this.metaDataTime = metaDataTime;
    }

    public String getResourceDataCreditorAccountId() {
        return resourceDataCreditorAccountId;
    }

    public void setResourceDataCreditorAccountId(String resourceDataCreditorAccountId) {
        this.resourceDataCreditorAccountId = resourceDataCreditorAccountId;
    }

    public String getResourceDataCreditorName() {
        return resourceDataCreditorName;
    }

    public void setResourceDataCreditorName(String resourceDataCreditorName) {
        this.resourceDataCreditorName = resourceDataCreditorName;
    }

    public String getResourceDataTransactionReferenceNumber() {
        return resourceDataTransactionReferenceNumber;
    }

    public void setResourceDataTransactionReferenceNumber(String resourceDataTransactionReferenceNumber) {
        this.resourceDataTransactionReferenceNumber = resourceDataTransactionReferenceNumber;
    }

    public String getResourceDataTransactionId() {
        return resourceDataTransactionId;
    }

    public void setResourceDataTransactionId(String resourceDataTransactionId) {
        this.resourceDataTransactionId = resourceDataTransactionId;
    }

    public String getResourceDataResponseCode() {
        return resourceDataResponseCode;
    }

    public void setResourceDataResponseCode(String resourceDataResponseCode) {
        this.resourceDataResponseCode = resourceDataResponseCode;
    }

    public String getResourceDataTransactionTime() {
        return resourceDataTransactionTime;
    }

    public void setResourceDataTransactionTime(String resourceDataTransactionTime) {
        this.resourceDataTransactionTime = resourceDataTransactionTime;
    }

    public String getResourceDataIdentifier() {
        return resourceDataIdentifier;
    }

    public void setResourceDataIdentifier(String resourceDataIdentifier) {
        this.resourceDataIdentifier = resourceDataIdentifier;
    }

    public String getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
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
        if (!(o instanceof BeneValidationDTO)) {
            return false;
        }

        BeneValidationDTO beneValidationDTO = (BeneValidationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, beneValidationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeneValidationDTO{" +
            "id=" + getId() +
            ", benevalidationId='" + getBenevalidationId() + "'" +
            ", finReqId='" + getFinReqId() + "'" +
            ", subGrpId='" + getSubGrpId() + "'" +
            ", remitterName='" + getRemitterName() + "'" +
            ", remitterMobileNumber='" + getRemitterMobileNumber() + "'" +
            ", debtorAccountId='" + getDebtorAccountId() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", creditorAccountId='" + getCreditorAccountId() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", paymentDescription='" + getPaymentDescription() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", merchantCode='" + getMerchantCode() + "'" +
            ", identifier='" + getIdentifier() + "'" +
            ", requestDateTime='" + getRequestDateTime() + "'" +
            ", metaDataStatus='" + getMetaDataStatus() + "'" +
            ", metaDataMessage='" + getMetaDataMessage() + "'" +
            ", metaDataVersion='" + getMetaDataVersion() + "'" +
            ", metaDataTime='" + getMetaDataTime() + "'" +
            ", resourceDataCreditorAccountId='" + getResourceDataCreditorAccountId() + "'" +
            ", resourceDataCreditorName='" + getResourceDataCreditorName() + "'" +
            ", resourceDataTransactionReferenceNumber='" + getResourceDataTransactionReferenceNumber() + "'" +
            ", resourceDataTransactionId='" + getResourceDataTransactionId() + "'" +
            ", resourceDataResponseCode='" + getResourceDataResponseCode() + "'" +
            ", resourceDataTransactionTime='" + getResourceDataTransactionTime() + "'" +
            ", resourceDataIdentifier='" + getResourceDataIdentifier() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", lastupdatedDateTime='" + getLastupdatedDateTime() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tradeEntity=" + getTradeEntity() +
            "}";
    }
}
