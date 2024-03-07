package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Organization.
 */
@Table("organization")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("org_id")
    private Long orgId;

    @Column("org_ulid_id")
    private String orgUlidId;

    @Column("org_name")
    private String orgName;

    @Column("org_address")
    private String orgAddress;

    @Column("industry_type")
    private String industryType;

    @Column("tenant_id")
    private String tenantId;

    @Transient
    @JsonIgnoreProperties(value = { "organization" }, allowSetters = true)
    private Set<Gstin> gstins = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Organization id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrgId() {
        return this.orgId;
    }

    public Organization orgId(Long orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgUlidId() {
        return this.orgUlidId;
    }

    public Organization orgUlidId(String orgUlidId) {
        this.setOrgUlidId(orgUlidId);
        return this;
    }

    public void setOrgUlidId(String orgUlidId) {
        this.orgUlidId = orgUlidId;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public Organization orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return this.orgAddress;
    }

    public Organization orgAddress(String orgAddress) {
        this.setOrgAddress(orgAddress);
        return this;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getIndustryType() {
        return this.industryType;
    }

    public Organization industryType(String industryType) {
        this.setIndustryType(industryType);
        return this;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public Organization tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Set<Gstin> getGstins() {
        return this.gstins;
    }

    public void setGstins(Set<Gstin> gstins) {
        if (this.gstins != null) {
            this.gstins.forEach(i -> i.setOrganization(null));
        }
        if (gstins != null) {
            gstins.forEach(i -> i.setOrganization(this));
        }
        this.gstins = gstins;
    }

    public Organization gstins(Set<Gstin> gstins) {
        this.setGstins(gstins);
        return this;
    }

    public Organization addGstin(Gstin gstin) {
        this.gstins.add(gstin);
        gstin.setOrganization(this);
        return this;
    }

    public Organization removeGstin(Gstin gstin) {
        this.gstins.remove(gstin);
        gstin.setOrganization(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return getId() != null && getId().equals(((Organization) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", orgId=" + getOrgId() +
            ", orgUlidId='" + getOrgUlidId() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgAddress='" + getOrgAddress() + "'" +
            ", industryType='" + getIndustryType() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
