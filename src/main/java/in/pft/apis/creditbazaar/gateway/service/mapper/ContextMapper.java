package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.Context;
import in.pft.apis.creditbazaar.gateway.service.dto.ContextDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Context} and its DTO {@link ContextDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContextMapper extends EntityMapper<ContextDTO, Context> {}
