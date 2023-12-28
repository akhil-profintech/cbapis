package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A InstaAlert.
 */
@Table("insta_alert")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InstaAlert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("insta_alert_id")
    private Long instaAlertId;

    @Column("fin_req_id")
    private String finReqId;

    @Column("sub_grp_id")
    private String subGrpId;

    @Column("customer_code")
    private String customerCode;

    @Column("customer_name")
    private String customerName;

    @Column("product_code")
    private String productCode;

    @Column("pooling_account_number")
    private String poolingAccountNumber;

    @Column("transaction_type")
    private String transactionType;

    @Column("batch_amt")
    private Long batchAmt;

    @Column("batch_amt_ccd")
    private String batchAmtCcd;

    @Column("credit_date")
    private String creditDate;

    @Column("va_number")
    private String vaNumber;

    @Column("utr_no")
    private String utrNo;

    @Column("credit_generation_time")
    private String creditGenerationTime;

    @Column("remitter_name")
    private String remitterName;

    @Column("remitter_account_number")
    private String remitterAccountNumber;

    @Column("ifsc_code")
    private String ifscCode;

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

    public InstaAlert id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstaAlertId() {
        return this.instaAlertId;
    }

    public InstaAlert instaAlertId(Long instaAlertId) {
        this.setInstaAlertId(instaAlertId);
        return this;
    }

    public void setInstaAlertId(Long instaAlertId) {
        this.instaAlertId = instaAlertId;
    }

    public String getFinReqId() {
        return this.finReqId;
    }

    public InstaAlert finReqId(String finReqId) {
        this.setFinReqId(finReqId);
        return this;
    }

    public void setFinReqId(String finReqId) {
        this.finReqId = finReqId;
    }

    public String getSubGrpId() {
        return this.subGrpId;
    }

    public InstaAlert subGrpId(String subGrpId) {
        this.setSubGrpId(subGrpId);
        return this;
    }

    public void setSubGrpId(String subGrpId) {
        this.subGrpId = subGrpId;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public InstaAlert customerCode(String customerCode) {
        this.setCustomerCode(customerCode);
        return this;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public InstaAlert customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public InstaAlert productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPoolingAccountNumber() {
        return this.poolingAccountNumber;
    }

    public InstaAlert poolingAccountNumber(String poolingAccountNumber) {
        this.setPoolingAccountNumber(poolingAccountNumber);
        return this;
    }

    public void setPoolingAccountNumber(String poolingAccountNumber) {
        this.poolingAccountNumber = poolingAccountNumber;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public InstaAlert transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getBatchAmt() {
        return this.batchAmt;
    }

    public InstaAlert batchAmt(Long batchAmt) {
        this.setBatchAmt(batchAmt);
        return this;
    }

    public void setBatchAmt(Long batchAmt) {
        this.batchAmt = batchAmt;
    }

    public String getBatchAmtCcd() {
        return this.batchAmtCcd;
    }

    public InstaAlert batchAmtCcd(String batchAmtCcd) {
        this.setBatchAmtCcd(batchAmtCcd);
        return this;
    }

    public void setBatchAmtCcd(String batchAmtCcd) {
        this.batchAmtCcd = batchAmtCcd;
    }

    public String getCreditDate() {
        return this.creditDate;
    }

    public InstaAlert creditDate(String creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getVaNumber() {
        return this.vaNumber;
    }

    public InstaAlert vaNumber(String vaNumber) {
        this.setVaNumber(vaNumber);
        return this;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }

    public String getUtrNo() {
        return this.utrNo;
    }

    public InstaAlert utrNo(String utrNo) {
        this.setUtrNo(utrNo);
        return this;
    }

    public void setUtrNo(String utrNo) {
        this.utrNo = utrNo;
    }

    public String getCreditGenerationTime() {
        return this.creditGenerationTime;
    }

    public InstaAlert creditGenerationTime(String creditGenerationTime) {
        this.setCreditGenerationTime(creditGenerationTime);
        return this;
    }

    public void setCreditGenerationTime(String creditGenerationTime) {
        this.creditGenerationTime = creditGenerationTime;
    }

    public String getRemitterName() {
        return this.remitterName;
    }

    public InstaAlert remitterName(String remitterName) {
        this.setRemitterName(remitterName);
        return this;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterAccountNumber() {
        return this.remitterAccountNumber;
    }

    public InstaAlert remitterAccountNumber(String remitterAccountNumber) {
        this.setRemitterAccountNumber(remitterAccountNumber);
        return this;
    }

    public void setRemitterAccountNumber(String remitterAccountNumber) {
        this.remitterAccountNumber = remitterAccountNumber;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public InstaAlert ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getLastupdatedDateTime() {
        return this.lastupdatedDateTime;
    }

    public InstaAlert lastupdatedDateTime(String lastupdatedDateTime) {
        this.setLastupdatedDateTime(lastupdatedDateTime);
        return this;
    }

    public void setLastupdatedDateTime(String lastupdatedDateTime) {
        this.lastupdatedDateTime = lastupdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public InstaAlert lastUpdatedBy(String lastUpdatedBy) {
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

    public InstaAlert tradeEntity(TradeEntity tradeEntity) {
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
        if (!(o instanceof InstaAlert)) {
            return false;
        }
        return getId() != null && getId().equals(((InstaAlert) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstaAlert{" +
            "id=" + getId() +
            ", instaAlertId=" + getInstaAlertId() +
            ", finReqId='" + getFinReqId() + "'" +
            ", subGrpId='" + getSubGrpId() + "'" +
            ", customerCode='" + getCustomerCode() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", poolingAccountNumber='" + getPoolingAccountNumber() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", batchAmt=" + getBatchAmt() +
            ", batchAmtCcd='" + getBatchAmtCcd() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            ", vaNumber='" + getVaNumber() + "'" +
            ", utrNo='" + getUtrNo() + "'" +
            ", creditGenerationTime='" + getCreditGenerationTime() + "'" +
            ", remitterName='" + getRemitterName() + "'" +
            ", remitterAccountNumber='" + getRemitterAccountNumber() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", lastupdatedDateTime='" + getLastupdatedDateTime() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            "}";
    }
}
