package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.TradePartner} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradePartnerDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String tenantId;

    @NotNull(message = "must not be null")
    private String tpId;

    @NotNull(message = "must not be null")
    private String orgId;

    @NotNull(message = "must not be null")
    private String customerName;

    @NotNull(message = "must not be null")
    private String orgName;

    @NotNull(message = "must not be null")
    private String gstId;

    @NotNull(message = "must not be null")
    private Long phoneNumber;

    private String tradePartnerName;

    private String location;

    private String tradepartnerGST;

    private String tradePartnerSector;

    private String acceptanceFromTradePartner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTpId() {
        return tpId;
    }

    public void setTpId(String tpId) {
        this.tpId = tpId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTradePartnerName() {
        return tradePartnerName;
    }

    public void setTradePartnerName(String tradePartnerName) {
        this.tradePartnerName = tradePartnerName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTradepartnerGST() {
        return tradepartnerGST;
    }

    public void setTradepartnerGST(String tradepartnerGST) {
        this.tradepartnerGST = tradepartnerGST;
    }

    public String getTradePartnerSector() {
        return tradePartnerSector;
    }

    public void setTradePartnerSector(String tradePartnerSector) {
        this.tradePartnerSector = tradePartnerSector;
    }

    public String getAcceptanceFromTradePartner() {
        return acceptanceFromTradePartner;
    }

    public void setAcceptanceFromTradePartner(String acceptanceFromTradePartner) {
        this.acceptanceFromTradePartner = acceptanceFromTradePartner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradePartnerDTO)) {
            return false;
        }

        TradePartnerDTO tradePartnerDTO = (TradePartnerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tradePartnerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradePartnerDTO{" +
            "id=" + getId() +
            ", tenantId='" + getTenantId() + "'" +
            ", tpId='" + getTpId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            ", tradePartnerName='" + getTradePartnerName() + "'" +
            ", location='" + getLocation() + "'" +
            ", tradepartnerGST='" + getTradepartnerGST() + "'" +
            ", tradePartnerSector='" + getTradePartnerSector() + "'" +
            ", acceptanceFromTradePartner='" + getAcceptanceFromTradePartner() + "'" +
            "}";
    }
}
