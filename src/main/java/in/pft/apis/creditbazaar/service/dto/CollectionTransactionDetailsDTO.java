package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.CollectionTransactionDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CollectionTransactionDetailsDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long trnxDetailsid;

    @NotNull(message = "must not be null")
    private String customerCode;

    @NotNull(message = "must not be null")
    private String customerName;

    @NotNull(message = "must not be null")
    private String productCode;

    @NotNull(message = "must not be null")
    private String transactionType;

    @NotNull(message = "must not be null")
    private String batchAmt;

    @NotNull(message = "must not be null")
    private String batchAmtCcd;

    @NotNull(message = "must not be null")
    private String creditDate;

    @NotNull(message = "must not be null")
    private String vaNumber;

    @NotNull(message = "must not be null")
    private String utrNo;

    @NotNull(message = "must not be null")
    private String creditGenerationTime;

    @NotNull(message = "must not be null")
    private String remitterName;

    @NotNull(message = "must not be null")
    private String remitterAccountNumber;

    @NotNull(message = "must not be null")
    private String ifscCode;

    private DisbursementDTO disbursement;

    private RepaymentDTO repayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrnxDetailsid() {
        return trnxDetailsid;
    }

    public void setTrnxDetailsid(Long trnxDetailsid) {
        this.trnxDetailsid = trnxDetailsid;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBatchAmt() {
        return batchAmt;
    }

    public void setBatchAmt(String batchAmt) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CollectionTransactionDetailsDTO)) {
            return false;
        }

        CollectionTransactionDetailsDTO collectionTransactionDetailsDTO = (CollectionTransactionDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, collectionTransactionDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CollectionTransactionDetailsDTO{" +
            "id=" + getId() +
            ", trnxDetailsid=" + getTrnxDetailsid() +
            ", customerCode='" + getCustomerCode() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", batchAmt='" + getBatchAmt() + "'" +
            ", batchAmtCcd='" + getBatchAmtCcd() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            ", vaNumber='" + getVaNumber() + "'" +
            ", utrNo='" + getUtrNo() + "'" +
            ", creditGenerationTime='" + getCreditGenerationTime() + "'" +
            ", remitterName='" + getRemitterName() + "'" +
            ", remitterAccountNumber='" + getRemitterAccountNumber() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", disbursement=" + getDisbursement() +
            ", repayment=" + getRepayment() +
            "}";
    }
}
