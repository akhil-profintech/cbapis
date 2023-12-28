package in.pft.apis.creditbazaar.service.mapper;

import in.pft.apis.creditbazaar.domain.CollectionTransactionDetails;
import in.pft.apis.creditbazaar.domain.Disbursement;
import in.pft.apis.creditbazaar.domain.Repayment;
import in.pft.apis.creditbazaar.service.dto.CollectionTransactionDetailsDTO;
import in.pft.apis.creditbazaar.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CollectionTransactionDetails} and its DTO {@link CollectionTransactionDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface CollectionTransactionDetailsMapper extends EntityMapper<CollectionTransactionDetailsDTO, CollectionTransactionDetails> {
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementCollectionTrnxDetailsId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentCollectionTrnxDetailsId")
    CollectionTransactionDetailsDTO toDto(CollectionTransactionDetails s);

    @Named("disbursementCollectionTrnxDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "collectionTrnxDetailsId", source = "collectionTrnxDetailsId")
    DisbursementDTO toDtoDisbursementCollectionTrnxDetailsId(Disbursement disbursement);

    @Named("repaymentCollectionTrnxDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "collectionTrnxDetailsId", source = "collectionTrnxDetailsId")
    RepaymentDTO toDtoRepaymentCollectionTrnxDetailsId(Repayment repayment);
}
