package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.Organization;
import in.pft.apis.creditbazaar.domain.UserDtls;
import in.pft.apis.creditbazaar.service.dto.OrganizationDTO;
import in.pft.apis.creditbazaar.service.dto.UserDtlsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserDtls} and its DTO {@link UserDtlsDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserDtlsMapper extends EntityMapper<UserDtlsDTO, UserDtls> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "organizationOrgId")
    UserDtlsDTO toDto(UserDtls s);

    @Named("organizationOrgId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "orgId", source = "orgId")
    OrganizationDTO toDtoOrganizationOrgId(Organization organization);
}
