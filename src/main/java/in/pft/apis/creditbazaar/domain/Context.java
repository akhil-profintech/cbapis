package in.pft.apis.creditbazaar.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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

    @NotNull(message = "must not be null")
    @Column("transaction_id")
    private Long transactionId;

    @NotNull(message = "must not be null")
    @Column("transaction_date")
    private Instant transactionDate;

    @NotNull(message = "must not be null")
    @Column("client_id")
    private Long clientId;

    @NotNull(message = "must not be null")
    @Column("cp_code")
    private String cpCode;

    @Transient
    @JsonIgnoreProperties(value = { "contexts" }, allowSetters = true)
    private Action action;

    @Column("action_id")
    private Long actionId;

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

    public Long getTransactionId() {
        return this.transactionId;
    }

    public Context transactionId(Long transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getTransactionDate() {
        return this.transactionDate;
    }

    public Context transactionDate(Instant transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getClientId() {
        return this.clientId;
    }

    public Context clientId(Long clientId) {
        this.setClientId(clientId);
        return this;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
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

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
        this.actionId = action != null ? action.getId() : null;
    }

    public Context action(Action action) {
        this.setAction(action);
        return this;
    }

    public Long getActionId() {
        return this.actionId;
    }

    public void setActionId(Long action) {
        this.actionId = action;
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
            ", transactionId=" + getTransactionId() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", clientId=" + getClientId() +
            ", cpCode='" + getCpCode() + "'" +
            "}";
    }
}
