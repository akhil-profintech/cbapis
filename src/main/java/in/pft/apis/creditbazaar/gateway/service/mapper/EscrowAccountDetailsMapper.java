package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.EscrowAccountDetails;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowAccountDetailsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EscrowAccountDetails} and its DTO {@link EscrowAccountDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface EscrowAccountDetailsMapper extends EntityMapper<EscrowAccountDetailsDTO, EscrowAccountDetails> {}
