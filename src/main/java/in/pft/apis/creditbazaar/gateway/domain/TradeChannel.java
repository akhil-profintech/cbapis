package in.pft.apis.creditbazaar.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A TradeChannel.
 */
@Table("trade_channel")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradeChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("trade_channel_ulid_id")
    private String tradeChannelUlidId;

    @Column("anchor_trader_id")
    private String anchorTraderId;

    @Column("trade_partner_id")
    private String tradePartnerId;

    @Column("finance_partner_id")
    private String financePartnerId;

    @Column("anchor_trader_tenant_id")
    private String anchorTraderTenantId;

    @Column("trade_partner_tenant_id")
    private String tradePartnerTenantId;

    @Column("finance_partner_tenant_id")
    private String financePartnerTenantId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TradeChannel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeChannelUlidId() {
        return this.tradeChannelUlidId;
    }

    public TradeChannel tradeChannelUlidId(String tradeChannelUlidId) {
        this.setTradeChannelUlidId(tradeChannelUlidId);
        return this;
    }

    public void setTradeChannelUlidId(String tradeChannelUlidId) {
        this.tradeChannelUlidId = tradeChannelUlidId;
    }

    public String getAnchorTraderId() {
        return this.anchorTraderId;
    }

    public TradeChannel anchorTraderId(String anchorTraderId) {
        this.setAnchorTraderId(anchorTraderId);
        return this;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getTradePartnerId() {
        return this.tradePartnerId;
    }

    public TradeChannel tradePartnerId(String tradePartnerId) {
        this.setTradePartnerId(tradePartnerId);
        return this;
    }

    public void setTradePartnerId(String tradePartnerId) {
        this.tradePartnerId = tradePartnerId;
    }

    public String getFinancePartnerId() {
        return this.financePartnerId;
    }

    public TradeChannel financePartnerId(String financePartnerId) {
        this.setFinancePartnerId(financePartnerId);
        return this;
    }

    public void setFinancePartnerId(String financePartnerId) {
        this.financePartnerId = financePartnerId;
    }

    public String getAnchorTraderTenantId() {
        return this.anchorTraderTenantId;
    }

    public TradeChannel anchorTraderTenantId(String anchorTraderTenantId) {
        this.setAnchorTraderTenantId(anchorTraderTenantId);
        return this;
    }

    public void setAnchorTraderTenantId(String anchorTraderTenantId) {
        this.anchorTraderTenantId = anchorTraderTenantId;
    }

    public String getTradePartnerTenantId() {
        return this.tradePartnerTenantId;
    }

    public TradeChannel tradePartnerTenantId(String tradePartnerTenantId) {
        this.setTradePartnerTenantId(tradePartnerTenantId);
        return this;
    }

    public void setTradePartnerTenantId(String tradePartnerTenantId) {
        this.tradePartnerTenantId = tradePartnerTenantId;
    }

    public String getFinancePartnerTenantId() {
        return this.financePartnerTenantId;
    }

    public TradeChannel financePartnerTenantId(String financePartnerTenantId) {
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
        if (!(o instanceof TradeChannel)) {
            return false;
        }
        return getId() != null && getId().equals(((TradeChannel) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeChannel{" +
            "id=" + getId() +
            ", tradeChannelUlidId='" + getTradeChannelUlidId() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", tradePartnerId='" + getTradePartnerId() + "'" +
            ", financePartnerId='" + getFinancePartnerId() + "'" +
            ", anchorTraderTenantId='" + getAnchorTraderTenantId() + "'" +
            ", tradePartnerTenantId='" + getTradePartnerTenantId() + "'" +
            ", financePartnerTenantId='" + getFinancePartnerTenantId() + "'" +
            "}";
    }
}
