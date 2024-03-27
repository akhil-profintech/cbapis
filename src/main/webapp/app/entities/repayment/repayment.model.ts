import dayjs from 'dayjs/esm';
import { ICreditAccountDetails } from 'app/entities/credit-account-details/credit-account-details.model';
import { IFundsTransferTransactionDetails } from 'app/entities/funds-transfer-transaction-details/funds-transfer-transaction-details.model';
import { IEscrowTransactionDetails } from 'app/entities/escrow-transaction-details/escrow-transaction-details.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface IRepayment {
  id: number;
  repaymentId?: number | null;
  repaymentUlidId?: string | null;
  repaymentRefNo?: string | null;
  acceptedOfferUlidId?: string | null;
  placedOfferUlidId?: string | null;
  reqOffUlidId?: string | null;
  offerAcceptedDate?: dayjs.Dayjs | null;
  dbmtRequestId?: string | null;
  dbmtStatus?: string | null;
  dbmtDateTime?: string | null;
  dbmtId?: number | null;
  dbmtAmount?: number | null;
  currency?: string | null;
  repaymentStatus?: string | null;
  repaymentDate?: dayjs.Dayjs | null;
  repaymentAmount?: number | null;
  financeRequestId?: number | null;
  repaymentDueDate?: dayjs.Dayjs | null;
  totalRepaymentAmount?: string | null;
  amountRepayed?: string | null;
  amountToBePaid?: string | null;
  sourceAccountName?: string | null;
  sourceAccountNumber?: string | null;
  ifscCode?: string | null;
  recordStatus?: string | null;
  referenceNumber?: string | null;
  creditAccountDetails?: Pick<ICreditAccountDetails, 'id'>[] | null;
  fundsTransferTransactionDetails?: Pick<IFundsTransferTransactionDetails, 'id'>[] | null;
  escrowTransactionDetails?: Pick<IEscrowTransactionDetails, 'id'>[] | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
}

export type NewRepayment = Omit<IRepayment, 'id'> & { id: null };
