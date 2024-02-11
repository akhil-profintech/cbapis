package in.pft.apis.creditbazaar.domain;

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
    private String orgId;

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
    private Set<UserDtls> userDtls = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "trades", "organization" }, allowSetters = true)
    private Set<TradePartner> tradePartners = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financeRequests", "acceptedOffers", "trades", "organization" }, allowSetters = true)
    private Set<AnchorTrader> anchorTraders = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "disbursements", "placedOffers", "acceptedOffers", "organization" }, allowSetters = true)
    private Set<FinancePartner> financePartners = new HashSet<>();

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

    public String getOrgId() {
        return this.orgId;
    }

    public Organization orgId(String orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
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

    public Set<UserDtls> getUserDtls() {
        return this.userDtls;
    }

    public void setUserDtls(Set<UserDtls> userDtls) {
        if (this.userDtls != null) {
            this.userDtls.forEach(i -> i.setOrganization(null));
        }
        if (userDtls != null) {
            userDtls.forEach(i -> i.setOrganization(this));
        }
        this.userDtls = userDtls;
    }

    public Organization userDtls(Set<UserDtls> userDtls) {
        this.setUserDtls(userDtls);
        return this;
    }

    public Organization addUserDtls(UserDtls userDtls) {
        this.userDtls.add(userDtls);
        userDtls.setOrganization(this);
        return this;
    }

    public Organization removeUserDtls(UserDtls userDtls) {
        this.userDtls.remove(userDtls);
        userDtls.setOrganization(null);
        return this;
    }

    public Set<TradePartner> getTradePartners() {
        return this.tradePartners;
    }

    public void setTradePartners(Set<TradePartner> tradePartners) {
        if (this.tradePartners != null) {
            this.tradePartners.forEach(i -> i.setOrganization(null));
        }
        if (tradePartners != null) {
            tradePartners.forEach(i -> i.setOrganization(this));
        }
        this.tradePartners = tradePartners;
    }

    public Organization tradePartners(Set<TradePartner> tradePartners) {
        this.setTradePartners(tradePartners);
        return this;
    }

    public Organization addTradePartner(TradePartner tradePartner) {
        this.tradePartners.add(tradePartner);
        tradePartner.setOrganization(this);
        return this;
    }

    public Organization removeTradePartner(TradePartner tradePartner) {
        this.tradePartners.remove(tradePartner);
        tradePartner.setOrganization(null);
        return this;
    }

    public Set<AnchorTrader> getAnchorTraders() {
        return this.anchorTraders;
    }

    public void setAnchorTraders(Set<AnchorTrader> anchorTraders) {
        if (this.anchorTraders != null) {
            this.anchorTraders.forEach(i -> i.setOrganization(null));
        }
        if (anchorTraders != null) {
            anchorTraders.forEach(i -> i.setOrganization(this));
        }
        this.anchorTraders = anchorTraders;
    }

    public Organization anchorTraders(Set<AnchorTrader> anchorTraders) {
        this.setAnchorTraders(anchorTraders);
        return this;
    }

    public Organization addAnchorTrader(AnchorTrader anchorTrader) {
        this.anchorTraders.add(anchorTrader);
        anchorTrader.setOrganization(this);
        return this;
    }

    public Organization removeAnchorTrader(AnchorTrader anchorTrader) {
        this.anchorTraders.remove(anchorTrader);
        anchorTrader.setOrganization(null);
        return this;
    }

    public Set<FinancePartner> getFinancePartners() {
        return this.financePartners;
    }

    public void setFinancePartners(Set<FinancePartner> financePartners) {
        if (this.financePartners != null) {
            this.financePartners.forEach(i -> i.setOrganization(null));
        }
        if (financePartners != null) {
            financePartners.forEach(i -> i.setOrganization(this));
        }
        this.financePartners = financePartners;
    }

    public Organization financePartners(Set<FinancePartner> financePartners) {
        this.setFinancePartners(financePartners);
        return this;
    }

    public Organization addFinancePartner(FinancePartner financePartner) {
        this.financePartners.add(financePartner);
        financePartner.setOrganization(this);
        return this;
    }

    public Organization removeFinancePartner(FinancePartner financePartner) {
        this.financePartners.remove(financePartner);
        financePartner.setOrganization(null);
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
            ", orgId='" + getOrgId() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", orgAddress='" + getOrgAddress() + "'" +
            ", industryType='" + getIndustryType() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            "}";
    }
}
