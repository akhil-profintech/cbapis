package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A CreditAccountDetails.
 */
@Table("credit_account_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("credit_acc_details_id")
    private Long creditAccDetailsId;

    @Column("credit_account_details_ulid_id")
    private String creditAccountDetailsUlidId;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private Long tenantId;

    @NotNull(message = "must not be null")
    @Column("customer_id")
    private Long customerId;

    @NotNull(message = "must not be null")
    @Column("acc_name")
    private String accName;

    @NotNull(message = "must not be null")
    @Column("ifsc_code")
    private String ifscCode;

    @NotNull(message = "must not be null")
    @Column("acc_number")
    private Long accNumber;

    @Column("destination_account_name")
    private String destinationAccountName;

    @Column("destination_account_number")
    private String destinationAccountNumber;

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

    @Column("disbursement_id")
    private Long disbursementId;

    @Column("repayment_id")
    private Long repaymentId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CreditAccountDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreditAccDetailsId() {
        return this.creditAccDetailsId;
    }

    public CreditAccountDetails creditAccDetailsId(Long creditAccDetailsId) {
        this.setCreditAccDetailsId(creditAccDetailsId);
        return this;
    }

    public void setCreditAccDetailsId(Long creditAccDetailsId) {
        this.creditAccDetailsId = creditAccDetailsId;
    }

    public String getCreditAccountDetailsUlidId() {
        return this.creditAccountDetailsUlidId;
    }

    public CreditAccountDetails creditAccountDetailsUlidId(String creditAccountDetailsUlidId) {
        this.setCreditAccountDetailsUlidId(creditAccountDetailsUlidId);
        return this;
    }

    public void setCreditAccountDetailsUlidId(String creditAccountDetailsUlidId) {
        this.creditAccountDetailsUlidId = creditAccountDetailsUlidId;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public CreditAccountDetails tenantId(Long tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public CreditAccountDetails customerId(Long customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccName() {
        return this.accName;
    }

    public CreditAccountDetails accName(String accName) {
        this.setAccName(accName);
        return this;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public CreditAccountDetails ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Long getAccNumber() {
        return this.accNumber;
    }

    public CreditAccountDetails accNumber(Long accNumber) {
        this.setAccNumber(accNumber);
        return this;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public String getDestinationAccountName() {
        return this.destinationAccountName;
    }

    public CreditAccountDetails destinationAccountName(String destinationAccountName) {
        this.setDestinationAccountName(destinationAccountName);
        return this;
    }

    public void setDestinationAccountName(String destinationAccountName) {
        this.destinationAccountName = destinationAccountName;
    }

    public String getDestinationAccountNumber() {
        return this.destinationAccountNumber;
    }

    public CreditAccountDetails destinationAccountNumber(String destinationAccountNumber) {
        this.setDestinationAccountNumber(destinationAccountNumber);
        return this;
    }

    public void setDestinationAccountNumber(String destinationAccountNumber) {
        this.destinationAccountNumber = destinationAccountNumber;
    }

    public Disbursement getDisbursement() {
        return this.disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
        this.disbursementId = disbursement != null ? disbursement.getId() : null;
    }

    public CreditAccountDetails disbursement(Disbursement disbursement) {
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

    public CreditAccountDetails repayment(Repayment repayment) {
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
        if (!(o instanceof CreditAccountDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((CreditAccountDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditAccountDetails{" +
            "id=" + getId() +
            ", creditAccDetailsId=" + getCreditAccDetailsId() +
            ", creditAccountDetailsUlidId='" + getCreditAccountDetailsUlidId() + "'" +
            ", tenantId=" + getTenantId() +
            ", customerId=" + getCustomerId() +
            ", accName='" + getAccName() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", accNumber=" + getAccNumber() +
            ", destinationAccountName='" + getDestinationAccountName() + "'" +
            ", destinationAccountNumber='" + getDestinationAccountNumber() + "'" +
            "}";
    }
}
