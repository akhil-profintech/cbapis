package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.Action;
import in.pft.apis.creditbazaar.gateway.service.dto.ActionDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link Action} and its DTO {@link ActionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {}
