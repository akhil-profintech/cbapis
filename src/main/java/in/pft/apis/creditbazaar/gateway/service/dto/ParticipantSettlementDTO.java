package in.pft.apis.creditbazaar.gateway.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.ParticipantSettlement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ParticipantSettlementDTO implements Serializable {

    private Long id;

    private Long participantSettlementId;

    private String participantSettlementUlidId;

    @NotNull(message = "must not be null")
    private Long participantId;

    @NotNull(message = "must not be null")
    private String participantName;

    @NotNull(message = "must not be null")
    private String gstId;

    @NotNull(message = "must not be null")
    private String settlementType;

    @NotNull(message = "must not be null")
    private Long settlementAmount;

    @NotNull(message = "must not be null")
    private String settlementDate;

    @NotNull(message = "must not be null")
    private String settlementDueDate;

    @NotNull(message = "must not be null")
    private String settlementContactInfo;

    @NotNull(message = "must not be null")
    private String patronOfPayment;

    @NotNull(message = "must not be null")
    private String recipientOfPayment;

    @NotNull(message = "must not be null")
    private String settlementMethod;

    @NotNull(message = "must not be null")
    private Long tenantId;

    @NotNull(message = "must not be null")
    private String accName;

    @NotNull(message = "must not be null")
    private String ifscCode;

    @NotNull(message = "must not be null")
    private Long accNumber;

    @NotNull(message = "must not be null")
    private String docId;

    private SettlementDTO settlement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipantSettlementId() {
        return participantSettlementId;
    }

    public void setParticipantSettlementId(Long participantSettlementId) {
        this.participantSettlementId = participantSettlementId;
    }

    public String getParticipantSettlementUlidId() {
        return participantSettlementUlidId;
    }

    public void setParticipantSettlementUlidId(String participantSettlementUlidId) {
        this.participantSettlementUlidId = participantSettlementUlidId;
    }

    public Long getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return participantName;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getGstId() {
        return gstId;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public String getSettlementType() {
        return settlementType;
    }

    public void setSettlementType(String settlementType) {
        this.settlementType = settlementType;
    }

    public Long getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(Long settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementDueDate() {
        return settlementDueDate;
    }

    public void setSettlementDueDate(String settlementDueDate) {
        this.settlementDueDate = settlementDueDate;
    }

    public String getSettlementContactInfo() {
        return settlementContactInfo;
    }

    public void setSettlementContactInfo(String settlementContactInfo) {
        this.settlementContactInfo = settlementContactInfo;
    }

    public String getPatronOfPayment() {
        return patronOfPayment;
    }

    public void setPatronOfPayment(String patronOfPayment) {
        this.patronOfPayment = patronOfPayment;
    }

    public String getRecipientOfPayment() {
        return recipientOfPayment;
    }

    public void setRecipientOfPayment(String recipientOfPayment) {
        this.recipientOfPayment = recipientOfPayment;
    }

    public String getSettlementMethod() {
        return settlementMethod;
    }

    public void setSettlementMethod(String settlementMethod) {
        this.settlementMethod = settlementMethod;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public SettlementDTO getSettlement() {
        return settlement;
    }

    public void setSettlement(SettlementDTO settlement) {
        this.settlement = settlement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParticipantSettlementDTO)) {
            return false;
        }

        ParticipantSettlementDTO participantSettlementDTO = (ParticipantSettlementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, participantSettlementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParticipantSettlementDTO{" +
            "id=" + getId() +
            ", participantSettlementId=" + getParticipantSettlementId() +
            ", participantSettlementUlidId='" + getParticipantSettlementUlidId() + "'" +
            ", participantId=" + getParticipantId() +
            ", participantName='" + getParticipantName() + "'" +
            ", gstId='" + getGstId() + "'" +
            ", settlementType='" + getSettlementType() + "'" +
            ", settlementAmount=" + getSettlementAmount() +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", settlementDueDate='" + getSettlementDueDate() + "'" +
            ", settlementContactInfo='" + getSettlementContactInfo() + "'" +
            ", patronOfPayment='" + getPatronOfPayment() + "'" +
            ", recipientOfPayment='" + getRecipientOfPayment() + "'" +
            ", settlementMethod='" + getSettlementMethod() + "'" +
            ", tenantId=" + getTenantId() +
            ", accName='" + getAccName() + "'" +
            ", ifscCode='" + getIfscCode() + "'" +
            ", accNumber=" + getAccNumber() +
            ", docId='" + getDocId() + "'" +
            ", settlement=" + getSettlement() +
            "}";
    }
}
