package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.EscrowAccountDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EscrowAccountDetailsDTO implements Serializable {

    private Long id;

    private Long escrowDetailsId;

    private String escrowDetailsUlidId;

    @NotNull(message = "must not be null")
    private Long tenantId;

    @NotNull(message = "must not be null")
    private Long customerId;

    @NotNull(message = "must not be null")
    private String accName;

    @NotNull(message = "must not be null")
    private String ifscCode;

    @NotNull(message = "must not be null")
    private String virtualAccNumber;

    @NotNull(message = "must not be null")
    private Long poolingAccNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEscrowDetailsId() {
        return escrowDetailsId;
    }

    public void setEscrowDetailsId(Long escrowDetailsId) {
        this.escrowDetailsId = escrowDetailsId;
    }

    public String getEscrowDetailsUlidId() {
        return escrowDetailsUlidId;
    }

    public void setEscrowDetailsUlidId(String escrowDetailsUlidId) {
        this.escrowDetailsUlidId = escrowDetailsUlidId;
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

    public String getVirtualAccNumber() {
        return virtualAccNumber;
    }

    public void setVirtualAccNumber(String virtualAccNumber) {
        this.virtualAccNumber = virtualAccNumber;
    }

    public Long getPoolingAccNumber() {
        return poolingAccNumber;
    }

    public void setPoolingAccNumber(Long poolingAccNumber) {
        this.poolingAccNumber = poolingAccNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EscrowAccountDetailsDTO)) {
            return false;
        }

        EscrowAccountDetailsDTO escrowAccountDetailsDTO = (EscrowAccountDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, escrowAccountDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EscrowAccountDetailsDTO{" +
            "id=" + getId() +
            ", escrowDetailsId=" + getEscrowDetailsId() +
            ", escrowDetailsUlidId='" + getEscrowDetailsUlidId() + "'" +
            ", tenantId=" + getTenantId() +
            ", customerId=" + getCustomerId() +
            ", accName='" + getAccName() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", virtualAccNumber='" + getVirtualAccNumber() + "'" +
            ", poolingAccNumber=" + getPoolingAccNumber() +
            "}";
    }
}
