package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.AnchorTraderPartner} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnchorTraderPartnerDTO implements Serializable {

    private Long id;

    private Long atpartnerId;

    private String atpartnerUlidId;

    private String pan;

    private String panStatus;

    private String name;

    private String aadhar;

    private String aadharOtp;

    private String aadharStatus;

    private String aadharName;

    private String aadharAddress;

    private AnchorTraderDTO anchortrader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtpartnerId() {
        return atpartnerId;
    }

    public void setAtpartnerId(Long atpartnerId) {
        this.atpartnerId = atpartnerId;
    }

    public String getAtpartnerUlidId() {
        return atpartnerUlidId;
    }

    public void setAtpartnerUlidId(String atpartnerUlidId) {
        this.atpartnerUlidId = atpartnerUlidId;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanStatus() {
        return panStatus;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAadharOtp() {
        return aadharOtp;
    }

    public void setAadharOtp(String aadharOtp) {
        this.aadharOtp = aadharOtp;
    }

    public String getAadharStatus() {
        return aadharStatus;
    }

    public void setAadharStatus(String aadharStatus) {
        this.aadharStatus = aadharStatus;
    }

    public String getAadharName() {
        return aadharName;
    }

    public void setAadharName(String aadharName) {
        this.aadharName = aadharName;
    }

    public String getAadharAddress() {
        return aadharAddress;
    }

    public void setAadharAddress(String aadharAddress) {
        this.aadharAddress = aadharAddress;
    }

    public AnchorTraderDTO getAnchortrader() {
        return anchortrader;
    }

    public void setAnchortrader(AnchorTraderDTO anchortrader) {
        this.anchortrader = anchortrader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnchorTraderPartnerDTO)) {
            return false;
        }

        AnchorTraderPartnerDTO anchorTraderPartnerDTO = (AnchorTraderPartnerDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, anchorTraderPartnerDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnchorTraderPartnerDTO{" +
            "id=" + getId() +
            ", atpartnerId=" + getAtpartnerId() +
            ", atpartnerUlidId='" + getAtpartnerUlidId() + "'" +
            ", pan='" + getPan() + "'" +
            ", panStatus='" + getPanStatus() + "'" +
            ", name='" + getName() + "'" +
            ", aadhar='" + getAadhar() + "'" +
            ", aadharOtp='" + getAadharOtp() + "'" +
            ", aadharStatus='" + getAadharStatus() + "'" +
            ", aadharName='" + getAadharName() + "'" +
            ", aadharAddress='" + getAadharAddress() + "'" +
            ", anchortrader=" + getAnchortrader() +
            "}";
    }
}
