package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.TenantDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TenantDetailsDTO implements Serializable {

    private Long id;

    private String tenantUlidId;

    private String tenantSchema;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantUlidId() {
        return tenantUlidId;
    }

    public void setTenantUlidId(String tenantUlidId) {
        this.tenantUlidId = tenantUlidId;
    }

    public String getTenantSchema() {
        return tenantSchema;
    }

    public void setTenantSchema(String tenantSchema) {
        this.tenantSchema = tenantSchema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenantDetailsDTO)) {
            return false;
        }

        TenantDetailsDTO tenantDetailsDTO = (TenantDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tenantDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenantDetailsDTO{" +
            "id=" + getId() +
            ", tenantUlidId='" + getTenantUlidId() + "'" +
            ", tenantSchema='" + getTenantSchema() + "'" +
            "}";
    }
}
