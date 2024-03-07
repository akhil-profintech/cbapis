import { ITradeEntity, NewTradeEntity } from './trade-entity.model';

export const sampleWithRequiredData: ITradeEntity = {
  id: 30244,
};

export const sampleWithPartialData: ITradeEntity = {
  id: 978,
  entityId: 24077,
  entityUlidId: 'trove bleak',
  entityName: 'coop scorch',
  entityGST: 'woot',
};

export const sampleWithFullData: ITradeEntity = {
  id: 19211,
  entityId: 11680,
  entityUlidId: 'criminal razor that',
  entityName: 'how prestigious',
  entityDesc: 'exactly',
  entityGST: 'gosh chunder rundown',
};

export const sampleWithNewData: NewTradeEntity = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
