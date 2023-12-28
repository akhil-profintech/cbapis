import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';
import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';

export interface IFTTransactionDetails {
  id: number;
  trnxDetailsId?: number | null;
  transactionID?: number | null;
  debitAccountNumber?: string | null;
  creditAccountNumber?: string | null;
  remitterName?: string | null;
  amount?: number | null;
  currency?: string | null;
  transactionType?: string | null;
  paymentDescription?: string | null;
  beneficiaryIFSC?: string | null;
  beneficiaryName?: string | null;
  beneficiaryAddress?: string | null;
  emailId?: string | null;
  mobileNo?: number | null;
  transactionRefNo?: string | null;
  trnxResourceDataStatus?: string | null;
  trnxMetaDataStatus?: string | null;
  transactionDateTime?: string | null;
  disbursement?: Pick<IDisbursement, 'id' | 'ftTrnxDetailsId'> | null;
  repayment?: Pick<IRepayment, 'id' | 'ftTrnxDetailsId'> | null;
  participantsettlement?: Pick<IParticipantSettlement, 'id' | 'participantSettlementId'> | null;
}

export type NewFTTransactionDetails = Omit<IFTTransactionDetails, 'id'> & { id: null };
