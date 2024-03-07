package in.pft.apis.creditbazaar.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A UpdateVA.
 */
@Table("update_va")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UpdateVA implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @NotNull(message = "must not be null")
    @Column("update_vir_acc_id")
    private Long updateVirAccId;

    @Column("fin_req_id")
    private String finReqId;

    @Column("sub_grp_id")
    private String subGrpId;

    @Column("msg_id")
    private String msgId;

    @Column("cnv_id")
    private String cnvId;

    @Column("ext_ref_id")
    private String extRefId;

    @Column("biz_obj_id")
    private String bizObjId;

    @Column("app_id")
    private String appId;

    @Column("timestamp")
    private String timestamp;

    @Column("va_num")
    private String vaNum;

    @Column("fin_par")
    private String finPar;

    @Column("client_code")
    private String clientCode;

    @NotNull(message = "must not be null")
    @Column("request_date_time")
    private String requestDateTime;

    @Column("reslt")
    private String reslt;

    @Column("status")
    private String status;

    @Column("status_desc")
    private String statusDesc;

    @Column("error_code")
    private String errorCode;

    @NotNull(message = "must not be null")
    @Column("response_date_time")
    private String responseDateTime;

    @NotNull(message = "must not be null")
    @Column("lastupdated_date_time")
    private String lastupdatedDateTime;

    @NotNull(message = "must not be null")
    @Column("last_updated_by")
    private String lastUpdatedBy;

    @Transient
    @JsonIgnoreProperties(value = { "beneValidations", "instaAlerts", "fundsTransfers", "updateVAS", "vANumbers" }, allowSetters = true)
    private TradeEntity tradeEntity;

    @Column("trade_entity_id")
    private Long tradeEntityId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UpdateVA id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUpdateVirAccId() {
        return this.updateVirAccId;
    }

    public UpdateVA updateVirAccId(Long updateVirAccId) {
        this.setUpdateVirAccId(updateVirAccId);
        return this;
    }

    public void setUpdateVirAccId(Long updateVirAccId) {
        this.updateVirAccId = updateVirAccId;
    }

    public String getFinReqId() {
        return this.finReqId;
    }

    public UpdateVA finReqId(String finReqId) {
        this.setFinReqId(finReqId);
        return this;
    }

    public void setFinReqId(String finReqId) {
        this.finReqId = finReqId;
    }

    public String getSubGrpId() {
        return this.subGrpId;
    }

    public UpdateVA subGrpId(String subGrpId) {
        this.setSubGrpId(subGrpId);
        return this;
    }

    public void setSubGrpId(String subGrpId) {
        this.subGrpId = subGrpId;
    }

    public String getMsgId() {
        return this.msgId;
    }

    public UpdateVA msgId(String msgId) {
        this.setMsgId(msgId);
        return this;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCnvId() {
        return this.cnvId;
    }

    public UpdateVA cnvId(String cnvId) {
        this.setCnvId(cnvId);
        return this;
    }

    public void setCnvId(String cnvId) {
        this.cnvId = cnvId;
    }

    public String getExtRefId() {
        return this.extRefId;
    }

    public UpdateVA extRefId(String extRefId) {
        this.setExtRefId(extRefId);
        return this;
    }

    public void setExtRefId(String extRefId) {
        this.extRefId = extRefId;
    }

    public String getBizObjId() {
        return this.bizObjId;
    }

    public UpdateVA bizObjId(String bizObjId) {
        this.setBizObjId(bizObjId);
        return this;
    }

    public void setBizObjId(String bizObjId) {
        this.bizObjId = bizObjId;
    }

    public String getAppId() {
        return this.appId;
    }

    public UpdateVA appId(String appId) {
        this.setAppId(appId);
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public UpdateVA timestamp(String timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getVaNum() {
        return this.vaNum;
    }

    public UpdateVA vaNum(String vaNum) {
        this.setVaNum(vaNum);
        return this;
    }

    public void setVaNum(String vaNum) {
        this.vaNum = vaNum;
    }

    public String getFinPar() {
        return this.finPar;
    }

    public UpdateVA finPar(String finPar) {
        this.setFinPar(finPar);
        return this;
    }

    public void setFinPar(String finPar) {
        this.finPar = finPar;
    }

    public String getClientCode() {
        return this.clientCode;
    }

    public UpdateVA clientCode(String clientCode) {
        this.setClientCode(clientCode);
        return this;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getRequestDateTime() {
        return this.requestDateTime;
    }

    public UpdateVA requestDateTime(String requestDateTime) {
        this.setRequestDateTime(requestDateTime);
        return this;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getReslt() {
        return this.reslt;
    }

    public UpdateVA reslt(String reslt) {
        this.setReslt(reslt);
        return this;
    }

    public void setReslt(String reslt) {
        this.reslt = reslt;
    }

    public String getStatus() {
        return this.status;
    }

    public UpdateVA status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return this.statusDesc;
    }

    public UpdateVA statusDesc(String statusDesc) {
        this.setStatusDesc(statusDesc);
        return this;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public UpdateVA errorCode(String errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseDateTime() {
        return this.responseDateTime;
    }

    public UpdateVA responseDateTime(String responseDateTime) {
        this.setResponseDateTime(responseDateTime);
        return this;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getLastupdatedDateTime() {
        return this.lastupdatedDateTime;
    }

    public UpdateVA lastupdatedDateTime(String lastupdatedDateTime) {
        this.setLastupdatedDateTime(lastupdatedDateTime);
        return this;
    }

    public void setLastupdatedDateTime(String lastupdatedDateTime) {
        this.lastupdatedDateTime = lastupdatedDateTime;
    }

    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public UpdateVA lastUpdatedBy(String lastUpdatedBy) {
        this.setLastUpdatedBy(lastUpdatedBy);
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public TradeEntity getTradeEntity() {
        return this.tradeEntity;
    }

    public void setTradeEntity(TradeEntity tradeEntity) {
        this.tradeEntity = tradeEntity;
        this.tradeEntityId = tradeEntity != null ? tradeEntity.getId() : null;
    }

    public UpdateVA tradeEntity(TradeEntity tradeEntity) {
        this.setTradeEntity(tradeEntity);
        return this;
    }

    public Long getTradeEntityId() {
        return this.tradeEntityId;
    }

    public void setTradeEntityId(Long tradeEntity) {
        this.tradeEntityId = tradeEntity;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UpdateVA)) {
            return false;
        }
        return getId() != null && getId().equals(((UpdateVA) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UpdateVA{" +
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
            "}";
    }
}
