import dayjs from 'dayjs/esm';
import { ICreditAccountDetails } from 'app/entities/credit-account-details/credit-account-details.model';
import { IEscrowAccountDetails } from 'app/entities/escrow-account-details/escrow-account-details.model';
import { IDocDetails } from 'app/entities/doc-details/doc-details.model';
import { IFTTransactionDetails } from 'app/entities/ft-transaction-details/ft-transaction-details.model';
import { ICollectionTransactionDetails } from 'app/entities/collection-transaction-details/collection-transaction-details.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';
import { IFinancePartner } from 'app/entities/finance-partner/finance-partner.model';

export interface IDisbursement {
  id: number;
  dbmtId?: string | null;
  disbursementRefNo?: string | null;
  acceptedOfferId?: number | null;
  offerId?: number | null;
  offerAcceptedDate?: dayjs.Dayjs | null;
  dbmtRequestId?: string | null;
  dbmtReqAmount?: number | null;
  currency?: string | null;
  dbmtRequestDate?: dayjs.Dayjs | null;
  dbmtstatus?: string | null;
  offerStatus?: string | null;
  ftTrnxDetailsId?: string | null;
  collectionTrnxDetailsId?: string | null;
  docId?: number | null;
  dbmtDateTime?: string | null;
  dbmtAmount?: number | null;
  financeRequestId?: string | null;
  amountToBeDisbursed?: string | null;
  destinationAccountName?: string | null;
  destinationAccountNumber?: string | null;
  ifscCode?: string | null;
  status?: string | null;
  actionByDate?: string | null;
  creditAccountDetails?: Pick<ICreditAccountDetails, 'id'>[] | null;
  escrowAccountDetails?: Pick<IEscrowAccountDetails, 'id'>[] | null;
  docDetails?: Pick<IDocDetails, 'id'>[] | null;
  fTTransactionDetails?: Pick<IFTTransactionDetails, 'id'>[] | null;
  collectionTransactionDetails?: Pick<ICollectionTransactionDetails, 'id'>[] | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'requestId'> | null;
  financepartner?: Pick<IFinancePartner, 'id' | 'fpId'> | null;
}

export type NewDisbursement = Omit<IDisbursement, 'id'> & { id: null };
