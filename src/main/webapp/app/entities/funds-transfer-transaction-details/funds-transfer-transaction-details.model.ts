import { IParticipantSettlement } from 'app/entities/participant-settlement/participant-settlement.model';
import { IDisbursement } from 'app/entities/disbursement/disbursement.model';
import { IRepayment } from 'app/entities/repayment/repayment.model';

export interface IFundsTransferTransactionDetails {
  id: number;
  ftTrnxDetailsId?: number | null;
  ftTrnxDetailsUlid?: string | null;
  transactionId?: number | null;
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
  participantsettlement?: Pick<IParticipantSettlement, 'id' | 'participantSettlementId'> | null;
  disbursement?: Pick<IDisbursement, 'id' | 'dbmtId'> | null;
  repayment?: Pick<IRepayment, 'id' | 'repaymentId'> | null;
}

export type NewFundsTransferTransactionDetails = Omit<IFundsTransferTransactionDetails, 'id'> & { id: null };
