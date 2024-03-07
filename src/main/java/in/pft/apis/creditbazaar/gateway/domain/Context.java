package in.pft.apis.creditbazaar.gateway.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Context.
 */
@Table("context")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Context implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("transaction_id")
    private String transactionId;

    @NotNull(message = "must not be null")
    @Column("transaction_date")
    private String transactionDate;

    @NotNull(message = "must not be null")
    @Column("action")
    private String action;

    @NotNull(message = "must not be null")
    @Column("user_id")
    private String userId;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private String tenantId;

    @NotNull(message = "must not be null")
    @Column("cp_code")
    private String cpCode;

    @NotNull(message = "must not be null")
    @Column("msgpayload")
    private String msgpayload;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Context id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public Context transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public Context transactionDate(String transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAction() {
        return this.action;
    }

    public Context action(String action) {
        this.setAction(action);
        return this;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return this.userId;
    }

    public Context userId(String userId) {
        this.setUserId(userId);
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public Context tenantId(String tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCpCode() {
        return this.cpCode;
    }

    public Context cpCode(String cpCode) {
        this.setCpCode(cpCode);
        return this;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getMsgpayload() {
        return this.msgpayload;
    }

    public Context msgpayload(String msgpayload) {
        this.setMsgpayload(msgpayload);
        return this;
    }

    public void setMsgpayload(String msgpayload) {
        this.msgpayload = msgpayload;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Context)) {
            return false;
        }
        return getId() != null && getId().equals(((Context) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Context{" +
            "id=" + getId() +
            ", transactionId='" + getTransactionId() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", action='" + getAction() + "'" +
            ", userId='" + getUserId() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", cpCode='" + getCpCode() + "'" +
            ", msgpayload='" + getMsgpayload() + "'" +
            "}";
    }
}
