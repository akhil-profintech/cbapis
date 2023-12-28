package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.Settlement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SettlementDTO implements Serializable {

    private Long id;

    private String settlementId;

    private String settlementRefNo;

    @NotNull(message = "must not be null")
    private Long offerId;

    @NotNull(message = "must not be null")
    private Long dbmtRequestId;

    @NotNull(message = "must not be null")
    private Long dbmtId;

    @NotNull(message = "must not be null")
    private String dbmtDate;

    @NotNull(message = "must not be null")
    private String dbmtStatus;

    @NotNull(message = "must not be null")
    private Long dbmtAmount;

    @NotNull(message = "must not be null")
    private String currency;

    private FinanceRequestDTO financerequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSettlementId() {
        return settlementId;
    }

    public void setSettlementId(String settlementId) {
        this.settlementId = settlementId;
    }

    public String getSettlementRefNo() {
        return settlementRefNo;
    }

    public void setSettlementRefNo(String settlementRefNo) {
        this.settlementRefNo = settlementRefNo;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Long getDbmtRequestId() {
        return dbmtRequestId;
    }

    public void setDbmtRequestId(Long dbmtRequestId) {
        this.dbmtRequestId = dbmtRequestId;
    }

    public Long getDbmtId() {
        return dbmtId;
    }

    public void setDbmtId(Long dbmtId) {
        this.dbmtId = dbmtId;
    }

    public String getDbmtDate() {
        return dbmtDate;
    }

    public void setDbmtDate(String dbmtDate) {
        this.dbmtDate = dbmtDate;
    }

    public String getDbmtStatus() {
        return dbmtStatus;
    }

    public void setDbmtStatus(String dbmtStatus) {
        this.dbmtStatus = dbmtStatus;
    }

    public Long getDbmtAmount() {
        return dbmtAmount;
    }

    public void setDbmtAmount(Long dbmtAmount) {
        this.dbmtAmount = dbmtAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public FinanceRequestDTO getFinancerequest() {
        return financerequest;
    }

    public void setFinancerequest(FinanceRequestDTO financerequest) {
        this.financerequest = financerequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SettlementDTO)) {
            return false;
        }

        SettlementDTO settlementDTO = (SettlementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, settlementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SettlementDTO{" +
            "id=" + getId() +
            ", settlementId='" + getSettlementId() + "'" +
            ", settlementRefNo='" + getSettlementRefNo() + "'" +
            ", offerId=" + getOfferId() +
            ", dbmtRequestId=" + getDbmtRequestId() +
            ", dbmtId=" + getDbmtId() +
            ", dbmtDate='" + getDbmtDate() + "'" +
            ", dbmtStatus='" + getDbmtStatus() + "'" +
            ", dbmtAmount=" + getDbmtAmount() +
            ", currency='" + getCurrency() + "'" +
            ", financerequest=" + getFinancerequest() +
            "}";
    }
}
