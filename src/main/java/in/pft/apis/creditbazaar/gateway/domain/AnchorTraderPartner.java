package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A AnchorTraderPartner.
 */
@Table("anchor_trader_partner")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnchorTraderPartner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("atpartner_id")
    private Long atpartnerId;

    @Column("atpartner_ulid_id")
    private String atpartnerUlidId;

    @Column("pan")
    private String pan;

    @Column("pan_status")
    private String panStatus;

    @Column("name")
    private String name;

    @Column("aadhar")
    private String aadhar;

    @Column("aadhar_otp")
    private String aadharOtp;

    @Column("aadhar_status")
    private String aadharStatus;

    @Column("aadhar_name")
    private String aadharName;

    @Column("aadhar_address")
    private String aadharAddress;

    @Transient
    @JsonIgnoreProperties(value = { "financeRequests", "anchorTraderPartners", "acceptedOffers", "trades" }, allowSetters = true)
    private AnchorTrader anchortrader;

    @Column("anchortrader_id")
    private Long anchortraderId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AnchorTraderPartner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAtpartnerId() {
        return this.atpartnerId;
    }

    public AnchorTraderPartner atpartnerId(Long atpartnerId) {
        this.setAtpartnerId(atpartnerId);
        return this;
    }

    public void setAtpartnerId(Long atpartnerId) {
        this.atpartnerId = atpartnerId;
    }

    public String getAtpartnerUlidId() {
        return this.atpartnerUlidId;
    }

    public AnchorTraderPartner atpartnerUlidId(String atpartnerUlidId) {
        this.setAtpartnerUlidId(atpartnerUlidId);
        return this;
    }

    public void setAtpartnerUlidId(String atpartnerUlidId) {
        this.atpartnerUlidId = atpartnerUlidId;
    }

    public String getPan() {
        return this.pan;
    }

    public AnchorTraderPartner pan(String pan) {
        this.setPan(pan);
        return this;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getPanStatus() {
        return this.panStatus;
    }

    public AnchorTraderPartner panStatus(String panStatus) {
        this.setPanStatus(panStatus);
        return this;
    }

    public void setPanStatus(String panStatus) {
        this.panStatus = panStatus;
    }

    public String getName() {
        return this.name;
    }

    public AnchorTraderPartner name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadhar() {
        return this.aadhar;
    }

    public AnchorTraderPartner aadhar(String aadhar) {
        this.setAadhar(aadhar);
        return this;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

    public String getAadharOtp() {
        return this.aadharOtp;
    }

    public AnchorTraderPartner aadharOtp(String aadharOtp) {
        this.setAadharOtp(aadharOtp);
        return this;
    }

    public void setAadharOtp(String aadharOtp) {
        this.aadharOtp = aadharOtp;
    }

    public String getAadharStatus() {
        return this.aadharStatus;
    }

    public AnchorTraderPartner aadharStatus(String aadharStatus) {
        this.setAadharStatus(aadharStatus);
        return this;
    }

    public void setAadharStatus(String aadharStatus) {
        this.aadharStatus = aadharStatus;
    }

    public String getAadharName() {
        return this.aadharName;
    }

    public AnchorTraderPartner aadharName(String aadharName) {
        this.setAadharName(aadharName);
        return this;
    }

    public void setAadharName(String aadharName) {
        this.aadharName = aadharName;
    }

    public String getAadharAddress() {
        return this.aadharAddress;
    }

    public AnchorTraderPartner aadharAddress(String aadharAddress) {
        this.setAadharAddress(aadharAddress);
        return this;
    }

    public void setAadharAddress(String aadharAddress) {
        this.aadharAddress = aadharAddress;
    }

    public AnchorTrader getAnchortrader() {
        return this.anchortrader;
    }

    public void setAnchortrader(AnchorTrader anchorTrader) {
        this.anchortrader = anchorTrader;
        this.anchortraderId = anchorTrader != null ? anchorTrader.getId() : null;
    }

    public AnchorTraderPartner anchortrader(AnchorTrader anchorTrader) {
        this.setAnchortrader(anchorTrader);
        return this;
    }

    public Long getAnchortraderId() {
        return this.anchortraderId;
    }

    public void setAnchortraderId(Long anchorTrader) {
        this.anchortraderId = anchorTrader;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnchorTraderPartner)) {
            return false;
        }
        return getId() != null && getId().equals(((AnchorTraderPartner) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnchorTraderPartner{" +
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
            "}";
    }
}
