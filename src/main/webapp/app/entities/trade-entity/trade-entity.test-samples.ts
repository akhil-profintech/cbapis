import { ITradeEntity, NewTradeEntity } from './trade-entity.model';

export const sampleWithRequiredData: ITradeEntity = {
  id: 9506,
};

export const sampleWithPartialData: ITradeEntity = {
  id: 25287,
  entityName: 'as',
  entityDesc: 'officially exhibition',
  entityGST: 'embed dice adept',
};

export const sampleWithFullData: ITradeEntity = {
  id: 21361,
  entityId: 5320,
  entityName: 'impala verbally',
  entityDesc: 'cheerfully favorable',
  entityGST: 'cream tenement yearly',
};

export const sampleWithNewData: NewTradeEntity = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
