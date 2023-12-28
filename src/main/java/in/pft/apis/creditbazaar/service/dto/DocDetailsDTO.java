package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.DocDetails} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DocDetailsDTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long docDetailsId;

    @NotNull(message = "must not be null")
    private Long docRecordId;

    @NotNull(message = "must not be null")
    private String link;

    @NotNull(message = "must not be null")
    private String description;

    @NotNull(message = "must not be null")
    private String docType;

    @NotNull(message = "must not be null")
    private String status;

    private DisbursementDTO disbursement;

    private RepaymentDTO repayment;

    private ParticipantSettlementDTO participantsettlement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocDetailsId() {
        return docDetailsId;
    }

    public void setDocDetailsId(Long docDetailsId) {
        this.docDetailsId = docDetailsId;
    }

    public Long getDocRecordId() {
        return docRecordId;
    }

    public void setDocRecordId(Long docRecordId) {
        this.docRecordId = docRecordId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DisbursementDTO getDisbursement() {
        return disbursement;
    }

    public void setDisbursement(DisbursementDTO disbursement) {
        this.disbursement = disbursement;
    }

    public RepaymentDTO getRepayment() {
        return repayment;
    }

    public void setRepayment(RepaymentDTO repayment) {
        this.repayment = repayment;
    }

    public ParticipantSettlementDTO getParticipantsettlement() {
        return participantsettlement;
    }

    public void setParticipantsettlement(ParticipantSettlementDTO participantsettlement) {
        this.participantsettlement = participantsettlement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocDetailsDTO)) {
            return false;
        }

        DocDetailsDTO docDetailsDTO = (DocDetailsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, docDetailsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DocDetailsDTO{" +
            "id=" + getId() +
            ", docDetailsId=" + getDocDetailsId() +
            ", docRecordId=" + getDocRecordId() +
            ", link='" + getLink() + "'" +
            ", description='" + getDescription() + "'" +
            ", docType='" + getDocType() + "'" +
            ", status='" + getStatus() + "'" +
            ", disbursement=" + getDisbursement() +
            ", repayment=" + getRepayment() +
            ", participantsettlement=" + getParticipantsettlement() +
            "}";
    }
}
