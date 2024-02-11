import { IFinanceRequestMapping, NewFinanceRequestMapping } from './finance-request-mapping.model';

export const sampleWithRequiredData: IFinanceRequestMapping = {
  id: 10134,
};

export const sampleWithPartialData: IFinanceRequestMapping = {
  id: 16207,
  financeRequestId: 'defiantly jovially because',
  anchorTraderId: 'softly out instead',
};

export const sampleWithFullData: IFinanceRequestMapping = {
  id: 29145,
  financeRequestId: 'stealthily instead stucco',
  anchorTraderId: 'worthwhile because',
  financePartnerId: 'at gah prostanoid',
  anchorTraderTenantId: 'wherever mellow transfix',
  financePartnerTenantId: 'below fit how',
};

export const sampleWithNewData: NewFinanceRequestMapping = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
