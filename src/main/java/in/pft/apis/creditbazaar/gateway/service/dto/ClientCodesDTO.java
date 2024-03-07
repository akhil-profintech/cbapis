package in.pft.apis.creditbazaar.gateway.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link in.pft.apis.creditbazaar.gateway.domain.ClientCodes} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientCodesDTO implements Serializable {

    private Long id;

    private Long clientCode;

    private String clientCharsCode;

    private String clientName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientCode() {
        return clientCode;
    }

    public void setClientCode(Long clientCode) {
        this.clientCode = clientCode;
    }

    public String getClientCharsCode() {
        return clientCharsCode;
    }

    public void setClientCharsCode(String clientCharsCode) {
        this.clientCharsCode = clientCharsCode;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientCodesDTO)) {
            return false;
        }

        ClientCodesDTO clientCodesDTO = (ClientCodesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientCodesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientCodesDTO{" +
            "id=" + getId() +
            ", clientCode=" + getClientCode() +
            ", clientCharsCode='" + getClientCharsCode() + "'" +
            ", clientName='" + getClientName() + "'" +
            "}";
    }
}
