import dayjs from 'dayjs/esm';
import { ICreditAccountDetails } from 'app/entities/credit-account-details/credit-account-details.model';
import { IEscrowAccountDetails } from 'app/entities/escrow-account-details/escrow-account-details.model';
import { IDocDetails } from 'app/entities/doc-details/doc-details.model';
import { IFTTransactionDetails } from 'app/entities/ft-transaction-details/ft-transaction-details.model';
import { ICollectionTransactionDetails } from 'app/entities/collection-transaction-details/collection-transaction-details.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface IRepayment {
  id: number;
  repaymentId?: string | null;
  repaymentRefNo?: string | null;
  acceptedOfferId?: number | null;
  offerId?: number | null;
  offerAcceptedDate?: dayjs.Dayjs | null;
  dbmtRequestId?: string | null;
  dbmtstatus?: string | null;
  dbmtDateTime?: string | null;
  dbmtId?: string | null;
  dbmtAmount?: number | null;
  currency?: string | null;
  repaymentStatus?: string | null;
  repaymentDate?: dayjs.Dayjs | null;
  repaymentAmount?: number | null;
  ftTrnxDetailsId?: string | null;
  collectionTrnxDetailsId?: string | null;
  docId?: number | null;
  financeRequestId?: string | null;
  repaymentDueDate?: dayjs.Dayjs | null;
  totalRepaymentAmount?: string | null;
  amountRepayed?: string | null;
  amountToBePaid?: string | null;
  sourceAccountName?: string | null;
  sourceAccountNumber?: string | null;
  ifscCode?: string | null;
  status?: string | null;
  referenceNumber?: string | null;
  creditAccountDetails?: Pick<ICreditAccountDetails, 'id'>[] | null;
  escrowAccountDetails?: Pick<IEscrowAccountDetails, 'id'>[] | null;
  docDetails?: Pick<IDocDetails, 'id'>[] | null;
  fTTransactionDetails?: Pick<IFTTransactionDetails, 'id'>[] | null;
  collectionTransactionDetails?: Pick<ICollectionTransactionDetails, 'id'>[] | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'requestId'> | null;
}

export type NewRepayment = Omit<IRepayment, 'id'> & { id: null };
