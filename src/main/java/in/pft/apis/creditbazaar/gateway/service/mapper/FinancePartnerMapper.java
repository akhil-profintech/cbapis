package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.FinancePartner;
import in.pft.apis.creditbazaar.gateway.service.dto.FinancePartnerDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity {@link FinancePartner} and its DTO {@link FinancePartnerDTO}.
 */
@Mapper(componentModel = "spring")
public interface FinancePartnerMapper extends EntityMapper<FinancePartnerDTO, FinancePartner> {}
