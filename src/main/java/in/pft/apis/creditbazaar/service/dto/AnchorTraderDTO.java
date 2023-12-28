package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.AnchorTrader} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnchorTraderDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private String tenantId;

    @NotNull(message = "must not be null")
    private String atId;

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

    private String anchorTraderName;

    private String location;

    private String anchorTraderGST;

    private String anchorTraderSector;

    private String anchorTraderPAN;

    private String kycCompleted;

    private String bankDetails;

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

    public String getAtId() {
        return atId;
    }

    public void setAtId(String atId) {
        this.atId = atId;
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

    public String getAnchorTraderName() {
        return anchorTraderName;
    }

    public void setAnchorTraderName(String anchorTraderName) {
        this.anchorTraderName = anchorTraderName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAnchorTraderGST() {
        return anchorTraderGST;
    }

    public void setAnchorTraderGST(String anchorTraderGST) {
        this.anchorTraderGST = anchorTraderGST;
    }

    public String getAnchorTraderSector() {
        return anchorTraderSector;
    }

    public void setAnchorTraderSector(String anchorTraderSector) {
        this.anchorTraderSector = anchorTraderSector;
    }

    public String getAnchorTraderPAN() {
        return anchorTraderPAN;
    }

    public void setAnchorTraderPAN(String anchorTraderPAN) {
        this.anchorTraderPAN = anchorTraderPAN;
    }

    public String getKycCompleted() {
        return kycCompleted;
    }

    public void setKycCompleted(String kycCompleted) {
        this.kycCompleted = kycCompleted;
    }

    public String getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(String bankDetails) {
        this.bankDetails = bankDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnchorTraderDTO)) {
            return false;
        }

        AnchorTraderDTO anchorTraderDTO = (AnchorTraderDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, anchorTraderDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnchorTraderDTO{" +
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
