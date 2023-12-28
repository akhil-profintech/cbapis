package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Repayment} and its DTO {@link RepaymentDTO}.
 */
@Mapper(componentModel = "spring")
public interface RepaymentMapper extends EntityMapper<RepaymentDTO, Repayment> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    RepaymentDTO toDto(Repayment s);

    @Named("financeRequestRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    FinanceRequestDTO toDtoFinanceRequestRequestId(FinanceRequest financeRequest);
}
