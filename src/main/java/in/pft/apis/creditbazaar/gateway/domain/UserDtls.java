package in.pft.apis.creditbazaar.gateway.domain;

import in.pft.apis.creditbazaar.gateway.domain.enumeration.Persona;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A UserDtls.
 */
@Table("user_dtls")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserDtls implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("user_ulid_id")
    private String userUlidId;

    @Column("user_name")
    private String userName;

    @Column("tenant_id")
    private String tenantId;

    @Column("is_anchor_trader_enabled")
    private Boolean isAnchorTraderEnabled;

    @Column("anchor_trader_id")
    private String anchorTraderId;

    @Column("is_trade_partner_enabled")
    private Boolean isTradePartnerEnabled;

    @Column("trade_partner_id")
    private String tradePartnerId;

    @Column("is_finance_partner_enabled")
    private Boolean isFinancePartnerEnabled;

    @Column("finance_partner_id")
    private String financePartnerId;

    @Column("default_persona")
    private Persona defaultPersona;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserDtls id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUlidId() {
        return this.userUlidId;
    }

    public UserDtls userUlidId(String userUlidId) {
        this.setUserUlidId(userUlidId);
        return this;
    }

    public void setUserUlidId(String userUlidId) {
        this.userUlidId = userUlidId;
    }

    public String getUserName() {
        return this.userName;
    }

    public UserDtls userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public UserDtls tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getIsAnchorTraderEnabled() {
        return this.isAnchorTraderEnabled;
    }

    public UserDtls isAnchorTraderEnabled(Boolean isAnchorTraderEnabled) {
        this.setIsAnchorTraderEnabled(isAnchorTraderEnabled);
        return this;
    }

    public void setIsAnchorTraderEnabled(Boolean isAnchorTraderEnabled) {
        this.isAnchorTraderEnabled = isAnchorTraderEnabled;
    }

    public String getAnchorTraderId() {
        return this.anchorTraderId;
    }

    public UserDtls anchorTraderId(String anchorTraderId) {
        this.setAnchorTraderId(anchorTraderId);
        return this;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public Boolean getIsTradePartnerEnabled() {
        return this.isTradePartnerEnabled;
    }

    public UserDtls isTradePartnerEnabled(Boolean isTradePartnerEnabled) {
        this.setIsTradePartnerEnabled(isTradePartnerEnabled);
        return this;
    }

    public void setIsTradePartnerEnabled(Boolean isTradePartnerEnabled) {
        this.isTradePartnerEnabled = isTradePartnerEnabled;
    }

    public String getTradePartnerId() {
        return this.tradePartnerId;
    }

    public UserDtls tradePartnerId(String tradePartnerId) {
        this.setTradePartnerId(tradePartnerId);
        return this;
    }

    public void setTradePartnerId(String tradePartnerId) {
        this.tradePartnerId = tradePartnerId;
    }

    public Boolean getIsFinancePartnerEnabled() {
        return this.isFinancePartnerEnabled;
    }

    public UserDtls isFinancePartnerEnabled(Boolean isFinancePartnerEnabled) {
        this.setIsFinancePartnerEnabled(isFinancePartnerEnabled);
        return this;
    }

    public void setIsFinancePartnerEnabled(Boolean isFinancePartnerEnabled) {
        this.isFinancePartnerEnabled = isFinancePartnerEnabled;
    }

    public String getFinancePartnerId() {
        return this.financePartnerId;
    }

    public UserDtls financePartnerId(String financePartnerId) {
        this.setFinancePartnerId(financePartnerId);
        return this;
    }

    public void setFinancePartnerId(String financePartnerId) {
        this.financePartnerId = financePartnerId;
    }

    public Persona getDefaultPersona() {
        return this.defaultPersona;
    }

    public UserDtls defaultPersona(Persona defaultPersona) {
        this.setDefaultPersona(defaultPersona);
        return this;
    }

    public void setDefaultPersona(Persona defaultPersona) {
        this.defaultPersona = defaultPersona;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDtls)) {
            return false;
        }
        return getId() != null && getId().equals(((UserDtls) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDtls{" +
            "id=" + getId() +
            ", userUlidId='" + getUserUlidId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", isAnchorTraderEnabled='" + getIsAnchorTraderEnabled() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", isTradePartnerEnabled='" + getIsTradePartnerEnabled() + "'" +
            ", tradePartnerId='" + getTradePartnerId() + "'" +
            ", isFinancePartnerEnabled='" + getIsFinancePartnerEnabled() + "'" +
            ", financePartnerId='" + getFinancePartnerId() + "'" +
            ", defaultPersona='" + getDefaultPersona() + "'" +
            "}";
    }
}
