package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Action;
import in.pft.apis.creditbazaar.service.dto.ActionDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Action} and its DTO {@link ActionDTO}.
 */
@Mapper(componentModel = "spring")
public interface ActionMapper extends EntityMapper<ActionDTO, Action> {}
