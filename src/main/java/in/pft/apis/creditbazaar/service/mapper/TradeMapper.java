package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.AnchorTrader;
import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.domain.Trade;
import in.pft.apis.creditbazaar.domain.TradePartner;
import in.pft.apis.creditbazaar.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.dto.TradeDTO;
import in.pft.apis.creditbazaar.service.dto.TradePartnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Trade} and its DTO {@link TradeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeMapper extends EntityMapper<TradeDTO, Trade> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    @Mapping(target = "tradepartner", source = "tradepartner", qualifiedByName = "tradePartnerTpId")
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    TradeDTO toDto(Trade s);

    @Named("financeRequestRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    FinanceRequestDTO toDtoFinanceRequestRequestId(FinanceRequest financeRequest);

    @Named("tradePartnerTpId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "tpId", source = "tpId")
    TradePartnerDTO toDtoTradePartnerTpId(TradePartner tradePartner);

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atId", source = "atId")
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);
}
