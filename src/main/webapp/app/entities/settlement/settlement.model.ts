import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { IFinanceRequest } from 'app/entities/finance-request/finance-request.model';

export interface ISettlement {
  id: number;
  settlementId?: number | null;
  settlementUlidId?: string | null;
  settlementRefNo?: string | null;
  acceptedOfferUlidId?: string | null;
  placedOfferUlidId?: string | null;
  reqOffUlidId?: string | null;
  dbmtRequestId?: number | null;
  dbmtId?: number | null;
  dbmtDate?: string | null;
  dbmtStatus?: string | null;
  dbmtAmount?: number | null;
  currency?: string | null;
  recordStatus?: string | null;
  participantSettlements?: Pick<IParticipantSettlement, 'id'>[] | null;
  financerequest?: Pick<IFinanceRequest, 'id' | 'financeRequestId'> | null;
}

export type NewSettlement = Omit<ISettlement, 'id'> & { id: null };
