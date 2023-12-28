package in.pft.apis.creditbazaar.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.CBCREProcess} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CBCREProcessDTO implements Serializable {

    private Long id;

    private String cbProcessId;

    private String financeRequestId;

    private String anchorTraderId;

    private String anchortTraderGst;

    private String financeAmount;

    private String processDateTime;

    private String creProcessStatus;

    private String responseDateTime;

    private String creRequestId;

    private Float cumilativetradescore;

    private String timestamp;

    private Long totalAmountRequested;

    private Long totalInvoiceAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCbProcessId() {
        return cbProcessId;
    }

    public void setCbProcessId(String cbProcessId) {
        this.cbProcessId = cbProcessId;
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

    public String getAnchortTraderGst() {
        return anchortTraderGst;
    }

    public void setAnchortTraderGst(String anchortTraderGst) {
        this.anchortTraderGst = anchortTraderGst;
    }

    public String getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(String financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getProcessDateTime() {
        return processDateTime;
    }

    public void setProcessDateTime(String processDateTime) {
        this.processDateTime = processDateTime;
    }

    public String getCreProcessStatus() {
        return creProcessStatus;
    }

    public void setCreProcessStatus(String creProcessStatus) {
        this.creProcessStatus = creProcessStatus;
    }

    public String getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getCreRequestId() {
        return creRequestId;
    }

    public void setCreRequestId(String creRequestId) {
        this.creRequestId = creRequestId;
    }

    public Float getCumilativetradescore() {
        return cumilativetradescore;
    }

    public void setCumilativetradescore(Float cumilativetradescore) {
        this.cumilativetradescore = cumilativetradescore;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTotalAmountRequested() {
        return totalAmountRequested;
    }

    public void setTotalAmountRequested(Long totalAmountRequested) {
        this.totalAmountRequested = totalAmountRequested;
    }

    public Long getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(Long totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CBCREProcessDTO)) {
            return false;
        }

        CBCREProcessDTO cBCREProcessDTO = (CBCREProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, cBCREProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CBCREProcessDTO{" +
            "id=" + getId() +
            ", cbProcessId='" + getCbProcessId() + "'" +
            ", financeRequestId='" + getFinanceRequestId() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", anchortTraderGst='" + getAnchortTraderGst() + "'" +
            ", financeAmount='" + getFinanceAmount() + "'" +
            ", processDateTime='" + getProcessDateTime() + "'" +
            ", creProcessStatus='" + getCreProcessStatus() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", cumilativetradescore=" + getCumilativetradescore() +
            ", timestamp='" + getTimestamp() + "'" +
            ", totalAmountRequested=" + getTotalAmountRequested() +
            ", totalInvoiceAmount=" + getTotalInvoiceAmount() +
            "}";
    }
}
