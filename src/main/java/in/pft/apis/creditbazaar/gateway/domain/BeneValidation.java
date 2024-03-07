package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A BeneValidation.
 */
@Table("bene_validation")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BeneValidation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("benevalidation_id")
    private String benevalidationId;

    @Column("fin_req_id")
    private String finReqId;

    @Column("sub_grp_id")
    private String subGrpId;

    @Column("remitter_name")
    private String remitterName;

    @Column("remitter_mobile_number")
    private String remitterMobileNumber;

    @Column("debtor_account_id")
    private String debtorAccountId;

    @Column("account_type")
    private String accountType;

    @Column("creditor_account_id")
    private String creditorAccountId;

    @Column("ifsc_code")
    private String ifscCode;

    @Column("payment_description")
    private String paymentDescription;

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("merchant_code")
    private String merchantCode;

    @Column("identifier")
    private String identifier;

    @NotNull(message = "must not be null")
    @Column("request_date_time")
    private String requestDateTime;

    @Column("meta_data_status")
    private String metaDataStatus;

    @Column("meta_data_message")
    private String metaDataMessage;

    @Column("meta_data_version")
    private String metaDataVersion;

    @Column("meta_data_time")
    private String metaDataTime;

    @Column("resource_data_creditor_account_id")
    private String resourceDataCreditorAccountId;

    @Column("resource_data_creditor_name")
    private String resourceDataCreditorName;

    @Column("resource_data_transaction_reference_number")
    private String resourceDataTransactionReferenceNumber;

    @Column("resource_data_transaction_id")
    private String resourceDataTransactionId;

    @Column("resource_data_response_code")
    private String resourceDataResponseCode;

    @Column("resource_data_transaction_time")
    private String resourceDataTransactionTime;

    @Column("resource_data_identifier")
    private String resourceDataIdentifier;

    @NotNull(message = "must not be null")
    @Column("response_date_time")
    private String responseDateTime;

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

    public BeneValidation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBenevalidationId() {
        return this.benevalidationId;
    }

    public BeneValidation benevalidationId(String benevalidationId) {
        this.setBenevalidationId(benevalidationId);
        return this;
    }

    public void setBenevalidationId(String benevalidationId) {
        this.benevalidationId = benevalidationId;
    }

    public String getFinReqId() {
        return this.finReqId;
    }

    public BeneValidation finReqId(String finReqId) {
        this.setFinReqId(finReqId);
        return this;
    }

    public void setFinReqId(String finReqId) {
        this.finReqId = finReqId;
    }

    public String getSubGrpId() {
        return this.subGrpId;
    }

    public BeneValidation subGrpId(String subGrpId) {
        this.setSubGrpId(subGrpId);
        return this;
    }

    public void setSubGrpId(String subGrpId) {
        this.subGrpId = subGrpId;
    }

    public String getRemitterName() {
        return this.remitterName;
    }

    public BeneValidation remitterName(String remitterName) {
        this.setRemitterName(remitterName);
        return this;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterMobileNumber() {
        return this.remitterMobileNumber;
    }

    public BeneValidation remitterMobileNumber(String remitterMobileNumber) {
        this.setRemitterMobileNumber(remitterMobileNumber);
        return this;
    }

    public void setRemitterMobileNumber(String remitterMobileNumber) {
        this.remitterMobileNumber = remitterMobileNumber;
    }

    public String getDebtorAccountId() {
        return this.debtorAccountId;
    }

    public BeneValidation debtorAccountId(String debtorAccountId) {
        this.setDebtorAccountId(debtorAccountId);
        return this;
    }

    public void setDebtorAccountId(String debtorAccountId) {
        this.debtorAccountId = debtorAccountId;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public BeneValidation accountType(String accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCreditorAccountId() {
        return this.creditorAccountId;
    }

    public BeneValidation creditorAccountId(String creditorAccountId) {
        this.setCreditorAccountId(creditorAccountId);
        return this;
    }

    public void setCreditorAccountId(String creditorAccountId) {
        this.creditorAccountId = creditorAccountId;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public BeneValidation ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getPaymentDescription() {
        return this.paymentDescription;
    }

    public BeneValidation paymentDescription(String paymentDescription) {
        this.setPaymentDescription(paymentDescription);
        return this;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public BeneValidation transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getMerchantCode() {
        return this.merchantCode;
    }

    public BeneValidation merchantCode(String merchantCode) {
        this.setMerchantCode(merchantCode);
        return this;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public BeneValidation identifier(String identifier) {
        this.setIdentifier(identifier);
        return this;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRequestDateTime() {
        return this.requestDateTime;
    }

    public BeneValidation requestDateTime(String requestDateTime) {
        this.setRequestDateTime(requestDateTime);
        return this;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getMetaDataStatus() {
        return this.metaDataStatus;
    }

    public BeneValidation metaDataStatus(String metaDataStatus) {
        this.setMetaDataStatus(metaDataStatus);
        return this;
    }

    public void setMetaDataStatus(String metaDataStatus) {
        this.metaDataStatus = metaDataStatus;
    }

    public String getMetaDataMessage() {
        return this.metaDataMessage;
    }

    public BeneValidation metaDataMessage(String metaDataMessage) {
        this.setMetaDataMessage(metaDataMessage);
        return this;
    }

    public void setMetaDataMessage(String metaDataMessage) {
        this.metaDataMessage = metaDataMessage;
    }

    public String getMetaDataVersion() {
        return this.metaDataVersion;
    }

    public BeneValidation metaDataVersion(String metaDataVersion) {
        this.setMetaDataVersion(metaDataVersion);
        return this;
    }

    public void setMetaDataVersion(String metaDataVersion) {
        this.metaDataVersion = metaDataVersion;
    }

    public String getMetaDataTime() {
        return this.metaDataTime;
    }

    public BeneValidation metaDataTime(String metaDataTime) {
        this.setMetaDataTime(metaDataTime);
        return this;
    }

    public void setMetaDataTime(String metaDataTime) {
        this.metaDataTime = metaDataTime;
    }

    public String getResourceDataCreditorAccountId() {
        return this.resourceDataCreditorAccountId;
    }

    public BeneValidation resourceDataCreditorAccountId(String resourceDataCreditorAccountId) {
        this.setResourceDataCreditorAccountId(resourceDataCreditorAccountId);
        return this;
    }

    public void setResourceDataCreditorAccountId(String resourceDataCreditorAccountId) {
        this.resourceDataCreditorAccountId = resourceDataCreditorAccountId;
    }

    public String getResourceDataCreditorName() {
        return this.resourceDataCreditorName;
    }

    public BeneValidation resourceDataCreditorName(String resourceDataCreditorName) {
        this.setResourceDataCreditorName(resourceDataCreditorName);
        return this;
    }

    public void setResourceDataCreditorName(String resourceDataCreditorName) {
        this.resourceDataCreditorName = resourceDataCreditorName;
    }

    public String getResourceDataTransactionReferenceNumber() {
        return this.resourceDataTransactionReferenceNumber;
    }

    public BeneValidation resourceDataTransactionReferenceNumber(String resourceDataTransactionReferenceNumber) {
        this.setResourceDataTransactionReferenceNumber(resourceDataTransactionReferenceNumber);
        return this;
    }

    public void setResourceDataTransactionReferenceNumber(String resourceDataTransactionReferenceNumber) {
        this.resourceDataTransactionReferenceNumber = resourceDataTransactionReferenceNumber;
    }

    public String getResourceDataTransactionId() {
        return this.resourceDataTransactionId;
    }

    public BeneValidation resourceDataTransactionId(String resourceDataTransactionId) {
        this.setResourceDataTransactionId(resourceDataTransactionId);
        return this;
    }

    public void setResourceDataTransactionId(String resourceDataTransactionId) {
        this.resourceDataTransactionId = resourceDataTransactionId;
    }

    public String getResourceDataResponseCode() {
        return this.resourceDataResponseCode;
    }

    public BeneValidation resourceDataResponseCode(String resourceDataResponseCode) {
        this.setResourceDataResponseCode(resourceDataResponseCode);
        return this;
    }

    public void setResourceDataResponseCode(String resourceDataResponseCode) {
        this.resourceDataResponseCode = resourceDataResponseCode;
    }

    public String getResourceDataTransactionTime() {
        return this.resourceDataTransactionTime;
    }

    public BeneValidation resourceDataTransactionTime(String resourceDataTransactionTime) {
        this.setResourceDataTransactionTime(resourceDataTransactionTime);
        return this;
    }

    public void setResourceDataTransactionTime(String resourceDataTransactionTime) {
        this.resourceDataTransactionTime = resourceDataTransactionTime;
    }

    public String getResourceDataIdentifier() {
        return this.resourceDataIdentifier;
    }

    public BeneValidation resourceDataIdentifier(String resourceDataIdentifier) {
        this.setResourceDataIdentifier(resourceDataIdentifier);
        return this;
    }

    public void setResourceDataIdentifier(String resourceDataIdentifier) {
        this.resourceDataIdentifier = resourceDataIdentifier;
    }

    public String getResponseDateTime() {
        return this.responseDateTime;
    }

    public BeneValidation responseDateTime(String responseDateTime) {
        this.setResponseDateTime(responseDateTime);
        return this;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getLastupdatedDateTime() {
        return this.lastupdatedDateTime;
    }

    public BeneValidation lastupdatedDateTime(String lastupdatedDateTime) {
        this.setLastupdatedDateTime(lastupdatedDateTime);
        return this;
    }

    public void setLastupdatedDateTime(String lastupdatedDateTime) {
        this.lastupdatedDateTime = lastupdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public BeneValidation lastUpdatedBy(String lastUpdatedBy) {
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

    public BeneValidation tradeEntity(TradeEntity tradeEntity) {
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
        if (!(o instanceof BeneValidation)) {
            return false;
        }
        return getId() != null && getId().equals(((BeneValidation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BeneValidation{" +
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
            "}";
    }
}
