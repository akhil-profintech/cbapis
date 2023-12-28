package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.Context} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContextDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long transactionId;

    @NotNull(message = "must not be null")
    private Instant transactionDate;

    @NotNull(message = "must not be null")
    private Long clientId;

    @NotNull(message = "must not be null")
    private String cpCode;

    private ActionDTO action;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Instant transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public ActionDTO getAction() {
        return action;
    }

    public void setAction(ActionDTO action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContextDTO)) {
            return false;
        }

        ContextDTO contextDTO = (ContextDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contextDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContextDTO{" +
            "id=" + getId() +
            ", transactionId=" + getTransactionId() +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", clientId=" + getClientId() +
            ", cpCode='" + getCpCode() + "'" +
            ", action=" + getAction() +
            "}";
    }
}
