package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.TenantDetails;
import in.pft.apis.creditbazaar.service.dto.TenantDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TenantDetails} and its DTO {@link TenantDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TenantDetailsMapper extends EntityMapper<TenantDetailsDTO, TenantDetails> {}
