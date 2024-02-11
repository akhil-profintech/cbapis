package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Organization;
import in.pft.apis.creditbazaar.service.dto.OrganizationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Organization} and its DTO {@link OrganizationDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {}
