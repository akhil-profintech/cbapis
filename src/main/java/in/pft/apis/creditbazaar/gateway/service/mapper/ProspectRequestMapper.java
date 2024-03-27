package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.ProspectRequest;
import in.pft.apis.creditbazaar.gateway.service.dto.ProspectRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProspectRequest} and its DTO {@link ProspectRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProspectRequestMapper extends EntityMapper<ProspectRequestDTO, ProspectRequest> {}
