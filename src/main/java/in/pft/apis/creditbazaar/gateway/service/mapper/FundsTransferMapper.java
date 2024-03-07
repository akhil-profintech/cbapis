package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.FundsTransfer;
import in.pft.apis.creditbazaar.gateway.domain.TradeEntity;
import in.pft.apis.creditbazaar.gateway.service.dto.FundsTransferDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.TradeEntityDTO;
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
    @Mapping(target = "entityUlidId", source = "entityUlidId")
    @Mapping(target = "entityName", source = "entityName")
    @Mapping(target = "entityDesc", source = "entityDesc")
    @Mapping(target = "entityGST", source = "entityGST")
    TradeEntityDTO toDtoTradeEntityEntityId(TradeEntity tradeEntity);
}
