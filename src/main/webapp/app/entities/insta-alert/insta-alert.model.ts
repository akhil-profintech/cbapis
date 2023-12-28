import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';

export interface IInstaAlert {
  id: number;
  instaAlertId?: number | null;
  finReqId?: string | null;
  subGrpId?: string | null;
  customerCode?: string | null;
  customerName?: string | null;
  productCode?: string | null;
  poolingAccountNumber?: string | null;
  transactionType?: string | null;
  batchAmt?: number | null;
  batchAmtCcd?: string | null;
  creditDate?: string | null;
  vaNumber?: string | null;
  utrNo?: string | null;
  creditGenerationTime?: string | null;
  remitterName?: string | null;
  remitterAccountNumber?: string | null;
  ifscCode?: string | null;
  lastupdatedDateTime?: string | null;
  lastUpdatedBy?: string | null;
  tradeEntity?: Pick<ITradeEntity, 'id' | 'entityId'> | null;
}

export type NewInstaAlert = Omit<IInstaAlert, 'id'> & { id: null };
