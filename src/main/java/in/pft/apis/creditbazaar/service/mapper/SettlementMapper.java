package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.domain.Settlement;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.dto.SettlementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Settlement} and its DTO {@link SettlementDTO}.
 */
@Mapper(componentModel = "spring")
public interface SettlementMapper extends EntityMapper<SettlementDTO, Settlement> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    SettlementDTO toDto(Settlement s);

    @Named("financeRequestRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    FinanceRequestDTO toDtoFinanceRequestRequestId(FinanceRequest financeRequest);
}
