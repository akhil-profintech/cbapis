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
 * A FinancePartner.
 */
@Table("finance_partner")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinancePartner implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private String tenantId;

    @NotNull(message = "must not be null")
    @Column("fp_id")
    private String fpId;

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

    @Transient
    @JsonIgnoreProperties(
        value = {
            "creditAccountDetails",
            "escrowAccountDetails",
            "docDetails",
            "fTTransactionDetails",
            "collectionTransactionDetails",
            "financerequest",
            "financepartner",
        },
        allowSetters = true
    )
    private Set<Disbursement> disbursements = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "financepartner" }, allowSetters = true)
    private Set<PlacedOffer> placedOffers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "financerequest", "financepartner", "anchortrader" }, allowSetters = true)
    private Set<AcceptedOffer> acceptedOffers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public FinancePartner id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public FinancePartner tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getFpId() {
        return this.fpId;
    }

    public FinancePartner fpId(String fpId) {
        this.setFpId(fpId);
        return this;
    }

    public void setFpId(String fpId) {
        this.fpId = fpId;
    }

    public String getOrgId() {
        return this.orgId;
    }

    public FinancePartner orgId(String orgId) {
        this.setOrgId(orgId);
        return this;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public FinancePartner customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public FinancePartner orgName(String orgName) {
        this.setOrgName(orgName);
        return this;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getGstId() {
        return this.gstId;
    }

    public FinancePartner gstId(String gstId) {
        this.setGstId(gstId);
        return this;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public Long getPhoneNumber() {
        return this.phoneNumber;
    }

    public FinancePartner phoneNumber(Long phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Disbursement> getDisbursements() {
        return this.disbursements;
    }

    public void setDisbursements(Set<Disbursement> disbursements) {
        if (this.disbursements != null) {
            this.disbursements.forEach(i -> i.setFinancepartner(null));
        }
        if (disbursements != null) {
            disbursements.forEach(i -> i.setFinancepartner(this));
        }
        this.disbursements = disbursements;
    }

    public FinancePartner disbursements(Set<Disbursement> disbursements) {
        this.setDisbursements(disbursements);
        return this;
    }

    public FinancePartner addDisbursement(Disbursement disbursement) {
        this.disbursements.add(disbursement);
        disbursement.setFinancepartner(this);
        return this;
    }

    public FinancePartner removeDisbursement(Disbursement disbursement) {
        this.disbursements.remove(disbursement);
        disbursement.setFinancepartner(null);
        return this;
    }

    public Set<PlacedOffer> getPlacedOffers() {
        return this.placedOffers;
    }

    public void setPlacedOffers(Set<PlacedOffer> placedOffers) {
        if (this.placedOffers != null) {
            this.placedOffers.forEach(i -> i.setFinancepartner(null));
        }
        if (placedOffers != null) {
            placedOffers.forEach(i -> i.setFinancepartner(this));
        }
        this.placedOffers = placedOffers;
    }

    public FinancePartner placedOffers(Set<PlacedOffer> placedOffers) {
        this.setPlacedOffers(placedOffers);
        return this;
    }

    public FinancePartner addPlacedOffer(PlacedOffer placedOffer) {
        this.placedOffers.add(placedOffer);
        placedOffer.setFinancepartner(this);
        return this;
    }

    public FinancePartner removePlacedOffer(PlacedOffer placedOffer) {
        this.placedOffers.remove(placedOffer);
        placedOffer.setFinancepartner(null);
        return this;
    }

    public Set<AcceptedOffer> getAcceptedOffers() {
        return this.acceptedOffers;
    }

    public void setAcceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        if (this.acceptedOffers != null) {
            this.acceptedOffers.forEach(i -> i.setFinancepartner(null));
        }
        if (acceptedOffers != null) {
            acceptedOffers.forEach(i -> i.setFinancepartner(this));
        }
        this.acceptedOffers = acceptedOffers;
    }

    public FinancePartner acceptedOffers(Set<AcceptedOffer> acceptedOffers) {
        this.setAcceptedOffers(acceptedOffers);
        return this;
    }

    public FinancePartner addAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.add(acceptedOffer);
        acceptedOffer.setFinancepartner(this);
        return this;
    }

    public FinancePartner removeAcceptedOffer(AcceptedOffer acceptedOffer) {
        this.acceptedOffers.remove(acceptedOffer);
        acceptedOffer.setFinancepartner(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinancePartner)) {
            return false;
        }
        return getId() != null && getId().equals(((FinancePartner) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinancePartner{" +
            "id=" + getId() +
            ", tenantId='" + getTenantId() + "'" +
            ", fpId='" + getFpId() + "'" +
            ", orgId='" + getOrgId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", orgName='" + getOrgName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", phoneNumber=" + getPhoneNumber() +
            "}";
    }
}
