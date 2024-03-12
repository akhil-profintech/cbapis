package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.TenantDetails;
import in.pft.apis.creditbazaar.gateway.service.dto.TenantDetailsDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link TenantDetails} and its DTO {@link TenantDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface TenantDetailsMapper extends EntityMapper<TenantDetailsDTO, TenantDetails> {}
