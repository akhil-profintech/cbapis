package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.AnchorTrader;
import in.pft.apis.creditbazaar.gateway.domain.FinanceRequest;
import in.pft.apis.creditbazaar.gateway.domain.Trade;
import in.pft.apis.creditbazaar.gateway.domain.TradePartner;
import in.pft.apis.creditbazaar.gateway.service.dto.AnchorTraderDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.TradePartnerDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Trade} and its DTO {@link TradeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TradeMapper extends EntityMapper<TradeDTO, Trade> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestFinanceRequestId")
    @Mapping(target = "anchortrader", source = "anchortrader", qualifiedByName = "anchorTraderAtId")
    @Mapping(target = "tradepartner", source = "tradepartner", qualifiedByName = "tradePartnerTpId")
    TradeDTO toDto(Trade s);

    @Named("financeRequestFinanceRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "financeRequestId", source = "financeRequestId")
    FinanceRequestDTO toDtoFinanceRequestFinanceRequestId(FinanceRequest financeRequest);

    @Named("anchorTraderAtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "atId", source = "atId")
    AnchorTraderDTO toDtoAnchorTraderAtId(AnchorTrader anchorTrader);

    @Named("tradePartnerTpId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "tpId", source = "tpId")
    TradePartnerDTO toDtoTradePartnerTpId(TradePartner tradePartner);
}
