package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A TradeEntity.
 */
@Table("trade_entity")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("entity_id")
    private Long entityId;

    @Column("entity_ulid_id")
    private String entityUlidId;

    @Column("entity_name")
    private String entityName;

    @Column("entity_desc")
    private String entityDesc;

    @Column("entity_gst")
    private String entityGST;

    @Transient
    @JsonIgnoreProperties(value = { "tradeEntity" }, allowSetters = true)
    private Set<BeneValidation> beneValidations = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "tradeEntity" }, allowSetters = true)
    private Set<InstaAlert> instaAlerts = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "tradeEntity" }, allowSetters = true)
    private Set<FundsTransfer> fundsTransfers = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "tradeEntity" }, allowSetters = true)
    private Set<UpdateVA> updateVAS = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "tradeEntity" }, allowSetters = true)
    private Set<VANumber> vANumbers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TradeEntity id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return this.entityId;
    }

    public TradeEntity entityId(Long entityId) {
        this.setEntityId(entityId);
        return this;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityUlidId() {
        return this.entityUlidId;
    }

    public TradeEntity entityUlidId(String entityUlidId) {
        this.setEntityUlidId(entityUlidId);
        return this;
    }

    public void setEntityUlidId(String entityUlidId) {
        this.entityUlidId = entityUlidId;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public TradeEntity entityName(String entityName) {
        this.setEntityName(entityName);
        return this;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityDesc() {
        return this.entityDesc;
    }

    public TradeEntity entityDesc(String entityDesc) {
        this.setEntityDesc(entityDesc);
        return this;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    public String getEntityGST() {
        return this.entityGST;
    }

    public TradeEntity entityGST(String entityGST) {
        this.setEntityGST(entityGST);
        return this;
    }

    public void setEntityGST(String entityGST) {
        this.entityGST = entityGST;
    }

    public Set<BeneValidation> getBeneValidations() {
        return this.beneValidations;
    }

    public void setBeneValidations(Set<BeneValidation> beneValidations) {
        if (this.beneValidations != null) {
            this.beneValidations.forEach(i -> i.setTradeEntity(null));
        }
        if (beneValidations != null) {
            beneValidations.forEach(i -> i.setTradeEntity(this));
        }
        this.beneValidations = beneValidations;
    }

    public TradeEntity beneValidations(Set<BeneValidation> beneValidations) {
        this.setBeneValidations(beneValidations);
        return this;
    }

    public TradeEntity addBeneValidation(BeneValidation beneValidation) {
        this.beneValidations.add(beneValidation);
        beneValidation.setTradeEntity(this);
        return this;
    }

    public TradeEntity removeBeneValidation(BeneValidation beneValidation) {
        this.beneValidations.remove(beneValidation);
        beneValidation.setTradeEntity(null);
        return this;
    }

    public Set<InstaAlert> getInstaAlerts() {
        return this.instaAlerts;
    }

    public void setInstaAlerts(Set<InstaAlert> instaAlerts) {
        if (this.instaAlerts != null) {
            this.instaAlerts.forEach(i -> i.setTradeEntity(null));
        }
        if (instaAlerts != null) {
            instaAlerts.forEach(i -> i.setTradeEntity(this));
        }
        this.instaAlerts = instaAlerts;
    }

    public TradeEntity instaAlerts(Set<InstaAlert> instaAlerts) {
        this.setInstaAlerts(instaAlerts);
        return this;
    }

    public TradeEntity addInstaAlert(InstaAlert instaAlert) {
        this.instaAlerts.add(instaAlert);
        instaAlert.setTradeEntity(this);
        return this;
    }

    public TradeEntity removeInstaAlert(InstaAlert instaAlert) {
        this.instaAlerts.remove(instaAlert);
        instaAlert.setTradeEntity(null);
        return this;
    }

    public Set<FundsTransfer> getFundsTransfers() {
        return this.fundsTransfers;
    }

    public void setFundsTransfers(Set<FundsTransfer> fundsTransfers) {
        if (this.fundsTransfers != null) {
            this.fundsTransfers.forEach(i -> i.setTradeEntity(null));
        }
        if (fundsTransfers != null) {
            fundsTransfers.forEach(i -> i.setTradeEntity(this));
        }
        this.fundsTransfers = fundsTransfers;
    }

    public TradeEntity fundsTransfers(Set<FundsTransfer> fundsTransfers) {
        this.setFundsTransfers(fundsTransfers);
        return this;
    }

    public TradeEntity addFundsTransfer(FundsTransfer fundsTransfer) {
        this.fundsTransfers.add(fundsTransfer);
        fundsTransfer.setTradeEntity(this);
        return this;
    }

    public TradeEntity removeFundsTransfer(FundsTransfer fundsTransfer) {
        this.fundsTransfers.remove(fundsTransfer);
        fundsTransfer.setTradeEntity(null);
        return this;
    }

    public Set<UpdateVA> getUpdateVAS() {
        return this.updateVAS;
    }

    public void setUpdateVAS(Set<UpdateVA> updateVAS) {
        if (this.updateVAS != null) {
            this.updateVAS.forEach(i -> i.setTradeEntity(null));
        }
        if (updateVAS != null) {
            updateVAS.forEach(i -> i.setTradeEntity(this));
        }
        this.updateVAS = updateVAS;
    }

    public TradeEntity updateVAS(Set<UpdateVA> updateVAS) {
        this.setUpdateVAS(updateVAS);
        return this;
    }

    public TradeEntity addUpdateVA(UpdateVA updateVA) {
        this.updateVAS.add(updateVA);
        updateVA.setTradeEntity(this);
        return this;
    }

    public TradeEntity removeUpdateVA(UpdateVA updateVA) {
        this.updateVAS.remove(updateVA);
        updateVA.setTradeEntity(null);
        return this;
    }

    public Set<VANumber> getVANumbers() {
        return this.vANumbers;
    }

    public void setVANumbers(Set<VANumber> vANumbers) {
        if (this.vANumbers != null) {
            this.vANumbers.forEach(i -> i.setTradeEntity(null));
        }
        if (vANumbers != null) {
            vANumbers.forEach(i -> i.setTradeEntity(this));
        }
        this.vANumbers = vANumbers;
    }

    public TradeEntity vANumbers(Set<VANumber> vANumbers) {
        this.setVANumbers(vANumbers);
        return this;
    }

    public TradeEntity addVANumber(VANumber vANumber) {
        this.vANumbers.add(vANumber);
        vANumber.setTradeEntity(this);
        return this;
    }

    public TradeEntity removeVANumber(VANumber vANumber) {
        this.vANumbers.remove(vANumber);
        vANumber.setTradeEntity(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeEntity)) {
            return false;
        }
        return getId() != null && getId().equals(((TradeEntity) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeEntity{" +
            "id=" + getId() +
            ", entityId=" + getEntityId() +
            ", entityUlidId='" + getEntityUlidId() + "'" +
            ", entityName='" + getEntityName() + "'" +
            ", entityDesc='" + getEntityDesc() + "'" +
            ", entityGST='" + getEntityGST() + "'" +
            "}";
    }
}
