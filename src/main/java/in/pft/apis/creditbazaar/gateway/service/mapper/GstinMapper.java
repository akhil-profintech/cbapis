package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.Gstin;
import in.pft.apis.creditbazaar.gateway.domain.Organization;
import in.pft.apis.creditbazaar.gateway.service.dto.GstinDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.OrganizationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Gstin} and its DTO {@link GstinDTO}.
 */
@Mapper(componentModel = "spring")
public interface GstinMapper extends EntityMapper<GstinDTO, Gstin> {
    @Mapping(target = "organization", source = "organization", qualifiedByName = "organizationOrgId")
    GstinDTO toDto(Gstin s);

    @Named("organizationOrgId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "orgId", source = "orgId")
    @Mapping(target = "orgUlidId", source = "orgUlidId")
    @Mapping(target = "orgName", source = "orgName")
    @Mapping(target = "orgAddress", source = "orgAddress")
    @Mapping(target = "industryType", source = "industryType")
    @Mapping(target = "tenantId", source = "tenantId")
    OrganizationDTO toDtoOrganizationOrgId(Organization organization);
}
