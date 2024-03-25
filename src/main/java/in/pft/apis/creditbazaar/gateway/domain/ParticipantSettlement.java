package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import in.pft.apis.creditbazaar.gateway.domain.enumeration.SettlementType;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ParticipantSettlement.
 */
@Table("participant_settlement")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ParticipantSettlement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("participant_settlement_id")
    private Long participantSettlementId;

    @Column("participant_settlement_ulid_id")
    private String participantSettlementUlidId;

    @NotNull(message = "must not be null")
    @Column("participant_id")
    private Long participantId;

    @NotNull(message = "must not be null")
    @Column("participant_name")
    private String participantName;

    @NotNull(message = "must not be null")
    @Column("gst_id")
    private String gstId;

    @NotNull(message = "must not be null")
    @Column("settlement_type")
    private SettlementType settlementType;

    @NotNull(message = "must not be null")
    @Column("settlement_amount")
    private Long settlementAmount;

    @NotNull(message = "must not be null")
    @Column("settlement_date")
    private String settlementDate;

    @NotNull(message = "must not be null")
    @Column("settlement_due_date")
    private String settlementDueDate;

    @NotNull(message = "must not be null")
    @Column("settlement_contact_info")
    private String settlementContactInfo;

    @NotNull(message = "must not be null")
    @Column("patron_of_payment")
    private String patronOfPayment;

    @NotNull(message = "must not be null")
    @Column("recipient_of_payment")
    private String recipientOfPayment;

    @NotNull(message = "must not be null")
    @Column("settlement_method")
    private String settlementMethod;

    @NotNull(message = "must not be null")
    @Column("tenant_id")
    private Long tenantId;

    @NotNull(message = "must not be null")
    @Column("acc_name")
    private String accName;

    @NotNull(message = "must not be null")
    @Column("ifsc_code")
    private String ifscCode;

    @NotNull(message = "must not be null")
    @Column("acc_number")
    private Long accNumber;

    @NotNull(message = "must not be null")
    @Column("doc_id")
    private String docId;

    @Transient
    @JsonIgnoreProperties(value = { "participantsettlement", "disbursement", "repayment" }, allowSetters = true)
    private Set<FundsTransferTransactionDetails> fundsTransferTransactionDetails = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "participantSettlements", "financerequest" }, allowSetters = true)
    private Settlement settlement;

    @Column("settlement_id")
    private Long settlementId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ParticipantSettlement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParticipantSettlementId() {
        return this.participantSettlementId;
    }

    public ParticipantSettlement participantSettlementId(Long participantSettlementId) {
        this.setParticipantSettlementId(participantSettlementId);
        return this;
    }

    public void setParticipantSettlementId(Long participantSettlementId) {
        this.participantSettlementId = participantSettlementId;
    }

    public String getParticipantSettlementUlidId() {
        return this.participantSettlementUlidId;
    }

    public ParticipantSettlement participantSettlementUlidId(String participantSettlementUlidId) {
        this.setParticipantSettlementUlidId(participantSettlementUlidId);
        return this;
    }

    public void setParticipantSettlementUlidId(String participantSettlementUlidId) {
        this.participantSettlementUlidId = participantSettlementUlidId;
    }

    public Long getParticipantId() {
        return this.participantId;
    }

    public ParticipantSettlement participantId(Long participantId) {
        this.setParticipantId(participantId);
        return this;
    }

    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    public String getParticipantName() {
        return this.participantName;
    }

    public ParticipantSettlement participantName(String participantName) {
        this.setParticipantName(participantName);
        return this;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public String getGstId() {
        return this.gstId;
    }

    public ParticipantSettlement gstId(String gstId) {
        this.setGstId(gstId);
        return this;
    }

    public void setGstId(String gstId) {
        this.gstId = gstId;
    }

    public SettlementType getSettlementType() {
        return this.settlementType;
    }

    public ParticipantSettlement settlementType(SettlementType settlementType) {
        this.setSettlementType(settlementType);
        return this;
    }

    public void setSettlementType(SettlementType settlementType) {
        this.settlementType = settlementType;
    }

    public Long getSettlementAmount() {
        return this.settlementAmount;
    }

    public ParticipantSettlement settlementAmount(Long settlementAmount) {
        this.setSettlementAmount(settlementAmount);
        return this;
    }

    public void setSettlementAmount(Long settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getSettlementDate() {
        return this.settlementDate;
    }

    public ParticipantSettlement settlementDate(String settlementDate) {
        this.setSettlementDate(settlementDate);
        return this;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getSettlementDueDate() {
        return this.settlementDueDate;
    }

    public ParticipantSettlement settlementDueDate(String settlementDueDate) {
        this.setSettlementDueDate(settlementDueDate);
        return this;
    }

    public void setSettlementDueDate(String settlementDueDate) {
        this.settlementDueDate = settlementDueDate;
    }

    public String getSettlementContactInfo() {
        return this.settlementContactInfo;
    }

    public ParticipantSettlement settlementContactInfo(String settlementContactInfo) {
        this.setSettlementContactInfo(settlementContactInfo);
        return this;
    }

    public void setSettlementContactInfo(String settlementContactInfo) {
        this.settlementContactInfo = settlementContactInfo;
    }

    public String getPatronOfPayment() {
        return this.patronOfPayment;
    }

    public ParticipantSettlement patronOfPayment(String patronOfPayment) {
        this.setPatronOfPayment(patronOfPayment);
        return this;
    }

    public void setPatronOfPayment(String patronOfPayment) {
        this.patronOfPayment = patronOfPayment;
    }

    public String getRecipientOfPayment() {
        return this.recipientOfPayment;
    }

    public ParticipantSettlement recipientOfPayment(String recipientOfPayment) {
        this.setRecipientOfPayment(recipientOfPayment);
        return this;
    }

    public void setRecipientOfPayment(String recipientOfPayment) {
        this.recipientOfPayment = recipientOfPayment;
    }

    public String getSettlementMethod() {
        return this.settlementMethod;
    }

    public ParticipantSettlement settlementMethod(String settlementMethod) {
        this.setSettlementMethod(settlementMethod);
        return this;
    }

    public void setSettlementMethod(String settlementMethod) {
        this.settlementMethod = settlementMethod;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public ParticipantSettlement tenantId(Long tenantId) {
        this.setTenantId(tenantId);
        return this;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getAccName() {
        return this.accName;
    }

    public ParticipantSettlement accName(String accName) {
        this.setAccName(accName);
        return this;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getIfscCode() {
        return this.ifscCode;
    }

    public ParticipantSettlement ifscCode(String ifscCode) {
        this.setIfscCode(ifscCode);
        return this;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public Long getAccNumber() {
        return this.accNumber;
    }

    public ParticipantSettlement accNumber(Long accNumber) {
        this.setAccNumber(accNumber);
        return this;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public String getDocId() {
        return this.docId;
    }

    public ParticipantSettlement docId(String docId) {
        this.setDocId(docId);
        return this;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Set<FundsTransferTransactionDetails> getFundsTransferTransactionDetails() {
        return this.fundsTransferTransactionDetails;
    }

    public void setFundsTransferTransactionDetails(Set<FundsTransferTransactionDetails> fundsTransferTransactionDetails) {
        if (this.fundsTransferTransactionDetails != null) {
            this.fundsTransferTransactionDetails.forEach(i -> i.setParticipantsettlement(null));
        }
        if (fundsTransferTransactionDetails != null) {
            fundsTransferTransactionDetails.forEach(i -> i.setParticipantsettlement(this));
        }
        this.fundsTransferTransactionDetails = fundsTransferTransactionDetails;
    }

    public ParticipantSettlement fundsTransferTransactionDetails(Set<FundsTransferTransactionDetails> fundsTransferTransactionDetails) {
        this.setFundsTransferTransactionDetails(fundsTransferTransactionDetails);
        return this;
    }

    public ParticipantSettlement addFundsTransferTransactionDetails(FundsTransferTransactionDetails fundsTransferTransactionDetails) {
        this.fundsTransferTransactionDetails.add(fundsTransferTransactionDetails);
        fundsTransferTransactionDetails.setParticipantsettlement(this);
        return this;
    }

    public ParticipantSettlement removeFundsTransferTransactionDetails(FundsTransferTransactionDetails fundsTransferTransactionDetails) {
        this.fundsTransferTransactionDetails.remove(fundsTransferTransactionDetails);
        fundsTransferTransactionDetails.setParticipantsettlement(null);
        return this;
    }

    public Settlement getSettlement() {
        return this.settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
        this.settlementId = settlement != null ? settlement.getId() : null;
    }

    public ParticipantSettlement settlement(Settlement settlement) {
        this.setSettlement(settlement);
        return this;
    }

    public Long getSettlementId() {
        return this.settlementId;
    }

    public void setSettlementId(Long settlement) {
        this.settlementId = settlement;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParticipantSettlement)) {
            return false;
        }
        return getId() != null && getId().equals(((ParticipantSettlement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ParticipantSettlement{" +
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
            "}";
    }
}
