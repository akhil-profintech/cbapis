package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.AnchorTrader;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FinanceRequest} and its DTO {@link FinanceRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface FinanceRequestMapper extends EntityMapper<FinanceRequestDTO, FinanceRequest> {
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    FinanceRequestDTO toDto(FinanceRequest s);

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atId", source = "atId")
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);
}
