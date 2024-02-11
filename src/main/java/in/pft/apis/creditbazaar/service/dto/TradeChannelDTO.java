package in.pft.apis.creditbazaar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.TradeChannel} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradeChannelDTO implements Serializable {

    private Long id;

    private String tradeChannelId;

    private String anchorTraderId;

    private String tradePartnerId;

    private String financePartnerId;

    private String anchorTraderTenantId;

    private String tradePartnerTenantId;

    private String financePartnerTenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeChannelId() {
        return tradeChannelId;
    }

    public void setTradeChannelId(String tradeChannelId) {
        this.tradeChannelId = tradeChannelId;
    }

    public String getAnchorTraderId() {
        return anchorTraderId;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getTradePartnerId() {
        return tradePartnerId;
    }

    public void setTradePartnerId(String tradePartnerId) {
        this.tradePartnerId = tradePartnerId;
    }

    public String getFinancePartnerId() {
        return financePartnerId;
    }

    public void setFinancePartnerId(String financePartnerId) {
        this.financePartnerId = financePartnerId;
    }

    public String getAnchorTraderTenantId() {
        return anchorTraderTenantId;
    }

    public void setAnchorTraderTenantId(String anchorTraderTenantId) {
        this.anchorTraderTenantId = anchorTraderTenantId;
    }

    public String getTradePartnerTenantId() {
        return tradePartnerTenantId;
    }

    public void setTradePartnerTenantId(String tradePartnerTenantId) {
        this.tradePartnerTenantId = tradePartnerTenantId;
    }

    public String getFinancePartnerTenantId() {
        return financePartnerTenantId;
    }

    public void setFinancePartnerTenantId(String financePartnerTenantId) {
        this.financePartnerTenantId = financePartnerTenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeChannelDTO)) {
            return false;
        }

        TradeChannelDTO tradeChannelDTO = (TradeChannelDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tradeChannelDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeChannelDTO{" +
            "id=" + getId() +
            ", tradeChannelId='" + getTradeChannelId() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", tradePartnerId='" + getTradePartnerId() + "'" +
            ", financePartnerId='" + getFinancePartnerId() + "'" +
            ", anchorTraderTenantId='" + getAnchorTraderTenantId() + "'" +
            ", tradePartnerTenantId='" + getTradePartnerTenantId() + "'" +
            ", financePartnerTenantId='" + getFinancePartnerTenantId() + "'" +
            "}";
    }
}
