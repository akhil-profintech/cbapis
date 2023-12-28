import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';

export interface IFundsTransfer {
  id: number;
  fundsTransferId?: string | null;
  fundsTransferRefNo?: string | null;
  finReqId?: string | null;
  subGrpId?: string | null;
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
  messageType?: string | null;
  transactionDateTime?: string | null;
  transactionRefNo?: string | null;
  trnxMetaDataStatus?: string | null;
  trnxMetaDataMessage?: string | null;
  trnxMetaDataVersion?: string | null;
  trnxMetaDataTime?: string | null;
  trnxResourceDataBeneficiaryName?: string | null;
  trnxResourceDataTransactionId?: string | null;
  trnxResourceDataStatus?: string | null;
  fundsTransferStatus?: string | null;
  lastupdatedDateTime?: string | null;
  lastUpdatedBy?: string | null;
  tradeEntity?: Pick<ITradeEntity, 'id' | 'entityId'> | null;
}

export type NewFundsTransfer = Omit<IFundsTransfer, 'id'> & { id: null };
