package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.CreditBazaarIntegrator} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CreditBazaarIntegratorDTO implements Serializable {

    private Long id;

    private String integratorUlidId;

    @NotNull(message = "must not be null")
    private String tenantId;

    @NotNull(message = "must not be null")
    private String orgId;

    @NotNull(message = "must not be null")
    private String customerName;

    @NotNull(message = "must not be null")
    private String orgName;

    @NotNull(message = "must not be null")
    private String gstId;

    @NotNull(message = "must not be null")
    private Long phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntegratorUlidId() {
        return integratorUlidId;
    }

    public void setIntegratorUlidId(String integratorUlidId) {
        this.integratorUlidId = integratorUlidId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CreditBazaarIntegratorDTO)) {
            return false;
        }

        CreditBazaarIntegratorDTO creditBazaarIntegratorDTO = (CreditBazaarIntegratorDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, creditBazaarIntegratorDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CreditBazaarIntegratorDTO{" +
            "id=" + getId() +
            ", integratorUlidId='" + getIntegratorUlidId() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
