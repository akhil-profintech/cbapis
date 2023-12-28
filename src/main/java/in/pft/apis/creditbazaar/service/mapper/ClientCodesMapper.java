package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.ClientCodes;
import in.pft.apis.creditbazaar.service.dto.ClientCodesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientCodes} and its DTO {@link ClientCodesDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientCodesMapper extends EntityMapper<ClientCodesDTO, ClientCodes> {}
