package in.pft.apis.creditbazaar.gateway.service.dto;

import in.pft.apis.creditbazaar.gateway.domain.enumeration.Persona;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.UserDtls} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserDtlsDTO implements Serializable {

    private Long id;

    private String userUlidId;

    private String userName;

    private String tenantId;

    private Boolean isAnchorTraderEnabled;

    private String anchorTraderId;

    private Boolean isTradePartnerEnabled;

    private String tradePartnerId;

    private Boolean isFinancePartnerEnabled;

    private String financePartnerId;

    private Persona defaultPersona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUlidId() {
        return userUlidId;
    }

    public void setUserUlidId(String userUlidId) {
        this.userUlidId = userUlidId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public Boolean getIsAnchorTraderEnabled() {
        return isAnchorTraderEnabled;
    }

    public void setIsAnchorTraderEnabled(Boolean isAnchorTraderEnabled) {
        this.isAnchorTraderEnabled = isAnchorTraderEnabled;
    }

    public String getAnchorTraderId() {
        return anchorTraderId;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public Boolean getIsTradePartnerEnabled() {
        return isTradePartnerEnabled;
    }

    public void setIsTradePartnerEnabled(Boolean isTradePartnerEnabled) {
        this.isTradePartnerEnabled = isTradePartnerEnabled;
    }

    public String getTradePartnerId() {
        return tradePartnerId;
    }

    public void setTradePartnerId(String tradePartnerId) {
        this.tradePartnerId = tradePartnerId;
    }

    public Boolean getIsFinancePartnerEnabled() {
        return isFinancePartnerEnabled;
    }

    public void setIsFinancePartnerEnabled(Boolean isFinancePartnerEnabled) {
        this.isFinancePartnerEnabled = isFinancePartnerEnabled;
    }

    public String getFinancePartnerId() {
        return financePartnerId;
    }

    public void setFinancePartnerId(String financePartnerId) {
        this.financePartnerId = financePartnerId;
    }

    public Persona getDefaultPersona() {
        return defaultPersona;
    }

    public void setDefaultPersona(Persona defaultPersona) {
        this.defaultPersona = defaultPersona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDtlsDTO)) {
            return false;
        }

        UserDtlsDTO userDtlsDTO = (UserDtlsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userDtlsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDtlsDTO{" +
            "id=" + getId() +
            ", userUlidId='" + getUserUlidId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", tenantId='" + getTenantId() + "'" +
            ", isAnchorTraderEnabled='" + getIsAnchorTraderEnabled() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", isTradePartnerEnabled='" + getIsTradePartnerEnabled() + "'" +
            ", tradePartnerId='" + getTradePartnerId() + "'" +
            ", isFinancePartnerEnabled='" + getIsFinancePartnerEnabled() + "'" +
            ", financePartnerId='" + getFinancePartnerId() + "'" +
            ", defaultPersona='" + getDefaultPersona() + "'" +
            "}";
    }
}
