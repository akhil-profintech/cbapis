package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.VANumber} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VANumberDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long vaId;

    @NotNull(message = "must not be null")
    private String poolingAccNumber;

    @NotNull(message = "must not be null")
    private String virtualAccNumber;

    @NotNull(message = "must not be null")
    private String vaStatus;

    private Long financeRequestId;

    private String subGroupId;

    private TradeEntityDTO tradeEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVaId() {
        return vaId;
    }

    public void setVaId(Long vaId) {
        this.vaId = vaId;
    }

    public String getPoolingAccNumber() {
        return poolingAccNumber;
    }

    public void setPoolingAccNumber(String poolingAccNumber) {
        this.poolingAccNumber = poolingAccNumber;
    }

    public String getVirtualAccNumber() {
        return virtualAccNumber;
    }

    public void setVirtualAccNumber(String virtualAccNumber) {
        this.virtualAccNumber = virtualAccNumber;
    }

    public String getVaStatus() {
        return vaStatus;
    }

    public void setVaStatus(String vaStatus) {
        this.vaStatus = vaStatus;
    }

    public Long getFinanceRequestId() {
        return financeRequestId;
    }

    public void setFinanceRequestId(Long financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public String getSubGroupId() {
        return subGroupId;
    }

    public void setSubGroupId(String subGroupId) {
        this.subGroupId = subGroupId;
    }

    public TradeEntityDTO getTradeEntity() {
        return tradeEntity;
    }

    public void setTradeEntity(TradeEntityDTO tradeEntity) {
        this.tradeEntity = tradeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VANumberDTO)) {
            return false;
        }

        VANumberDTO vANumberDTO = (VANumberDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, vANumberDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VANumberDTO{" +
            "id=" + getId() +
            ", vaId=" + getVaId() +
            ", poolingAccNumber='" + getPoolingAccNumber() + "'" +
            ", virtualAccNumber='" + getVirtualAccNumber() + "'" +
            ", vaStatus='" + getVaStatus() + "'" +
            ", financeRequestId=" + getFinanceRequestId() +
            ", subGroupId='" + getSubGroupId() + "'" +
            ", tradeEntity=" + getTradeEntity() +
            "}";
    }
}
