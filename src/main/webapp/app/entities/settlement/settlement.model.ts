import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface ISettlement {
  id: number;
  settlementId?: string | null;
  settlementRefNo?: string | null;
  offerId?: number | null;
  dbmtRequestId?: number | null;
  dbmtId?: number | null;
  dbmtDate?: string | null;
  dbmtStatus?: string | null;
  dbmtAmount?: number | null;
  currency?: string | null;
  participantSettlements?: Pick<IParticipantSettlement, 'id'>[] | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'requestId'> | null;
}

export type NewSettlement = Omit<ISettlement, 'id'> & { id: null };
