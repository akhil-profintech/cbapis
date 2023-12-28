import { ITradeEntity } from 'app/entities/trade-entity/trade-entity.model';

export interface IUpdateVA {
  id: number;
  updateVirAccId?: number | null;
  finReqId?: string | null;
  subGrpId?: string | null;
  msgId?: string | null;
  cnvId?: string | null;
  extRefId?: string | null;
  bizObjId?: string | null;
  appId?: string | null;
  timestamp?: string | null;
  vaNum?: string | null;
  finPar?: string | null;
  clientCode?: string | null;
  requestDateTime?: string | null;
  reslt?: string | null;
  status?: string | null;
  statusDesc?: string | null;
  errorCode?: string | null;
  responseDateTime?: string | null;
  lastupdatedDateTime?: string | null;
  lastUpdatedBy?: string | null;
  tradeEntity?: Pick<ITradeEntity, 'id' | 'entityId'> | null;
}

export type NewUpdateVA = Omit<IUpdateVA, 'id'> & { id: null };
