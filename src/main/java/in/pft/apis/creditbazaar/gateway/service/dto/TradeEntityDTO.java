package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.TradeEntity} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradeEntityDTO implements Serializable {

    private Long id;

    private Long entityId;

    private String entityUlidId;

    private String entityName;

    private String entityDesc;

    private String entityGST;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityUlidId() {
        return entityUlidId;
    }

    public void setEntityUlidId(String entityUlidId) {
        this.entityUlidId = entityUlidId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityDesc() {
        return entityDesc;
    }

    public void setEntityDesc(String entityDesc) {
        this.entityDesc = entityDesc;
    }

    public String getEntityGST() {
        return entityGST;
    }

    public void setEntityGST(String entityGST) {
        this.entityGST = entityGST;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeEntityDTO)) {
            return false;
        }

        TradeEntityDTO tradeEntityDTO = (TradeEntityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tradeEntityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeEntityDTO{" +
            "id=" + getId() +
            ", entityId=" + getEntityId() +
            ", entityUlidId='" + getEntityUlidId() + "'" +
            ", entityName='" + getEntityName() + "'" +
            ", entityDesc='" + getEntityDesc() + "'" +
            ", entityGST='" + getEntityGST() + "'" +
            "}";
    }
}
