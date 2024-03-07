package in.pft.apis.creditbazaar.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A FinanceRequestMapping.
 */
@Table("finance_request_mapping")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinanceRequestMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("finance_request_id")
    private Long financeRequestId;

    @Column("finance_request_mapping_ulid_id")
    private String financeRequestMappingUlidId;

    @Column("anchor_trader_id")
    private String anchorTraderId;

    @Column("finance_partner_id")
    private String financePartnerId;

    @Column("anchor_trader_tenant_id")
    private String anchorTraderTenantId;

    @Column("finance_partner_tenant_id")
    private String financePartnerTenantId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FinanceRequestMapping id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinanceRequestId() {
        return this.financeRequestId;
    }

    public FinanceRequestMapping financeRequestId(Long financeRequestId) {
        this.setFinanceRequestId(financeRequestId);
        return this;
    }

    public void setFinanceRequestId(Long financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public String getFinanceRequestMappingUlidId() {
        return this.financeRequestMappingUlidId;
    }

    public FinanceRequestMapping financeRequestMappingUlidId(String financeRequestMappingUlidId) {
        this.setFinanceRequestMappingUlidId(financeRequestMappingUlidId);
        return this;
    }

    public void setFinanceRequestMappingUlidId(String financeRequestMappingUlidId) {
        this.financeRequestMappingUlidId = financeRequestMappingUlidId;
    }

    public String getAnchorTraderId() {
        return this.anchorTraderId;
    }

    public FinanceRequestMapping anchorTraderId(String anchorTraderId) {
        this.setAnchorTraderId(anchorTraderId);
        return this;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getFinancePartnerId() {
        return this.financePartnerId;
    }

    public FinanceRequestMapping financePartnerId(String financePartnerId) {
        this.setFinancePartnerId(financePartnerId);
        return this;
    }

    public void setFinancePartnerId(String financePartnerId) {
        this.financePartnerId = financePartnerId;
    }

    public String getAnchorTraderTenantId() {
        return this.anchorTraderTenantId;
    }

    public FinanceRequestMapping anchorTraderTenantId(String anchorTraderTenantId) {
        this.setAnchorTraderTenantId(anchorTraderTenantId);
        return this;
    }

    public void setAnchorTraderTenantId(String anchorTraderTenantId) {
        this.anchorTraderTenantId = anchorTraderTenantId;
    }

    public String getFinancePartnerTenantId() {
        return this.financePartnerTenantId;
    }

    public FinanceRequestMapping financePartnerTenantId(String financePartnerTenantId) {
        this.setFinancePartnerTenantId(financePartnerTenantId);
        return this;
    }

    public void setFinancePartnerTenantId(String financePartnerTenantId) {
        this.financePartnerTenantId = financePartnerTenantId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinanceRequestMapping)) {
            return false;
        }
        return getId() != null && getId().equals(((FinanceRequestMapping) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinanceRequestMapping{" +
            "id=" + getId() +
            ", financeRequestId=" + getFinanceRequestId() +
            ", financeRequestMappingUlidId='" + getFinanceRequestMappingUlidId() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", financePartnerId='" + getFinancePartnerId() + "'" +
            ", anchorTraderTenantId='" + getAnchorTraderTenantId() + "'" +
            ", financePartnerTenantId='" + getFinancePartnerTenantId() + "'" +
            "}";
    }
}
