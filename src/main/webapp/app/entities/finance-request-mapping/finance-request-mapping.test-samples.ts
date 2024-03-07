import { IFinanceRequestMapping, NewFinanceRequestMapping } from './finance-request-mapping.model';

export const sampleWithRequiredData: IFinanceRequestMapping = {
  id: 6205,
};

export const sampleWithPartialData: IFinanceRequestMapping = {
  id: 11664,
  financeRequestId: 29218,
  anchorTraderTenantId: 'because an',
};

export const sampleWithFullData: IFinanceRequestMapping = {
  id: 6647,
  financeRequestId: 3996,
  financeRequestMappingUlidId: 'debar apropos',
  anchorTraderId: 'although swiftly daintily',
  financePartnerId: 'gah endoderm',
  anchorTraderTenantId: 'jealously perfectly',
  financePartnerTenantId: 'descry',
};

export const sampleWithNewData: NewFinanceRequestMapping = {
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
