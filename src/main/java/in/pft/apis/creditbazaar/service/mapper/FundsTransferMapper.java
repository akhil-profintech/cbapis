package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.FundsTransfer;
import in.pft.apis.creditbazaar.domain.TradeEntity;
import in.pft.apis.creditbazaar.service.dto.FundsTransferDTO;
import in.pft.apis.creditbazaar.service.dto.TradeEntityDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FundsTransfer} and its DTO {@link FundsTransferDTO}.
 */
@Mapper(componentModel = "spring")
public interface FundsTransferMapper extends EntityMapper<FundsTransferDTO, FundsTransfer> {
    @Mapping(target = "tradeEntity", source = "tradeEntity", qualifiedByName = "tradeEntityEntityId")
    FundsTransferDTO toDto(FundsTransfer s);

    @Named("tradeEntityEntityId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "entityId", source = "entityId")
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
