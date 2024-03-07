package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A EscrowTransactionDetails.
 */
@Table("escrow_transaction_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EscrowTransactionDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("escrow_trnx_details_id")
    private Long escrowTrnxDetailsId;

    @Column("escrow_trnx_details_ulid_id")
    private String escrowTrnxDetailsUlidId;

    @NotNull(message = "must not be null")
    @Column("customer_code")
    private String customerCode;

    @NotNull(message = "must not be null")
    @Column("customer_name")
    private String customerName;

    @NotNull(message = "must not be null")
    @Column("product_code")
    private String productCode;

    @NotNull(message = "must not be null")
    @Column("transaction_type")
    private String transactionType;

    @NotNull(message = "must not be null")
    @Column("batch_amt")
    private String batchAmt;

    @NotNull(message = "must not be null")
    @Column("batch_amt_ccd")
    private String batchAmtCcd;

    @NotNull(message = "must not be null")
    @Column("credit_date")
    private String creditDate;

    @NotNull(message = "must not be null")
    @Column("va_number")
    private String vaNumber;

    @NotNull(message = "must not be null")
    @Column("utr_no")
    private String utrNo;

    @NotNull(message = "must not be null")
    @Column("credit_generation_time")
    private String creditGenerationTime;

    @NotNull(message = "must not be null")
    @Column("remitter_name")
    private String remitterName;

    @NotNull(message = "must not be null")
    @Column("remitter_account_number")
    private String remitterAccountNumber;

    @NotNull(message = "must not be null")
    @Column("ifsc_code")
    private String ifscCode;

    @Transient
    @JsonIgnoreProperties(
        value = { "creditAccountDetails", "fTTransactionDetails", "escrowTransactionDetails", "financerequest", "financepartner" },
        allowSetters = true
    )
    private Disbursement disbursement;

    @Transient
    @JsonIgnoreProperties(
        value = { "creditAccountDetails", "fTTransactionDetails", "escrowTransactionDetails", "financerequest" },
        allowSetters = true
    )
    private Repayment repayment;

    @Column("disbursement_id")
    private Long disbursementId;

    @Column("repayment_id")
    private Long repaymentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EscrowTransactionDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEscrowTrnxDetailsId() {
        return this.escrowTrnxDetailsId;
    }

    public EscrowTransactionDetails escrowTrnxDetailsId(Long escrowTrnxDetailsId) {
        this.setEscrowTrnxDetailsId(escrowTrnxDetailsId);
        return this;
    }

    public void setEscrowTrnxDetailsId(Long escrowTrnxDetailsId) {
        this.escrowTrnxDetailsId = escrowTrnxDetailsId;
    }

    public String getEscrowTrnxDetailsUlidId() {
        return this.escrowTrnxDetailsUlidId;
    }

    public EscrowTransactionDetails escrowTrnxDetailsUlidId(String escrowTrnxDetailsUlidId) {
        this.setEscrowTrnxDetailsUlidId(escrowTrnxDetailsUlidId);
        return this;
    }

    public void setEscrowTrnxDetailsUlidId(String escrowTrnxDetailsUlidId) {
        this.escrowTrnxDetailsUlidId = escrowTrnxDetailsUlidId;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public EscrowTransactionDetails customerCode(String customerCode) {
        this.setCustomerCode(customerCode);
        return this;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public EscrowTransactionDetails customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public EscrowTransactionDetails productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public EscrowTransactionDetails transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getBatchAmt() {
        return this.batchAmt;
    }

    public EscrowTransactionDetails batchAmt(String batchAmt) {
        this.setBatchAmt(batchAmt);
        return this;
    }

    public void setBatchAmt(String batchAmt) {
        this.batchAmt = batchAmt;
    }

    public String getBatchAmtCcd() {
        return this.batchAmtCcd;
    }

    public EscrowTransactionDetails batchAmtCcd(String batchAmtCcd) {
        this.setBatchAmtCcd(batchAmtCcd);
        return this;
    }

    public void setBatchAmtCcd(String batchAmtCcd) {
        this.batchAmtCcd = batchAmtCcd;
    }

    public String getCreditDate() {
        return this.creditDate;
    }

    public EscrowTransactionDetails creditDate(String creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    public String getVaNumber() {
        return this.vaNumber;
    }

    public EscrowTransactionDetails vaNumber(String vaNumber) {
        this.setVaNumber(vaNumber);
        return this;
    }

    public void setVaNumber(String vaNumber) {
        this.vaNumber = vaNumber;
    }

    public String getUtrNo() {
        return this.utrNo;
    }

    public EscrowTransactionDetails utrNo(String utrNo) {
        this.setUtrNo(utrNo);
        return this;
    }

    public void setUtrNo(String utrNo) {
        this.utrNo = utrNo;
    }

    public String getCreditGenerationTime() {
        return this.creditGenerationTime;
    }

    public EscrowTransactionDetails creditGenerationTime(String creditGenerationTime) {
        this.setCreditGenerationTime(creditGenerationTime);
        return this;
    }

    public void setCreditGenerationTime(String creditGenerationTime) {
        this.creditGenerationTime = creditGenerationTime;
    }

    public String getRemitterName() {
        return this.remitterName;
    }

    public EscrowTransactionDetails remitterName(String remitterName) {
        this.setRemitterName(remitterName);
        return this;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterAccountNumber() {
        return this.remitterAccountNumber;
    }

    public EscrowTransactionDetails remitterAccountNumber(String remitterAccountNumber) {
        this.setRemitterAccountNumber(remitterAccountNumber);
        return this;
    }

    public void setRemitterAccountNumber(String remitterAccountNumber) {
        this.remitterAccountNumber = remitterAccountNumber;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public EscrowTransactionDetails ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Disbursement getDisbursement() {
        return this.disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
        this.disbursementId = disbursement != null ? disbursement.getId() : null;
    }

    public EscrowTransactionDetails disbursement(Disbursement disbursement) {
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

    public EscrowTransactionDetails repayment(Repayment repayment) {
        this.setRepayment(repayment);
        return this;
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
        if (!(o instanceof EscrowTransactionDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((EscrowTransactionDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EscrowTransactionDetails{" +
            "id=" + getId() +
            ", escrowTrnxDetailsId=" + getEscrowTrnxDetailsId() +
            ", escrowTrnxDetailsUlidId='" + getEscrowTrnxDetailsUlidId() + "'" +
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
            "}";
    }
}
