package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CreditBazaarIntegrator;
import in.pft.apis.creditbazaar.service.dto.CreditBazaarIntegratorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CreditBazaarIntegrator} and its DTO {@link CreditBazaarIntegratorDTO}.
 */
@Mapper(componentModel = "spring")
public interface CreditBazaarIntegratorMapper extends EntityMapper<CreditBazaarIntegratorDTO, CreditBazaarIntegrator> {}
