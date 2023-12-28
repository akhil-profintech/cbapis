package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Action;
import in.pft.apis.creditbazaar.domain.Context;
import in.pft.apis.creditbazaar.service.dto.ActionDTO;
import in.pft.apis.creditbazaar.service.dto.ContextDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Context} and its DTO {@link ContextDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContextMapper extends EntityMapper<ContextDTO, Context> {
    @Mapping(target = "action", source = "action", qualifiedByName = "actionActionVal")
    ContextDTO toDto(Context s);

    @Named("actionActionVal")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "actionVal", source = "actionVal")
    ActionDTO toDtoActionActionVal(Action action);
}
