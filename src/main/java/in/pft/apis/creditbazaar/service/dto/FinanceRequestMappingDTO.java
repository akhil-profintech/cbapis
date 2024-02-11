package in.pft.apis.creditbazaar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.FinanceRequestMapping} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinanceRequestMappingDTO implements Serializable {

    private Long id;

    private String financeRequestId;

    private String anchorTraderId;

    private String financePartnerId;

    private String anchorTraderTenantId;

    private String financePartnerTenantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinanceRequestId() {
        return financeRequestId;
    }

    public void setFinanceRequestId(String financeRequestId) {
        this.financeRequestId = financeRequestId;
    }

    public String getAnchorTraderId() {
        return anchorTraderId;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getFinancePartnerId() {
        return financePartnerId;
    }

    public void setFinancePartnerId(String financePartnerId) {
        this.financePartnerId = financePartnerId;
    }

    public String getAnchorTraderTenantId() {
        return anchorTraderTenantId;
    }

    public void setAnchorTraderTenantId(String anchorTraderTenantId) {
        this.anchorTraderTenantId = anchorTraderTenantId;
    }

    public String getFinancePartnerTenantId() {
        return financePartnerTenantId;
    }

    public void setFinancePartnerTenantId(String financePartnerTenantId) {
        this.financePartnerTenantId = financePartnerTenantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinanceRequestMappingDTO)) {
            return false;
        }

        FinanceRequestMappingDTO financeRequestMappingDTO = (FinanceRequestMappingDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, financeRequestMappingDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinanceRequestMappingDTO{" +
            "id=" + getId() +
            ", financeRequestId='" + getFinanceRequestId() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", financePartnerId='" + getFinancePartnerId() + "'" +
            ", anchorTraderTenantId='" + getAnchorTraderTenantId() + "'" +
            ", financePartnerTenantId='" + getFinancePartnerTenantId() + "'" +
            "}";
    }
}
