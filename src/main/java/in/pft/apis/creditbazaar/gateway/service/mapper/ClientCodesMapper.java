package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.ClientCodes;
import in.pft.apis.creditbazaar.gateway.service.dto.ClientCodesDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link ClientCodes} and its DTO {@link ClientCodesDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientCodesMapper extends EntityMapper<ClientCodesDTO, ClientCodes> {}
