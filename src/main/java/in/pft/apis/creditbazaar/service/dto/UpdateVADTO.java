package in.pft.apis.creditbazaar.service.dto;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.domain.UpdateVA} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UpdateVADTO implements Serializable {

    private Long id;

    @NotNull(message = "must not be null")
    private Long updateVirAccId;

    private String finReqId;

    private String subGrpId;

    private String msgId;

    private String cnvId;

    private String extRefId;

    private String bizObjId;

    private String appId;

    private String timestamp;

    private String vaNum;

    private String finPar;

    private String clientCode;

    @NotNull(message = "must not be null")
    private String requestDateTime;

    private String reslt;

    private String status;

    private String statusDesc;

    private String errorCode;

    @NotNull(message = "must not be null")
    private String responseDateTime;

    @NotNull(message = "must not be null")
    private String lastupdatedDateTime;

    @NotNull(message = "must not be null")
    private String lastUpdatedBy;

    private TradeEntityDTO tradeEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpdateVirAccId() {
        return updateVirAccId;
    }

    public void setUpdateVirAccId(Long updateVirAccId) {
        this.updateVirAccId = updateVirAccId;
    }

    public String getFinReqId() {
        return finReqId;
    }

    public void setFinReqId(String finReqId) {
        this.finReqId = finReqId;
    }

    public String getSubGrpId() {
        return subGrpId;
    }

    public void setSubGrpId(String subGrpId) {
        this.subGrpId = subGrpId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCnvId() {
        return cnvId;
    }

    public void setCnvId(String cnvId) {
        this.cnvId = cnvId;
    }

    public String getExtRefId() {
        return extRefId;
    }

    public void setExtRefId(String extRefId) {
        this.extRefId = extRefId;
    }

    public String getBizObjId() {
        return bizObjId;
    }

    public void setBizObjId(String bizObjId) {
        this.bizObjId = bizObjId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVaNum() {
        return vaNum;
    }

    public void setVaNum(String vaNum) {
        this.vaNum = vaNum;
    }

    public String getFinPar() {
        return finPar;
    }

    public void setFinPar(String finPar) {
        this.finPar = finPar;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getReslt() {
        return reslt;
    }

    public void setReslt(String reslt) {
        this.reslt = reslt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getLastupdatedDateTime() {
        return lastupdatedDateTime;
    }

    public void setLastupdatedDateTime(String lastupdatedDateTime) {
        this.lastupdatedDateTime = lastupdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public TradeEntityDTO getTradeEntity() {
        return tradeEntity;
    }

    public void setTradeEntity(TradeEntityDTO tradeEntity) {
        this.tradeEntity = tradeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateVADTO)) {
            return false;
        }

        UpdateVADTO updateVADTO = (UpdateVADTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, updateVADTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UpdateVADTO{" +
            "id=" + getId() +
            ", updateVirAccId=" + getUpdateVirAccId() +
            ", finReqId='" + getFinReqId() + "'" +
            ", subGrpId='" + getSubGrpId() + "'" +
            ", msgId='" + getMsgId() + "'" +
            ", cnvId='" + getCnvId() + "'" +
            ", extRefId='" + getExtRefId() + "'" +
            ", bizObjId='" + getBizObjId() + "'" +
            ", appId='" + getAppId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", vaNum='" + getVaNum() + "'" +
            ", finPar='" + getFinPar() + "'" +
            ", clientCode='" + getClientCode() + "'" +
            ", requestDateTime='" + getRequestDateTime() + "'" +
            ", reslt='" + getReslt() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusDesc='" + getStatusDesc() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", lastupdatedDateTime='" + getLastupdatedDateTime() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", tradeEntity=" + getTradeEntity() +
            "}";
    }
}
