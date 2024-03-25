import dayjs from 'dayjs/esm';
import { ICreditAccountDetails } from 'app/entities/credit-account-details/credit-account-details.model';
import { IFundsTransferTransactionDetails } from 'app/entities/funds-transfer-transaction-details/funds-transfer-transaction-details.model';
import { IEscrowTransactionDetails } from 'app/entities/escrow-transaction-details/escrow-transaction-details.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';

export interface IDisbursement {
  id: number;
  dbmtId?: number | null;
  disbursementUlidId?: string | null;
  disbursementRefNo?: string | null;
  acceptedOfferUlidId?: string | null;
  placedOfferUlidId?: string | null;
  reqOffUlidId?: string | null;
  offerAcceptedDate?: dayjs.Dayjs | null;
  dbmtRequestId?: string | null;
  dbmtReqAmount?: number | null;
  currency?: string | null;
  dbmtRequestDate?: dayjs.Dayjs | null;
  dbmtStatus?: string | null;
  offerStatus?: string | null;
  docId?: number | null;
  dbmtDateTime?: string | null;
  dbmtAmount?: number | null;
  financeRequestId?: number | null;
  amountToBeDisbursed?: string | null;
  destinationAccountName?: string | null;
  destinationAccountNumber?: string | null;
  status?: string | null;
  actionByDate?: string | null;
  creditAccountDetails?: Pick<ICreditAccountDetails, 'id'>[] | null;
  fundsTransferTransactionDetails?: Pick<IFundsTransferTransactionDetails, 'id'>[] | null;
  escrowTransactionDetails?: Pick<IEscrowTransactionDetails, 'id'>[] | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
  financepartner?: Pick<IFinancePartner, 'id' | 'fpId'> | null;
}

export type NewDisbursement = Omit<IDisbursement, 'id'> & { id: null };
