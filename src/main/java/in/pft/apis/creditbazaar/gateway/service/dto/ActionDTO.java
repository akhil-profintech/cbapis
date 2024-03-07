package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.Action} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ActionDTO implements Serializable {

    private Long id;

    private String actionId;

    private String actionCode;

    private String actionDescription;

    private String actionVal;

    private String cpCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionDescription() {
        return actionDescription;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getActionVal() {
        return actionVal;
    }

    public void setActionVal(String actionVal) {
        this.actionVal = actionVal;
    }

    public String getCpCode() {
        return cpCode;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ActionDTO)) {
            return false;
        }

        ActionDTO actionDTO = (ActionDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, actionDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ActionDTO{" +
            "id=" + getId() +
            ", actionId='" + getActionId() + "'" +
            ", actionCode='" + getActionCode() + "'" +
            ", actionDescription='" + getActionDescription() + "'" +
            ", actionVal='" + getActionVal() + "'" +
            ", cpCode='" + getCpCode() + "'" +
            "}";
    }
}
