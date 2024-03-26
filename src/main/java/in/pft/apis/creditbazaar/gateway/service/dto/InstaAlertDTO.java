package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.InstaAlert} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class InstaAlertDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long instaAlertId;

    private String finReqId;

    private String subGrpId;

    private String customerCode;

    private String customerName;

    private String productCode;

    private String poolingAccountNumber;

    private String transactionType;

    private Long batchAmt;

    private String batchAmtCcd;

    private String creditDate;

    private String vaNumber;

    private String utrNo;

    private String creditGenerationTime;

    private String remitterName;

    private String remitterAccountNumber;

    private String ifscCode;

    @NotNull(message = "must not be null")
    private String lastupdatedDateTime;

    @NotNull(message = "must not be null")
    private String lastUpdatedBy;

    private String dataKey;

    private TradeEntityDTO tradeEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInstaAlertId() {
        return instaAlertId;
    }

    public void setInstaAlertId(Long instaAlertId) {
        this.instaAlertId = instaAlertId;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getPoolingAccountNumber() {
        return poolingAccountNumber;
    }

    public void setPoolingAccountNumber(String poolingAccountNumber) {
        this.poolingAccountNumber = poolingAccountNumber;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getBatchAmt() {
        return batchAmt;
    }

    public void setBatchAmt(Long batchAmt) {
        this.batchAmt = batchAmt;
    }

    public String getBatchAmtCcd() {
        return batchAmtCcd;
    }

    public void setBatchAmtCcd(String batchAmtCcd) {
        this.batchAmtCcd = batchAmtCcd;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getVaNumber() {
        return vaNumber;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }

    public String getUtrNo() {
        return utrNo;
    }

    public void setUtrNo(String utrNo) {
        this.utrNo = utrNo;
    }

    public String getCreditGenerationTime() {
        return creditGenerationTime;
    }

    public void setCreditGenerationTime(String creditGenerationTime) {
        this.creditGenerationTime = creditGenerationTime;
    }

    public String getRemitterName() {
        return remitterName;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterAccountNumber() {
        return remitterAccountNumber;
    }

    public void setRemitterAccountNumber(String remitterAccountNumber) {
        this.remitterAccountNumber = remitterAccountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
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

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
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
        if (!(o instanceof InstaAlertDTO)) {
            return false;
        }

        InstaAlertDTO instaAlertDTO = (InstaAlertDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, instaAlertDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InstaAlertDTO{" +
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
            ", dataKey='" + getDataKey() + "'" +
            ", tradeEntity=" + getTradeEntity() +
            "}";
    }
}
