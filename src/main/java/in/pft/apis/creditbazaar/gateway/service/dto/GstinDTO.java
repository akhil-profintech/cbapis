package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.Gstin} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GstinDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String companyName;

    @NotNull(message = "must not be null")
    private String gstId;

    private OrganizationDTO organization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GstinDTO)) {
            return false;
        }

        GstinDTO gstinDTO = (GstinDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, gstinDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GstinDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", organization=" + getOrganization() +
            "}";
    }
}
