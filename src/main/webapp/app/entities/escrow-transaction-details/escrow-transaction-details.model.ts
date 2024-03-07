import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';

export interface IEscrowTransactionDetails {
  id: number;
  escrowTrnxDetailsId?: number | null;
  escrowTrnxDetailsUlidId?: string | null;
  customerCode?: string | null;
  customerName?: string | null;
  productCode?: string | null;
  transactionType?: string | null;
  batchAmt?: string | null;
  batchAmtCcd?: string | null;
  creditDate?: string | null;
  vaNumber?: string | null;
  utrNo?: string | null;
  creditGenerationTime?: string | null;
  remitterName?: string | null;
  remitterAccountNumber?: string | null;
  ifscCode?: string | null;
  disbursement?: Pick<IDisbursement, 'id' | 'dbmtId'> | null;
  repayment?: Pick<IRepayment, 'id' | 'repaymentId'> | null;
}

export type NewEscrowTransactionDetails = Omit<IEscrowTransactionDetails, 'id'> & { id: null };
