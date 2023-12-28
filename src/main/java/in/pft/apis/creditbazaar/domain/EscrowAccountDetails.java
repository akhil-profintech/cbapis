package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A EscrowAccountDetails.
 */
@Table("escrow_account_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EscrowAccountDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("escrow_details_id")
    private Long escrowDetailsId;

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
    @Column("virtual_acc_number")
    private String virtualAccNumber;

    @NotNull(message = "must not be null")
    @Column("pooling_acc_number")
    private Long poolingAccNumber;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "creditAccountDetails",
            "escrowAccountDetails",
            "docDetails",
            "fTTransactionDetails",
            "collectionTransactionDetails",
            "financerequest",
            "financepartner",
        },
        allowSetters = true
    )
    private Disbursement disbursement;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "creditAccountDetails",
            "escrowAccountDetails",
            "docDetails",
            "fTTransactionDetails",
            "collectionTransactionDetails",
            "financerequest",
        },
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

    public EscrowAccountDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEscrowDetailsId() {
        return this.escrowDetailsId;
    }

    public EscrowAccountDetails escrowDetailsId(Long escrowDetailsId) {
        this.setEscrowDetailsId(escrowDetailsId);
        return this;
    }

    public void setEscrowDetailsId(Long escrowDetailsId) {
        this.escrowDetailsId = escrowDetailsId;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public EscrowAccountDetails tenantId(Long tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public EscrowAccountDetails customerId(Long customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccName() {
        return this.accName;
    }

    public EscrowAccountDetails accName(String accName) {
        this.setAccName(accName);
        return this;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public EscrowAccountDetails ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getVirtualAccNumber() {
        return this.virtualAccNumber;
    }

    public EscrowAccountDetails virtualAccNumber(String virtualAccNumber) {
        this.setVirtualAccNumber(virtualAccNumber);
        return this;
    }

    public void setVirtualAccNumber(String virtualAccNumber) {
        this.virtualAccNumber = virtualAccNumber;
    }

    public Long getPoolingAccNumber() {
        return this.poolingAccNumber;
    }

    public EscrowAccountDetails poolingAccNumber(Long poolingAccNumber) {
        this.setPoolingAccNumber(poolingAccNumber);
        return this;
    }

    public void setPoolingAccNumber(Long poolingAccNumber) {
        this.poolingAccNumber = poolingAccNumber;
    }

    public Disbursement getDisbursement() {
        return this.disbursement;
    }

    public void setDisbursement(Disbursement disbursement) {
        this.disbursement = disbursement;
        this.disbursementId = disbursement != null ? disbursement.getId() : null;
    }

    public EscrowAccountDetails disbursement(Disbursement disbursement) {
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

    public EscrowAccountDetails repayment(Repayment repayment) {
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
        if (!(o instanceof EscrowAccountDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((EscrowAccountDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EscrowAccountDetails{" +
            "id=" + getId() +
            ", escrowDetailsId=" + getEscrowDetailsId() +
            ", tenantId=" + getTenantId() +
            ", customerId=" + getCustomerId() +
            ", accName='" + getAccName() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", virtualAccNumber='" + getVirtualAccNumber() + "'" +
            ", poolingAccNumber=" + getPoolingAccNumber() +
            "}";
    }
}
