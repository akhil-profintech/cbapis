package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.ProspectRequest} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProspectRequestDTO implements Serializable {

    private Long id;

    private Long prospectRequestId;

    private String prospectRequestUlidId;

    @NotNull(message = "must not be null")
    private Long anchorTraderId;

    @NotNull(message = "must not be null")
    private String requestAmount;

    @NotNull(message = "must not be null")
    private LocalDate prospectRequestDate;

    @NotNull(message = "must not be null")
    private String currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProspectRequestId() {
        return prospectRequestId;
    }

    public void setProspectRequestId(Long prospectRequestId) {
        this.prospectRequestId = prospectRequestId;
    }

    public String getProspectRequestUlidId() {
        return prospectRequestUlidId;
    }

    public void setProspectRequestUlidId(String prospectRequestUlidId) {
        this.prospectRequestUlidId = prospectRequestUlidId;
    }

    public Long getAnchorTraderId() {
        return anchorTraderId;
    }

    public void setAnchorTraderId(Long anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(String requestAmount) {
        this.requestAmount = requestAmount;
    }

    public LocalDate getProspectRequestDate() {
        return prospectRequestDate;
    }

    public void setProspectRequestDate(LocalDate prospectRequestDate) {
        this.prospectRequestDate = prospectRequestDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProspectRequestDTO)) {
            return false;
        }

        ProspectRequestDTO prospectRequestDTO = (ProspectRequestDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, prospectRequestDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProspectRequestDTO{" +
            "id=" + getId() +
            ", prospectRequestId=" + getProspectRequestId() +
            ", prospectRequestUlidId='" + getProspectRequestUlidId() + "'" +
            ", anchorTraderId=" + getAnchorTraderId() +
            ", requestAmount='" + getRequestAmount() + "'" +
            ", prospectRequestDate='" + getProspectRequestDate() + "'" +
            ", currency='" + getCurrency() + "'" +
            "}";
    }
}
