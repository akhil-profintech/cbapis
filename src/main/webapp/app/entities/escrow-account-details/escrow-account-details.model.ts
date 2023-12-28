import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';

export interface IEscrowAccountDetails {
  id: number;
  escrowDetailsId?: number | null;
  tenantId?: number | null;
  customerId?: number | null;
  accName?: string | null;
  ifscCode?: string | null;
  virtualAccNumber?: string | null;
  poolingAccNumber?: number | null;
  disbursement?: Pick<IDisbursement, 'id' | 'dbmtRequestId'> | null;
  repayment?: Pick<IRepayment, 'id' | 'repaymentId'> | null;
}

export type NewEscrowAccountDetails = Omit<IEscrowAccountDetails, 'id'> & { id: null };
