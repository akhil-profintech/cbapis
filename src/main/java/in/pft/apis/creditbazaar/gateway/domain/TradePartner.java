package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A TradePartner.
 */
@Table("trade_partner")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradePartner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("tp_id")
    private Long tpId;

    @Column("tp_ulid_id")
    private String tpUlidId;

    @NotNull(message = "must not be null")
    @Column("org_id")
    private String orgId;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private String tenantId;

    @NotNull(message = "must not be null")
    @Column("customer_name")
    private String customerName;

    @NotNull(message = "must not be null")
    @Column("org_name")
    private String orgName;

    @NotNull(message = "must not be null")
    @Column("gst_id")
    private String gstId;

    @NotNull(message = "must not be null")
    @Column("phone_number")
    private Long phoneNumber;

    @Column("trade_partner_name")
    private String tradePartnerName;

    @Column("location")
    private String location;

    @Column("trade_partner_gst")
    private String tradePartnerGST;

    @Column("trade_partner_sector")
    private String tradePartnerSector;

    @Column("acceptance_from_trade_partner")
    private String acceptanceFromTradePartner;

    @Column("tos_document")
    private String tosDocument;

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "anchortrader", "tradepartner" }, allowSetters = true)
    private Set<Trade> trades = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TradePartner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTpId() {
        return this.tpId;
    }

    public TradePartner tpId(Long tpId) {
        this.setTpId(tpId);
        return this;
    }

    public void setTpId(Long tpId) {
        this.tpId = tpId;
    }

    public String getTpUlidId() {
        return this.tpUlidId;
    }

    public TradePartner tpUlidId(String tpUlidId) {
        this.setTpUlidId(tpUlidId);
        return this;
    }

    public void setTpUlidId(String tpUlidId) {
        this.tpUlidId = tpUlidId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public TradePartner orgId(String orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public TradePartner tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public TradePartner customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public TradePartner orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return this.gstId;
    }

    public TradePartner gstId(String gstId) {
        this.setGstId(gstId);
        return this;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public TradePartner phoneNumber(Long phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTradePartnerName() {
        return this.tradePartnerName;
    }

    public TradePartner tradePartnerName(String tradePartnerName) {
        this.setTradePartnerName(tradePartnerName);
        return this;
    }

    public void setTradePartnerName(String tradePartnerName) {
        this.tradePartnerName = tradePartnerName;
    }

    public String getLocation() {
        return this.location;
    }

    public TradePartner location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTradePartnerGST() {
        return this.tradePartnerGST;
    }

    public TradePartner tradePartnerGST(String tradePartnerGST) {
        this.setTradePartnerGST(tradePartnerGST);
        return this;
    }

    public void setTradePartnerGST(String tradePartnerGST) {
        this.tradePartnerGST = tradePartnerGST;
    }

    public String getTradePartnerSector() {
        return this.tradePartnerSector;
    }

    public TradePartner tradePartnerSector(String tradePartnerSector) {
        this.setTradePartnerSector(tradePartnerSector);
        return this;
    }

    public void setTradePartnerSector(String tradePartnerSector) {
        this.tradePartnerSector = tradePartnerSector;
    }

    public String getAcceptanceFromTradePartner() {
        return this.acceptanceFromTradePartner;
    }

    public TradePartner acceptanceFromTradePartner(String acceptanceFromTradePartner) {
        this.setAcceptanceFromTradePartner(acceptanceFromTradePartner);
        return this;
    }

    public void setAcceptanceFromTradePartner(String acceptanceFromTradePartner) {
        this.acceptanceFromTradePartner = acceptanceFromTradePartner;
    }

    public String getTosDocument() {
        return this.tosDocument;
    }

    public TradePartner tosDocument(String tosDocument) {
        this.setTosDocument(tosDocument);
        return this;
    }

    public void setTosDocument(String tosDocument) {
        this.tosDocument = tosDocument;
    }

    public Set<Trade> getTrades() {
        return this.trades;
    }

    public void setTrades(Set<Trade> trades) {
        if (this.trades != null) {
            this.trades.forEach(i -> i.setTradepartner(null));
        }
        if (trades != null) {
            trades.forEach(i -> i.setTradepartner(this));
        }
        this.trades = trades;
    }

    public TradePartner trades(Set<Trade> trades) {
        this.setTrades(trades);
        return this;
    }

    public TradePartner addTrade(Trade trade) {
        this.trades.add(trade);
        trade.setTradepartner(this);
        return this;
    }

    public TradePartner removeTrade(Trade trade) {
        this.trades.remove(trade);
        trade.setTradepartner(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradePartner)) {
            return false;
        }
        return getId() != null && getId().equals(((TradePartner) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradePartner{" +
            "id=" + getId() +
            ", tpId=" + getTpId() +
            ", tpUlidId='" + getTpUlidId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", tradePartnerName='" + getTradePartnerName() + "'" +
            ", location='" + getLocation() + "'" +
            ", tradePartnerGST='" + getTradePartnerGST() + "'" +
            ", tradePartnerSector='" + getTradePartnerSector() + "'" +
            ", acceptanceFromTradePartner='" + getAcceptanceFromTradePartner() + "'" +
            ", tosDocument='" + getTosDocument() + "'" +
            "}";
    }
}
