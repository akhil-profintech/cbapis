package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.Context} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContextDTO implements Serializable {

    private Long id;

    private String transactionId;

    @NotNull(message = "must not be null")
    private String transactionDate;

    @NotNull(message = "must not be null")
    private String action;

    @NotNull(message = "must not be null")
    private String userId;

    @NotNull(message = "must not be null")
    private String tenantId;

    @NotNull(message = "must not be null")
    private String cpCode;

    @NotNull(message = "must not be null")
    private String msgpayload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    public String getMsgpayload() {
        return msgpayload;
    }

    public void setMsgpayload(String msgpayload) {
        this.msgpayload = msgpayload;
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
