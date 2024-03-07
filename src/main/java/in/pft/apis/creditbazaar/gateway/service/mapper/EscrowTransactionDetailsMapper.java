package in.pft.apis.creditbazaar.gateway.service.mapper;

import in.pft.apis.creditbazaar.gateway.domain.Disbursement;
import in.pft.apis.creditbazaar.gateway.domain.EscrowTransactionDetails;
import in.pft.apis.creditbazaar.gateway.domain.Repayment;
import in.pft.apis.creditbazaar.gateway.service.dto.DisbursementDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.EscrowTransactionDetailsDTO;
import in.pft.apis.creditbazaar.gateway.service.dto.RepaymentDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link EscrowTransactionDetails} and its DTO {@link EscrowTransactionDetailsDTO}.
 */
@Mapper(componentModel = "spring")
public interface EscrowTransactionDetailsMapper extends EntityMapper<EscrowTransactionDetailsDTO, EscrowTransactionDetails> {
    @Mapping(target = "disbursement", source = "disbursement", qualifiedByName = "disbursementDbmtId")
    @Mapping(target = "repayment", source = "repayment", qualifiedByName = "repaymentRepaymentId")
    EscrowTransactionDetailsDTO toDto(EscrowTransactionDetails s);

    @Named("disbursementDbmtId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "dbmtId", source = "dbmtId")
    @Mapping(target = "disbursementUlidId", source = "disbursementUlidId")
    @Mapping(target = "disbursementRefNo", source = "disbursementRefNo")
    @Mapping(target = "acceptedOfferUlidId", source = "acceptedOfferUlidId")
    @Mapping(target = "placedOfferUlidId", source = "placedOfferUlidId")
    @Mapping(target = "reqOffUlidId", source = "reqOffUlidId")
    @Mapping(target = "offerAcceptedDate", source = "offerAcceptedDate")
    @Mapping(target = "dbmtRequestId", source = "dbmtRequestId")
    @Mapping(target = "dbmtReqAmount", source = "dbmtReqAmount")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "dbmtRequestDate", source = "dbmtRequestDate")
    @Mapping(target = "dbmtStatus", source = "dbmtStatus")
    @Mapping(target = "offerStatus", source = "offerStatus")
    @Mapping(target = "docId", source = "docId")
    @Mapping(target = "dbmtDateTime", source = "dbmtDateTime")
    @Mapping(target = "dbmtAmount", source = "dbmtAmount")
    @Mapping(target = "financeRequestId", source = "financeRequestId")
    @Mapping(target = "amountToBeDisbursed", source = "amountToBeDisbursed")
    @Mapping(target = "destinationAccountName", source = "destinationAccountName")
    @Mapping(target = "destinationAccountNumber", source = "destinationAccountNumber")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "actionByDate", source = "actionByDate")
    @Mapping(target = "financerequest", source = "financerequest")
    @Mapping(target = "financepartner", source = "financepartner")
    DisbursementDTO toDtoDisbursementDbmtId(Disbursement disbursement);

    @Named("repaymentRepaymentId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "repaymentId", source = "repaymentId")
    @Mapping(target = "repaymentUlidId", source = "repaymentUlidId")
    @Mapping(target = "repaymentRefNo", source = "repaymentRefNo")
    @Mapping(target = "acceptedOfferUlidId", source = "acceptedOfferUlidId")
    @Mapping(target = "placedOfferUlidId", source = "placedOfferUlidId")
    @Mapping(target = "reqOffUlidId", source = "reqOffUlidId")
    @Mapping(target = "offerAcceptedDate", source = "offerAcceptedDate")
    @Mapping(target = "dbmtRequestId", source = "dbmtRequestId")
    @Mapping(target = "dbmtStatus", source = "dbmtStatus")
    @Mapping(target = "dbmtDateTime", source = "dbmtDateTime")
    @Mapping(target = "dbmtId", source = "dbmtId")
    @Mapping(target = "dbmtAmount", source = "dbmtAmount")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "repaymentStatus", source = "repaymentStatus")
    @Mapping(target = "repaymentDate", source = "repaymentDate")
    @Mapping(target = "repaymentAmount", source = "repaymentAmount")
    @Mapping(target = "financeRequestId", source = "financeRequestId")
    @Mapping(target = "repaymentDueDate", source = "repaymentDueDate")
    @Mapping(target = "totalRepaymentAmount", source = "totalRepaymentAmount")
    @Mapping(target = "amountRepayed", source = "amountRepayed")
    @Mapping(target = "amountToBePaid", source = "amountToBePaid")
    @Mapping(target = "sourceAccountName", source = "sourceAccountName")
    @Mapping(target = "sourceAccountNumber", source = "sourceAccountNumber")
    @Mapping(target = "ifscCode", source = "ifscCode")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "referenceNumber", source = "referenceNumber")
    @Mapping(target = "financerequest", source = "financerequest")
    RepaymentDTO toDtoRepaymentRepaymentId(Repayment repayment);
}
