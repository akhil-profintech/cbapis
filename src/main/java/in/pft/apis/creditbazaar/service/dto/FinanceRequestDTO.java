package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.FinanceRequest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FinanceRequestDTO implements Serializable {

    private Long id;

    private String requestId;

    private String financeRequestRefNo;

    @NotNull(message = "must not be null")
    private String requestAmount;

    @NotNull(message = "must not be null")
    private LocalDate requestDate;

    @NotNull(message = "must not be null")
    private String currency;

    @NotNull(message = "must not be null")
    private String requestStatus;

    @NotNull(message = "must not be null")
    private LocalDate dueDate;

    private AnchorTraderDTO anchortrader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getFinanceRequestRefNo() {
        return financeRequestRefNo;
    }

    public void setFinanceRequestRefNo(String financeRequestRefNo) {
        this.financeRequestRefNo = financeRequestRefNo;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public AnchorTraderDTO getAnchortrader() {
        return anchortrader;
    }

    public void setAnchortrader(AnchorTraderDTO anchortrader) {
        this.anchortrader = anchortrader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FinanceRequestDTO)) {
            return false;
        }

        FinanceRequestDTO financeRequestDTO = (FinanceRequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, financeRequestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FinanceRequestDTO{" +
            "id=" + getId() +
            ", requestId='" + getRequestId() + "'" +
            ", financeRequestRefNo='" + getFinanceRequestRefNo() + "'" +
            ", requestAmount='" + getRequestAmount() + "'" +
            ", requestDate='" + getRequestDate() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", dueDate='" + getDueDate() + "'" +
            ", anchortrader=" + getAnchortrader() +
            "}";
    }
}
