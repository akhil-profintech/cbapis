package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CBCREProcess;
import in.pft.apis.creditbazaar.service.dto.CBCREProcessDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CBCREProcess} and its DTO {@link CBCREProcessDTO}.
 */
@Mapper(componentModel = "spring")
public interface CBCREProcessMapper extends EntityMapper<CBCREProcessDTO, CBCREProcess> {}
