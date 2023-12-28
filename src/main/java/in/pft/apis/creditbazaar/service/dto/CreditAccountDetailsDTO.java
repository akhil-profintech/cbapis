package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.CreditAccountDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditAccountDetailsDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long creditAccDetailsId;

    @NotNull(message = "must not be null")
    private Long tenantId;

    @NotNull(message = "must not be null")
    private Long customerId;

    @NotNull(message = "must not be null")
    private String accName;

    @NotNull(message = "must not be null")
    private String ifscCode;

    @NotNull(message = "must not be null")
    private Long accNumber;

    private DisbursementDTO disbursement;

    private RepaymentDTO repayment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreditAccDetailsId() {
        return creditAccDetailsId;
    }

    public void setCreditAccDetailsId(Long creditAccDetailsId) {
        this.creditAccDetailsId = creditAccDetailsId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
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
        if (!(o instanceof CreditAccountDetailsDTO)) {
            return false;
        }

        CreditAccountDetailsDTO creditAccountDetailsDTO = (CreditAccountDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, creditAccountDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditAccountDetailsDTO{" +
            "id=" + getId() +
            ", creditAccDetailsId=" + getCreditAccDetailsId() +
            ", tenantId=" + getTenantId() +
            ", customerId=" + getCustomerId() +
            ", accName='" + getAccName() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", accNumber=" + getAccNumber() +
            ", disbursement=" + getDisbursement() +
            ", repayment=" + getRepayment() +
            "}";
    }
}
