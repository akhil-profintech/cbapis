package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.CreditBazaarIntegrator;
import in.pft.apis.creditbazaar.gateway.service.dto.CreditBazaarIntegratorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreditBazaarIntegrator} and its DTO {@link CreditBazaarIntegratorDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreditBazaarIntegratorMapper extends EntityMapper<CreditBazaarIntegratorDTO, CreditBazaarIntegrator> {}
