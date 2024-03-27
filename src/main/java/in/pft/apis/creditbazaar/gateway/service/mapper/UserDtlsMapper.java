package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.UserDtls;
import in.pft.apis.creditbazaar.gateway.service.dto.UserDtlsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserDtls} and its DTO {@link UserDtlsDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserDtlsMapper extends EntityMapper<UserDtlsDTO, UserDtls> {}
