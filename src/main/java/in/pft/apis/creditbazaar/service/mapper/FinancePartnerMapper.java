package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.FinancePartner;
import in.pft.apis.creditbazaar.service.dto.FinancePartnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinancePartner} and its DTO {@link FinancePartnerDTO}.
 */
@Mapper(componentModel = "spring")
public interface FinancePartnerMapper extends EntityMapper<FinancePartnerDTO, FinancePartner> {}
