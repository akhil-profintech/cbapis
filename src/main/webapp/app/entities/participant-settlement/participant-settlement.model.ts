import { IFundsTransferTransactionDetails } from 'app/entities/funds-transfer-transaction-details/funds-transfer-transaction-details.model';
import { ISettlement } from 'app/entities/settlement/settlement.model';
import { SettlementType } from 'app/entities/enumerations/settlement-type.model';

export interface IParticipantSettlement {
  id: number;
  participantSettlementId?: number | null;
  participantSettlementUlidId?: string | null;
  participantId?: number | null;
  participantName?: string | null;
  gstId?: string | null;
  settlementType?: keyof typeof SettlementType | null;
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
  fundsTransferTransactionDetails?: Pick<IFundsTransferTransactionDetails, 'id'>[] | null;
  settlement?: Pick<ISettlement, 'id' | 'settlementId'> | null;
}

export type NewParticipantSettlement = Omit<IParticipantSettlement, 'id'> & { id: null };
