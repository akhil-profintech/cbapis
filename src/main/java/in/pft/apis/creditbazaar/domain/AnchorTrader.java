package in.pft.apis.creditbazaar.domain;

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
 * A AnchorTrader.
 */
@Table("anchor_trader")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnchorTrader implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private String tenantId;

    @NotNull(message = "must not be null")
    @Column("at_id")
    private String atId;

    @NotNull(message = "must not be null")
    @Column("org_id")
    private String orgId;

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

    @Column("anchor_trader_name")
    private String anchorTraderName;

    @Column("location")
    private String location;

    @Column("anchor_trader_gst")
    private String anchorTraderGST;

    @Column("anchor_trader_sector")
    private String anchorTraderSector;

    @Column("anchor_trader_pan")
    private String anchorTraderPAN;

    @Column("kyc_completed")
    private String kycCompleted;

    @Column("bank_details")
    private String bankDetails;

    @Transient
    @JsonIgnoreProperties(
        value = {
            "repayments",
            "requestOffers",
            "disbursements",
            "prospectRequests",
            "trades",
            "placedOffers",
            "acceptedOffers",
            "settlements",
            "anchortrader",
        },
        allowSetters = true
    )
    private Set<FinanceRequest> financeRequests = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "financepartner", "anchortrader" }, allowSetters = true)
    private Set<AcceptedOffer> acceptedOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "tradepartner", "anchortrader" }, allowSetters = true)
    private Set<Trade> trades = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnchorTrader id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public AnchorTrader tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getAtId() {
        return this.atId;
    }

    public AnchorTrader atId(String atId) {
        this.setAtId(atId);
        return this;
    }

    public void setAtId(String atId) {
        this.atId = atId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public AnchorTrader orgId(String orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public AnchorTrader customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public AnchorTrader orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return this.gstId;
    }

    public AnchorTrader gstId(String gstId) {
        this.setGstId(gstId);
        return this;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public AnchorTrader phoneNumber(Long phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAnchorTraderName() {
        return this.anchorTraderName;
    }

    public AnchorTrader anchorTraderName(String anchorTraderName) {
        this.setAnchorTraderName(anchorTraderName);
        return this;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getLocation() {
        return this.location;
    }

    public AnchorTrader location(String location) {
        this.setLocation(location);
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnchorTraderGST() {
        return this.anchorTraderGST;
    }

    public AnchorTrader anchorTraderGST(String anchorTraderGST) {
        this.setAnchorTraderGST(anchorTraderGST);
        return this;
    }

    public void setAnchorTraderGST(String anchorTraderGST) {
        this.anchorTraderGST = anchorTraderGST;
    }

    public String getAnchorTraderSector() {
        return this.anchorTraderSector;
    }

    public AnchorTrader anchorTraderSector(String anchorTraderSector) {
        this.setAnchorTraderSector(anchorTraderSector);
        return this;
    }

    public void setAnchorTraderSector(String anchorTraderSector) {
        this.anchorTraderSector = anchorTraderSector;
    }

    public String getAnchorTraderPAN() {
        return this.anchorTraderPAN;
    }

    public AnchorTrader anchorTraderPAN(String anchorTraderPAN) {
        this.setAnchorTraderPAN(anchorTraderPAN);
        return this;
    }

    public void setAnchorTraderPAN(String anchorTraderPAN) {
        this.anchorTraderPAN = anchorTraderPAN;
    }

    public String getKycCompleted() {
        return this.kycCompleted;
    }

    public AnchorTrader kycCompleted(String kycCompleted) {
        this.setKycCompleted(kycCompleted);
        return this;
    }

    public void setKycCompleted(String kycCompleted) {
        this.kycCompleted = kycCompleted;
    }

    public String getBankDetails() {
        return this.bankDetails;
    }

    public AnchorTrader bankDetails(String bankDetails) {
        this.setBankDetails(bankDetails);
        return this;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Set<FinanceRequest> getFinanceRequests() {
        return this.financeRequests;
    }

    public void setFinanceRequests(Set<FinanceRequest> financeRequests) {
        if (this.financeRequests != null) {
            this.financeRequests.forEach(i -> i.setAnchortrader(null));
        }
        if (financeRequests != null) {
            financeRequests.forEach(i -> i.setAnchortrader(this));
        }
        this.financeRequests = financeRequests;
    }

    public AnchorTrader financeRequests(Set<FinanceRequest> financeRequests) {
        this.setFinanceRequests(financeRequests);
        return this;
    }

    public AnchorTrader addFinanceRequest(FinanceRequest financeRequest) {
        this.financeRequests.add(financeRequest);
        financeRequest.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeFinanceRequest(FinanceRequest financeRequest) {
        this.financeRequests.remove(financeRequest);
        financeRequest.setAnchortrader(null);
        return this;
    }

    public Set<AcceptedOffer> getAcceptedOffers() {
        return this.acceptedOffers;
    }

    public void setAcceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        if (this.acceptedOffers != null) {
            this.acceptedOffers.forEach(i -> i.setAnchortrader(null));
        }
        if (acceptedOffers != null) {
            acceptedOffers.forEach(i -> i.setAnchortrader(this));
        }
        this.acceptedOffers = acceptedOffers;
    }

    public AnchorTrader acceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        this.setAcceptedOffers(acceptedOffers);
        return this;
    }

    public AnchorTrader addAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.add(acceptedOffer);
        acceptedOffer.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.remove(acceptedOffer);
        acceptedOffer.setAnchortrader(null);
        return this;
    }

    public Set<Trade> getTrades() {
        return this.trades;
    }

    public void setTrades(Set<Trade> trades) {
        if (this.trades != null) {
            this.trades.forEach(i -> i.setAnchortrader(null));
        }
        if (trades != null) {
            trades.forEach(i -> i.setAnchortrader(this));
        }
        this.trades = trades;
    }

    public AnchorTrader trades(Set<Trade> trades) {
        this.setTrades(trades);
        return this;
    }

    public AnchorTrader addTrade(Trade trade) {
        this.trades.add(trade);
        trade.setAnchortrader(this);
        return this;
    }

    public AnchorTrader removeTrade(Trade trade) {
        this.trades.remove(trade);
        trade.setAnchortrader(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnchorTrader)) {
            return false;
        }
        return getId() != null && getId().equals(((AnchorTrader) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnchorTrader{" +
            "id=" + getId() +
            ", tenantId='" + getTenantId() + "'" +
            ", atId='" + getAtId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", anchorTraderName='" + getAnchorTraderName() + "'" +
            ", location='" + getLocation() + "'" +
            ", anchorTraderGST='" + getAnchorTraderGST() + "'" +
            ", anchorTraderSector='" + getAnchorTraderSector() + "'" +
            ", anchorTraderPAN='" + getAnchorTraderPAN() + "'" +
            ", kycCompleted='" + getKycCompleted() + "'" +
            ", bankDetails='" + getBankDetails() + "'" +
            "}";
    }
}
