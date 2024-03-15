package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.CBCREProcess} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CBCREProcessDTO implements Serializable {

    private Long id;

    private Long cbProcessId;

    private String cbProcessUlidId;

    private String cbProcessRefNo;

    private String anchorTraderId;

    private String anchorTraderGst;

    private String financeAmount;

    private String processDateTime;

    private String creProcessStatus;

    private String responseDateTime;

    private String creRequestId;

    private Float cumilativeTradeScore;

    private String timestamp;

    private Long totalAmountRequested;

    private Long totalInvoiceAmount;

    private String status;

    private FinanceRequestDTO financeRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCbProcessId() {
        return cbProcessId;
    }

    public void setCbProcessId(Long cbProcessId) {
        this.cbProcessId = cbProcessId;
    }

    public String getCbProcessUlidId() {
        return cbProcessUlidId;
    }

    public void setCbProcessUlidId(String cbProcessUlidId) {
        this.cbProcessUlidId = cbProcessUlidId;
    }

    public String getCbProcessRefNo() {
        return cbProcessRefNo;
    }

    public void setCbProcessRefNo(String cbProcessRefNo) {
        this.cbProcessRefNo = cbProcessRefNo;
    }

    public String getAnchorTraderId() {
        return anchorTraderId;
    }

    public void setAnchorTraderId(String anchorTraderId) {
        this.anchorTraderId = anchorTraderId;
    }

    public String getAnchorTraderGst() {
        return anchorTraderGst;
    }

    public void setAnchorTraderGst(String anchorTraderGst) {
        this.anchorTraderGst = anchorTraderGst;
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

    public Float getCumilativeTradeScore() {
        return cumilativeTradeScore;
    }

    public void setCumilativeTradeScore(Float cumilativeTradeScore) {
        this.cumilativeTradeScore = cumilativeTradeScore;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FinanceRequestDTO getFinanceRequest() {
        return financeRequest;
    }

    public void setFinanceRequest(FinanceRequestDTO financeRequest) {
        this.financeRequest = financeRequest;
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
            ", cbProcessId=" + getCbProcessId() +
            ", cbProcessUlidId='" + getCbProcessUlidId() + "'" +
            ", cbProcessRefNo='" + getCbProcessRefNo() + "'" +
            ", anchorTraderId='" + getAnchorTraderId() + "'" +
            ", anchorTraderGst='" + getAnchorTraderGst() + "'" +
            ", financeAmount='" + getFinanceAmount() + "'" +
            ", processDateTime='" + getProcessDateTime() + "'" +
            ", creProcessStatus='" + getCreProcessStatus() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", creRequestId='" + getCreRequestId() + "'" +
            ", cumilativeTradeScore=" + getCumilativeTradeScore() +
            ", timestamp='" + getTimestamp() + "'" +
            ", totalAmountRequested=" + getTotalAmountRequested() +
            ", totalInvoiceAmount=" + getTotalInvoiceAmount() +
            ", status='" + getStatus() + "'" +
            ", financeRequest=" + getFinanceRequest() +
            "}";
    }
}
