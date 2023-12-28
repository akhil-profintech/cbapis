import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';

export interface IBeneValidation {
  id: number;
  benevalidationId?: string | null;
  finReqId?: string | null;
  subGrpId?: string | null;
  remitterName?: string | null;
  remitterMobileNumber?: string | null;
  debtorAccountId?: string | null;
  accountType?: string | null;
  creditorAccountId?: string | null;
  ifscCode?: string | null;
  paymentDescription?: string | null;
  transactionReferenceNumber?: string | null;
  merchantCode?: string | null;
  identifier?: string | null;
  requestDateTime?: string | null;
  metaDataStatus?: string | null;
  metaDataMessage?: string | null;
  metaDataVersion?: string | null;
  metaDataTime?: string | null;
  resourceDataCreditorAccountId?: string | null;
  resourceDataCreditorName?: string | null;
  resourceDataTransactionReferenceNumber?: string | null;
  resourceDataTransactionId?: string | null;
  resourceDataResponseCode?: string | null;
  resourceDataTransactionTime?: string | null;
  resourceDataIdentifier?: string | null;
  responseDateTime?: string | null;
  lastupdatedDateTime?: string | null;
  lastUpdatedBy?: string | null;
  tradeEntity?: Pick<ITradeEntity, 'id' | 'entityId'> | null;
}

export type NewBeneValidation = Omit<IBeneValidation, 'id'> & { id: null };
