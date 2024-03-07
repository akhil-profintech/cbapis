package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.FinanceRequestMapping;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestMappingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinanceRequestMapping} and its DTO {@link FinanceRequestMappingDTO}.
 */
@Mapper(componentModel = "spring")
public interface FinanceRequestMappingMapper extends EntityMapper<FinanceRequestMappingDTO, FinanceRequestMapping> {}
