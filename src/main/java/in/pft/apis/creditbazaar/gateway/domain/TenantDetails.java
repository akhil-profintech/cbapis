package in.pft.apis.creditbazaar.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A TenantDetails.
 */
@Table("tenant_details")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TenantDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("tenant_ulid_id")
    private String tenantUlidId;

    @Column("tenant_schema")
    private String tenantSchema;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TenantDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantUlidId() {
        return this.tenantUlidId;
    }

    public TenantDetails tenantUlidId(String tenantUlidId) {
        this.setTenantUlidId(tenantUlidId);
        return this;
    }

    public void setTenantUlidId(String tenantUlidId) {
        this.tenantUlidId = tenantUlidId;
    }

    public String getTenantSchema() {
        return this.tenantSchema;
    }

    public TenantDetails tenantSchema(String tenantSchema) {
        this.setTenantSchema(tenantSchema);
        return this;
    }

    public void setTenantSchema(String tenantSchema) {
        this.tenantSchema = tenantSchema;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TenantDetails)) {
            return false;
        }
        return getId() != null && getId().equals(((TenantDetails) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TenantDetails{" +
            "id=" + getId() +
            ", tenantUlidId='" + getTenantUlidId() + "'" +
            ", tenantSchema='" + getTenantSchema() + "'" +
            "}";
    }
}
