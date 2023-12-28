package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.FinanceRequest;
import in.pft.apis.creditbazaar.domain.ProspectRequest;
import in.pft.apis.creditbazaar.service.dto.FinanceRequestDTO;
import in.pft.apis.creditbazaar.service.dto.ProspectRequestDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProspectRequest} and its DTO {@link ProspectRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProspectRequestMapper extends EntityMapper<ProspectRequestDTO, ProspectRequest> {
    @Mapping(target = "financerequest", source = "financerequest", qualifiedByName = "financeRequestRequestId")
    ProspectRequestDTO toDto(ProspectRequest s);

    @Named("financeRequestRequestId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "requestId", source = "requestId")
    FinanceRequestDTO toDtoFinanceRequestRequestId(FinanceRequest financeRequest);
}
