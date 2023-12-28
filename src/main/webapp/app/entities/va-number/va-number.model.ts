import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';

export interface IVANumber {
  id: number;
  vaId?: number | null;
  poolingAccNumber?: string | null;
  virtualAccNumber?: string | null;
  vaStatus?: string | null;
  financeRequestId?: string | null;
  subGroupId?: string | null;
  tradeEntity?: Pick<ITradeEntity, 'id' | 'entityId'> | null;
}

export type NewVANumber = Omit<IVANumber, 'id'> & { id: null };
