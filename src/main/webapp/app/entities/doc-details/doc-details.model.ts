import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';

export interface IDocDetails {
  id: number;
  docDetailsId?: number | null;
  docRecordId?: number | null;
  link?: string | null;
  description?: string | null;
  docType?: string | null;
  status?: string | null;
  disbursement?: Pick<IDisbursement, 'id' | 'dbmtRequestId'> | null;
  repayment?: Pick<IRepayment, 'id' | 'repaymentId'> | null;
  participantsettlement?: Pick<IParticipantSettlement, 'id' | 'participantSettlementId'> | null;
}

export type NewDocDetails = Omit<IDocDetails, 'id'> & { id: null };
