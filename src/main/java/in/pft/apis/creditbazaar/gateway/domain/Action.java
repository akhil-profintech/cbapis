package in.pft.apis.creditbazaar.gateway.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Action.
 */
@Table("action")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Action implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("action_id")
    private String actionId;

    @Column("action_code")
    private String actionCode;

    @Column("action_description")
    private String actionDescription;

    @Column("action_val")
    private String actionVal;

    @Column("cp_code")
    private String cpCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Action id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActionId() {
        return this.actionId;
    }

    public Action actionId(String actionId) {
        this.setActionId(actionId);
        return this;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionCode() {
        return this.actionCode;
    }

    public Action actionCode(String actionCode) {
        this.setActionCode(actionCode);
        return this;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionDescription() {
        return this.actionDescription;
    }

    public Action actionDescription(String actionDescription) {
        this.setActionDescription(actionDescription);
        return this;
    }

    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }

    public String getActionVal() {
        return this.actionVal;
    }

    public Action actionVal(String actionVal) {
        this.setActionVal(actionVal);
        return this;
    }

    public void setActionVal(String actionVal) {
        this.actionVal = actionVal;
    }

    public String getCpCode() {
        return this.cpCode;
    }

    public Action cpCode(String cpCode) {
        this.setCpCode(cpCode);
        return this;
    }

    public void setCpCode(String cpCode) {
        this.cpCode = cpCode;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Action)) {
            return false;
        }
        return getId() != null && getId().equals(((Action) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Action{" +
            "id=" + getId() +
            ", actionId='" + getActionId() + "'" +
            ", actionCode='" + getActionCode() + "'" +
            ", actionDescription='" + getActionDescription() + "'" +
            ", actionVal='" + getActionVal() + "'" +
            ", cpCode='" + getCpCode() + "'" +
            "}";
    }
}
