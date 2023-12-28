package in.pft.apis.creditbazaar.domain;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ClientCodes.
 */
@Table("client_codes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientCodes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("client_code")
    private Long clientCode;

    @Column("client_chars_code")
    private String clientCharsCode;

    @Column("client_name")
    private String clientName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClientCodes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientCode() {
        return this.clientCode;
    }

    public ClientCodes clientCode(Long clientCode) {
        this.setClientCode(clientCode);
        return this;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientCharsCode() {
        return this.clientCharsCode;
    }

    public ClientCodes clientCharsCode(String clientCharsCode) {
        this.setClientCharsCode(clientCharsCode);
        return this;
    }

    public void setClientCharsCode(String clientCharsCode) {
        this.clientCharsCode = clientCharsCode;
    }

    public String getClientName() {
        return this.clientName;
    }

    public ClientCodes clientName(String clientName) {
        this.setClientName(clientName);
        return this;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientCodes)) {
            return false;
        }
        return getId() != null && getId().equals(((ClientCodes) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientCodes{" +
            "id=" + getId() +
            ", clientCode=" + getClientCode() +
            ", clientCharsCode='" + getClientCharsCode() + "'" +
            ", clientName='" + getClientName() + "'" +
            "}";
    }
}
