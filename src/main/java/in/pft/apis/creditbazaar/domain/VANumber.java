package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A VANumber.
 */
@Table("va_number")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VANumber implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("va_id")
    private Long vaId;

    @NotNull(message = "must not be null")
    @Column("pooling_acc_number")
    private String poolingAccNumber;

    @NotNull(message = "must not be null")
    @Column("virtual_acc_number")
    private String virtualAccNumber;

    @NotNull(message = "must not be null")
    @Column("va_status")
    private String vaStatus;

    @Column("finance_request_id")
    private String financeRequestId;

    @Column("sub_group_id")
    private String subGroupId;

    @Transient
    @JsonIgnoreProperties(value = { "beneValidations", "instaAlerts", "fundsTransfers", "updateVAS", "vANumbers" }, allowSetters = true)
    private TradeEntity tradeEntity;

    @Column("trade_entity_id")
    private Long tradeEntityId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VANumber id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVaId() {
        return this.vaId;
    }

    public VANumber vaId(Long vaId) {
        this.setVaId(vaId);
        return this;
    }

    public void setVaId(Long vaId) {
        this.vaId = vaId;
    }

    public String getPoolingAccNumber() {
        return this.poolingAccNumber;
    }

    public VANumber poolingAccNumber(String poolingAccNumber) {
        this.setPoolingAccNumber(poolingAccNumber);
        return this;
    }

    public void setPoolingAccNumber(String poolingAccNumber) {
        this.poolingAccNumber = poolingAccNumber;
    }

    public String getVirtualAccNumber() {
        return this.virtualAccNumber;
    }

    public VANumber virtualAccNumber(String virtualAccNumber) {
        this.setVirtualAccNumber(virtualAccNumber);
        return this;
    }

    public void setVirtualAccNumber(String virtualAccNumber) {
        this.virtualAccNumber = virtualAccNumber;
    }

    public String getVaStatus() {
        return this.vaStatus;
    }

    public VANumber vaStatus(String vaStatus) {
        this.setVaStatus(vaStatus);
        return this;
    }

    public void setVaStatus(String vaStatus) {
        this.vaStatus = vaStatus;
    }

    public String getFinanceRequestId() {
        return this.financeRequestId;
    }

    public VANumber financeRequestId(String financeRequestId) {
        this.setFinanceRequestId(financeRequestId);
        return this;
    }

    public void setFinanceRequestId(String financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public String getSubGroupId() {
        return this.subGroupId;
    }

    public VANumber subGroupId(String subGroupId) {
        this.setSubGroupId(subGroupId);
        return this;
    }

    public void setSubGroupId(String subGroupId) {
        this.subGroupId = subGroupId;
    }

    public TradeEntity getTradeEntity() {
        return this.tradeEntity;
    }

    public void setTradeEntity(TradeEntity tradeEntity) {
        this.tradeEntity = tradeEntity;
        this.tradeEntityId = tradeEntity != null ? tradeEntity.getId() : null;
    }

    public VANumber tradeEntity(TradeEntity tradeEntity) {
        this.setTradeEntity(tradeEntity);
        return this;
    }

    public Long getTradeEntityId() {
        return this.tradeEntityId;
    }

    public void setTradeEntityId(Long tradeEntity) {
        this.tradeEntityId = tradeEntity;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VANumber)) {
            return false;
        }
        return getId() != null && getId().equals(((VANumber) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VANumber{" +
            "id=" + getId() +
            ", vaId=" + getVaId() +
            ", poolingAccNumber='" + getPoolingAccNumber() + "'" +
            ", virtualAccNumber='" + getVirtualAccNumber() + "'" +
            ", vaStatus='" + getVaStatus() + "'" +
            ", financeRequestId='" + getFinanceRequestId() + "'" +
            ", subGroupId='" + getSubGroupId() + "'" +
            "}";
    }
}
