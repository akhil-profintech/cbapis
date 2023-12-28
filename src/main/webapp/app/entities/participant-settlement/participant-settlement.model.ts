import { IFTTransactionDetails } from 'app/entities/ft-transaction-details/ft-transaction-details.model';
import { IDocDetails } from 'app/entities/doc-details/doc-details.model';
import { ISettlement } from 'app/entities/settlement/settlement.model';

export interface IParticipantSettlement {
  id: number;
  participantSettlementId?: number | null;
  participantId?: number | null;
  participantName?: string | null;
  gstId?: string | null;
  settlementType?: string | null;
  settlementAmount?: number | null;
  settlementDate?: string | null;
  settlementDueDate?: string | null;
  settlementContactInfo?: string | null;
  patronOfPayment?: string | null;
  recipientOfPayment?: string | null;
  settlementMethod?: string | null;
  tenantId?: number | null;
  accName?: string | null;
  ifscCode?: string | null;
  accNumber?: number | null;
  docId?: string | null;
  fTTransactionDetails?: Pick<IFTTransactionDetails, 'id'>[] | null;
  docDetails?: Pick<IDocDetails, 'id'>[] | null;
  settlement?: Pick<ISettlement, 'id' | 'settlementId'> | null;
}

export type NewParticipantSettlement = Omit<IParticipantSettlement, 'id'> & { id: null };
