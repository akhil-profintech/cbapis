import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';

export interface ICreditAccountDetails {
  id: number;
  creditAccDetailsId?: number | null;
  tenantId?: number | null;
  customerId?: number | null;
  accName?: string | null;
  ifscCode?: string | null;
  accNumber?: number | null;
  disbursement?: Pick<IDisbursement, 'id' | 'dbmtRequestId'> | null;
  repayment?: Pick<IRepayment, 'id' | 'repaymentId'> | null;
}

export type NewCreditAccountDetails = Omit<ICreditAccountDetails, 'id'> & { id: null };
